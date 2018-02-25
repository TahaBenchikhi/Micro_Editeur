package Adapter;

import javafx.scene.paint.Color;
import Bars.Bars;
import Shapes.MyShapes;
import ShapesFx.RectangleFx;

public class RectangleAdapter implements ShapesAdapter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RectangleFx rectanglefx;

	public RectangleAdapter() {
		rectanglefx = new RectangleFx();
	}

	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();
			((RectangleAdapter) a).rectanglefx = (RectangleFx) rectanglefx
					.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	@Override
	public void draw(Bars b, MyShapes myshape, String s) {
		if (s.equals("JavaFx")) {
			rectanglefx.draw(b, myshape);
		}
	}



	@Override
	public Object Initialise(String s) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = rectanglefx.Initialise();
		}
		return result;
	}

	@Override
	public void setposition(double x, double y, String s, Object o) {
		if (s.equals("JavaFx")) {
			rectanglefx.setposition(x, y, o);
		}
	}

	@Override
	public Object createform(String s, Color c, Object o) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = rectanglefx.createform(c, o);
		}
		return result;
	}

	@Override
	public void setcolor(Color c, String s, Object o) {
		if (s.equals("JavaFx")) {
			rectanglefx.setcolor(c, o);
		}

	}

	@Override
	public void setWidth(double width, String s, Object form) {
		if (s.equals("JavaFx")) {
			rectanglefx.setWidth(width, form);
		}
	}

	@Override
	public void setHeight(double height, String s, Object form) {
		if (s.equals("JavaFx")) {
			rectanglefx.setHeight(height, form);
		}
	}

	public void settranslation(double a, String s, Object form) {
		if (s.equals("JavaFx")) {
			rectanglefx.settranslation(a, form);
		}
	}

	public void setBorder_radius(double border_radius, String s, Object form) {
		if (s.equals("JavaFx")) {
			rectanglefx.setBorder_radius(border_radius, form);
		}
	}

	@Override
	public void setrotation(double rotation, String s, Object form) {
		if (s.equals("JavaFx")) {
			rectanglefx.setrotation(rotation, form);
		}
	}

	@Override
	public boolean isInside(double x1,double y1 , double x2 , double y2,String s)
	{
		boolean result = false;
		if (s.equals("JavaFx")) {

			result = rectanglefx.isInside(x1,y1,x2,y2);
		}
		return result;
	}

	@Override
	public void setRotation_center_x(double rotation_center_x, String s) {
		if (s.equals("JavaFx")) {
			rectanglefx.setRotation_center_x(rotation_center_x);
		}
		
	}

	@Override
	public void setRotation_center_y(double rotation_center_y, String s) {
		if (s.equals("JavaFx")) {
			rectanglefx.setRotation_center_y(rotation_center_y);
		}
		
	}

}
