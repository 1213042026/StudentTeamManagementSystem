package me.cmnt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import me.cmnt.model.CheckIn;
@Repository("checkInDao")
public class CheckInDaoImpl extends BaseDaoImpl {

	@Override
	public List<Object> query(Object obj, int queryType) {
		if (obj instanceof CheckIn) {
			CheckIn checkIn = (CheckIn) obj;
			String HQL = "";
			switch (queryType) {
				case 0: HQL = "from CheckIn"; break;
				case 1: HQL = "from CheckIn where id = " + checkIn.getId(); break;
				case 2: HQL = "from CheckIn where community_id =" + checkIn.getCommunity_id(); break;
				case 3: HQL = "from CheckIn"; break;
				default: break;
			}
			if (!HQL.isEmpty()) {
				if (queryType == 3) {
					return sessionFactory.getCurrentSession().createQuery(HQL).setMaxResults(10).list();
				} else {
					return sessionFactory.getCurrentSession().createQuery(HQL).list();
				}
			}
		}
		return null;
	}

}
