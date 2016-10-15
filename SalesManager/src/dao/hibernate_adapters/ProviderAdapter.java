package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Provider;

public class ProviderAdapter {
	public static List<Provider> getAll() {
		return HibernateUtil.getList("from Provider", null);
	}
	
	public static Provider get(int id){
		return HibernateUtil.getSingle("from Provider where providerId = :p0", new Object[]{ id });
	}
}
