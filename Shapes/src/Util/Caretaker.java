package Util;

import java.util.ArrayList;

public class Caretaker {

 ArrayList<BarMemento> bar = new ArrayList<BarMemento>();
	private static Caretaker instance = null;
	private Caretaker()
	{
		
	}
 public void addMemento(BarMemento m) 
 { 
	 bar.add(m); 
	 
 }

 public BarMemento getMemento(int index) 
 { 
	 
	 return bar.get(index);
	 
 
 }
 public int getsize()
 {
	 return bar.size();
 }
 public static Caretaker getInstance() {
	 if(instance == null)
	 {
		 instance = new Caretaker();
		 
	 }
		return instance;
	}
}
