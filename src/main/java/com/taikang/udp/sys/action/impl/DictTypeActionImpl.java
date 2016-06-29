package com.taikang.udp.sys.action.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.action.impl.BaseActionImpl;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.sys.action.IDictTypeAction;
import com.taikang.udp.sys.model.DictEntryBO;
import com.taikang.udp.sys.model.DictTypeBO;
import com.taikang.udp.sys.service.IDictEntryService;
import com.taikang.udp.sys.service.IDictTypeService;
import com.taikang.udp.sys.util.enums.Status;
import com.taikang.udp.sys.util.vo.Tree;

/**
  * DictTypeAction
  */
  @Service(IDictTypeAction.ACTION_ID)
public class DictTypeActionImpl extends BaseActionImpl 
  implements IDictTypeAction {

    /**
      * 注入service
      */
    @Resource(name=IDictTypeService.SERVICE_ID)
	private IDictTypeService<DictTypeBO> dictTypeService;	
    
    @Resource(name=IDictEntryService.SERVICE_ID)
	private IDictEntryService<DictEntryBO> dictEntryService;	
	
	/**
	  * 增加数据
	  */
	public void insertObject(Dto param) {
		logger.debug("<======DictTypeAction--addDictType======>");
		
		DictTypeBO dictTypeBO = BaseDto.toModel(DictTypeBO.class , param);
		dictTypeService.insertObject(dictTypeBO);
	}
	
	/**
      * 修改数据
      */
	public void updateObject(Dto param){
		logger.debug("<======DictTypeAction--updateDictType======>");
		
		DictTypeBO dictTypeBO = BaseDto.toModel(DictTypeBO.class , param);
		dictTypeService.updateObject(dictTypeBO);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(Dto param) {
		logger.debug("<======DictTypeAction--deleteDictType======>");
		DictTypeBO dictTypeBO = BaseDto.toModel(DictTypeBO.class , param);
		
		DictEntryBO dictEntryBO = new DictEntryBO ();
		dictEntryBO.setDictTypeId(String.valueOf(param.get("dictType")));
		List<DictEntryBO> list = dictEntryService.findAll(dictEntryBO);
		if (list != null && list.size()>=1) {
			throw new TKBizException("err", this.getClass().getName(), "deleteObject",
			        "请先删除该字典类型下的字典实体！", null);
		}
		dictTypeBO.setDictTypeId(String.valueOf(param.get("dictType")));
		dictTypeService.deleteObject(dictTypeBO);
	}
	
	/**
      * 查询单条数据
      */
	public Dto findOne(Dto param) {
		logger.debug("<======DictTypeAction--findOneDictType======>");
		
		DictTypeBO dictTypeBO = BaseDto.toModel(DictTypeBO.class , param);
		return dictTypeService.findOne(dictTypeBO).toDto();//返回的BO对象自动转换成Dto返回
	}  
	
	/**
      * 查询所有数据
      */
	public List<Dto> findAll(Dto param) {
		logger.debug("<======DictTypeAction--findAllDictType======>");
				
		return dictTypeService.findAllMap(param);
	} 
	
		
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage){
		logger.debug("<======DictTypeAction--queryDictTypeForPage======>");
		
		return dictTypeService.queryForPage(currentPage);
	}

	@Override
	public List<Tree> findAllDictType() {
		DictTypeBO param = new DictTypeBO();
		param.setStatus(Status.Normal.getCode());//只查询正常状态的字典类型
		List<DictTypeBO> dictTyleList=dictTypeService.findAll(param);//查询DictType
		List<Tree> childrenList = new ArrayList<Tree>();
		Tree childrenTree;
		for(DictTypeBO dictType:dictTyleList){
			childrenTree=new Tree();
			childrenTree.setId(dictType.getDictTypeId());
			childrenTree.setText(dictType.getDictTypeName());
			childrenList.add(childrenTree);
		}
		return childrenList;
	}

	/**
	 * 分级查询字典类别树信息<br/>
	 * @return   
	 * List<Tree>
	 */
	public List<Tree> findAllDictTypeTree() {
		DictTypeBO param = new DictTypeBO();
		param.setStatus(Status.Normal.getCode());// 只查询正常状态的字典类型
		List<Dto> dictTypeList = dictTypeService.findTopDictMap(param.toDto());
		List<Tree> dictTypeTreeList = new ArrayList<Tree>();
		Tree dictTypeTree;
		for (int i=0; i<dictTypeList.size(); i++) {
			DictTypeBO dictType = (DictTypeBO)dictTypeList.get(i);
			/*DictTypeBO dictTypeBO = (DictTypeBO)dictType;*/
			dictTypeTree = new Tree();
			dictTypeTree.setId(dictType.getDictTypeId());
			dictTypeTree.setText(dictType.getDictTypeName());
			// 获取子类型信息
			dictTypeTree.setChildren(this.getChildDictTreeLst(
			        dictType.getParentId(), "open",
			        dictType.getDictTypeId()));

			dictTypeTreeList.add(dictTypeTree);
		}
		return dictTypeTreeList;
	}
	
	/**
	 * 获取指定字典类型的子类型<br/>
	 * @param comId
	 * @return   
	 * List<Tree>
	 */
	public List<Tree> getChildDictTreeLst(String parentId, String state,
	        String currId) {
		List<Tree> treeLst = new ArrayList<Tree>();
		Tree tree;
		DictTypeBO param = new DictTypeBO();
		param.setParentId(parentId);
		param.setStatus(Status.Normal.getCode());// 只查询正常状态的字典类型
		if (parentId == null || "".equals(parentId)) {
			parentId = currId;
		}
		param.setParentId(parentId);
		List<Dto> dictTypeLst = dictTypeService.queryDictTypeChilds(param
		        .toDto());
		for (int i = 0; i < dictTypeLst.size(); i++) {
			tree = new Tree();
			DictTypeBO dictType = (DictTypeBO) dictTypeLst.get(i);
			String transId = "";
			if (parentId == null || "".equals(parentId)) {
				transId = dictType.getDictTypeId();
			} else {
				transId = parentId + "|" + dictType.getDictTypeId();
			}
			tree.setId(dictType.getDictTypeId());
			tree.setText(dictType.getDictTypeName());
			tree.setState(state);
			List<Tree> childrenLst = this.getChildDictTreeLst(transId, state,
			        dictType.getDictTypeId());
			tree.setChildren(childrenLst);
			// tree.setIconCls("icon-chart_organisation");
			treeLst.add(tree);
		}
		return treeLst;
	}

}
