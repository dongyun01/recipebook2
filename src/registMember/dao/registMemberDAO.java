package registMember.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import registMember.dto.registMemberDTO;

public class registMemberDAO {
private Connection con;
   
   public registMemberDAO() {
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
   
   public registMemberDTO selectId(String id) {
      String sql = "SELECT * FROM member WHERE id = ?";
      PreparedStatement ps;
      ResultSet rs;
      
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, id);
         rs = ps.executeQuery();
         if(rs.next()) {
            registMemberDTO dto = new registMemberDTO(rs.getString(1), rs.getString(2), 
                  rs.getString(3), rs.getString(4), rs.getString(5));
            return dto;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }
   
   public int insert(registMemberDTO dto) {
      String sql = "INSERT INTO member VALUES(?,?,?,?,?)";
      PreparedStatement ps;
      
      try {
         ps = con.prepareStatement(sql);
         ps.setString(1, dto.getId());
         ps.setString(2, dto.getPw());
         ps.setString(3, dto.getName());
         ps.setString(4, dto.getEmail());
         ps.setString(5, dto.getAge());
         return ps.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }
}
