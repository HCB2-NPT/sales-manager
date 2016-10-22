package pojo;

import java.text.DecimalFormat;

public class ImportExt extends _pojo_Initializer {
	private int importId = 0;
	private int itemId = 0;
	private int providerId = 0;
	private Provider provider = null;
	private Item item = null;
	private double cost = 0d;
	private int num = 0;
	
	public ImportExt(){
		super();
	}
	
	public int getImportId() {
		return importId;
	}
	public void setImportId(int importId) {
		this.importId = importId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getCostFormat() {
		return new DecimalFormat("#,###.00").format(cost);
	}
	public String getTotalPrice() {
		return new DecimalFormat("#,###.00").format(cost * num);
	}
	public String getItemName(){
		if (item == null)
			return null;
		return item.getName();
	}
}
