package login.service;

import java.io.IOException;

import common.CommonService;
import login.dao.LoginDAO;
import login.dto.LoginDTO;
import main.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService{

	@Override
	public LoginDTO loginProc(Parent loginForm) {
		
		TextField idText = (TextField)loginForm.lookup("#idText");
		PasswordField pwText = (PasswordField)loginForm.lookup("#pwText");
		LoginDAO dao = new LoginDAO();
		LoginDTO dto = dao.selectId(idText.getText());
		
		// 비밀번호 정규식을 건다면 이곳에 if ( -- )
		if(dto != null  && dto.getPw().equals(pwText.getText())) {
			return dto;
		}else if(idText.getText().equals("admin") && pwText.getText().equals("admin")) {
			CommonService.WindowClose(loginForm);
			Controller ctrl = new Controller();
			ctrl.openForm("관리자메뉴", loginForm,dto);
			return null;
		}
		else if(idText.getText().equals("")){
			CommonService.Msg("아이디를 입력하세요");
			idText.requestFocus();
			return null;
		}
		else if(pwText.getText().equals("")) {
			CommonService.Msg("비밀번호를 입력하세요");
			pwText.requestFocus();
			return null;
		}else {
			CommonService.Msg("아이디 혹은 비밀번호가 잘못되었습니다.");
			return null;
		}
		
		
	}

	@Override
	public void cancelProc(Parent loginForm) {
		TextField idText = (TextField)loginForm.lookup("#idText");
		PasswordField pwText = (PasswordField)loginForm.lookup("#pwText");
		idText.clear();
		pwText.clear();
		idText.requestFocus();
	}

	@Override
	public void regOpenProc() {
		// implements Override
		// 기존 코드는 필요없어서 삭제했습니다.
	}
	

	

}
