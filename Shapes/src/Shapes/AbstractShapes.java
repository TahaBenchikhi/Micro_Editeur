package Shapes;

import Adapter.*;
import Bars.Bars;
import Bars.LeftBar;
import Bars.Paper;
import javafx.scene.paint.Color;

public abstract class AbstractShapes implements MyShapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double X;
	protected double Y;
	protected transient Color color = Color.BLACK;
	protected String color2 ;
	protected transient  Object shape;
	protected  ShapesAdapter adapt;
	protected String s;
	protected double width = 20;
	protected double height = 20;
	protected double rotation_center_x = width / 2;
	protected double rotation_center_y = height / 2;
	protected double rotation;

	public void setposition(double x, double y) {
		adapt.setposition(x, y, s, shape);
		X = x;
		Y = y;

	}

	@Override
	public Object createform(Object a) {
		return adapt.createform(s, color, a);
	}

	public void setcolor(Color c) {
		color = c;
		color2 = String.valueOf(c);
		adapt.setcolor(c, s, shape);

	}

	@Override
	public void draw(Bars b) {
		
		adapt.draw(b, this, s);
		
		if(b==Paper.getInstance("JavaFx"))Paper.getInstance("JavaFx").SaveState();
		if(b==LeftBar.getInstance("JavaFx"))LeftBar.getInstance("JavaFx").SaveState();
			

	}





	public void setshape(Object c) {
		shape = c;

	}

	public double getX() {
		return X;
	}

	public double getY() {
		return Y;
	}

	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();

			createform(a);
			((AbstractShapes) a).setadapter((ShapesAdapter) adapt.clone());

		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	public Color getcolor() {

		return color;
	}

	public Object getshape() {
		return shape;
	}

	public ShapesAdapter getadapter() {
		return adapt;
	}

	public void setadapter(ShapesAdapter a) {
		adapt = a;
	}

	public void setWidth(double width) {
		this.width = width;
		adapt.setWidth(width, s, shape);
	}

	public void setHeight(double height) {
		this.height = height;
		adapt.setHeight(height, s, shape);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	public abstract void getProprietys();
	
	public boolean isInside(double x1,double y1 , double x2 , double y2)
	{
		return adapt.isInside(x1,y1,x2,y2,s);
	}
	public void setrotation(double rotation) {
		this.rotation = rotation;
		adapt.setrotation(rotation, s, shape);
	}
	public double getrotation() {
		return rotation;
	}
	public String getcolorserialisation()
	{
		return color2;
	}

	public double getRotation_center_x() {
		return rotation_center_x;
	}

	public void setRotation_center_x(double rotation_center_x) {
		this.rotation_center_x = rotation_center_x;
		adapt.setRotation_center_x(rotation_center_x,s);
	}

	public double getRotation_center_y() {
		return rotation_center_y;
	}

	public void setRotation_center_y(double rotation_center_y) {
		this.rotation_center_y = rotation_center_y;
		adapt.setRotation_center_y(rotation_center_y,s);
	}
	



}
