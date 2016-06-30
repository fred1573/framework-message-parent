package com.tomasky.msp.core.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.tomasky.msp.model.Page;

 public interface MongoBaseDao<T> {

	
	 Long count(Query query);

	 void saveOrUpdate(T t);

	 T findOne(String id);

	 List<T> find(Query query);
	 
	 Page<T> page(Page<T> page,Query query);
	 
	
}
