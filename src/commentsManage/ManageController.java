package commentsManage;

import java.net.URL;
import java.util.ResourceBundle;

import commentsManage.dao.commentsManageDAO;
import commentsManage.dto.commentsManageDTO;
import commentsManage.service.commentsManageService;
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
	private commentsManageService comSvc;
	private Parent commentsManageForm;
	private commentsManageDAO commentsmanageDao = new commentsManageDAO();
	private ObservableList<commentsManageDTO> oblist = FXCollections.observableArrayList();
	
	@FXML
	private TableView<commentsManageDTO> comtable;
	@FXML
	private TableColumn<commentsManageDTO, String> col_foodname, col_id, col_com;
	
	public void setCommentsManageForm(Parent commentsManageForm) {
		this.commentsManageForm = commentsManageForm;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comSvc = new commentsManageService();
		show();
	}
		
	private void show() {
		col_foodname.setCellValueFactory(new PropertyValueFactory<>("foodname"));
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_com.setCellValueFactory(new PropertyValueFactory<>("com"));
		oblist = commentsmanageDao.selectAll();
		comtable.setItems(oblist);
		
	}
	public void delete() {
		try {
			String id = comtable.getSelectionModel().getSelectedItem().getId();
			String com = comtable.getSelectionModel().getSelectedItem().getCom();
			comSvc.delete(id, com);
			show();
		} catch(NullPointerException e) {
			comSvc.nullPointer();
		}
	}
	
	public void exit() {
		comSvc.exit(commentsManageForm);
	}
	
	public void close() {
		System.exit(0);
	}
}
