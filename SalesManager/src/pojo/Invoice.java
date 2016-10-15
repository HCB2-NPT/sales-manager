package pojo;

import java.io.Serializable;
import java.util.Date;

public class Invoice implements Serializable {
	private int invoiceId = 0;
	private Date date = null;
	private Account creater = null;
	private boolean isPayment = false;
	private Date paymentDate = null;
	private Customer customer = null;
	private boolean isDeleted = false;
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int id) {
		this.invoiceId = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Account getCreater() {
		return creater;
	}
	public void setCreater(Account creater) {
		this.creater = creater;
	}
	public boolean getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(boolean isPayment) {
		this.isPayment = isPayment;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
