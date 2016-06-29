package com.taikang.udp.manage.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.common.constant.Globals;
import com.taikang.udp.common.modelview.JModelAndView;
import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.sys.action.ISysConfigAction;
import com.taikang.udp.sys.model.SysConfigBO;
import com.taikang.udp.sys.util.CommonUtil;
import com.taikang.udp.view.action.INavigationAction;
import com.taikang.udp.view.model.NavigationBO;


@Controller("navigationController")
public class NavigationManageController  extends BaseController  {
		
	@Resource(name=INavigationAction.ACTION_ID)
	private INavigationAction navigationAction;
	
	@Resource(name=ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;
		
	@RequestMapping("/admin/navigation_list.htm")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, CurrentPage page) {
		SysConfigBO sysConfigBO = configAction.getSysConfig();
		ModelAndView mv = new JModelAndView("admin/blue/navigation_list",sysConfigBO,0, request, response);
		page.setParamObject(CommonUtil.getParamAsDto(request));
	    page = navigationAction.queryForPage(page);
        List<Dto> pList = page.getPageItems();     //内容集合
        int pageSize = page.getRows();             //每页数
        int totalRows = page.getTotalRows();       //总行数
        int currentPage = page.getPage();          //当前页数
        int totalPage = page.getTotalPages();
        //必要的分页信息
        mv.addObject("objs", pList);
        mv.addObject("totalPage",totalPage);
        mv.addObject("pageSize",pageSize);
        mv.addObject("rows", totalRows);
        mv.addObject("currentPage",currentPage);
        //表单方式分页
        mv.addObject("gotoPageFormHTML",CommUtil.showPageFormHtml(currentPage,totalPage));
        return mv;
	}
	
	@RequestMapping("/admin/navigation_add.htm")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new JModelAndView("admin/blue/navigation_add",
				configAction.getSysConfig(),0, request, response);
		/*List<ArticleClass> acs = articleClassService.listRoot();*/
		NavigationBO obj = new NavigationBO();
		obj.setDisplay(1);
		obj.setType("diy");
		obj.setNewWin(1);
		obj.setLocation(0);
		mv.addObject("obj", obj);
		/*mv.addObject("currentPage", currentPage);*/
		/*mv.addObject("acs", acs);*/
		return mv;
	}
	
	/**
	 * navigation保存管理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/admin/navigation_save.htm")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response, 
			String cmd, String list_url, String add_url,NavigationBO nav) {
		
		if (nav.getId() == null) {
			nav.setAddtime(new Date());
		} 
		nav.setOriginalUrl(nav.getUrl());
		if (nav.getId() == null) {
			nav.setDeletestatus(Globals.STATUS_ACTIVITY);
			navigationAction.insertObject(nav);
		} else
			navigationAction.updateObject(nav);
		ModelAndView mv = new JModelAndView("admin/blue/success",
				configAction.getSysConfig(),0, request, response);
		mv.addObject("list_url", list_url);
		mv.addObject("op_title", "保存页面导航成功");
		if (add_url != null) {
			mv.addObject("add_url", add_url);
		}
		return mv;
	}
	
	@RequestMapping("/admin/navigation_edit.htm")
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response,NavigationBO nav) {
		ModelAndView mv = new JModelAndView("admin/blue/navigation_add",
				configAction.getSysConfig(),0, request, response);
		if (nav.getId() != null) {
			NavigationBO navigation = navigationAction.findOne(nav);
			/*List<ArticleClassBO> acs = articleClassService.listRoot();*/
			/*mv.addObject("acs", acs);*/
			mv.addObject("obj", navigation);
			/*mv.addObject("currentPage", nav.getCurrentPage());*/
			mv.addObject("edit", true);
		}
		return mv;
	}
	
	@RequestMapping("/admin/navigation_del.htm")
    public ModelAndView delete(HttpServletRequest request, String mulitId) {
		NavigationBO  bo = new NavigationBO();
		String[] ids = mulitId.split(",");
        for (String id : ids) {
            if (!id.equals("")) {
            	bo.setId(Long.parseLong(id));
            	navigationAction.deleteObject(bo);
            }
        }
        return new ModelAndView("redirect:navigation_list.htm");
    }
}

