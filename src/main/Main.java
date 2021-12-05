package main;

import event.enterEvent;
import event.stageMove;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/loginForm.fxml"));
		Parent loginForm = loader.load();
		
		Controller ctrl = new Controller();
		ctrl.setLoginCtrl(loader.getController());
		ctrl.getLoginCtrl().setLoginForm(loginForm);
		
		enterEvent ev = new enterEvent();
		TextField idText = (TextField)loginForm.lookup("#idText");
		PasswordField pwText = (PasswordField)loginForm.lookup("#pwText");
		ev.enterkey(idText,pwText,loginForm, ctrl);
		
		
		AnchorPane ap = (AnchorPane)loginForm.lookup("#ap");
		stageMove move = new stageMove(ap);
		
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		primaryStage.setTitle("Recipe_Book"); // 메인 (로그인) 타이틀 
		primaryStage.setScene(new Scene(loginForm));
		primaryStage.show();
	}

}
