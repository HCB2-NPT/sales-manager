package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pojo.Account;

public class AccountAdapter {
	public static List<Account> getAll() {
		List<Account> ds = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		String hql = "from Account";
		ds = session.createQuery(hql).getResultList();
		} catch (HibernateException ex) {
			//Log the exception
			System.err.println(ex);
		} finally {
			session.close();
		}
		return ds;
	}
}
