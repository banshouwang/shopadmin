package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.UserDao;
import com.banshou.app.domain.Order;
import com.banshou.app.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public boolean isExist(String openId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserInfo(String openId) {
		return em.find(User.class, openId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User login(String mobile, String pass) {
		try {
			//String sql = "SELECT * FROM bs_user WHERE u_mobile='" + mobile + "' AND u_password='" + pass + "'";
			String sql = "SELECT * FROM bs_user WHERE u_mobile=? AND u_password=?";
			System.out.println("SQL: " + sql);
			Query query = em.createNativeQuery(sql, User.class);
			query.setParameter(1, mobile);
			query.setParameter(2, pass);
			List<User> users = query.getResultList();
			return users.get(0);
		} catch (Exception e) {
			return null;
		}
	}

}
