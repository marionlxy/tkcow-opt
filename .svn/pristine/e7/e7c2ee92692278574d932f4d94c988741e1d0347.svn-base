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
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.manage.action.IArticleAction;
import com.taikang.udp.manage.action.IArticleClassAction;
import com.taikang.udp.manage.model.ArticleBO;
import com.taikang.udp.manage.model.ArticleClassBO;
import com.taikang.udp.sys.action.ISysConfigAction;
import com.taikang.udp.sys.model.SysConfigBO;

@Controller
public class ArticleManageController extends BaseController{
	
	@Resource(name=ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;
	
	@Resource(name=IArticleAction.ACTION_ID)
	private IArticleAction articleAction;
	
	@Resource(name=IArticleClassAction.ACTION_ID)
	private IArticleClassAction articleClassAction;
	
	@RequestMapping("/admin/article_list.htm")
    public ModelAndView list(HttpServletRequest request,
            HttpServletResponse response, CurrentPage page) {
		SysConfigBO sysconfigBO = configAction.getSysConfig();
        ModelAndView mv = new JModelAndView("admin/blue/article_list",
        		sysconfigBO,0, request, response);
        String url = sysconfigBO.getAddress();
        if (url == null || url.equals("")) {
            url = WebUtil.getURL(request);
        }
        page.setParamObject(getParamAsDto(request));
        page = articleAction.queryForPage(page);
        
        List<Dto> pList = page.getPageItems();   //内容集合
        articleAction.decorateArticleList(pList);
        int totalPage = page.getTotalPages();       //总页数
        int pageSize = page.getRows();              //每页数
        int totalRows = page.getTotalPages();       //总行数
        int currentPage = page.getPage();           //当前页数
        
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
	
	@RequestMapping("/admin/article_edit.htm")
    public ModelAndView edit(HttpServletRequest request,
            HttpServletResponse response, String id, String currentPage) {
        ModelAndView mv = new JModelAndView("admin/blue/article_add",configAction.getSysConfig(),0, request, response);
        if (id != null && !id.equals("")) {
        	ArticleBO bo = new ArticleBO();
        	bo.setId(Long.parseLong(id));
            ArticleBO article = articleAction.findOne(bo);
            List<ArticleClassBO> acs = articleClassAction.listRoot();
            mv.addObject("acs", acs);
            mv.addObject("obj", article);
            mv.addObject("currentPage", currentPage);
            mv.addObject("edit", true);
        }
        return mv;
    }
    
    @RequestMapping("/admin/article_del.htm")
    public ModelAndView delete(HttpServletRequest request, String mulitId) {
        String[] ids = mulitId.split(",");
        for (String id : ids) {
            if (!id.equals("")) {
            	ArticleBO bo = new ArticleBO();
            	bo.setId(Long.parseLong(id));
            	articleAction.deleteObject(bo);
            }
        }
        return new ModelAndView("redirect:article_list.htm");
    }
    
    /**
     * article添加管理
     * 
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/admin/article_add.htm")
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response, String currentPage, String class_id) {
        ModelAndView mv = new JModelAndView("admin/blue/article_add",configAction.getSysConfig(),0, request, response);
        List<ArticleClassBO> acs = articleClassAction.listRoot();
        ArticleBO obj = new ArticleBO();
        obj.setDisplay(1);
        if (class_id != null && !class_id.equals("")){
        	ArticleClassBO bo = new ArticleClassBO();
        	bo.setId(Long.parseLong(class_id));
        	obj.setArticleClass(this.articleClassAction.findOne(bo));
        }
           
        mv.addObject("obj", obj);
        mv.addObject("acs", acs);
       /* mv.addObject("currentPage", currentPage);*/
        return mv;
    }
    
    @RequestMapping("/admin/article_save.htm")
    public ModelAndView save(HttpServletRequest request,HttpServletResponse response,String list_url, String add_url,ArticleBO article) {
        
        if (article.getId() == null) {
            article.setAddtime(new Date());
        } 
        if (article.getId() == null) {
        	article.setDeletestatus(Globals.STATUS_ACTIVITY);
            this.articleAction.insertObject(article);
        } else
            this.articleAction.updateObject(article);
        
        ModelAndView mv = new JModelAndView("admin/blue/success",configAction.getSysConfig(), 0, request, response);
        mv.addObject("list_url", list_url);
        mv.addObject("op_title", "保存文章成功");
        if (add_url != null) {
            mv.addObject("add_url", add_url + "?articleClass_id=" + article.getArticleclassId());
        }
        return mv;
    }
}
