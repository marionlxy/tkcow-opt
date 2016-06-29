package com.taikang.udp.threadpool.action;

import java.util.List;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.util.vo.Tree;


/**
  * IComAction
  */

public interface ITestThreadAction extends IBaseAction { 

	final String ACTION_ID = "testThreadAction"; 	
	
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
	 * 按条件分页查询机构数据<br/>
	 * @param currentPage
	 * @return CurrentPage
	 */
	public CurrentPage getByconlist (CurrentPage currentPage);
}
