package foodManage.service;



import common.CommonService;
import foodManage.dao.foodManageDAO;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class foodManageService implements foodManageServiceInterface{

	@Override
	public void delete(String foodname) {
		foodManageDAO dao = new foodManageDAO();
		CommonService.Msg(foodname + " 레시피가 삭제되었습니다.");
		dao.delete(foodname);
		
	}

	@Override
	public void exit(Parent foodManageForm) {
		Stage stage = (Stage)foodManageForm.getScene().getWindow();
	    stage.close();
		
	}

	@Override
	public void foodNameUpdate(String gredient, Parent foodManageForm) {
		TextField foodnameText = (TextField)foodManageForm.lookup("#foodnameText");
		
		if(foodnameText.getLength()>19) {
			foodnameText.setText("");
			CommonService.Msg("20자 제한입니다.");
		}else {
		
		String foodname = foodnameText.getText();
		foodManageDAO dao = new foodManageDAO();
		if(foodname.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
				dao.foodNameUpdate(foodname, gredient);
				CommonService.Msg("레시피 음식 이름이 " + foodname + "(으)로 변경되었습니다.");
				foodnameText.clear();
			}
		}
	}

	@Override
	public void gredientUpdate(String foodname, Parent foodManageForm)  {
		TextField gredientText = (TextField)foodManageForm.lookup("#gredientText");
		
		if(gredientText.getLength()>199) {
			gredientText.setText("");
			CommonService.Msg("200자 제한입니다.");
		}else {
		String gredient = gredientText.getText();
		foodManageDAO dao = new foodManageDAO();
		if(gredient.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
				dao.gredientUpdate(gredient, foodname);
				CommonService.Msg(foodname + " 레시피의 재료가 " + gredient + "(으)로 변경되었습니다.");
				gredientText.clear();
			}
		}
	}
	
	@Override
	public void mediaUpdate(String foodname, Parent foodManageForm)  {
		TextField mediaText = (TextField)foodManageForm.lookup("#mediaText");
		
		if(mediaText.getLength()>39) {
			mediaText.setText("");
			CommonService.Msg("40자 제한입니다.");
		}else {
		String media = mediaText.getText();
		foodManageDAO dao = new foodManageDAO();
		if(media.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
				dao.mediaUpdate(media, foodname);
				CommonService.Msg(foodname + " 레시피의 영상이 " + media + "(으)로 변경되었습니다.");
				mediaText.clear();
			}
		}
	}

	@Override
	public void detailRecipeUpdate(String foodname, Parent foodManageForm)  {
		TextArea detailText = (TextArea)foodManageForm.lookup("#detailText");


		if(detailText.getLength()>3999) {
			detailText.setText("");
			CommonService.Msg("4000자 제한입니다.");
		}else {

		
		String detail = detailText.getText();
		foodManageDAO dao = new foodManageDAO();
		if(detail.equals("")) {
			CommonService.Msg("값을 입력 후에 수정 버튼을 눌러주세요.");
		} else {
				dao.detailUpdate(detail, foodname);
				CommonService.Msg(foodname + " 레시피의 상세내용이 변경되었습니다.");
				detailText.clear();
			}
		}
	}

	@Override
	public void nullPointer() {
		CommonService.Msg("테이블에서 레시피를 선택 후 수정 버튼을 클릭해주십시오.");
		
	}
}
