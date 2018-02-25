package ShapesFx;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Util.Caretaker;
import Util.Originator;
import Bars.Bars;
import Bars.LeftBar;
import Bars.Paper;
import Shapes.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class TopBarFx {

	Button save;
	Button SaveAs;
	Button Undo;
	Button Redo;
	Button Load;
	private ToolBar tool;
	public static int cnt = 1;

	public Object Initialise() {
		tool = new ToolBar();
		save = new Button("Save");
		SaveAs = new Button("SaveAs");
		Undo = new Button("Undo");
		Redo = new Button("Redo");
		Load = new Button("Load");
		Undo.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				undo();
			}
		});
		Redo.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				redo();
			}
		});
		SaveAs.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				savetoload();
			}
		});
		save.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				save();
			}
		});
		Load.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					LoadWithDialog();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		tool.getItems().addAll(save, SaveAs, Undo, Redo, Load);
		return tool;
	}

	public void save() {

		try {
			savefile(new FileOutputStream("Data.Shapes"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Load() throws ClassNotFoundException, IOException {

		loadfile("Data.Shapes");

	}

	public void LoadWithDialog() throws ClassNotFoundException, IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Ouvrir un fichier");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Shapes File", "*.Shapes"));

		File selectedFile = fileChooser.showOpenDialog(((Node) Paper
				.getInstance("JavaFx").getbar()).getScene().getWindow());
		if (selectedFile != null) {
			try {
				loadfile(selectedFile.getPath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void undo() {

		if (Caretaker.getInstance().getsize() - cnt >= 0
				&& cnt <= Caretaker.getInstance().getsize()) {
			Originator origin = new Originator();
			Bars currentstate = null;
			String classe = origin
					.restoreFromMemento(
							Caretaker.getInstance().getMemento(
									Caretaker.getInstance().getsize() - cnt))
									.getClass().toString();
			if (classe.equals("class Bars.Paper")) {
				currentstate = origin.restoreFromMemento(Caretaker
						.getInstance().getMemento(
								Caretaker.getInstance().getsize() - cnt));
				((Pane) Paper.getInstance("JavaFx").getbar()).getChildren()
				.clear();
				if (!currentstate.getShapes().isEmpty()) {

					for (MyShapes a : currentstate.getShapes()) {
						((Pane) Paper.getInstance("JavaFx").getbar())
						.getChildren().add(
								(Node) ((AbstractShapes) a).getshape());

					}

				}
			} else {
				currentstate = origin.restoreFromMemento(Caretaker
						.getInstance().getMemento(
								Caretaker.getInstance().getsize() - cnt));
				((Pane) LeftBar.getInstance("JavaFx").getbar()).getChildren()
				.clear();
				if (!currentstate.getShapes().isEmpty()) {

					for (MyShapes a : currentstate.getShapes()) {
						((Pane) LeftBar.getInstance("JavaFx").getbar())
						.getChildren().add(
								(Node) ((AbstractShapes) a).getshape());

					}

				}
			}

			cnt++;
			Paper.getInstance("JavaFx").elements = currentstate.elements;

		} else {

			((Pane) Paper.getInstance("JavaFx").getbar()).getChildren().clear();
		}

	}

	public void redo() {
		if (cnt > 1) {

			Originator origin = new Originator();
			Paper currentstate = (Paper) origin.restoreFromMemento(Caretaker
					.getInstance().getMemento(
							Caretaker.getInstance().getsize() - (cnt - 1)));

			if (!currentstate.getShapes().isEmpty()) {
				((Pane) Paper.getInstance("JavaFx").getbar()).getChildren()
				.removeAll(
						((Pane) Paper.getInstance("JavaFx").getbar())
						.getChildren());

				for (MyShapes a : currentstate.getShapes()) {
					((Pane) Paper.getInstance("JavaFx").getbar()).getChildren()
					.add((Node) ((AbstractShapes) a).getshape());

				}

			}
			cnt--;
			Paper.getInstance("JavaFx").elements = currentstate.elements;
		}

	}

	public void saveas() {

	}

	private void loop(MyShapes theshape) {
		((GroupOfShapes) theshape).setshape(theshape.createform(theshape));
		for (MyShapes shape : ((GroupOfShapes) theshape).getelements()) {
			if (shape instanceof GroupOfShapes) {
				loop(shape);
			} else {
				AbstractShapes target = (AbstractShapes) shape;
				target.setshape(target.createform(target));

				target.setHeight(target.getHeight());
				target.setWidth(target.getWidth());
				target.setposition(target.getX(), target.getY());
				target.setcolor(Color.web(target.getcolorserialisation()));
			}

		}
	}

	private void drawElements(ArrayList<MyShapes> array, Bars b) {
		for (MyShapes a : array) {
			AbstractShapes theshape = (AbstractShapes) a;
			theshape.setshape(theshape.createform(theshape));
			if (theshape instanceof GroupOfShapes) {

				loop(theshape);
			} else {
				theshape.setHeight(theshape.getHeight());
				theshape.setWidth(theshape.getWidth());
				theshape.setposition(theshape.getX(), theshape.getY());
				theshape.setcolor(Color.web(theshape.getcolorserialisation()));
			}

			theshape.draw(b);

		}
	}

	public void draw(Object o) {
		((VBox) o).getChildren().add(tool);
	}

	public void savetoload() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Ouvrir un fichier");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Shapes File", "*.Shapes"));

		File selectedFile = fileChooser.showOpenDialog(((Node) Paper
				.getInstance("JavaFx").getbar()).getScene().getWindow());
		if (selectedFile != null) {
			try {
				savefile(new FileOutputStream(selectedFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void savefile(FileOutputStream file) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {

			fout = file;
			oos = new ObjectOutputStream(fout);
			oos.writeObject(LeftBar.getInstance("JavaFx"));
			oos.writeObject(Paper.getInstance("JavaFx"));

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void loadfile(String Path) throws IOException,
	ClassNotFoundException {

		List<Object> results = new ArrayList<Object>();
		FileInputStream fis = new FileInputStream(Path);
		File file = new File(Path);
		if (file.length() > 0) {

			ObjectInputStream ois = new ObjectInputStream(fis);

			try {
				while (true) {
					results.add(ois.readObject());
				}
			} catch (EOFException e) {
				ois.close();
			} finally {

				Paper paper = (Paper) results.get(1);
				ArrayList<MyShapes> paperelements = paper.elements;
				((Pane) Paper.getInstance("JavaFx").getbar()).getChildren()
				.clear();
				drawElements(paperelements, Paper.getInstance("JavaFx"));
				
				
				LeftBar leftbar = (LeftBar) results.get(0);
				ArrayList<MyShapes> leftbarelements = leftbar.elements;
				((Pane) LeftBar.getInstance("JavaFx").getbar()).getChildren()
				.clear();
				drawElements(leftbarelements, LeftBar.getInstance("JavaFx"));
				

			}

		}
	}

}
