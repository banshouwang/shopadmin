package com.banshou.app.dao;

import java.util.List;

import com.banshou.app.domain.City;

public interface CityDao {
	public void addCity(City c);
	public List<City> getAll();
	public void deleteByNumber(String number);
}
