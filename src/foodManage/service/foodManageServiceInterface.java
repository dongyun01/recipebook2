package foodManage.service;

import javafx.scene.Parent;

public interface foodManageServiceInterface {
	public void delete(String foodname);
	public void exit(Parent foodManageForm);
	public void foodNameUpdate(String gredient, Parent foodManageForm);
	public void gredientUpdate(String foodname, Parent foodManageForm);
	public void mediaUpdate(String foodname, Parent foodManageForm);
	public void detailRecipeUpdate(String foodname, Parent foodManageForm);
	public void nullPointer();
}
