package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Invoice;

public class InvoiceAdapter {
	private static final Logger logger = Logger.getLogger(InvoiceAdapter.class);
	
	public static List<Invoice> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Invoice", null);
	}
	
	public static Invoice get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Invoice where invoiceId = :p0", new Object[]{ id });
	}
}
