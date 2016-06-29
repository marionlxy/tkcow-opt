<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link  href="<%=basePath%>/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<link  href="<%=basePath%>/resources/style/common/css/jquery-ui-1.8.22.custom.css" type=text/css rel=stylesheet>
<script src="<%=basePath%>/resources/js/jquery-1.6.2.js"></script>
<script src="<%=basePath%>/resources/js/jquery.validate.min.js"></script>
<script src="<%=basePath%>/resources/js/jquery.shop.common.js"></script>
<script src="<%=basePath%>/resources/js/jquery.poshytip.min.js"></script>
<script src="<%=basePath%>/resources/js/jquery-ui-1.8.21.js"></script>
<script src="<%=basePath%>/resources/js/jquery.zh.cn.js"></script>
<script>
jQuery(document).ready(function(){
  jQuery("#theForm").validate({
    rules:{
	 adTitle:{required:true},
	 adApId:{required:true},
	 adBeginTime:{required:true},
	 adEndTime:{required:true},
	 acc:{
	    accept:"${config.imagesuffix}"
	  }
	 },
    messages:{
	 adTitle:{required:"广告标题不能为空"},
	 adApId:{required:"广告位置不能为空"},
	 adBeginTime:{required:"开始时间不能为空"},
	 adEndTime:{required:"结束时间不能为空"},
	 acc:{
	    accept:"不允许的图片格式"
	  }
	}
  });
 	//改变系统提示的样式
  jQuery("span .w").mousemove(function(){
	var id=jQuery(this.parentNode).attr("id");
	if(id="nothis"){
	   jQuery(this.parentNode).attr("id","this")
	}
  }).mouseout(function(){
     var id=jQuery(this.parentNode).attr("id");
	 if(id="this"){
	   jQuery(this.parentNode).attr("id","nothis")
	 }
  });
  //
  jQuery("#logoShow").mouseover(function(){
	    jQuery("#logoImg").css('display','block');
   }).mouseout(function(){
		jQuery("#logoImg").css('display','none');
	});
  //
  jQuery("#acc").change(function(){
	 jQuery("#textfield1").val(jQuery("#acc").val());
   })
  //
  jQuery('#adBeginTime').attr("readyonly","readyonly").datepicker({
	  dateFormat:"yy-mm-dd",
	  changeMonth: true,
	  changeYear: true
  });
  jQuery('#adEndTime').attr("readyonly","readyonly").datepicker({
	  dateFormat:"yy-mm-dd",
	  changeMonth: true,
	  changeYear: true
  });
  //
  jQuery("#adApId").change(function(){
     var ap_type=jQuery(this).find('option:selected').attr("ap_type");
	 if(ap_type=="text"){
	   jQuery("#advert_text").show();
	   jQuery("#advert_img").hide();
	   jQuery("#advert_slide").hide();
	 }
	 if(ap_type=="slide"){
	   jQuery("#advert_img").show();
	   jQuery("#advert_slide").show();
	   jQuery("#advert_text").hide();
	 }
	 if(ap_type=="img"||ap_type=="scroll"){
	   jQuery("#advert_img").show();
	   jQuery("#advert_text").hide();
	   jQuery("#advert_slide").hide();
	 }
  });
  //
  jQuery("#adApId").val("${obj.ad_ap.id}");
   <c:if test="${obj.ad_ap.apType=='text'}">
	   jQuery("#advert_text").show();
	   jQuery("#advert_img").hide();
	   jQuery("#advert_slide").hide();
   </c:if>
   
   <c:if test="${obj.ad_ap.apType=='slide'}">
   	    jQuery("#advert_slide").show();
   </c:if>
   
   <c:if test="${obj.ad_ap.apType=='slide' || obj.ad_ap.apType=='img' || obj.ad_ap.apType=='scroll'}">
	   jQuery("#advert_img").show();
	   jQuery("#advert_text").hide();
	   jQuery("#advert_slide").hide();
    </c:if>
});
</script>
</head>
<body>
<div class="cont">
  <h1 class="seth1">广告管理</h1>
  <div class="settab">
  	<span class="tab-one"></span> 
  	<span class="tabs"> 
  		<a href="${webPath}/admin/advert_list.htm">所有广告</a>
  	   | <a href="${webPath}/admin/advert_add.htm" <c:if test="${empty edit}">class="this"</c:if>>新增广告</a>
  	   | <a href="${webPath}/admin/adv_pos_list.htm">所有广告位</a>
  	   | <a href="${webPath}/admin/adv_pos_add.htm">新增广告位</a>
  	   <c:if test="${not empty edit}">
  	   | <a href="javascript:void(0);" class="this">编辑</a>
  	   </c:if>
	 </span> 
	 <span class="tab-two"></span>
  </div>
  <form action="${webPath}/admin/advert_save.htm" method="post" enctype="multipart/form-data" id="theForm">
  <div class="setcont">
    <!--鼠标经过样式-->
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告名称</li>
      <li><span class="webname">
        <input name="adTitle" type="text" id="adTitle" value="${obj.adTitle}" />
        </span></li>
    </ul>
    <!--鼠标未经过样式-->
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告位置</li>
      <li>
        <span class="webnamesec sizese">
        <select name="adApId" id="adApId">
          <option value="">请选择所属广告位...</option>
          	<c:forEach var="ap" items="${aps}">
          		<option value="${ap.id}" ap_type="${ap.ap_type}">${ap.ap_title}(${ap.ap_width}*${ap.ap_height})</option>
          	</c:forEach>
        </select>
        </span>
       </li>
    </ul>
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>开始时间</li>
      <li><span class="webname">                                   
        <input name="adBeginTime" type="text" id="adBeginTime" value="<fmt:formatDate value="${obj.adBeginTime}" type="date"/>" />
        </span></li>
    </ul>
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>结束时间</li>
      <li>
        <span class="webname">                                     
         <input name="adEndTime" type="text" id="adEndTime" value="<fmt:formatDate value="${obj.adEndTime}" type="date"/>"/>
        </span>
      </li>
    </ul>
    <ul class="set1" id="advert_img" style="display:none;">
        <li>广告图片</li>
        <li> 
          <span class="size13" >
           <input name="textfield" type="text" id="textfield1" />
          </span> 
          <span class="filebtn">
            <input name="button" type="button" id="button1" value=""/>
          </span> 
          <span style="float:left;" class="file">
            <input name="acc" type="file" id="acc" class="file-text" size="30"/>
          </span> 
          <span class="preview"> 
            <img src="${webPath}/resources/style/system/manage/blue/images/preview.jpg" width="25" height="25"  id="logoShow" style="cursor:help"/> 
          </span>
          <c:if test="${empty obj.ad_acc}">
          	<span id="nothis"><strong class="q"></strong><strong class="w">暂无图片</strong><strong class="c"></strong></span>
          </c:if>
          <c:if test="${not empty obj.ad_acc}">
          	<div class="bigimgpre" id="logoImg" style="display:none;">
          	  <img src="${webPath}/${obj.ad_acc.path}/${obj.ad_acc.name}"  width="${obj.ad_acc.width}" height="${obj.ad_acc.height}" id="logoShow2"/> 
          	</div>
          </c:if>
        </li>
      </ul>
     <ul class="set1" id="advert_text" style="display:none;">
      <li>广告文字：</li>
      <li><span class="webname">
        <input name="adText" type="text" id="adText" value="${obj.adText}" />
        </span></li>
    </ul> 
     <ul class="set1" id="advert_url">
      <li>广告链接：</li>
      <li><span class="webname">
        <input name="adUrl" type="text" id="adUrl" value="${obj.adUrl}" />
        </span></li>
    </ul> 
    <ul class="set1" id="advert_slide" style="display:none;">
      <li>幻灯片序号：</li>
      <li>
        <span class="webname">
         <input name="adSlideSequence" type="text" id="adSlideSequence" value="${obj.adSlideSequence}" />
        </span>
      </li>
    </ul>  
  </div>
  <div class="submit">
    <input name="" type="submit" value="提交" style="cursor:pointer;" />
    <input name="add_url" type="hidden" id="add_url" value="${webPath}/admin/advert_add.htm" />
    <input name="list_url" type="hidden" id="list_url" value="${webPath}/admin/advert_list.htm" />
    <input name="id" type="hidden" id="id" <c:if test="${not empty obj.id}">value="${obj.id}"</c:if>/>
    <%-- <input name="page" type="hidden" id="page" value="${currentPage}"/> --%>
  </div>
  </form>
</div>
</body>
</html>
