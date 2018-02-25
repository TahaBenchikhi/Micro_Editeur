package Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import Bars.Paper;
import Shapes.AbstractShapes;
import Shapes.PolygoneShape;

public class PolygoneDialog implements DialogFactory {

	private Dialog<ButtonType> dialog = new Dialog<>();;
	private Map<String, TextField> Propriety;
	private ArrayList<String> result = new ArrayList<String>();
	private int counter = 0;
	private ArrayList<String> last_state = new ArrayList<String>();
	private PolygoneShape element;

	public PolygoneDialog(Map<String, String> map, AbstractShapes shapes) {
		Propriety = new LinkedHashMap<>();
		element = (PolygoneShape) shapes;
		dialog.setTitle("Proprieter");
		dialog.setHeaderText("Veuillez Saisir vos proprietées");
		ButtonType OK = new ButtonType("OK", ButtonData.OK_DONE);
		ButtonType Cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		dialog.getDialogPane().getButtonTypes().addAll(OK, Cancel);
		Button btOk1 = new Button("Appliquer");

		dialog.setGraphic(btOk1);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			TextField temp = new TextField();
			temp.setText((String) value);
			grid.add(new Label(key + ":"), 0, counter);
			grid.add(temp, 1, counter);
			Propriety.put(key, temp);
			last_state.add((String) value);
			counter++;

		}

		dialog.getDialogPane().setContent(grid);

		// ******************************Appliquer********************
		btOk1.addEventFilter(ActionEvent.ACTION, event -> {

			for (Map.Entry<String, TextField> entry : Propriety.entrySet()) {

				Object value = entry.getValue();
				result.add(((TextField) value).getText());

			}
			setProprietys(result, element);

			result = new ArrayList<String>();

		});
		Optional<ButtonType> resulte = dialog.showAndWait();
		if (resulte.get() == OK) {
			for (Map.Entry<String, TextField> entry : Propriety.entrySet()) {
				Object value = entry.getValue();
				result.add(((TextField) value).getText());

			}
			setProprietys(result, element);
			((Pane) Paper.getInstance("JavaFx").getbar()).getChildren().remove(
					element.getshape());
			Paper.getInstance("JavaFx").elements.remove(element);
			element.draw(Paper.getInstance("JavaFx"));
			result = new ArrayList<String>();
		} else {
			setProprietys(last_state, element);
		}

	}

	public void setProprietys(ArrayList<String> a, Object forms) {
		((PolygoneShape) forms).setposition(Double.parseDouble(a.get(0)),
				Double.parseDouble(a.get(1)));
		((PolygoneShape) forms).setWidth(Double.parseDouble(a.get(2)));
		((PolygoneShape) forms).setHeight(Double.parseDouble(a.get(3)));
		((PolygoneShape) forms).setrotation(Double.parseDouble(a.get(4)));
		((PolygoneShape) forms).setcolor(Color.web(a.get(5)));
		((PolygoneShape) forms).setcote(Integer.parseInt(a.get(6)));
		((PolygoneShape) forms).settranslation(Double.parseDouble(a.get(7)));
		((PolygoneShape) forms).setRotation_center_x(Double.parseDouble(a.get(8)));
		((PolygoneShape) forms).setRotation_center_y(Double.parseDouble(a.get(9)));
	}
}
