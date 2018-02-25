package Shapes;

import java.io.Serializable;

import Bars.Bars;

public interface MyShapes extends Cloneable,Serializable {

	public Object clone();

	public void draw(Bars p);

	public Object createform(Object a);
}
