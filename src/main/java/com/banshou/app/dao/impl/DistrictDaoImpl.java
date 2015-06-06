package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.DistrictDao;
import com.banshou.app.domain.District;

@Repository
public class DistrictDaoImpl implements DistrictDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<District> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<District> cq = builder.createQuery(District.class);
	    Root<District> root = cq.from(District.class);
	    cq.select(root);
	    return em.createQuery(cq).getResultList();
	}
}
