package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import helper.Security;
import pojo.Account;

public class AccountAdapter {
	private static final Logger logger = Logger.getLogger(AccountAdapter.class);
	
	public static List<Account> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from Account where permissionId > 0", null);
	}
	
	public static Account get(int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Account where accountId = :p0", new Object[]{ id });
	}
	
	public static Account signin(String username, String password){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from Account where username = :p0 and password = :p1", new Object[]{ username, Security.Encrypt(password) });
	}
	
	public static boolean signup(Account obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (obj.getAccountId() == 0){
			obj.setPassword(Security.Encrypt(obj.getUsername()));
			return HibernateUtil.save(obj);
		}
		return false;
	}
	
	public static boolean update(Account obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getAccountId() > 0 && HibernateUtil.execute("update Account set name = :p0, username = :p1 where accountId = :p2", new Object[]{ obj.getName(), obj.getUsername(), obj.getAccountId() }) >= 0;
	}
	
	public static boolean changePassword(String newpass, int id){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.execute("update Account set password = :p0 where accountId = :p1", new Object[]{ Security.Encrypt(newpass), id }) >= 0;
	}
	
	public static boolean resetPassword(Account obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getAccountId() > 0 && HibernateUtil.execute("update Account set password = :p0 where accountId = :p1", new Object[]{ obj.getUsername(), obj.getAccountId() }) >= 0;
	}
	
	public static boolean changePermission(Account obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (obj.getAccountId() > 0 && PermissionAdapter.get(obj.getPermission().getPermissionId()) != null
				&& HibernateUtil.execute("update Account set permissionId = :p0 where accountId = :p1", new Object[]{ obj.getPermission().getPermissionId() }) >= 0){
			HibernateUtil.refresh(obj.getPermission());
			return true;
		}
		return false;
	}
	
	public static boolean deleteOrRecover(Account obj){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return obj.getAccountId() > 0 && HibernateUtil.execute("update Account set isDeleted = :p0 where accountId = :p1", new Object[]{ obj.getIsDeleted(), obj.getAccountId() }) >= 0;
	}
}
