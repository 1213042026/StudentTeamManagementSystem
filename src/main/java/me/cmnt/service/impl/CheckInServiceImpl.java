package me.cmnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.cmnt.dao.BaseDaoI;
@Service("checkInService")
public class CheckInServiceImpl extends BaseServiceImpl {

	@Autowired
	private BaseDaoI checkInDao;
	
	@Override
	public void save(Object obj) {
		checkInDao.save(obj);
	}

	@Override
	public void delete(Object obj) {
		checkInDao.delete(obj);
	}

	@Override
	public void update(Object obj) {
		checkInDao.update(obj);
	}
	
	@Override
	public List<Object> query(Object obj, int queryType) {
		return checkInDao.query(obj, queryType);
	}
}
