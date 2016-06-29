package com.taikang.tkcoww.module.service.impl;

import com.taikang.tkcoww.module.service.IModuleService;
import java.util.List;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import java.util.Arrays;
import com.taikang.tkcoww.module.model.ModuleBO;
import com.taikang.udp.framework.common.datastructre.Dto;
import org.springframework.stereotype.Service;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;

/**
 * ModuleServiceImpl
 */
@Service(IModuleService.SERVICE_ID)
public class ModuleServiceImpl extends BaseServiceImpl implements
		IModuleService<ModuleBO> {

	/**
	 * 增加数据
	 */
	public void insertObject(ModuleBO module) {
		logger.debug("<======ModuleServiceImpl--insertModule======>");
		appDao.insert("Module.addModule", module);
	}

	/**
	 * 修改数据
	 */
	public void updateObject(ModuleBO module) {
		logger.debug("<======ModuleServiceImpl--updateModule======>");
		appDao.update("Module.updateModule", module);
	}

	/**
	 * 删除数据
	 */
	public void deleteObject(ModuleBO module) {
		logger.debug("<======ModuleServiceImpl--deleteModule======>");
		appDao.delete("Module.deleteModule", module);
	}

	/**
	 * 查询数据
	 */
	public ModuleBO findOne(ModuleBO module) {
		logger.debug("<======ModuleServiceImpl--findModule======>");

		ModuleBO moduleBackBO = appDao.queryOneObject("Module.findOneModule",
				module);
		return moduleBackBO;
	}
	
	/**
	 * 查询数据(新)
	 */
	public ModuleBO findNewOne(ModuleBO module) {
		logger.debug("<======ModuleServiceImpl--findNewOne======>");

		ModuleBO moduleBackBO = appDao.queryOneObject("Module.findNewOneModule",
				module);
		return moduleBackBO;
	}

	/**
	 * 查询所有数据
	 */
	public List<ModuleBO> findAll(ModuleBO module) {
		logger.debug("<======ModuleServiceImpl--findAllModule======>");
		return appDao.queryForEntityList("Module.findAllModule", module);
	}

	/**
	 * 分页查询数据
	 */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ModuleServiceImpl--queryModuleForPage======>");
		return appDao.queryForPage("Module.queryModuleForPage", currentPage);
	}

	/**
	 * 查询所有数据 ，重新组装为map
	 */
	public List<Dto> findAllMap(Dto param) {
		logger.debug("<======ModuleServiceImpl--findAllMapModule======>");
		return appDao.queryForMapList("Module.findAllMapModule", param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taikang.tkcoww.module.service.IModuleService#queryNavMenuForPage(
	 * com.taikang.udp.framework.core.persistence.pagination.CurrentPage)
	 */
	@Override
	public CurrentPage queryNavMenuForPage(CurrentPage page) {
		logger.debug("<======ModuleServiceImpl--queryNavMenuForPage======>");
		return appDao.queryForPage("Module.queryNavMenuForPage", page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taikang.tkcoww.module.service.IModuleService#querySubMenu(com.taikang
	 * .udp.framework.core.persistence.pagination.CurrentPage)
	 */
	@Override
	public CurrentPage querySubMenu(CurrentPage page) {
		// TODO Auto-generated method stub
		logger.debug("<======ModuleServiceImpl--querySubMenu======>");
		return appDao.queryForPage("Module.querySubMenu", page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taikang.tkcoww.module.service.IModuleService#updateSubObject(com.
	 * taikang.tkcoww.module.model.ModuleBO)
	 */
	@Override
	public void updateSubObject(ModuleBO moduleBO) {
		// TODO Auto-generated method stub
		logger.debug("<======ModuleServiceImpl--updateSubModule======>");
		appDao.update("Module.updateSubModule", moduleBO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taikang.tkcoww.module.service.IModuleService#queryModulelist(com.
	 * taikang.udp.framework.core.persistence.pagination.CurrentPage)
	 */
	@Override
	public CurrentPage queryModulelist(CurrentPage page) {
		// TODO Auto-generated method stub
		logger.debug("<======ModuleServiceImpl--queryModulelist======>");
		return appDao.queryForPage("Module.queryModulelist", page);
	}
}
