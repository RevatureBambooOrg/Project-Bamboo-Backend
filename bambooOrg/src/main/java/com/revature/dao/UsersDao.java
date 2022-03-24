package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.model.Users;
import com.revature.util.HibernateUtil;

public class UsersDao {

	public int insert(Users user) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(user);
		tx.commit();
		return pk;
	}

	public List<Users> selectAll() {
		Session ses = HibernateUtil.getSession();
		return ses.createQuery("from SuperPrison", Users.class).list();
	}

	public Users selectById(int ID) {
		Session ses = HibernateUtil.getSession();
		return ses.get(Users.class, ID);
	}

	public void update(Users user) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(user);
		tx.commit();
	}

	public Users selectByUsername(String username) {
		Session ses = HibernateUtil.getSession();

		// Users user = (Users)ses.createQuery("from Users where name='"+username+"'
		// ",Users.class);
		// Not sure what this will return if users doesn't exist?
		return (Users) ses.createCriteria(Users.class).add(Restrictions.like("username", username));
	}
}
