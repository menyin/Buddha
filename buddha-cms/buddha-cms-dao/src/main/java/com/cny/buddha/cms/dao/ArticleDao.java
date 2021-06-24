/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.buddha.cms.dao;

import com.cny.buddha.cms.entity.Article;
import com.cny.buddha.cms.entity.Category;
import com.cny.buddha.common.persistence.CrudDao;

import java.util.List;

/**
 * 文章DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
//@MyBatisDao //去掉注解，直接在配置文件里规定 <property name="basePackage" value="com.cny.buddha.**.dao" />
public interface ArticleDao extends CrudDao<Article> {
	
	public List<Article> findByIdIn(String[] ids);
//	{
//		return find("from Article where id in (:p1)", new Parameter(new Object[]{ids}));
//	}
	
	public int updateHitsAddOne(String id);
//	{
//		return update("update Article set hits=hits+1 where id = :p1", new Parameter(id));
//	}
	
	public int updateExpiredWeight(Article article);
	
	public List<Category> findStats(Category category);
//	{
//		return update("update Article set weight=0 where weight > 0 and weightDate < current_timestamp()");
//	}
	
}
