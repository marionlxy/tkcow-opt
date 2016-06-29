package com.taikang.tkcoww.banner.controller;

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
import com.taikang.tkcoww.moduleContent.action.IModuleContentAction;
import com.taikang.udp.common.constant.Globals;
import com.taikang.udp.common.util.CommUtil;
import com.taikang.udp.common.util.FileHelper;
import com.taikang.udp.common.util.ProperHelper;
import com.taikang.udp.common.util.WebUtil;
import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.common.util.TKStringUtils;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.util.CommonUtil;

/**
 * BannerController
 */
@Controller("bannerController")
@RequestMapping(value = "/banner")
public class BannerController extends BaseController {

	@Resource(name=IBannerAction.ACTION_ID)
	private IBannerAction bannerAction;

	@Resource(name=IModuleContentAction.ACTION_ID)
	private IModuleContentAction moduleContentAction;

	/**
	 * 打开主查询页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showBannerIndexPage(HttpServletRequest request,
			Model model) {
		Dto paramAsDto = CommonUtil.getParamAsDto(request);
		String modId = paramAsDto.getAsString("backUp2");

		model.addAttribute("modId", modId);
		return "banner/bannerIndex";
	}

	/**
	 * 查询信息列表
	 * @return 分页列表数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> getBannerList(HttpServletRequest request,CurrentPage page){
		Map<String, Object> map = new HashMap<String, Object>();
		Dto param = CommonUtil.getParamAsDto(request);
		page.setParamObject(param);
		CurrentPage currentPage = bannerAction.getBannerList(page);
		
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		
		return map;
	}

	/**
	 * 查询信息列表 叶子 内容模板
	 * 
	 * @return 分页列表数据
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/listModuleContent")
	@ResponseBody
	public Map<String, Object> listModuleContent(HttpServletRequest request,
			CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();
		Dto param = CommonUtil.getParamAsDto(request);
		Dto contentOne = moduleContentAction.findNewOne(param);
		param.put("delflag", "0");
		// 注意图片组的Id是内容Id
		param.put("modId", contentOne.getAsString("contentId"));
		// param.put("banImgId", "并没有");
		// 搜索条件应该加一个 是 内容模板 类型
		page.setParamObject(param);
		CurrentPage currentPage = bannerAction.getBannerList(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增或修改页面
	 * 
	 * @return
	 */
	@RequestMapping("add")
	public String showBannerAddPage(String banImgId,String modId, Model model) {
		model.addAttribute("modId", modId);
		if (banImgId != null && !banImgId.equals("")) {
			model.addAttribute("banImgId", banImgId);
		}

		return "banner/bannerAdd";
	}
	
	/**
	 * 打开新增或修改页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("edit")
	public String showBannerEditPage(String banImgId,String modId, Model model) {

		if (banImgId != null && !banImgId.equals("")) {
			model.addAttribute("banImgId", banImgId);
			model.addAttribute("modId", modId);
			Dto param = new BaseDto();
			param.put("banImgId", banImgId);
			Dto bannerOne = bannerAction.findOne(param);
			model.addAllAttributes(bannerOne);
		}
		
		return "banner/bannerEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getOne")
	@ResponseBody
	public Dto getBannerById(@RequestParam("banImgId")String banImgId) 
	{
		Dto param = new BaseDto();
		param.put("banImgId", banImgId);
		return bannerAction.findOne(param);
	}


	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/save")
	@ResponseBody
	private ResponseEntity<String> saveBannerInfo(HttpServletRequest request,Model model) {
		// 定义响应参数的格式防止弹出下载框
				HttpHeaders resHeader = new HttpHeaders();
				resHeader.setContentType(MediaType.TEXT_HTML);
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Map<String, String> map = new HashMap<String, String>();
				Dto paramAsDto = CommonUtil.getParamAsDto(request);
				// String menId = paramAsDto.getAsString("editor");
				String banerpath = (String) (ProperHelper.BANNERFODLER != null ? ProperHelper.BANNERFODLER
						: FileHelper.buildFolder(request));// 获取tomcat目录
				CommUtil.createFolder(banerpath);

				String[] extendes = (Globals.DEFAULT_IMAGE_SUFFIX).split("[|]");

				String bannerFilename = null,saveBannerName = null,bannerUrl = null;
				Map<String, Object> mapban = new HashMap<String,Object>();
				MultipartFile fmodBanner = multiRequest.getFile("modBanner");
				//System.out.println(CommonUtil.isValidateImgContennt(fmodBanner));
				if (!fmodBanner.isEmpty()&&fmodBanner.getSize()>0 ) {
					// 保存banner到服务器
					logger.info("开始上传文件:" + bannerFilename);
					bannerFilename = fmodBanner.getOriginalFilename();
					saveBannerName = CommonUtil.generateFileName(FilenameUtils
							.getExtension(bannerFilename));
					try {
				
						mapban = WebUtil.saveFileToServer(multiRequest, "modBanner",
								banerpath, saveBannerName, extendes);
						bannerUrl =banerpath.replaceAll("\\\\", "/")+ "/"
								+ saveBannerName;
						/*realBannerPath = request.getSession().getServletContext()
								.getRealPath(UploadVertor.FILE_UPLOAD_BANNER);*/
						/*realBannerPath = request.getSession().getServletContext()
								.getRealPath(banerpath);
						FileUtils.copyInputStreamToFile(fmodBanner.getInputStream(),
								new File(realBannerPath, saveBannerName));*/
						// 获得http服务器路径
						/*bannerUrl = WebUtil.getURL(request) + ""
								+ UploadVertor.FILE_UPLOAD_BANNER + "/"
								+ saveBannerName;*/
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				map.put(RTN_RESULT, "false");
				if (!TKStringUtils.isEmpty(mapban)&& !TKStringUtils.isEmpty(mapban.get("error"))){
					map.put(MESSAGE_INFO, "新增失败！"+ mapban.get("error").toString());
					String json = JSONObject.fromObject(map).toString();
					return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
					}else {
					// paramAsDto
					String modId = paramAsDto.getAsString("modId");;
					paramAsDto.put("modId",modId);
					// 保存到banner表中
					if (paramAsDto.get("banImgId") == null || "".equals(paramAsDto.get("banImgId"))) {
						String banImgId = UUID.randomUUID().toString().replace("-", "");
						paramAsDto.put("banId", banImgId);
						// String banId = UUID.randomUUID().toString().replace("-", "");
						paramAsDto.put("banImgId", banImgId);// 主键
						paramAsDto.put("banImgName", paramAsDto.getAsString("banImgName"));
						paramAsDto.put("belongId", modId);
						paramAsDto.put("banType", paramAsDto.getAsString("banType"));//banner类型
						paramAsDto.put("banImgUrl", bannerUrl);
						paramAsDto.put("banImgDes", paramAsDto.getAsString("banImgDes"));
						paramAsDto.put("banImgOutside", paramAsDto.getAsString("banImgOutside"));
						paramAsDto.put("banImgNum",paramAsDto.getAsString("banImgNum"));
						paramAsDto.put("banImgRename", saveBannerName);
						Dto user = SecurityUserHolder.getCurrentUser().toDto();
						paramAsDto.put("createdTime", TKDateTimeUtils.getTodayTimeStamp());
						paramAsDto.put("createdBy", user.getAsString("id"));
						paramAsDto.put("version", "1");
						paramAsDto.put("delflag", "0");
						bannerAction.insertObject(paramAsDto);
						map.put(MESSAGE_INFO, "新增成功！");
					}else{
						String banImgId =paramAsDto.getAsString("banImgId");
						//paramAsDto.put("banId", banImgId);
						paramAsDto.put("banImgId", banImgId);// 主键
						if (!fmodBanner.isEmpty()&&fmodBanner.getSize()>0 ) {
						paramAsDto.put("banImgRename", saveBannerName);
						paramAsDto.put("banImgUrl", bannerUrl);
						}
						//paramAsDto.put("belongId", modId);
						//paramAsDto.put("banType", paramAsDto.getAsString("banType"));//banner类型
						paramAsDto.put("banImgName", paramAsDto.getAsString("banImgName"));
						paramAsDto.put("banImgDes", paramAsDto.getAsString("banImgDes"));
						paramAsDto.put("banImgOutside", paramAsDto.getAsString("banImgOutside"));
						paramAsDto.put("banImgNum",paramAsDto.getAsString("banImgNum"));
					
						Dto user = SecurityUserHolder.getCurrentUser().toDto();
						paramAsDto.put("modifiedTime", TKDateTimeUtils.getTodayTimeStamp());
						paramAsDto.put("modifiedBy", user.getAsString("id"));
						//paramAsDto.put("version", "1");
						//paramAsDto.put("delflag", "0");
						bannerAction.updateObject(paramAsDto);
						
						map.put(MESSAGE_INFO, "修改成功！");
					}
					map.put(RTN_RESULT, "true");
					
				}
				logger.info("结束文件上传:" + bannerFilename);
				String json = JSONObject.fromObject(map).toString();
				return new ResponseEntity<String>(json, resHeader, HttpStatus.OK);
	}
	
	
	
	
	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveBannerImg")
	@ResponseBody
	private Map<String, String> saveBannerImg(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		Dto param = CommonUtil.getParamAsDto(request);
		if (param.get("rowId") == null || "".equals(param.get("rowId"))) {

			String banImgNum = request.getParameter("banImgNum");

			String banImgId = UUID.randomUUID().toString().replace("-", "");
			String banId = UUID.randomUUID().toString().replace("-", "");
			param.put("banImgId", banImgId);
			param.put("banId", banId);

			// 必要的默认值
			param.put("banImgName", "默认原图片名");
			param.put("banImgNum", banImgNum);
			param.put("banImgRename", "默认重命名图片名");
			param.put("belongId", "默认belongId");
			param.put("banImgUrl", "默认modImgUrl");
			param.put("delflag", "0");

			// 创建时间
			param.put("createdTime", new Timestamp(System.currentTimeMillis()));

			bannerAction.insertObject(param);

			map.put("banId", banId);
			map.put("banImgId", banImgId);
			map.put("sId", banImgId);

			map.put(MESSAGE_INFO, "新增成功！");
		} else {
			bannerAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 图片组 图片上传
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/uploadBySpringGrpBanner")
	public String uploadBySpringGrpModuleDes(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Dto param = CommonUtil.getParamAsDto(request);
		System.out.println(param);
		System.out.println(param);
		System.out.println(param);

		String banImgId = request.getParameter("banImgId");
		try {
			String pictureUrl = bannerAction.uploadBySpringGrpBanner(request);
		} catch (Exception e) {
		}

		return "moduleDes/moduleDesAdd";
	}

	/**
	 *删除一条或多条记录
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/del")
	@ResponseBody
	public Map<String, String> deleteBanner(
		@RequestParam("banImgId") String banImgId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto user = SecurityUserHolder.getCurrentUser().toDto();
		Dto param = new BaseDto();
		param.put("banImgId", banImgId);
		param.put("modifiedTime", TKDateTimeUtils.getTodayTimeStamp());
		param.put("modifiedBy", user.getAsString("id"));
		param.put("delflag", "1");
		bannerAction.updateObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}
	
}
