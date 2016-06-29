package com.taikang.udp.manage.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * AdvertPositionBO 
  */
@SuppressWarnings("unused")
public class AdvertPositionBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public AdvertPositionBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","addtime","modifytime","modifier","deletestatus","ap_acc_url","ap_code","ap_content","ap_height","ap_price","ap_sale_type","ap_show_type","ap_status","ap_sys_type","ap_text","ap_title","ap_type","ap_use_status","ap_width","ap_acc_id"));
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
		
		
	private String apAccUrl;
		public void setApAccUrl(String apAccUrl){
		getData().put("ap_acc_url",apAccUrl);
		this.apAccUrl=apAccUrl;
	}
	
	public String getApAccUrl(){
		return (String)getData().get("ap_acc_url");
	}	
		
		
	private String apCode;
		public void setApCode(String apCode){
		getData().put("ap_code",apCode);
		this.apCode=apCode;
	}
	
	public String getApCode(){
		return (String)getData().get("ap_code");
	}	
		
		
	private String apContent;
		public void setApContent(String apContent){
		getData().put("ap_content",apContent);
		this.apContent=apContent;
	}
	
	public String getApContent(){
		return (String)getData().get("ap_content");
	}	
		
		
	private Integer apHeight;
		public void setApHeight(Integer apHeight){
		getData().put("ap_height",apHeight);
		this.apHeight=apHeight;
	}
	
	public Integer getApHeight(){
		return (Integer)getData().get("ap_height");
	}	
		
		
	private Integer apPrice;
		public void setApPrice(Integer apPrice){
		getData().put("ap_price",apPrice);
		this.apPrice=apPrice;
	}
	
	public Integer getApPrice(){
		return (Integer)getData().get("ap_price");
	}	
		
		
	private Integer apSaleType;
		public void setApSaleType(Integer apSaleType){
		getData().put("ap_sale_type",apSaleType);
		this.apSaleType=apSaleType;
	}
	
	public Integer getApSaleType(){
		return (Integer)getData().get("ap_sale_type");
	}	
		
		
	private Integer apShowType;
		public void setApShowType(Integer apShowType){
		getData().put("ap_show_type",apShowType);
		this.apShowType=apShowType;
	}
	
	public Integer getApShowType(){
		return (Integer)getData().get("ap_show_type");
	}	
		
		
	private Integer apStatus;
		public void setApStatus(Integer apStatus){
		getData().put("ap_status",apStatus);
		this.apStatus=apStatus;
	}
	
	public Integer getApStatus(){
		return (Integer)getData().get("ap_status");
	}	
		
		
	private Integer apSysType;
		public void setApSysType(Integer apSysType){
		getData().put("ap_sys_type",apSysType);
		this.apSysType=apSysType;
	}
	
	public Integer getApSysType(){
		return (Integer)getData().get("ap_sys_type");
	}	
		
		
	private String apText;
		public void setApText(String apText){
		getData().put("ap_text",apText);
		this.apText=apText;
	}
	
	public String getApText(){
		return (String)getData().get("ap_text");
	}	
		
		
	private String apTitle;
		public void setApTitle(String apTitle){
		getData().put("ap_title",apTitle);
		this.apTitle=apTitle;
	}
	
	public String getApTitle(){
		return (String)getData().get("ap_title");
	}	
		
		
	private String apType;
		public void setApType(String apType){
		getData().put("ap_type",apType);
		this.apType=apType;
	}
	
	public String getApType(){
		return (String)getData().get("ap_type");
	}	
		
		
	private Integer apUseStatus;
		public void setApUseStatus(Integer apUseStatus){
		getData().put("ap_use_status",apUseStatus);
		this.apUseStatus=apUseStatus;
	}
	
	public Integer getApUseStatus(){
		return (Integer)getData().get("ap_use_status");
	}	
		
		
	private Integer apWidth;
		public void setApWidth(Integer apWidth){
		getData().put("ap_width",apWidth);
		this.apWidth=apWidth;
	}
	
	public Integer getApWidth(){
		return (Integer)getData().get("ap_width");
	}	
		
		
	private Long apAccId;
		public void setApAccId(Long apAccId){
		getData().put("ap_acc_id",apAccId);
		this.apAccId=apAccId;
	}
	
	public Long getApAccId(){
		return (Long)getData().get("ap_acc_id");
	}
	
	private AccessoryBO ap_acc;// 广告默认图片，图片广告

	public AccessoryBO getAp_acc() {
		return ap_acc;
	}

	public void setAp_acc(AccessoryBO ap_acc) {
		this.ap_acc = ap_acc;
	}
	
	private List<AdvertBO> advs = new ArrayList<AdvertBO>();// 广告位对应的广告
	
	private Long adv_id;
	

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public List<AdvertBO> getAdvs() {
		return advs;
	}

	public void setAdvs(List<AdvertBO> advs) {
		this.advs = advs;
	}
	
}

