package event;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class stageMove {
	private double x=0;
	private double y=0;
	private Stage stage;
	
	
	public stageMove(AnchorPane ap) {
		ap.setOnMousePressed((event) -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});
			    
		ap.setOnMouseDragged((event) -> {
			stage = (Stage) ap.getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});
			    
		ap.setOnMouseReleased((event) -> {
			stage = (Stage) ap.getScene().getWindow();
		});
	}
}
