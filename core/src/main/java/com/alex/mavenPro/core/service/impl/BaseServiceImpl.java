package com.alex.mavenPro.core.service.impl;

import com.alex.mavenPro.core.DAO.BaseDao;
import com.alex.mavenPro.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	//@Qualifier(value = "baseDao")
	private BaseDao<T> baseDao;


	public Serializable save(T o) {
		return baseDao.save(o);
	}

	public void save(List<T> list) {
		for (T t : list) {
			baseDao.save(t);
		}
	}

	public void delete(T o) {
		baseDao.delete(o);
	}

	public void update(T o) {
		baseDao.update(o);
	}

	public void saveOrUpdate(T o) {
		baseDao.saveOrUpdate(o);
	}

	public T getById(Serializable id) {
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return baseDao.getById(c, id);
	}

	public T getByHql(String hql) {
		return baseDao.getByHql(hql);
	}

	public T getByHql(String hql, Map<String, Object> params) {
		return baseDao.getByHql(hql, params);
	}

	public List<T> find() {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		return find(hql);
	}

	public List<T> find(String hql) {
		return baseDao.find(hql);
	}

	public List<T> find(String hql, Map<String, Object> params) {
		return baseDao.find(hql, params);
	}

	public List<T> find(String hql, int page, int rows) {
		return baseDao.find(hql, page, rows);
	}

	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		return baseDao.find(hql, params, page, rows);
	}

	public List<T> find(int page, int rows) {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		return find(hql, page, rows);
	}

	public Long count(String hql) {
		return baseDao.count(hql);
	}

	public Long count(String hql, Map<String, Object> params) {
		return baseDao.count(hql, params);
	}

	public Long count() {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select count(distinct t) from " + className + " t";
		return count(hql);
	}

	public int executeHql(String hql) {
		return baseDao.executeHql(hql);
	}

	public int executeHql(String hql, Map<String, Object> params) {
		return baseDao.executeHql(hql, params);
	}

	public List findBySql(String sql) {
		return baseDao.findBySql(sql);
	}

	public List findBySql(String sql, int page, int rows) {
		return baseDao.findBySql(sql, page, rows);
	}

	public List findBySql(String sql, Map<String, Object> params) {
		return baseDao.findBySql(sql, params);
	}

	public List findBySql(String sql, Map<String, Object> params, int page, int rows) {
		return baseDao.findBySql(sql, params, page, rows);
	}

	public int executeSql(String sql) {
		return baseDao.executeSql(sql);
	}

	public int executeSql(String sql, Map<String, Object> params) {
		return baseDao.executeSql(sql, params);
	}

	public BigInteger countBySql(String sql) {
		return baseDao.countBySql(sql);
	}

	public BigInteger countBySql(String sql, Map<String, Object> params) {
		return baseDao.countBySql(sql, params);
	}

}
