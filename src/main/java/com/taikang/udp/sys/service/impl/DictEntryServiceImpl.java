package com.taikang.udp.sys.service.impl;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.cache.ICacheService;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.DictEntryBO;
import com.taikang.udp.sys.model.DictTypeBO;
import com.taikang.udp.sys.service.IDictEntryService;
import com.taikang.udp.sys.service.IDictTypeService;
import com.taikang.udp.sys.util.enums.Status;
 
  
/**
  * DictEntryServiceImpl
  */
 @Service(IDictEntryService.SERVICE_ID)
 public class DictEntryServiceImpl extends BaseServiceImpl 
 implements IDictEntryService<DictEntryBO>
 {
	//把数据放入到第三放缓存中	
	private  Map<String, List<DictTypeBO>> dictTypeMap;
	private  Map<String, List<DictEntryBO>> dictEntryMap;
  	
	@Resource(name=IDictTypeService.SERVICE_ID)
	private IDictTypeService<DictTypeBO> dictTypeService;
	
	@CacheEvict(value=ICacheService.SYS_CACHE_NAME,key=IDictEntryService.DICT_ENTRY_KEY,condition="#root.target.isCacheSwitch()",allEntries=true)
	public void remove() {
		//如果有更新，则需要刷新缓存
	}

	@Cacheable(value=ICacheService.SYS_CACHE_NAME,key=IDictEntryService.DICT_ENTRY_KEY,condition="#root.target.isCacheSwitch()")
	public Map<String, List<DictEntryBO>> get() {		
		if(dictEntryMap ==null || dictEntryMap.isEmpty() ==true)
		{
			loadDictEntry();
		}
		return dictEntryMap;	
	}
	
	/**
	 * 重新加载数据字典
	 */
	private void loadDictEntry() {
		dictTypeMap = dictTypeService.get();
		dictEntryMap = new HashMap<String, List<DictEntryBO>>();
		
		List<DictTypeBO> bscDictTypeList = dictTypeMap.get(IDictTypeService.DICT_TYPE_KEY);
		
		DictEntryBO tEntryParam;
		if (bscDictTypeList != null && bscDictTypeList.size() > 0)
		{
			Iterator<DictTypeBO> it = bscDictTypeList.iterator();
			while (it.hasNext())
			{
				DictTypeBO tDictTypeT = it.next();
				
				tEntryParam = new DictEntryBO();
				tEntryParam.setStatus(Status.Normal.getCode());
				tEntryParam.setDictTypeId(tDictTypeT.getDictTypeId());
				List<DictEntryBO> bscDictEntryList = appDao.queryForEntityList("DictEntry.findAllDictEntry",tEntryParam);
				dictEntryMap.put(tDictTypeT.getDictTypeId(), bscDictEntryList);
			}
		}
	}
	
 	/**
	  * 增加数据
	  */
	public void insertObject(DictEntryBO dictEntry) {
		logger.debug("<======DictEntryServiceImpl--insertDictEntry======>");		
		appDao.insert("DictEntry.addDictEntry",dictEntry);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(DictEntryBO dictEntry) {
		logger.debug("<======DictEntryServiceImpl--updateDictEntry======>");
		appDao.update("DictEntry.updateDictEntry",dictEntry);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(DictEntryBO dictEntry) {
		logger.debug("<======DictEntryServiceImpl--deleteDictEntry======>");
		appDao.delete("DictEntry.deleteDictEntry",dictEntry);
	}
	
	/**
      * 查询数据
      */
	public DictEntryBO findOne(DictEntryBO dictEntry) {
		logger.debug("<======DictEntryServiceImpl--findDictEntry======>");
		
		DictEntryBO dictEntryBackBO=appDao.queryOneObject("DictEntry.findOneDictEntry", dictEntry);
		return dictEntryBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<DictEntryBO> findAll(DictEntryBO  dictEntry) {
		logger.debug("<======DictEntryServiceImpl--findAllDictEntry======>");
		return appDao.queryForEntityList("DictEntry.findAllDictEntry", dictEntry);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======DictEntryServiceImpl--queryDictEntryForPage======>");
	
		return appDao.queryForPage("DictEntry.queryDictEntryForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======DictEntryServiceImpl--findAllMapDictEntry======>");		
		return appDao.queryForMapList("DictEntry.findAllMapDictEntry", param);
	}
 }