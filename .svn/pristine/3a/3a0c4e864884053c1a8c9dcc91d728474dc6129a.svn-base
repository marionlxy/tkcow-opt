package com.taikang.udp.view.service.impl;
 
import com.taikang.udp.view.model.NavigationBO;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.view.service.INavigationService;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
 
  
/**
  * NavigationServiceImpl
  */
 @Service(INavigationService.SERVICE_ID)
public class NavigationServiceImpl extends BaseServiceImpl implements INavigationService<NavigationBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(NavigationBO navigation) {
		logger.debug("<======NavigationServiceImpl--insertObject======>");		
		appDao.insert("Navigation.addNavigation",navigation);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(NavigationBO navigation) {
		logger.debug("<======NavigationServiceImpl--updateObject======>");
		appDao.update("Navigation.updateNavigation",navigation);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(NavigationBO navigation) {
		logger.debug("<======NavigationServiceImpl--deleteObject======>");
		appDao.delete("Navigation.deleteNavigation",navigation);
	}
	
	/**
      * 查询数据
      */
	public NavigationBO findOne(NavigationBO navigation) {
		logger.debug("<======NavigationServiceImpl--findOne======>");
		
		NavigationBO navigationBackBO=appDao.queryOneObject("Navigation.findOneNavigation", navigation);
		return navigationBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<NavigationBO> findAll(NavigationBO  navigation) {
		logger.debug("<======NavigationServiceImpl--findAll======>");
		return appDao.queryForEntityList("Navigation.findAllNavigation", navigation);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======NavigationServiceImpl--queryForPage======>");
		return appDao.queryForPage("Navigation.queryNavigationForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======NavigationServiceImpl--findAllMap======>");		
		return appDao.queryForMapList("Navigation.findAllMapNavigation", param);
	}
	
	/**
     * 批量增加数据
     */
	public boolean batchInsert(List<NavigationBO> navigationList){
		logger.debug("<======NavigationServiceImpl--batchInsert======>");
		return appDao.batchSaveByBO("Navigation.addNavigation" ,navigationList);
	}
	/**
     * 批量修改数据
     */
	public boolean batchUpdate(List<NavigationBO> navigationList){
		logger.debug("<======NavigationServiceImpl--batchUpdate======>");
		return appDao.batchUpdateByBO("Navigation.updateNavigation" ,navigationList);
	}
	/**
     * 批量删除数据
     */
	public boolean batchDelete(List<NavigationBO> navigationList){
		logger.debug("<======NavigationServiceImpl--batchDelete======>");
		return appDao.batchDeleteByBO("Navigation.deleteNavigation" ,navigationList);
	}
}
  