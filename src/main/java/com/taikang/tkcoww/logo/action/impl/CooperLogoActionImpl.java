package com.taikang.tkcoww.logo.action.impl;


import com.taikang.tkcoww.logo.action.ICooperLogoAction;
import java.util.Arrays;
import java.util.List;
import com.taikang.tkcoww.logo.model.CooperLogoBO;
import org.springframework.stereotype.Service;
import com.taikang.tkcoww.logo.service.ICooperLogoService;
import javax.annotation.Resource;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;

/**
  * CooperLogoAction
  */
  @Service(ICooperLogoAction.ACTION_ID)
public class CooperLogoActionImpl extends BaseActionImpl 
  implements ICooperLogoAction {

    /**
      * 注入service
      */
    @Resource(name=ICooperLogoService.SERVICE_ID)
	private ICooperLogoService<CooperLogoBO> cooperLogoService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======CooperLogoAction--addCooperLogo======>");
		
		CooperLogoBO cooperLogoBO = BaseDto.toModel(CooperLogoBO.class , param);
		cooperLogoService.insertObject(cooperLogoBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======CooperLogoAction--updateCooperLogo======>");
		
		CooperLogoBO cooperLogoBO = BaseDto.toModel(CooperLogoBO.class , param);
		cooperLogoService.updateObject(cooperLogoBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======CooperLogoAction--deleteCooperLogo======>");
		
		CooperLogoBO cooperLogoBO = BaseDto.toModel(CooperLogoBO.class , param);
		cooperLogoService.deleteObject(cooperLogoBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======CooperLogoAction--findOneCooperLogo======>");
		
		CooperLogoBO cooperLogoBO = BaseDto.toModel(CooperLogoBO.class , param);
		return cooperLogoService.findOne(cooperLogoBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======CooperLogoAction--findAllCooperLogo======>");
				
		return cooperLogoService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======CooperLogoAction--queryCooperLogoForPage======>");
		
		return cooperLogoService.queryForPage(currentPage);
	}

	@Override
	public CurrentPage getLogoModelPage(CurrentPage page) {
        logger.debug("<======CooperLogoAction--queryCooperLogoForPage======>");
	    return cooperLogoService.getLogoModelPage(page);
	}

	/* (non-Javadoc)
	 * @see com.taikang.tkcoww.logo.action.ICooperLogoAction#getTypeCooperLogoList(com.taikang.udp.framework.core.persistence.pagination.CurrentPage)
	 * 模糊查询
	 */
	@Override
	public CurrentPage getTypeCooperLogoList(CurrentPage page) {
		// TODO Auto-generated method stub
		logger.debug("<======CooperLogoAction--getTypeCooperLogoList======>");
		return cooperLogoService.getTypeCooperLogoList(page);
	}	
}
