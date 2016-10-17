package pojo;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Provider implements Serializable {
	private int providerId = 0;
	private String name = null;
	
	public int getProviderId() {
		return providerId;
	}
	protected void setProviderId(int id) {
		this.providerId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
