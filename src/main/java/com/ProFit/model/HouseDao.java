package com.ProFit.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class HouseDao implements IHouseDao{

	private Session session;

	public HouseDao(Session session) {
		this.session = session;
	}

	@Override
	public House insert(House bean) {
		if (bean != null) {
			session.persist(bean);
			return bean;
		}
		return null;
	}

	@Override
	public House selectById(Integer houseid) {
		return session.get(House.class, houseid);
	}

	@Override
	public List<House> selectAll() {
		Query<House> query = session.createQuery("from House", House.class);
		return query.list();
	}

	@Override
	public House update(Integer houseid, String housename) {
		House resultBean = session.get(House.class, houseid);

		if (resultBean != null) {
			resultBean.setHousename(housename);
		}

		return resultBean;
	}

	@Override
	public boolean deleteById(Integer houseid) {
		House resultBean = session.get(House.class, houseid);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

}
