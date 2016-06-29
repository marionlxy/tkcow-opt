<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
<script src="${webPath}/resources/js/jquery.validate.min.js"></script>
<script>
jQuery(document).ready(function(){
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
//标志图片鼠标经过显示
jQuery("#brandImgShow").mouseover(function(){
	jQuery("#brandImg").show();
})
jQuery("#brandImgShow").mouseout(function(){
	jQuery("#brandImg").hide();
})
jQuery("#theForm").validate({
    rules:{
	  title:{
	    required :true
	  },
	  url:{
	    required :true,
		url:true
	  },
	  image:{
	    accept:"${config.imagesuffix}"
	  }
	 },
	messages:{
	  title:{required:"标题不能为空"},
	  url:{required:"URL不能为空",url:"URL格式不对"},
	  image:{accept:"系统不允许上传该文件类型"}
	}
  });
//结束
});

//样式
jQuery(function(){
    var textButton="<input type='text' name='textfield' id='textfield1' class='size13' /><input type='button' name='button' id='button1' value='' class='filebtn' />"
	jQuery(textButton).insertBefore("#image");
	jQuery("#image").change(function(){
	jQuery("#textfield1").val(jQuery("#image").val());
	})
});	
//保存
function saveForm(){
	jQuery("#theForm").submit();
}
</script>
<body>
<form action="${webPath}/admin/partner_save.htm" method="post" enctype="multipart/form-data" name="theForm" id="theForm">
  <input name="id" id="id" type="hidden" value="${obj.id}"/>
  <input name="list_url" type="hidden" id="list_url" value="${webPath}/admin/partner_list.htm?currentPage=${currentPage}"/>
  <input name="add_url" type="hidden" id="add_url" value="${webPath}/admin/partner_add.htm" />
  <div class="cont">
    <h1 class="seth1">合作伙伴</h1>
    <div class="settab"> 
	<span class="tab-one"></span> <span class="tabs"> 
	<a href="${webPath}/admin/partner_list.htm">管理</a> | 
											   
	<a href="${webPath}/admin/partner_add.htm" <c:if test="${empty edit}">class="this"</c:if>>新增</a> 
	<c:if test="${not empty edit}">
	   <a href="javascript:void(0);" class="this">编辑</a>
	</c:if>
	</span> 
	<span class="tab-two"></span> 
	</div>
    <div class="setcont" id="base">
      <!--鼠标经过样式-->
      <ul class="set1">
        <li><strong class="orange fontsize20">*</strong>标题</li>
        <li><span class="webname">
          <input name="title" type="text" id="title" value="${obj.title}" />
        </span></li>
      </ul> 
	  <ul class="set1">
        <li><strong class="orange fontsize20">*</strong>链接</li>
        <li><span class="webname">
          <input name="url" type="text" id="url" value="${obj.url}" />
        </span></li>
		 <span id="nothis"><strong class="q"></strong><strong class="w">合作伙伴的链接网址，填写时候需要带http://</strong><strong class="c"></strong></span>
      </ul> 
      <ul class="set1">
        <li>图片链接</li>
        <li>
	      <span class="size13" ><input name="textfield" type="text" id="textfield1" />
	      </span>
		  <span class="filebtn"><input name="button" type="button" id="button1" value=""/>
		  </span>
		  <span style="float:left;" class="file" >
		  <input name="image" type="file" id="image" size="30"/>
		  </span>
		  <span class="preview">
			  <c:if test="${not empty obj.imageAccessory}">
			  	<img src="${webPath}/resources/style/system/manage/blue/images/preview.jpg" width="25" height="25" id="brandImgShow"/>
			  </c:if> 
		  </span>
	      <div class="bigimgpre" id="brandImg" style="display:none;">
	         <img src="${webPath}/${obj.imageAccessory.path}/${obj.imageAccessory.name}"/>
	      </div>
	     </li>
      </ul>
      <ul class="set1">
        <li>排序</li>
        <li><span class="webname">
          <input name="sequence" type="text" id="sequence" value="${obj.sequence}" reg="^-?\d+$" tip="只能为整数"/>
        </span></li>
		<span id="nothis"><strong class="q"></strong><strong class="w">合作伙伴的链接序号，序号越小显示越靠前</strong><strong class="c"></strong></span>
      </ul>
    </div>
     <div class="setcont" id="qq" style="display:none;"> <img src="${webPath}/resources/style/system/manage/blue/images/zwtp.jpg" width="400px" height="200px"/> </div>
  </div>
  <div class="submit">
    <input name="" type="button" value="提交" onclick="saveForm();"/>
  </div>
</form>
</body>
</html>
