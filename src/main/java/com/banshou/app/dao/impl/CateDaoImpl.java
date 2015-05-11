package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.CateDao;
import com.banshou.app.domain.Category;

@Repository
public class CateDaoImpl implements CateDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addCate(Category category) {
		em.persist(category);
	}
	
	@Override
	public List<Category> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Category> cq = builder.createQuery(Category.class);
	    Root<Category> root = cq.from(Category.class);
	    cq.select(root);
	    return em.createQuery(cq).getResultList();
	}
}
