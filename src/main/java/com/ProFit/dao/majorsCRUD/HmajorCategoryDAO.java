package com.ProFit.dao.majorsCRUD;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ProFit.bean.majorsBean.MajorCategoryBean;

public class HmajorCategoryDAO implements IHmahorCategoryDAO {

	private Session session;

	public HmajorCategoryDAO(Session session) {
		this.session = session;
	}

	// 新增 MajorCategory
	@Override
	public MajorCategoryBean insertMajorCategory(MajorCategoryBean majorCategory) {
		session.persist(majorCategory);
		return majorCategory;
	}

	// 修改 MajorCategory(的name by id)
	@Override
	public boolean updateMajorCategory(MajorCategoryBean newMajorCategory) {
		// 查詢原本的資料
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
			return false;
		}
	}

	// 刪除 MajorCategory(by id)
	@Override
	public boolean deleteMajorCategory(int majorCategoryId) {
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
		Query<MajorCategoryBean> query = session.createQuery("from MajorCategoryBean", MajorCategoryBean.class);
		return query.list();
	}

	// 查詢單筆 MajorCategory(by id)
	@Override
	public MajorCategoryBean findMajorCategoryById(int majorCategoryId) {
		return session.get(MajorCategoryBean.class, majorCategoryId);
	}

}
