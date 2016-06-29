package com.taikang.udp.accessory.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * AccessoryBO 
  */
@SuppressWarnings("unused")
public class AccessoryBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public AccessoryBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","addtime","modifytime","modifier","deletestatus","ext","height","info","name","path","size","width","album_id","user_id"));
	}
	
	private Long id;
		public void setId(Long id){
		getData().put("id",id);
		this.id=id;
	}
	
	public Long getId(){
		return (Long)getData().get("id");
	}	
		
		
	private Date addtime;
		public void setAddtime(Date addtime){
		getData().put("addtime",addtime);
		this.addtime=addtime;
	}
	
	public Date getAddtime(){
		return (Date)getData().get("addtime");
	}	
		
		
	private Date modifytime;
		public void setModifytime(Date modifytime){
		getData().put("modifytime",modifytime);
		this.modifytime=modifytime;
	}
	
	public Date getModifytime(){
		return (Date)getData().get("modifytime");
	}	
		
		
	private String modifier;
		public void setModifier(String modifier){
		getData().put("modifier",modifier);
		this.modifier=modifier;
	}
	
	public String getModifier(){
		return (String)getData().get("modifier");
	}	
		
		
	private Integer deletestatus;
		public void setDeletestatus(Integer deletestatus){
		getData().put("deletestatus",deletestatus);
		this.deletestatus=deletestatus;
	}
	
	public Integer getDeletestatus(){
		return (Integer)getData().get("deletestatus");
	}	
		
		
	private String ext;
		public void setExt(String ext){
		getData().put("ext",ext);
		this.ext=ext;
	}
	
	public String getExt(){
		return (String)getData().get("ext");
	}	
		
		
	private Integer height;
		public void setHeight(Integer height){
		getData().put("height",height);
		this.height=height;
	}
	
	public Integer getHeight(){
		return (Integer)getData().get("height");
	}	
		
		
	private String info;
		public void setInfo(String info){
		getData().put("info",info);
		this.info=info;
	}
	
	public String getInfo(){
		return (String)getData().get("info");
	}	
		
		
	private String name;
		public void setName(String name){
		getData().put("name",name);
		this.name=name;
	}
	
	public String getName(){
		return (String)getData().get("name");
	}	
		
		
	private String path;
		public void setPath(String path){
		getData().put("path",path);
		this.path=path;
	}
	
	public String getPath(){
		return (String)getData().get("path");
	}	
		
		
	private Float size;
		public void setSize(Float size){
		getData().put("size",size);
		this.size=size;
	}
	
	public Float getSize(){
		return (Float)getData().get("size");
	}	
		
		
	private Integer width;
		public void setWidth(Integer width){
		getData().put("width",width);
		this.width=width;
	}
	
	public Integer getWidth(){
		return (Integer)getData().get("width");
	}	
		
		
	private Long albumId;
		public void setAlbumId(Long albumId){
		getData().put("album_id",albumId);
		this.albumId=albumId;
	}
	
	public Long getAlbumId(){
		return (Long)getData().get("album_id");
	}	
		
		
	private Long userId;
		public void setUserId(Long userId){
		getData().put("user_id",userId);
		this.userId=userId;
	}
	
	public Long getUserId(){
		return (Long)getData().get("user_id");
	}
	
}

