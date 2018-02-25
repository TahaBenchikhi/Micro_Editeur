package Adapter;
import java.io.IOException;

import ShapesFx.TopBarFx;

public class TopBarAdapter {
	
	TopBarFx topbarfx;
	
	public TopBarAdapter()
	{
		topbarfx = new TopBarFx();
	}
	public Object Initialise(String s) {
		Object result = null;
		if (s.equals("JavaFx")) {
			result = topbarfx.Initialise();
		}
		return result;
	}

	public void save(String s) {
		if (s.equals("JavaFx")) {
			topbarfx.save();
		}
	}
	public void Load(String s) throws ClassNotFoundException, IOException {
		if (s.equals("JavaFx")) {
			topbarfx.Load();
		}
	}

	public void saveas(String s) {
		if (s.equals("JavaFx")) {
			topbarfx.saveas();
		}
	}

	public void undo(String s) {
		if (s.equals("JavaFx")) {
			topbarfx.undo();
		}
	}

	public void redo(String s) {
		if (s.equals("JavaFx")) {
			topbarfx.redo();
		}
	}
	
	public void draw(Object o, String s) {
	
		if (s.equals("JavaFx")) {
			topbarfx.draw(o);
		}
		
	}

}
