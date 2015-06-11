package com.banshou.app.service;

import java.util.List;

import com.banshou.app.domain.Store;

public interface StoreService {
	public void addGoods(Store store);
	public List<Store> getAll();
	public Store getStoreByNum(String number);
	public void updateStoreByNum(Store store);
	public void deleteByNum(String number);
	public void updateIcon(String iconName, String storeNum);
	public void deleteImage(String imageName, String storeNum);
	public void updateImage(String imageName, String storeNum);
}
