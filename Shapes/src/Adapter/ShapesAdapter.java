package Adapter;

import java.io.Serializable;
import javafx.scene.paint.Color;
import Bars.Bars;
import Shapes.MyShapes;

public interface ShapesAdapter extends Cloneable,Serializable {

	public void draw(Bars b, MyShapes myshape, String s);

	public Object Initialise(String s);

	public void setposition(double x, double y, String s, Object o);

	public Object createform(String s, Color c, Object o);

	public void setcolor(Color c, String s, Object o);

	public Object clone();

	public void setHeight(double height, String s, Object shape);

	public void setWidth(double width, String s, Object shape);
		
	
	public boolean isInside(double x1,double y1 , double x2 , double y2,String s);
	
	public void setrotation(double rotation,String s , Object shape);
	
	public void setRotation_center_x(double rotation_center_x,String s);
	
	public void setRotation_center_y(double rotation_center_y,String s);
}
