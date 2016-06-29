package com.taikang.udp.manage.controller;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.common.constant.Globals;
import com.taikang.udp.common.modelview.JModelAndView;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.manage.action.IArticleClassAction;
import com.taikang.udp.manage.model.ArticleClassBO;
import com.taikang.udp.sys.action.ISysConfigAction;





@Controller("ArticleClassManageController")
public class ArticleClassManageController {
	
	@Resource(name=ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;
	
	@Resource(name=IArticleClassAction.ACTION_ID)
	private IArticleClassAction articleClassAction;
		
	@RequestMapping("/admin/articleclass_list.htm")
    public ModelAndView list(HttpServletRequest request,
            HttpServletResponse response,CurrentPage page) {
        ModelAndView mv = new JModelAndView("admin/blue/articleclass_list", configAction.getSysConfig(), 0, request, response);
        
      /*  if(articleClass.getCurrentPage() == null) articleClass.setCurrentPage(1);*/
        List<ArticleClassBO> pList = articleClassAction.listRoot();
        mv.addObject("objs",pList);
        return mv;
    }
    
   @RequestMapping("/admin/articleclass_add.htm")
    public ModelAndView add(HttpServletRequest request,
            HttpServletResponse response, String pid) {
        ModelAndView mv = new JModelAndView("admin/blue/articleclass_add",
                configAction.getSysConfig(), 0, request, response);
        List<ArticleClassBO> acs = articleClassAction.listRoot();
        if (pid != null && !pid.equals("")) {
            ArticleClassBO obj = new ArticleClassBO();
            ArticleClassBO bo = new ArticleClassBO();
            
            bo.setId(Long.parseLong(pid));
            ArticleClassBO pac = articleClassAction.findOne(bo);
            obj.setParent(pac);
            mv.addObject("obj", obj);
        }
        mv.addObject("acs", acs);
        return mv;
    }
    
    
    @RequestMapping("/admin/articleclass_save.htm")
    public ModelAndView save(HttpServletRequest request,
            HttpServletResponse response, ArticleClassBO articleClass, String cmd, String list_url, String add_url) {
        articleClass.setMark("news");
        if (articleClass.getId()==null) {
            articleClass.setAddtime(new Date());
            articleClass.setDeletestatus(Globals.STATUS_ACTIVITY);
            if(articleClass.getLevel() == null) articleClass.setLevel(0);
            articleClassAction.insertObject(articleClass);
        }else{
            articleClassAction.updateObject(articleClass); 
        }
        ModelAndView mv = new JModelAndView("admin/blue/success",
        		configAction.getSysConfig(), 0, request, response);
        
        mv.addObject("list_url", list_url);
        mv.addObject("op_title", "保存文章分类成功");
        if (add_url != null) {
            mv.addObject("add_url", add_url);
        }
        return mv;
    }
    
    @RequestMapping("/admin/articleclass_data.htm")
    public ModelAndView articleclass_data(HttpServletRequest request,
            HttpServletResponse response, String pid, String currentPage) {
        ModelAndView mv = new JModelAndView(
                "admin/blue/articleclass_data", configAction
                        .getSysConfig(), 0, request, response);
        ArticleClassBO ac = new ArticleClassBO();
        ac.setId(Long.parseLong(pid));
        articleClassAction.fillChilds(ac);
        mv.addObject("acs", ac.getChilds());
        if(currentPage == null) currentPage = "1";
        mv.addObject("currentPage", currentPage);
        return mv;
    }
    
    @RequestMapping("/admin/articleclass_edit.htm")
    public ModelAndView edit(HttpServletRequest request,
            HttpServletResponse response, String id, String currentPage) {
        ModelAndView mv = new JModelAndView("admin/blue/articleclass_add",
                configAction.getSysConfig(),0, request, response);
        if (id != null && !id.equals("")) {
        	ArticleClassBO articleClass = new ArticleClassBO();
        	articleClass.setId(Long.parseLong(id));
            articleClass = articleClassAction.findOne(articleClass);
            List<ArticleClassBO> acs = articleClassAction.listRoot();
            mv.addObject("acs", acs);
            mv.addObject("obj", articleClass);
            mv.addObject("currentPage", currentPage);
            mv.addObject("edit", true);
        }
        return mv;
    }
    
    @RequestMapping("/admin/articleclass_del.htm")
    public ModelAndView delete(HttpServletRequest request, String mulitId) {
        String[] ids = mulitId.split(",");
        ArticleClassBO bo = new ArticleClassBO();
        for (String id : ids) {
            if (!id.equals("")) {
            	bo.setId(Long.parseLong(id));
                ArticleClassBO articleClass = articleClassAction.findOne(bo);
                Set<Long> idSet = articleClassAction.genericIds(articleClass);
                Iterator<Long> it = idSet.iterator();
                while (it.hasNext()) {
                    Long ac_id = it.next();
                    bo.setId(ac_id);
                    articleClassAction.deleteObject(bo);
                }
            }
        }
        return new ModelAndView("redirect:articleclass_list.htm");
    }
	
}

