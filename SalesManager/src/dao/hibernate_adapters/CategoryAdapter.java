package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Category;

public class CategoryAdapter {
	public static List<Category> getAll() {
		return HibernateUtil.getList("from Category", null);
	}
	
	public static Category get(int id){
		return HibernateUtil.getSingle("from Category where catId = :p0", new Object[]{ id });
	}
}
