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
	
	public static boolean addNumber(int num, int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		Item i = get(id);
		if (i != null){
			int cur = i.getNum();
			return HibernateUtil.execute("update Item set num = :p0 where itemId = :p1", new Object[]{ cur + num, id }) >= 0;
		}
		return false;
	}
}
