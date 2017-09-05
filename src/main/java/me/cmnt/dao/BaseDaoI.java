package me.cmnt.dao;

import java.util.List;

public interface BaseDaoI {
	
	/**
	 * 删除
	 * @param obj
	 */
	public void delete(Object obj);
	
	/**
	 * 增加
	 * @param obj
	 */
	public void save(Object obj);
	
	/**
	 * 更新
	 * @param obj
	 */
	public void update(Object obj);
	
	/**
	 * 查找
	 * @param obj
	 * @param queryType
	 * @return
	 */
	public List<Object> query(Object obj, int queryType);
}
