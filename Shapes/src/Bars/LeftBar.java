package Bars;

import java.util.ArrayList;

import Adapter.LeftBarAdapter;
import Shapes.MyShapes;
import Util.MappingList;

public class LeftBar extends Bars {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LeftBar instance = null;
	private LeftBar(String str) {

		adapt = new LeftBarAdapter(MappingList.getInstance());
		shape = adapt.Initialise(str, this);
		s = str;
		list = MappingList.getInstance();
		elements = new ArrayList<MyShapes>();
		
		

	}
	 public static LeftBar getInstance(String str) {
		 if(instance == null)
		 {
			 instance = new LeftBar(str);
			 
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
