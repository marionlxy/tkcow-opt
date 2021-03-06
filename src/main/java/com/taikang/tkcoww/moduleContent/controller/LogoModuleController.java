package com.taikang.tkcoww.moduleContent.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.taikang.tkcoww.logo.action.ICooperLogoAction;
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
import com.taikang.udp.sys.action.IMenuAction;
import com.taikang.udp.sys.action.IRoleMenuAction;
import com.taikang.udp.sys.action.IUserRoleAction;
import com.taikang.udp.sys.util.CommonUtil;

@Controller("LogoModuleController")
@RequestMapping(value = "/logoModule")
public class LogoModuleController extends BaseController {
	/**
	 * 模块service
	 */
	@Resource(name = IModuleAction.ACTION_ID)
	private IModuleAction moduleAction;
	/**
	 * 模块模板service
	 */
	@Resource(name = IModuleContentAction.ACTION_ID)
	private IModuleContentAction moduleContentAction;

	/**
	 * 内容模板service
	 */
	@Resource(name = IModuleDesAction.ACTION_ID)
	private IModuleDesAction moduleDesAction;
	/**
	 * logoservice
	 */
	@Resource(name = ICooperLogoAction.ACTION_ID)
	private ICooperLogoAction cooperLogoAction;
	/**
	 * 角色菜单service
	 */
	@Resource(name = IRoleMenuAction.ACTION_ID)
	private IRoleMenuAction roleMenuAction;
	/**
	 * 菜单service
	 */
	@Resource(name = IMenuAction.ACTION_ID)
	private IMenuAction menuAction;
	/**
	 * 用户角色
	 */
	@Resource(name = IUserRoleAction.ACTION_ID)
	private IUserRoleAction userRoleAction;

	@Resource(name = IBannerAction.ACTION_ID)
	private IBannerAction bannerAction;

	/**
	 * 打开新增或修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/addLogoModel")
	public String addLogoModel(Model model) {
		return "module/logModelAdd";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editLogoModule")
	public String editLogoModule(String modId, Model model) {
		model.addAttribute("modId", modId);
		return "module/logoModuleEdit";
	}

	/**
	 * 保存logo模板
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/saveLogoModel")
	@ResponseBody
	public ResponseEntity<String> saveLogoModel(HttpServletRequest request,
			Model model) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String> map = new HashMap<String, String>();
		Dto paramAsDto = CommonUtil.getParamAsDto(request);// 获得前台参数
		String icopath = (String) (ProperHelper.ICOIMG != null ? ProperHelper.ICOIMG
				: FileHelper.buildFolder(request));// 获取tomcat目录
		String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
				: FileHelper.buildFolder(request));// 获取tomcat目录
		String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");

		String bannerFilename = null,saveBannerName = null, bannerUrl = null;// 文件名字
		Map<String, Object> mapban = new HashMap<String,Object>();
		MultipartFile fmodBanner = multiRequest.getFile("modBanner");
		if (!fmodBanner.isEmpty()) {
		/*//  增加文件验证的方法 20160328  向旭
			//System.out.println(CommonUtil.isValidateImgContennt(fmodBanner));
			if (!CommonUtil.isValidateImgContennt(fmodBanner)){
				map.put(MESSAGE_INFO, "新增失败,请上传正确文件！");
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
			}*/
			bannerFilename = fmodBanner.getOriginalFilename();
			saveBannerName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(bannerFilename));// 重命名
			// 保存banner到服务器
			try {
				logger.info("开始上传文件:" + bannerFilename);
				mapban = WebUtil.saveFileToServer(multiRequest, "modBanner",
						banerpath, saveBannerName, extendes);// 上传文件方法

				/*realBannerPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
						new File(realBannerPath, saveBannerName));// 获得http相对路径
				// 获得http服务器路径
				bannerUrl = WebUtil.getURL(request) + ""
						+ UploadVertor.FILE_UPLOAD_BANNER + "/"
						+ saveBannerName;*/
				bannerUrl =banerpath.replaceAll("\\\\", "/")+ "/"
						+ saveBannerName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//上传图标
		String imgIconFilename = null,saveimgIconName = null,imgIconUrl = null;
		Map<String, Object> mapImgIcon = new HashMap<String,Object>();
		MultipartFile fImgIcon = multiRequest.getFile("imgIcon");
		if (!fImgIcon.isEmpty()) {
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
			}else {
			String modId = UUID.randomUUID().toString().replace("-", "");
			paramAsDto.put("modId", modId);
			int modSequence = Integer.parseInt(paramAsDto.getAsString("modSquence"));
			paramAsDto.put("modSquence", modSequence);
			int modLevel = Integer.parseInt(paramAsDto.getAsString("modLevel")) + 1;
			paramAsDto.put("modLevel", modLevel);

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
			// int modIsleaf = Integer.parseInt(paramAsDto
			// .getAsString("modIsleaf"));
			// 创建时间
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			paramAsDto.put("createdTime", timestamp);
			paramAsDto.put("modIsleaf", "1");// "1"表示叶子节点

			paramAsDto.put("modType", "05");
			paramAsDto.put(
					"createdBy",
					SecurityUserHolder.getCurrentUser() == null ? null : String
							.valueOf(SecurityUserHolder.getCurrentUser()
									.getUsername()));
			paramAsDto.put("delflag", "0");
			// 保存先保存到到主表
			
			moduleAction.insertObject(paramAsDto);
			// 保存到menu
			Dto menuDto = new BaseDto();
			Dto findOne = null;
			int menuId = 0;
			do {
				menuId = Integer.parseInt(CommUtil.randomInt(5));
				menuDto.put("menuId", menuId);
				findOne = menuAction.findOne(menuDto);
			} while (!findOne.isEmpty());
			menuDto.put("parentId", paramAsDto.getAsString("parentId") + "|"
					+ paramAsDto.getAsString("menuId"));
			menuDto.put("isLeaf", "1");
			menuDto.put("menuName", paramAsDto.getAsString("modName"));
	
				menuDto.put("menuUrl", "/logoModule");
		
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
			paramAsDto.put("modIco", imgIconUrl);
			paramAsDto.put("modType", "05");
			//
			paramAsDto.put("title", paramAsDto.getAsString("modName"));
			moduleDesAction.insertObject(paramAsDto);

			// 更新到banner表中
			if (!fmodBanner.isEmpty()) {
				paramAsDto.put("banId", banImgId);
				// String banId = UUID.randomUUID().toString().replace("-", "");
				paramAsDto.put("banImgId", banImgId);// 主键
				paramAsDto.put("banImgName", bannerFilename);
				paramAsDto.put("belongId", modId);
				paramAsDto.put("banImgUrl", bannerUrl);

				paramAsDto.put("banImgDes", "logo模板图");
				paramAsDto.put("banImgOutside", "");
				paramAsDto.put("banImgNum", "999");
				paramAsDto.put("banImgRename", saveBannerName);
				bannerAction.insertObject(paramAsDto);
			}
			// 跳转到
			map.put(MESSAGE_INFO, "新增成功！");
			map.put(RTN_RESULT, "true");

			map.put("reqMenuId", paramAsDto.getAsString("menuId"));
			map.put("menuName", paramAsDto.getAsString("menuName"));
			map.put("backUp2", paramAsDto.getAsString("modParentId"));

		}
		logger.info("结束文件上传:" + bannerFilename + "|"+imgIconFilename);
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 跳转logo模板内容页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showModuleContentIndexPage(HttpServletRequest request,
			Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		String modId = paramAsDto.getAsString("backUp2");

		model.addAttribute("modId", modId);
		return "module/logoModuleIndex";
	}

	/**
	 * 获得logo模板的基本信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getLogoModuleOne")
	@ResponseBody
	public Dto getLogoModuleOne(@RequestParam("modId") String modId, Model model) {

		Dto param = new BaseDto();
		param.put("modId", modId);
		Dto module = moduleAction.findNewOne(param);

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
	 * 修改logo模板
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateLogoModel")
	@ResponseBody
	public ResponseEntity<String> updateLogoModel(HttpServletRequest request,
			Model model) {
		// 定义响应参数的格式防止弹出下载框
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.setContentType(MediaType.TEXT_HTML);

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, String> map = new HashMap<String, String>();
		Dto paramAsDto = CommonUtil.getParamAsDto(request);// 获得前台参数

		String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
				: FileHelper.buildFolder(request));// 获取tomcat目录
		String icopath = (String) (ProperHelper.ICOIMG != null ? ProperHelper.ICOIMG
				: FileHelper.buildFolder(request));// 获取tomcat目录
		
		String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");

		String bannerFilename = null,saveBannerName = null,bannerUrl = null;// 文件名字
		Map<String, Object> mapban = new HashMap<String,Object>();
		MultipartFile fmodBanner = multiRequest.getFile("modBanner");
		if (!fmodBanner.isEmpty() && fmodBanner.getSize() > 0) {
			// 保存banner到服务器
			bannerFilename = fmodBanner.getOriginalFilename();
			saveBannerName = CommonUtil.generateFileName(FilenameUtils
					.getExtension(bannerFilename));// 重命名
			try {
				logger.info("开始上传文件:" + bannerFilename);
				mapban = WebUtil.saveFileToServer(multiRequest, "modBanner",
						banerpath, saveBannerName, extendes);// 上传文件方法
				/*realBannerPath = request.getSession().getServletContext()
						.getRealPath(banerpath);
				FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
						new File(realBannerPath, saveBannerName));// 获得http相对路径
				// 获得http服务器路径
				bannerUrl = WebUtil.getURL(request) + ""
						+ UploadVertor.FILE_UPLOAD_BANNER + "/"
						+ saveBannerName;*/
				bannerUrl =banerpath.replaceAll("\\\\", "/")+ "/"
						+ saveBannerName;
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
			}else {
			String modId = paramAsDto.getAsString("modId");
			paramAsDto.put("modId", modId);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			paramAsDto.put("createdTime", timestamp);
			paramAsDto.put("modType", "05");
			paramAsDto.put(
					"createdBy",
					SecurityUserHolder.getCurrentUser() == null ? null : String
							.valueOf(SecurityUserHolder.getCurrentUser()
									.getUsername()));
			paramAsDto.put("delflag", "0");
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
			// 保存先保存到到主表
			moduleAction.updateObject(paramAsDto);

			// 更新到des表,肯定会有一部分数据
			paramAsDto.put("title", paramAsDto.getAsString("modName"));
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
					paramAsDto.put("banImgDes", "logo模板图");
					paramAsDto.put("banImgOutside", "");
					paramAsDto.put("banImgNum", "999");
					paramAsDto.put("banImgRename", saveBannerName);
					bannerAction.insertObject(paramAsDto);
				} else {
					paramAsDto.put("banImgId", newBanImgId);
					paramAsDto.put("banImgName", bannerFilename);
					// paramAsDto.put("banImgName", bannerFilename);
					paramAsDto.put("belongId", modId);
					paramAsDto.put("banImgUrl", bannerUrl);
					paramAsDto.put("banImgDes", "logo模板图");
					paramAsDto.put("banImgOutside", "");
					paramAsDto.put("banImgNum", "999");
					paramAsDto.put("banImgRename", saveBannerName);
					bannerAction.updateObject(paramAsDto);
				}

			}
			// 跳转到
			map.put(MESSAGE_INFO, "修改成功！");
			map.put(RTN_RESULT, "true");

			map.put("reqMenuId", paramAsDto.getAsString("menuId"));
			map.put("menuName", paramAsDto.getAsString("menuName"));
			map.put("backUp2", paramAsDto.getAsString("modParentId"));

		}
		logger.info("结束文件上传:" + bannerFilename + "|"+imgIconFilename);
		String json = JSONObject.fromObject(map).toString();
		return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}

	/**
	 * 删除logo模板内容
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delLogoModule")
	@ResponseBody
	public Map<String, String> delLogoModule(
			@RequestParam("modId") String modId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("modId", modId);
		param.put("delflag", "1");
		moduleDesAction.updateObject(param);
		moduleAction.updateObject(param);
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");
		return map;
	}

	/**
	 * logo模板内容分页
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> getModuleContentList(String modId,HttpServletRequest request,
			CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
         Dto paramDto = new BaseDto();
         Dto param = CommonUtil.getParamAsDto(request);
         param.put("belongId",modId);
		page.setParamObject(param);
		CurrentPage currentPage = cooperLogoAction.getLogoModelPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 添加logo模板内容
	 * 
	 * @return
	 */
	@RequestMapping("/addLogoImge")
	public String addLogoImge(String modId,Model model) {
		Dto param = new BaseDto();
		List<Dto> logoNameList = cooperLogoAction.findAll(param);
		model.addAttribute("logoNameList", logoNameList);
		model.addAttribute("modId",modId);
		return "module/logoAdd";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editLogoImge")
	public String editLogoImge(String cooperLogoId,String modId, Model model) {
		Dto param = new BaseDto();
		model.addAttribute("cooperLogoId", cooperLogoId);
		List<Dto> logoNameList = cooperLogoAction.findAll(param);
		model.addAttribute("logoNameList", logoNameList);
		model.addAttribute("modId",modId);
		return "module/logoEdit";
	}

	/**
	 * 获得logo模板的基本信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getOne")
	@ResponseBody
	public Dto getModuleContentById(
			@RequestParam("cooperLogoId") String cooperLogoId) {
		Dto param = new BaseDto();
		param.put("cooperLogoId", cooperLogoId);
		return cooperLogoAction.findOne(param);
	}

	/**
	 * 删除logo模板内容
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteModule")
	@ResponseBody
	public Map<String, String> deleteModule(
			@RequestParam("cooperLogoId") String cooperLogoId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("cooperLogoId", cooperLogoId);
		param.put("logoSquence", "1");
		param.put("belongId", "1");
		cooperLogoAction.updateObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}

	/**
	 * 保存logo模板内容
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveLogoInfo")
	@ResponseBody
	private Map<String, String> saveLogoInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = CommonUtil.getParamAsDto(request);

		Dto paramDto = new BaseDto();
		paramDto.put("cooperLogoId", param.get("cooperLogoId"));
		paramDto.put("belongId", param.get("modId"));
		
		paramDto.put("logoSquence", param.get("logoSquence"));
		paramDto.put("cooperLogoName", param.get("cooperLogoName"));
		cooperLogoAction.updateObject(paramDto);
		
		map.put(MESSAGE_INFO, "新增成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}
	
	/**
	 * 保存logo模板内容
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/upLogoInfo")
	@ResponseBody
	private Map<String, String> upLogoInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = CommonUtil.getParamAsDto(request);

		Dto paramDto = new BaseDto();
		String modId=param.getAsString("modId");
		Dto ppo = new BaseDto();
	
		
		ppo.put("cooperLogoId",param.get("cooperLogoId"));
		ppo.put("logoSquence", "1");
		ppo.put("belongId", "1");
		cooperLogoAction.updateObject(ppo);
		
		
			
		paramDto.put("cooperLogoId", param.get("cooperLogoToId"));
		paramDto.put("belongId", param.get("modId"));
		paramDto.put("logoSquence", param.get("logoSquence"));
		paramDto.put("cooperLogoName", param.get("cooperLogoName"));
		cooperLogoAction.updateObject(paramDto);
		map.put(MESSAGE_INFO, "更新成功！");
		map.put(RTN_RESULT, "true");

		return map;
	}
}
