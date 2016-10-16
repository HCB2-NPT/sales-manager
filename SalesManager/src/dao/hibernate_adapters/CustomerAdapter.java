package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.Customer;

public class CustomerAdapter {
	private static final Logger logger = Logger.getLogger(CustomerAdapter.class);
	
	public static List<Customer> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Customer", null);
	}
	
	public static Customer get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Customer where customerId = :p0", new Object[]{ id });
	}
	
	public static boolean insert(Customer obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getCustomerId() == 0 && HibernateUtil.save(obj);
	}
	
	public static boolean update(Customer obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getCustomerId() > 0 && HibernateUtil.update(obj);
	}
}
