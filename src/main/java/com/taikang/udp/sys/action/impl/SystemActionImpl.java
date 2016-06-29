package com.taikang.udp.sys.action.impl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.taikang.udp.framework.common.util.TKDateTimeUtils;
import com.taikang.udp.framework.core.exception.app.TKBizException;
import com.taikang.udp.framework.core.service.impl.BaseServiceImpl;
import com.taikang.udp.sys.action.ISystemAction;
import com.taikang.udp.sys.model.MenuBO;
import com.taikang.udp.sys.model.RoleBO;
import com.taikang.udp.sys.model.UserBO;
import com.taikang.udp.sys.service.ILoginService;
import com.taikang.udp.sys.service.IMenuService;
import com.taikang.udp.sys.service.IRoleService;
import com.taikang.udp.sys.service.IUserService;
import com.taikang.udp.sys.util.UserUtils;
import com.taikang.udp.sys.util.enums.UserStatus;
import com.taikang.udp.sys.util.enums.YesNo;
import com.taikang.udp.sys.util.vo.LoginUser;
import com.taikang.udp.sys.util.vo.SysUserMenu;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author ThinkGem
 */
@Service(ISystemAction.SERVICE_ID)
public class SystemActionImpl  extends BaseServiceImpl implements ISystemAction  {

	/**
     * 注入service
     */
	@Resource(name=IUserService.SERVICE_ID)
	private IUserService<UserBO> userService;
	
	@Resource(name=IRoleService.SERVICE_ID)
	private IRoleService<RoleBO> roleService;
	
	@Resource(name=IMenuService.SERVICE_ID)
	private IMenuService<MenuBO> menuService;
	
    @Resource(name=ILoginService.SERVICE_ID)
	private ILoginService loginService;
	
	
	
	//-- LoginUser Service --//
	
	public LoginUser getUser(int userId) {
		UserBO userParam = new UserBO();
		
		
		return userBO2LoginUser(userService.findOne(userParam));
	}
	
	/**
	 * 登陆判断时使用，根据登录名查询用户信息
	 * @param loginName
	 * @return
	 */
	public LoginUser getUserByLoginName(String loginName) {
		
		UserBO userBack;
		try{
			UserBO userParam = new UserBO();
			
			//loginService.checkUserByUserInfo(userParam);
			userBack = appDao.queryOneObject("Login.checkUserByUserInfo", userParam);
		}catch(Exception e){
			throw new TKBizException("",this.getClass().getName(),"checkUserByUserInfo","登陆账户异常，无法登陆，请联系管理员！",e);
		}
		
		if(userBack == null){
			throw new TKBizException("",this.getClass().getName(),"checkUserByUserInfo","系统不存在此用户！",null);
		}else if(UserStatus.Locking.getCode().toString().equals("")){
			throw new TKBizException("",this.getClass().getName(),"checkUserByUserInfo","该用户已锁定，请联系管理员！",null);
		}else if(UserStatus.Deleting.getCode().toString().equals("") ||
				UserStatus.Invalid.getCode().toString().equals("")){
			throw new TKBizException("",this.getClass().getName(),"checkUserByUserInfo","该用户已失效或已删除，无法登陆！",null);
		}
		
		return userBO2LoginUser(userBack);
	}
	
	public void saveUser(LoginUser user) {
		
		userService.insertObject(user.getUser());
		
	}
	
	/**
	 * 更新用户密码
	 * @param userId
	 * @param loginName
	 * @param newPassword
	 */
	public void updatePasswordById(int userId,String loginName, String newPassword) {
		try{
			UserBO userBO = new UserBO();
			userService.updateObject(userBO);
		}catch(Exception e){
			throw new TKBizException("",this.getClass().getName(),"updatePasswordById","加密密码时发生错误！",e);
		}
	}
	
	/**
	 * 更新用户登陆信息
	 * @param userId
	 */
	public void updateUserLoginInfo(int userId) {
		UserBO userBO = new UserBO();
		
		userService.updateObject(userBO);
	}
		
	//-- RoleBO Service --//
	
	public RoleBO getRole(Long roleId) {
		RoleBO roleBO = new RoleBO();
		roleBO.setId(roleId);
		return roleService.findOne(roleBO);
	}
	
	public List<RoleBO> findAllRole(){
		return UserUtils.getRoleList();
	}
	
	//查出所有当前有效的用户信息
	public List<LoginUser> findAllUser(){
		UserBO userBO = new UserBO();
		List<UserBO> userList = userService.findAll(userBO);
		List<LoginUser> loginUserList = new ArrayList<LoginUser>();
		for(UserBO oneUser:userList){
			loginUserList.add(userBO2LoginUser(oneUser));
		}
		return loginUserList;
	}
	
	public void saveRole(RoleBO role) {
		roleService.updateObject(role);
		
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
	}
	
	public void deleteRole(Long roleId) {
		RoleBO roleBO = new RoleBO();
		roleBO.setId(roleId);
		roleBO.setDeletestatus(UserStatus.Deleting.getCode());//标记为删除状态
		
		roleService.deleteObject(roleBO);//删除角色与用户关联信息的操作在角色的删除方法中完成
		
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
	}
	
	/**
	 * 给用户删除某一角色
	 * @param role
	 * @param userId
	 * @return
	 */
	public Boolean outUserInRole(RoleBO role, int userId) {
		LoginUser user = this.getUser(userId);
		List<Long> roleIds = user.getRoleIdList();
		List<RoleBO> roles = user.getRoleList();
		if (roleIds.contains(role.getId())) {
			roles.remove(role);
			saveUser(user);
			return true;
		}
		return false;
	}
	
	/**
	 * 给用户增加某一角色
	 * @param role
	 * @param userId
	 * @return
	 */
	public LoginUser assignUserToRole(RoleBO role, int userId) {
		LoginUser user = this.getUser(userId);
		List<Long> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		user.getRoleList().add(role);
		saveUser(user);		
		return user;
	}
	
	//-- Menu Service --//	
	public MenuBO getMenu(int menuId) {
		MenuBO menuBO = new MenuBO();
		menuBO.setMenuId(menuId);
		return menuService.findOne(menuBO);
	}

	public List<SysUserMenu> findAllMenu(){
		return UserUtils.getMenuList();
	}
	
	public void saveMenu(MenuBO menu) {
		menuService.insertObject(menu);
	
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
	}
	
	@Transactional(readOnly = false)
	public void deleteMenu(int menuId) {
		MenuBO menuBO = new MenuBO();
		menuBO.setMenuId(menuId);
		menuBO.setMenuStatus(UserStatus.Deleting.getCode());//标记为删除状态，删除菜单与角色关联信息的操作在菜单删除方法中完成
		menuService.deleteObject(menuBO);
	
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
	}
	
	//从UserPO转为User
	private LoginUser userBO2LoginUser(UserBO userBO){
		if(userBO != null){
			LoginUser user = new LoginUser(userBO);				
			return user;
		}else{
			return null;
		}		
	}

	@Override
	public RoleBO getRole(int roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRole(int roleId) {
		// TODO Auto-generated method stub
		
	}
}
