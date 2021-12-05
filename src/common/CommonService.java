package common;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CommonService {
	public static void Msg(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setHeaderText("정보 알림");
		alert.setContentText(contentText);
		alert.show();
	}
	
	public static void WindowClose(ActionEvent event) {
		Parent form = (Parent)event.getSource();
		WindowClose(form);
	}
	public static void WindowClose(Parent form) {
		Stage stage = (Stage)form.getScene().getWindow();
		stage.close();
	}
}
