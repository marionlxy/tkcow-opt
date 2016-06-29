package com.taikang.udp.sys.model;

import java.sql.Timestamp;
import java.lang.Integer;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * UserRoleBO 
  */
public class UserRoleBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public UserRoleBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("user_id","role_id","creator","create_time","last_modby","last_modtime"));
	}
	
		
		
	private Integer userId;
		public void setUserId(Integer userId){
		getData().put("user_id",userId);
		this.userId=userId;
	}
	
	public Integer getUserId(){
		return (Integer)getData().get("user_id");
	}	
		
		
	private Integer roleId;
		public void setRoleId(Integer roleId){
		getData().put("role_id",roleId);
		this.roleId=roleId;
	}
	
	public Integer getRoleId(){
		return (Integer)getData().get("role_id");
	}	
		
		
	private Integer creator;
		public void setCreator(Integer creator){
		getData().put("creator",creator);
		this.creator=creator;
	}
	
	public Integer getCreator(){
		return (Integer)getData().get("creator");
	}	
		
		
	private Timestamp createTime;
		public void setCreateTime(Timestamp createTime){
		getData().put("create_time",createTime);
		this.createTime=createTime;
	}
	
	public Timestamp getCreateTime(){
		return (Timestamp)getData().get("create_time");
	}	
		
		
	private Integer lastModby;
		public void setLastModby(Integer lastModby){
		getData().put("last_modby",lastModby);
		this.lastModby=lastModby;
	}
	
	public Integer getLastModby(){
		return (Integer)getData().get("last_modby");
	}	
		
		
	private Timestamp lastModtime;
		public void setLastModtime(Timestamp lastModtime){
		getData().put("last_modtime",lastModtime);
		this.lastModtime=lastModtime;
	}
	
	public Timestamp getLastModtime(){
		return (Timestamp)getData().get("last_modtime");
	}	
	 }

