package com.taikang.tkcoww.module.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.taikang.tkcoww.banner.action.IBannerAction;
import com.taikang.tkcoww.banner.model.BannerBO;
import com.taikang.tkcoww.module.action.IModuleAction;
import com.taikang.tkcoww.moduleContent.action.IModuleContentAction;
import com.taikang.tkcoww.moduleDes.action.IModuleDesAction;
import com.taikang.tkcoww.moduleImg.action.IModuleImgAction;
import com.taikang.udp.common.constant.Globals;
import com.taikang.udp.common.constant.UploadVertor;
import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.common.util.FileHelper;
import com.taikang.udp.common.util.ProperHelper;
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.TKStringUtils;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.IMenuAction;
import com.taikang.udp.sys.action.IRoleMenuAction;
import com.taikang.udp.sys.action.IUserRoleAction;
import com.taikang.udp.sys.util.CommonUtil;

/**
 * ModuleController
 * 
 * 
 * 
 */
@Controller("moduleController")
@RequestMapping(value = "/module")
public class ModuleController extends BaseController {
	@Resource(name = IUserRoleAction.ACTION_ID)
	private IUserRoleAction userRoleAction;

	@Resource(name = IModuleAction.ACTION_ID)
	private IModuleAction moduleAction;

	@Resource(name = IModuleDesAction.ACTION_ID)
	private IModuleDesAction moduleDesAction;

	@Resource(name = IModuleContentAction.ACTION_ID)
	private IModuleContentAction moduleContentAction;

	@Resource(name = IBannerAction.ACTION_ID)
	private IBannerAction bannerAction;

	@Resource(name = IModuleImgAction.ACTION_ID)
	private IModuleImgAction moduleImgAction;

	@Resource(name = IMenuAction.ACTION_ID)
	private IMenuAction menuAction;

	@Resource(name = IRoleMenuAction.ACTION_ID)
	private IRoleMenuAction roleMenuAction;

	/*
	 * @Resource(name = IMenuAction.ACTION_ID) private IMenuAction menuAction;
	 */
	// /**
	// * 打开主查询页面
	// * @return 页面地址
	// */
	// @RequestMapping("")
	// public String showModuleIndexPage() {
	// return "module/moduleIndex";
	// }

	/**
	 * 打开主查询页面-bylixiaoyang
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showModuleIndexPage(
			@RequestParam("reqMenuId") String reqMenuId,
			HttpServletRequest request, Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);

		paramAsDto.put("modId", paramAsDto.getAsString("backUp2"));
		Dto findOne = moduleAction.findOne(paramAsDto);
		if (reqMenuId != null && !reqMenuId.equals("")) {
			model.addAttribute("reqMenuId", reqMenuId);
			model.addAttribute("modId", paramAsDto.getAsString("backUp2"));
			model.addAttribute("menuId", paramAsDto.getAsString("reqMenuId"));
			model.addAttribute("parentId", paramAsDto.getAsString("parentId"));
			model.addAttribute("isLeaf", paramAsDto.getAsString("isLeaf"));
			model.addAttribute("status", paramAsDto.getAsString("status"));
			model.addAttribute("menuName", paramAsDto.getAsString("menuName"));
			model.addAttribute("modIsleaf", findOne.getAsString("modIsleaf"));
			model.addAttribute("modName", findOne.getAsString("modName"));
			model.addAttribute("modLevel", findOne.getAsString("modLevel"));
			model.addAttribute("modParentId",
					findOne.getAsString("modParentId"));
		}
		return "module/moduleIndex";
	}

	/**
	 * 打开下级栏目列表页面-bylixiaoyang
	 * 
	 * @return 页面地址
	 */
	@RequestMapping(value = "subList")
	public String showModuleSubPage(
			@RequestParam("reqMenuId") String reqMenuId,
			HttpServletRequest request, Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		paramAsDto.put("modId", paramAsDto.getAsString("backUp2"));
		Dto findOne = moduleAction.findOne(paramAsDto);

		/*
		 * Dto menuOne=null; if(!paramAsDto.getAsString("modId").isEmpty()){ Dto
		 * param = new BaseDto(); param.put("backUp2",
		 * paramAsDto.getAsString("modId")); menuOne =
		 * menuAction.findNewOne(param); }
		 */

		if (reqMenuId != null && !reqMenuId.equals("")) {
			model.addAttribute("reqMenuId", reqMenuId);
			model.addAttribute("modId", paramAsDto.getAsString("backUp2"));
			model.addAttribute("menuId", paramAsDto.getAsString("reqMenuId"));
			model.addAttribute("parentId", paramAsDto.getAsString("parentId"));
			model.addAttribute("isLeaf", paramAsDto.getAsString("isLeaf"));
			model.addAttribute("status", paramAsDto.getAsString("status"));
			model.addAttribute("menuName", paramAsDto.getAsString("menuName"));
			model.addAttribute("modIsleaf", findOne.getAsString("modIsleaf"));
			model.addAttribute("modName", findOne.getAsString("modName"));
			model.addAttribute("modLevel", findOne.getAsString("modLevel"));
			model.addAttribute("modParentId",
					findOne.getAsString("modParentId"));

		}
		return "module/moduleSubList";
	}

	/**
	 * 查询信息列表
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> getModuleList(HttpServletRequest request,
			CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(CommonUtil.getParamAsDto(request));
		CurrentPage currentPage = moduleAction.queryModulelist(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/*
	 * 得到菜单
	 */
	@RequestMapping("/getNavMenuList")
	@ResponseBody
	public Map<String, Object> getNavMenuList(HttpServletRequest request,
			CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		page.setParamObject(paramAsDto);
		CurrentPage currentPage = moduleAction.queryNavMenuForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		return map;
	}

	/*
	 * 得到导航菜单信息列表
	 */
	@RequestMapping("/getSubMenu")
	@ResponseBody
	public Map<String, Object> getSubMenu(HttpServletRequest request,
			CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		page.setParamObject(paramAsDto);
		CurrentPage currentPage = moduleAction.querySubMenu(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		return map;
	}

	/**
	 * 首页设置.内容模板 跳转
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/editFromMain")
	public String showMainPage(String backUp2, Model model) {
		Dto param = new BaseDto();
		if (backUp2 != null && !backUp2.equals("")) {
			model.addAttribute("menuId", backUp2);
			model.addAttribute("modId", backUp2);
			param.put("modId", backUp2);
		}
		Dto module = new BaseDto();
		model.addAttribute("modParentName", "");
		module = moduleAction.findNewOne(param);
		model.addAttribute("modName", module.getAsString("modName"));
		model.addAttribute("modByname", module.getAsString("modByname"));
		model.addAttribute("modSquence", module.getAsString("modSquence"));
		model.addAttribute("modUrl", module.getAsString("modUrl"));
		model.addAttribute("seoDes", module.getAsString("seoDes"));
		model.addAttribute("modSeo", module.getAsString("modSeo"));
		model.addAttribute("modDes", module.getAsString("modDes"));
		model.addAttribute("keyWord", module.getAsString("modkeyWorld"));

		Dto paramBan = new BaseDto();
		paramBan.put("belong_id", backUp2);
		//取得是banner，用循环多余
		List<Dto> bannerList = bannerAction.findAll(paramBan);
		for (Dto b : bannerList) {

			String banImgId = b.getAsString("BAN_IMG_ID");
			System.out.println(banImgId);
			if (banImgId != null && !"".equals(banImgId)) {
				param.put("banImgId", banImgId);
				module.put("modNewBanner", b.getAsString("BAN_IMG_NAME"));
				model.addAttribute("modBannerUrl", b.getAsString("BAN_IMG_URL"));
			}
		}

		Dto paramImg = new BaseDto();
		Dto imgBo = new BaseDto();
		paramImg.put("belongId", backUp2);
		imgBo = moduleImgAction.findOne(paramImg);
		String modImg = imgBo.getAsString("modImgId");
		if (modImg != null && !"".equals(modImg)) {
			param.put("modImgId", modImg);
			module.put("modNewImg", imgBo.getAsString("modImgName"));
			model.addAttribute("modImgUrl", imgBo.getAsString("modImgUrl"));
		}
		param.put("backup2", module.getAsString("modId"));
		Dto menuOne = new BaseDto();
		model.addAttribute("fromMain", true);
		return "moduleDes/moduleDesEdit";
	}

	/**
	 * 打开新增或修改页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/edit")
	public String showModuleEditPage(String modId, String menuParentName,
			String modLevel, String modType, Model model) {
		Dto param = new BaseDto();
		Dto module = new BaseDto();
		Dto bannerBo = new BaseDto();
		Dto imgBo = new BaseDto();
		Dto menuOne = new BaseDto();

		if (modId != null && !modId.equals("")) {
			model.addAttribute("modId", modId);
			param.put("modId", modId);
		}
		String pName=null;
		try {
			 pName =java.net.URLDecoder.decode(menuParentName.replace("\\\\", ""),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//String mName = CommUtil.convert(menuParentName, "UTF-8");
		model.addAttribute("modParentName", pName);
		module = moduleAction.findNewOne(param);
		model.addAttribute("modName", module.getAsString("modName"));
		model.addAttribute("modParentId", module.getAsString("modParentId"));
		model.addAttribute("modByname", module.getAsString("modByname"));
		model.addAttribute("modSquence", module.getAsString("modSquence"));
		model.addAttribute("modUrl", module.getAsString("modUrl"));
		model.addAttribute("seoDes", module.getAsString("seoDes"));
		model.addAttribute("modSeo", module.getAsString("modSeo"));
		model.addAttribute("modDes", module.getAsString("modDes"));
		model.addAttribute("keyWord", module.getAsString("modkeyWorld"));
		//B.MOD_ISDES,B.MOD_IMG,B.MOD_ICO
		model.addAttribute("modIsdes", module.getAsString("modIsdes"));
		model.addAttribute("modImg", module.getAsString("modImg"));
		model.addAttribute("modIco", module.getAsString("modIco"));
		// 查询banner表

		Dto paramBan = new BaseDto();
		paramBan.put("belong_id", modId);
		List<Dto> bannerList = bannerAction.findAll(paramBan);
		for (Dto b : bannerList) {

			String banImgId = b.getAsString("BAN_IMG_ID");
			if (banImgId != null && !"".equals(banImgId)) {
				param.put("banImgId", banImgId);
				module.put("modNewBanner", b.getAsString("BAN_IMG_NAME"));
				model.addAttribute("modBannerUrl", b.getAsString("BAN_IMG_URL"));
			}
		}

		/*
		 * // 查询小图表 String modImg = module.getAsString("modImg"); if (modImg !=
		 * null && !"".equals(modImg)) { param.put("modImgId", modImg); modImgBo
		 * = moduleImgAction.findOne(param); model.addAttribute("modImgUrl",
		 * modImgBo.getAsString("modImgUrl")); }
		 */

		// 查询小图表
		Dto paramImg = new BaseDto();
		paramImg.put("belongId", modId);
		imgBo = moduleImgAction.findOne(paramImg);
		String modImg = imgBo.getAsString("modImgId");
		if (modImg != null && !"".equals(modImg)) {
			param.put("modImgId", modImg);
			module.put("modNewImg", imgBo.getAsString("modImgName"));
			model.addAttribute("modImgUrl", imgBo.getAsString("modImgUrl"));
		}
		// 查询菜单表
		param.put("backup2", module.getAsString("modId"));
		menuOne = menuAction.findNewOne(param);
		if (menuOne != null && !menuOne.isEmpty()) {
			model.addAttribute("menuName", menuOne.getAsString("menuName"));
			model.addAttribute("menuId", menuOne.getAsString("menuId"));
		}
		// 一级栏目 关于我们
		if (modLevel != null && !"".equals(modLevel)
				&& Integer.parseInt(modLevel) == 1
				&& module.getAsString("modType").equals("02")) {
			return "module/moduleEdit";
		} else if (modLevel != null && !"".equals(modLevel)
				&& Integer.parseInt(modLevel) == 1&&module.getAsString("modType").equals("01")) {
			return "module/moduleNewEdit";
			// 栏目介绍
		} else if (module.getAsString("modType").equals("02")) {
			return "module/moduleEdit";
			// 03 内容模板
		} else if (module.getAsString("modType").equals("03")) {
			return "moduleDes/moduleDesEdit";
			// 04 带图列表模板
		} else if (module.getAsString("modType").equals("04")) {
			return "moduleDes/imgListModuleEdit";
			// 05 Logo列表模板
		} else if (module.getAsString("modType").equals("05")) {
			return "module/logoModuleEdit";
			// 06 不带图列表模板
		} else if (module.getAsString("modType").equals("06")) {
			return "moduleDes/withoutImgListModuleEdit";
			// 07 发展历程模板
		} else if (module.getAsString("modType").equals("07")) {
			return "moduleDes/developCourseModuleEdit";
		} else {
			Dto content = moduleContentAction.findNewOne(param);
			model.addAttribute("content", content.get("content"));
			return "module/moduleNewEdit";
		}

	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getOne")
	@ResponseBody
	public Dto getModuleById(@RequestParam("modId") String modId, Model model) {
		Dto param = new BaseDto();
		param.put("modId", modId);

		Dto module = moduleAction.findNewOne(param);
		//查询图标
		module.put("modIco", module.get("modIco"));
		// 查询内容表
		Dto content = moduleContentAction.findNewOne(param);
		module.put("content", content.get("content"));

		// 查询banner表
		Dto paramBan = new BaseDto();
		paramBan.put("belongId", modId);
		Dto bannerBo = bannerAction.findOne(paramBan);
		String banImgId = bannerBo.getAsString("banImgId");
		if (banImgId != null && !"".equals(banImgId)) {
			module.put("banImgId", banImgId);
			module.put("modNewBanner", bannerBo.getAsString("banImgName"));
			module.put("banImgUrl", bannerBo.getAsString("banImgUrl"));
		}
		// 查询小图表
		Dto paramImg = new BaseDto();
		paramImg.put("belongId", modId);
		Dto imgBo = moduleImgAction.findOne(paramImg);
		String modImg = imgBo.getAsString("modImgId");
		if (modImg != null && !"".equals(modImg)) {
			module.put("modImgId", modImg);
			module.put("modNewImg", imgBo.getAsString("modImgName"));
		}
		// 查询菜单表
		param.put("backup2", modId);
		Dto menuOne = menuAction.findNewOne(param);
		if (!menuOne.isEmpty()) {
			module.put("menuId", menuOne.getAsString("menuId"));
		}
		module.put("code", "0");
		return module;
	}

	/**
	 * 打开选项卡页面
	 * 
	 * @return
	 */
	@RequestMapping("add")
	public String addModulePage(HttpServletRequest request, Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		String modId = paramAsDto.getAsString("modId");
		if (modId != null && !modId.equals("")) {
			model.addAllAttributes(paramAsDto);
			String mName = CommUtil.convert(paramAsDto.getAsString("menuName"),
					"UTF-8");
			
			//String modName = CommUtil.decode(paramAsDto.getAsString("modName"), "UTF-8");
			String modName=null;
			try {;
				 modName =java.net.URLDecoder.decode(paramAsDto.getAsString("modName").replaceAll("\\\\", ""),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
			
			model.addAttribute("menuName", mName);
			model.addAttribute("modName", modName);
			model.addAttribute("modId", modId);
		}

		return "module/moduleAdd";
	}

	/**
	 * 新增下级栏目
	 * 
	 * @return
	 */
	@RequestMapping("addSubCol")
	public String addSubColPage(HttpServletRequest request, Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		String modId = paramAsDto.getAsString("modId");
		if (modId != null && !modId.equals("")) {
			String mName=null;
			try {
				mName =java.net.URLDecoder.decode(paramAsDto.getAsString("modName").replaceAll("\\\\", ""),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
//			String mName = CommUtil.convert(paramAsDto.getAsString("modName"),
//					"UTF-8");
			model.addAttribute("modParentName", mName);
			model.addAttribute("modParentId", paramAsDto.getAsString("modId"));
			model.addAttribute("menuType", paramAsDto.getAsString("menuType"));
			model.addAttribute("modIsleaf", paramAsDto.getAsString("modIsleaf"));
			model.addAttribute("modLevel", paramAsDto.getAsString("modLevel"));
			model.addAttribute("menuId", paramAsDto.getAsString("menuId"));
			model.addAttribute("parentId", paramAsDto.getAsString("parentId"));

		}

		return "module/moduleSubCloAdd";
	}

	/**
	 * 新增叶子节点
	 * 
	 * @return
	 */
	@RequestMapping("addLeafCol")
	public String addLeafCol(HttpServletRequest request, Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);

		String menuId = paramAsDto.getAsString("menuId");
		model.addAttribute("modIsleaf", "1");
		if (menuId != null && !menuId.equals("")) {
			model.addAttribute(paramAsDto);
			String mName =null;
			try {
				 mName =java.net.URLDecoder.decode(paramAsDto.getAsString("menuName").replaceAll("\\\\", ""),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  

			model.addAttribute("modParentName", mName);
			model.addAttribute("modParentId", paramAsDto.getAsString("menuId"));
			model.addAttribute("modType", paramAsDto.getAsString("leafType"));
		}
		String modId = paramAsDto.getAsString("modId");
		if (modId != null && !modId.equals("")) {
			model.addAttribute(paramAsDto);
			String modParentName =null;
			try {
				modParentName =java.net.URLDecoder.decode(paramAsDto.getAsString("modName").replaceAll("\\\\", ""),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
			model.addAttribute("modParentName", modParentName);
			model.addAttribute("modParentId", paramAsDto.getAsString("modId"));
			model.addAttribute("modType", paramAsDto.getAsString("leafType"));
		}
		String pagePath = "";
		if (paramAsDto.getAsString("leafType").equals("03")) {
			// 03 内容模板
			pagePath = "moduleDes/moduleDesAdd";
			// 04 带图列表模板
		} else if (paramAsDto.getAsString("leafType").equals("04")) {
			pagePath = "moduleDes/imgListModuleAdd";
		} else if (paramAsDto.getAsString("leafType").equals("05")) {
			// 05 Logo列表模板
			pagePath = "module/logoModelAdd";
		} else if (paramAsDto.getAsString("leafType").equals("06")) {
			//  06 不带图列表模板
			pagePath = "moduleDes/withoutImgListModuleAdd";
		} else if(paramAsDto.getAsString("leafType").equals("07")){
			//07 发展历程模板
			pagePath = "moduleDes/developCourseModuleAdd";
		} else {
			// 默认内容模板
			pagePath = "moduleDes/moduleDesAdd";
		}
		model.addAttribute("modLevel", paramAsDto.getAsString("modLevel"));
		model.addAttribute("leafType", paramAsDto.getAsString("leafType"));
		model.addAttribute("parentId", paramAsDto.getAsString("parentId"));
		model.addAttribute("menuId", paramAsDto.getAsString("menuId"));
		return pagePath;
	}

	/**
	 * 保存下级栏目
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("addFormSub")
	@ResponseBody
	public ResponseEntity<String> addFormSub(HttpServletRequest request,
			Model model) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String> map = new HashMap<String, String>();
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		
		String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
				: FileHelper.buildFolder(request));// 获取tomcat目录
		String smalPath = (String) (ProperHelper.SMALLIMAGE != null ? ProperHelper.SMALLIMAGE
				: FileHelper.buildFolder(request));// 	
		String icopath = (String) (ProperHelper.ICOIMG != null ? ProperHelper.ICOIMG
				: FileHelper.buildFolder(request));// 
		
		String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");
		String bannerFilename = null,saveBannerName=null,bannerUrl=null;
		Map<String, Object> mapban = new HashMap<String,Object>();
		MultipartFile fmodBanner = multiRequest.getFile("modBanner");
	/*//  增加文件验证的方法 20160328  向旭
		if (!CommonUtil.isValidateImgContennt(fmodBanner)){
				map.put(MESSAGE_INFO, "新增失败,请上传正确banner文件！");
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}*/
	
		if (!fmodBanner.isEmpty()) {
			// 保存banner到服务器
			bannerFilename = fmodBanner.getOriginalFilename();
			saveBannerName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(bannerFilename));
			try {
				logger.info("开始上传文件:" + bannerFilename);
				mapban = WebUtil.saveFileToServer(multiRequest, "modBanner",
						banerpath, saveBannerName, extendes);
				bannerUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveBannerName;
			/*	realBannerPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
						new File(realBannerPath, saveBannerName));*/
				// 获得http服务器路径
				/*
				 * bannerUrl = WebUtil.getURL(request) + "" +
				 * UploadVertor.FILE_UPLOAD_BANNER + "/" + saveBannerName;
				 */
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//上传图标
		String imgIconFilename = null,saveimgIconName = null,imgIconUrl = null;
		Map<String, Object> mapImgIcon = new HashMap<String,Object>();
		MultipartFile fImgIcon = multiRequest.getFile("imgIcon");
		if (!fImgIcon.isEmpty()) {
			// 保存图标到服务器
			imgIconFilename = fImgIcon.getOriginalFilename();
			saveimgIconName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(imgIconFilename));
			try {
				logger.info("开始上传文件:" + imgIconFilename);
				mapImgIcon = WebUtil.saveFileToServer(multiRequest, "imgIcon",
						icopath, saveimgIconName, extendes);
				imgIconUrl = icopath.replaceAll("\\\\", "/") + "/"
						+ saveimgIconName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		MultipartFile fmodImg = multiRequest.getFile("modImg");
		String imgFilename = null,saveImgName = null,imgUrl=null;
		Map<String, Object> mapimg = new HashMap<String,Object>();
		if (!fmodImg.isEmpty()) {
			imgFilename = fmodImg.getOriginalFilename();
			// 保存小图片到服务器
			saveImgName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(imgFilename));
			try {
				logger.info("开始上传文件:" + imgFilename);
				mapimg = WebUtil.saveFileToServer(multiRequest, "modImg",
						smalPath, saveImgName, extendes);
				imgUrl = smalPath.replaceAll("\\\\", "/") + "/" + saveImgName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		map.put(RTN_RESULT, "false");
		if (!TKStringUtils.isEmpty(mapban)&& !TKStringUtils.isEmpty(mapban.get("error"))){
		map.put(MESSAGE_INFO, "新增失败！"+ mapban.get("error").toString());
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}else if(!TKStringUtils.isEmpty(mapImgIcon)
			&& !TKStringUtils.isEmpty(mapImgIcon.get("error"))){
		map.put(MESSAGE_INFO, "新增失败！"+ mapImgIcon.get("error").toString());
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}else if(!TKStringUtils.isEmpty(mapimg)&& !TKStringUtils.isEmpty(mapimg.get("error"))){
		map.put(MESSAGE_INFO, "新增失败！"+ mapimg.get("error").toString());
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else {
			// paramAsDto
			String modId = UUID.randomUUID().toString().replace("-", "");
			paramAsDto.put("modId", modId);
			int modSequence = Integer.parseInt(paramAsDto.getAsString("modSquence"));
			paramAsDto.put("modSquence", modSequence);
			int modLevel = Integer.parseInt(paramAsDto.getAsString("modLevel")) + 1;
			paramAsDto.put("modLevel", modLevel);
			int modIsleaf = Integer.parseInt(paramAsDto
					.getAsString("modIsleaf"));
			// 创建时间
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			paramAsDto.put("createdTime", timestamp);
			if (modLevel == 4) {
				paramAsDto.put("modIsleaf", "1");// "1"表示叶子节点
			} else {
				paramAsDto.put("modIsleaf", "0");
			}
			// 事实上，paramDto已有数据
			try {
				paramAsDto.put("createdBy",
						SecurityUserHolder.getCurrentUser() == null ? null
								: String.valueOf(SecurityUserHolder
										.getCurrentUser().getUsername()));
			} catch (Exception e) {
				map.put(MESSAGE_INFO, "新增失败！" + mapimg.get("error"));
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
			}
			paramAsDto.put("version", "1");
			paramAsDto.put("delflag", "0");
			// 保存先保存到到主表
			// 保证url唯一
			String modUrl = paramAsDto.getAsString("modUrl");
			Dto nullDto = new BaseDto();
			// nullDto.put("modUrl", modUrl);
			nullDto.put("mod_url", modUrl);
			nullDto.put("delflag", "0");
			if (!TKStringUtils.isEmpty(modUrl)) {
				List<Dto> lstDto = moduleAction.findAll(nullDto);
				if (lstDto.size() > 0) {
					for (Dto onedto : lstDto) {
						if (onedto.getAsString("MOD_URL").equalsIgnoreCase(
								modUrl)) {
							map.put(MESSAGE_INFO, "新增失败！与["+onedto.getAsString("MOD_NAME")+"]链接名称重复！");
							map.put(RTN_RESULT, "false");
							String json = JSONObject.fromObject(map).toString();
							return new ResponseEntity<String>(json, resHeader,
									HttpStatus.OK);
						}
						;
					}
				}
			}
			moduleAction.insertObject(paramAsDto);
			// 保存到menu
			Dto menuDto = new BaseDto();
			Dto findOne = null;
			int menuId = 0;
			do {
				menuId = Integer.parseInt(CommUtil.randomInt(6));
				menuDto.put("menuId", menuId);
				findOne = menuAction.findOne(menuDto);

			} while (!findOne.isEmpty());
			menuDto.put("parentId", paramAsDto.getAsString("parentId") + "|"
					+ paramAsDto.getAsString("menuId"));
			menuDto.put("isLeaf", paramAsDto.getAsString("modIsleaf"));
			menuDto.put("menuName", paramAsDto.getAsString("modName"));
			if (modLevel == 4) {
				paramAsDto.put("menuUrl", "");// "1"表示叶子节点
			} else {
				menuDto.put("menuUrl", "/module/subList");
			}
			menuDto.put("menuOrder", 99-modSequence);
			menuDto.put("menuStatus", 1);
			menuDto.put("Backup2", modId);
			String loginId = SecurityUserHolder.getCurrentUser() == null ? null
					: String.valueOf(SecurityUserHolder.getCurrentUser()
							.getId());
			paramAsDto.put("creator", loginId);
			menuDto.put("createTime", timestamp);
			menuDto.put("isShow", 1);
			menuAction.insertObject(menuDto);

			// 更新到权限表Dto
			Dto roleUserDto = new BaseDto();
			roleUserDto.put("userId", loginId);
			Dto roleUseOne = userRoleAction.findOne(roleUserDto);
			Dto roleMenuDto = new BaseDto();
			roleMenuDto.put("roleId", roleUseOne.getAsString("roleId"));
			roleMenuDto.put("menuId", menuId);
			roleMenuDto.put("creator", loginId);
			roleMenuDto.put("createTime", timestamp);
			roleMenuAction.insertObject(roleMenuDto);

			String banImgId = null;
			if (!fmodBanner.isEmpty()) {
				banImgId = UUID.randomUUID().toString().replace("-", "");
				paramAsDto.put("modBanner", banImgId);
			}
			paramAsDto.put("modType", "01");

			String modImgId = null;
			if (!fmodImg.isEmpty()) {
				modImgId = UUID.randomUUID().toString().replace("-", "");
				paramAsDto.put("modImg", modImgId);
			}
			// 跟新到Des表
			paramAsDto.put("title", paramAsDto.getAsString("modName"));
			paramAsDto.put("modBanner", bannerUrl);
			paramAsDto.put("modImg", imgUrl);
			paramAsDto.put("modIco", imgIconUrl);
			moduleDesAction.insertObject(paramAsDto);
			
			
		
			//图片组更新到banner
			String contentId = UUID.randomUUID().toString()
						.replace("-", "");
				String banId_new = UUID.randomUUID().toString().replace("-", "");
			String imgList = paramAsDto.getAsString("bannerList");
			String ll = "{info:" + imgList.replaceAll("\\\\", "") + "}";
			JSONObject paramStr = JSONObject.fromObject(ll);
			JSONArray bannerList = paramStr.getJSONArray("info");
			for (int i = 0; i < bannerList.size(); i++) {
				String banImgNum = bannerList.getJSONObject(i).getString(
						"BAN_IMG_NUM");
				// String banImgId =
				// bannerList.getJSONObject(i).getString("BAN_IMG_ID");
				String banImgUrl = bannerList.getJSONObject(i).getString(
						"BAN_IMG_URL");
				// String banId =
				// bannerList.getJSONObject(i).getString("BAN_ID");
				String banImgName = bannerList.getJSONObject(i).getString(
						"BAN_IMG_NAME");
				String banImgRename = bannerList.getJSONObject(i).getString(
						"BAN_IMG_RENAME");
				String banTitle = bannerList.getJSONObject(i).getString(
						"BAN_TITLE");
				String banImgOutside = bannerList.getJSONObject(i).getString(
						"BAN_IMG_OUTSIDE");
				// banner 表
				Dto paramBanner = new BaseDto();
				String banImgIdNew = UUID.randomUUID().toString()
						.replace("-", "");
				paramBanner.put("banTitle", banTitle);// 标题，链接
				paramBanner.put("belongId", contentId);// 所属模块或者内容ID
				paramBanner.put("banId", banId_new);// bannerId
				paramBanner.put("banImgId", banImgIdNew);// 图片Id
				paramBanner.put("banImgName", banImgName);// 图片名
				paramBanner.put("banImgUrl", banImgUrl);// 图片URL
				paramBanner.put("banImgNum", banImgNum);// 权重号 图片序号
				paramBanner.put("banImgDes", "图片组banner图");
				paramBanner.put("banImgOutside", banImgOutside);
				paramBanner.put("banImgRename", banImgRename);// 图片重命名名
				paramBanner.put("delflag", "0");// 删除标记
				paramBanner.put("banType", "1");// 删除标记
				paramBanner.put("createdTime",
						new Timestamp(System.currentTimeMillis()));// 时间
				bannerAction.insertObject(paramBanner);
			}
				//插入内容表	
			if (!bannerList.isEmpty()&&bannerList.size()>0) {
				paramAsDto.put("hasBanner", "0");
			}else{
				paramAsDto.put("hasBanner", "1");
			}
				paramAsDto.put("contentId", contentId);
				paramAsDto.put("modNumber", "999");
				paramAsDto.put("contentImg", "");
				//paramAsDto.put("hasBanner", "1");
				paramAsDto.put("bannerId", banImgId);
				paramAsDto.put("content", paramAsDto.get("editor_name"));
				paramAsDto.put("isDisplay", "1");
				moduleContentAction.insertObject(paramAsDto);

			// 更新到banner表中
			if (!fmodBanner.isEmpty()) {
				paramAsDto.put("banId", banImgId);
				// String banId = UUID.randomUUID().toString().replace("-", "");
				paramAsDto.put("banImgId", banImgId);// 主键
				paramAsDto.put("banImgName", bannerFilename);
				paramAsDto.put("belongId", modId);
				paramAsDto.put("banImgUrl", bannerUrl);
				paramAsDto.put("banType", "1");
				paramAsDto.put("banImgDes", "下级banner图");
				paramAsDto.put("banImgOutside", "");
				paramAsDto.put("banImgNum", "999");
				paramAsDto.put("banImgRename", saveBannerName);
				bannerAction.insertObject(paramAsDto);
			}
			

		
			if (!fmodImg.isEmpty()) {
				// 小图
				paramAsDto.put("modImgId", modImgId);
				paramAsDto.put("modImgName", imgFilename);
				paramAsDto.put("belongId", modId);
				paramAsDto.put("modImgUrl", imgUrl);
				paramAsDto.put("modImgNum", "999");
				paramAsDto.put("modImgRename", saveImgName);
				paramAsDto.put("modImgDes", "下级小图片");
				paramAsDto.put("modImgOutside", "");
				moduleImgAction.insertObject(paramAsDto);
			}
			// 跳转到
			map.put(MESSAGE_INFO, "新增成功！");
			map.put(RTN_RESULT, "true");

			map.put("reqMenuId", paramAsDto.getAsString("menuId"));
			map.put("menuName", paramAsDto.getAsString("menuName"));
			map.put("backUp2", paramAsDto.getAsString("modParentId"));
			// model.addAttribute(MESSAGE_INFO, "新增成功！");
			// model.addAttribute(RTN_RESULT, true);
		}
		logger.info("结束文件上传:" + bannerFilename + "|"+imgIconFilename+"|" + imgFilename);
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 修改下级栏目
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("editFormSub")
	@ResponseBody
	public ResponseEntity<String> editFormSub(HttpServletRequest request,
			Model model) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String> map = new HashMap<String, String>();
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		
		String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
				: FileHelper.buildFolder(request));// 获取tomcat目录
		String smalPath = (String) (ProperHelper.SMALLIMAGE != null ? ProperHelper.SMALLIMAGE
				: FileHelper.buildFolder(request));// 
		String icopath = (String) (ProperHelper.ICOIMG != null ? ProperHelper.ICOIMG
				: FileHelper.buildFolder(request));//

		String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");
		String bannerFilename=null,saveBannerName=null,bannerUrl = null;
		Map<String, Object> mapban = new HashMap<String,Object>();
		MultipartFile fmodBanner = multiRequest.getFile("modNewBanner");
		if (!fmodBanner.isEmpty() && fmodBanner.getSize() > 0) {
			// 保存banner到服务器
			bannerFilename = fmodBanner.getOriginalFilename();
			saveBannerName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(bannerFilename));
			try {
				logger.info("开始上传文件:" + bannerFilename);
				mapban = WebUtil.saveFileToServer(multiRequest, "modNewBanner",
						banerpath, saveBannerName, extendes);
				bannerUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveBannerName;
		/*		realBannerPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
						new File(realBannerPath, saveBannerName));*/
				// 获得http服务器路径
				/*
				 * bannerUrl = WebUtil.getURL(request) + "" +
				 * UploadVertor.FILE_UPLOAD_BANNER + "/" + saveBannerName;
				 */
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			//上传图标
		String imgIconFilename = null,saveimgIconName = null,imgIconUrl = null;;
		Map<String, Object> mapImgIcon = new HashMap<String,Object>();
		MultipartFile fImgIcon = multiRequest.getFile("imgIcon");
		if (!fImgIcon.isEmpty()&&fImgIcon.getSize()>0) {
			// 保存图标到服务器
			imgIconFilename = fImgIcon.getOriginalFilename();
			saveimgIconName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(imgIconFilename));
			try {
				logger.info("开始上传文件:" + imgIconFilename);
				mapImgIcon = WebUtil.saveFileToServer(multiRequest, "imgIcon",
						icopath, saveimgIconName, extendes);
				/*realImgIconPath = request.getSession().getServletContext()
						.getRealPath(icopath);
				FileUtils.copyInputStreamToFile(fImgIcon.getInputStream(),
						new File(realImgIconPath, saveimgIconName));*/
				imgIconUrl = icopath.replaceAll("\\\\", "/") + "/"
						+ saveimgIconName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		MultipartFile fmodImg = multiRequest.getFile("modNewImg");
		String imgFilename = null,saveImgName = null,imgUrl = null;
		Map<String, Object> mapimg = new HashMap<String,Object>();
		if (!fmodImg.isEmpty() && fmodImg.getSize() > 0) {
			imgFilename = fmodImg.getOriginalFilename();
			// 保存小图片到服务器
			saveImgName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(imgFilename));
			try {
				logger.info("开始上传文件:" + imgFilename);
				mapimg = WebUtil.saveFileToServer(multiRequest, "modNewImg",
						smalPath, saveImgName, extendes);
				imgUrl = smalPath.replaceAll("\\\\", "/") + "/" + saveImgName;
				/*realSmalImgPath = request.getSession().getServletContext()
						.getRealPath(smalPath);
				FileUtils.copyInputStreamToFile(fmodImg.getInputStream(),
						new File(realSmalImgPath, saveImgName));
				 * imgUrl = WebUtil.getURL(request) + "" +
				 * UploadVertor.FILE_UPLOAD_SMALIMAGE + "/" + saveImgName;
				 */
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		map.put(RTN_RESULT, "false");
		if (!TKStringUtils.isEmpty(mapban)&& !TKStringUtils.isEmpty(mapban.get("error"))){
		map.put(MESSAGE_INFO, "修改失败！"+ mapban.get("error").toString());
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}else if(!TKStringUtils.isEmpty(mapImgIcon)
			&& !TKStringUtils.isEmpty(mapImgIcon.get("error"))){
		map.put(MESSAGE_INFO, "修改失败！"+ mapImgIcon.get("error").toString());
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}else if(!TKStringUtils.isEmpty(mapimg)&& !TKStringUtils.isEmpty(mapimg.get("error"))){
		map.put(MESSAGE_INFO, "修改失败！"+ mapimg.get("error").toString());
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else {
			String modId = paramAsDto.getAsString("modId");
			paramAsDto.put("modId", modId);
			// 事实上，paramDto已有数据
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			paramAsDto.put("modifiedTime", timestamp);
			try {
				paramAsDto.put(
						"modifiedBy",
						SecurityUserHolder.getCurrentUser() == null ? null
								: String.valueOf(SecurityUserHolder
										.getCurrentUser().getUsername()));
			} catch (Exception e) {
				map.put(MESSAGE_INFO, "修改失败！" + mapimg.get("error"));
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
			}
			paramAsDto
					.put("version",
							paramAsDto.getAsString("version") != null
									&& !"".equals(paramAsDto
											.getAsString("version")) ? Integer
									.parseInt(paramAsDto.getAsString("version")) + 1
									: "");
			// 保证url唯一
			String modUrl = paramAsDto.getAsString("modUrl");
			Dto nullDto = new BaseDto();
			// nullDto.put("modUrl", modUrl);
			nullDto.put("mod_url", modUrl);
			nullDto.put("delflag", "0");
			if (!TKStringUtils.isEmpty(modUrl)) {
				//修改本身的数据略过和修改其他的数据不略过
				List<Dto> lstDto = moduleAction.findAll(nullDto);
				if (lstDto.size() > 0) {
					for (Dto onedto : lstDto) {
						if (!onedto.getAsString("MOD_ID").equalsIgnoreCase(
								modId)) {
							map.put(MESSAGE_INFO, "修改失败！与["+onedto.getAsString("MOD_NAME")+"]链接名称重复！");
							map.put(RTN_RESULT, "false");
							String json = JSONObject.fromObject(map).toString();
							return new ResponseEntity<String>(json, resHeader,
									HttpStatus.OK);
						}
						;
					}
				}
			}
			moduleAction.updateObject(paramAsDto);
			
			paramAsDto.put("title", paramAsDto.getAsString("modName"));
			// 更新到des表,肯定会有一部分数据
			paramAsDto.put("modBanner", bannerUrl);
			paramAsDto.put("modImg", imgUrl);
			paramAsDto.put("modIco", imgIconUrl);
			moduleDesAction.updateObject(paramAsDto);

			// 保存到menu
			Dto menuDto = new BaseDto();
			menuDto.put("menuId", paramAsDto.getAsString("menuId"));
			menuDto.put("menuName", paramAsDto.getAsString("modName"));
			menuDto.put("Backup2", modId);
			String loginId = SecurityUserHolder.getCurrentUser() == null ? null
					: String.valueOf(SecurityUserHolder.getCurrentUser()
							.getId());
			menuDto.put("lastModby", loginId);
			menuDto.put("lastModtime", timestamp);
			menuDto.put("isShow", 1);
			menuAction.updateObject(menuDto);
			

			// 图片组更新到内容表banner
			Dto paramDto = new BaseDto();
			paramDto.put("modId", paramAsDto.getAsString("modId"));
			paramDto.put("delflag", "0");
			Dto contentDto = moduleContentAction.findOne(paramDto);
			String contentId = contentDto.getAsString("contentId");

			String banId_new = UUID.randomUUID().toString().replace("-", "");
			String imgList = paramAsDto.getAsString("bannerList");
			String ll = "{info:" + imgList.replaceAll("\\\\", "") + "}";
			JSONObject paramStr = JSONObject.fromObject(ll);
			JSONArray bannerList = paramStr.getJSONArray("info");
			//
			for (int i = 0; i < bannerList.size(); i++) {
				String banImgNum = bannerList.getJSONObject(i).getString(
						"BAN_IMG_NUM");
				String banImgId = bannerList.getJSONObject(i).getString(
						"BAN_IMG_ID");

				String banImgUrl = bannerList.getJSONObject(i).getString(
						"BAN_IMG_URL");
				// String banId =
				// bannerList.getJSONObject(i).getString("BAN_ID");
				String banImgName = bannerList.getJSONObject(i).getString(
						"BAN_IMG_NAME");
				String banImgRename = bannerList.getJSONObject(i).getString(
						"BAN_IMG_RENAME");
				String banTitle = bannerList.getJSONObject(i).getString(
						"BAN_TITLE");
				String banImgOutside = bannerList.getJSONObject(i).getString(
						"BAN_IMG_OUTSIDE");
				if (!TKStringUtils.isEmpty(banImgId)) {
					// banner 表
					Dto paramBanner = new BaseDto();
					// String banImgIdNew = UUID.randomUUID().toString()
					// .replace("-", "");
					paramBanner.put("banTitle", banTitle);// 标题，链接
					paramBanner.put("belongId", contentId);// 所属模块或者内容ID
					// paramBanner.put("banId", banId_new);// bannerId
					paramBanner.put("banImgId", banImgId);// 图片Id
					paramBanner.put("banImgName", banImgName);// 图片名
					paramBanner.put("banImgUrl", banImgUrl);// 图片URL
					paramBanner.put("banImgNum", banImgNum);// 权重号 图片序号
					paramBanner.put("banImgOutside", banImgOutside);
					paramBanner.put("banImgRename", banImgRename);// 图片重命名名
					paramBanner.put("modifiedTime", timestamp);
					paramBanner.put("delflag", "0");
					paramBanner.put(
							"modifiedBy",
							SecurityUserHolder.getCurrentUser() == null ? null
									: String.valueOf(SecurityUserHolder
											.getCurrentUser().getUsername()));
					bannerAction.updateObject(paramBanner);
				} else {
					// banner 表
					Dto paramBanner = new BaseDto();
					String banImgIdNew = UUID.randomUUID().toString()
							.replace("-", "");
					paramBanner.put("banTitle", banTitle);// 标题，链接
					paramBanner.put("belongId", contentId);// 所属模块或者内容ID
					paramBanner.put("banId", banId_new);// bannerId
					paramBanner.put("banImgId", banImgIdNew);// 图片Id
					paramBanner.put("banImgName", banImgName);// 图片名
					paramBanner.put("banImgUrl", banImgUrl);// 图片URL
					paramBanner.put("banImgNum", banImgNum);// 权重号 图片序号
					paramBanner.put("banImgOutside", banImgOutside);
					paramBanner.put("banImgDes", "图片组banner图");
					paramBanner.put("banImgRename", banImgRename);// 图片重命名名
					paramBanner.put("delflag", "0");// 
					paramBanner.put("createdTime",
							new Timestamp(System.currentTimeMillis()));// 时间
					bannerAction.insertObject(paramBanner);
				}
				
			}
			// 更新到内容表
			Dto conDto = new BaseDto();
			conDto.put("modId", modId);

			Dto contentBo = moduleContentAction.findOne(contentDto);
			if (!bannerList.isEmpty()&&bannerList.size()>0) {
				contentBo.put("hasBanner", "0");
			}else{
				contentBo.put("hasBanner", "1");
			}
			if (!contentBo.isEmpty()) {
					// contentBo.put("content",
					// paramAsDto.getAsString("content"));
					contentBo.put("content", paramAsDto.get("editor_name"));
					contentBo.put("modifiedTime", timestamp);
					contentBo.put(
							"modifiedBy",
							SecurityUserHolder.getCurrentUser() == null ? null
									: String.valueOf(SecurityUserHolder
											.getCurrentUser().getUsername()));
					contentBo
							.put("version",
									contentBo.getAsInteger("version") != null
											&& !"".equals(contentBo
													.getAsInteger("version")) ? contentBo
											.getAsInteger("version") + 1 : "");
					moduleContentAction.updateObject(contentBo);
				} 

			Dto paramBan = new BaseDto();
			paramBan.put("belongId", modId);

			// 更新到banner表中
			if (fmodBanner.getSize() > 0 && !fmodBanner.isEmpty()) {
				// 判断是否有更新
				Dto bannerBo = bannerAction.findOne(paramBan);
				String newBanImgId = bannerBo.getAsString("banImgId");
				//
				if (TKStringUtils.isEmpty(newBanImgId)) {
					// banner图
					String banImgId = UUID.randomUUID().toString()
							.replace("-", "");
					paramAsDto.put("banImgId", banImgId);

					paramAsDto.put("banId", banImgId);
					paramAsDto.put("banImgName", bannerFilename);
					paramAsDto.put("belongId", modId);
					paramAsDto.put("banImgUrl", bannerUrl);
					paramAsDto.put("banImgDes", "下级banner图");
					paramAsDto.put("banImgOutside", "");
					paramAsDto.put("banImgNum", "999");
					paramAsDto.put("banType", "1");
					paramAsDto.put("banImgRename", saveBannerName);
					bannerAction.insertObject(paramAsDto);
				} else {
					paramAsDto.put("banImgId", newBanImgId);
					paramAsDto.put("banImgName", bannerFilename);
					// paramAsDto.put("banImgName", bannerFilename);
					paramAsDto.put("belongId", modId);
					paramAsDto.put("banImgUrl", bannerUrl);
					paramAsDto.put("banImgDes", "下级banner图");
					paramAsDto.put("banImgOutside", "");
					paramAsDto.put("banImgNum", "999");
					paramAsDto.put("banType", "1");
					paramAsDto.put("banImgRename", saveBannerName);
					bannerAction.updateObject(paramAsDto);
				}

			}
			Dto paramImg = new BaseDto();
			paramImg.put("belongId", modId);

			if (fmodImg.getSize() > 0 && !fmodImg.isEmpty()) {
				// 小图
				Dto imgBo = moduleImgAction.findOne(paramImg);
				String newModImg = imgBo.getAsString("modImgId");

				if (TKStringUtils.isEmpty(newModImg)) {
					String modImgId = UUID.randomUUID().toString()
							.replace("-", "");
					paramAsDto.put("modImgId", modImgId);
					paramAsDto.put("modImgName", imgFilename);
					// paramAsDto.put("belongId", modId);
					paramAsDto.put("modImgUrl", imgUrl);
					paramAsDto.put("modImgNum", "999");
					paramAsDto.put("modImgRename", saveImgName);
					paramAsDto.put("modImgDes", "下级小图片");
					paramAsDto.put("modImgOutside", "");
					paramAsDto.put("belongId", modId);
					moduleImgAction.insertObject(paramAsDto);
				} else {
					paramAsDto.put("modImgId", newModImg);
					paramAsDto.put("modImgName", imgFilename);
					paramAsDto.put("modImgUrl", imgUrl);
					paramAsDto.put("modImgNum", "999");
					paramAsDto.put("modImgRename", saveImgName);
					paramAsDto.put("modImgDes", "下级小图片");
					paramAsDto.put("modImgOutside", "");
					paramAsDto.put("belongId", modId);
					moduleImgAction.updateObject(paramAsDto);
				}
			}

			// 跳转到
			map.put(MESSAGE_INFO, "修改成功！");
			map.put(RTN_RESULT, "true");

			map.put("reqMenuId", paramAsDto.getAsString("menuId"));
			map.put("menuName", paramAsDto.getAsString("modParentName"));
			map.put("backUp2", paramAsDto.getAsString("modParentId"));
		}
		logger.info("结束文件上传:" + bannerFilename + "|"+imgIconFilename+"|" + imgFilename);
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	private Map<String, String> saveModuleInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = CommonUtil.getParamAsDto(request);
		if (param.get("rowId") == null || "".equals(param.get("rowId"))) {
			moduleAction.insertObject(param);
			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			moduleAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 判断是否第四级
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/isFourLevel")
	@ResponseBody
	private Map<String, String> isFourMenuLevel(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = CommonUtil.getParamAsDto(request);
		Dto dto = moduleAction.findSubOneModule(param);

		/*
		 * String rtn = dto.getAsString("result");
		 * if(!rtn.equalsIgnoreCase("ok")||StringUtils.isEmpty(rtn)) {
		 * map.put(MESSAGE_INFO, "新增成功！"); }
		 */

		map.putAll(dto);
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 删除一条或多条记录
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/del")
	@ResponseBody
	public Map<String, String> deleteModule(
			@RequestParam("modId") String modId,
			@RequestParam("modLevel") String modLevel,
			@RequestParam("menuId") String menuId,
			@RequestParam("parentId") String parentId) {
		Map<String, String> map = new HashMap<String, String>();
		// 注意顺序
		Dto param = new BaseDto();
		param.put("modId", modId);
		param.put("delflag", "1");
		moduleAction.updateObject(param);
		moduleDesAction.updateObject(param);
		// param.put("belongId", "modId");
		// param.put("backUp2", "modId");
		// param.put("menuStatus", "0");
		//
		if (!modLevel.equals("4")) {

			// 删除属所子节点
			Dto moduleDto = new BaseDto();
			moduleDto.put("mod_parent_id", modId);
			List<Dto> modulefindAll = moduleAction.findAll(moduleDto);
			if (!modulefindAll.isEmpty()) {
				for (Dto dto : modulefindAll) {
					Dto mdto = new BaseDto();
					String subModId = dto.getAsString("MOD_ID");
					mdto.put("modId", subModId);
					mdto.put("delflag", "1");
					moduleAction.updateObject(mdto);
					moduleDesAction.updateObject(param);
				}
			}

			Dto dto2 = new BaseDto();
			dto2.put("parent_id", parentId + "|" + menuId);
			List<Dto> findmenuAll = menuAction.findAll(dto2);

			if (!findmenuAll.isEmpty()) {
				for (Dto dto5 : findmenuAll) {
					String subMenuId = dto5.getAsString("Menu_Id");
					Dto dto4 = new BaseDto();
					dto4.put("menuId", subMenuId);
					dto4.put("menuStatus", "0");
					menuAction.updateObject(dto4);
					// menuAction.deleteObject(dto4);
				}
			}
		}

		Dto dto3 = new BaseDto();
		dto3.put("menuId", menuId);
		dto3.put("menuStatus", "0");
		menuAction.updateObject(dto3);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}

	@RequestMapping("/upload")
	public void editorUpload(HttpServletRequest request,
			HttpServletResponse response) {
		String path = ProperHelper.EDITIMG;
		Dto imgUpload = CommonUtil.generateFile2Server("upload", path, request);
		String code = imgUpload.getAsString("code");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String callback = request.getParameter("CKEditorFuncNum");
		if (code == null || !"0".equals(code)) {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'图片上传失败（图片格式必须为.jpg/.gif/.bmp/.png/.ico/.jpeg 文件）');");
				out.println("</script>");

				out.flush();
				out.close();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		String baseUrl = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + baseUrl;
		String storePath = basePath + "/" + imgUpload.getAsString("storePath");

		try {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + storePath + "',''" + ")");
			out.println("</script>");

			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/weblink")
	public String webLinkPage(HttpServletRequest request) {
		return "module/webLinkIndex";
	}

	@RequestMapping("weblinkAdd")
	public String webLinkAddPage(HttpServletRequest request) {
		return "module/webLinkAdd";
	}

	@RequestMapping("weblinkEdit")
	public String weblinkEdit(HttpServletRequest request, Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		model.addAttribute("dictTypeId", paramAsDto.getAsString("dictTypeId"));
		model.addAttribute("dictId", paramAsDto.getAsString("dictId"));
		return "module/webLinkEdit";
	}
}
