package Bars;

import java.util.ArrayList;

import Shapes.MyShapes;
import Util.MappingList;
import Adapter.PaperAdapter;

public class Paper extends Bars {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Paper instance = null;
	private Paper(String str) {
		adapt = new PaperAdapter(MappingList.getInstance());
		shape = adapt.Initialise(str, this);
		s = str;
		list = MappingList.getInstance();
		elements = new ArrayList<MyShapes>();
	}
	 public static Paper getInstance(String str) {
		 if(instance == null)
		 {
			 instance = new Paper(str);
			 
		 }
			return instance;
		}

	@Override
	public Object getbar() {
		return shape;
	}
	

	@Override
	public MappingList getList() {
		return list;
	}

	@Override
	public ArrayList<MyShapes> getShapes() {

		return elements;
	}



}
