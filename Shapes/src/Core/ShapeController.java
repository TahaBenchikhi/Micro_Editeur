package Core;

import java.io.IOException;

import Shapes.PolygoneShape;
import Shapes.RectangleShape;
import Util.Trash;
import Bars.LeftBar;
import Bars.Paper;
import Bars.TopBar;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShapeController {
	@FXML
	private VBox vbox3;
	@FXML
	private VBox vbox1;
	@FXML
	private VBox poubelle;
	@FXML
	private FlowPane Window;
	@FXML
	private GridPane grid;


	@FXML
	public void Start(Stage primaryStage) throws ClassNotFoundException,
	IOException {

		TopBar tool = new TopBar("JavaFx");
		LeftBar leftbar = LeftBar.getInstance("JavaFx");
		Paper whitepaper = Paper.getInstance("JavaFx");
		Trash trash = new Trash();
		
		tool.draw(vbox1);

		leftbar.draw(grid);

		whitepaper.draw(vbox3);

		trash.draw(poubelle);
		
		RectangleShape rectangle2 = new RectangleShape("JavaFx");
		rectangle2.setposition(17, 55);
		rectangle2.setWidth(30);
		rectangle2.setHeight(30);
		rectangle2.setBorder_radius(5);
		rectangle2.setcolor(Color.ORANGE);
		rectangle2.draw(leftbar);

		PolygoneShape ploygone = new PolygoneShape("JavaFx");
		ploygone.setposition(15, 125);
		ploygone.setcolor(Color.web("#007bff"));
		ploygone.draw(leftbar);
		tool.Load();

		primaryStage.setOnCloseRequest(event -> {
			tool.save();
		});

	}
}
