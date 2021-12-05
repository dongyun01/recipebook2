package userManage.dto;

public class ManageDTO {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String age;
	
	public ManageDTO(String id, String pw, String name, String age, String email) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.email = email;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
}
