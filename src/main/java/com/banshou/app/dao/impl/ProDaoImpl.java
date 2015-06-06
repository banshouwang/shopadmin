package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.ProDao;
import com.banshou.app.domain.Province;

@Repository
public class ProDaoImpl implements ProDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addPro(Province p) {
		em.persist(p);
	}
	
	@Override
	public List<Province> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Province> cq = builder.createQuery(Province.class);
	    Root<Province> root = cq.from(Province.class);
	    cq.select(root);
	    return em.createQuery(cq).getResultList();
	}

	@Override
	public void deleteByNumber(String number) {
		Province p = em.find(Province.class, number);
		em.remove(p);
	}
}
