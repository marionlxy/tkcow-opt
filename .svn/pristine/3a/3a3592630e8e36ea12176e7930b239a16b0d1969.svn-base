package com.taikang.udp.sys.service.impl;
 
import java.util.List;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.ComRelaBO;
import com.taikang.udp.sys.service.IComRelaService;
 
  
/**
  * ComRelaServiceImpl
  */
 @Service(IComRelaService.SERVICE_ID)
 public class ComRelaServiceImpl extends BaseServiceImpl 
 implements IComRelaService<ComRelaBO>  
  {
  	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ComRelaBO comRela) {
		logger.debug("<======ComRelaServiceImpl--insertComRela======>");		
		appDao.insert("ComRela.addComRela",comRela);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ComRelaBO comRela) {
		logger.debug("<======ComRelaServiceImpl--updateComRela======>");
		appDao.update("ComRela.updateComRela",comRela);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ComRelaBO comRela) {
		logger.debug("<======ComRelaServiceImpl--deleteComRela======>");
		appDao.delete("ComRela.deleteComRela",comRela);
	}
	
	/**
      * 查询数据
      */
	public ComRelaBO findOne(ComRelaBO comRela) {
		logger.debug("<======ComRelaServiceImpl--findComRela======>");
		
		ComRelaBO comRelaBackBO=appDao.queryOneObject("ComRela.findOneComRela", comRela);
		return comRelaBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ComRelaBO> findAll(ComRelaBO  comRela) {
		logger.debug("<======ComRelaServiceImpl--findAllComRela======>");
		return appDao.queryForEntityList("ComRela.findAllComRela", comRela);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ComRelaServiceImpl--queryComRelaForPage======>");
		return appDao.queryForPage("ComRela.queryComRelaForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ComRelaServiceImpl--findAllMapComRela======>");		
		return appDao.queryForMapList("ComRela.findAllMapComRela", param);
	}
	
	 /**
	  * 删除数据ByParentId<br/>
	  * @param comRela   
	  * void
	  */
	public void deleteObjectByParentId(ComRelaBO comRela) {
		logger.debug("<======ComRelaServiceImpl--deleteObjectByParentId======>");
		appDao.delete("ComRela.deleteComRelaByParentId",comRela);
	}

	 /**
	  * 删除数据 ByChildId<br/>
	  * @param comRela   
	  * void
	  */
	public void deleteObjectByChildId(ComRelaBO comRela) {
		logger.debug("<======ComRelaServiceImpl--deleteObjectByChildId======>");
		appDao.delete("ComRela.deleteComRelaByChildId",comRela);
	}
	
	/**
	  * 删除数据 ByChildId<br/>
	  * @param comRela   
	  * void
	  */
	public void deleteObjectByParentIdAndChild(ComRelaBO comRela){
		logger.debug("<======ComRelaServiceImpl--deleteObjectByParentIdAndChild======>");
		appDao.delete("ComRela.deleteObjectByParentIdAndChild",comRela);
	}
 }
  