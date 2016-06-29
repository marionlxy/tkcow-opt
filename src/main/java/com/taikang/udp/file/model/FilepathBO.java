package com.taikang.udp.file.model;

import java.util.Arrays;
import java.sql.Timestamp;
import java.lang.String;
import java.lang.Integer;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * FilepathBO 
  */
public class FilepathBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public FilepathBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","relation_no","filepath_name","file_name","file_path","desc","remark","oper_name","create_time"));
	}
	
		
		
	private Integer id;
		public void setId(Integer id){
		getData().put("id",id);
		this.id=id;
	}
	
	public Integer getId(){
		return (Integer)getData().get("id");
	}	
		
		
	private String relationNo;
		public void setRelationNo(String relationNo){
		getData().put("relation_no",relationNo);
		this.relationNo=relationNo;
	}
	
	public String getRelationNo(){
		return (String)getData().get("relation_no");
	}	
		
		
	private String filepathName;
		public void setFilepathName(String filepathName){
		getData().put("filepath_name",filepathName);
		this.filepathName=filepathName;
	}
	
	public String getFilepathName(){
		return (String)getData().get("filepath_name");
	}	
		
		
	private String fileName;
		public void setFileName(String fileName){
		getData().put("file_name",fileName);
		this.fileName=fileName;
	}
	
	public String getFileName(){
		return (String)getData().get("file_name");
	}	
		
		
	private String filePath;
		public void setFilePath(String filePath){
		getData().put("file_path",filePath);
		this.filePath=filePath;
	}
	
	public String getFilePath(){
		return (String)getData().get("file_path");
	}	
		
		
	private String desc;
		public void setDesc(String desc){
		getData().put("desc",desc);
		this.desc=desc;
	}
	
	public String getDesc(){
		return (String)getData().get("desc");
	}	
		
		
	private String remark;
		public void setRemark(String remark){
		getData().put("remark",remark);
		this.remark=remark;
	}
	
	public String getRemark(){
		return (String)getData().get("remark");
	}	
		
		
	private String operName;
		public void setOperName(String operName){
		getData().put("oper_name",operName);
		this.operName=operName;
	}
	
	public String getOperName(){
		return (String)getData().get("oper_name");
	}	
		
		
	private Timestamp createTime;
		public void setCreateTime(Timestamp createTime){
		getData().put("create_time",createTime);
		this.createTime=createTime;
	}
	
	public Timestamp getCreateTime(){
		return (Timestamp)getData().get("create_time");
	}	
	 }

