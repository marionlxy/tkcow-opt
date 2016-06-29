<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<link  href="${webPath}/resources/style/common/css/jquery-ui-1.8.22.custom.css" type=text/css rel=stylesheet>
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
<script src="${webPath}/resources/js/jquery.validate.min.js"></script>
<script src="${webPath}/resources/js/jquery.shop.common.js"></script>
<script src="${webPath}/resources/js/jquery.poshytip.min.js"></script>
<script src="${webPath}/resources/js/jquery-ui-1.8.21.js"></script>
<script src="${webPath}/resources/js/jquery.zh.cn.js"></script>
<script>
jQuery(document).ready(function(){
  jQuery("#theForm").validate({
    rules:{
	 ap_title:{required:true},
	 ap_content:{required:true},
	 ap_price:{required:true,digits:true},
	 ap_width:{required:true,digits:true},
	 ap_height:{required:true,digits:true},
	 acc:{
	    accept:"${config.imagesuffix}"
	  }
	 },
    messages:{
	 ap_title:{required:"广告标题不能为空"},
	 ap_content:{required:"广告简介不能为空"},
	 ap_price:{required:"广告价格不能为空",digits:"广告价格只能为整数"},
	 ap_width:{required:"广告宽度不能为空",digits:"广告宽度只能为整数"},
	 ap_height:{required:"广告高度不能为空",digits:"广告高度只能为整数"},
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
  jQuery("#logoShow").mouseover(function(){
	    jQuery("#logoImg").css('display','block');
   }).mouseout(function(){
		jQuery("#logoImg").css('display','none');
	});
  jQuery("#acc").change(function(){
	 jQuery("#textfield1").val(jQuery("#acc").val());
   })
  jQuery("#ap_type").val("${obj.apType}");
  jQuery(":radio[id=apSysType][value=${obj.apSysType}]").attr("checked","checked");
  jQuery(":radio[id=apShowType][value=${obj.apShowType}]").attr("checked",true); 
  jQuery(":radio[id=apStatus][value=${obj.apStatus}]").attr("checked",true); 
  <c:choose>
	<c:when test="${obj.apType=='text'}">
		jQuery("#advertText").show();
		jQuery("#advertImg").hide();
	</c:when>
	<c:otherwise>
		jQuery("#advertText").hide();
		jQuery("#advertImg").show();
	</c:otherwise>
  </c:choose>
  jQuery("#apType").change(function(){
    var type=jQuery(this).val();
	if(type=="text"){
	  jQuery("#advertText").show();
	  jQuery("#advertImg").hide();
	}else{
	  jQuery("#advertText").hide();
	  jQuery("#advertImg").show();	  
	}
  });
});

function saveForm(){
	jQuery("#theForm").submit();
}
</script>
</head>
<body>
<div class="cont">
  <h1 class="seth1">广告管理</h1>
  <div class="settab">
  	<span class="tab-one"></span> 
  	<span class="tabs"> 
  	   <a href="${webPath}/admin/advert_list.htm">所有广告</a>
  	 | <a href="${webPath}/admin/advert_add.htm" >新增广告</a>
  	 | <a href="${webPath}/admin/adv_pos_list.htm">所有广告位</a>
  	 | <a href="${webPath}/admin/adv_pos_add.htm" <c:if test="${empty edit}">class="this"</c:if>>新增广告位</a> 
  	 <c:if test="${not empty edit}">
	 | <a href="javascript:void(0);" class="this">编辑</a>
	 </c:if>
	 </span> 
	 <span class="tab-two"></span>
  </div>
  <form action="${webPath}/admin/adv_pos_save.htm" method="post" enctype="multipart/form-data" id="theForm">
  <div class="setcont">
    <!--鼠标经过样式-->
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告位名称</li>
      <li>
        <span class="webname">
         <input name="apTitle" type="text" id="apTitle" value="${obj.apTitle}" />
        </span>
      </li>
    </ul>
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告位简介</li>
      <li>
       <span class="webtj">
        <textarea name="apContent" cols="" rows="" id="apContent">${obj.apContent}</textarea>
       </span>
      </li>
    </ul>
    <!--鼠标未经过样式-->
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告位类别</li>
      <li><span class="webnamesec sizese">
        <select name="apType" id="apType">
          <option value="img">图片</option>
          <option value="slide">幻灯</option>
          <option value="scroll">滚动</option>
          <option value="text">文字</option>
        </select>
        </span><span id="nothis"><strong class="q"></strong><strong class="w">选择此广告位广告的形式</strong><strong class="c"></strong>
        </span>
       </li>
    </ul>
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告位价格</li>
      <li><span class="webname">
        <input name="apPrice" type="text" id="apPrice" value="${obj.apPrice}" />
        </span><span id="nothis"><strong class="q"></strong><strong class="w">金币/月</strong><strong class="c"></strong></span>
       </li>
    </ul>
    <ul class="set4">
      <li>系统广告</li>
      <li class="radiosp"><span>
        <input name="apSysType" id="apSysType" type="radio" value="0" />
        </span><span>是</span><span>
        <input name="apSysType" id="apSysType" type="radio" value="1" checked="checked" />
        </span><span>否</span><span id="nothis"><strong class="q"></strong><strong class="w">系统广告不可删除，主要处理商城预留广告位</strong><strong class="c"></strong></span></li>
    </ul>
    <ul class="set4">
      <li>是否启用</li>
      <li class="radiosp"><span>
        <input name="apStatus"  id="apStatus" type="radio" value="1" checked="checked"/>
        </span><span>是</span><span>
        <input name="apStatus" id="apStatus" type="radio" value="0"  />
        </span><span>否</span></li>
    </ul>
    <ul class="set4">
      <li>展示方式</li>
      <li  class="radiosp"><label><span>
        <input name="apShowType" id="apShowType" type="radio" value="1" checked="checked"/>
        </span><span>可以发布多条广告并随机展示</span></label></li>
      <li class="radiosp"><label><span>
        <input name="apShowType" id="apShowType" type="radio" value="0"  />
        </span><span>只允许发布并展示一条广告</span></label></li>
    </ul>
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告位宽度</li>
      <li><span class="webname">
        <input name="apWidth" type="text" id="apWidth" value="${obj.apWidth}" />
        </span></li>
    </ul>
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>广告位高度</li>
      <li><span class="webname">
        <input name="apHeight" type="text" id="apHeight" value="${obj.apHeight}" />
        </span></li>
    </ul>
    <ul class="set1" id="advert_img">
        <li>广告位默认图片</li>
        <li> 
          <span class="size13" >
          	<input name="textfield" type="text" id="textfield1"/>
          </span> 
          <span class="filebtn">
          	<input name="button" type="button" id="button1" value=""/>
          </span> 
          <span style="float:left;" class="file">
             <input name="acc" type="file" id="acc" class="file-text" size="30"/>
          </span> 
          <span class="preview"> 
          	<img src="${webPath}/resources/style/system/manage/blue/images/preview.jpg" width="25" height="25"  id="logoShow" style="cursor:help"/></span>
          	<c:if test="${empty obj.ap_acc}">
          		<span id="nothis"><strong class="q"></strong><strong class="w">暂无图片</strong><strong class="c"></strong></span>
          	</c:if>
          	<c:if test="${not empty obj.ap_acc}">
          		<div class="bigimgpre" id="logoImg" style="display:none;">
          			<img src="${webPath}/${obj.ap_acc.path}/${obj.ap_acc.name}"  width="${obj.apWidth}" height="${obj.apHeight}" id="logoShow2"/> 
          		</div>
          	</c:if>
        </li>
      </ul>
     <ul class="set1" id="advertText" style="display:none;">
      <li>默认文字：</li>
      <li>
        <span class="webname">
        <input name="apText" type="text" id="apText" value="${obj.apText}" />
        </span>
      </li>
    </ul> 
    <ul class="set1">
      <li>默认链接：</li>
      <li><span class="webname">
        <input name="apAccUrl" type="text" id="apAccUrl" value="${obj.apAccUrl}" />
        </span></li>
     </ul> 
  </div>
  <div class="submit">
    <input name="" type="button" value="提交" onclick="saveForm();" />
    <input name="add_url" type="hidden" id="add_url" value="${webPath}/admin/adv_pos_add.htm" />
    <input name="list_url" type="hidden" id="list_url" value="${webPath}/admin/adv_pos_list.htm" />
    <input name="id" type="hidden" id="id" value="${obj.id}" />
  </div>
  </form>
</div>
</body>
</html>
