package event;

import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import login.dto.LoginDTO;
import login.service.LoginServiceImpl;
import main.Controller;
import recipe.dao.recipeDAO;

public class enterEvent {
	
	
	
	public void enterkey(TextField searchtext,ListView<String> list,ListView<String> rank,recipeDAO dao) {
		searchtext.addEventFilter(KeyEvent.KEY_PRESSED,event->{
			if(event.getCode() == KeyCode.ENTER) {
				// ArrayList<String> food = new ArrayList<String>();
				if(searchtext.getText().equals("")) {
					CommonService.Msg("검색할 내용을 입력해주세요.");
				}
				else {
					list.getItems().removeAll(list.getItems());
					rank.getItems().removeAll(rank.getItems());
					dao.searchClickf(searchtext,list);
					dao.searchClickg(searchtext,list);
					dao.ranklist(rank);
				}
				if(!searchtext.getText().equals("") && list.getItems().toString().equals("[]")) {
					CommonService.Msg("해당 검색내용이 존재하지 않습니다.");
				}
			}
		});
	}
	
	public void enterkey(TextField idText,PasswordField pwText,Parent loginForm,Controller ctrl) {
		LoginServiceImpl LoginSvc = new LoginServiceImpl();
		
		idText.addEventFilter(KeyEvent.KEY_PRESSED,event->{
			if(event.getCode() == KeyCode.ENTER) {
				LoginDTO dto = LoginSvc.loginProc(loginForm);
				if(dto != null) {
					CommonService.WindowClose(loginForm);
					ctrl.openForm("레시피 검색",loginForm,dto);
				}
			}
		});
		
		pwText.addEventFilter(KeyEvent.KEY_PRESSED,event->{
			if(event.getCode() == KeyCode.ENTER) {
				LoginDTO dto = LoginSvc.loginProc(loginForm);
				if(dto != null) {
					CommonService.WindowClose(loginForm);
					ctrl.openForm("레시피 검색",loginForm,dto);
				}
			}
		});
	}

}
