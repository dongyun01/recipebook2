package commentsManage.service;

import javafx.scene.Parent;

public interface commentsManageServiceInterface {
	public void delete(String foodname, String com);
	public void exit(Parent commentsManageForm);
	public void nullPointer();
}
