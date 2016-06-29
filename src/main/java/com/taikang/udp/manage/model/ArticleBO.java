package com.taikang.udp.manage.model;

import java.lang.Long;
import java.lang.Integer;
import java.lang.String;
import java.util.Arrays;
import java.util.Date;

import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * ArticleBO 
  */
@SuppressWarnings("unused")
public class ArticleBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public ArticleBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","addtime","modifytime","modifier","deletestatus","content","display","mark","sequence","title","url","articleclass_id"));
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
		
		
	private String content;
		public void setContent(String content){
		getData().put("content",content);
		this.content=content;
	}
	
	public String getContent(){
		return (String)getData().get("content");
	}	
		
		
	private Integer display;
		public void setDisplay(Integer display){
		getData().put("display",display);
		this.display=display;
	}
	
	public Integer getDisplay(){
		return (Integer)getData().get("display");
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
		
		
	private String title;
		public void setTitle(String title){
		getData().put("title",title);
		this.title=title;
	}
	
	public String getTitle(){
		return (String)getData().get("title");
	}	
		
		
	private String url;
		public void setUrl(String url){
		getData().put("url",url);
		this.url=url;
	}
	
	public String getUrl(){
		return (String)getData().get("url");
	}	
		
		
	
	private Long articleclassId;
		public void setArticleclassId(Long articleclassId){
		getData().put("articleclass_id",articleclassId);
		this.articleclassId=articleclassId;
	}
	
	public Long getArticleclassId(){
		return (Long)getData().get("articleclass_id");
	}	
	
	public static Integer SHOW = 1;
    public static Integer HIDDEN = 0;
    private ArticleClassBO articleClass;// 文章分类
    private String class_mark; //父类型标识
    
    

	public String getClass_mark() {
		return class_mark;
	}

	public void setClass_mark(String class_mark) {
		getData().put("class_mark",class_mark);
		this.class_mark=class_mark;
		this.class_mark = class_mark;
	}

	public ArticleClassBO getArticleClass() {
		return articleClass;
	}

	public void setArticleClass(ArticleClassBO articleClass) {
		this.articleClass = articleClass;
	}
    
}

