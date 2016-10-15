package pojo;

import java.io.Serializable;
import java.util.Date;

public class Import implements Serializable {
	private int importId = 0;
	private Account creater = null;
	private Date date = null;
	
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
}
