package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.OrderDao;
import com.banshou.app.domain.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Order> getAll() {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Order> cq = builder.createQuery(Order.class);
			Root<Order> root = cq.from(Order.class);
			cq.select(root);
			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getToday() {
		String sql = "SELECT * FROM bs_order WHERE date(o_createTime) = curdate()";
		System.out.println("SQL: " + sql);
		Query query = em.createNativeQuery(sql, Order.class);
		List<Order> orders = query.getResultList();
		return orders;
	}

	@Override
	public String deleteById(String id) {
		try {
			Order order = em.find(Order.class, id);
			em.remove(order);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
}
