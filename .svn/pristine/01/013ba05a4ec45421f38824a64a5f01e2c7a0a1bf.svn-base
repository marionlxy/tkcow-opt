package com.taikang.udp.sys.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.taikang.udp.framework.common.datastructre.impl.BaseBO;

import org.apache.commons.lang.StringUtils;


/**
  * ResBO 
  */

@SuppressWarnings("unused")
public class ResBO  extends BaseBO {
	
	private List<RoleBO> roles = new ArrayList<RoleBO>();
	
	private static final long serialVersionUID = 1L;
	
	public ResBO(){
		init();
	}
	
	public String getRoleAuthorities() {
		List<String> roleAuthorities = new ArrayList<String>();
		for (RoleBO role : roles) {
			roleAuthorities.add(role.getRolecode());
		}
		return StringUtils.join(roleAuthorities.toArray(), ",");
	}
	
	public void setRoles(List<RoleBO> roles) {
		this.roles = roles;
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","addtime","modifytime","modifier","deletestatus","info","resname","sequence","type","value"));
	}
	
		
		
	private Long id;
		public void setId(Long id){
		getData().put("id",id);
		this.id=id;
	}
	
	public Long getId(){
		return (Long)getData().get("id");
	}	
		
		
	private Timestamp addtime;
		public void setAddtime(Timestamp addtime){
		getData().put("addtime",addtime);
		this.addtime=addtime;
	}
	
	public Timestamp getAddtime(){
		return (Timestamp)getData().get("addtime");
	}	
		
	private Timestamp modifytime;
		public void setModifytime(Timestamp modifytime){
		getData().put("modifytime",modifytime);
		this.modifytime=modifytime;
	}
	
	public Timestamp getModifytime(){
		return (Timestamp)getData().get("modifytime");
	}	
		
		
	private String modifier;
		public void setModifier(String modifier){
		getData().put("modifier",modifier);
		this.modifier=modifier;
	}
	
	public String getModifier(){
		return (String)getData().get("modifier");
	}	
		
		
	private String deletestatus;
		public void setDeletestatus(String deletestatus){
		getData().put("deletestatus",deletestatus);
		this.deletestatus=deletestatus;
	}
	
	public String getDeletestatus(){
		return (String)getData().get("deletestatus");
	}	
		
		
	private String info;
		public void setInfo(String info){
		getData().put("info",info);
		this.info=info;
	}
	
	public String getInfo(){
		return (String)getData().get("info");
	}	
		
		
	private String resname;
		public void setResname(String resname){
		getData().put("resname",resname);
		this.resname=resname;
	}
	
	public String getResname(){
		return (String)getData().get("resname");
	}	
		
		
	private Integer sequence;
		public void setSequence(Integer sequence){
		getData().put("sequence",sequence);
		this.sequence=sequence;
	}
	
	public Integer getSequence(){
		return (Integer)getData().get("sequence");
	}	
		
		
	private String type;
		public void setType(String type){
		getData().put("type",type);
		this.type=type;
	}
	
	public String getType(){
		return (String)getData().get("type");
	}	
		
		
	private String value;
		public void setValue(String value){
		getData().put("value",value);
		this.value=value;
	}
	
	public String getValue(){
		return (String)getData().get("value");
	}	
}

