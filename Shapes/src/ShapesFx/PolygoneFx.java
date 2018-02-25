package ShapesFx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import Bars.Bars;
import Shapes.AbstractShapes;
import Shapes.MyShapes;

public class PolygoneFx implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double X = 0;
	private double Y = 0;;
	private transient Color color = Color.BLACK;
	private double width = 20;
	private double height = 20;
	private int numberOfPoints;
	List<Double> Points = new ArrayList<>();
	private int cote = 5;
	private double rotation = 0;
	@SuppressWarnings("unused")
	private double translation = 0;
	private double rotation_center_x = width / 2;
	private double rotation_center_y = height / 2;

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

	public void setposition(double x, double y, Object shapy) {
		((Polygon) shapy).setTranslateX(x);
		((Polygon) shapy).setTranslateY(y);
		setX(x);
		setY(y);

	}

	public void settranslation(double a, Object form) {
		translation = a;
		((Polygon) form).setTranslateY(Y + a);
		((Polygon) form).setTranslateX(X + a);
		setX(X + a);
		setY(Y + a);
	}

	public Shape createform(Color c, Object o) {

		((AbstractShapes) o).setshape(setup());
		return setup();
	}

	public void setWidth(double width, Object form) {
		this.width = width;
		((Polygon) form).prefWidth(width);
		((Polygon) form).getPoints().removeAll(Points);
		Points = new ArrayList<>();
		setNumberOfPoints(cote);
		((Polygon) form).getPoints().addAll(Points);
	}

	public void setHeight(double height, Object form) {
		this.height = height;
		((Polygon) form).prefHeight(height);
		((Polygon) form).getPoints().removeAll(Points);
		Points = new ArrayList<>();
		setNumberOfPoints(cote);
		((Polygon) form).getPoints().addAll(Points);

	}

	public void setcolor(Color c, Object shapes) {
		color = c;
		((Polygon) shapes).setFill(c);
		((Polygon) shapes).getPoints().removeAll(Points);
		Points = new ArrayList<>();
		setNumberOfPoints(cote);
		((Polygon) shapes).getPoints().addAll(Points);

	}

	public void setrotation(double rotation, Object form) {
		this.rotation = rotation;
		((Polygon) form).getTransforms().add(
				new Rotate(rotation, rotation_center_x, rotation_center_y));
		((Polygon) form).getPoints().removeAll(Points);
		Points = new ArrayList<>();
		setNumberOfPoints(cote);
		((Polygon) form).getPoints().addAll(Points);
	}

	public void setRotation_center_x(double rotationx) {
		this.rotation_center_x = rotationx;

	}

	public void setRotation_center_y(double rotationy) {
		this.rotation_center_y = rotationy;

	}

	public void setcote(Object shapes, int nb) {
		cote = nb;
		((Polygon) shapes).getPoints().removeAll(Points);
		Points = new ArrayList<>();
		setNumberOfPoints(cote);
		((Polygon) shapes).getPoints().addAll(Points);

	}

	public void draw(Bars b, MyShapes m) {
		((Pane) b.getbar()).getChildren().add(
				(Node) ((AbstractShapes) m).getshape());
		b.getList().setelement(((AbstractShapes) m).getshape(), m);
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

	public void setNumberOfPoints(int n) {
		numberOfPoints = n;
		double angle = Math.PI * 2 / numberOfPoints;
		for (int ii = 0; ii < numberOfPoints; ii++) {
			double a = angle * ii;

			double x = (Math.cos(a) * width) + width;
			double y = (Math.sin(a) * width) + width;
			Points.add(x);
			Points.add(y);
		}
	}

	private Polygon setup() {
		Polygon s = new Polygon();
		Points = new ArrayList<>();
		setNumberOfPoints(cote);
		s.getPoints().addAll(Points);
		s.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Dragboard db = s.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("Polygone");
				db.setContent(content);

			}
		});
		s.prefWidth(width);
		s.prefHeight(height);
		s.setTranslateY(Y);
		s.setTranslateX(X);
		s.setFill(color);
		s.getTransforms().add(
				new Rotate(rotation, rotation_center_x, rotation_center_y));
		return s;
	}

	public boolean isInside(double x1, double y1, double x2, double y2) {
		return (x1 < X && X < x2) && (y1 < Y && Y < y2);
	}
}
