package pojo;

import java.text.DecimalFormat;

public class InvoiceExt extends _pojo_Initializer {
	private int invoiceId = 0;
	private int itemId = 0;
	private Item item = null;
	private double cost = 0;
	private int num = 0;
	
	public InvoiceExt(){
		super();
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	//Format//
	public String getNameFormat(){
		return item.getName();
	}
	
	public String getCategoryFormat(){
		return item.getCat().getName();
	}
	
	public String getCostFormat(){
		return new DecimalFormat("#,###.00").format(cost);
	}
	
	public String getTotalFormat(){
		return new DecimalFormat("#,###.00").format(cost*num);
	}
	
	
	
}
