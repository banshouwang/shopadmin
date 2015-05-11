package com.banshou.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banshou.app.dao.CateDao;
import com.banshou.app.domain.Category;
import com.banshou.app.service.CateService;

@Service
public class CateServiceImpl implements CateService {

	@Autowired
	private CateDao cateDao;
	
	@Override
	@Transactional
	public void addCate(Category category) {
		cateDao.addCate(category);
	}

	@Override
	@Transactional
	public List<Category> getAll() {
		return cateDao.getAll();
	}

}
