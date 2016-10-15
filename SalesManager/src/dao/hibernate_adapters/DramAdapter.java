package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Dram;

public class DramAdapter {
	public static List<Dram> getAll() {
		return HibernateUtil.getList("from Dram", null);
	}
	
	public static Dram get(int id){
		return HibernateUtil.getSingle("from Dram where dramId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(Dram obj){
		return obj.getDramId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Dram obj){
		return obj.getDramId() > 0 && HibernateUtil.update(obj);
	}
}
