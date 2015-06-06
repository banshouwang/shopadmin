package com.banshou.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banshou.app.dao.CityDao;
import com.banshou.app.domain.City;
import com.banshou.app.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Override
	@Transactional
	public void addCity(City city) {
		cityDao.addCity(city);
	}

	@Override
	@Transactional
	public List<City> getAll() {
		return cityDao.getAll();
	}

	@Override
	@Transactional
	public void deleteByNumber(String number) {
		cityDao.deleteByNumber(number);
	}
}
