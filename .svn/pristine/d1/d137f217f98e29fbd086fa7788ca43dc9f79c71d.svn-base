package com.taikang.udp.file.action.impl;


import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import com.taikang.udp.file.service.IFilepathService;
import javax.annotation.Resource;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.file.action.IFilepathAction;
import com.taikang.udp.file.model.FilepathBO;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;

/**
  * FilepathAction
  */
  @Service(IFilepathAction.ACTION_ID)
public class FilepathActionImpl extends BaseActionImpl 
  implements IFilepathAction {

    /**
      * 注入service
      */
    @Resource(name=IFilepathService.SERVICE_ID)
	private IFilepathService<FilepathBO> filepathService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======FilepathAction--addFilepath======>");
		
		FilepathBO filepathBO = BaseDto.toModel(FilepathBO.class , param);
		filepathService.insertObject(filepathBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======FilepathAction--updateFilepath======>");
		
		FilepathBO filepathBO = BaseDto.toModel(FilepathBO.class , param);
		filepathService.updateObject(filepathBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======FilepathAction--deleteFilepath======>");
		
		FilepathBO filepathBO = BaseDto.toModel(FilepathBO.class , param);
		filepathService.deleteObject(filepathBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======FilepathAction--findOneFilepath======>");
		
		FilepathBO filepathBO = BaseDto.toModel(FilepathBO.class , param);
		return filepathService.findOne(filepathBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======FilepathAction--findAllFilepath======>");
				
		return filepathService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======FilepathAction--queryFilepathForPage======>");
		
		return filepathService.queryForPage(currentPage);
	}	
}
