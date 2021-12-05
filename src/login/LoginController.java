package login;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import login.dto.LoginDTO;
import login.service.LoginService;
import login.service.LoginServiceImpl;
import main.Controller;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class LoginController implements Initializable{

	private Controller ctrl;
	private Parent loginForm;
	private LoginService loginSvc;

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	public void setLoginForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginSvc = new LoginServiceImpl();
	}


	public void loginProc() {
		LoginDTO dto = loginSvc.loginProc(loginForm);

		if(dto != null) {
			CommonService.WindowClose(loginForm);
			ctrl.openForm("레시피 검색",loginForm,dto);
		}
	}
	public void cancelProc() {
		loginSvc.cancelProc(loginForm);
	}
	public void regOpenProc() {
		LoginDTO dto = new LoginDTO(null, null, null, null, null);
		
		CommonService.WindowClose(loginForm);
		ctrl.openForm("회원가입",loginForm,dto);
	}
	
	public void close() {
		System.exit(0);
	}
}




