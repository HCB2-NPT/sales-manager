package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Permission;

public class PermissionAdapter {
	private static final Logger logger = Logger.getLogger(PermissionAdapter.class);
	
	public static List<Permission> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Permission where permissionId > 0", null);
	}
	
	public static Permission get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Permission where permissionId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(Permission obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getPermissionId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Permission obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getPermissionId() > 0 && HibernateUtil.update(obj);
	}
}
