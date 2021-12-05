package userManage.service;

import javafx.scene.Parent;

public interface userManageServiceInterface {
	public void delete(String id);
	public void exit(Parent userManageForm);
	public void ageUpdate(String id, Parent userManageForm);
	public void nameUpdate(String id, Parent userManageForm);
	public void passUpdate(String id, Parent userManageForm);
	public void emailUpdate(String id, Parent userManageForm);
	public void nullPointer();
}
