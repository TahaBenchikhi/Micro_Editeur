package Util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Shapes.MyShapes;

public class MappingList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Object, MyShapes> myMap;
	private static MappingList instance = null;
	private MappingList() {
		myMap = new HashMap<>();
	}
	 public static MappingList getInstance() {
		 if(instance == null)
		 {
			 instance = new MappingList();
			 
		 }
			return instance;
		}

	public void setelement(Object s, MyShapes rec) {
		myMap.put(s, rec);
	}

	public void delteelement(Object s, MyShapes rec) {
		myMap.remove(s, rec);
	}

	public MyShapes getelement(Object s) {
		return myMap.get(s);
	}
}
