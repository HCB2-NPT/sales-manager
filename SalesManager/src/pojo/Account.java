package pojo;

import java.io.Serializable;

public class Account implements Serializable {
	private int accountId = 0;
	private String name = null;
	private String username = null;
	private String password = null;
	private int permissionId = 0;
	private Permission permission = null;
	private boolean isDeleted = false;
	
	public int getAccountId() {
		return accountId;
	}
	protected void setAccountId(int id) {
		this.accountId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	protected int getPermissionId() {
		return permissionId;
	}
	protected void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
}
