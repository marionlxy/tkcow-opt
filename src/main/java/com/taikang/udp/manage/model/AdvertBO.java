package com.taikang.udp.manage.model;

import java.util.Date;
import java.sql.Timestamp;
import java.lang.Long;
import java.lang.Integer;
import java.lang.String;
import java.util.Arrays;

import com.taikang.udp.accessory.model.AccessoryBO;
import com.taikang.udp.framework.common.datastructre.impl.BaseBO;


/**
  * AdvertBO 
  */

@SuppressWarnings("unused")
public class AdvertBO  extends BaseBO {
	
	private static final long serialVersionUID = 1L;
	
	public AdvertBO(){
		init();
	}
	
	protected void init(){
		super.init();
		this.addList(Arrays.asList("id","addtime","modifytime","modifier","deletestatus","ad_begin_time","ad_click_num","ad_end_time","ad_gold","ad_slide_sequence","ad_status","ad_text","ad_title","ad_url","ad_acc_id","ad_ap_id","ad_user_id"));
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
		
		
	private Date adBeginTime;
		public void setAdBeginTime(Date adBeginTime){
		getData().put("ad_begin_time",adBeginTime);
		this.adBeginTime=adBeginTime;
	}
	
	public Date getAdBeginTime(){
		return (Date)getData().get("ad_begin_time");
	}	
		
		
	private Integer adClickNum;
		public void setAdClickNum(Integer adClickNum){
		getData().put("ad_click_num",adClickNum);
		this.adClickNum=adClickNum;
	}
	
	public Integer getAdClickNum(){
		return (Integer)getData().get("ad_click_num");
	}	
		
		
	private Date adEndTime;
		public void setAdEndTime(Date adEndTime){
		getData().put("ad_end_time",adEndTime);
		this.adEndTime=adEndTime;
	}
	
	public Date getAdEndTime(){
		return (Date)getData().get("ad_end_time");
	}	
		
		
	private Integer adGold;
		public void setAdGold(Integer adGold){
		getData().put("ad_gold",adGold);
		this.adGold=adGold;
	}
	
	public Integer getAdGold(){
		return (Integer)getData().get("ad_gold");
	}	
		
		
	private Integer adSlideSequence;
		public void setAdSlideSequence(Integer adSlideSequence){
		getData().put("ad_slide_sequence",adSlideSequence);
		this.adSlideSequence=adSlideSequence;
	}
	
	public Integer getAdSlideSequence(){
		return (Integer)getData().get("ad_slide_sequence");
	}	
		
		
	private Integer adStatus;
		public void setAdStatus(Integer adStatus){
		getData().put("ad_status",adStatus);
		this.adStatus=adStatus;
	}
	
	public Integer getAdStatus(){
		return (Integer)getData().get("ad_status");
	}	
		
		
	private String adText;
		public void setAdText(String adText){
		getData().put("ad_text",adText);
		this.adText=adText;
	}
	
	public String getAdText(){
		return (String)getData().get("ad_text");
	}	
		
		
	private String adTitle;
		public void setAdTitle(String adTitle){
		getData().put("ad_title",adTitle);
		this.adTitle=adTitle;
	}
	
	public String getAdTitle(){
		return (String)getData().get("ad_title");
	}	
		
		
	private String adUrl;
		public void setAdUrl(String adUrl){
		getData().put("ad_url",adUrl);
		this.adUrl=adUrl;
	}
	
	public String getAdUrl(){
		return (String)getData().get("ad_url");
	}	
		
		
	private Long adAccId;
		public void setAdAccId(Long adAccId){
		getData().put("ad_acc_id",adAccId);
		this.adAccId=adAccId;
	}
	
	public Long getAdAccId(){
		return (Long)getData().get("ad_acc_id");
	}	
		
		
	private Long adApId;
		public void setAdApId(Long adApId){
		getData().put("ad_ap_id",adApId);
		this.adApId=adApId;
	}
	
	public Long getAdApId(){
		return (Long)getData().get("ad_ap_id");
	}	
		
		
	private Long adUserId;
		public void setAdUserId(Long adUserId){
		getData().put("ad_user_id",adUserId);
		this.adUserId=adUserId;
	}
	
	public Long getAdUserId(){
		return (Long)getData().get("ad_user_id");
	}
	
	private AccessoryBO ad_acc;// 广告图片
	
	private AdvertPositionBO ad_ap;//广告位 
	
	public AdvertPositionBO getAd_ap() {
		return ad_ap;
	}

	public void setAd_ap(AdvertPositionBO ad_ap) {
		this.ad_ap = ad_ap;
	}

	public AccessoryBO getAd_acc() {
		return ad_acc;
	}
	public void setAd_acc(AccessoryBO ad_acc) {
		this.ad_acc = ad_acc;
	}
}

