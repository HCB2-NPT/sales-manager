package pojo;

import java.io.Serializable;

public class Permission implements Serializable {
	private int permissionId = 0;
	private String name = null;
	
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int id) {
		this.permissionId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
