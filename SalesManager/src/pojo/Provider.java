package pojo;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Provider implements Serializable {
	/*
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
	*/
	private final IntegerProperty propertyId = new SimpleIntegerProperty(0);
	private final StringProperty propertyName = new SimpleStringProperty(null);
	
	public int getProviderId() {
		return propertyId.get();
	}
	protected void setProviderId(int id) {
		propertyId.set(id);
	}
	public IntegerProperty propertyId(){
		return propertyId;
	}
	public String getName() {
		return propertyName.get();
	}
	public void setName(String name) {
		propertyName.set(name);
	}
	public StringProperty propertyName(){
		return propertyName;
	}
}
