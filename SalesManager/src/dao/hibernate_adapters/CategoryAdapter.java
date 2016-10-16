package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Category;

public class CategoryAdapter {
	private static final Logger logger = Logger.getLogger(CategoryAdapter.class);
	
	public static List<Category> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Category", null);
	}
	
	public static Category get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Category where catId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(Category obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getCatId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Category obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getCatId() > 0 && HibernateUtil.update(obj);
	}
}
