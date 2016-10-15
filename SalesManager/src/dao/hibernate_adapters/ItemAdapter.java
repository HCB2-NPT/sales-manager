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
	
	public static boolean insert(Item obj){
		return obj.getItemId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Item obj){
		if (obj.getItemId() > 0){
			if (HibernateUtil.save(obj.getCat()))
				HibernateUtil.refresh(obj.getCat());
			if (HibernateUtil.save(obj.getDram()))
				HibernateUtil.refresh(obj.getDram());
			return HibernateUtil.execute("update Item set name = :p0, img = :p1, cost = :p2, catId = :p3, dramId = :p4 where itemId = :p5", new Object[]{ obj.getName(), obj.getImg(), obj.getCost(), obj.getItemId(), obj.getCat().getCatId(), obj.getDram().getDramId() }) >= 0;
		}
		return false;
	}
}
