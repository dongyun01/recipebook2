package foodManage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import event.stageMove;
import foodManage.dao.foodManageDAO;
import foodManage.dto.foodManageDTO;
import foodManage.service.foodManageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class foodManageController implements Initializable{
   private foodManageService foodmSvc;
   private Parent foodManageForm;
   private foodManageDAO foodmanageDao = new foodManageDAO();
   private ObservableList<foodManageDTO> oblist = FXCollections.observableArrayList();
   
    @FXML
    private TableView<foodManageDTO> recipetable;

    @FXML
    private TableColumn<foodManageDTO, String> col_foodname;

    @FXML
    private TableColumn<foodManageDTO, String> col_gredient;

    @FXML
    private TableColumn<foodManageDTO, String> col_media;

    @FXML
    private TableColumn<foodManageDTO, Integer> col_rank;

    @FXML
    private TableColumn<foodManageDTO, String> col_detail;
    
   public void setfoodManageForm(Parent foodManageForm) {
      this.foodManageForm = foodManageForm;
   }
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      foodmSvc = new foodManageService();
      show();
   }
      
   public void show() {
      col_foodname.setCellValueFactory(new PropertyValueFactory<>("foodname"));
      col_gredient.setCellValueFactory(new PropertyValueFactory<>("gredient"));
      col_media.setCellValueFactory(new PropertyValueFactory<>("media"));
      col_rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
      col_detail.setCellValueFactory(new PropertyValueFactory<>("detailrecipe"));
      oblist = foodmanageDao.selectAll();
      recipetable.setItems(oblist);
      
   }
   public void delete() {
      try {
         String foodname = recipetable.getSelectionModel().getSelectedItem().getFoodname();
         foodmSvc.delete(foodname);
         show();
      } catch(NullPointerException e) {
         CommonService.Msg("테이블에서 레시피를 선택 후 삭제 버튼을 클릭해주십시오.");
      }
   }
   
   public void exit() {
      foodmSvc.exit(foodManageForm);
      
   }
   
   public void foodnameUpdate() {
      try {
      String gredient = recipetable.getSelectionModel().getSelectedItem().getGredient();
      if(gredient.length()>19) {
         CommonService.Msg("20자 제한입니다.");
         gredient ="";
      }
      foodmSvc.foodNameUpdate(gredient, foodManageForm); 
      show(); 
      } catch(NullPointerException e) { 
         foodmSvc.nullPointer(); 
         } 
      }
   
   public void gredientUpdate() {
      try {
      String foodname = recipetable.getSelectionModel().getSelectedItem().getFoodname();
      if(foodname.length()>200) {
         CommonService.Msg("200자 제한입니다.");
         foodname = "";
      }
      foodmSvc.gredientUpdate(foodname, foodManageForm); 
      show(); 
      } catch(NullPointerException e) { 
         foodmSvc.nullPointer(); 
         } 
      }
   
   public void mediaUpdate() {
      try {
      String foodname = recipetable.getSelectionModel().getSelectedItem().getFoodname();
      if(foodname.length()>40) {
         CommonService.Msg("40자 제한입니다.");
         foodname = "";
      }
      foodmSvc.mediaUpdate(foodname, foodManageForm); 
      show(); 
      } catch(NullPointerException e) { 
         foodmSvc.nullPointer(); 
         } 
      }
   
   public void insert() {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("insertFood.fxml"));
      try {
         Parent insertFoodForm = loader.load();
         Stage insertfoodStage = new Stage();
         
         foodManageInsertController ctrl = loader.getController();
         ctrl.setInsertFoodForm(insertFoodForm);
         
         AnchorPane ap = (AnchorPane)insertFoodForm.lookup("#ap");
         new stageMove(ap); 
         
         insertfoodStage.initStyle(StageStyle.UNDECORATED);
         insertfoodStage.setScene(new Scene(insertFoodForm));
         insertfoodStage.setTitle("레시피 추가");
         insertfoodStage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      CommonService.WindowClose(foodManageForm);
   }
   
   public void detailUpdate() {
      try {
      String foodname = recipetable.getSelectionModel().getSelectedItem().getFoodname();
      if(foodname.length()>4000) {
         CommonService.Msg("4000자 제한입니다.");
         foodname = "";
      }
      foodmSvc.detailRecipeUpdate(foodname, foodManageForm); 
      show(); 
      } catch(NullPointerException e) { 
         foodmSvc.nullPointer(); 
         } 
      }
   
   public void close() {
      foodmSvc.exit(foodManageForm);
   }
}