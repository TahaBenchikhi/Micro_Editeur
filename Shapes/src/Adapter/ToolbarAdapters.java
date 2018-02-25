package Adapter;

import Bars.Bars;

public interface ToolbarAdapters extends Cloneable {

	public Object Initialise(String s, Object o);

	public Object clone();

	public Object createform(String s, Object a);
	
	public void SaveState(Bars b,String s);
	
	public void draw(Object o,String s);
}
