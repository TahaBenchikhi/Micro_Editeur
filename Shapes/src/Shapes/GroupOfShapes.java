package Shapes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import Util.GroupDialog;
import Adapter.GroupeAdapter;

public class GroupOfShapes extends AbstractShapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MyShapes> list;

	public GroupOfShapes(String str) {
		list = new ArrayList<MyShapes>();
		adapt = new GroupeAdapter();
		shape = adapt.Initialise(str);
		s = str;
	}

	public void addShape(MyShapes a) {
		((GroupeAdapter) adapt).addShape(a, s);
		list.add(a);

	}

	public ArrayList<MyShapes> getelements() {
		list = ((GroupeAdapter) adapt).getElements(s);
		return list;

	}
	public void setelements(ArrayList<MyShapes> elements)
	{
		((GroupeAdapter) adapt).setelements(elements, s);
	}
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	@Override
	public void getProprietys() {
		Map<String, String>  propertys = new LinkedHashMap() {
			{
				put("X",String.valueOf(X));
				put("Y",String.valueOf(Y));
				put("Width",String.valueOf(width));
				put("Height",String.valueOf(height));
				put("Rotation",String.valueOf(rotation));
				put("Color",String.valueOf(color));
				put("RotationCenterX",String.valueOf(rotation_center_x));
				put("RotationCenterY",String.valueOf(rotation_center_y));
			}
		};
		new GroupDialog(propertys,this);
		
	}

}
