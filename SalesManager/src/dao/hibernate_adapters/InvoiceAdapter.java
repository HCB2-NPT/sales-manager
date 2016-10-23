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
	
	public static List<Invoice> getAllDebtor() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Invoice where ispayment=1 and paymentdate!=NULL", null);
	}
	
	public static Invoice get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Invoice where invoiceId = :p0", new Object[]{ id });
	}
	
	public static int updatePaid(Invoice obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.execute("update Invoice set isPayment = false where invoiceId = :p0", new Object[]{ obj.getInvoiceId() });
	}
	
	public static boolean insert(Invoice obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getInvoiceId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean delete(Invoice obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		InvoiceExtAdapter.deleteList(InvoiceExtAdapter.where(InvoiceExtAdapter.OneColumn.Invoice, obj.getInvoiceId()));
		return obj.getInvoiceId() != 0 && HibernateUtil.delete(obj);
	}
}
