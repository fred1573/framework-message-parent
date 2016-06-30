package com.tomasky.msp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tomasky.msp.core.dao.MongoBaseDao;
import com.tomasky.msp.core.support.ReflectUtils;
import com.tomasky.msp.model.Page;

@Repository
public   class MongoDaoImpl<T> implements MongoBaseDao<T> {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@SuppressWarnings("unchecked")
	private Class<T> getGenericType(){
		return ReflectUtils.getClassGenericType(this.getClass(), 0);
	}
	
	
	@Override
	public Long count(Query query) {
		return mongoTemplate.count(query,getGenericType());
	}


	@Override
	public void saveOrUpdate(T t) {
		mongoTemplate.save(t);
	}

	@Override
	public T findOne(String id) {
		return mongoTemplate.findById(id, getGenericType());
	}


	@Override
	public List<T> find(Query query) {
		return  mongoTemplate.find(query, getGenericType());
	}


	@Override
	public Page<T> page(Page<T> page, Query query) {
		page.setTotalCount(this.count(query));
		query.skip((page.getPageIndex()-1) * page.getPageSize()).limit(page.getPageSize());
		page.setDatas(this.find(query));
		return page;
	}
	
}
