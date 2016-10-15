package pojo;

import java.io.Serializable;

public class Category implements Serializable {
	private int catId = 0;
	private String name = null;
	
	public int getCatId() {
		return catId;
	}
	public void setCatId(int id) {
		this.catId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
