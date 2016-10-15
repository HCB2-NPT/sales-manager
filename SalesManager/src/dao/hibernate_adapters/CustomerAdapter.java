package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Customer;

public class CustomerAdapter {
	public static List<Customer> getAll() {
		return HibernateUtil.getList("from Customer", null);
	}
	
	public static Customer get(int id){
		return HibernateUtil.getSingle("from Customer where customerId = :p0", new Object[]{ id });
	}
}
