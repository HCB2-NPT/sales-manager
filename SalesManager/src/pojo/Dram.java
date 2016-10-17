package pojo;

public class Dram extends _pojo_Initializer {
	private int dramId = 0;
	private String name = null;
	
	public Dram(){
		super();
	}
	
	public int getDramId() {
		return dramId;
	}
	protected void setDramId(int id) {
		this.dramId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
