package Shapes;

import java.util.LinkedHashMap;
import Util.RectangleDialog;
import Adapter.RectangleAdapter;

public class RectangleShape extends AbstractShapes {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double border_radius;
	private double translation = 0;
	public RectangleShape(String str) {
		adapt = new RectangleAdapter();
		shape = adapt.Initialise(str);
		s = str;

	}

	public double getBorder_radius() {
		return border_radius;
	}

	public void setBorder_radius(double border_radius) {
		this.border_radius = border_radius;
		((RectangleAdapter) adapt).setBorder_radius(border_radius, s, shape);
	}

	public void settranslation(double a) {
		translation = a;
		((RectangleAdapter) adapt).settranslation(a, s, shape);
	}

	public double gettranslation()
	{
		return translation;
	}
	@Override
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public void getProprietys()
	{
		LinkedHashMap  propertys =  new LinkedHashMap() {
			{
				put("X",String.valueOf(X));
				put("Y",String.valueOf(Y));
				put("Width",String.valueOf(width));
				put("Height",String.valueOf(height));
				put("Rotation",String.valueOf(rotation));
				put("Color",String.valueOf(color));
				put("Radius",String.valueOf(border_radius));
				put("Transaltion",String.valueOf(translation));
				put("RotationCenterX",String.valueOf(rotation_center_x));
				put("RotationCenterY",String.valueOf(rotation_center_y));
			}
		};
		new RectangleDialog(propertys,this);
	}

	

}
