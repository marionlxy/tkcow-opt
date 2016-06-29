package com.taikang.udp.sys.action.impl;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.ILogAction;
import com.taikang.udp.sys.model.LogBO;
import com.taikang.udp.sys.service.ILogService;

/**
  * LogAction
  */
  @Service(ILogAction.ACTION_ID)
public class LogActionImpl extends BaseActionImpl 
  implements ILogAction {

    /**
      * 注入service
      */
    @Resource(name=ILogService.SERVICE_ID)
	private ILogService<LogBO> logService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======LogAction--addLog======>");
		
		LogBO logBO = BaseDto.toModel(LogBO.class , param);
		logService.insertObject(logBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======LogAction--updateLog======>");
		
		LogBO logBO = BaseDto.toModel(LogBO.class , param);
		logService.updateObject(logBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======LogAction--deleteLog======>");
		
		LogBO logBO = BaseDto.toModel(LogBO.class , param);
		logService.deleteObject(logBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======LogAction--findOneLog======>");
		
		LogBO logBO = BaseDto.toModel(LogBO.class , param);
		return logService.findOne(logBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======LogAction--findAllLog======>");
				
		return logService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======LogAction--queryLogForPage======>");
		
		return logService.queryForPage(currentPage);
	}
	
	/**
	 * 按条件分页查询日志信息<br/>
	 * @param currentPage
	 * @return CurrentPage
	 */
	public CurrentPage queryByconForPage(CurrentPage currentPage) {
		logger.debug("<======LogAction--queryBycomForPage======>");
		
		return logService.queryByconForPage(currentPage);
	}
}
