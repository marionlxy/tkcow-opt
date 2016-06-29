package com.taikang.udp.sys.model;

import java.sql.Timestamp;
import java.lang.Integer;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * ComRelaBO 
  */
public class ComRelaBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public ComRelaBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("parent_id","child_id","create_user","create_time","last_modify_user","last_modify_time"));
	}
	
		
		
	private Integer parentId;
		public void setParentId(Integer parentId){
		getData().put("parent_id",parentId);
		this.parentId=parentId;
	}
	
	public Integer getParentId(){
		return (Integer)getData().get("parent_id");
	}	
		
		
	private Integer childId;
		public void setChildId(Integer childId){
		getData().put("child_id",childId);
		this.childId=childId;
	}
	
	public Integer getChildId(){
		return (Integer)getData().get("child_id");
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

