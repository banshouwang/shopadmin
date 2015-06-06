package com.banshou.app.service;

import java.util.List;

import com.banshou.app.domain.City;

public interface CityService {
	public void addCity(City c);
	public List<City> getAll();
	public void deleteByNumber(String number);
}
