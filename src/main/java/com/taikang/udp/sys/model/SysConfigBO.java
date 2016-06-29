package com.taikang.udp.sys.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * SysConfigBO 
  */

@SuppressWarnings("unused")
public class SysConfigBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public SysConfigBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","addtime","modifytime","modifier","deletestatus","address","bigheight","bigwidth","closereason","copyright","description","emailenable","emailhost","emailport","emailpws","emailtest","emailuser","emailusername","hotsearch","imagefilesize","imagesavetype","imagesuffix","integral","keywords","middleheight","middlewidth","securitycodelogin","securitycoderegister","securitycodetype","smallheight","smallwidth","smsenbale","smspassword","smstest","smsurl","smsusername","syslanguage","title","uploadfilepath","visitorconsult","websitename","websitestate","membericon_id","websitelogo_id","qq_login","qq_login_id","qq_login_key","qq_domain_code","sina_domain_code","sina_login","sina_login_id","sina_login_key","imagewebserver","service_qq_list","service_telphone_list","currency_code","websitecss"));
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
		
		
	private String address;
		public void setAddress(String address){
		getData().put("address",address);
		this.address=address;
	}
	
	public String getAddress(){
		return (String)getData().get("address");
	}	
		
		
	private Integer bigheight;
		public void setBigheight(Integer bigheight){
		getData().put("bigheight",bigheight);
		this.bigheight=bigheight;
	}
	
	public Integer getBigheight(){
		return (Integer)getData().get("bigheight");
	}	
		
		
	private Integer bigwidth;
		public void setBigwidth(Integer bigwidth){
		getData().put("bigwidth",bigwidth);
		this.bigwidth=bigwidth;
	}
	
	public Integer getBigwidth(){
		return (Integer)getData().get("bigwidth");
	}	
		
		
	private String closereason;
		public void setClosereason(String closereason){
		getData().put("closereason",closereason);
		this.closereason=closereason;
	}
	
	public String getClosereason(){
		return (String)getData().get("closereason");
	}	
		
		
	private String copyright;
		public void setCopyright(String copyright){
		getData().put("copyright",copyright);
		this.copyright=copyright;
	}
	
	public String getCopyright(){
		return (String)getData().get("copyright");
	}	
		
		
	private String description;
		public void setDescription(String description){
		getData().put("description",description);
		this.description=description;
	}
	
	public String getDescription(){
		return (String)getData().get("description");
	}	
		
		
	private Integer emailenable;
		public void setEmailenable(Integer emailenable){
		getData().put("emailenable",emailenable);
		this.emailenable=emailenable;
	}
	
	public Integer getEmailenable(){
		return (Integer)getData().get("emailenable");
	}	
		
		
	private String emailhost;
		public void setEmailhost(String emailhost){
		getData().put("emailhost",emailhost);
		this.emailhost=emailhost;
	}
	
	public String getEmailhost(){
		return (String)getData().get("emailhost");
	}	
		
		
	private Integer emailport;
		public void setEmailport(Integer emailport){
		getData().put("emailport",emailport);
		this.emailport=emailport;
	}
	
	public Integer getEmailport(){
		return (Integer)getData().get("emailport");
	}	
		
		
	private String emailpws;
		public void setEmailpws(String emailpws){
		getData().put("emailpws",emailpws);
		this.emailpws=emailpws;
	}
	
	public String getEmailpws(){
		return (String)getData().get("emailpws");
	}	
		
		
	private String emailtest;
		public void setEmailtest(String emailtest){
		getData().put("emailtest",emailtest);
		this.emailtest=emailtest;
	}
	
	public String getEmailtest(){
		return (String)getData().get("emailtest");
	}	
		
		
	private String emailuser;
		public void setEmailuser(String emailuser){
		getData().put("emailuser",emailuser);
		this.emailuser=emailuser;
	}
	
	public String getEmailuser(){
		return (String)getData().get("emailuser");
	}	
		
		
	private String emailusername;
		public void setEmailusername(String emailusername){
		getData().put("emailusername",emailusername);
		this.emailusername=emailusername;
	}
	
	public String getEmailusername(){
		return (String)getData().get("emailusername");
	}	
		
		
	private String hotsearch;
		public void setHotsearch(String hotsearch){
		getData().put("hotsearch",hotsearch);
		this.hotsearch=hotsearch;
	}
	
	public String getHotsearch(){
		return (String)getData().get("hotsearch");
	}	
		
		
	private Integer imagefilesize;
		public void setImagefilesize(Integer imagefilesize){
		getData().put("imagefilesize",imagefilesize);
		this.imagefilesize=imagefilesize;
	}
	
	public Integer getImagefilesize(){
		return (Integer)getData().get("imagefilesize");
	}	
		
		
	private String imagesavetype;
		public void setImagesavetype(String imagesavetype){
		getData().put("imagesavetype",imagesavetype);
		this.imagesavetype=imagesavetype;
	}
	
	public String getImagesavetype(){
		return (String)getData().get("imagesavetype");
	}	
		
		
	private String imagesuffix;
		public void setImagesuffix(String imagesuffix){
		getData().put("imagesuffix",imagesuffix);
		this.imagesuffix=imagesuffix;
	}
	
	public String getImagesuffix(){
		return (String)getData().get("imagesuffix");
	}	
		
		
	private String integral;
		public void setIntegral(String integral){
		getData().put("integral",integral);
		this.integral=integral;
	}
	
	public String getIntegral(){
		return (String)getData().get("integral");
	}	
		
		
	private String keywords;
		public void setKeywords(String keywords){
		getData().put("keywords",keywords);
		this.keywords=keywords;
	}
	
	public String getKeywords(){
		return (String)getData().get("keywords");
	}	
		
		
	private Integer middleheight;
		public void setMiddleheight(Integer middleheight){
		getData().put("middleheight",middleheight);
		this.middleheight=middleheight;
	}
	
	public Integer getMiddleheight(){
		return (Integer)getData().get("middleheight");
	}	
		
		
	private Integer middlewidth;
		public void setMiddlewidth(Integer middlewidth){
		getData().put("middlewidth",middlewidth);
		this.middlewidth=middlewidth;
	}
	
	public Integer getMiddlewidth(){
		return (Integer)getData().get("middlewidth");
	}	
		
		
	private String securitycodelogin;
		public void setSecuritycodelogin(String securitycodelogin){
		getData().put("securitycodelogin",securitycodelogin);
		this.securitycodelogin=securitycodelogin;
	}
	
	public String getSecuritycodelogin(){
		return (String)getData().get("securitycodelogin");
	}	
		
		
	private Integer securitycoderegister;
		public void setSecuritycoderegister(Integer securitycoderegister){
		getData().put("securitycoderegister",securitycoderegister);
		this.securitycoderegister=securitycoderegister;
	}
	
	public Integer getSecuritycoderegister(){
		return (Integer)getData().get("securitycoderegister");
	}	
		
		
	private String securitycodetype;
		public void setSecuritycodetype(String securitycodetype){
		getData().put("securitycodetype",securitycodetype);
		this.securitycodetype=securitycodetype;
	}
	
	public String getSecuritycodetype(){
		return (String)getData().get("securitycodetype");
	}	
		
		
	private Integer smallheight;
		public void setSmallheight(Integer smallheight){
		getData().put("smallheight",smallheight);
		this.smallheight=smallheight;
	}
	
	public Integer getSmallheight(){
		return (Integer)getData().get("smallheight");
	}	
		
		
	private Integer smallwidth;
		public void setSmallwidth(Integer smallwidth){
		getData().put("smallwidth",smallwidth);
		this.smallwidth=smallwidth;
	}
	
	public Integer getSmallwidth(){
		return (Integer)getData().get("smallwidth");
	}	
		
		
	private String smsenbale;
		public void setSmsenbale(String smsenbale){
		getData().put("smsenbale",smsenbale);
		this.smsenbale=smsenbale;
	}
	
	public String getSmsenbale(){
		return (String)getData().get("smsenbale");
	}	
		
		
	private String smspassword;
		public void setSmspassword(String smspassword){
		getData().put("smspassword",smspassword);
		this.smspassword=smspassword;
	}
	
	public String getSmspassword(){
		return (String)getData().get("smspassword");
	}	
		
		
	private String smstest;
		public void setSmstest(String smstest){
		getData().put("smstest",smstest);
		this.smstest=smstest;
	}
	
	public String getSmstest(){
		return (String)getData().get("smstest");
	}	
		
		
	private String smsurl;
		public void setSmsurl(String smsurl){
		getData().put("smsurl",smsurl);
		this.smsurl=smsurl;
	}
	
	public String getSmsurl(){
		return (String)getData().get("smsurl");
	}	
		
		
	private String smsusername;
		public void setSmsusername(String smsusername){
		getData().put("smsusername",smsusername);
		this.smsusername=smsusername;
	}
	
	public String getSmsusername(){
		return (String)getData().get("smsusername");
	}	
		
		
	private String syslanguage;
		public void setSyslanguage(String syslanguage){
		getData().put("syslanguage",syslanguage);
		this.syslanguage=syslanguage;
	}
	
	public String getSyslanguage(){
		return (String)getData().get("syslanguage");
	}	
		
		
	private String title;
		public void setTitle(String title){
		getData().put("title",title);
		this.title=title;
	}
	
	public String getTitle(){
		return (String)getData().get("title");
	}	
		
		
	private String uploadfilepath;
		public void setUploadfilepath(String uploadfilepath){
		getData().put("uploadfilepath",uploadfilepath);
		this.uploadfilepath=uploadfilepath;
	}
	
	public String getUploadfilepath(){
		return (String)getData().get("uploadfilepath");
	}	
		
		
	private String visitorconsult;
		public void setVisitorconsult(String visitorconsult){
		getData().put("visitorconsult",visitorconsult);
		this.visitorconsult=visitorconsult;
	}
	
	public String getVisitorconsult(){
		return (String)getData().get("visitorconsult");
	}	
		
		
	private String websitename;
		public void setWebsitename(String websitename){
		getData().put("websitename",websitename);
		this.websitename=websitename;
	}
	
	public String getWebsitename(){
		return (String)getData().get("websitename");
	}	
		
		
	private String websitestate;
		public void setWebsitestate(String websitestate){
		getData().put("websitestate",websitestate);
		this.websitestate=websitestate;
	}
	
	public String getWebsitestate(){
		return (String)getData().get("websitestate");
	}	
		
		
	private Long membericonId;
		public void setMembericonId(Long membericonId){
		getData().put("membericon_id",membericonId);
		this.membericonId=membericonId;
	}
	
	public Long getMembericonId(){
		return (Long)getData().get("membericon_id");
	}	
		
		
	private Long websitelogoId;
		public void setWebsitelogoId(Long websitelogoId){
		getData().put("websitelogo_id",websitelogoId);
		this.websitelogoId=websitelogoId;
	}
	
	public Long getWebsitelogoId(){
		return (Long)getData().get("websitelogo_id");
	}	
		
		
	private String qqLogin;
		public void setQqLogin(String qqLogin){
		getData().put("qq_login",qqLogin);
		this.qqLogin=qqLogin;
	}
	
	public String getQqLogin(){
		return (String)getData().get("qq_login");
	}	
		
		
	private String qqLoginId;
		public void setQqLoginId(String qqLoginId){
		getData().put("qq_login_id",qqLoginId);
		this.qqLoginId=qqLoginId;
	}
	
	public String getQqLoginId(){
		return (String)getData().get("qq_login_id");
	}	
		
		
	private String qqLoginKey;
		public void setQqLoginKey(String qqLoginKey){
		getData().put("qq_login_key",qqLoginKey);
		this.qqLoginKey=qqLoginKey;
	}
	
	public String getQqLoginKey(){
		return (String)getData().get("qq_login_key");
	}	
		
		
	private String qqDomainCode;
		public void setQqDomainCode(String qqDomainCode){
		getData().put("qq_domain_code",qqDomainCode);
		this.qqDomainCode=qqDomainCode;
	}
	
	public String getQqDomainCode(){
		return (String)getData().get("qq_domain_code");
	}	
		
		
	private String sinaDomainCode;
		public void setSinaDomainCode(String sinaDomainCode){
		getData().put("sina_domain_code",sinaDomainCode);
		this.sinaDomainCode=sinaDomainCode;
	}
	
	public String getSinaDomainCode(){
		return (String)getData().get("sina_domain_code");
	}	
		
		
	private String sinaLogin;
		public void setSinaLogin(String sinaLogin){
		getData().put("sina_login",sinaLogin);
		this.sinaLogin=sinaLogin;
	}
	
	public String getSinaLogin(){
		return (String)getData().get("sina_login");
	}	
		
		
	private String sinaLoginId;
		public void setSinaLoginId(String sinaLoginId){
		getData().put("sina_login_id",sinaLoginId);
		this.sinaLoginId=sinaLoginId;
	}
	
	public String getSinaLoginId(){
		return (String)getData().get("sina_login_id");
	}	
		
		
	private String sinaLoginKey;
		public void setSinaLoginKey(String sinaLoginKey){
		getData().put("sina_login_key",sinaLoginKey);
		this.sinaLoginKey=sinaLoginKey;
	}
	
	public String getSinaLoginKey(){
		return (String)getData().get("sina_login_key");
	}	
		
		
	private String imagewebserver;
		public void setImagewebserver(String imagewebserver){
		getData().put("imagewebserver",imagewebserver);
		this.imagewebserver=imagewebserver;
	}
	
	public String getImagewebserver(){
		return (String)getData().get("imagewebserver");
	}	
		
		
	private String serviceQqList;
		public void setServiceQqList(String serviceQqList){
		getData().put("service_qq_list",serviceQqList);
		this.serviceQqList=serviceQqList;
	}
	
	public String getServiceQqList(){
		return (String)getData().get("service_qq_list");
	}	
		
		
	private String serviceTelphoneList;
		public void setServiceTelphoneList(String serviceTelphoneList){
		getData().put("service_telphone_list",serviceTelphoneList);
		this.serviceTelphoneList=serviceTelphoneList;
	}
	
	public String getServiceTelphoneList(){
		return (String)getData().get("service_telphone_list");
	}	
		
		
	private String currencyCode;
		public void setCurrencyCode(String currencyCode){
		getData().put("currency_code",currencyCode);
		this.currencyCode=currencyCode;
	}
	
	public String getCurrencyCode(){
		return (String)getData().get("currency_code");
	}	
		
		
	private String websitecss;
		public void setWebsitecss(String websitecss){
		getData().put("websitecss",websitecss);
		this.websitecss=websitecss;
	}
	
	public String getWebsitecss(){
		return (String)getData().get("websitecss");
	}
	
	private AccessoryBO websiteLogo;// 网站logo
	
	private AccessoryBO memberIcon; // 默认用户图标
	
	

	public AccessoryBO getMemberIcon() {
		return memberIcon;
	}

	public void setMemberIcon(AccessoryBO memberIcon) {
		this.memberIcon = memberIcon;
	}

	public AccessoryBO getWebsiteLogo() {
		return websiteLogo;
	}

	public void setWebsiteLogo(AccessoryBO websiteLogo) {
		this.websiteLogo = websiteLogo;
	}
	
	
	
}

