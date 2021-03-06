package dao.hibernate_adapters;

import java.util.List;
import org.apache.log4j.Logger;
import dao.HibernateUtil;
import javafx.collections.ObservableList;
import pojo.InvoiceExt;

public class InvoiceExtAdapter {
	private static final Logger logger = Logger.getLogger(InvoiceExtAdapter.class);
	
	public enum OneColumn{
		Invoice, Item
	}
	
	public static List<InvoiceExt> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from InvoiceExt", null);
	}
	
	public static List<InvoiceExt> where(OneColumn e, int p0){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		String hql = "from InvoiceExt";
		switch (e){
			case Invoice:
				hql = "from InvoiceExt where invoiceId = :p0";
				break;
			case Item:
				hql = "from InvoiceExt where itemId = :p0";
				break;
		}
		return HibernateUtil.getList(hql, new Object[]{ p0 });
	}
	
	public static InvoiceExt where(int invoiceid, int itemid){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from InvoiceExt where invoiceId = :p0 and itemId = :p1", new Object[]{ invoiceid, itemid });
	}
	
	public static boolean insertList(ObservableList<InvoiceExt> obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.saveList(obj);
	}
	
	public static boolean deleteList(List<InvoiceExt> obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			for (InvoiceExt invoiceExt : obj) {
				logger.info("Restore Number of itemid" + invoiceExt.getItemId() + " " + invoiceExt.getNum());
				ItemAdapter.addNumber(-invoiceExt.getNum(), invoiceExt.getItemId());
				logger.info("Restored");
				logger.info("Delete InvoiceID " + invoiceExt.getInvoiceId() + ", ItemID " + invoiceExt.getItemId());
				HibernateUtil.delete(invoiceExt);
			}
		} catch (Exception e) {
			return false;
		} 
		return true;
	}
}
