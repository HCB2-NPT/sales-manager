package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Permission;

public class PermissionAdapter {
	public static List<Permission> getAll() {
		return HibernateUtil.getList("from Permission", null);
	}
	
	public static Permission get(int id){
		return HibernateUtil.getSingle("from Permission where permissionId = :p0", new Object[]{ id });
	}
}
