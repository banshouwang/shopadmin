package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.GoodsDao;
import com.banshou.app.domain.Goods;
import com.banshou.app.domain.OrderAndGoods;

@Repository
public class GoodsDaoImpl implements GoodsDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addGoods(Goods goods) {
		em.persist(goods);
	}

	@Override
	public List<Goods> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Goods> cq = builder.createQuery(Goods.class);
		Root<Goods> root = cq.from(Goods.class);
		cq.select(root);
		return em.createQuery(cq).getResultList();
	}

	@Override
	public void deleteById(String id) {
		Goods goods = em.find(Goods.class, id);
		em.remove(goods);
	}

	@Override
	public List<Goods> getGoodsByAddress(String addr) {
		return null;
	}

	@Override
	public String consume(String password) {
		Query query = em.createNativeQuery("SELECT * FROM bs_order_goods where g_password ='" + password + "'", OrderAndGoods.class);
		OrderAndGoods oag = (OrderAndGoods) query.getSingleResult();
		
		if("unconsume".equals(oag.getStatus())){
			oag.setStatus("consumed");
			em.merge(oag);
			em.flush();
			return "success";
		} else {
			return "already";
		}
	}
}
