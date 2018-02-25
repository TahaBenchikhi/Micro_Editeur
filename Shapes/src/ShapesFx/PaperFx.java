package ShapesFx;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import Shapes.AbstractShapes;
import Shapes.GroupOfShapes;
import Shapes.MyShapes;
import Bars.Bars;
import Bars.Paper;
import Util.BarMemento;
import Util.MappingList;
import Util.Originator;
import Util.Caretaker;
import javafx.scene.Group;
import ShapesFx.PaperFx;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;

public class PaperFx implements Cloneable {

	private Pane box;
	private GroupOfShapes Container;
	final ContextMenu cm = new ContextMenu();
	private double x = 99999;
	private double y = 99999;
	private ArrayList<AbstractShapes> elements;
	private Object current;
	private MappingList list;
	private Bars paper;
	private Rectangle selection = new Rectangle();
	private boolean isselection = false;

	public Object Initialise(MappingList liste, Object o) {
		current = null;
		elements = new ArrayList<AbstractShapes>();

		final ColorPicker colorssPicker = new ColorPicker();
		colorssPicker.setStyle("-fx-background-color: white;");

		MenuItem cmItem1 = new MenuItem("De-Group");
		MenuItem cmItem2 = new MenuItem(null, colorssPicker);
		MenuItem cmItem3 = new MenuItem("Fermer");
		MenuItem cmItem4 = new MenuItem("Group");
		MenuItem cmItem5 = new MenuItem("Edit");

		cm.getItems().addAll(cmItem1, cmItem2, cmItem3, cmItem4, cmItem5);

		box = new Pane();
		Container = new GroupOfShapes("JavaFx");

		list = liste;
		paper = (Bars) o;
		cmItem1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ent) {

				Object e = current;
				Container = new GroupOfShapes("JavaFx");
				elements = new ArrayList<AbstractShapes>();
				x = 100000;
				y = 100000;
				Node find = (Node) e;
				while (!find.getParent().equals(box)) {
					find = find.getParent();
				}
				AbstractShapes target = (AbstractShapes) list
						.getelement(find);
				if (find instanceof Group) {
					GroupOfShapes a = ((GroupOfShapes) target);
					if (!(((Group) a.getshape()).getParent() instanceof Group)) {
						for (MyShapes i : a.getelements()) {
							AbstractShapes oui = (AbstractShapes) i.clone();
							oui.setposition(
									((AbstractShapes) i).getX() + a.getX(),
									((AbstractShapes) i).getY() + a.getY());
							oui.draw(paper);

						}
						box.getChildren().remove(target.getshape());

					}
				}

			}
		});

		cmItem2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ent) {

				Object e = current;
				Node find = (Node) e;
				while (!find.getParent().equals(box)) {
					find = find.getParent();
				}
				AbstractShapes target2 = (AbstractShapes) list
						.getelement(find);
				AbstractShapes targetclone = (AbstractShapes) list
						.getelement(find).clone();
				box.getChildren().remove(target2.getshape());
				paper.elements.remove(target2);
				current = targetclone.getshape();
				targetclone.setcolor(colorssPicker.getValue());
				targetclone.draw(paper);
			}
		});
		cmItem5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ent) {

				Object e = current;
				Node find = (Node) e;
				while (!find.getParent().equals(box)) {
					find = find.getParent();
				}
				AbstractShapes target2 = (AbstractShapes) list
						.getelement(find);
				AbstractShapes target2clone = (AbstractShapes) list
						.getelement(find).clone();

				box.getChildren().remove(target2.getshape());
				paper.elements.remove(target2);

				target2clone.draw(paper);

				current = target2clone.getshape();
				target2clone.getProprietys();

			}
		});
		cmItem4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ent) {

				for (AbstractShapes a : elements) {
					if (a.getX() < x)
						x = a.getX();
					if (a.getY() < y)
						y = a.getY();
				}
				for (AbstractShapes m : elements) {
					double tempx = m.getX();
					double tempy = m.getY();
					m.setposition(tempx - x, tempy - y);
					Container.addShape(m);
				}
				Container.setposition(x, y);

				Container.draw(paper);
				Container = new GroupOfShapes("JavaFx");
				elements = new ArrayList<AbstractShapes>();
				x = 100000;
				y = 100000;
			}
		});

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
				box.getChildren().remove(selection);
				Container = new GroupOfShapes("JavaFx");
				elements = new ArrayList<AbstractShapes>();
				x = 100000;
				y = 100000;
				Object noeu = event.getGestureSource();

				if (((Node) noeu).getParent().equals(box)) {
					MyShapes s = list.getelement(noeu);
					box.getChildren().remove(((AbstractShapes) s).getshape());
					paper.elements.remove(s);
					AbstractShapes hu = (AbstractShapes) s.clone();
					hu.setposition(event.getX(), event.getY());
					hu.draw(paper);

				} else {

					AbstractShapes target = (AbstractShapes) list.getelement(
							noeu).clone();

					target.setposition(event.getX(), event.getY());

					target.draw(paper);
					TopBarFx.cnt = 1;

				}

				event.setDropCompleted(true);
				event.consume();
			}
		});

		box.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				Object element = event.getTarget();
				box.getChildren().remove(selection);

				if (event.isSecondaryButtonDown() && !element.equals(box)
						&& element != selection) {
					cm.getItems().remove(cmItem1);
					cm.getItems().remove(cmItem4);
					if (!((Node) element).getParent().equals(box))
						cm.getItems().add(cmItem1);
					if (!elements.isEmpty())
						cm.getItems().add(cmItem4);

					cm.show(box, event.getScreenX(), event.getScreenY());
					Node find = (Node) element;

					while (!find.getParent().equals(box)) {
						find = find.getParent();
					}
					current = find;

				}
				if (event.isSecondaryButtonDown() && !element.equals(box)
						&& element == selection) {

					for (Node node : box.getChildren()) {
						Node find = node;

						while (!find.getParent().equals(box)) {
							find = find.getParent();
						}
						AbstractShapes target = (AbstractShapes) list
								.getelement(find);

						if (target != null
								&& target.isInside(selection.getX(),
										selection.getY(), event.getX(),
										event.getY()) && find != selection) {
							cm.show(box, event.getScreenX(), event.getScreenY());
							break;

						}
					}

				}

				if (event.isPrimaryButtonDown() && element != selection) {

					if (event.isControlDown() && !element.equals(box)
							&& element != selection) {
						box.getChildren().remove(selection);
						if (isselection) {
							elements = new ArrayList<AbstractShapes>();
							isselection = false;
						}

						Node find = (Node) element;

						while (!find.getParent().equals(box)) {
							find = find.getParent();
						}
						AbstractShapes target = (AbstractShapes) list
								.getelement(find);

						elements.add(target);
					}
					if (element.equals(box) && !event.isControlDown()) {
						box.getChildren().remove(selection);
						selection = new Rectangle();
						selection.setX(event.getX());
						selection.setY(event.getY());
						selection.toBack();

					}

				}
				if (element == selection) {

					for (Node node : box.getChildren()) {
						Node find = node;

						while (!find.getParent().equals(box)) {
							find = find.getParent();
						}
						AbstractShapes target = (AbstractShapes) list
								.getelement(find);

						if (target != null
								&& target.isInside(selection.getX(),
										selection.getY(), event.getX(),
										event.getY()) && find != selection) {
							cm.show(box, event.getScreenX(), event.getScreenY());
							break;

						}
					}

				}

			}

		});
		box.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().toString().equals("PRIMARY")
						&& !event.isControlDown()) {

					selection.setWidth(event.getX() - selection.getX());
					selection.setHeight(event.getY() - selection.getY());
					selection.setFill(Color.TRANSPARENT);

					selection.setStroke(Color.BLACK);
					selection.getStrokeDashArray().addAll(2d);
					selection.toBack();
					box.getChildren().add(selection);
					selection.toBack();
					for (Node node : box.getChildren()) {
						Node find = node;

						while (!find.getParent().equals(box)) {
							find = find.getParent();
						}
						AbstractShapes target = (AbstractShapes) list
								.getelement(find);

						if (target != null
								&& target.isInside(selection.getX(),
										selection.getY(), event.getX(),
										event.getY())) {
							elements.add(target);
							isselection = true;

						}
					}

				}
			}

		});
		box.setPrefHeight(362.0);
		box.setPrefWidth(531.0);
		return box;

	}

	public void setbar(Object s) {
		box = (Pane) s;
	}

	public Object getbar() {
		box.setPrefHeight(362.0);
		box.setPrefWidth(531.0);
		return box;
	}

	public Pane createform(Color c, Object form) {

		Pane pane = new Pane();
		pane.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = pane.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("Paper");
				db.setContent(content);
			}

		});

		pane.setPrefHeight(362.0);
		pane.setPrefWidth(531.0);

		((Bars) form).setshape(pane);
		return pane;
	}

	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();

		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	public void SaveState(Bars bar) {
		TopBarFx.cnt = 1;
		Paper barcopy = (Paper) bar;
		ArrayList<MyShapes> result = new ArrayList<MyShapes>();
		for (Node node : ((Pane) barcopy.getbar()).getChildren()) {
			if (node instanceof Group) {
				MyShapes shape = bar.getList().getelement(node);
				list.setelement(((AbstractShapes) shape).getshape(), shape);
				result.add(shape);

			} else {
				MyShapes shape = (MyShapes) bar.getList().getelement(node)
						.clone();
				list.setelement(((AbstractShapes) shape).getshape(), shape);
				result.add(shape);

			}

		}
		barcopy.elements = result;
		Originator origin = new Originator();
		origin.set(bar);
		BarMemento memento = origin.storeInMemento();
		Caretaker.getInstance().addMemento(memento);
	}

	public void draw(Object o) {
		((VBox) o).getChildren().add(box);
	}
}
