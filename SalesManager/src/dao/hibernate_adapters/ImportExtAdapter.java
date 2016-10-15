package dao.hibernate_adapters;

import java.util.List;

import org.apache.log4j.Logger;

import dao.HibernateUtil;
import pojo.ImportExt;

public class ImportExtAdapter {
	private static final Logger logger = Logger.getLogger(ImportExtAdapter.class);
	
	public enum OneColumn{
		Import, Item, Provider
	}
	
	public enum TwoColumn{
		Import_Item, Import_Provider, Item_Provider
	}
	
	public static List<ImportExt> getAll() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getList("from ImportExt", null);
	}
	
	public static List<ImportExt> where(OneColumn e, int p0){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		String hql = "from ImportExt";
		switch (e){
			case Import:
				hql = "from ImportExt where importId = :p0";
				break;
			case Item:
				hql = "from ImportExt where itemId = :p0";
				break;
			case Provider:
				hql = "from ImportExt where providerId = :p0";
				break;
		}
		return HibernateUtil.getList(hql, new Object[]{ p0 });
	}
	
	public static List<ImportExt> where(TwoColumn e, int p0, int p1){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		String hql = "from ImportExt";
		switch (e){
			case Import_Item:
				hql = "from ImportExt where importId = :p0 and itemId = :p1";
				break;
			case Import_Provider:
				hql = "from ImportExt where importId = :p0 and providerId = :p1";
				break;
			case Item_Provider:
				hql = "from ImportExt where itemId = :p0 and providerId = :p1";
				break;
		}
		return HibernateUtil.getList(hql, new Object[]{ p0, p1 });
	}
	
	public static ImportExt where(int importid, int itemid, int providerid){
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return HibernateUtil.getSingle("from ImportExt where importId = :p0 and itemId = :p1 and providerId = :p2", new Object[]{ importid, itemid, providerid });
	}
}
