package commentsManage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import commentsManage.dto.commentsManageDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class commentsManageDAO {
	private Connection con;
	
	public commentsManageDAO() {
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
	
	public void delete(String id, String com) {
		String sql = "DELETE FROM comments WHERE id = ? and com = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, com);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ObservableList<commentsManageDTO> selectAll() {
		String sql = "SELECT * FROM comments";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<commentsManageDTO> oblist = FXCollections.observableArrayList();

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				oblist.add(new commentsManageDTO(rs.getString("id"), rs.getString("com"), rs.getString("foodname")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oblist;
	}
}
