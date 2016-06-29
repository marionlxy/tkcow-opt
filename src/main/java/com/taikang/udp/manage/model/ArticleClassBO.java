package com.taikang.udp.manage.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * ArticleClassBO 
  */
@SuppressWarnings("unused")
public class ArticleClassBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public ArticleClassBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","addtime","modifytime","modifier","deletestatus","classname","level","mark","sequence","parent_id"));
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
		
		
	private String classname;
		public void setClassname(String classname){
		getData().put("classname",classname);
		this.classname=classname;
	}
	
	public String getClassname(){
		return (String)getData().get("classname");
	}	
		
		
	private Integer level;
		public void setLevel(Integer level){
		getData().put("level",level);
		this.level=level;
	}
	
	public Integer getLevel(){
		return (Integer)getData().get("level");
	}	
		
		
	private String mark;
		public void setMark(String mark){
		getData().put("mark",mark);
		this.mark=mark;
	}
	
	public String getMark(){
		return (String)getData().get("mark");
	}	
		
		
	private Integer sequence;
		public void setSequence(Integer sequence){
		getData().put("sequence",sequence);
		this.sequence=sequence;
	}
	
	public Integer getSequence(){
		return (Integer)getData().get("sequence");
	}	
		
	private Long parentId;
		public void setParentId(Long parentId){
		getData().put("parent_id",parentId);
		this.parentId=parentId;
	}
	
	public Long getParentId(){
		return (Long)getData().get("parent_id");
	}
	
	
	public List<ArticleClassBO> getChilds() {
		return childs;
	}

	public void setChilds(List<ArticleClassBO> childs) {
		this.childs = childs;
	}
	
	private List<ArticleClassBO> childs = new ArrayList<ArticleClassBO>();
	
	private ArticleClassBO parent;

	public ArticleClassBO getParent() {
		return parent;
	}

	public void setParent(ArticleClassBO parent) {
		this.parent = parent;
	}
	
	
	 
}

