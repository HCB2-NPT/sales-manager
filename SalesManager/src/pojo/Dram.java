package pojo;

import java.io.Serializable;

public class Dram implements Serializable {
	private int dramId = 0;
	private String name = null;
	
	public int getDramId() {
		return dramId;
	}
	public void setDramId(int id) {
		this.dramId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
