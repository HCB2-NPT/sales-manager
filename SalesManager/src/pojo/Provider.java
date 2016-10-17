package pojo;

public class Provider extends _pojo_Initializer {
	private int providerId = 0;
	private String name = null;
	
	public Provider(){
		super();
	}
	
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
