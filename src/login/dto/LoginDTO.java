package login.dto;

/*
sql> desc member;

Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
ID                                                 VARCHAR2(15)
PW                                                 VARCHAR2(20)
NAME                                               VARCHAR2(15)
EMAIL                                              VARCHAR2(30)
AGE                                                VARCHAR2(10)

*/

public class LoginDTO {
   private String ID;
   private String PW;
   //private String PWV; //비밀번호 확인
   private String name;
   private String email;
   private String age;
   
   public LoginDTO(String ID, String PW, String name, String email, String age) {
      this.ID = ID;
      this.PW = PW;
      this.name = name;
      this.email = email;
      this.age = age;
   }
   public void setId(String ID) {
      this.ID = ID;
   }
   public void setPw(String PW) {
      this.PW = PW;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public void setAge(String age) {
      this.age = age;
   }
   
   public String getId() {
      return ID;
   }
   public String getPw() {
      return PW;
   }
   public String getName() {
      return name;
   }
   public String getEmail() {
      return email;
   }
   public String getAge() {
      return age;
   }
   
}