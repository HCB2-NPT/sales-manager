package pojo;

import java.util.Date;

public class Invoice extends _pojo_Initializer {
	private int invoiceId = 0;
	private Date date = null;
	private Account creater = null;
	private int createrId = 0;
	private int customerId = 0;
	private boolean isPayment = false;
	private Date paymentDate = null;
	private Customer customer = null;
	private boolean isDeleted = false;
	
	public Invoice(){
		super();
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	protected void setInvoiceId(int id) {
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

	public int getCreaterId() {
		return createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
