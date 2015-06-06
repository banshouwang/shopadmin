package com.banshou.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banshou.app.dao.DistrictDao;
import com.banshou.app.domain.District;
import com.banshou.app.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictDao districtDao;
	
	
	@Override
	@Transactional
	public List<District> getAll() {
		return districtDao.getAll();
	}
}
