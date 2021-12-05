package foodManage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import foodManage.dto.foodManageDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import registMember.dto.registMemberDTO;

public class foodManageDAO {
	private static Connection con;

	public foodManageDAO() {
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

	public foodManageDTO selectId(String foodname) {
		String sql = "SELECT * FROM recipe WHERE foodname = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, foodname);
			rs = ps.executeQuery();
			if (rs.next()) {
				foodManageDTO dto = new foodManageDTO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5));
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delete(String foodname) {
		String sql = "DELETE FROM recipe WHERE foodname = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, foodname);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ObservableList<foodManageDTO> selectAll() {
		String sql = "SELECT * FROM recipe";
		PreparedStatement ps;
		ResultSet rs;
		ObservableList<foodManageDTO> oblist = FXCollections.observableArrayList();

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				oblist.add(new foodManageDTO(rs.getString("foodname"), rs.getString("gredient"), rs.getString("media"),
						rs.getString("detailrecipe"), rs.getInt("rank")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oblist;
	}

	public void foodNameUpdate(String foodname, String gredient) { 
		String sql = "UPDATE recipe SET foodname=? WHERE gredient=?"; 
		PreparedStatement ps; 
		try { 
			ps = con.prepareStatement(sql); 
			ps.setString(1, foodname); 
			ps.setString(2, gredient);
			ps.executeUpdate(); 
			
			}	
		catch (Exception e) { 
			e.printStackTrace(); 
			} 
		}
	public void gredientUpdate(String gredient, String foodname) { 
		String sql = "UPDATE recipe SET gredient=? WHERE foodname=?"; 
		PreparedStatement ps; 
		try { 
			ps = con.prepareStatement(sql); 
			ps.setString(1, gredient); 
			ps.setString(2, foodname);
			ps.executeUpdate(); 
			
			}	
		catch (Exception e) { 
			e.printStackTrace(); 
			} 
		}
	
	public void mediaUpdate(String media, String foodname) { 
		String sql = "UPDATE recipe SET media=? WHERE foodname=?"; 
		PreparedStatement ps; 
		try { 
			ps = con.prepareStatement(sql); 
			ps.setString(1, media.toLowerCase()); 
			ps.setString(2, foodname);
			ps.executeUpdate(); 
			
			}	
		catch (Exception e) { 
			e.printStackTrace(); 
			} 
		}
	
	public void detailUpdate(String detailrecipe, String foodname) { 
		String sql = "UPDATE recipe SET detailrecipe=? WHERE foodname=?"; 
		PreparedStatement ps; 
		try { 
			ps = con.prepareStatement(sql); 
			ps.setString(1, detailrecipe); 
			ps.setString(2, foodname);
			ps.executeUpdate(); 
			
			}	
		catch (Exception e) { 
			e.printStackTrace(); 
			} 
		}
	
	  public int insert(foodManageDTO dto) {
		     String sql = "INSERT INTO recipe VALUES(?,?,?,?,0)";
		     PreparedStatement ps;
		      
		     try {
		        ps = con.prepareStatement(sql);
		        ps.setString(1, dto.getFoodname());
		        ps.setString(2, dto.getGredient());
		        ps.setString(3, dto.getMedia());
		        ps.setString(4, dto.getDetailrecipe());
		        return ps.executeUpdate();
		     } catch (Exception e) {
		        e.printStackTrace();
		     }
		     return 0;
		  }
	
}
