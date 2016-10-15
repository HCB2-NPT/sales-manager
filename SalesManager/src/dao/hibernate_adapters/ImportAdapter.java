package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Import;

public class ImportAdapter {
	public static List<Import> getAll() {
		return HibernateUtil.getList("from Import", null);
	}
	
	public static Import get(int id){
		return HibernateUtil.getSingle("from Import where importId = :p0", new Object[]{ id });
	}
}
