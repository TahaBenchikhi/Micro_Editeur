package Bars;

import java.io.IOException;

import Adapter.TopBarAdapter;


public class TopBar {
	private Object form;
	private TopBarAdapter adapter;
	private String str;

	public TopBar(String s) {
		adapter = new TopBarAdapter();
		form = adapter.Initialise(s);
		str = s;

	}

	public void save() {
		adapter.save(str);
	}
	public void Load() throws ClassNotFoundException, IOException {
		adapter.Load(str);
	}

	public void saveas() {
     adapter.saveas(str);
	}

	public void undo() {
		adapter.undo(str);
	}

	public void redo() {
		adapter.redo(str);
	}

	public Object getForm() {
		return form;
	}
	public void draw(Object o) 
	{
		adapter.draw(o,str);
	}

}
