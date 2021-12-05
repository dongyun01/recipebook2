package userManage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import userManage.dto.ManageDTO;

public class ManageDAO {
	private static Connection con;
	public ManageDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ManageDTO selectId(String id) {
		String sql = "SELECT * FROM member WHERE ID = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				ManageDTO dto = new ManageDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void delete(String id) {
		String sql = "DELETE FROM member WHERE ID = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ObservableList<ManageDTO> selectAll() {
		String sql = "SELECT * FROM member";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<ManageDTO> oblist = FXCollections.observableArrayList();
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				oblist.add(new ManageDTO(rs.getString("id"),rs.getString("pw"),rs.getString("name"),rs.getString("age"),rs.getString("email")));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oblist;
	}
	
	public void pwUpdate(String id, String pw) {
		String sql = "UPDATE member SET pw=? WHERE id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setString(2, id);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void nameUpdate(String id, String name) {
		String sql = "UPDATE member SET name=? WHERE id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ageUpdate(String id, String age) {
		String sql = "UPDATE member SET age=? WHERE id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, age);
			ps.setString(2, id);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void emailUpdate(String id, String email) {
		String sql = "UPDATE member SET email=? WHERE id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, id);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
		
		
