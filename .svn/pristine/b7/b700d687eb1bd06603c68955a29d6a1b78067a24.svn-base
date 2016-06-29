package com.taikang.udp.sys.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taikang.udp.framework.common.datastructre.Dto;
import com.taikang.udp.framework.common.datastructre.impl.BaseDto;
import com.taikang.udp.framework.common.util.TKClassUtils;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.core.exception.TKAppException;
import com.taikang.udp.framework.core.exception.TKCheckedException;
import com.taikang.udp.framework.core.persistence.pagination.CurrentPage;
import com.taikang.udp.framework.core.web.BaseController;
import com.taikang.udp.framework.core.web.log.Log;
import com.taikang.udp.framework.core.web.log.LogLevel;
import com.taikang.udp.framework.core.web.log.OperType;
import com.taikang.udp.security.service.SecurityUserHolder;
import com.taikang.udp.sys.action.IRoleAction;
import com.taikang.udp.sys.action.ISystemAction;
import com.taikang.udp.sys.action.IUserAction;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.util.DictUtils;
import com.taikang.udp.sys.util.ExcelReplaceDataVO;
import com.taikang.udp.sys.util.ExcelUtil;
import com.taikang.udp.sys.util.UserUtils;
import com.taikang.udp.sys.util.vo.LoginUser;

/**
 * UserController
 */
@Controller("userController")
@RequestMapping(value = "user")
public class UserController extends BaseController {

	@Resource(name = IUserAction.ACTION_ID)
	private IUserAction userAction;

	@Resource(name = IRoleAction.ACTION_ID)
	private IRoleAction roleAction;

	@Resource(name = ISystemAction.SERVICE_ID)
	private ISystemAction iSystemAction;

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	private String temPlatePath = "D:///template//excel//";
	private String tmpPath = "D:///2233//tmp//";

	/*
	 * 跳转到密码修改页面
	 */
	@RequestMapping("/changePwd")
	private String changePwd(Model model) {
		UserBO user = SecurityUserHolder.getCurrentUser();
		//LoginUser user = UserUtils.getUser();
		//model.addAttribute("userName", "");
		model.addAttribute("userId", String.valueOf(user.getId()));
		return "/uptPwd";
	}

	/**
	 * 打开主查询页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("")
	public String showUserIndexPage(@RequestParam("reqMenuId") String reqMenuId, Model model) {

		if (reqMenuId != null && !reqMenuId.equals("")) {
			model.addAttribute("reqMenuId", reqMenuId);
		}
		return "sys/user/userIndex";
	}

	/**
	 * 打开配置页面
	 * 
	 * @return 页面地址
	 */
	@RequestMapping("/showConfig")
	public String showConfig(@RequestParam("columnStr") String columnStr, Model model) {

		if (columnStr != null && !columnStr.equals("")) {
			try {
				columnStr = java.net.URLDecoder.decode(columnStr, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("中文处理出错！");
			}
			model.addAttribute("columnStr", columnStr);
		}
		return "sys/gridConfig";
	}

	/**
	 * 查询信息列表
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/getUserByConList")
	@ResponseBody
	// @Log(message = "按条件查询用户信息", operType = OperType.READ ,operName =
	// "分页查询用户",level=LogLevel.TRACE )
	public Map<String, Object> getUserByConList(HttpServletRequest request, CurrentPage page,
			@RequestParam("user_code") String userCode, @RequestParam("user_name") String userName) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (userCode == null || ",".equals(userCode.trim()) || "".equals(userCode.trim())) {
			userCode = null;
		}
		if (userName == null || ",".equals(userName.trim()) || "".equals(userName.trim())) {
			userName = null;
		}

		// List<Tree> list = DictUtils.getChildDictTreeLst("", "MultiState");
		// Dto param = getParamAsDto(request);
		// String user_code = request.getParameter("user_code");
		request.setAttribute("user_code", userCode);
		request.setAttribute("user_name", userName);
		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = userAction.queryByCondition(page);
		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());
		return map;
	}

	/**
	 * 用户角色设置<br/>
	 * 
	 * @param userCode
	 * @param userName
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping("/setUserRole")
	public String setUserRole(@RequestParam("userCode") String userCode, @RequestParam("userName") String userName,
			HttpServletRequest request, Model model) {
		if (userCode != null && !userCode.equals("")) {
			model.addAttribute("userCode", userCode);
		}
		if (userName != null && !userName.equals("")) {
			model.addAttribute("userName", userName);
		}
		Dto param = getParamAsDto(request);
		List<Dto> roleList = roleAction.findAll(param);
		model.addAttribute("roleList", roleList);
		return "sys/user/setUserRole";

	}

	/**
	 * 用户机构设置<br/>
	 * 
	 * @param userId
	 * @param userName
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping("/setOrgaize")
	public String setOrgaize(@RequestParam("userId") String userId, @RequestParam("userName") String userName,
			HttpServletRequest request, Model model) {
		if (userId != null && !userId.equals("")) {
			model.addAttribute("userId", userId);
		}
		if (userName != null && !userName.equals("")) {
			model.addAttribute("userName", userName);
		}
		return "sys/user/setUserOrganize";
	}

	/**
	 * 查询信息列表
	 * 
	 * @return 分页列表数据
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	public Map<String, Object> getUserList(HttpServletRequest request, CurrentPage page) {
		Map<String, Object> map = new HashMap<String, Object>();

		page.setParamObject(getParamAsDto(request));
		CurrentPage currentPage = userAction.queryForPage(page);

		map.put("rows", currentPage.getPageItems());
		map.put("total", currentPage.getTotalRows());

		return map;
	}

	/**
	 * 打开新增用户页面<br/>
	 * 
	 * @return
	 */
	@RequestMapping("/showUserAddPage")
	public String showUserAddPage(String rowId, Model model) {
		return "sys/user/userAdd";
	}

	/**
	 * 打开修改页面<br/>
	 * 
	 * @param rowId
	 * @param model
	 * @return
	 */
	@RequestMapping("/showUserEditPage")
	public String showUserEditPage(String rowId, Model model) {
		if (rowId != null && !rowId.equals("")) {
			model.addAttribute("userId", rowId);
		}
		return "sys/user/userEdit";
	}

	/**
	 * 获取一条记录详细信息，用来填充修改界面
	 * 
	 * @return
	 */
	@RequestMapping("/getUserById")
	@ResponseBody
	public Dto getUserById(@RequestParam("rowId") String rowId) {
		Dto param = new BaseDto();
		param.put("userId", rowId);
		return userAction.findOne(param);
	}

	/**
	 * 保存新增或修改的记录，将其持久化到数据库中
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveUserInfo")
	@ResponseBody
	@Log(message = "保存用户信息", operType = OperType.READ, operName = "保存用户信息", level = LogLevel.TRACE)
	private Map<String, String> saveUserInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());
		String userPwd = String.valueOf(param.get("userPwd"));
		if (param.get("userId") == null || "".equals(param.get("userId"))) {
			// 新增用户时初始化用户密码
			String entryptPassword = "";
			try {
				entryptPassword = userPwd;

				param.put("userPwd", entryptPassword);
				param.put("creator", loginId);
				param.put("createTime", TKDateTimeUtils.getTodayTimeStamp());

				userAction.insertObject(param);
				map.put(MESSAGE_INFO, "新增成功！");

			} catch (Exception e) {
				map.put(RTN_RESULT, "false");
				map.put(MESSAGE_INFO, e.getMessage());
				return map;
			}
		} else {
			param.put("lastModby", loginId);
			param.put("lastModtime", TKDateTimeUtils.getTodayTimeStamp());
			userAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 重置选中用户密码<br/>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reSetPwd")
	@ResponseBody
	@Log(message = "重置指定用户密码", operType = OperType.READ, operName = "重置用户密码", level = LogLevel.TRACE)
	public Map<String, String> reSetPwd(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		String passwd;

		passwd = param.getAsString("userCode");

		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());
		param.put("userPwd", passwd);
		// 判断重置的是否为当前用户
		if (loginId.equals(param.get("userId"))) {
			// 当前用户时更新shiro
			iSystemAction.updatePasswordById(Integer.valueOf(loginId), "", param.getAsString("userCode"));
		} else {
			userAction.updateObject(param);
		}
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "密码重置成功！");
		return map;
	}

	/**
	 * 打开查看权限页面<br/>
	 * 
	 * @param roleName
	 * @param roleId
	 * @param roleCode
	 * @param model
	 * @return String
	 */
	@RequestMapping("/showAuthorithPage")
	public String showAuthorithPage(String roleName, String roleId, String roleCode, Model model) {
		if (roleName != null && !roleName.equals("")) {
			model.addAttribute("roleName", roleName);
		}
		if (roleId != null && !roleId.equals("")) {
			model.addAttribute("roleId", roleId);
		}
		if (roleCode != null && !roleCode.equals("")) {
			model.addAttribute("roleCode", roleCode);
		}
		return "sys/user/showAuthorith";
	}

	/**
	 * 删除用户<br/>
	 * 
	 * @param rowId
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	@Log(message = "删除指定用户", operType = OperType.READ, operName = "用户删除", level = LogLevel.TRACE)
	public Map<String, String> deleteUser(@RequestParam("rowId") String rowId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = new BaseDto();
		param.put("userId", rowId);
		userAction.deleteObject(param);

		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "操作成功！");

		return map;
	}

	/**
	 * 修改指定用户密码<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/pwdSave")
	@ResponseBody
	@Log(message = "修改指定用户密码", operType = OperType.READ, operName = "修改用户密码", level = LogLevel.TRACE)
	public Map<String, String> pwdSave(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		String userPwd = param.getAsString("userPwd");
		String userId = String.valueOf(param.get("userId"));

		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());
		String entrypuserPwd;
		try {
			entrypuserPwd = userPwd;
			param.put("userPwd", entrypuserPwd);
		} catch (Exception e) {
			map.put(RTN_RESULT, "false");
			map.put(MESSAGE_INFO, "加密密码时发生错误！");
			return map;
		}
		// 判断重置的是否为当前用户
		if (loginId.equals(param.get("userId"))) {
			// 当前用户时更新shiro
			iSystemAction.updatePasswordById(Integer.valueOf(loginId), "", userPwd);
		} else {
			userAction.updateObject(param);
		}
		map.put(RTN_RESULT, "true");
		map.put(MESSAGE_INFO, "密码重置成功！");
		return map;
	}

	/**
	 * 修改用户机构信息<br/>
	 * 
	 * @param request
	 * @return Map<String,String>
	 */
	@RequestMapping("/setCom")
	@ResponseBody
	@Log(message = "修改指定用户机构", operType = OperType.READ, operName = "修改用户机构信息", level = LogLevel.TRACE)
	private Map<String, String> setCom(HttpServletRequest request, @RequestParam("Com_Id") String Com_Id,
			@RequestParam("userId") String userId) {
		Map<String, String> map = new HashMap<String, String>();
		Dto param = getParamAsDto(request);
		
		//LoginUser user = UserUtils.getUser();
		UserBO user = SecurityUserHolder.getCurrentUser();
		String loginId = String.valueOf(user.getId());
		// String userPwd = String.valueOf(param.get("userPwd"));
		if (userId == null || "".equals(userId)) {
			map.put(RTN_RESULT, "false");
			map.put(MESSAGE_INFO, "没有传入需要设置机构的用户！");
			return map;
		} else {
			param.put("userId", userId);
			param.put("userCom", Com_Id);
			param.put("lastModby", loginId);
			param.put("lastModtime", TKDateTimeUtils.getTodayTimeStamp());
			userAction.updateObject(param);
			map.put(MESSAGE_INFO, "更新成功！");
		}
		map.put(RTN_RESULT, "true");

		return map;
	}

	/**
	 * 导出用户Excel列表<br/>
	 * 
	 * @param request
	 * @param Com_Id
	 * @param userId
	 * @return
	 */
	@RequestMapping("/exportExcel")
	private void exportExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("usercode") String userCode, @RequestParam("username") String userName) {
		Dto param = getParamAsDto(request);
		String fileName = "excel用户信息";
		request.setAttribute("user_code", userCode);
		request.setAttribute("user_name", userName);
		// 取得填充Excel数据
		List<Dto> userinfos = userAction.findAll(param);
		List<Map<String, Object>> list = createExcelRecord(userinfos);
		String columnNames[] = { "用户编码", "用户姓名", "用户性质", "是否在岗", "所属部门" };// 列名
		String keys[] = { "userCode", "userName", "userNature", "workStatus", "userDept" };// map中的key
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			ExcelUtil.createWorkBook("用户信息表", list, keys, columnNames).write(os);

			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			throw new TKAppException("error", "UserController", "exportExcel", "导出Eecel异常！", e);
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					throw new TKAppException("error", "UserController", "exportExcel", "导出Eecel异常！", e);
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					throw new TKAppException("error", "UserController", "exportExcel", "导出Eecel异常！", e);
				}
		}
	}

	/**
	 * 获得Excel中需要的数据<br/>
	 * 
	 * @param dataLists
	 * @return
	 */
	private List<Map<String, Object>> createExcelRecord(List<Dto> dataLists) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		Dto userDto = null;
		for (int j = 0; j < dataLists.size(); j++) {
			// UserBo=(UserBO) dataLists.get(j);
			// UserBo = BaseDto.toModel(UserBO.class , dataLists.get(j));
			userDto = dataLists.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("userCode", userDto.get("User_Code"));
			mapValue.put("userName", userDto.get("User_Name"));
			if ("H".equals(userDto.get("User_Nature"))) {
				mapValue.put("userNature", "总公司");
			} else {
				mapValue.put("userNature", "分公司");
			}
			if ("0".equals(userDto.get("User_Status"))) {
				mapValue.put("workStatus", "已删除");
			} else if ("1".equals(userDto.get("User_Status"))) {
				mapValue.put("workStatus", "正常");
			} else {
				mapValue.put("workStatus", "冻结");
			}

			mapValue.put("userDept", userDto.get("User_Dept"));
			listmap.add(mapValue);
		}
		return listmap;
	}

	/**
	 * 导出用户Excel列表<br/>
	 * 
	 * @param request
	 * @param Com_Id
	 * @param userId
	 * @return
	 */
	@RequestMapping("/exportExcelByTemplate")
	private void exportExcelByTemplate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("rowId") String rowId) {

		temPlatePath = DictUtils.getDictName("01", "temPlatePath", temPlatePath);
		tmpPath = DictUtils.getDictName("02", "temPlatePath", tmpPath);
		String templateName = "userInfoTemplate.xls";// Excel模版
		String templatePath = temPlatePath + templateName;
		Dto param = getParamAsDto(request);
		param.put("userId", rowId);
		Dto dto = userAction.findOne(param);
		String fileName = dto.getAsString("userName") + "用户信息.xls";// 导出Excel文件名
		String filePath = tmpPath + fileName;
		URL url = TKClassUtils.getResource(templateName, UserController.class);
		List<ExcelReplaceDataVO> datas = new ArrayList<ExcelReplaceDataVO>();
		// 找到第14行第2列的company，用"XXX有限公司"替换掉company
		ExcelReplaceDataVO vo1 = new ExcelReplaceDataVO();
		vo1.setRow(0);
		vo1.setColumn(0);
		vo1.setKey("Title");
		vo1.setValue("用户" + dto.getAsString("userName") + "的信息");
		// 找到第5行第2列的content，用"aa替换的内容aa"替换掉content
		ExcelReplaceDataVO vo2 = new ExcelReplaceDataVO();
		vo2.setRow(3);
		vo2.setColumn(0);
		vo2.setKey("userCode");
		vo2.setValue(dto.getAsString("userCode"));
		ExcelReplaceDataVO vo3 = new ExcelReplaceDataVO();
		vo3.setRow(3);
		vo3.setColumn(1);
		vo3.setKey("userName");
		vo3.setValue(dto.getAsString("userName"));
		ExcelReplaceDataVO vo4 = new ExcelReplaceDataVO();
		vo4.setRow(3);
		vo4.setColumn(2);
		vo4.setKey("userNature");
		vo4.setValue(dto.getAsString("userNature"));
		ExcelReplaceDataVO vo5 = new ExcelReplaceDataVO();
		vo5.setRow(3);
		vo5.setColumn(3);
		vo5.setKey("workStatus");
		vo5.setValue(dto.getAsString("workStatus"));
		ExcelReplaceDataVO vo6 = new ExcelReplaceDataVO();
		vo6.setRow(3);
		vo6.setColumn(4);
		vo6.setKey("userDept");
		vo6.setValue(dto.getAsString("userDept"));
		datas.add(vo1);
		datas.add(vo2);
		datas.add(vo3);
		datas.add(vo4);
		datas.add(vo5);
		datas.add(vo6);
		ExcelUtil.replaceModel(datas, templatePath, filePath);

		try {
			ExcelUtil.download(filePath, response, fileName);
		} catch (IOException e) {
			throw new TKAppException("error", "UserController", "exportExcelByTemplate", "导出Eecel异常！", e);
		}
	}

	public String getTemPlatePath() {
		return temPlatePath;
	}

	public void setTemPlatePath(String temPlatePath) {
		this.temPlatePath = temPlatePath;
	}

	public String getTmpPath() {
		return tmpPath;
	}

	public void setTmpPath(String tmpPath) {
		this.tmpPath = tmpPath;
	}

}
