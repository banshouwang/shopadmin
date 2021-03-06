package com.banshou.app.dao;

import java.util.List;

import com.banshou.app.domain.Goods;

public interface GoodsDao {
	public void addGoods(Goods goods);
	public List<Goods> getAllByNumber(String storeNumber);
	public List<Goods> getGoodsByAddress(String addr);
	public void deleteById(String id);
	public String consume(String password);
}
