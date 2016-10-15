package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Item;

public class ItemAdapter {
	public static List<Item> getAll() {
		return HibernateUtil.getList("from Item", null);
	}
	
	public static Item get(int id){
		return HibernateUtil.getSingle("from Item where itemId = :p0", new Object[]{ id });
	}
}
