package com.taikang.udp.sys.model;

import java.sql.Timestamp;
import java.lang.String;
import java.lang.Integer;
import java.math.BigDecimal;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * LogBO 
  */
public class LogBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public LogBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("log_id","log_type","oper_type","oper_name","message","log_level","user_id","user_name","create_time","client_ip","req_url","req_method","req_param","backup1","backup2"));
	}
	
		
		
	private BigDecimal logId;
		public void setLogId(BigDecimal logId){
		getData().put("log_id",logId);
		this.logId=logId;
	}
	
	public BigDecimal getLogId(){
		return (BigDecimal)getData().get("log_id");
	}	
		
		
	private Integer logType;
		public void setLogType(Integer logType){
		getData().put("log_type",logType);
		this.logType=logType;
	}
	
	public Integer getLogType(){
		return (Integer)getData().get("log_type");
	}	
		
		
	private String operType;
		public void setOperType(String operType){
		getData().put("oper_type",operType);
		this.operType=operType;
	}
	
	public String getOperType(){
		return (String)getData().get("oper_type");
	}	
		
		
	private String operName;
		public void setOperName(String operName){
		getData().put("oper_name",operName);
		this.operName=operName;
	}
	
	public String getOperName(){
		return (String)getData().get("oper_name");
	}	
		
		
	private String message;
		public void setMessage(String message){
		getData().put("message",message);
		this.message=message;
	}
	
	public String getMessage(){
		return (String)getData().get("message");
	}	
		
		
	private String logLevel;
		public void setLogLevel(String logLevel){
		getData().put("log_level",logLevel);
		this.logLevel=logLevel;
	}
	
	public String getLogLevel(){
		return (String)getData().get("log_level");
	}	
		
		
	private String userId;
		public void setUserId(String userId){
		getData().put("user_id",userId);
		this.userId=userId;
	}
	
	public String getUserId(){
		return (String)getData().get("user_id");
	}	
		
		
	private String userName;
		public void setUserName(String userName){
		getData().put("user_name",userName);
		this.userName=userName;
	}
	
	public String getUserName(){
		return (String)getData().get("user_name");
	}	
		
		
	private Timestamp createTime;
		public void setCreateTime(Timestamp createTime){
		getData().put("create_time",createTime);
		this.createTime=createTime;
	}
	
	public Timestamp getCreateTime(){
		return (Timestamp)getData().get("create_time");
	}	
		
		
	private String clientIp;
		public void setClientIp(String clientIp){
		getData().put("client_ip",clientIp);
		this.clientIp=clientIp;
	}
	
	public String getClientIp(){
		return (String)getData().get("client_ip");
	}	
		
		
	private String reqUrl;
		public void setReqUrl(String reqUrl){
		getData().put("req_url",reqUrl);
		this.reqUrl=reqUrl;
	}
	
	public String getReqUrl(){
		return (String)getData().get("req_url");
	}	
		
		
	private String reqMethod;
		public void setReqMethod(String reqMethod){
		getData().put("req_method",reqMethod);
		this.reqMethod=reqMethod;
	}
	
	public String getReqMethod(){
		return (String)getData().get("req_method");
	}	
		
		
	private String reqParam;
		public void setReqParam(String reqParam){
		getData().put("req_param",reqParam);
		this.reqParam=reqParam;
	}
	
	public String getReqParam(){
		return (String)getData().get("req_param");
	}	
		
		
	private String backup1;
		public void setBackup1(String backup1){
		getData().put("backup1",backup1);
		this.backup1=backup1;
	}
	
	public String getBackup1(){
		return (String)getData().get("backup1");
	}	
		
		
	private String backup2;
		public void setBackup2(String backup2){
		getData().put("backup2",backup2);
		this.backup2=backup2;
	}
	
	public String getBackup2(){
		return (String)getData().get("backup2");
	}	
	 }

