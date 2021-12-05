package commentsManage.service;

import commentsManage.dao.commentsManageDAO;
import common.CommonService;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class commentsManageService implements commentsManageServiceInterface{
	
	@Override
	public void delete(String id, String com)  {
		commentsManageDAO dao = new commentsManageDAO();
		CommonService.Msg(id + "가 작성한 댓글 " + "["+com+"]"+"을(를) 삭제하였습니다.");
		dao.delete(id, com);
	}

	@Override
	public void nullPointer() {
		CommonService.Msg("테이블에서 댓글을 선택 후 삭제 버튼을 클릭해주십시오. ");
		
	}
	
	@Override
	public void exit(Parent commentsManageForm) {
		Stage stage = (Stage)commentsManageForm.getScene().getWindow();
	    stage.close();
		
	}
}
