package pojo;

import java.text.DecimalFormat;
import java.util.Date;

import dao.hibernate_adapters.AccountAdapter;
import dao.hibernate_adapters.ImportAdapter;
import dao.hibernate_adapters.ImportExtAdapter;
import dao.hibernate_adapters.InvoiceExtAdapter;

public class Import extends _pojo_Initializer {
	private int importId = 0;
	private int createrId = 0;
	private Account creater = null;
	private Date date = null;
	
	public Import(){
		super();
	}
	
	public int getImportId() {
		return importId;
	}
	protected void setImportId(int id) {
		this.importId = id;
	}
	public Account getCreater() {
		return creater;
	}
	public void setCreater(Account creater) {
		this.creater = creater;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public int getCreaterId() {
		return createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
	}
	
	public String getCreaterName(){
		return AccountAdapter.get(createrId).getName();
	}
	
	public String getTotalMoney(){
		double a = 0;
		for (ImportExt item : ImportExtAdapter.where(ImportExtAdapter.OneColumn.Import, importId)) {
			a += item.getCost()*item.getNum();
		}
		return new DecimalFormat("#,###.00").format(a);
	}
}
