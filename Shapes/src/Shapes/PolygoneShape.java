package Shapes;

import java.util.LinkedHashMap;
import java.util.Map;

import Util.PolygoneDialog;
import Adapter.PolygoneAdapter;

public class PolygoneShape extends AbstractShapes {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cote = 5;
	private double translation = 0;
	public PolygoneShape(String str) {


		adapt = new PolygoneAdapter();
		shape = adapt.Initialise(str);
		s = str;

	}

	public void setcote(int nb) {
		cote = nb;
		((PolygoneAdapter) adapt).setcote(s, shape, nb);

	}
	public int getcote()
	{
		return cote;
	}

	public void settranslation(double a) {
		translation = a;
		((PolygoneAdapter) adapt).settranslation(a, s, shape);
	}

	public double gettranslation()
	{
		return translation;
	}

	@Override
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public void getProprietys() {
		Map<String, String>  propertys =  new LinkedHashMap() {
			{
				put("X",String.valueOf(X));
				put("Y",String.valueOf(Y));
				put("Width",String.valueOf(width));
				put("Height",String.valueOf(height));
				put("Rotation",String.valueOf(rotation));
				put("Color",String.valueOf(color));
				put("Nombre de Cote",String.valueOf(cote));
				put("Transaltion",String.valueOf(translation));
				put("RotationCenterX",String.valueOf(rotation_center_x));
				put("RotationCenterY",String.valueOf(rotation_center_y));
			}
		};
		new PolygoneDialog(propertys,this);
		
	}

}
