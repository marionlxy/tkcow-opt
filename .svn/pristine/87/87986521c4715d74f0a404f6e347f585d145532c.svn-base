package com.taikang.udp.sys.service;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.IBaseService;
import com.taikang.udp.sys.util.vo.Tree;

/**
  * IComService
  */
 
 public interface IComService<T> extends IBaseService<T>{
 
 	final String SERVICE_ID = "comService";  	
 	
	/**
	 * 获取机构树信息<br/>
	 * @return   
	 * List<Tree>
	 */
	public List<Tree> getComInfo(); 
	
	/**
	 * 获取指定机构的子机构信息<br/>
	 * @param comId
	 * @return   
	 * List<Tree>
	 */
	public List<Tree> getChildrenTreeLst(Integer comId, String state);
	
	/**
	 * 获取指定机构的子机构信息<br/>
	 * @param comId
	 * @return   
	 * List<Tree>
	 */
	public CurrentPage getChildList(CurrentPage currentPage);
	
	/**
	 * 保存机构数据<br/>
	 * @param param   
	 * void
	 */
	public void savwObject(Dto param);
	
	/**
	 * 获取指定条件取得机构信息<br/>
	 * @param comId
	 * @return   
	 * List<Tree>
	 */
	public CurrentPage getByconlist(CurrentPage currentPage);
 }
 
 
 