package foodManage;

import java.io.IOException;

import common.CommonService;
import event.stageMove;
import foodManage.dao.foodManageDAO;
import foodManage.dto.foodManageDTO;
import foodManage.service.foodManageService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class foodManageInsertController {
	private Parent insertFoodForm;
	private foodManageDAO foodmanageDao = new foodManageDAO();
	private foodManageService foodmSvc = new foodManageService();
	
	public void setInsertFoodForm(Parent insertFoodForm) {
		this.insertFoodForm = insertFoodForm;
	}
	
	public void insert() {
		TextField foodnameText = (TextField)insertFoodForm.lookup("#foodnameText");
	    TextField gredientText = (TextField)insertFoodForm.lookup("#gredientText");
	    TextField mediaText = (TextField)insertFoodForm.lookup("#mediaText");
	    TextArea detailText = (TextArea)insertFoodForm.lookup("#detailText");
	    
	    String foodname = foodnameText.getText();
	    String gredient = gredientText.getText();
	    String media = mediaText.getText().toLowerCase();
	    String detail = detailText.getText();
	    
	    if(foodnameText.getLength()>19 || gredientText.getLength()>199 || mediaText.getLength()>39 || detailText.getLength()>3999) {
	    	if(foodnameText.getLength()>19) {
	    		CommonService.Msg("20자 제한입니다.");
	    		foodnameText.setText("");
	    	}
	    	else if(gredientText.getLength()>199) {
	    		CommonService.Msg("200자 제한입니다.");
	    		gredientText.setText("");
	    	}
	    	else if(mediaText.getLength()>39) {
	    		CommonService.Msg("40자 제한입니다.");
	    		mediaText.setText("");
	    	}
	    	else if(detailText.getLength()>3999) {
	    		CommonService.Msg("4000자 제한입니다.");
	    		detailText.setText("");
	    	}
	    }else {
	    
	    if(foodname.isEmpty() || gredient.isEmpty() || media.isEmpty() || detail.isEmpty()) {
	    	CommonService.Msg("내용을 모두 입력해주세요");
	    } else {

	    		if(foodmanageDao.selectId(foodname) == null) {
	    			foodManageDTO dto = new foodManageDTO(foodname, gredient, media, detail, 0);
	    			dto.setFoodname(foodname);
	    			dto.setGredient(gredient);
	    			dto.setMedia(media);
	    			dto.setDetailrecipe(detail);
	    			dto.setRank(0);
	    			foodmanageDao.insert(dto);
	    			CommonService.WindowClose(insertFoodForm);
	  
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("foodManage.fxml"));
	    			try {
	    				Parent foodManageForm = loader.load();
	    				Stage foodStage = new Stage();
	    				
	    				foodManage.foodManageController ctrl = loader.getController();
	    				ctrl.setfoodManageForm(foodManageForm);
				
	    				AnchorPane ap = (AnchorPane)foodManageForm.lookup("#ap");
	    				new stageMove(ap); 
				
	    				foodStage.initStyle(StageStyle.UNDECORATED);
	    				foodStage.setScene(new Scene(foodManageForm));
	    				foodStage.setTitle("레시피 관리");
	    				foodStage.show();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    			CommonService.Msg(foodname + " 레시피가 생성되었습니다.");
	    		} else {
	    			CommonService.Msg("등록된 레시피가 있습니다.");
	    			}
	    	
	    }
	    
	    }
	}
	     
	
	public void exit() {
		CommonService.WindowClose(insertFoodForm);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("foodManage.fxml"));
		try {
			Parent foodManageForm = loader.load();
			Stage foodStage = new Stage();
			
			foodManage.foodManageController ctrl = loader.getController();
			ctrl.setfoodManageForm(foodManageForm);
			
			AnchorPane ap = (AnchorPane)foodManageForm.lookup("#ap");
			new stageMove(ap); 
			
			foodStage.initStyle(StageStyle.UNDECORATED);
			foodStage.setScene(new Scene(foodManageForm));
			foodStage.setTitle("레시피 관리");
			foodStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void close() {
		CommonService.WindowClose(insertFoodForm);
	}
		
		
	}