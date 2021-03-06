package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Import;

public class ImportAdapter {
	private static final Logger logger = Logger.getLogger(ImportAdapter.class);
	
	public static List<Import> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Import", null);
	}
	
	public static Import get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Import where importId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(pojo.Import obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.save(obj);
	}
	
	public static pojo.Import getCreatedLast(){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Import a where a.importId = (select max(b.importId) from Import b)", null);
	}
}
