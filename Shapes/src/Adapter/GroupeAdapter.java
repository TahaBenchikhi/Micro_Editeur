package Adapter;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import Bars.Bars;
import Shapes.MyShapes;
import ShapesFx.GroupeFx;

public class GroupeAdapter implements ShapesAdapter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GroupeFx groupfx;

	public GroupeAdapter() {
		groupfx = new GroupeFx();
	}

	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();
			((GroupeAdapter) a).groupfx = (GroupeFx) groupfx.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	@Override
	public void draw(Bars b, MyShapes myshape, String s) {
		if (s.equals("JavaFx")) {

			groupfx.draw(b, myshape);
		}
	}



	@Override
	public Object Initialise(String s) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = groupfx.Initialise();
		}
		return result;
	}

	@Override
	public void setposition(double x, double y, String s, Object o) {
		if (s.equals("JavaFx")) {
			groupfx.setposition(x, y, o);
		}
	}

	@Override
	public Object createform(String s, Color c, Object o) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = groupfx.createform(c, o);

		}
		return result;
	}

	@Override
	public void setcolor(Color c, String s, Object o) {
		if (s.equals("JavaFx")) {
			groupfx.setcolor(c, o);
		}

	}

	@Override
	public void setWidth(double width, String s, Object form) {
		if (s.equals("JavaFx")) {
			groupfx.setWidth(width, form);
		}
	}

	@Override
	public void setHeight(double height, String s, Object form) {
		if (s.equals("JavaFx")) {
			groupfx.setHeight(height, form);
		}
	}

	@Override
	public void setrotation(double rotation, String s, Object form) {
		if (s.equals("JavaFx")) {
			groupfx.setrotation(rotation, form);
		}
	}

	public void addShape(MyShapes m, String s) {
		if (s.equals("JavaFx")) {

			groupfx.addShape(m);
		}

	}

	public ArrayList<MyShapes> getElements(String s) {
		ArrayList<MyShapes> result = null;
		if (s.equals("JavaFx")) {

			result = groupfx.getelements();
		}
		return result;
	}

	@Override
	public boolean isInside(double x1,double y1 , double x2 , double y2,String s)
	{
		boolean result = false;
		if (s.equals("JavaFx")) {

			result = groupfx.isInside(x1,y1,x2,y2);
		}
		return result;
	}
	public void setelements(ArrayList<MyShapes> elements,String s)
	{
		if (s.equals("JavaFx")) {

			 groupfx.setelements(elements);
		}
	}
	@Override
	public void setRotation_center_x(double rotation_center_x, String s) {
		if (s.equals("JavaFx")) {
			groupfx.setRotation_center_x(rotation_center_x);
		}
		
	}

	@Override
	public void setRotation_center_y(double rotation_center_y, String s) {
		if (s.equals("JavaFx")) {
			groupfx.setRotation_center_y(rotation_center_y);
		}
		
	}
}
