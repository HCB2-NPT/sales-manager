package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Invoice;

public class InvoiceAdapter {
	public static List<Invoice> getAll() {
		return HibernateUtil.getList("from Invoice", null);
	}
	
	public static Invoice get(int id){
		return HibernateUtil.getSingle("from Invoice where invoiceId = :p0", new Object[]{ id });
	}
}
