package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Dram;

public class DramAdapter {
	private static final Logger logger = Logger.getLogger(DramAdapter.class);
	
	public static List<Dram> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Dram", null);
	}
	
	public static Dram get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Dram where dramId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(Dram obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getDramId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Dram obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getDramId() > 0 && HibernateUtil.update(obj);
	}
}
