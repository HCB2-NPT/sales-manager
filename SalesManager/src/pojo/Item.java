package pojo;

import java.text.DecimalFormat;

public class Item extends _pojo_Initializer {
	private int itemId = 0;
	private String name = null;
	private double cost = 0;
	private int num = 0;
	//private int dramId = 0;
	//private int catId = 0;
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
	/*protected int getDramId() {
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
	}*/
	public String getCostFormat() {
		return new DecimalFormat("#,###.00").format(cost);
	}
	public void setCostFormat(String costF) {
		this.cost = Double.valueOf(costF);
	}
	public String getCategoryFormat(){
		if (cat == null)
			return null;
		return cat.getName();
	}
	public String getDramFormat(){
		if (dram == null)
			return null;
		return dram.getName();
	}
	public Boolean getCanShowImg(){
		return this.img != null && !this.img.equals("");
	}
	@Override
	public String toString() {
		return String.format("%1$4s - %2$s", itemId, name);
	}
}
