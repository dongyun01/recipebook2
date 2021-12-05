package login.service;

import login.dto.LoginDTO;
import javafx.scene.Parent;

public interface LoginService {
	public LoginDTO loginProc(Parent loginForm);
	public void cancelProc(Parent loginForm);
	public void regOpenProc();
}
