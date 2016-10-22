package dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javafx.collections.ObservableList;

public class HibernateUtil {
	private static final Logger logger = Logger.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure("config/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void tryConnectDatabase(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
	}
	
	public static <T> List<T> getList(String hql, Object[] params){
		List<T> result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery(hql);
			if (params != null){
				int i = 0;
				for (Object p : params) {
					q.setParameter("p" + i++, p);
				}
			}
			result = q.getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return result;
	}
	
	public static <T> T getSingle(String hql, Object[] params){
		T result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery(hql);
			if (params != null){
				int i = 0;
				for (Object p : params) {
					q.setParameter("p" + i++, p);
				}
			}
			result = (T) q.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return result;
	}
	
	public static int execute(String hql, Object[] params){
		int result = -1;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery(hql);
			if (params != null){
				int i = 0;
				for (Object p : params) {
					q.setParameter("p" + i++, p);
				}
			}
			result = q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return result;
	}
	
	public static <T> boolean save(T obj){
		boolean success = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
			success = true;
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return success;
	}
	
	public static <T> boolean saveList(ObservableList<T> obj){
		boolean success = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			for (T t : obj) {
				session.save(t);
			}
			session.getTransaction().commit();
			success = true;
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return success;
	}
	
	public static <T> boolean saveOrUpdate(T obj){
		boolean success = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
			success = true;
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return success;
	}
	
	public static <T> boolean delete(T obj){
		boolean success = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
			success = true;
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return success;
	}
	
	public static <T> boolean update(T obj){
		boolean success = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			success = true;
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return success;
	}
	
	public static <T> boolean refresh(T obj){
		boolean success = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.refresh(obj);
			session.getTransaction().commit();
			success = true;
		} catch (Exception ex) {
			session.getTransaction().rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return success;
	}
}
