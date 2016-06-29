package com.taikang.udp.sys.util.vo;

/**
 * 用户菜单模型视图
 * @author Johnny.lu
 * @version [V1.0.0]
 * @Credited 2014年9月5日 下午4:23:14
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class SysUserMenu  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String menuId;//菜单ID
    private String menuName;//菜单名称
    private String isLeaf;//是否叶子节点
    private String parentId;//父菜单ID
    private String parentName;//父菜单名称
    private String url;//菜单显示路径
    private String icon;//菜单显示图标
    
    private String isShow;//区分菜单与按钮，1-菜单（显示）；0-按钮（不显示）
    private String permission;//按钮权限验证的标示
    
    // Constructors
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
     
}