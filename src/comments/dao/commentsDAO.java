package comments.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import login.dto.LoginDTO;

public class commentsDAO {
	private Connection con;
	
	public commentsDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password ="oracle";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void commentSet(String foodname, ListView<String> clist) {
		String sql = "select * from comments";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(3).equals(foodname)) {
				clist.getItems().add("[작성자: "+rs.getString(1) +"] "+rs.getString(2));
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void insertComment(String foodname, TextField ctext,LoginDTO dto) {
		String sql = "insert into comments values(?,?,?)";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, dto.getId());
			ps.setString(2, ctext.getText());
			ps.setString(3, foodname);
			
			ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeComment(String foodname, String comment,String commentS, ListView<String> clist) {
		String sql = "delete from comments where foodname = ? and ID = ? and com = ?";
		String sql1 = "commit";
		PreparedStatement ps;
		PreparedStatement ps1;
		
		try {
			ps = con.prepareStatement(sql);
			ps1 = con.prepareStatement(sql1);
			ps.setString(1, foodname);
			ps.setString(2, comment);
			ps.setString(3, commentS.trim());
			clist.getItems().remove(clist.getSelectionModel().getSelectedItem());
			ps.executeQuery();
			ps1.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
