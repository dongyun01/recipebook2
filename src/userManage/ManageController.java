package userManage;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import userManage.dao.ManageDAO;
import userManage.dto.ManageDTO;
import userManage.service.userManageService;

public class ManageController implements Initializable{
	private userManageService usermSvc;
	private Parent userManageForm;
	private ManageDAO manageDao = new ManageDAO();
	private ObservableList<ManageDTO> oblist = FXCollections.observableArrayList();
	
	@FXML
	private TableView<ManageDTO> usertable;
	@FXML
	private TableColumn<ManageDTO, String> col_name, col_id, col_password, col_age, col_email;
	
	public void setUserManageForm(Parent userManageForm) {
		this.userManageForm = userManageForm;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usermSvc = new userManageService();
		show();
	}
		
	private void show() {
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_password.setCellValueFactory(new PropertyValueFactory<>("pw"));
		col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
		col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		oblist = manageDao.selectAll();
		usertable.setItems(oblist);
		
	}
	public void delete() {
		try {
			String id = usertable.getSelectionModel().getSelectedItem().getId();
			usermSvc.delete(id);
			show();
		} catch(NullPointerException e) {
			CommonService.Msg("테이블에서 계정을 선택 후 삭제 버튼을 클릭해주십시오.");
		}
	}
	public void exit() {
		usermSvc.exit(userManageForm);
	}
	
	public void ageUpdate() { 
		try {
			String id = usertable.getSelectionModel().getSelectedItem().getId();
			usermSvc.ageUpdate(id, userManageForm);
			show();
		} catch(NullPointerException e) {
			usermSvc.nullPointer();
		}
	}
	
	public void nameUpdate() {
		try {
			String id = usertable.getSelectionModel().getSelectedItem().getId();
			usermSvc.nameUpdate(id, userManageForm);
			show();
		} catch(NullPointerException e) {
			usermSvc.nullPointer();
		}
	}
	public void passUpdate() {
		try {
			String id = usertable.getSelectionModel().getSelectedItem().getId();
			usermSvc.passUpdate(id, userManageForm);
			show();
		} catch(NullPointerException e) {
			usermSvc.nullPointer();
		}
	}
	public void emailUpdate() {
		try {
			String id = usertable.getSelectionModel().getSelectedItem().getId();
			usermSvc.emailUpdate(id, userManageForm);
			show();
		} catch(NullPointerException e) {
			usermSvc.nullPointer();
		}
	}
	
	public void close() {
		usermSvc.exit(userManageForm);
	}
}
