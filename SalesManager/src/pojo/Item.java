package pojo;

public class Item extends _pojo_Initializer {
	private int itemId = 0;
	private String name = null;
	private double cost = 0d;
	private int num = 0;
	private int dramId = 0;
	private int catId = 0;
	private Dram dram = null;
	private Category cat = null;
	private String img = null;
	
	public Item(){
		super();
	}
	
	public int getItemId() {
		return itemId;
	}
	protected void setItemId(int id) {
		this.itemId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Dram getDram() {
		return dram;
	}
	public void setDram(Dram dram) {
		this.dram = dram;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	protected int getDramId() {
		return dramId;
	}
	protected void setDramId(int dramId) {
		this.dramId = dramId;
	}
	protected int getCatId() {
		return catId;
	}
	protected void setCatId(int catId) {
		this.catId = catId;
	}
}
