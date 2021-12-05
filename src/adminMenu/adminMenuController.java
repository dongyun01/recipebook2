package adminMenu;

import java.io.IOException;

import event.stageMove;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import userManage.ManageController;

public class adminMenuController {
	private Parent adminMenuForm;
	private Parent loginForm;
	
	public void setAdminMenuForm(Parent adminMenuForm) {
		this.adminMenuForm = adminMenuForm;
	}
	public Parent getAdminMenuForm() {
		return adminMenuForm;
	}
	public void setLogForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	public Parent getLogForm(){
		return loginForm;
	}
	
	public void memberOpenProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../userManage/userManage.fxml"));
		try {
			Parent userManageForm = loader.load();
			Stage userStage = new Stage();
			
			ManageController ctrl = loader.getController();
			ctrl.setUserManageForm(userManageForm);
			
			AnchorPane ap = (AnchorPane)userManageForm.lookup("#ap");
			stageMove move = new stageMove(ap);
			
			userStage.initStyle(StageStyle.UNDECORATED);
			userStage.setScene(new Scene(userManageForm));
			userStage.setTitle("회원 관리");
			userStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void foodOpenProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../foodManage/foodManage.fxml"));
		try {
			Parent foodManageForm = loader.load();
			Stage foodStage = new Stage();
			
			foodManage.foodManageController ctrl = loader.getController();
			ctrl.setfoodManageForm(foodManageForm);
			
			AnchorPane ap = (AnchorPane)foodManageForm.lookup("#ap");
			new stageMove(ap);
			foodStage.initStyle(StageStyle.UNDECORATED);
			foodStage.setScene(new Scene(foodManageForm));
			foodStage.setTitle("음식 관리");
			foodStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void commentsOpenProc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../commentsManage/commentsManage.fxml"));
		try {
			Parent commentsManageForm = loader.load();
			Stage commentsStage = new Stage();
			
			commentsManage.ManageController ctrl = loader.getController();
			ctrl.setCommentsManageForm(commentsManageForm);
			
			AnchorPane ap = (AnchorPane)commentsManageForm.lookup("#ap");
			new stageMove(ap);
			commentsStage.initStyle(StageStyle.UNDECORATED);
			commentsStage.setScene(new Scene(commentsManageForm));
			commentsStage.setTitle("댓글 관리");
			commentsStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void close() {
		System.exit(0);
	}

}
