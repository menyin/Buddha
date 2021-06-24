/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.buddha.common.service;

import com.cny.buddha.common.dao.persistence.CrudDao;
import com.cny.buddha.common.entity.persistence.Page;
import com.cny.buddha.common.edsw.utils.IdGen;
import com.cny.buddha.common.edsw.utils.SessionUtils;
import com.cny.buddha.common.edsw.utils.StringUtils;
import com.cny.buddha.common.entity.persistence.DataEntity;
import com.cny.buddha.common.entity.persistence.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	protected CrudService() {
	}

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			this.preInsert(entity);
			dao.insert(entity);
		}else{
			this.preUpdate(entity);
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	protected void preInsert(T entity){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!entity.getIsNewRecord()){
			entity.setId(IdGen.uuid());
		}
		User user = SessionUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			entity.setUpdateBy(user);
			entity.setCreateBy(user);
		}
		entity.setUpdateDate(new Date());
		entity.setCreateDate(entity.getUpdateDate());

	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	protected void preUpdate(T entity){
		User user = SessionUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			entity.setUpdateBy(user);
		}
		entity.setUpdateDate(new Date());
	}

}
