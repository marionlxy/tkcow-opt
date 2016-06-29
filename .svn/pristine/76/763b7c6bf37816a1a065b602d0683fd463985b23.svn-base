package com.taikang.udp.manage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.accessory.service.IAccessoryService;
import com.taikang.udp.common.modelview.JModelAndView;
import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.manage.action.IAdvertAction;
import com.taikang.udp.manage.action.IAdvertPositionAction;
import com.taikang.udp.manage.model.AdvertBO;
import com.taikang.udp.manage.model.AdvertPositionBO;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.ISysConfigAction;
import com.taikang.udp.sys.model.SysConfigBO;
import com.taikang.udp.sys.util.CommonUtil;

@Controller
public class AdvertManageController extends BaseController{
	
	@Resource(name=ISysConfigAction.ACTION_ID)
	private ISysConfigAction configAction;
	
	
	@Resource(name=IAdvertAction.ACTION_ID)
	private IAdvertAction advertAction;
	
	
	@Resource(name=IAdvertPositionAction.ACTION_ID)
	private IAdvertPositionAction advertPositionAction;
	
	@Resource(name=IAccessoryService.SERVICE_ID)
	private IAccessoryService<AccessoryBO> accessoryService;
	
	/**
     * advert保存管理
     * 
     * @param id
     * @return
	 * @throws IOException 
     */
    @RequestMapping("/admin/advert_save.htm")
    public ModelAndView advert_save(HttpServletRequest request,
            HttpServletResponse response, AdvertBO advert) {
       
        if (advert.getId()== null) {
            //新增
            advert.setAddtime(new Date());
            advert.setAdUserId(SecurityUserHolder.getCurrentUser().getId());
        } else {
            //修改填充附件
        	AdvertBO obj = new AdvertBO();
        	obj.setId(advert.getId());
        	advert = advertAction.findOne(obj);
            advert.setAd_acc(obj.getAd_acc());
        }
        String fileName = "";
        if (advert.getAd_acc() != null) {
            fileName = advert.getAd_acc().getName();
        }
        SysConfigBO sysConfig = configAction.getSysConfig();
        //存储文件到服务器
        String uploadFilePath = sysConfig.getUploadfilepath();
        String saveFilePathName = request.getSession().getServletContext().getRealPath("/")+ uploadFilePath + File.separator + "advert";
        Map<String,Object> map = null ;
    	//  增加文件验证的方法 20160328  向旭
		//System.out.println(CommonUtil.isValidateImgContennt(fmodBanner));
        /*MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			if (!CommonUtil.isValidateImgContennt(multiRequest)){
				map.put(MESSAGE_INFO, "新增失败,请上传正确文件！");
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
			}	*/
        try {
            map = WebUtil.saveFileToServer(request, "acc", saveFilePathName,fileName, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("保存广告出错",e);
        }
           
        //附件图片如果不存在进行新增，如果存在则修改
        AccessoryBO acc = null;
        if (fileName.equals("")) {
            if (map.get("fileName") != "") {
                acc = new AccessoryBO();
                acc.setName(CommUtil.null2String(map.get("fileName")));
                acc.setExt(CommUtil.null2String(map.get("mime")));
                acc.setSize(CommUtil.null2Float(map.get("fileSize")));
                acc.setPath(uploadFilePath + "/advert");
                acc.setWidth(CommUtil.null2Int(map.get("width")));
                acc.setHeight(CommUtil.null2Int(map.get("height")));
                acc.setAddtime(new Date());
                acc.setDeletestatus(0); //初始化保存
                accessoryService.insertObject(acc);
                advert.setAd_acc(acc);
                advert.setAdAccId(acc.getId());
            }
        } else {
            if (map.get("fileName") != "") {
                acc = advert.getAd_acc();
                acc.setName(CommUtil.null2String(map.get("fileName")));
                acc.setExt(CommUtil.null2String(map.get("mime")));
                acc.setSize(CommUtil.null2Float(map.get("fileSize")));
                acc.setPath(uploadFilePath + "/advert");
                acc.setWidth(CommUtil.null2Int(map.get("width")));
                acc.setHeight(CommUtil.null2Int(map.get("height")));
                acc.setAddtime(new Date());
                accessoryService.updateObject(acc);
            }
        }
        if(advert.getAdStatus() == null)
        advert.setAdStatus(1);
        advert.setDeletestatus(0);
        if(advert.getAdClickNum() == null)
        advert.setAdClickNum(0);
        if(advert.getAdGold() == null)
        advert.setAdGold(0);
        if(advert.getAdSlideSequence() == null) advert.setAdSlideSequence(0);
        if (advert.getId() == null) {
        	advertAction.insertObject(advert);
        } else
        	advertAction.updateObject(advert);
        ModelAndView mv = new JModelAndView("admin/blue/success",
                sysConfig,  0, request, response);
       
        mv.addObject("list_url", WebUtil.getURL(request) + "/admin/advert_list.htm");
        mv.addObject("op_title", "保存广告成功");
        mv.addObject("add_url", WebUtil.getURL(request) + "/admin/advert_add.htm");
        return mv;
    }
    
    //删除广告
    @RequestMapping("/admin/advert_del.htm")
    public ModelAndView advert_del(HttpServletRequest request,
            HttpServletResponse response, String mulitId, String currentPage) {
        String[] ids = mulitId.split(",");
        for (String id : ids) {
            if (!id.equals("")) {
            	AdvertBO _ad = new AdvertBO();
            	_ad.setId(Long.parseLong(id));
            	AdvertBO advert  =  advertAction.findOne(_ad);
               /* WebUtil.del_acc(request, advert.getAd_acc());*/
            	//删除广告后,需要根据业务是否需要删除附件
            	advertAction.deleteObject(advert.toDto());
            }
        }
        if(currentPage == null) currentPage = "1";
        return new ModelAndView("redirect:advert_list.htm?currentPage=" + currentPage);
    }
    
    @RequestMapping("/admin/adv_pos_edit.htm")
    public ModelAndView adv_pos_edit(HttpServletRequest request,
            HttpServletResponse response, String id, String currentPage) {
        ModelAndView mv = new JModelAndView("admin/blue/adv_pos_add",
                configAction.getSysConfig(), 0, request, response);
        if (id != null && !id.equals("")) {
        	AdvertPositionBO bo = new AdvertPositionBO();
        	bo.setId(Long.parseLong(id));
            AdvertPositionBO obj = advertPositionAction.findOne(bo);
            mv.addObject("obj", obj);
            mv.addObject("currentPage", currentPage);
            mv.addObject("edit", true);
        }
        return mv;
    }
    
    @RequestMapping("/admin/adv_pos_save.htm")
    public ModelAndView adv_pos_save(HttpServletRequest request,
            HttpServletResponse response, String list_url, String add_url,String currentPage,AdvertPositionBO ap) throws IOException {
    	
        if (ap.getId() == null) {
            ap.setAddtime(new Date());
        } else {
          //修改填充附件
        	AdvertPositionBO obj = new AdvertPositionBO();
        	obj.setId(ap.getId());
            obj = advertPositionAction.findOne(obj);
            ap.setAp_acc(obj.getAp_acc());
            ap.setApAccId(obj.getApAccId());
        }
        
        SysConfigBO sysConfigBO = configAction.getSysConfig();
        //设置路径上传文件
        String uploadFilePath = sysConfigBO.getUploadfilepath();
        String saveFilePathName = request.getSession().getServletContext().getRealPath("/") + uploadFilePath + File.separator + "advert";
        Map<String,Object> map = new HashMap<String,Object>();
        String fileName = "";
        if (ap.getAp_acc() != null) {
            fileName = ap.getAp_acc().getName();
        }
        try {
            map = WebUtil.saveFileToServer(request, "acc", saveFilePathName,fileName, null);
            AccessoryBO acc = null;
            if (fileName.equals("")) {
                if (map.get("fileName") != "") {
                    acc = new AccessoryBO();
                    acc.setName(CommUtil.null2String(map.get("fileName")));
                    acc.setExt(CommUtil.null2String(map.get("mime")));
                    acc.setSize(CommUtil.null2Float(map.get("fileSize")));
                    acc.setPath(uploadFilePath + "/advert");
                    acc.setWidth(CommUtil.null2Int(map.get("width")));
                    acc.setHeight(CommUtil.null2Int(map.get("height")));
                    acc.setAddtime(new Date());
                    acc.setDeletestatus(0);
                    accessoryService.insertObject(acc);
                    ap.setAp_acc(acc);
                    ap.setApAccId(acc.getId());
                }
            } else {
                if (map.get("fileName") != "") {
                    acc = ap.getAp_acc();
                    acc.setName(CommUtil.null2String(map.get("fileName")));
                    acc.setExt(CommUtil.null2String(map.get("mime")));
                    acc.setSize(CommUtil.null2Float(map.get("fileSize")));
                    acc.setPath(uploadFilePath + "/advert");
                    acc.setWidth(CommUtil.null2Int(map.get("width")));
                    acc.setHeight(CommUtil.null2Int(map.get("height")));
                    acc.setAddtime(new Date());
                    accessoryService.updateObject(acc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        
        if (ap.getId() == null) {
        	ap.setDeletestatus(0); //未删除
        	ap.setApSaleType(1); //自定义广告
        	ap.setApUseStatus(0);//空闲状态
        	advertPositionAction.insertObject(ap);
        } else
        	advertPositionAction.updateObject(ap);
        ModelAndView mv = new JModelAndView("admin/blue/success",
                configAction.getSysConfig(), 0, request, response);
        mv.addObject("list_url", list_url);
        mv.addObject("op_title", "保存广告位成功");
        if (add_url != null) {
            mv.addObject("add_url", add_url);
        }
        return mv;
    }
	

	@RequestMapping("/admin/advert_list.htm")
	public ModelAndView advert_list(HttpServletRequest request,
			HttpServletResponse response, CurrentPage page) {
		ModelAndView mv = new JModelAndView("admin/blue/advert_list",configAction.getSysConfig(), 0, request, response);
		
		
	    page.setParamObject(CommonUtil.getParamAsDto(request));
	    page = advertAction.queryForPage(page);
	    
        List<Dto> pList = page.getPageItems();     //内容集合
        advertAction.decorateAdvertList(pList);
        
        
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
        Dto dto = page.getParamObject();
        String ad_title = (String)dto.get("ad_title");
		mv.addObject("ad_title", ad_title);
		return mv;
	}
	
	/**
     * advert添加管理
     * 
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/admin/advert_add.htm")
    public ModelAndView advert_add(HttpServletRequest request,
            HttpServletResponse response, String currentPage) {
        ModelAndView mv = new JModelAndView("admin/blue/advert_add",configAction.getSysConfig(),0, request, response);
        AdvertPositionBO t = new AdvertPositionBO();
        List<Dto> aps  = advertPositionAction.findAll(t.toDto());
        mv.addObject("aps", aps);
        mv.addObject("currentPage", currentPage);
        return mv;
    }
    
    /**
     * advert编辑管理
     * 
     * @param id
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/admin/advert_edit.htm")
    public ModelAndView advert_edit(HttpServletRequest request,
            HttpServletResponse response, String id, String currentPage) {
        ModelAndView mv = new JModelAndView("admin/blue/advert_add",
                configAction.getSysConfig(), 0, request, response);
        if (id != null && !id.equals("")) {
        	AdvertBO  advert = new AdvertBO();
        	advert.setId(Long.parseLong(id));
            advert = advertAction.findOne(advert);
            AdvertPositionBO advertPosition = new AdvertPositionBO();
            List<Dto> aps  = advertPositionAction.findAll(advertPosition.toDto());
            mv.addObject("aps", aps);
            mv.addObject("obj", advert);
            mv.addObject("currentPage", currentPage);
            mv.addObject("edit", true);
        }
        return mv;
    }
    
    @RequestMapping("/admin/adv_pos_list.htm")
    public ModelAndView adv_pos_list(HttpServletRequest request,
            HttpServletResponse response, CurrentPage page) {
        ModelAndView mv = new JModelAndView("admin/blue/adv_pos_list",configAction.getSysConfig(), 0, request, response);
        
        page.setParamObject(CommonUtil.getParamAsDto(request));
	    page = advertPositionAction.queryForPage(page);
        List<Dto> pList = page.getPageItems();   //内容集合
        int totalPage = page.getTotalPages();       //总页数
        int pageSize = page.getRows();              //每页数
        int totalRows = page.getTotalRows();       //总行数
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
    
    //新增广告位
    @RequestMapping("/admin/adv_pos_add.htm")
    public ModelAndView adv_pos_add(HttpServletRequest request,
            HttpServletResponse response, String id) {
        ModelAndView mv = new JModelAndView("admin/blue/adv_pos_add",
                configAction.getSysConfig(),  0, request, response);
        return mv;
    }

}
