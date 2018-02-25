package Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import Bars.Paper;
import Shapes.AbstractShapes;
import Shapes.RectangleShape;
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

public class RectangleDialog implements DialogFactory {

	private Dialog<ButtonType> dialog = new Dialog<>();;
	private Map<String, TextField> Propriety;
	private ArrayList<String> result = new ArrayList<String>();
	private int counter = 0;
	private ArrayList<String> last_state = new ArrayList<String>();
	private RectangleShape elements;
	public RectangleDialog(Map<String, String> map, AbstractShapes shapes) {
		Propriety = new LinkedHashMap<>();
		elements = (RectangleShape) shapes;
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
			setProprietys(result, elements);

			result = new ArrayList<String>();

		});
		Optional<ButtonType> resulte = dialog.showAndWait();
		if (resulte.get() == OK) {
			for (Map.Entry<String, TextField> entry : Propriety.entrySet()) {
				Object value = entry.getValue();
				result.add(((TextField) value).getText());

			}
			setProprietys(result, elements);
			((Pane) Paper.getInstance("JavaFx").getbar()).getChildren().remove(
					elements.getshape());
			Paper.getInstance("JavaFx").elements.remove(elements);
			elements.draw(Paper.getInstance("JavaFx"));
			result = new ArrayList<String>();
		} else {

			setProprietys(last_state, elements);
			
			
		}

	}

	public void setProprietys(ArrayList<String> a, Object forms) {
		((RectangleShape) forms).setposition(Double.parseDouble(a.get(0)),
				Double.parseDouble(a.get(1)));
		((RectangleShape) forms).setWidth(Double.parseDouble(a.get(2)));
		((RectangleShape) forms).setHeight(Double.parseDouble(a.get(3)));
		((RectangleShape) forms).setrotation(Double.parseDouble(a.get(4)));
		((RectangleShape) forms).setcolor(Color.web(a.get(5)));
		((RectangleShape) forms).setBorder_radius(Double.parseDouble(a.get(6)));
		((RectangleShape) forms).settranslation(Double.parseDouble(a.get(7)));
		((RectangleShape) forms).setRotation_center_x(Double.parseDouble(a.get(8)));
		((RectangleShape) forms).setRotation_center_y(Double.parseDouble(a.get(9)));
	}
}
