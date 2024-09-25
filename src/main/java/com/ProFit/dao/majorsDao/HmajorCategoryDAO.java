package com.ProFit.dao.majorsDao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.majorsBean.MajorCategoryBean;

@Repository
@Transactional
public class HmajorCategoryDAO implements IHmajorCategoryDAO {

	@Autowired
	private SessionFactory factory;
	

	// 新增 MajorCategory
	@Override
	public MajorCategoryBean insertMajorCategory(MajorCategoryBean majorCategory) {
		Session session = factory.getCurrentSession();
		session.persist(majorCategory);
		return majorCategory;
	}

	// 修改 MajorCategory(的name by id)
	@Override
	public boolean updateMajorCategory(MajorCategoryBean newMajorCategory) {
		// 查詢原本的資料
		Session session = factory.getCurrentSession();
		MajorCategoryBean oldMajorCategory = session.get(MajorCategoryBean.class,
				newMajorCategory.getMajorCategoryId());

		// 若原本沒有資料存在
		if (oldMajorCategory == null) {
			System.out.println("Category with id:" + newMajorCategory.getMajorCategoryId() + "does not exist");
			return false;
		} else {
			// 若有則更新
			oldMajorCategory
					.setCategoryName(newMajorCategory.getCategoryName() == null ? oldMajorCategory.getCategoryName()
							: newMajorCategory.getCategoryName());

			session.merge(oldMajorCategory);
			return true;
		}
	}

	// 刪除 MajorCategory(by id)
	@Override
	public boolean deleteMajorCategory(int majorCategoryId) {
		Session session = factory.getCurrentSession();
		MajorCategoryBean resultBean = session.get(MajorCategoryBean.class, majorCategoryId);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	// 查詢全部 MajorCategory
	@Override
	public List<MajorCategoryBean> findAllMajorCategories() {
		Session session = factory.getCurrentSession();
		Query<MajorCategoryBean> query = session.createQuery("from MajorCategoryBean", MajorCategoryBean.class);
		return query.list();
	}

	// 查詢單筆 MajorCategory(by id)
	@Override
	public MajorCategoryBean findMajorCategoryById(int majorCategoryId) {
		Session session = factory.getCurrentSession();
		return session.get(MajorCategoryBean.class, majorCategoryId);
	}

}
