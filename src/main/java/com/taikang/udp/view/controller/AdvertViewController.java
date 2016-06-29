package com.taikang.udp.view.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.common.modelview.JModelAndView;
import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.manage.action.IAdvertAction;
import com.taikang.udp.manage.action.IAdvertPositionAction;
import com.taikang.udp.manage.model.AdvertBO;
import com.taikang.udp.manage.model.AdvertPositionBO;
import com.taikang.udp.sys.action.ISysConfigAction;

@Controller
public class AdvertViewController {
	
	@Resource(name=ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;
	
	@Resource(name=IAdvertPositionAction.ACTION_ID)
	private IAdvertPositionAction advertPositionAction;
	
	@Resource(name=IAdvertAction.ACTION_ID)
	private IAdvertAction advertAction;
	
	/**
     * 广告调用方法
     * 
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/advert_invoke.htm")
    public ModelAndView advert_invoke(HttpServletRequest request,
            HttpServletResponse response, String id) {
        ModelAndView mv = new JModelAndView("advert_invoke",configAction.getSysConfig(),1, request, response);
        if (id != null && !id.equals("")) {
        	AdvertPositionBO bo = new AdvertPositionBO();
        	bo.setId(Long.parseLong(id));
            AdvertPositionBO ap = advertPositionAction.findOne(bo);
            if (ap != null) {
            	AdvertPositionBO _ap = new AdvertPositionBO();
                _ap.setApType(ap.getApType());
                _ap.setApStatus(ap.getApStatus());
                _ap.setApShowType(ap.getApShowType());
                _ap.setApWidth(ap.getApWidth());
                _ap.setApHeight(ap.getApHeight());
                _ap.setId(ap.getId());
                _ap.setAp_acc(ap.getAp_acc());
                advertPositionAction.decorateAdvertPositionBO(_ap);
                if (_ap.getApStatus() == 1) {
                    mv.addObject("obj", _ap);
                } else {
                    mv.addObject("obj", new AdvertPositionBO());
                }  
            }
        }
        return mv;
    }
    
    /**
     * 广告URL跳转方法
     * 
     * @param request
     * @param response
     * @param url
     * @param id
     * @throws IOException 
     */
    @RequestMapping("/advert_redirect.htm")
    public void advert_redirect(HttpServletRequest request,
            HttpServletResponse response, String id) throws IOException {
    	    AdvertBO obj = new AdvertBO();
    	    obj.setId(CommUtil.null2Long(id));
    	    AdvertBO adb = advertAction.findOne(obj);
            if (adb != null) {
            	adb.setAdClickNum(adb.getAdClickNum() + 1);
                this.advertAction.updateObject(adb);
            }
            if (adb != null) {
                String url = adb.getAdUrl();
                response.sendRedirect(url);
            } else {
                response.sendRedirect(WebUtil.getURL(request));
            }
        
    }

}
