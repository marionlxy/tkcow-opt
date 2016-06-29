package com.taikang.tkcoww.moduleDes.model;

import java.sql.Timestamp;
import java.lang.Integer;
import java.lang.String;
import java.util.Arrays;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * ModuleDesBO 
  */
public class ModuleDesBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public ModuleDesBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("mod_id","mod_type","mod_name","mod_byname","mod_seo","title","mod_url","mod_des","mod_banner","mod_img"
				,"created_time","created_by","modified_time","modified_by","version","delflag","weight","seo_des", "mod_key_world","mod_isdes","mod_ico"));
	}
	
		
		
	private String modId;
		public void setModId(String modId){
		getData().put("mod_id",modId);
		this.modId=modId;
	}
	
	public String getModId(){
		return (String)getData().get("mod_id");
	}	
		
		
	private String modType;
		public void setModType(String modType){
		getData().put("mod_type",modType);
		this.modType=modType;
	}
	
	public String getModType(){
		return (String)getData().get("mod_type");
	}	
		
		
	private String modName;
		public void setModName(String modName){
		getData().put("mod_name",modName);
		this.modName=modName;
	}
	
	public String getModName(){
		return (String)getData().get("mod_name");
	}	
		
		
	private String modByname;
		public void setModByname(String modByname){
		getData().put("mod_byname",modByname);
		this.modByname=modByname;
	}
	
	public String getModByname(){
		return (String)getData().get("mod_byname");
	}	
		
		
	private String modKeyWorld;
		public void setModKeyWorld(String modKeyWorld){
		getData().put("mod_key_world",modKeyWorld);
		this.modKeyWorld=modKeyWorld;
	}
	
	public String getModKeyWorld(){
		return (String)getData().get("mod_key_world");
	}	
		
		
	private String modSeo;
		public void setModSeo(String modSeo){
		getData().put("mod_seo",modSeo);
		this.modSeo=modSeo;
	}
	
	public String getModSeo(){
		return (String)getData().get("mod_seo");
	}	
		
		
		
	private String title;
		public void setTitle(String title){
		getData().put("title",title);
		this.title=title;
	}
	
	public String getTitle(){
		return (String)getData().get("title");
	}	
		
		
	private String modUrl;
		public void setModUrl(String modUrl){
		getData().put("mod_url",modUrl);
		this.modUrl=modUrl;
	}
	
	public String getModUrl(){
		return (String)getData().get("mod_url");
	}	
		
		
	private String modDes;
		public void setModDes(String modDes){
		getData().put("mod_des",modDes);
		this.modDes=modDes;
	}
	
	public String getModDes(){
		return (String)getData().get("mod_des");
	}	
		
		
	private String modIsdes;
		public void setModIsdes(String modIsdes){
		getData().put("mod_isdes",modIsdes);
		this.modIsdes=modIsdes;
	}
	
	public String getModIsdes(){
		return (String)getData().get("mod_isdes");
	}	
		
		
	private String modBanner;
		public void setModBanner(String modBanner){
		getData().put("mod_banner",modBanner);
		this.modBanner=modBanner;
	}
	
	public String getModBanner(){
		return (String)getData().get("mod_banner");
	}	
		
		
	private String modImg;
		public void setModImg(String modImg){
		getData().put("mod_img",modImg);
		this.modImg=modImg;
	}
	
	public String getModImg(){
		return (String)getData().get("mod_img");
	}	
		
		
	private String modIco;
		public void setModIco(String modIco){
		getData().put("mod_ico",modIco);
		this.modIco=modIco;
	}
	
	public String getModIco(){
		return (String)getData().get("mod_ico");
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
	
	
	
	private Integer weight;
	public void setWeight(Integer weight){
	getData().put("weight",weight);
	this.weight=weight;
	}
	
	public Integer getWeight(){
		return (Integer)getData().get("weight");
	}	
	
	private String seoDes;
	public void setSeoDes(String seoDes){
		getData().put("seo_des",seoDes);
		this.seoDes=seoDes;
	}
	
	public String getSeoDes(){
		return (String)getData().get("seo_des");
	}
	
	private String keyWord;
	public void setkeyWord(String keyWord){
		getData().put("mod_key_world",keyWord);
		this.keyWord=keyWord;
	}
	
	public String getkeyWord(){
		return (String)getData().get("mod_key_world");
	}
	
	
	 }

