package pojo;

import java.io.Serializable;

public class Provider implements Serializable {
	private int providerId = 0;
	private String name = null;
	
	public int getProviderId() {
		return providerId;
	}
	protected void setProviderId(int id) {
		this.providerId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
