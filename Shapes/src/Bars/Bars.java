package Bars;

import java.io.Serializable;
import java.util.ArrayList;

import Adapter.ToolbarAdapters;
import Shapes.MyShapes;
import Util.MappingList;

public abstract class Bars implements Cloneable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient ToolbarAdapters adapt;
	public ArrayList<MyShapes> elements;
	protected transient Object shape;
	protected String s;
	protected transient MappingList list;

	public abstract Object getbar();

	public abstract MappingList getList();

	public abstract ArrayList<MyShapes> getShapes();
	
	
	public void SaveState() 
	{
		adapt.SaveState(this, s);
		
	}

	public void addtobar(MyShapes a) {
		elements.add(a);
	}

	public Object createform(Object a) {
		return adapt.createform(s, a);
	}

	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();
			ArrayList<MyShapes> temp = new ArrayList<MyShapes>();
			((Bars) a).elements = new ArrayList<MyShapes>();
			for (MyShapes z : elements) {
				temp.add(z);
			}
			((Bars) a).elements = temp;

			createform(a);
			((Bars) a).setadapter((ToolbarAdapters) adapt.clone());

		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	public ToolbarAdapters getadapter() {
		return adapt;
	}

	public void setadapter(ToolbarAdapters a) {
		adapt = a;
	}

	public void setshape(Object c) {
		shape = c;

	}
	public void draw(Object o) 
	{
		 adapt.draw(o,s);
	}
}
