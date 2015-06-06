package com.banshou.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banshou.app.dao.ProDao;
import com.banshou.app.domain.Province;
import com.banshou.app.service.ProService;

@Service
public class ProServiceImpl implements ProService {

	@Autowired
	private ProDao proDao;
	
	@Override
	@Transactional
	public void addPro(Province p) {
		proDao.addPro(p);
	}

	@Override
	@Transactional
	public List<Province> getAll() {
		return proDao.getAll();
	}

	@Override
	@Transactional
	public void deleteByNumber(String number) {
		proDao.deleteByNumber(number);
	}
}
