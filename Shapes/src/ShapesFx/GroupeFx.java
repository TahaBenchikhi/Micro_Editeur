package ShapesFx;

import java.io.Serializable;
import java.util.ArrayList;

import Bars.Bars;
import Shapes.AbstractShapes;
import Shapes.MyShapes;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import Shapes.GroupOfShapes;

public class GroupeFx implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MyShapes> elements;
	private double X;
	private double Y;
	private transient Group shape;
	private double rotation = 0;
	private double width = 20;
	private double height = 20;
	@SuppressWarnings("unused")
	private transient Color color = Color.BLACK;
	private double rotation_center_x = width / 2;
	private double rotation_center_y = height / 2;
	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();
			ArrayList<MyShapes> e = new ArrayList<MyShapes>();
			for (MyShapes el : elements) {
				AbstractShapes u = (AbstractShapes) el.clone();
				u.setposition(((AbstractShapes) el).getX(),
						((AbstractShapes) el).getY());
				e.add(u);

			}
			((GroupeFx) a).elements = e;

		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	public Object Initialise() {
		elements = new ArrayList<MyShapes>();
		return setup();
	}

	public void setGroup(Group grp) {
		shape = grp;
	}

	public void setposition(double x, double y, Object shapy) {

		((Group) shapy).setTranslateX(x);
		((Group) shapy).setTranslateY(y);
		X = x;
		Y = y;
	}

	public void addShape(MyShapes a) {
		MyShapes b = a;
		elements.add(b);

	}

	public ArrayList<MyShapes> getelements() {

		return elements;

	}

	public void setcolor(Color c, Object shapes) {
		color = c;

		for (MyShapes s : elements) {
			((AbstractShapes) s).setcolor(c);
		}

	}

	public void setWidth(double width, Object form) {
		this.width = width;

		for (MyShapes s : elements) {
			((AbstractShapes) s).setWidth(width);
		}
	}

	public void setHeight(double height, Object form) {
		this.height = height;
		for (MyShapes s : elements) {
			((AbstractShapes) s).setHeight(height);
		}
	}

	public void setrotation(double rotation, Object form) {
		this.rotation = rotation;
		((Group) form).getTransforms().add(new Rotate(rotation, rotation_center_x,rotation_center_y));

	}
	public void setRotation_center_x(double rotationx) {
		this.rotation_center_x = rotationx;
		
	}
	public void setRotation_center_y(double rotationy) {
		this.rotation_center_y = rotationy;
		
	}

	public Object getshape() {

		return shape;
	}

	public Object createform(Color c, Object o) {

		((AbstractShapes) o).setshape(setup());
		return setup();
	}

	public void draw(Bars b, MyShapes m) {

		for (MyShapes a : elements) {
			if (a instanceof GroupOfShapes) {
				a.draw(b);

			}
			Node adding = (Node) ((AbstractShapes) a).getshape();
			((Group) ((AbstractShapes) m).getshape()).getChildren().remove(
					adding);
			((Group) ((AbstractShapes) m).getshape()).getChildren().add(adding);

		}
		((Pane) b.getbar()).getChildren().remove(
				((AbstractShapes) m).getshape());
		((Pane) b.getbar()).getChildren().add(
				(Node) ((AbstractShapes) m).getshape());
		b.getList().setelement(((AbstractShapes) m).getshape(), m);
		b.getShapes().remove(m);
		b.getShapes().add(m);

	}

	private Group setup() {
		Group f = new Group();
		f.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = f.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("Groupe");
				db.setContent(content);

			}
		});
		f.getTransforms().add(new Rotate(rotation, rotation_center_x,rotation_center_y));
		f.setTranslateX(X);
		f.setTranslateY(Y);
		return f;
	}

	public boolean isInside(double x1, double y1, double x2, double y2) {
		return (x1 < X  && X < x2)&&(y1 < Y && Y < y2);
	}

	public void setelements(ArrayList<MyShapes> elements) {
		this.elements = elements;
	}

}
