package com.taikang.udp.sys.service.impl;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.cache.ICacheService;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.DictTypeBO;
import com.taikang.udp.sys.service.IDictTypeService;
import com.taikang.udp.sys.util.enums.Status;
 
  
/**
  * DictTypeServiceImpl
  */
 @Service(IDictTypeService.SERVICE_ID)
 public class DictTypeServiceImpl extends BaseServiceImpl 
 implements IDictTypeService<DictTypeBO>  
 {
	//把数据放入到第三放缓存中	
	private Map<String, List<DictTypeBO>> dictTypeMap;
	
	@CacheEvict(value=ICacheService.SYS_CACHE_NAME,key=IDictTypeService.DICT_TYPE_KEY,condition="#root.target.isCacheSwitch()",allEntries=true)
	public void remove() {
		//如果有更新，则需要刷新缓存
	}
	
	@Cacheable(value=ICacheService.SYS_CACHE_NAME,key=IDictTypeService.DICT_TYPE_KEY,condition="#root.target.isCacheSwitch()")
	public Map<String, List<DictTypeBO>> get() {		
		if(dictTypeMap ==null || dictTypeMap.isEmpty()) {
			loadDictType();
		}
		return dictTypeMap;	
	}
	
	/**
	 * 重新加载数据字典
	 */
	private void loadDictType() {
		dictTypeMap = new HashMap<String, List<DictTypeBO>>();
		
		DictTypeBO tTypeParam = new DictTypeBO();
		tTypeParam.setStatus(Status.Normal.getCode());
		List<DictTypeBO> bscDictTypeList = this.findAll(tTypeParam);
		dictTypeMap.put(IDictTypeService.DICT_TYPE_KEY, bscDictTypeList);
	}
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(DictTypeBO dictType) {
		logger.debug("<======DictTypeServiceImpl--insertDictType======>");		
		appDao.insert("DictType.addDictType",dictType);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(DictTypeBO dictType) {
		logger.debug("<======DictTypeServiceImpl--updateDictType======>");
		appDao.update("DictType.updateDictType",dictType);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(DictTypeBO dictType) {
		logger.debug("<======DictTypeServiceImpl--deleteDictType======>");
		appDao.delete("DictType.deleteDictType",dictType);
	}
	
	/**
      * 查询数据
      */
	public DictTypeBO findOne(DictTypeBO dictType) {
		logger.debug("<======DictTypeServiceImpl--findDictType======>");
		
		DictTypeBO dictTypeBackBO=appDao.queryOneObject("DictType.findOneDictType", dictType);
		return dictTypeBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<DictTypeBO> findAll(DictTypeBO  dictType) {
		logger.debug("<======DictTypeServiceImpl--findAllDictType======>");
		return appDao.queryForEntityList("DictType.findAllDictType", dictType);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======DictTypeServiceImpl--queryDictTypeForPage======>");
		return appDao.queryForPage("DictType.queryDictTypeForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======DictTypeServiceImpl--findAllMapDictType======>");		
		return appDao.queryForMapList("DictType.findAllMapDictType", param);
	}

	/**
	 * 查询字典表一级类型<br/>
	 * @param param
	 * @return   
	 * List<Dto>
	 */
	public List<Dto> findTopDictMap(Dto param) {
		logger.debug("<======DictTypeServiceImpl--findTopDictMap======>");
		return appDao.queryForMapList("dictdeal.findTopDictMap", param);
	}

	/**
	 * 查询字典表所有子类型<br/>
	 * @param param
	 * @return   
	 * List<Dto>
	 */
	public List<Dto> queryDictTypeChilds(Dto param) {
		logger.debug("<======DictTypeServiceImpl--queryDictTypeChilds======>");
		return appDao.queryForMapList("dictdeal.queryDictTypeChilds", param);
	}
 }
  