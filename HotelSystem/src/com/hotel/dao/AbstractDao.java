package com.hotel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public abstract class AbstractDao {

	private SessionFactory sessionFactory ;
 	
	public AbstractDao(){

        Configuration configuration = new Configuration()
                .configure(AbstractDao.class.getClassLoader()
                        .getResource("com/hotel/resources/hibernate.cfg.xml"));
        ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	protected void saveOrUpdate(Object object){
		Session session = sessionFactory.openSession();
		try {
	    Transaction tx = session.beginTransaction();
		session.saveOrUpdate(object);
		tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close() ;
			
		}
	}
	
	
}
