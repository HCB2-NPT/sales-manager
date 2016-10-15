package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.InvoiceExt;

public class InvoiceExtAdapter {
	public enum OneColumn{
		Invoice, Item
	}
	
	public static List<InvoiceExt> getAll() {
		return HibernateUtil.getList("from InvoiceExt", null);
	}
	
	public static List<InvoiceExt> where(OneColumn e, int p0){
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
		return HibernateUtil.getSingle("from InvoiceExt where invoiceId = :p0 and itemId = :p1", new Object[]{ invoiceid, itemid });
	}
}
