package Util;

import Bars.LeftBar;
import Bars.Paper;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Trash {
	VBox trash;

	public Trash() {
		trash = new VBox();
		trash.setPrefHeight(200);
		trash.setPrefWidth(100);
		trash.setStyle("-fx-background-image: url('file:trash.png');-fx-background-position: left top, center;-fx-background-repeat: no-repeat;-fx-background-size: 70%, 70%;");
		trash.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != trash
						&& event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				Object e = event.getGestureSource();
				Node a = (Node) e;
				if (a.getParent().equals(LeftBar.getInstance("JavaFx").getbar())) {
					((Pane) LeftBar.getInstance("JavaFx").getbar()).getChildren().remove(a);
					 LeftBar.getInstance("JavaFx").elements.remove(LeftBar.getInstance("JavaFx").getList().getelement(a));

				} else {

					((Pane) Paper.getInstance("JavaFx").getbar()).getChildren().remove(a);
					Paper.getInstance("JavaFx").SaveState();
				}
			}
		});
		trash.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != trash
						&& event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

	}

	public Object getTrash() {
		return trash;
	}
	public void draw(Object o) {
		((VBox) o).getChildren().add(trash);
	}
}
