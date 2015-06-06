package com.banshou.app.dao;

import java.util.List;

import com.banshou.app.domain.Province;

public interface ProDao {
	public void addPro(Province p);
	public List<Province> getAll();
	public void deleteByNumber(String number);
}
