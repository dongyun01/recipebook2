package userManage.service;

import java.util.regex.Pattern;

import comments.dao.commentsDAO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userManage.dao.ManageDAO;


public class userManageService implements userManageServiceInterface{
	 private String agepattern = "^\\d*$";
     private String emailpattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

	@Override
	public void exit(Parent userManageForm) {
		Stage stage = (Stage)userManageForm.getScene().getWindow();
	    stage.close();
	}

	@Override
	public void delete(String id)  {
		ManageDAO dao = new ManageDAO();
		CommonService.Msg(id + " 계정이 삭제되었습니다.");
		dao.delete(id);
	}
	@Override
	public void ageUpdate(String id, Parent userManageForm) {
		TextField ageText = (TextField)userManageForm.lookup("#ageText");
		
		if(ageText.getLength()>9) {
			ageText.setText("");
			CommonService.Msg("10자 제한");
		}else {
		String age = ageText.getText();
		ManageDAO dao = new ManageDAO();
		if(age.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
			if(Pattern.matches(agepattern, age) == false) {
				CommonService.Msg("숫자만 입력할 수 있습니다.");
				ageText.clear();
			} else {
				dao.ageUpdate(id, age);
				CommonService.Msg(id + " 계정의 나이가 " + age + "(으)로 변경되었습니다.");
				ageText.clear();
			}
		}
		}
	}

	@Override
	public void nameUpdate(String id, Parent userManageForm) {
		TextField nameText = (TextField)userManageForm.lookup("#nameText");
		
		if(nameText.getLength()>14) {
			nameText.setText("");
			CommonService.Msg("15자 제한");
		}else {
		
		String name = nameText.getText();
		ManageDAO dao = new ManageDAO();
		if(name.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
		dao.nameUpdate(id, name);
		CommonService.Msg(id + " 계정의 이름이 " + name + "(으)로 변경되었습니다.");
		nameText.clear();
		}
		}

	}

	@Override
	public void passUpdate(String id, Parent userManageForm) {
		TextField passText = (TextField)userManageForm.lookup("#passText");
		
		if(passText.getLength()>19) {
			passText.setText("");
			CommonService.Msg("20자 제한");
		}else {
		String pass = passText.getText();
		ManageDAO dao = new ManageDAO();
		if(pass.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
		dao.pwUpdate(id, pass);
		CommonService.Msg(id + " 계정의 비밀번호가 " + pass + "(으)로 변경되었습니다.");
		passText.clear();
		}
		}
	}

	@Override
	public void emailUpdate(String id, Parent userManageForm) {
		TextField emailText = (TextField)userManageForm.lookup("#emailText");
		
		if(emailText.getLength()>29) {
			emailText.setText("");
			CommonService.Msg("30자 제한");
		}else {
		String email = emailText.getText();
		ManageDAO dao = new ManageDAO();
		if(email.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
			if(Pattern.matches(emailpattern, email) == false) {
				CommonService.Msg("이메일 형식과 맞지 않습니다.");
				emailText.clear();
			} else {
				dao.emailUpdate(id, email);
				CommonService.Msg(id + " 계정의 이메일이 " + email + "(으)로 변경되었습니다.");
				emailText.clear();
			}
		}
		}
	}

	@Override
	public void nullPointer() {
		CommonService.Msg("테이블에서 계정을 선택 후 수정 버튼을 클릭해주십시오.");
		
	}

}
