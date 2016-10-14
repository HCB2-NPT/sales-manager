package pojo;

import java.io.Serializable;
import java.util.Date;

public class Import implements Serializable {
	private int id;
	private int createrId;
	private Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreaterId() {
		return createrId;
	}
	public void setCreaterId(int creater) {
		this.createrId = creater;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
