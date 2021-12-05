package recipe.dto;

/*
SQL> desc recipe
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
FOODNAME                                           VARCHAR2(20)
GREDIENT                                           VARCHAR2(20)
MEDIA                                              VARCHAR2(40)
DETAILRECIPE                                       VARCHAR2(100)
RANK                                               NUMBER(38)
*/

public class recipeDTO {
	private String foodname;
	private String gredient;
	private String media;
	private String detailrecipe;
	private Integer rank;
	
	public recipeDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public void setFoodname(String foodname){
		this.foodname = foodname;
	}
	
	public void setGredient(String gredient) {
		this.gredient = gredient;
	}
	
	public void setMedia(String media) {
		this.media = media;
	}
	
	public void setDetailrecipe(String detailrecipe) {
		this.detailrecipe = detailrecipe;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public String getFoodname() {
		return foodname;
	}
	
	public String getGredient() {
		return gredient;
	}
	
	public String getMedia() {
		return media;
	}
	
	public String getDetailrecipe() {
		return detailrecipe;
	}
	
	public Integer getRank() {
		return rank;
	}
	
}
