package com.banshou.app.service;

import java.util.List;

import com.banshou.app.domain.Province;

public interface ProService {
	public void addPro(Province p);
	public List<Province> getAll();
	public void deleteByNumber(String number);
}
