package pojo;

import java.io.Serializable;

public class Item implements Serializable {
	private int itemId = 0;
	private String name = null;
	private double cost = 0d;
	private int num = 0;
	private Dram dram = null;
	private Category cat = null;
	private String img = null;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int id) {
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
}
