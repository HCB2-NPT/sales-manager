package pojo;

import java.util.Date;

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
}
