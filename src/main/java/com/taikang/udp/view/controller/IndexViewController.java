package com.taikang.udp.view.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.manage.action.IArticleAction;
import com.taikang.udp.common.modelview.JModelAndView;
import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.view.action.INavigationAction;
import com.taikang.udp.view.model.NavigationBO;
import com.taikang.udp.manage.model.ArticleBO;
import com.taikang.udp.sys.action.ISysConfigAction;
import com.taikang.udp.sys.model.SysConfigBO;

@Controller
public class IndexViewController {
	
	
	@Resource(name=INavigationAction.ACTION_ID)
	private INavigationAction navigationAction;
	
	
	@Resource(name=ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;
	
	@Resource(name=IArticleAction.ACTION_ID)
	private IArticleAction articleAction;
	
	

	/**
	 * 商城首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.htm")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		//基础设置
		SysConfigBO sysConfigBO = (SysConfigBO) configAction.getSysConfig();
		ModelAndView mv = new JModelAndView("index",sysConfigBO,1, request, response);
		
		/*//商品分类
		GoodsClass goodsClass = new GoodsClass();
		goodsClass.setDisplay(true);
		List<GoodsClass> gcs = this.goodsClassService.listRootNode(goodsClass);
		mv.addObject("gcs", gcs); */
		
		//商城新闻
		ArticleBO article = new ArticleBO();
        article.setDisplay(ArticleBO.SHOW);
        article.setClass_mark("news");
        List<ArticleBO> articles = articleAction.findByArticleByClass(article);
        mv.addObject("articles", articles);
      
		mv.addObject("acs", null);
		mv.addObject("store_reommend_goods", null);
		
		mv.addObject("bgs", null);
		mv.addObject("dgs", null);
		return mv;
	}
	
	@RequestMapping("/top.htm")
	public ModelAndView top(HttpServletRequest request,
            HttpServletResponse response) {
	    ModelAndView mv = new JModelAndView("top",(SysConfigBO)configAction.getSysConfig(),
               1, request, response);
        return mv;
	}
	
	@RequestMapping("/head.htm")
    public ModelAndView head(HttpServletRequest request,
            HttpServletResponse response) {
		SysConfigBO sysConfigBO = (SysConfigBO) configAction.getSysConfig();
        ModelAndView mv = new JModelAndView("head",
        		sysConfigBO,1, request, response);
        String type = CommUtil.null2String(request.getAttribute("type"));
        mv.addObject("type", type.equals("") ? "goods" : type);
        return mv;
    }
	
	
	/**
     * 前台公用导航主菜单页面
     * @param request
     * @return
     */
	@RequestMapping("/nav.htm")
    public ModelAndView nav(HttpServletRequest request,
            HttpServletResponse response) {
		SysConfigBO sysConfigBO = (SysConfigBO) configAction.getSysConfig();
        ModelAndView mv = new JModelAndView("nav",sysConfigBO,1, request, response);
        NavigationBO navigation = new NavigationBO();
        navigation.setDisplay(NavigationBO.SHOW);
        navigation.setLocation(NavigationBO.MID);
        List<NavigationBO> navs = navigationAction.findAll(navigation);
        mv.addObject("navs", navs);
        return mv;
    }
	
	/**
     * 前台底部页面菜单导航
     * @param request
     * @return
     */
	@RequestMapping("/footer.htm")
    public ModelAndView footer(HttpServletRequest request,
            HttpServletResponse response) {
		SysConfigBO sysConfigBO = (SysConfigBO) configAction.getSysConfig();
        ModelAndView mv = new JModelAndView("footer",sysConfigBO, 1, request, response);
        NavigationBO navigation = new NavigationBO();
        navigation.setDisplay(NavigationBO.SHOW);
        navigation.setLocation(NavigationBO.BOTTOM);
        List<NavigationBO> navs = navigationAction.findAll(navigation);
        mv.addObject("navs", navs);
        return mv;
    }
	
	/*@RequestMapping("/500.htm")
    public ModelAndView error500(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new JModelAndView("500",
                configService.getSysConfig(),
                null, 1, request, response);
        return mv;
    }
	
	@RequestMapping("/404.htm")
    public ModelAndView error404(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new JModelAndView("404",
                configService.getSysConfig(),
                null, 1, request, response);
        return mv;
    }*/

}
