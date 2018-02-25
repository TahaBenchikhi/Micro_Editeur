package Adapter;

import Bars.Bars;
import ShapesFx.PaperFx;
import Util.MappingList;

public class PaperAdapter implements ToolbarAdapters {
	PaperFx paperfx;
	MappingList list;

	public PaperAdapter(MappingList a) {
		paperfx = new PaperFx();
		list = a;
	}

	@Override
	public Object clone() {
		Object a = null;
		try {

			a = super.clone();
			((PaperAdapter) a).paperfx = (PaperFx) paperfx.clone();
		} catch (CloneNotSupportedException e) {

			e.printStackTrace();
		}

		return a;

	}

	@Override
	public Object createform(String s, Object o) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = paperfx.createform(null, o);
		}
		return result;
	}

	@Override
	public Object Initialise(String s, Object o) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = paperfx.Initialise(list, o);
		}
		return result;
	}



	@Override
	public void SaveState(Bars b,String s) {
		if (s.equals("JavaFx")) {
		paperfx.SaveState(b);
		}
		
	}

	@Override
	public void draw(Object o, String s) {
	
		if (s.equals("JavaFx")) {
			paperfx.draw(o);
		}
		
	}

}
