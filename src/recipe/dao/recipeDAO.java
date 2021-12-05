package recipe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import login.dto.LoginDTO;
import recipe.dto.*;

public class recipeDAO {
	private Connection con;
	
	public recipeDAO() {
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
	
	
	public void searchClickf(TextField text,ListView<String> list) {
		String sql = "Select * from recipe where foodname = ?";
		String sql1 = "Update recipe Set rank = ? where foodname = ?";
		PreparedStatement ps;
		PreparedStatement ps1;

		ResultSet rs;


		String textField = text.getText();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, textField);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.getItems().add("["+rs.getString(1)
						+" 레시피] 재료:"+rs.getString(2));
				
				ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, rs.getInt(5)+1);
				ps1.setString(2, rs.getString(1));
				ps1.execute();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void searchClickg(TextField text,ListView<String> list) {
		String sql = "Select * from recipe where gredient like ?";
		String sql1 = "Update recipe Set rank = ? where foodname = ?";
		PreparedStatement ps;
		PreparedStatement ps1;
		ResultSet rs;


		String textField = "%"+text.getText()+"%";
		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, textField);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.getItems().add("["+rs.getString(1)
				+" 레시피] 재료:"+rs.getString(2));
				
				ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, rs.getInt(5)+1);
				ps1.setString(2, rs.getString(1));
				ps1.execute();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void ranklist(ListView<String> rank) {
		String sql = "Select * from recipe";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(5));
				// 기존코드
				//rank.getItems().add(rs.getString(1)
				//		+"/ "+rs.getInt(5));
			}
			
			List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
			Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
				// compare로 값을 비교
				public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
					// 오름 차순 정렬
					return obj2.getValue().compareTo(obj1.getValue());
				}
			});
			
			for(Entry<String, Integer> entry : list_entries) {
				rank.getItems().add("[조회수 "+entry.getValue()+"회] "+entry.getKey());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String detailRecipe(String foodname) {
		String sql = "Select detailrecipe from recipe where foodname = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, foodname);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String mediaSet(String foodname) {
		String sql = "select media from recipe where foodname = ?";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, foodname);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
