package ShapesFx;

import java.io.Serializable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import Bars.Bars;
import Shapes.AbstractShapes;
import Shapes.MyShapes;

public class RectangleFx implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double X = 0;
	private double Y = 0;;
	private transient Color color = Color.BLACK;
	private double width = 20;
	private double height = 20;
	private double border_radius;
	private double rotation = 0;
	private double rotation_center_x = width / 2;
	private double rotation_center_y = height / 2;
	@SuppressWarnings("unused")
	private double translation = 0;

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

	public Object Initialise() {

		return setup();

	}

	public void setposition(double x, double y, Object form) {

		((Rectangle) form).setTranslateY(y);
		((Rectangle) form).setTranslateX(x);
		setY(y);
		setX(x);
	}

	public void settranslation(double a, Object form) {
		translation = a;

		((Rectangle) form).setTranslateY(Y + a);
		((Rectangle) form).setTranslateX(X + a);
		setY(Y + a);
		setX(X + a);
	}

	public Shape createform(Color c, Object o) {

		((AbstractShapes) o).setshape(setup());
		return setup();
	}

	public void setWidth(double width, Object form) {
		this.width = width;
		((Rectangle) form).setWidth(width);
	}

	public void setHeight(double height, Object form) {
		this.height = height;
		((Rectangle) form).setHeight(height);
	}

	public void setBorder_radius(double border_radius, Object form) {
		this.border_radius = border_radius;
		((Rectangle) form).setArcHeight(border_radius);
		((Rectangle) form).setArcWidth(border_radius);
	}

	public void setrotation(double rotation, Object form) {
		this.rotation = rotation;
		((Rectangle) form).getTransforms().add(
				new Rotate(rotation, rotation_center_x, rotation_center_y));

	}

	public void setRotation_center_x(double rotationx) {
		this.rotation_center_x = rotationx;

	}

	public void setRotation_center_y(double rotationy) {
		this.rotation_center_y = rotationy;

	}

	public void setcolor(Color c, Object shapes) {
		color = c;
		((Rectangle) shapes).setFill(c);

	}

	public void draw(Bars b, MyShapes m) {

		((Pane) b.getbar()).getChildren().add(
				(Node) ((AbstractShapes) m).getshape());
		b.getList().setelement(((AbstractShapes) m).getshape(), m);
		b.getShapes().remove(m);
		b.getShapes().add(m);

	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	private Rectangle setup() {
		Rectangle s = new Rectangle();
		s.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = s.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("Rectangle");
				db.setContent(content);
			}

		});

		s.setWidth(width);
		s.setTranslateY(Y);
		s.setTranslateX(X);
		s.setHeight(height);
		s.setFill(color);
		s.setArcHeight(border_radius);
		s.setArcWidth(border_radius);
		s.getTransforms().add(
				new Rotate(rotation, rotation_center_x, rotation_center_y));
		return s;
	}

	public boolean isInside(double x1, double y1, double x2, double y2) {
		return (x1 < X && X < x2) && (y1 < Y && Y < y2);
	}

}
