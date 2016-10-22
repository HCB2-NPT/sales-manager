package pojo;

public class Provider extends _pojo_Initializer {
	private int providerId = 0;
	private String name = null;
	private String desc = null;
	
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
