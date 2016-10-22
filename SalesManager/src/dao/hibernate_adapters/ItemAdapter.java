package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Item;

public class ItemAdapter {
	private static final Logger logger = Logger.getLogger(ItemAdapter.class);
	
	public static List<Item> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Item", null);
	}
	
	public static Item get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Item where itemId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(Item obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getItemId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Item obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getItemId() > 0 && HibernateUtil.execute("update Item set name = :p0, img = :p1, cost = :p2, catId = :p3, dramId = :p4 where itemId = :p5", new Object[]{ obj.getName(), obj.getImg(), obj.getCost(), obj.getCat().getCatId(), obj.getDram().getDramId(), obj.getItemId() }) >= 0;
	}
	
	public static boolean updateNum(Item obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getItemId() > 0 && HibernateUtil.execute("update Item set name = :p0, img = :p1, cost = :p2, catId = :p3, dramId = :p4,num = :p6 where itemId = :p5", new Object[]{ obj.getName(), obj.getImg(), obj.getCost(), obj.getCat().getCatId(), obj.getDram().getDramId(), obj.getItemId(),obj.getNum() }) >= 0;
	}
}
