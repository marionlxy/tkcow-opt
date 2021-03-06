package com.taikang.tkcoww.moduleDes.action;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.action.IBaseAction;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;


/**
  * IModuleDesAction
  */

public interface IModuleDesAction extends IBaseAction { 

	final String ACTION_ID = "moduleDesAction";

	String uploadBySpringGrpModuleDes(HttpServletRequest request);

	//不带图内容模板
	CurrentPage getWithoutImgListModuleList(CurrentPage page);
	
	//发展历程内容模板
	CurrentPage getDevelopCourseModuleList(CurrentPage page);
	
	//带图内容列表
	CurrentPage getImgListModuleList(CurrentPage page);
	
	//查询单个modDes
	Dto findOneModDes (Dto param);

	Dto findNewOne(Dto paramDes);
}
