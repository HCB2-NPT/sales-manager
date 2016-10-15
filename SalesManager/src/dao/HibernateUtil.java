package dao;

import java.util.List;

import javax.persistence.Parameter;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateUtil {
	private static final Logger logger = Logger.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure("config/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static <T> List<T> getList(String hql, Object[] params){
		List<T> result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query q = session.createQuery(hql);
			if (params != null){
				int i = 0;
				for (Object p : params) {
					q.setParameter("p" + i, p);
				}
			}
			result = q.getResultList();
		} catch (HibernateException ex) {
			logger.debug(ex.getMessage());
		} finally {
			session.close();
		}
		return result;
	}
	
	public static <T> T getSingle(String hql, Object[] params){
		T result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query q = session.createQuery(hql);
			if (params != null){
				int i = 0;
				for (Object p : params) {
					q.setParameter("p" + i, p);
				}
			}
			result = (T) q.getSingleResult();
		} catch (HibernateException ex) {
			logger.debug(ex.getMessage());
		} finally {
			session.close();
		}
		return result;
	}
}
