package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Provider;

public class ProviderAdapter {
	private static final Logger logger = Logger.getLogger(ProviderAdapter.class);
	
	public static List<Provider> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Provider", null);
	}
	
	public static Provider get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Provider where providerId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(Provider obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getProviderId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Provider obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getProviderId() > 0 && HibernateUtil.update(obj);
	}
}
