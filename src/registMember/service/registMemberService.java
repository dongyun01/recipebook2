package registMember.service;

import java.util.regex.Pattern;

import common.CommonService;
import event.stageMove;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import registMember.dao.registMemberDAO;
import registMember.dto.registMemberDTO;

public class registMemberService {
   public void insertProc(Parent regForm,Parent loginForm) {
      
      TextField idTxt = (TextField)regForm.lookup("#idTxt");
      PasswordField pwTxt = (PasswordField)regForm.lookup("#pwTxt");
      PasswordField pwvTxt = (PasswordField)regForm.lookup("#pwvTxt");
      TextField nameTxt = (TextField)regForm.lookup("#nameTxt");
      TextField emailTxt = (TextField)regForm.lookup("#emailTxt");
      TextField ageTxt = (TextField)regForm.lookup("#ageTxt");
      String agepattern = "^\\d*$"; 
      String emailpattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
      
      
      String id = idTxt.getText();
      String pw = pwTxt.getText();
      String pwv = pwvTxt.getText();
      String name = nameTxt.getText();
      String email = emailTxt.getText();
      String age = ageTxt.getText();
      
      
      
      // 입력 검증
      if(id.isEmpty() || pw.isEmpty() || pwv.isEmpty() || name.isEmpty() || email.isEmpty() || age.isEmpty()) {
         CommonService.Msg("내용을 모두 입력하세요.");
         return;
      }
      
      // 이메일 검증
      if(Pattern.matches(emailpattern, email) == false) {
         CommonService.Msg("이메일 형식에 맞게 입력해 주세요.");
         return;
      }
      
      // 나이 검증
      if(Pattern.matches(agepattern, age) == false) {
         CommonService.Msg("나이는 숫자만 입력할 수 있습니다.");
         return;
      }
      
   if(idTxt.getLength()>14 || pwTxt.getLength()>19 || nameTxt.getLength()>14 ||
		   emailTxt.getLength()>29 || ageTxt.getLength()>9 || id.equals("admin")) {
  	if(idTxt.getLength()>14) {
		CommonService.Msg("아이디는 15자를 초과할 수 없습니다.");
		idTxt.setText("");
	}
  	if(pwTxt.getLength()>19) {
  		CommonService.Msg("비밀번호는 20자를 초과할 수 없습니다.");
  		pwTxt.setText("");
  	}
  	if(nameTxt.getLength()>14) {
  		CommonService.Msg("이름은 15자를 초과할 수 없습니다.");
  		nameTxt.setText("");
  	}
  	if(emailTxt.getLength()>29) {
  		CommonService.Msg("이메일은 30자를 초과할 수 없습니다.");
  		emailTxt.setText("");
  	}
  	if(ageTxt.getLength()>9) {
  		CommonService.Msg("나이는 10자를 초과할 수 없습니다.");
  		ageTxt.setText("");
  	}
  	if(id.equals("admin")) {
  		CommonService.Msg("해당 ID는 사용하실 수 없습니다.");
  		idTxt.setText("");
  	}
   }else {
      if(pw.equals(pwv)) {
         registMemberDAO dao = new registMemberDAO();
         registMemberDTO check = dao.selectId(id);
         if(check == null) {
            registMemberDTO dto = new registMemberDTO(id, pw, name, email, age);
            dto.setId(id);
            dto.setPw(pw);
            dto.setName(name);
            dto.setEmail(email);
            dto.setAge(age);
            int success = dao.insert(dto);
            if(success == 1) {
              // 회원가입 후 창 닫고 로그인 폼 띄우기
               Stage logStage = new Stage();
               logStage.initStyle(StageStyle.UNDECORATED);
			   logStage.setScene(loginForm.getScene());
			   logStage.show();
			   CommonService.Msg(id + "를(을) 등록 완료 했습니다.");
               CommonService.WindowClose(regForm);
               
               
            }else {
               CommonService.Msg("에러가 발생했습니다.");
            }
         }else {
            CommonService.Msg(id + "는(은) 등록된 계정입니다.");
         }
      }else {
         CommonService.Msg("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
      }
   }
   }

   public void cancelProc(Parent regForm) {
      // 회원 가입 화면을 닫기.
      CommonService.WindowClose(regForm);
      
   }

}
