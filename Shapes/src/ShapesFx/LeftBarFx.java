package ShapesFx;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import Shapes.AbstractShapes;
import Shapes.MyShapes;
import Bars.Bars;
import Bars.LeftBar;
import Util.BarMemento;
import Util.Caretaker;
import Util.MappingList;
import Util.Originator;
import javafx.scene.layout.GridPane;
public class LeftBarFx {

	private Pane box;
	private MappingList list;
	private Bars p;

	public Object Initialise(MappingList liste, Object o) {
		list = liste;
		box = new Pane();
		p = (Bars) o;
		
		box.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != box
						&& event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		box.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != box
						&& event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}

				Object re = event.getGestureSource();

				if (((Node) re).getParent().equals(box)) {
					MyShapes s = list.getelement(re);
					box.getChildren().remove(((AbstractShapes) s).getshape());
					p.elements.remove(s);
					AbstractShapes hu = (AbstractShapes) s.clone();
					hu.setposition(event.getX(), event.getY());
					hu.draw(p);

				} else {

					AbstractShapes target = (AbstractShapes) list.getelement(
							re).clone();

					target.setposition(event.getX(), event.getY());

					target.draw(p);
					

				}

				event.setDropCompleted(true);
				event.consume();

			}
		});
		box.setPrefHeight(362.0);
		box.setPrefWidth(531.0);
		return box;
	}

	public void setbar(Pane s) {
		box = s;
	}

	public Pane getbar() {
		box.setPrefHeight(200.0);
		box.setPrefWidth(100.0);
		return box;
	}


	public MappingList getList() {
		return list;
	}
	public void SaveState(Bars bar) {
		TopBarFx.cnt = 1;
		LeftBar barcopy = (LeftBar) bar;
		ArrayList<MyShapes> result = new ArrayList<MyShapes>();
		for (Node node : ((Pane) barcopy.getbar()).getChildren()) {
			if(node instanceof Group)
			{
				MyShapes shape = bar.getList().getelement(node);
				list.setelement(((AbstractShapes)shape).getshape(), shape);
				result.add(shape);

			}
			else
			{
				MyShapes shape = (MyShapes) bar.getList().getelement(node).clone();
				list.setelement(((AbstractShapes)shape).getshape(), shape);
				result.add(shape);

			}
			
		}
		barcopy.elements = result;
//		Originator origin = new Originator();
//		origin.set(bar);
//		BarMemento memento = origin.storeInMemento();
//		Caretaker.getInstance().addMemento(memento);
//		
	}
	public Pane createform(Color c, Object o) {

		Pane s = new Pane();
		s.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = s.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("LeftBar");
				db.setContent(content);
			}

		});

		s.setPrefHeight(362.0);
		s.setPrefWidth(531.0);

		((Bars) o).setshape(s);
		return s;
	}
	public void draw(Object o) {
		((GridPane) o).add(box, 1, 0);
	}

}
