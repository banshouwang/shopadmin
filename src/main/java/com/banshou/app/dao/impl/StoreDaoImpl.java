package com.banshou.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.banshou.app.dao.StoreDao;
import com.banshou.app.domain.Store;

@Repository
public class StoreDaoImpl implements StoreDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addGoods(Store store) {

	}

	@Override
	public List<Store> getAll() {
		return null;
	}

	@Override
	public Store getStoreByNum(String number) {
		Store s = em.find(Store.class, number);
		return s == null ? null : s;
	}

	@Override
	public void deleteByNum(String number) {

	}

	@Override
	public void updateStoreByNum(Store store) {
		Store s = em.find(Store.class, store.getNumber());
		s.setName(store.getName());
		s.setAddress(store.getAddress());
		s.setBrief(store.getBrief());
		s.setLatitude(store.getLatitude());
		s.setLongitude(store.getLongitude());
		s.setTel(store.getTel());
		em.merge(s);
		em.flush();
	}

	@Override
	public void updateIcon(String iconName, String storeNum) {
		Store s = em.find(Store.class, storeNum);
		s.setIcon(iconName);
		em.merge(s);
		em.flush();
	}

	@Override
	public void deleteImage(String imageName, String storeNum) {
		Store s = em.find(Store.class, storeNum);
		String[] images = s.getImage().split(",");
		String tmp = "";
		for(int i=0; i<images.length; i++){
			if(!imageName.equals(images[i])){
				tmp = tmp + images[i] + ",";
			}
		}
		if(!"".equals(tmp)){
			tmp = tmp.substring(0, tmp.length()-1);
		}
		
		s.setImage(tmp);
		em.merge(s);
		em.flush();
	}

	@Override
	public void updateImage(String imageName, String storeNum) {
		Store s = em.find(Store.class, storeNum);
		String image = s.getImage();
		if(!"".equals(image)){
			image = image + "," + imageName;
		} else {
			image = image + imageName;
		}
		s.setImage(image);
		em.merge(s);
		em.flush();
	}
}
