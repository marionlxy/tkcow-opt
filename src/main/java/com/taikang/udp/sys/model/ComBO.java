package com.taikang.udp.sys.model;

import java.sql.Timestamp;
import java.lang.String;
import java.lang.Integer;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * ComBO 
  */
public class ComBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public ComBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("com_id","com_code","com_sname","com_name","com_type","com_level","com_status","provice_no","city_no","district_no","com_address","com_post","com_linkman","com_phone","remark","create_user","create_time","last_modify_user","last_modify_time"));
	}
	
		
		
	private Integer comId;
		public void setComId(Integer comId){
		getData().put("com_id",comId);
		this.comId=comId;
	}
	
	public Integer getComId(){
		return (Integer)getData().get("com_id");
	}	
		
		
	private String comCode;
		public void setComCode(String comCode){
		getData().put("com_code",comCode);
		this.comCode=comCode;
	}
	
	public String getComCode(){
		return (String)getData().get("com_code");
	}	
		
		
	private String comSname;
		public void setComSname(String comSname){
		getData().put("com_sname",comSname);
		this.comSname=comSname;
	}
	
	public String getComSname(){
		return (String)getData().get("com_sname");
	}	
		
		
	private String comName;
		public void setComName(String comName){
		getData().put("com_name",comName);
		this.comName=comName;
	}
	
	public String getComName(){
		return (String)getData().get("com_name");
	}	
		
		
	private String comType;
		public void setComType(String comType){
		getData().put("com_type",comType);
		this.comType=comType;
	}
	
	public String getComType(){
		return (String)getData().get("com_type");
	}	
		
		
	private Integer comLevel;
		public void setComLevel(Integer comLevel){
		getData().put("com_level",comLevel);
		this.comLevel=comLevel;
	}
	
	public Integer getComLevel(){
		return (Integer)getData().get("com_level");
	}	
		
		
	private String comStatus;
		public void setComStatus(String comStatus){
		getData().put("com_status",comStatus);
		this.comStatus=comStatus;
	}
	
	public String getComStatus(){
		return (String)getData().get("com_status");
	}	
		
		
	private String proviceNo;
		public void setProviceNo(String proviceNo){
		getData().put("provice_no",proviceNo);
		this.proviceNo=proviceNo;
	}
	
	public String getProviceNo(){
		return (String)getData().get("provice_no");
	}	
		
		
	private String cityNo;
		public void setCityNo(String cityNo){
		getData().put("city_no",cityNo);
		this.cityNo=cityNo;
	}
	
	public String getCityNo(){
		return (String)getData().get("city_no");
	}	
		
		
	private String districtNo;
		public void setDistrictNo(String districtNo){
		getData().put("district_no",districtNo);
		this.districtNo=districtNo;
	}
	
	public String getDistrictNo(){
		return (String)getData().get("district_no");
	}	
		
		
	private String comAddress;
		public void setComAddress(String comAddress){
		getData().put("com_address",comAddress);
		this.comAddress=comAddress;
	}
	
	public String getComAddress(){
		return (String)getData().get("com_address");
	}	
		
		
	private String comPost;
		public void setComPost(String comPost){
		getData().put("com_post",comPost);
		this.comPost=comPost;
	}
	
	public String getComPost(){
		return (String)getData().get("com_post");
	}	
		
		
	private String comLinkman;
		public void setComLinkman(String comLinkman){
		getData().put("com_linkman",comLinkman);
		this.comLinkman=comLinkman;
	}
	
	public String getComLinkman(){
		return (String)getData().get("com_linkman");
	}	
		
		
	private String comPhone;
		public void setComPhone(String comPhone){
		getData().put("com_phone",comPhone);
		this.comPhone=comPhone;
	}
	
	public String getComPhone(){
		return (String)getData().get("com_phone");
	}	
		
		
	private String remark;
		public void setRemark(String remark){
		getData().put("remark",remark);
		this.remark=remark;
	}
	
	public String getRemark(){
		return (String)getData().get("remark");
	}	
		
		
	private Integer createUser;
		public void setCreateUser(Integer createUser){
		getData().put("create_user",createUser);
		this.createUser=createUser;
	}
	
	public Integer getCreateUser(){
		return (Integer)getData().get("create_user");
	}	
		
		
	private Timestamp createTime;
		public void setCreateTime(Timestamp createTime){
		getData().put("create_time",createTime);
		this.createTime=createTime;
	}
	
	public Timestamp getCreateTime(){
		return (Timestamp)getData().get("create_time");
	}	
		
		
	private Integer lastModifyUser;
		public void setLastModifyUser(Integer lastModifyUser){
		getData().put("last_modify_user",lastModifyUser);
		this.lastModifyUser=lastModifyUser;
	}
	
	public Integer getLastModifyUser(){
		return (Integer)getData().get("last_modify_user");
	}	
		
		
	private Timestamp lastModifyTime;
		public void setLastModifyTime(Timestamp lastModifyTime){
		getData().put("last_modify_time",lastModifyTime);
		this.lastModifyTime=lastModifyTime;
	}
	
	public Timestamp getLastModifyTime(){
		return (Timestamp)getData().get("last_modify_time");
	}	
	 }

