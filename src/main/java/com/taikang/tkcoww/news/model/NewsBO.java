package com.taikang.tkcoww.news.model;

import java.sql.Timestamp;
import java.lang.Integer;
import java.lang.String;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * NewsBO 
  */
public class NewsBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public NewsBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("news_id","news_title","news_keyworld","news_comment","news_content","created_time","created_by","modified_time","modified_by","version","delflag"));
	}
	
		
		
	private String newsId;
		public void setNewsId(String newsId){
		getData().put("news_id",newsId);
		this.newsId=newsId;
	}
	
	public String getNewsId(){
		return (String)getData().get("news_id");
	}	
		
		
	private String newsTitle;
		public void setNewsTitle(String newsTitle){
		getData().put("news_title",newsTitle);
		this.newsTitle=newsTitle;
	}
	
	public String getNewsTitle(){
		return (String)getData().get("news_title");
	}	
		
		
	private String newsKeyworld;
		public void setNewsKeyworld(String newsKeyworld){
		getData().put("news_keyworld",newsKeyworld);
		this.newsKeyworld=newsKeyworld;
	}
	
	public String getNewsKeyworld(){
		return (String)getData().get("news_keyworld");
	}	
		
		
	private String newsComment;
		public void setNewsComment(String newsComment){
		getData().put("news_comment",newsComment);
		this.newsComment=newsComment;
	}
	
	public String getNewsComment(){
		return (String)getData().get("news_comment");
	}	
		
		
	private String newsContent;
		public void setNewsContent(String newsContent){
		getData().put("news_content",newsContent);
		this.newsContent=newsContent;
	}
	
	public String getNewsContent(){
		return (String)getData().get("news_content");
	}	
		
		
	private Timestamp createdTime;
		public void setCreatedTime(Timestamp createdTime){
		getData().put("created_time",createdTime);
		this.createdTime=createdTime;
	}
	
	public Timestamp getCreatedTime(){
		return (Timestamp)getData().get("created_time");
	}	
		
		
	private String createdBy;
		public void setCreatedBy(String createdBy){
		getData().put("created_by",createdBy);
		this.createdBy=createdBy;
	}
	
	public String getCreatedBy(){
		return (String)getData().get("created_by");
	}	
		
		
	private Timestamp modifiedTime;
		public void setModifiedTime(Timestamp modifiedTime){
		getData().put("modified_time",modifiedTime);
		this.modifiedTime=modifiedTime;
	}
	
	public Timestamp getModifiedTime(){
		return (Timestamp)getData().get("modified_time");
	}	
		
		
	private String modifiedBy;
		public void setModifiedBy(String modifiedBy){
		getData().put("modified_by",modifiedBy);
		this.modifiedBy=modifiedBy;
	}
	
	public String getModifiedBy(){
		return (String)getData().get("modified_by");
	}	
		
		
	private Integer version;
		public void setVersion(Integer version){
		getData().put("version",version);
		this.version=version;
	}
	
	public Integer getVersion(){
		return (Integer)getData().get("version");
	}	
		
		
	private String delflag;
		public void setDelflag(String delflag){
		getData().put("delflag",delflag);
		this.delflag=delflag;
	}
	
	public String getDelflag(){
		return (String)getData().get("delflag");
	}	
	 }

