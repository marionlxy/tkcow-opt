package com.taikang.tkcoww.moduleDes.controller;

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
import com.taikang.tkcoww.module.action.IModuleAction;
import com.taikang.tkcoww.moduleContent.action.IModuleContentAction;
import com.taikang.tkcoww.moduleDes.action.IModuleDesAction;
import com.taikang.udp.common.constant.Globals;
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
import com.taikang.udp.sys.action.IFileloadAction;
import com.taikang.udp.sys.action.IMenuAction;
import com.taikang.udp.sys.action.IRoleMenuAction;
import com.taikang.udp.sys.action.IUserRoleAction;
import com.taikang.udp.sys.util.CommonUtil;

/**
 * ModuleDesController
 */
@Controller("moduleDesController")
@RequestMapping(value = "/moduleDes")
public class ModuleDesController extends BaseController {

	@Resource(name = IModuleDesAction.ACTION_ID)
	private IModuleDesAction moduleDesAction;
	@Resource(name = IModuleAction.ACTION_ID)
	private IModuleAction moduleAction;
	@Resource(name = IModuleContentAction.ACTION_ID)
	private IModuleContentAction moduleContentAction;
	@Resource(name = IBannerAction.ACTION_ID)
	private IBannerAction bannerAction;
	@Resource(name = IMenuAction.ACTION_ID)
	private IMenuAction menuAction;
	@Resource(name = "fileloadAction")
	private IFileloadAction fileloadAction;
	@Resource(name = IUserRoleAction.ACTION_ID)
	private IUserRoleAction userRoleAction;
	@Resource(name = IRoleMenuAction.ACTION_ID)
	private IRoleMenuAction roleMenuAction;

	/**
	 * 打开 logo列表模板 列表 页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("/logoListModuleIndex")
	public String logoListModuleIndex() {
		return "moduleDes/logoListModuleIndex";
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
				out.println("window.parent.CKEDITOR.tools.callFunction("
						+ callback
						+ ",'',"
						+ "'图片上传失败（图片格式必须为.jpg/.gif/.bmp/.png/.ico/.jpeg 文件）');");
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

	/**
	 * 打开主查询页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showModuleDesIndexPage() {
		return "moduleDes/moduleDesIndex";
	}

	/**
	 * 查询信息列表
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> getModuleDesList(HttpServletRequest request,
			CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(CommonUtil.getParamAsDto(request));
		CurrentPage currentPage = moduleDesAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开 不带图列表模板 列表 页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("/withoutImgListModuleIndex")
	@SuppressWarnings("unchecked")
	public String withoutImgListModuleIndex(HttpServletRequest request,
			Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		paramAsDto.put("modId", paramAsDto.getAsString("backUp2"));
		paramAsDto.put("delflag", "0");
		Dto findOne = moduleAction.findOne(paramAsDto);
		model.addAttribute("modId", paramAsDto.getAsString("backUp2"));
		model.addAttribute("menuId", paramAsDto.getAsString("reqMenuId"));
		model.addAttribute("parentId", paramAsDto.getAsString("parentId"));
		model.addAttribute("isLeaf", paramAsDto.getAsString("isLeaf"));
		model.addAttribute("status", paramAsDto.getAsString("status"));
		model.addAttribute("menuName", paramAsDto.getAsString("menuName"));
		model.addAttribute("modIsleaf", findOne.getAsString("modIsleaf"));
		model.addAttribute("modName", findOne.getAsString("modName"));
		model.addAttribute("modLevel", findOne.getAsString("modLevel"));
		model.addAttribute("modParentId", findOne.getAsString("modParentId"));
		model.addAttribute("reqMenuId", paramAsDto.getAsString("reqMenuId"));

		return "moduleDes/withoutImgListModuleIndex";
	}

	/**
	 * 打开发展历程列表页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/developCourseModuleIndex")
	public String developCourseModuleIndex(HttpServletRequest request,
			Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		paramAsDto.put("modId", paramAsDto.getAsString("backUp2"));
		paramAsDto.put("delflag", "0");
		Dto findOne = moduleAction.findOne(paramAsDto);
		model.addAttribute("modId", paramAsDto.getAsString("backUp2"));
		model.addAttribute("menuId", paramAsDto.getAsString("reqMenuId"));
		model.addAttribute("parentId", paramAsDto.getAsString("parentId"));
		model.addAttribute("isLeaf", paramAsDto.getAsString("isLeaf"));
		model.addAttribute("status", paramAsDto.getAsString("status"));
		model.addAttribute("menuName", paramAsDto.getAsString("menuName"));
		model.addAttribute("modIsleaf", findOne.getAsString("modIsleaf"));
		model.addAttribute("modName", findOne.getAsString("modName"));
		model.addAttribute("modLevel", findOne.getAsString("modLevel"));
		model.addAttribute("modParentId", findOne.getAsString("modParentId"));
		model.addAttribute("reqMenuId", paramAsDto.getAsString("reqMenuId"));

		return "moduleDes/developCourseModuleIndex";
	}

	/**
	 * 查询 不带图列表模板 列表 页面
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/getWithoutImgListModuleList")
	@ResponseBody
	public Map<String, Object> getWithoutImgListModuleList(
			HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(CommonUtil.getParamAsDto(request));
		CurrentPage currentPage = moduleDesAction
				.getWithoutImgListModuleList(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 查询发展历程列表模板页面
	 * 
	 * @return
	 */
	@RequestMapping("/getDevelopCourseModuleList")
	@ResponseBody
	public Map<String, Object> getDevelopCourseModuleList(
			HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		page.setParamObject(CommonUtil.getParamAsDto(request));
		CurrentPage currentPage = moduleDesAction
				.getDevelopCourseModuleList(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		return map;
	}

	/**
	 * 打开新增或修改页面
	 * 
	 * @return
	 */
	@RequestMapping("edit")
	public String showModuleDesEditPage(String rowId, Model model) {

		if (rowId != null && !rowId.equals("")) {
			model.addAttribute("rowId", rowId);
		}

		return "moduleDes/moduleDesEdit";
	}

	/**
	 * 不带图列表模板 修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/withoutImgListModuleEdit")
	public String withoutImgListModuleEdit(String modIdAndContentId, Model model) {

		if (modIdAndContentId != null && !modIdAndContentId.equals("")) {
			String[] str = modIdAndContentId.split(",");

			String modId = str[0];
			String contentId = str[1];

			model.addAttribute("modId", modId);
			model.addAttribute("contentId", contentId);
			model.addAttribute("modIdAndContentId", modIdAndContentId);
		}

		return "moduleDes/withoutImgListModuleEdit";
	}

	/**
	 * 不带图列表元素 修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/withOutImgListElementEdit")
	@SuppressWarnings("unchecked")
	public String withOutImgListElementEdit(HttpServletRequest request,
			Model mode) {
		Dto param = CommonUtil.getParamAsDto(request);
		param.put("delflag", "0");
		mode.addAttribute("modId", param.getAsString("modId"));
		Dto desParam = new BaseDto();
		desParam.put("mod_id", param.getAsString("modId"));
		desParam.put("delflag", "0");
		Dto des = moduleDesAction.findAll(desParam).get(0);
		Dto modParam = new BaseDto();
		modParam.put("modId", param.getAsString("modId"));
		Dto mod = moduleAction.findOne(modParam);
		Dto content = moduleContentAction.findNewOne(modParam);
		mode.addAttribute("modDesObj", des);
		mode.addAttribute("modObj", mod);
		mode.addAttribute("modContentObj", content);
		String modName = null;
		try {
			modName = java.net.URLDecoder.decode(param.getAsString("modName")
					.replaceAll("\\\\", ""), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mode.addAttribute("modName", modName);
		return "moduleDes/withoutImgListElementEdit";
	}

	/**
	 * 新增 不带图列表模板
	 * 
	 * @return
	 */
	@RequestMapping("/withoutImgListModuleAdd")
	public String withoutImgListModuleAdd() {
		return "moduleDes/withoutImgListModuleAdd";
	}

	/**
	 * 新增 不带图列表元素
	 * 
	 * @return
	 */
	@RequestMapping("/withoutImgListElementAdd")
	public String withoutImgListElementAdd(HttpServletRequest request,
			Model mode) {
		Dto param = CommonUtil.getParamAsDto(request);
		mode.addAttribute("parent_modid", param.getAsString("modId"));
		mode.addAttribute("modId", param.getAsString("modId"));
		mode.addAttribute("modLevel", param.getAsString("modLevel"));
		mode.addAttribute("reqMenuId", param.getAsString("reqMenuId"));

		String modName = null;
		try {
			modName = java.net.URLDecoder.decode(param.getAsString("modName")
					.replaceAll("\\\\", ""), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mode.addAttribute("modName", modName);
		return "moduleDes/withoutImgListElementAdd";
	}

	/**
	 * 新增发展历程列表元素
	 * 
	 * @param request
	 * @param mode
	 * @return
	 */
	@RequestMapping("/developCourseElementAdd")
	public String developCourseElementAdd(HttpServletRequest request, Model mode) {
		Dto param = CommonUtil.getParamAsDto(request);
		mode.addAttribute("parent_modid", param.getAsString("modId"));
		mode.addAttribute("modId", param.getAsString("modId"));
		mode.addAttribute("modLevel", param.getAsString("modLevel"));
		mode.addAttribute("reqMenuId", param.getAsString("reqMenuId"));
		String modName = null;
		try {
			modName = java.net.URLDecoder.decode(param.getAsString("modName")
					.replaceAll("\\\\", ""), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mode.addAttribute("modName", modName);
		return "moduleDes/developCourseElementAdd";
	}

	/**
	 * 新删除不带图列表元素
	 * 
	 * @return
	 */
	@RequestMapping("/withoutImgListElementDel")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String withoutImgListElementDel(HttpServletRequest request,
			Model mode) {
		Dto param = CommonUtil.getParamAsDto(request);
		param.put("delflag", "1");
		moduleDesAction.updateObject(param);
		moduleContentAction.updateObject(param);
		moduleAction.updateObject(param);
		Dto result = new BaseDto();
		result.put("code", "0");
		return result.toJson();
	}

	/**
	 * 保存不带图列表元素
	 * 
	 * @return
	 */
	@RequestMapping("/saveWithoutImgListElementInfo")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> saveWithoutImgListElementInfo(
			HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);

		// mod ID
		String modId = UUID.randomUUID().toString().replace("-", "");
		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);
		// 写mod表
		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");
		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (onedto.getAsString("MOD_URL").equalsIgnoreCase(modUrl)) {
						map.put(MESSAGE_INFO,
								"新增失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}
		}
		Dto modParam = new BaseDto();
		modParam.put("createdBy", createBy);
		modParam.put("createdTime", createTime);
		modParam.put("delflag", "0");
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modIsleaf", "1");
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		modParam.put("modParentId", paramDto.getAsString("pmodid"));
		modParam.put("modSquence", paramDto.getAsString("weight"));
		// 增加级别
		int modLevel = Integer.parseInt(paramDto.getAsString("modLevel")) + 1;
		modParam.put("modLevel", modLevel);
		moduleAction.insertObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("createdBy", createBy);
		modDesParam.put("createdTime", createTime);
		modDesParam.put("delflag", "0");
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modType", "08");
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));

		moduleDesAction.insertObject(modDesParam);

		// 写modcontent表
		Dto modContParam = new BaseDto();
		String contentId = UUID.randomUUID().toString().replace("-", "");
		modContParam.put("contentId", contentId);
		modContParam.put("modNumber", "0");
		modContParam.put("hasBanner", "0");
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("isDisplay", "0");
		modContParam.put("createdBy", createBy);
		modContParam.put("createdTime", createTime);
		modContParam.put("weight", paramDto.getAsString("weight"));
		modContParam.put("delflag", "0");
		modContParam.put("modId", modId);
		moduleContentAction.insertObject(modContParam);

		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		map.put("backUp2", paramDto.getAsString("pmodid"));
		map.put("reqMenuId", paramDto.getAsString("reqMenuId"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 保存发展历程列表元素..新增
	 * 
	 * @return
	 */
	@RequestMapping("/saveDevelopCourseElementInfo")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> saveDevelopCourseElementInfo(
			HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);

		// mod ID
		String modId = UUID.randomUUID().toString().replace("-", "");
		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);
		// 写mod表
		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");
		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (onedto.getAsString("MOD_URL").equalsIgnoreCase(modUrl)) {
						map.put(MESSAGE_INFO,
								"新增失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}
		}
		Dto modParam = new BaseDto();
		modParam.put("createdBy", createBy);
		modParam.put("createdTime", createTime);
		modParam.put("delflag", "0");
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modIsleaf", "1");
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		modParam.put("modParentId", paramDto.getAsString("pmodid"));
		modParam.put("modSquence", paramDto.getAsString("weight"));
		// 增加级别
		int modLevel = Integer.parseInt(paramDto.getAsString("modLevel")) + 1;
		modParam.put("modLevel", modLevel);
		moduleAction.insertObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("createdBy", createBy);
		modDesParam.put("createdTime", createTime);
		modDesParam.put("delflag", "0");
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modType", "10");
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));

		moduleDesAction.insertObject(modDesParam);

		// 写modcontent表
		Dto modContParam = new BaseDto();
		String contentId = UUID.randomUUID().toString().replace("-", "");
		modContParam.put("contentId", contentId);
		modContParam.put("modNumber", "0");
		modContParam.put("hasBanner", "0");
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("isDisplay", "0");
		modContParam.put("createdBy", createBy);
		modContParam.put("createdTime", createTime);
		modContParam.put("weight", paramDto.getAsString("weight"));
		modContParam.put("delflag", "0");
		modContParam.put("modId", modId);
		moduleContentAction.insertObject(modContParam);

		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		map.put("backUp2", paramDto.getAsString("pmodid"));
		map.put("reqMenuId", paramDto.getAsString("reqMenuId"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 保存不带图列表元素
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/updataImgListElementInfo")
	public ResponseEntity<String> updataWithoutImgListElementInfo(
			HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);

		// mod ID
		String modId = paramDto.getAsString("modId");
		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);
		// 写mod表
		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");
		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (!onedto.getAsString("MOD_ID").equalsIgnoreCase(modId)) {
						map.put(MESSAGE_INFO,
								"修改失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}

		}

		Dto modParam = new BaseDto();
		modParam.put("modifiedBy", createBy);
		modParam.put("modifiedTime", createTime);
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		modParam.put("modSquence", paramDto.getAsString("weight"));
		moduleAction.updateObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("modifiedBy", createBy);
		modDesParam.put("modifiedTime", createTime);
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));
		moduleDesAction.updateObject(modDesParam);
		// 写modcontent表
		Dto modContParam = new BaseDto();
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("modifiedBy", createBy);
		modContParam.put("modifiedTime", createTime);
		modContParam.put("weight", paramDto.getAsString("weight"));
		modContParam.put("modId", modId);
		moduleContentAction.updateObject(modContParam);

		map.put(MESSAGE_INFO, "更新成功！");
		map.put(RTN_RESULT, "true");

		map.put("backUp2",
				paramDto.getAsString("pmodid") == null ? paramDto
						.getAsString("modParentId") : paramDto
						.getAsString("pmodid"));
		map.put("reqMenuId", paramDto.getAsString("reqMenuId"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 保存发展历程列表元素
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/updataDevelopCourseElementInfo")
	public ResponseEntity<String> updataDevelopCourseElementInfo(
			HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);

		// mod ID
		String modId = paramDto.getAsString("modId");
		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);
		// 写mod表
		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");
		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (!onedto.getAsString("MOD_ID").equalsIgnoreCase(modId)) {
						map.put(MESSAGE_INFO,
								"修改失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}

		}

		Dto modParam = new BaseDto();
		modParam.put("modifiedBy", createBy);
		modParam.put("modifiedTime", createTime);
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		modParam.put("modSquence", paramDto.getAsString("weight"));
		moduleAction.updateObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("modifiedBy", createBy);
		modDesParam.put("modifiedTime", createTime);
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));
		moduleDesAction.updateObject(modDesParam);
		// 写modcontent表
		Dto modContParam = new BaseDto();
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("modifiedBy", createBy);
		modContParam.put("modifiedTime", createTime);
		modContParam.put("weight", paramDto.getAsString("weight"));
		modContParam.put("modId", modId);
		moduleContentAction.updateObject(modContParam);

		map.put(MESSAGE_INFO, "更新成功！");
		map.put(RTN_RESULT, "true");

		map.put("backUp2",
				paramDto.getAsString("pmodid") == null ? paramDto
						.getAsString("modParentId") : paramDto
						.getAsString("pmodid"));
		map.put("reqMenuId", paramDto.getAsString("reqMenuId"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 新增 内容模板
	 * 
	 * @return
	 */
	@RequestMapping("add")
	public String moduleDesAdd() {
		return "moduleDes/moduleDesAdd";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面
	 * 
	 * @return
	 */
	@RequestMapping("/getOne")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Dto getModuleDesById(@RequestParam("modId") String modId, Model model) {
		Dto param = new BaseDto();
		param.put("modId", modId);

		Dto module = moduleAction.findNewOne(param);
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
		}

		// 查询菜单表
		param.put("backup2", modId);
		Dto menuOne = menuAction.findNewOne(param);
		if (!menuOne.isEmpty()) {
			module.put("menuId", menuOne.getAsString("menuId"));
		}

		return module;

	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@SuppressWarnings("unchecked")
	private Map<String, String> saveModuleDesInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = CommonUtil.getParamAsDto(request);
		if (param.get("rowId") == null || "".equals(param.get("rowId"))) {
			String modId = UUID.randomUUID().toString().replace("-", "");
			param.put("modId", modId);
			// 创建时间
			param.put("createdTime", new Timestamp(System.currentTimeMillis()));

			// 将 modId 插入到 zjow_module 表中
			moduleAction.insertObject(param);
			moduleDesAction.insertObject(param);

			map.put("modId", modId);
			map.put("sId", modId);

			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			moduleDesAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 查询 带图列表模板 列表 页面
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/getImgListModuleList")
	@ResponseBody
	public Map<String, Object> getImgListModuleList(HttpServletRequest request,
			CurrentPage page) {
		Dto param = CommonUtil.getParamAsDto(request);
		Map<String, Object> map = new HashMap<String, Object>();
		page.setParamObject(param);
		CurrentPage currentPage = moduleDesAction.getImgListModuleList(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 新增带图列表元素.
	 * 
	 * @return
	 */
	@RequestMapping("/imgListElementAdd")
	public String imgListElementAdd(HttpServletRequest request, Model mode) {
		Dto param = CommonUtil.getParamAsDto(request);
		mode.addAttribute("parent_modid", param.getAsString("modId"));
		mode.addAttribute("modParentId", param.getAsString("modId"));
		mode.addAttribute("reqMenuId", param.getAsString("reqMenuId"));
		mode.addAttribute("modLevel", param.getAsString("modLevel"));

		String modName = null;
		try {
			modName = java.net.URLDecoder.decode(param.getAsString("modName")
					.replaceAll("\\\\", ""), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mode.addAttribute("modName", modName);
		return "moduleDes/imgListElementAdd";
	}

	/**
	 * 编辑带图列表元素.
	 * 
	 * @return
	 */
	@RequestMapping("/imgListElementEdit")
	@SuppressWarnings("unchecked")
	public String imgListElementEdit(HttpServletRequest request, Model mode) {
		Dto param = CommonUtil.getParamAsDto(request);
		param.put("delflag", "0");
		mode.addAttribute("modId", param.getAsString("modId"));
		Dto desParam = new BaseDto();
		desParam.put("mod_id", param.getAsString("modId"));
		desParam.put("delflag", "0");
		Dto des = moduleDesAction.findAll(desParam).get(0);
		Dto modParam = new BaseDto();
		modParam.put("modId", param.getAsString("modId"));
		Dto mod = moduleAction.findOne(modParam);
		Dto content = moduleContentAction.findNewOne(modParam);
		mode.addAttribute("modDesObj", des);
		mode.addAttribute("modObj", mod);
		mode.addAttribute("modContentObj", content);
		String modName = null;
		try {
			modName = java.net.URLDecoder.decode(param.getAsString("modName")
					.replaceAll("\\\\", ""), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mode.addAttribute("modName", modName);
		return "moduleDes/imgListElementEdit";
	}

	/**
	 * 删除带图列表元素.
	 * 
	 * @return
	 */
	@RequestMapping("/imgListElementDelete")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String imgListElementDelete(HttpServletRequest request, Model mode) {
		Dto param = CommonUtil.getParamAsDto(request);
		param.put("delflag", "1");
		moduleDesAction.updateObject(param);
		moduleContentAction.updateObject(param);
		moduleAction.updateObject(param);
		Dto result = new BaseDto();
		result.put("code", "0");
		return result.toJson();
	}

	/**
	 * 图片列表模板 保存
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveImgListElementInfo")
	private ResponseEntity<String> saveImgListElement(
			HttpServletRequest request, HttpServletResponse response) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);

		// 保存图片
		String coverPath = ProperHelper.SMALLIMAGE;
		Dto imgUpload = CommonUtil.generateFile2Server("coverImg", coverPath,
				request);
		if ("1".equals(imgUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		// mod ID
		String modId = UUID.randomUUID().toString().replace("-", "");
		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);

		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");

		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (onedto.getAsString("MOD_URL").equalsIgnoreCase(modUrl)) {
						map.put(MESSAGE_INFO,
								"新增失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}
		}
		// 写mod表
		Dto modParam = new BaseDto();
		modParam.put("createBy", createBy);
		modParam.put("createdTime", createTime);
		modParam.put("delflag", "0");
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modIsleaf", "1");
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		modParam.put("modParentId", paramDto.getAsString("pmodid"));
		modParam.put("modSquence", paramDto.getAsString("weight"));
		int modLevel = Integer.parseInt(paramDto.getAsString("modLevel")) + 1;
		modParam.put("modLevel", modLevel);
		moduleAction.insertObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("createBy", createBy);
		modDesParam.put("createdTime", createTime);
		modDesParam.put("delflag", "0");
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modType", "09");
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));
		modDesParam.put("modImg", imgUpload.getAsString("storePath"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		moduleDesAction.insertObject(modDesParam);

		// 写modcontent表
		Dto modContParam = new BaseDto();
		String contentId = UUID.randomUUID().toString().replace("-", "");
		modContParam.put("contentId", contentId);
		modContParam.put("modNumber", "0");
		modContParam.put("hasBanner", "0");
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("isDisplay", "0");
		modContParam.put("createBy", createBy);
		modContParam.put("createdTime", createTime);
		modContParam.put("delflag", "0");
		modContParam.put("modId", modId);
		moduleContentAction.insertObject(modContParam);

		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		map.put("backUp2", paramDto.getAsString("modParentId"));
		map.put("reqMenuId", paramDto.getAsString("reqMenuId"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 图片列表模板 保存
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveImgListModuleInfo")
	@ResponseBody
	private ResponseEntity<String> saveImgListModule(
			HttpServletRequest request, HttpServletResponse response) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);
		// 保存图片.
		String coverPath = ProperHelper.SMALLIMAGE;
		Dto imgUpload = CommonUtil.generateFile2Server("coverImg", coverPath,
				request);
		if ("1".equals(imgUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		String bannerPath = ProperHelper.BANNERFODLER;
		Dto banUpload = CommonUtil.generateFile2Server("banImgUrl", bannerPath,
				request);
		if ("1".equals(banUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		String editimg = ProperHelper.ICOIMG;
		Dto iconUpload = CommonUtil.generateFile2Server("imgIcon", editimg,
				request);
		if ("1".equals(iconUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		// mod ID
		String modId = UUID.randomUUID().toString().replace("-", "");

		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);

		// banner ID
		String bannerId = UUID.randomUUID().toString().replace("-", "");
		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");
		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (onedto.getAsString("MOD_URL").equalsIgnoreCase(modUrl)) {
						map.put(MESSAGE_INFO,
								"新增失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}
		}
		// 写banner表
		if ("0".equals(banUpload.getAsString("code"))) {
			Dto banParam = new BaseDto();
			banParam.put("createBy", createBy);
			banParam.put("createdTime", createTime);
			banParam.put("banImgId", bannerId);
			banParam.put("banId", bannerId);
			banParam.put("belongId", modId);
			banParam.put("banImgName", banUpload.getAsString("originName"));
			banParam.put("banImgUrl", banUpload.getAsString("storePath"));
			String imgId = UUID.randomUUID().toString().replace("-", "");
			banParam.put("banImgId", imgId);
			banParam.put("banImgNum", 1);
			banParam.put("banType", 1);
			banParam.put("banImgRename", banUpload.getAsString("trueName"));
			banParam.put("delflag", "0");
			bannerAction.insertObject(banParam);
		}

		// 写mod表
		Dto modParam = new BaseDto();
		modParam.put("createBy", createBy);
		modParam.put("createdTime", createTime);
		modParam.put("delflag", "0");
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modIsleaf", "1");
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		int modSquence = Integer.parseInt(paramDto.getAsString("weight"));
		modParam.put("modSquence", modSquence);
		int modLevel = Integer.parseInt(paramDto.getAsString("modLevel")) + 1;
		modParam.put("modLevel", modLevel);
		modParam.put("modParentId", paramDto.getAsString("modParentId"));
		moduleAction.insertObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("createBy", createBy);
		modDesParam.put("createdTime", createTime);
		modDesParam.put("delflag", "0");
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modType", "04");
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));
		if ("0".equals(banUpload.getAsString("code"))) {
			modDesParam.put("modBanner", bannerId);
		}
		modDesParam.put("modImg", imgUpload.getAsString("storePath"));
		modDesParam.put("modIco", iconUpload.getAsString("storePath"));
		moduleDesAction.insertObject(modDesParam);

		// 写modcontent表
		Dto modContParam = new BaseDto();
		String contentId = UUID.randomUUID().toString().replace("-", "");
		modContParam.put("contentId", contentId);
		modContParam.put("modNumber", "0");
		modContParam.put("hasBanner", "1");
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("isDisplay", "0");
		modContParam.put("createBy", createBy);
		modContParam.put("createdTime", createTime);
		modContParam.put("delflag", "0");
		modContParam.put("modId", modId);
		moduleContentAction.insertObject(modContParam);

		// 写menu表
		Dto menuParam = new BaseDto();
		String menuId = CommUtil.randomInt(5);
		menuParam.put("menuId", menuId);
		String parentId = paramDto.getAsString("parentId") + "|"
				+ paramDto.getAsString("menuId");
		menuParam.put("parentId", parentId);
		String url = "/moduleDes/showImgModList";
		menuParam.put("menuUrl", url);
		menuParam.put("isLeaf", "1");
		menuParam.put("menuName", paramDto.getAsString("title"));
		menuParam.put("menuOrder", 99 - modSquence);
		menuParam.put("menuStatus", 1);
		menuParam.put("Backup2", modId);
		menuParam.put("creator", SecurityUserHolder.getCurrentUser().getId());
		menuParam.put("createdTime", createTime);
		menuParam.put("isShow", 1);
		menuAction.insertObject(menuParam);

		Dto roleUserDto = new BaseDto();
		roleUserDto.put("userId", SecurityUserHolder.getCurrentUser().getId());
		roleUserDto.put("delflag", "0");
		Dto roleUseOne = userRoleAction.findOne(roleUserDto);
		Dto roleMenuDto = new BaseDto();
		roleMenuDto.put("roleId", roleUseOne.getAsString("roleId"));
		roleMenuDto.put("menuId", menuId);
		roleMenuDto.put("creator", SecurityUserHolder.getCurrentUser().getId());
		roleMenuDto.put("createdTime", createTime);
		roleMenuAction.insertObject(roleMenuDto);

		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		map.put("reqMenuId", paramDto.getAsString("menuId"));
		map.put("menuName", paramDto.getAsString("modParentName"));
		map.put("backUp2", paramDto.getAsString("modParentId"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 跳转到带图列表页面
	 */
	@RequestMapping("/showImgModList")
	@SuppressWarnings("unchecked")
	private String showImgModList(HttpServletRequest request, Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		paramAsDto.put("modId", paramAsDto.getAsString("backUp2"));
		paramAsDto.put("delflag", "0");
		Dto findOne = moduleAction.findOne(paramAsDto);
		model.addAttribute("modId", paramAsDto.getAsString("backUp2"));
		model.addAttribute("menuId", paramAsDto.getAsString("reqMenuId"));
		model.addAttribute("parentId", paramAsDto.getAsString("parentId"));
		model.addAttribute("isLeaf", paramAsDto.getAsString("isLeaf"));
		model.addAttribute("status", paramAsDto.getAsString("status"));
		model.addAttribute("menuName", paramAsDto.getAsString("menuName"));
		model.addAttribute("modIsleaf", findOne.getAsString("modIsleaf"));
		model.addAttribute("modName", findOne.getAsString("modName"));
		model.addAttribute("modLevel", findOne.getAsString("modLevel"));
		model.addAttribute("modParentId", findOne.getAsString("modParentId"));
		model.addAttribute("reqMenuId", paramAsDto.getAsString("reqMenuId"));
		return "moduleDes/imgListModuleIndex";
	}

	/**
	 * 内容模板··保存
	 * 
	 * @return
	 */
	@RequestMapping("/saveModuleDesInfo")
	@ResponseBody
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ResponseEntity<String> saveContentModule(
			MultipartHttpServletRequest request) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String> map = new HashMap<String, String>();
		String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
				: FileHelper.buildFolder(request));// 获取tomcat目录
		String icopath = (String) (ProperHelper.ICOIMG != null ? ProperHelper.ICOIMG
				: FileHelper.buildFolder(request));// 获取tomcat目录

		String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");
		String bannerFilename = null, saveBannerName = null, bannerUrl = null;
		Map<String, Object> mapban = new HashMap<String, Object>();
		MultipartFile fmodBanner = multiRequest.getFile("modBanner");
		if (!fmodBanner.isEmpty()) {
			/*
			 * // 增加文件验证的方法 20160328 向旭
			 * //System.out.println(CommonUtil.isValidateImgContennt
			 * (fmodBanner)); if
			 * (!CommonUtil.isValidateImgContennt(fmodBanner)){
			 * map.put(MESSAGE_INFO, "新增失败,请上传正确文件！"); String json =
			 * JSONObject.fromObject(map).toString(); return new
			 * ResponseEntity<String>(json, resHeader, HttpStatus.OK); }
			 */
			bannerFilename = fmodBanner.getOriginalFilename();
			saveBannerName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(bannerFilename));
			// 保存banner到服务器
			try {
				logger.info("开始上传文件:" + bannerFilename);
				mapban = WebUtil.saveFileToServer(multiRequest, "modBanner",
						banerpath, saveBannerName, extendes);
				/*
				 * realBannerPath = request.getSession().getServletContext()
				 * .getRealPath(banerpath);
				 * FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
				 * new File(realBannerPath, saveBannerName)); // 获得http服务器路径
				 * bannerUrl = WebUtil.getURL(request) + "" +
				 * UploadVertor.FILE_UPLOAD_BANNER + "/" + saveBannerName;
				 */
				bannerUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveBannerName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 上传图标
		String imgIconFilename = null, saveimgIconName = null, imgIconUrl = null;
		Map<String, Object> mapImgIcon = new HashMap<String, Object>();
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
				/*
				 * realImgIconPath = request.getSession().getServletContext()
				 * .getRealPath(icopath);
				 * FileUtils.copyInputStreamToFile(fImgIcon.getInputStream(),
				 * new File(realImgIconPath, saveimgIconName));
				 */
				imgIconUrl = icopath.replaceAll("\\\\", "/") + "/"
						+ saveimgIconName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 上传封面图
		String imgCoverFilename = null, saveimgCoverName = null, imgCoverUrl = null;
		Map<String, Object> mapImgCover = new HashMap<String, Object>();
		MultipartFile fImgCover = multiRequest.getFile("imgCover");
		if (!fImgCover.isEmpty()) {
			imgCoverFilename = fImgCover.getOriginalFilename();
			saveimgCoverName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(imgCoverFilename));
			// 保存封面图到服务器
			try {
				logger.info("开始上传文件:" + imgCoverFilename);
				mapImgCover = WebUtil.saveFileToServer(multiRequest,
						"imgCover", banerpath, saveimgCoverName, extendes);
				/*
				 * realImgCoverPath = request.getSession().getServletContext()
				 * .getRealPath(banerpath);
				 * FileUtils.copyInputStreamToFile(fImgCover.getInputStream(),
				 * new File(realImgCoverPath, saveimgCoverName));
				 */
				imgCoverUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveimgCoverName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Dto param = CommonUtil.getParamAsDto(request);
		String modId = null;
		map.put(RTN_RESULT, "false");
		if (!TKStringUtils.isEmpty(mapban)
				&& !TKStringUtils.isEmpty(mapban.get("error"))) {
			map.put(MESSAGE_INFO, "新增失败！" + mapban.get("error").toString());
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else if (!TKStringUtils.isEmpty(mapImgIcon)
				&& !TKStringUtils.isEmpty(mapImgIcon.get("error"))) {
			map.put(MESSAGE_INFO, "新增失败！" + mapImgIcon.get("error").toString());
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else if (!TKStringUtils.isEmpty(mapImgCover)
				&& !TKStringUtils.isEmpty(mapImgCover.get("error"))) {
			map.put(MESSAGE_INFO, "新增失败！" + mapImgCover.get("error").toString());
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else {
			modId = UUID.randomUUID().toString().replace("-", "");
			param.put("modId", modId);
			// 将 modId 插入到 zjow_module 表中
			param.put("modName", param.getAsString("title"));
			param.put("modIsleaf", "1");
			int modSequence = Integer.parseInt(param.getAsString("modSquence"));
			param.put("modSquence", modSequence);
			int modLevel = Integer.parseInt(param.getAsString("modLevel")) + 1;
			param.put("modLevel", modLevel);
			// 创建时间
			param.put("createdTime", new Timestamp(System.currentTimeMillis()));
			try {
				param.put(
						"createdBy",
						SecurityUserHolder.getCurrentUser() == null ? null
								: String.valueOf(SecurityUserHolder
										.getCurrentUser().getUsername()));
			} catch (Exception e) {
				map.put(MESSAGE_INFO, "新增失败！" + mapban.get("error"));
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader,
						HttpStatus.OK);
			}
			param.put("version", "1");
			param.put("delflag", "0");

			// 保证url唯一
			String modUrl = param.getAsString("modUrl");
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
							map.put(MESSAGE_INFO,
									"新增失败！与[" + onedto.getAsString("MOD_NAME")
											+ "]链接名称重复！");
							map.put(RTN_RESULT, "false");
							String json = JSONObject.fromObject(map).toString();
							return new ResponseEntity<String>(json, resHeader,
									HttpStatus.OK);
						}
						;
					}
				}
			}
			moduleAction.insertObject(param);
			// 保存到menu
			Dto menuDto = new BaseDto();
			Dto findOne = null;
			int menuId = 0;
			do {
				menuId = Integer.parseInt(CommUtil.randomInt(5));
				menuDto.put("menuId", menuId);
				findOne = menuAction.findOne(menuDto);

			} while (!findOne.isEmpty());
			menuDto.put(
					"parentId",
					param.getAsString("parentId") + "|"
							+ param.getAsString("menuId"));
			menuDto.put("isLeaf", param.getAsString("modIsleaf"));
			menuDto.put("menuName", param.getAsString("modName"));
			menuDto.put("menuOrder", 99 - modSequence);
			menuDto.put("menuStatus", 1);// 代表正常有效
			menuDto.put("Backup2", modId);
			String loginId = SecurityUserHolder.getCurrentUser() == null ? null
					: String.valueOf(SecurityUserHolder.getCurrentUser()
							.getId());
			param.put("creator", loginId);
			menuDto.put("createTime", new Timestamp(System.currentTimeMillis()));
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
			roleMenuDto.put("createTime",
					new Timestamp(System.currentTimeMillis()));
			roleMenuAction.insertObject(roleMenuDto);
			// 更新到Des表
			String banImgId = null;
			if (!fmodBanner.isEmpty()) {
				banImgId = UUID.randomUUID().toString().replace("-", "");
				param.put("modBanner", banImgId);
			}
			param.put("modType", "03");
			param.put("keyWord", param.getAsString("key"));
			param.put("modByname", param.getAsString("modByname"));
			param.put("title", param.getAsString("title"));
			// param.put("modBanner", bannerUrl);
			param.put("modImg", imgCoverUrl);
			param.put("modIco", imgIconUrl);
			moduleDesAction.insertObject(param);

			// banner图更新表中
			if (!fmodBanner.isEmpty()) {
				param.put("banId", banImgId);
				// String banId = UUID.randomUUID().toString().replace("-", "");
				param.put("banImgId", banImgId);// 主键
				param.put("banImgName", bannerFilename);
				param.put("belongId", modId);
				param.put("banImgUrl", bannerUrl);
				param.put("banType", 1);
				param.put("banImgDes", "banner图");
				param.put("banImgOutside", "");
				param.put("banImgNum", "999");
				param.put("banImgRename", saveBannerName);
				bannerAction.insertObject(param);
			}
			// 图片组更新到内容表和banner
			String contentId = UUID.randomUUID().toString().replace("-", "");
			String banId_new = UUID.randomUUID().toString().replace("-", "");
			String imgList = param.getAsString("bannerList");
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

			// 更新到内容表，有无内容都更新
			param.put("contentId", contentId);
			param.put("modNumber", "999");
			param.put("contentImg", "");
			// banner判断

			if (!bannerList.isEmpty() && bannerList.size() > 0) {
				param.put("hasBanner", "0");
			} else {
				param.put("hasBanner", "1");
			}

			param.put("bannerId", banImgId);
			param.put("content", param.get("editor_name"));
			param.put("isDisplay", "1");
			param.put("weight", param.getAsString("weight"));
			moduleContentAction.insertObject(param);
		}

		map.put("reqMenuId", param.getAsString("menuId"));
		String mName = param.getAsString("modParentName");
		map.put("menuName", mName);
		map.put("backUp2", param.getAsString("modParentId"));

		// 根据 图片ID update banner表
		map.put("modId", modId);
		map.put("sId", modId);
		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");
		logger.info("结束文件上传:" + bannerFilename + "|"+imgIconFilename+"|" + imgCoverFilename);
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 内容模板··编辑
	 * 
	 * @return
	 */
	@RequestMapping("/updateModuleDesInfo")
	@ResponseBody
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ResponseEntity<String> updateContentModule(
			MultipartHttpServletRequest request) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String> map = new HashMap<String, String>();
		String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
				: FileHelper.buildFolder(request));// 获取tomcat目录
		CommUtil.createFolder(banerpath);
		String icopath = (String) (ProperHelper.ICOIMG != null ? ProperHelper.ICOIMG
				: FileHelper.buildFolder(request));// 获取tomcat目录
		CommUtil.createFolder(icopath);
		String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");
		
		String bannerFilename = null,saveBannerName = null,bannerUrl = null;
		Map<String, Object> mapban = new HashMap<String,Object>();
		MultipartFile fmodBanner = multiRequest.getFile("modBanner");
/*		if (!CommonUtil.isValidateImgContennt(fmodBanner)) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确封面文件！");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}*/
		if (!fmodBanner.isEmpty() && fmodBanner.getSize() > 0) {
			bannerFilename = fmodBanner.getOriginalFilename();
			saveBannerName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(bannerFilename));
			// 保存banner到服务器
			try {
				logger.info("开始上传文件:" + bannerFilename);
				mapban = WebUtil.saveFileToServer(multiRequest, "modBanner",
						banerpath, saveBannerName, extendes);
				/*realBannerPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
						new File(realBannerPath, saveBannerName));
				// 获得http服务器路径
				
				 * bannerUrl = WebUtil.getURL(request) + "" +
				 * UploadVertor.FILE_UPLOAD_BANNER + "/" + saveBannerName;
				 */
				bannerUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveBannerName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 上传图标
		String imgIconFilename = null,saveimgIconName = null,imgIconUrl = null;
		Map<String, Object> mapImgIcon = new HashMap<String,Object>();
		//String realImgIconPath = null;
		MultipartFile fImgIcon = multiRequest.getFile("imgIcon");
		if (!fImgIcon.isEmpty() && fImgIcon.getSize() > 0) {
			imgIconFilename = fImgIcon.getOriginalFilename();
			saveimgIconName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(imgIconFilename));
			// 保存图标到服务器
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
		// 上传封面图
		String imgCoverFilename = null,saveimgCoverName = null,imgCoverUrl = null;
		Map<String, Object> mapImgCover = new HashMap<String,Object>();
		MultipartFile fImgCover = multiRequest.getFile("imgCover");
		if (!fImgCover.isEmpty()&&fImgCover.getSize()>0) {
			// 保存封面图到服务器
			imgCoverFilename = fImgCover.getOriginalFilename();
			saveimgCoverName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(imgCoverFilename));
			try {
				logger.info("开始上传文件:" + imgCoverFilename);
				mapImgCover = WebUtil.saveFileToServer(multiRequest,
						"imgCover", banerpath, saveimgCoverName, extendes);
				/*realImgCoverPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fImgCover.getInputStream(),
						new File(realImgCoverPath, saveimgCoverName));*/
				imgCoverUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveimgCoverName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Dto param = CommonUtil.getParamAsDto(request);
		map.put(RTN_RESULT, "false");
		String modId = param.getAsString("modId");
		if (!TKStringUtils.isEmpty(mapban)
				&& !TKStringUtils.isEmpty(mapban.get("error"))) {
			map.put(MESSAGE_INFO, "修改失败！" + mapban.get("error").toString());
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else if (!TKStringUtils.isEmpty(mapImgIcon)
				&& !TKStringUtils.isEmpty(mapImgIcon.get("error"))) {
			map.put(MESSAGE_INFO, "修改失败！" + mapImgIcon.get("error").toString());
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else if (!TKStringUtils.isEmpty(mapImgCover)
				&& !TKStringUtils.isEmpty(mapImgCover.get("error"))) {
			map.put(MESSAGE_INFO, "修改失败！" + mapImgCover.get("error").toString());
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}else {
			// modId = UUID.randomUUID().toString().replace("-", "");
			param.put("modId", param.getAsString("modId"));
			// 将 modId 插入到 zjow_module 表中
			param.put("modName", param.getAsString("title"));
			param.put("modSquence", param.getAsString("modSquence"));

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			param.put("modifiedTime", timestamp);
			try {
				param.put(
						"modifiedBy",
						SecurityUserHolder.getCurrentUser() == null ? null
								: String.valueOf(SecurityUserHolder
										.getCurrentUser().getUsername()));
			} catch (Exception e) {
				map.put(MESSAGE_INFO, "修改失败！" + mapban.get("error"));
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader,
						HttpStatus.OK);
			}
			// 保证url唯一
			String modUrl = param.getAsString("modUrl");
			Dto nullDto = new BaseDto();
			// nullDto.put("modUrl", modUrl);
			nullDto.put("mod_url", modUrl);
			nullDto.put("delflag", "0");
			if (!TKStringUtils.isEmpty(modUrl)) {
				List<Dto> lstDto = moduleAction.findAll(nullDto);
				// 修改本身的数据略过和修改其他的数据不略过
				if (lstDto.size() > 0) {
					for (Dto onedto : lstDto) {
						if (!onedto.getAsString("MOD_ID").equalsIgnoreCase(
								modId)) {
							map.put(MESSAGE_INFO,
									"修改失败！与[" + onedto.getAsString("MOD_NAME")
											+ "]链接名称重复！");
							map.put(RTN_RESULT, "false");
							String json = JSONObject.fromObject(map).toString();
							return new ResponseEntity<String>(json, resHeader,
									HttpStatus.OK);
						}
						;
					}
				}
			}
			moduleAction.updateObject(param);

			// 保存到menu
			Dto menuDto = new BaseDto();
			menuDto.put("menuId", param.getAsString("menuId"));
			menuDto.put("menuName", param.getAsString("modName"));
			menuDto.put("Backup2", modId);
			String loginId = SecurityUserHolder.getCurrentUser() == null ? null
					: String.valueOf(SecurityUserHolder.getCurrentUser()
							.getId());
			menuDto.put("lastModby", loginId);
			menuDto.put("lastModtime", timestamp);
			menuAction.updateObject(menuDto);
			// 更新到Des表
			// String banImgId = null;
			// if (!fmodBanner.isEmpty()) {
			// banImgId = UUID.randomUUID().toString().replace("-", "");
			// param.put("modBanner", banImgId);
			// }
			// param.put("modType", "03");
			param.put("keyWord", param.getAsString("key"));
			param.put("modByname", param.getAsString("modByname"));
			param.put("title", param.getAsString("title"));
			param.put("modImg", imgCoverUrl);
			param.put("modIco", imgIconUrl);
			moduleDesAction.updateObject(param);

			String imgList = param.getAsString("bannerList");

			// banner图更新表中
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
					param.put("banImgId", banImgId);

					param.put("banId", banImgId);
					param.put("banImgName", bannerFilename);
					param.put("belongId", modId);
					param.put("banImgUrl", bannerUrl);
					param.put("banImgDes", "banner图");
					param.put("banImgOutside", "");
					param.put("banImgNum", "999");
					param.put("banType", "1");
					param.put("banImgRename", saveBannerName);
					param.put("delflag", "0");
					bannerAction.insertObject(param);
				} else {
					param.put("banImgId", newBanImgId);
					param.put("banImgName", bannerFilename);
					// param.put("banImgName", bannerFilename);
					param.put("belongId", modId);
					param.put("banImgUrl", bannerUrl);
					param.put("banImgDes", "banner图");
					param.put("banImgOutside", "");
					param.put("banImgNum", "999");
					param.put("banImgRename", saveBannerName);
					param.put("delflag", "0");
					bannerAction.updateObject(param);
				}

			}
			// 图片组更新到内容表banner
			Dto paramDto = new BaseDto();
			paramDto.put("modId", param.getAsString("modId"));
			paramDto.put("delflag", "0");
			Dto contentDto = moduleContentAction.findOne(paramDto);
			String contentId = contentDto.getAsString("contentId");

			String banId_new = UUID.randomUUID().toString().replace("-", "");

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

			// 更新到内容表，有无内容都更新
			// String contentId = UUID.randomUUID().toString().replace("-", "");
			// param.put("contentId", contentId);
			// param.put("modNumber", "999");
			// param.put("contentImg", "");
			// param.put("hasBanner", "1");
			// param.put("bannerId", banImgId);
			param.put("content", param.get("editor_name"));
			// param.put("isDisplay", "1");
			// param.put("weight", param.getAsString("weight"));
			if (!bannerList.isEmpty() && bannerList.size() > 0) {
				param.put("hasBanner", "0");
			} else {
				param.put("hasBanner", "1");
			}
			moduleContentAction.updateObject(param);
		}

		map.put("reqMenuId", param.getAsString("menuId"));
		map.put("menuName", param.getAsString("modParentName"));
		map.put("backUp2", param.getAsString("modParentId"));

		// 根据 图片ID update banner表
		// map.put("modId", modId);
		// map.put("sId", modId);
		map.put(MESSAGE_INFO, "修改成功！");
		map.put(RTN_RESULT, "true");
		logger.info("结束文件上传:" + bannerFilename + "|"+imgIconFilename+"|" + imgCoverFilename);
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 发展历程模板 ··修改 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateDevelopCourseModule")
	@ResponseBody
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ResponseEntity<String> updateDevelopCourseModule(
			HttpServletRequest request) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String> map = new HashMap<String, String>();
		String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
				: FileHelper.buildFolder(request));// 获取tomcat目录
		CommUtil.createFolder(banerpath);
		String icopath = (String) (ProperHelper.ICOIMG != null ? ProperHelper.ICOIMG
				: FileHelper.buildFolder(request));// 获取tomcat目录
		CommUtil.createFolder(icopath);
		String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");
		String bannerFilename = null;
		String saveBannerName = null;
		Map<String, Object> mapban = null;
		String realBannerPath = null;
		MultipartFile fmodBanner = multiRequest.getFile("modBanner");
		String bannerUrl = null;
		if (!fmodBanner.isEmpty() && fmodBanner.getSize() > 0) {
			// 保存banner到服务器
			try {
				bannerFilename = fmodBanner.getOriginalFilename();
				// String getFileName2 =
				// FileHelper.getFileName(multiRequest.getFile("modBanner").getName());
				logger.info("开始上传文件:" + bannerFilename);

				saveBannerName = CommonUtil.generateFileName(FilenameUtils
						.getExtension(bannerFilename));
				mapban = WebUtil.saveFileToServer(multiRequest, "modBanner",
						banerpath, saveBannerName, extendes);
				realBannerPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
						new File(realBannerPath, saveBannerName));
				// 获得http服务器路径
				/*
				 * bannerUrl = WebUtil.getURL(request) + "" +
				 * UploadVertor.FILE_UPLOAD_BANNER + "/" + saveBannerName;
				 */
				bannerUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveBannerName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 上传图标
		String imgIconFilename = null;
		String saveimgIconName = null;
		Map<String, Object> mapImgIcon = null;
		String realImgIconPath = null;
		MultipartFile fImgIcon = multiRequest.getFile("imgIcon");
		String imgIconUrl = null;
		if (!fImgIcon.isEmpty()) {
			// 保存图标到服务器
			try {
				imgIconFilename = fImgIcon.getOriginalFilename();
				logger.info("开始上传文件:" + imgIconFilename);

				saveimgIconName = CommonUtil.generateFileName(FilenameUtils
						.getExtension(imgIconFilename));
				mapImgIcon = WebUtil.saveFileToServer(multiRequest, "imgIcon",
						icopath, saveimgIconName, extendes);
				realImgIconPath = request.getSession().getServletContext()
						.getRealPath(icopath);
				FileUtils.copyInputStreamToFile(fImgIcon.getInputStream(),
						new File(realImgIconPath, saveimgIconName));
				imgIconUrl = icopath.replaceAll("\\\\", "/") + "/"
						+ saveimgIconName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 上传封面图
		String imgCoverFilename = null;
		String saveimgCoverName = null;
		Map<String, Object> mapImgCover = null;
		String realImgCoverPath = null;
		MultipartFile fImgCover = multiRequest.getFile("imgCover");
		String imgCoverUrl = null;
		if (!fImgCover.isEmpty()) {
			// 保存封面图到服务器
			try {
				imgCoverFilename = fImgCover.getOriginalFilename();
				logger.info("开始上传文件:" + imgCoverFilename);

				saveimgCoverName = CommonUtil.generateFileName(FilenameUtils
						.getExtension(imgCoverFilename));
				mapImgCover = WebUtil.saveFileToServer(multiRequest,
						"imgCover", banerpath, saveimgCoverName, extendes);
				realImgCoverPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fImgCover.getInputStream(),
						new File(realImgCoverPath, saveimgCoverName));
				imgCoverUrl = banerpath.replaceAll("\\\\", "/") + "/"
						+ saveimgCoverName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Dto param = CommonUtil.getParamAsDto(request);
		map.put(RTN_RESULT, "true");
		String modId = param.getAsString("modId");
		if ((!TKStringUtils.isEmpty(mapban) && !((List) mapban.get("error"))
				.isEmpty())
				|| (!TKStringUtils.isEmpty(mapImgIcon) && !((List) mapImgIcon
						.get("error")).isEmpty())
				|| ((!TKStringUtils.isEmpty(mapImgCover) && !((List) mapImgCover
						.get("error")).isEmpty()))) {
			map.put(MESSAGE_INFO, "新增失败！" + mapban.get("error"));
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		} else {
			// modId = UUID.randomUUID().toString().replace("-", "");
			param.put("modId", param.getAsString("modId"));
			// 将 modId 插入到 zjow_module 表中
			param.put("modName", param.getAsString("title"));
			param.put("modSquence", param.getAsString("modSquence"));

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			param.put("modifiedTime", timestamp);
			try {
				param.put(
						"modifiedBy",
						SecurityUserHolder.getCurrentUser() == null ? null
								: String.valueOf(SecurityUserHolder
										.getCurrentUser().getUsername()));
			} catch (Exception e) {
				map.put(MESSAGE_INFO, "修改失败！" + mapban.get("error"));
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader,
						HttpStatus.OK);
			}
			// 保证url唯一
			String modUrl = param.getAsString("modUrl");
			Dto nullDto = new BaseDto();
			// nullDto.put("modUrl", modUrl);
			nullDto.put("mod_url", modUrl);
			nullDto.put("delflag", "0");
			if (!TKStringUtils.isEmpty(modUrl)) {
				List<Dto> lstDto = moduleAction.findAll(nullDto);
				// 修改本身的数据略过和修改其他的数据不略过
				if (lstDto.size() > 0) {
					for (Dto onedto : lstDto) {
						if (!onedto.getAsString("MOD_ID").equalsIgnoreCase(
								modId)) {
							map.put(MESSAGE_INFO,
									"修改失败！与" + onedto.getAsString("MOD_NAME")
											+ "链接名称重复！");
							map.put(RTN_RESULT, "false");
							String json = JSONObject.fromObject(map).toString();
							return new ResponseEntity<String>(json, resHeader,
									HttpStatus.OK);
						}
						;
					}
				}
			}
			moduleAction.updateObject(param);

			// 保存到menu
			Dto menuDto = new BaseDto();
			menuDto.put("menuId", param.getAsString("menuId"));
			menuDto.put("menuName", param.getAsString("modName"));
			menuDto.put("Backup2", modId);
			String loginId = SecurityUserHolder.getCurrentUser() == null ? null
					: String.valueOf(SecurityUserHolder.getCurrentUser()
							.getId());
			menuDto.put("lastModby", loginId);
			menuDto.put("lastModtime", timestamp);
			menuAction.updateObject(menuDto);
			// 更新到Des表
			// String banImgId = null;
			// if (!fmodBanner.isEmpty()) {
			// banImgId = UUID.randomUUID().toString().replace("-", "");
			// param.put("modBanner", banImgId);
			// }
			// param.put("modType", "03");
			param.put("keyWord", param.getAsString("key"));
			param.put("modByname", param.getAsString("modByname"));
			param.put("title", param.getAsString("title"));
			param.put("modImg", imgCoverUrl);
			param.put("modIco", imgIconUrl);
			moduleDesAction.updateObject(param);
			String imgList = param.getAsString("bannerList");

			// banner图更新表中
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
					param.put("banImgId", banImgId);

					param.put("banId", banImgId);
					param.put("banImgName", bannerFilename);
					param.put("belongId", modId);
					param.put("banImgUrl", bannerUrl);
					param.put("banImgDes", "banner图");
					param.put("banImgOutside", "");
					param.put("banImgNum", "999");
					param.put("banType", "1");
					param.put("banImgRename", saveBannerName);
					param.put("delflag", "0");
					bannerAction.insertObject(param);
				} else {
					param.put("banImgId", newBanImgId);
					param.put("banImgName", bannerFilename);
					// param.put("banImgName", bannerFilename);
					param.put("belongId", modId);
					param.put("banImgUrl", bannerUrl);
					param.put("banImgDes", "banner图");
					param.put("banImgOutside", "");
					param.put("banImgNum", "999");
					param.put("banImgRename", saveBannerName);
					param.put("delflag", "0");
					bannerAction.updateObject(param);
				}

			}
			// 图片组更新到内容表banner
			Dto paramDto = new BaseDto();
			paramDto.put("modId", param.getAsString("modId"));
			paramDto.put("delflag", "0");
			Dto contentDto = moduleContentAction.findOne(paramDto);
			String contentId = contentDto.getAsString("contentId");

			String banId_new = UUID.randomUUID().toString().replace("-", "");

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

			// 更新到内容表，有无内容都更新
			// String contentId = UUID.randomUUID().toString().replace("-", "");
			// param.put("contentId", contentId);
			// param.put("modNumber", "999");
			// param.put("contentImg", "");
			// param.put("hasBanner", "1");
			// param.put("bannerId", banImgId);
			param.put("content", param.get("editor_name"));
			// param.put("isDisplay", "1");
			// param.put("weight", param.getAsString("weight"));
			if (!bannerList.isEmpty() && bannerList.size() > 0) {
				param.put("hasBanner", "0");
			} else {
				param.put("hasBanner", "1");
			}
			moduleContentAction.updateObject(param);
		}

		map.put("reqMenuId", param.getAsString("menuId"));
		map.put("menuName", param.getAsString("modParentName"));
		map.put("backUp2", param.getAsString("modParentId"));

		// 根据 图片ID update banner表
		// map.put("modId", modId);
		// map.put("sId", modId);
		map.put(MESSAGE_INFO, "修改成功！");
		logger.info("结束文件上传:" + bannerFilename);
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 发展历程模板 ··增加 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveDevelopCourseModule")
	@ResponseBody
	@SuppressWarnings("unchecked")
	private ResponseEntity<String> saveDevelopCourseModule(
			HttpServletRequest request) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		// MultipartHttpServletRequest multiRequest =
		// (MultipartHttpServletRequest) request; //
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);
		// 保存图片.
		String coverPath = ProperHelper.SMALLIMAGE;
		Dto imgUpload = CommonUtil.generateFile2Server("coverImg", coverPath,
				request);
		if ("1".equals(imgUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的banner文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		String bannerPath = ProperHelper.BANNERFODLER;
		Dto banUpload = CommonUtil.generateFile2Server("banImgUrl", bannerPath,
				request);
		if ("1".equals(banUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的封面图文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		String editimg = ProperHelper.ICOIMG;
		Dto icoUpload = CommonUtil.generateFile2Server("imgIcon", editimg,
				request);
		if ("1".equals(icoUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的图标文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}

		// mod ID
		String modId = UUID.randomUUID().toString().replace("-", "");

		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);

		// banner ID
		String bannerId = UUID.randomUUID().toString().replace("-", "");
		// 写banner表
		if ("0".equals(banUpload.getAsString("code"))) {
			Dto banParam = new BaseDto();
			banParam.put("createBy", createBy);
			banParam.put("createdTime", createTime);
			banParam.put("banImgId", bannerId);
			banParam.put("banId", bannerId);
			banParam.put("belongId", modId);
			banParam.put("banImgName", banUpload.getAsString("originName"));
			banParam.put("banImgUrl", banUpload.getAsString("storePath"));
			String imgId = UUID.randomUUID().toString().replace("-", "");
			banParam.put("banImgId", imgId);
			banParam.put("banImgNum", 1);
			banParam.put("banImgRename", banUpload.getAsString("trueName"));
			banParam.put("delflag", "0");
			bannerAction.insertObject(banParam);
		}

		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");
		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (onedto.getAsString("MOD_URL").equalsIgnoreCase(modUrl)) {
						map.put(MESSAGE_INFO,
								"新增失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}
		}
		// 写mod表
		Dto modParam = new BaseDto();
		modParam.put("createBy", createBy);
		modParam.put("createdTime", createTime);
		modParam.put("delflag", "0");
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modIsleaf", "1");
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		int modSquence = Integer.parseInt(paramDto.getAsString("weight"));
		modParam.put("modSquence", modSquence);
		int modLevel = Integer.parseInt(paramDto.getAsString("modLevel")) + 1;
		modParam.put("modLevel", modLevel);
		modParam.put("modParentId", paramDto.getAsString("modParentId"));
		moduleAction.insertObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("createBy", createBy);
		modDesParam.put("createdTime", createTime);
		modDesParam.put("delflag", "0");
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modType", "07");
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));
		if ("0".equals(banUpload.getAsString("code"))) {
			modDesParam.put("modBanner", bannerId);
		}
		modDesParam.put("modImg", imgUpload.getAsString("storePath"));
		modDesParam.put("modIco", icoUpload.getAsString("storePath"));
		moduleDesAction.insertObject(modDesParam);

		// 写modcontent表
		Dto modContParam = new BaseDto();
		String contentId = UUID.randomUUID().toString().replace("-", "");
		modContParam.put("contentId", contentId);
		modContParam.put("modNumber", "0");
		modContParam.put("hasBanner", "1");
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("isDisplay", "0");
		modContParam.put("createBy", createBy);
		modContParam.put("createdTime", createTime);
		modContParam.put("delflag", "0");
		modContParam.put("modId", modId);
		moduleContentAction.insertObject(modContParam);

		Dto menuParam = new BaseDto();
		String menuId = CommUtil.randomInt(5);
		menuParam.put("menuId", menuId);
		String parentId = paramDto.getAsString("parentId") + "|"
				+ paramDto.getAsString("menuId");
		menuParam.put("parentId", parentId);
		String url = "/moduleDes/developCourseModuleIndex";
		menuParam.put("menuUrl", url);
		menuParam.put("isLeaf", "1");
		menuParam.put("menuName", paramDto.getAsString("title"));
		menuParam.put("menuOrder", 99 - modSquence);
		menuParam.put("menuStatus", 1);
		menuParam.put("Backup2", modId);
		menuParam.put("creator", SecurityUserHolder.getCurrentUser().getId());
		menuParam.put("createdTime", createTime);
		menuParam.put("isShow", 1);
		menuAction.insertObject(menuParam);

		Dto roleUserDto = new BaseDto();
		roleUserDto.put("userId", SecurityUserHolder.getCurrentUser().getId());
		roleUserDto.put("delflag", "0");
		Dto roleUseOne = userRoleAction.findOne(roleUserDto);
		Dto roleMenuDto = new BaseDto();
		roleMenuDto.put("roleId", roleUseOne.getAsString("roleId"));
		roleMenuDto.put("menuId", menuId);
		roleMenuDto.put("creator", SecurityUserHolder.getCurrentUser().getId());
		roleMenuDto.put("createdTime", createTime);
		roleMenuAction.insertObject(roleMenuDto);

		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		map.put("reqMenuId", paramDto.getAsString("menuId"));
		map.put("menuName", paramDto.getAsString("modParentName"));
		map.put("backUp2",
				paramDto.getAsString("parent_modid") == null ? paramDto
						.getAsString("modParentId") : paramDto
						.getAsString("parent_modid"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 不带图列表模板 ·· 保存
	 * 
	 * @return
	 */
	@RequestMapping("/saveWithoutImgListModule")
	@ResponseBody
	@SuppressWarnings("unchecked")
	private ResponseEntity<String> saveWithoutImgListModule(
			HttpServletRequest request) {
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>();
		Dto paramDto = CommonUtil.getParamAsDto(request);
		// 保存图片.
		String coverPath = ProperHelper.SMALLIMAGE;
		Dto imgUpload = CommonUtil.generateFile2Server("coverImg", coverPath,
				request);
		if ("1".equals(imgUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		String bannerPath = ProperHelper.BANNERFODLER;
		Dto banUpload = CommonUtil.generateFile2Server("banImgUrl", bannerPath,
				request);
		if ("1".equals(banUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		String editimg = ProperHelper.ICOIMG;
		Dto iconUpload = CommonUtil.generateFile2Server("imgIcon", editimg,
				request);
		if ("1".equals(iconUpload.getAsString("code"))) {
			map.put(MESSAGE_INFO, "新增失败,请上传正确的文件！");
			map.put(RTN_RESULT, "false");
			String json = JSONObject.fromObject(map).toString();
			return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
		}
		// mod ID
		String modId = UUID.randomUUID().toString().replace("-", "");

		// 创建者
		String createBy = SecurityUserHolder.getCurrentUser().getUsername();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// 创建时间
		String createTime = String.valueOf(time);

		// banner ID
		String bannerId = UUID.randomUUID().toString().replace("-", "");
		// 写banner表
		if ("0".equals(banUpload.getAsString("code"))) {
			Dto banParam = new BaseDto();
			banParam.put("createBy", createBy);
			banParam.put("createdTime", createTime);
			banParam.put("banImgId", bannerId);
			banParam.put("banId", bannerId);
			banParam.put("belongId", modId);
			banParam.put("banImgName", banUpload.getAsString("originName"));
			banParam.put("banImgUrl", banUpload.getAsString("storePath"));
			String imgId = UUID.randomUUID().toString().replace("-", "");
			banParam.put("banImgId", imgId);
			banParam.put("banImgNum", 1);
			banParam.put("banImgRename", banUpload.getAsString("trueName"));
			banParam.put("delflag", "0");
			bannerAction.insertObject(banParam);
		}

		// 保证url唯一
		String modUrl = paramDto.getAsString("modUrl");
		Dto nullDto = new BaseDto();
		// nullDto.put("modUrl", modUrl);
		nullDto.put("mod_url", modUrl);
		nullDto.put("delflag", "0");
		if (!TKStringUtils.isEmpty(modUrl)) {
			List<Dto> lstDto = moduleAction.findAll(nullDto);
			if (lstDto.size() > 0) {
				for (Dto onedto : lstDto) {
					if (onedto.getAsString("MOD_URL").equalsIgnoreCase(modUrl)) {
						map.put(MESSAGE_INFO,
								"新增失败！与" + onedto.getAsString("MOD_NAME")
										+ "链接名称重复！");
						map.put(RTN_RESULT, "false");
						String json = JSONObject.fromObject(map).toString();
						return new ResponseEntity<String>(json, resHeader,
								HttpStatus.OK);
					}
					;
				}
			}
		}
		// 写mod表
		Dto modParam = new BaseDto();
		modParam.put("createBy", createBy);
		modParam.put("createdTime", createTime);
		modParam.put("delflag", "0");
		modParam.put("modId", modId);
		modParam.put("modName", paramDto.getAsString("title"));
		modParam.put("modIsleaf", "1");
		modParam.put("modUrl", paramDto.getAsString("modUrl"));
		int modSquence = Integer.parseInt(paramDto.getAsString("weight"));
		modParam.put("modSquence", modSquence);
		int modLevel = Integer.parseInt(paramDto.getAsString("modLevel")) + 1;
		modParam.put("modLevel", modLevel);
		modParam.put("modParentId", paramDto.getAsString("modParentId"));
		moduleAction.insertObject(modParam);

		// 写moddes表
		Dto modDesParam = new BaseDto();
		modDesParam.put("createBy", createBy);
		modDesParam.put("createdTime", createTime);
		modDesParam.put("delflag", "0");
		modDesParam.put("modId", modId);
		modDesParam.put("modName", paramDto.getAsString("title"));
		modDesParam.put("title", paramDto.getAsString("title"));
		modDesParam.put("modType", "06");
		modDesParam.put("modByname", paramDto.getAsString("title"));
		modDesParam.put("modSeo", paramDto.getAsString("modSeo"));
		modDesParam.put("keyWord", paramDto.getAsString("key"));
		modDesParam.put("modUrl", paramDto.getAsString("modUrl"));
		modDesParam.put("modDes", paramDto.getAsString("modDes"));
		if ("0".equals(banUpload.getAsString("code"))) {
			modDesParam.put("modBanner", bannerId);
		}
		modDesParam.put("modImg", imgUpload.getAsString("storePath"));
		modDesParam.put("modIco", iconUpload.getAsString("storePath"));
		moduleDesAction.insertObject(modDesParam);

		// 写modcontent表
		Dto modContParam = new BaseDto();
		String contentId = UUID.randomUUID().toString().replace("-", "");
		modContParam.put("contentId", contentId);
		modContParam.put("modNumber", "0");
		modContParam.put("hasBanner", "1");
		modContParam.put("content", paramDto.get("editor"));
		modContParam.put("isDisplay", "0");
		modContParam.put("createBy", createBy);
		modContParam.put("createdTime", createTime);
		modContParam.put("delflag", "0");
		modContParam.put("modId", modId);
		moduleContentAction.insertObject(modContParam);

		Dto menuParam = new BaseDto();
		String menuId = CommUtil.randomInt(5);
		menuParam.put("menuId", menuId);
		String parentId = paramDto.getAsString("parentId") + "|"
				+ paramDto.getAsString("menuId");
		menuParam.put("parentId", parentId);
		String url = "/moduleDes/withoutImgListModuleIndex";
		menuParam.put("menuUrl", url);
		menuParam.put("isLeaf", "1");
		menuParam.put("menuName", paramDto.getAsString("title"));
		menuParam.put("menuOrder", 99 - modSquence);
		menuParam.put("menuStatus", 1);
		menuParam.put("Backup2", modId);
		menuParam.put("creator", SecurityUserHolder.getCurrentUser().getId());
		menuParam.put("createdTime", createTime);
		menuParam.put("isShow", 1);
		menuAction.insertObject(menuParam);

		Dto roleUserDto = new BaseDto();
		roleUserDto.put("userId", SecurityUserHolder.getCurrentUser().getId());
		roleUserDto.put("delflag", "0");
		Dto roleUseOne = userRoleAction.findOne(roleUserDto);
		Dto roleMenuDto = new BaseDto();
		roleMenuDto.put("roleId", roleUseOne.getAsString("roleId"));
		roleMenuDto.put("menuId", menuId);
		roleMenuDto.put("creator", SecurityUserHolder.getCurrentUser().getId());
		roleMenuDto.put("createdTime", createTime);
		roleMenuAction.insertObject(roleMenuDto);

		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		map.put("reqMenuId", paramDto.getAsString("menuId"));
		map.put("menuName", paramDto.getAsString("modParentName"));
		map.put("backUp2",
				paramDto.getAsString("parent_modid") == null ? paramDto
						.getAsString("modParentId") : paramDto
						.getAsString("parent_modid"));
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 不带图列表模板 ·· 编辑
	 * 
	 * @return
	 */
	@RequestMapping("/updateWithoutImgListModule")
	@ResponseBody
	private Map<String, String> updateWithoutImgListModule(
			HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = CommonUtil.getParamAsDto(request);

		moduleDesAction.updateObject(param);
		moduleContentAction.updateObject(param);
		map.put(MESSAGE_INFO, "更新成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * banner图替换 图片上传
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/uploadBySpringGrpModuleDes")
	public String uploadBySpringGrpModuleDes(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		String modId = request.getParameter("modId");
		try {
			String pictureUrl = moduleDesAction
					.uploadBySpringGrpModuleDes(request);
		} catch (Exception e) {
		}

		return "moduleDes/moduleDesAdd";
	}

	/**
	 * 弹出框
	 * 
	 * @return
	 */
	@RequestMapping("/showImgsAddPage")
	public String showImgsAddPage() {

		return "moduleDes/imgsAdd";
	}

	/**
	 * 删除一条或多条记录
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, String> deleteModuleDes(
			@RequestParam("modId") String modId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("modId", modId);
		moduleDesAction.deleteObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}
}