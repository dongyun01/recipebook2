package registMember;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import registMember.service.registMemberService;

public class registMemberController implements Initializable{
	private Parent regForm;
	private registMemberService regSvc;
	private Parent loginForm;
	
	public void setRegForm(Parent regForm) {
		this.regForm = regForm;
	}
	public Parent getRegForm() {
		return regForm;
	}
	public void setLogForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	public Parent getLogForm(){
		return loginForm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		regSvc = new registMemberService();
	}
	public void insertProc() {
		regSvc.insertProc(regForm,loginForm);
	}
	public void cancelProc() {
		regSvc.cancelProc(regForm);
	}
	
	public void close() {
		System.exit(0);
	}

}
