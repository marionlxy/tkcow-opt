package com.taikang.udp.sys.service;

import java.util.List;

import com.taikang.udp.framework.common.cache.ICacheService;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.service.IBaseService;

/**
  * IDictTypeService
  */
 
public interface IDictTypeService<T> extends IBaseService<T>,
        ICacheService<String, List<T>> {

	public static final String SERVICE_ID = "dictTypeService";

	public static final String DICT_TYPE_KEY = "dictTypeKey";

	/**
	 * 查询字典表一级类型<br/>
	 * 
	 * @param param
	 * @return List<Dto>
	 */
	public List<Dto> findTopDictMap(Dto param);

	/**
	 * 查询字典表所有子类型<br/>
	 * 
	 * @param param
	 * @return List<Dto>
	 */
	public List<Dto> queryDictTypeChilds(Dto param);
}
 
 
 