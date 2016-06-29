package com.taikang.udp.view.action.impl;


import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import javax.annotation.Resource;
import com.taikang.udp.view.action.INavigationAction;
import com.taikang.udp.view.model.NavigationBO;
import java.util.List;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.view.service.INavigationService;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;

/**
  * NavigationAction
  */
  @Service(INavigationAction.ACTION_ID)
public class NavigationActionImpl extends BaseActionImpl 
  implements INavigationAction {

    /**
      * 注入service
      */
    @Resource(name=INavigationService.SERVICE_ID)
	private INavigationService<NavigationBO> navigationService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======NavigationAction--addNavigation======>");
		
		NavigationBO navigationBO = BaseDto.toModel(NavigationBO.class , param);
		navigationService.insertObject(navigationBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======NavigationAction--updateNavigation======>");
		
		NavigationBO navigationBO = BaseDto.toModel(NavigationBO.class , param);
		navigationService.updateObject(navigationBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======NavigationAction--deleteNavigation======>");
		
		NavigationBO navigationBO = BaseDto.toModel(NavigationBO.class , param);
		navigationService.deleteObject(navigationBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======NavigationAction--findOneNavigation======>");
		
		NavigationBO navigationBO = BaseDto.toModel(NavigationBO.class , param);
		return navigationService.findOne(navigationBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======NavigationAction--findAllNavigation======>");
				
		return navigationService.findAllMap(param);
	} 
	
	/**
     * 查询所有数据
     */
	public List<NavigationBO> findAll(NavigationBO param) {
		logger.debug("<======NavigationAction--findAllNavigation======>");
				
		return navigationService.findAll(param);
	}
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======NavigationAction--queryNavigationForPage======>");
		
		return navigationService.queryForPage(currentPage);
	}

	@Override
	public void deleteObject(NavigationBO param) {
		// TODO Auto-generated method stub
		navigationService.deleteObject(param);
	}

	@Override
	public void insertObject(NavigationBO param) {
		// TODO Auto-generated method stub
		navigationService.insertObject(param);
		
	}

	@Override
	public void updateObject(NavigationBO param) {
		// TODO Auto-generated method stub
		navigationService.updateObject(param);
		
	}

	@Override
	public NavigationBO findOne(NavigationBO param) {
		// TODO Auto-generated method stub
		return navigationService.findOne(param);
	}	
}
