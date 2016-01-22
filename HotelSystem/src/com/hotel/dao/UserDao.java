package com.hotel.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hotel.model.User;

public class UserDao extends AbstractDao {

	public User find(String username, String password) {
		Session session = getSessionFactory().openSession();
		try {
			String hql = " FROM User WHERE username = :username and password= :password";
			Query query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("password", password);
			List users = query.list();
			if (users != null && users.size() == 1) {
				return (User) users.iterator().next();
			} else {
				return null;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
