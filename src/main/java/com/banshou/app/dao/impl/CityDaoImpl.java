package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.CityDao;
import com.banshou.app.domain.City;

@Repository
public class CityDaoImpl implements CityDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<City> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<City> cq = builder.createQuery(City.class);
	    Root<City> root = cq.from(City.class);
	    cq.select(root);
	    return em.createQuery(cq).getResultList();
	}

	@Override
	public void deleteByNumber(String number) {
		City c = em.find(City.class, number);
		em.remove(c);
	}

	@Override
	public void addCity(City c) {
		em.persist(c);
	}
}
