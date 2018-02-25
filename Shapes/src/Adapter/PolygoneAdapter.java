package Adapter;

import javafx.scene.paint.Color;
import Bars.Bars;
import Shapes.MyShapes;
import ShapesFx.PolygoneFx;

public class PolygoneAdapter implements ShapesAdapter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PolygoneFx polygonefx;

	public PolygoneAdapter() {
		polygonefx = new PolygoneFx();
	}

	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();
			((PolygoneAdapter) a).polygonefx = (PolygoneFx) polygonefx.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	@Override
	public void draw(Bars b, MyShapes myshape, String s) {
		if (s.equals("JavaFx")) {

			polygonefx.draw(b, myshape);
		}
	}


	@Override
	public Object Initialise(String s) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = polygonefx.Initialise();
		}
		return result;
	}

	@Override
	public void setposition(double x, double y, String s, Object o) {
		if (s.equals("JavaFx")) {
			polygonefx.setposition(x, y, o);
		}
	}

	@Override
	public Object createform(String s, Color c, Object o) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = polygonefx.createform(c, o);

		}
		return result;
	}

	public void settranslation(double a, String s, Object form) {
		if (s.equals("JavaFx")) {
			polygonefx.settranslation(a, form);
		}
	}

	@Override
	public void setcolor(Color c, String s, Object o) {
		if (s.equals("JavaFx")) {

			polygonefx.setcolor(c, o);
		}

	}

	@Override
	public void setWidth(double width, String s, Object form) {
		if (s.equals("JavaFx")) {
			polygonefx.setWidth(width, form);
		}
	}

	@Override
	public void setHeight(double height, String s, Object form) {
		if (s.equals("JavaFx")) {
			polygonefx.setHeight(height, form);
		}
	}

	public void setcote(String s, Object shapes, int nb) {
		if (s.equals("JavaFx")) {

			polygonefx.setcote(shapes, nb);

		}

	}

	@Override
	public void setrotation(double rotation, String s, Object form) {
		if (s.equals("JavaFx")) {
			polygonefx.setrotation(rotation, form);
		}
	}

	
	@Override
	public boolean isInside(double x1,double y1 , double x2 , double y2,String s)
	{
		boolean result = false;
		if (s.equals("JavaFx")) {

			result = polygonefx.isInside(x1,y1,x2,y2);
		}
		return result;
	}
	@Override
	public void setRotation_center_x(double rotation_center_x, String s) {
		if (s.equals("JavaFx")) {
			polygonefx.setRotation_center_x(rotation_center_x);
		}
		
	}

	@Override
	public void setRotation_center_y(double rotation_center_y, String s) {
		if (s.equals("JavaFx")) {
			polygonefx.setRotation_center_y(rotation_center_y);
		}
		
	}


}
