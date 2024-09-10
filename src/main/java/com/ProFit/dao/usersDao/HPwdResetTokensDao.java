package com.ProFit.dao.usersDao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.ProFit.bean.usersBean.Pwd_reset_tokens;

public class HPwdResetTokensDao implements IHPwdResetTokensDao {

	private Session session;

	public HPwdResetTokensDao(Session session) {
		this.session = session;
	}

	@Override
	public Pwd_reset_tokens saveTokensInfo(Pwd_reset_tokens token) {
		session.persist(token);
		return token;
	}

	@Override
	public boolean deleteTokensInfo(int token_id) {
		Pwd_reset_tokens resultBean = session.get(Pwd_reset_tokens.class, token_id);
		if (resultBean != null) {
			session.remove(resultBean);
			return true;
		}
		return false;
	}

	@Override
	public List<Pwd_reset_tokens> getAllTokensInfo() {
		List<Pwd_reset_tokens> tokens = new ArrayList<>();

		String hql = "SELECT t FROM Pwd_reset_tokens t JOIN t.user u";

		Query<Pwd_reset_tokens> query = session.createQuery(hql, Pwd_reset_tokens.class);
		tokens = query.getResultList();

		return tokens;
	}

}
