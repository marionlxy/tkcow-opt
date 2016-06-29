package com.taikang.udp.sys.service.impl;
 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.model.ComBO;
import com.taikang.udp.sys.model.ComRelaBO;
import com.taikang.udp.sys.service.IComRelaService;
import com.taikang.udp.sys.service.IComService;
import com.taikang.udp.sys.util.sequence.SequenceGenerator;
import com.taikang.udp.sys.util.vo.Tree;
 
  
/**
  * ComServiceImpl
  */
 @Service(IComService.SERVICE_ID)
 public class ComServiceImpl extends BaseServiceImpl 
 implements IComService<ComBO>  
  {
	 
	/**
	 * 注入service
	 */
	@Resource(name = IComRelaService.SERVICE_ID)
	private IComRelaService<ComRelaBO> comRelaService;
 	 	 	
 	/**
	  * 增加数据
	  */
	public void insertObject(ComBO com) {
		logger.debug("<======ComServiceImpl--insertCom======>");		
		appDao.insert("Com.addCom",com);
	} 	
 	
 	/**
      * 修改数据
      */
	public void updateObject(ComBO com) {
		logger.debug("<======ComServiceImpl--updateCom======>");
		appDao.update("Com.updateCom",com);
	}

	 /**
      * 删除数据
      */
	public void deleteObject(ComBO com) {
		logger.debug("<======ComServiceImpl--deleteCom======>");
		ComRelaBO comRela = new ComRelaBO();
		comRela.setParentId(com.getComId());
		comRelaService.deleteObjectByParentId(comRela);
		//comRelaAction.deleteObject(comRela.toDto());
		ComRelaBO comRela1 = new ComRelaBO();
		comRela1.setChildId(com.getComId());
		comRelaService.deleteObjectByChildId(comRela1);
		//comRelaAction.deleteObject(comRela1.toDto());
		appDao.delete("Com.deleteCom",com);
	}
	
	/**
      * 查询数据
      */
	public ComBO findOne(ComBO com) {
		logger.debug("<======ComServiceImpl--findCom======>");
		
		ComBO comBackBO=appDao.queryOneObject("Com.findOneCom", com);
		return comBackBO;		
	}
	
	/**
      * 查询所有数据
      */
	public List<ComBO> findAll(ComBO  com) {
		logger.debug("<======ComServiceImpl--findAllCom======>");
		return appDao.queryForEntityList("Com.findAllCom", com);
	}
	
	
	 /**
      * 分页查询数据
      */
	public CurrentPage queryForPage(CurrentPage currentPage) {
		logger.debug("<======ComServiceImpl--queryComForPage======>");
		return appDao.queryForPage("Com.queryComForPage", currentPage);
	}
		
	/**
      * 查询所有数据 ，重新组装为map
      */
	public List<Dto> findAllMap(Dto param){
		logger.debug("<======ComServiceImpl--findAllMapCom======>");		
		return appDao.queryForMapList("Com.findAllMapCom", param);
	}

	/**
	 * 获取机构树信息<br/>
	 * @return   
	 * List<Tree>
	 */
	public List<Tree> getComInfo() {

		List<Tree> treeLst = new ArrayList<Tree>();
		List<Tree> childrenLst;
		Tree tree;
		ComBO com = new ComBO();
		com.setComLevel(1);
		// 查询所有一级机构信息
		List<ComBO> ComLst = this.findAll(com);

		if (ComLst != null) {
			for (ComBO comrow : ComLst) {
				tree = new Tree();
				tree.setId(String.valueOf(comrow.getComId()));
				tree.setText(comrow.getComCode() + "_" + comrow.getComName());
				//tree.setPid(pid);
				childrenLst = this.getChildrenTreeLst(comrow.getComId(),"open");
				tree.setChildren(childrenLst);
				//tree.setIconCls("icon-chart_organisation");
				treeLst.add(tree);
			}
		}
		return treeLst;
	}
	
	/**
	 * 获取指定机构的子机构信息<br/>
	 * @param comId
	 * @return   
	 * List<Tree>
	 */
	public List<Tree> getChildrenTreeLst(Integer comId, String state) {
		List<Tree> treeLst = new ArrayList<Tree>();
		Tree tree;
		ComBO comBO = new ComBO();
		comBO.setComId(comId);

		List<ComBO> lst = appDao
		        .queryForEntityList("Com.getComsByComId", comBO);
		for (ComBO comRelabo : lst) {
			tree = new Tree();
			tree.setId(String.valueOf(comRelabo.getComId()));
			tree.setText(comRelabo.getComCode() + "_" + comRelabo.getComName());
			if (3 == comRelabo.getComLevel() || 4 == comRelabo.getComLevel()) {
				tree.setState(state);
			}
			// tree.setPid(String.valueOf(comId));
			List<Tree> childrenLst = this.getChildrenTreeLst(comRelabo
			        .getComId(),state);
			tree.setChildren(childrenLst);
			//tree.setIconCls("icon-chart_organisation");
			treeLst.add(tree);
		}

		return treeLst;
	}
	
	 /**
     * 分页查询数据
     */
	public CurrentPage getChildList(CurrentPage currentPage) {
		logger.debug("<======ComServiceImpl--queryComForPage======>");
		return appDao.queryForPage("Com.getChildComs", currentPage);
	}
	
	/**
	 * 保存机构数据<br/>
	 * @param param   
	 * void
	 */
	public void savwObject(Dto param) {
		logger.debug("<======ComServiceImpl--savwObject======>");
		ComBO comBO = BaseDto.toModel(ComBO.class , param);
		ComBO querycom = new ComBO();
		querycom.setComCode(comBO.getComCode());
		List<ComBO> comLst = this.findAll(querycom);
		if (comLst != null && comLst.size() >=1) {
			throw new TKBizException("err", this.getClass().getName(), "insertObject",
			        "该机构编码:" + comBO.getComCode() + "已经存在！", null);
		}
		SequenceGenerator generator = SequenceGenerator.getInstance("Com_Id",20);
		long nextid = generator.nextId();
		comBO.setComId(Integer.valueOf(String.valueOf(nextid)));
		appDao.insert("Com.addCom",comBO);
		
		if (param.getAsString("comParent") != null
		        && !"".equals(param.getAsString("comParent").trim())) {

			ComRelaBO comRela = new ComRelaBO();
			comRela.setParentId(Integer.valueOf(param.getAsString("comParent").trim()));
			comRela.setChildId(Integer.valueOf(String.valueOf(nextid)));
			comRela.setCreateUser(comBO.getCreateUser());
			comRela.setCreateTime(comBO.getCreateTime());
			comRela.setLastModifyUser(comBO.getCreateUser());
			comRela.setLastModifyTime(comBO.getCreateTime());
			comRelaService.insertObject(comRela);
		}
	}

	/**
	 * 获取指定条件取得机构信息<br/>
	 * @param comId
	 * @return   
	 * List<Tree>
	 */
	public CurrentPage getByconlist(CurrentPage currentPage) {
		logger.debug("<======ComServiceImpl--getByconlist======>");
		return appDao.queryForPage("Com.getByconlist", currentPage);
	}
 }
  