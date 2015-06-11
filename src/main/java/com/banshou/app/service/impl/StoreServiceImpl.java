package com.banshou.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banshou.app.dao.StoreDao;
import com.banshou.app.domain.Store;
import com.banshou.app.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDao;

	@Override
	@Transactional
	public void addGoods(Store store) {
		
	}

	@Override
	@Transactional
	public List<Store> getAll() {
		return null;
	}

	@Override
	@Transactional
	public Store getStoreByNum(String number) {
		return storeDao.getStoreByNum(number);
	}

	@Override
	@Transactional
	public void deleteByNum(String number) {
		
	}

	@Override
	@Transactional
	public void updateStoreByNum(Store store) {
		storeDao.updateStoreByNum(store);
	}

	@Override
	@Transactional
	public void updateIcon(String iconName, String storeNum) {
		storeDao.updateIcon(iconName, storeNum);
	}

	@Override
	@Transactional
	public void deleteImage(String imageName, String storeNum) {
		storeDao.deleteImage(imageName, storeNum);
	}

	@Override
	@Transactional
	public void updateImage(String imageName, String storeNum) {
		storeDao.updateImage(imageName, storeNum);
	}
}
