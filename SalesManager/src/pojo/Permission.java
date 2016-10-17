package pojo;

public class Permission extends _pojo_Initializer {
	private int permissionId = 0;
	private String name = null;
	
	public int getPermissionId() {
		return permissionId;
	}
	protected void setPermissionId(int id) {
		this.permissionId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
