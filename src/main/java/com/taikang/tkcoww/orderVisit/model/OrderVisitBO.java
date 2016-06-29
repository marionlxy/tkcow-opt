package com.taikang.tkcoww.orderVisit.model;

import java.sql.Timestamp;
import java.lang.Integer;
import java.lang.String;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * OrderVisitBO 
  */
public class OrderVisitBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public OrderVisitBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("visit_id","visit_num","visit_name","visit_tel","visit_count","visit_time","visit_site","visit_age","visit_sex","visit_service","visit_bak","visit_from","user_ip","created_time","created_by","modified_time","modified_by","version","delflag"));
	}
	
		
		
	private String visitId;
		public void setVisitId(String visitId){
		getData().put("visit_id",visitId);
		this.visitId=visitId;
	}
	
	public String getVisitId(){
		return (String)getData().get("visit_id");
	}	
		
		
	private String visitNum;
		public void setVisitNum(String visitNum){
		getData().put("visit_num",visitNum);
		this.visitNum=visitNum;
	}
	
	public String getVisitNum(){
		return (String)getData().get("visit_num");
	}	
		
		
	private String visitName;
		public void setVisitName(String visitName){
		getData().put("visit_name",visitName);
		this.visitName=visitName;
	}
	
	public String getVisitName(){
		return (String)getData().get("visit_name");
	}	
		
		
	private String visitTel;
		public void setVisitTel(String visitTel){
		getData().put("visit_tel",visitTel);
		this.visitTel=visitTel;
	}
	
	public String getVisitTel(){
		return (String)getData().get("visit_tel");
	}	
		
		
	private String visitCount;
		public void setVisitCount(String visitCount){
		getData().put("visit_count",visitCount);
		this.visitCount=visitCount;
	}
	
	public String getVisitCount(){
		return (String)getData().get("visit_count");
	}	
		
		
	private Timestamp visitTime;
		public void setVisitTime(Timestamp visitTime){
		getData().put("visit_time",visitTime);
		this.visitTime=visitTime;
	}
	
	public Timestamp getVisitTime(){
		return (Timestamp)getData().get("visit_time");
	}	
		
		
	private String visitSite;
		public void setVisitSite(String visitSite){
		getData().put("visit_site",visitSite);
		this.visitSite=visitSite;
	}
	
	public String getVisitSite(){
		return (String)getData().get("visit_site");
	}	
		
		
	private String visitAge;
		public void setVisitAge(String visitAge){
		getData().put("visit_age",visitAge);
		this.visitAge=visitAge;
	}
	
	public String getVisitAge(){
		return (String)getData().get("visit_age");
	}	
		
		
	private String visitSex;
		public void setVisitSex(String visitSex){
		getData().put("visit_sex",visitSex);
		this.visitSex=visitSex;
	}
	
	public String getVisitSex(){
		return (String)getData().get("visit_sex");
	}	
		
		
	private String visitService;
		public void setVisitService(String visitService){
		getData().put("visit_service",visitService);
		this.visitService=visitService;
	}
	
	public String getVisitService(){
		return (String)getData().get("visit_service");
	}	
		
		
	private String visitBak;
		public void setVisitBak(String visitBak){
		getData().put("visit_bak",visitBak);
		this.visitBak=visitBak;
	}
	
	public String getVisitBak(){
		return (String)getData().get("visit_bak");
	}	
		
		
	private String visitFrom;
		public void setVisitFrom(String visitFrom){
		getData().put("visit_from",visitFrom);
		this.visitFrom=visitFrom;
	}
	
	public String getVisitFrom(){
		return (String)getData().get("visit_from");
	}	
		
		
	private String userIp;
		public void setUserIp(String userIp){
		getData().put("user_ip",userIp);
		this.userIp=userIp;
	}
	
	public String getUserIp(){
		return (String)getData().get("user_ip");
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

