package com.ProFit.model;

import java.util.List;

import org.hibernate.Session;

public class HouseService implements IHouseService {
	
	private HouseDao hDao;

	public HouseService(Session session) {
		hDao = new HouseDao(session);
	}

	@Override
	public House insert(House bean) {
		return hDao.insert(bean);
	}

	@Override
	public House selectById(Integer houseid) {
		return hDao.selectById(houseid);
	}

	@Override
	public List<House> selectAll() {
		return hDao.selectAll();
	}

	@Override
	public House update(Integer houseid, String housename) {
		return hDao.update(houseid, housename);
	}

	@Override
	public boolean deleteById(Integer houseid) {
		return hDao.deleteById(houseid);
	}

}
