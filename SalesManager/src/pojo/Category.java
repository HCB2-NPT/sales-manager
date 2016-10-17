package pojo;

public class Category extends _pojo_Initializer {
	private int catId = 0;
	private String name = null;
	
	public Category(){
		super();
	}
	
	public int getCatId() {
		return catId;
	}
	protected void setCatId(int id) {
		this.catId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
