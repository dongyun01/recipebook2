package main;

import login.LoginController;
import login.dto.LoginDTO;
import main.service.MainService;
import event.enterEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;

public class Controller {
	private LoginController loginCtrl;
	private MainService mainSvc;
	private Parent menuForm;

	public Controller() {
		mainSvc = new MainService();
	}
	public void setLoginCtrl(LoginController loginCtrl) {
		this.loginCtrl = loginCtrl;
		this.loginCtrl.setCtrl(this);
	}
	public void setMenuForm(Parent menuForm) {
		this.menuForm = menuForm;
	}

	
	public LoginController getLoginCtrl() {
		return loginCtrl;
	}
	public void openForm(String name,Parent loginForm,LoginDTO dto) {

		if(name.equals("레시피 검색")) {
			mainSvc.setController(this);
			mainSvc.menuOpenForm(loginForm,dto);
		}
		else if(name.equals("회원가입")) {
			mainSvc.regOpenForm(loginForm);
		}else if(name.equals("관리자메뉴")) {
			mainSvc.setController(this);
			mainSvc.adminMenuOpenForm(loginForm);
		}
	}
	
	public void close() {
		System.exit(0);
	}
}
