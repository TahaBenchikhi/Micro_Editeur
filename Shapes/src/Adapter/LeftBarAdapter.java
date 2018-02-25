package Adapter;

import Bars.Bars;
import ShapesFx.LeftBarFx;
import Util.MappingList;

public class LeftBarAdapter implements ToolbarAdapters {

	LeftBarFx leftbarfx;
	MappingList list;

	public LeftBarAdapter(MappingList a) {
		leftbarfx = new LeftBarFx();
		list = a;
	}

	@Override
	public Object Initialise(String s, Object o) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = leftbarfx.Initialise(list, o);
		}
		return result;
	}



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


	@Override
	public Object createform(String s, Object a) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = leftbarfx.createform(null,a);
		}
		return result;
	}

	@Override
	public void SaveState(Bars b,String s) {
		if (s.equals("JavaFx")) {
			leftbarfx.SaveState(b);
		}
		
	}

	@Override
	public void draw(Object o, String s) {
	
		if (s.equals("JavaFx")) {
			leftbarfx.draw(o);
		}
		
	}

}
