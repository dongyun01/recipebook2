package commentsManage.dto;
/*
 * sql > desc comments
  Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ID                                                 VARCHAR2(20)
 COM                                                VARCHAR2(70)
 FOODNAME                                           VARCHAR2(20)
 */
public class commentsManageDTO {
	private String id;
	private String com;
	private String foodname;
	
	public commentsManageDTO(String id, String com, String foodname) {
		this.id = id;
		this.com = com;
		this.foodname = foodname;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
}