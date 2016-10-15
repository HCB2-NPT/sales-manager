package pojo;

import java.io.Serializable;

public class Customer implements Serializable {
	private int customerId = 0;
	private String name = null;
	private String personalId = null;
	private String phoneNumber = null;
	private String company = null;
	
	public int getCustomerId() {
		return customerId;
	}
	protected void setCustomerId(int id) {
		this.customerId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
}
