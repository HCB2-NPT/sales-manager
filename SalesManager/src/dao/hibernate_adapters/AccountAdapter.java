package dao.hibernate_adapters;

import java.util.List;

import dao.HibernateUtil;
import pojo.Account;

public class AccountAdapter {
	public static List<Account> getAll() {
		return HibernateUtil.getList("from Account", null);
	}
	
	public static Account get(int id){
		return HibernateUtil.getSingle("from Account where accountId = :p0", new Object[]{ id });
	}
}
