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
<script src="${webPath}/resources/js/jquery.shop.common.js"></script>
<script src="${webPath}/resources/js/jquery.poshytip.min.js"></script>
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
jQuery("#parentId").val("${obj.parent.id}");
//
  jQuery("#theForm").validate({
    rules:{
	  className:{
	    required :true
	  }
	 },
	messages:{
	  className:{required:"分类名称不能为空"}
	}
  });	
});

function saveForm(method){
   jQuery("#theForm").submit();
}


</script>
<body>
<div class="cont">
  <h1 class="seth1">文章分类</h1>
  <div class="settab"><span class="tab-one"></span>
      <span class="tabs">
       <a href="${webPath}/admin/articleclass_list.htm">管理</a> |
       												   
       <a href="${webPath}/admin/articleclass_add.htm" <c:if test="${empty edit}">class="this"</c:if>>新增</a> 
       <c:if test="${not empty edit}">
	   	<a href="javascript:void(0);" class="this">编辑</a>
	   </c:if>
    </span>
      
      <span class="tab-two"></span></div>
 <form name="theForm" id="theForm" action="${webPath}/admin/articleclass_save.htm" method="post">
 <input name="id" type="hidden" id="id" value="${obj.id}" />
  <input name="currentPage" type="hidden" id="currentPage" value="${currentPage}" />
  <input name="list_url" type="hidden" id="list_url" value="${webPath}/admin/articleclass_list.htm" />
  <input name="add_url" type="hidden" id="add_url" value="${webPath}/admin/articleclass_add.htm" />
  <div class="setcont">
    <!--鼠标经过样式-->
    <ul class="set1">
      <li><strong class="orange fontsize20">*</strong>分类名称</li>
      <li><span class="webname">
        <input name="classname" type="text" id="classname" value="${obj.classname}" size="40" />
      </span></li>
    </ul>
    <!--鼠标未经过样式-->
    <ul class="set1">
      <li>上级分类</li>
      <li><span class="webnamesec sizese">
        <select name="parentId" id="parentId">
              <option value="">请选择...</option>
              <c:forEach var="ac" items="${acs}">
              	 <option value="${ac.id}">${ac.classname}</option>
              </c:forEach>
         </select>
      </span><span id="nothis"><strong class="q"></strong><strong class="w">如果选择上级分类，那么新增的分类则为被选择上级分类的子分类</strong><strong class="c"></strong></span></li>
    </ul>
	<ul class="set1">
      <li>排序</li>
      <li><span class="webname">
        <input name="sequence" type="text" id="sequence" value="${obj.sequence}"/>
      </span><span id="nothis"><strong class="q"></strong><strong class="w">序号越小，分类显示越靠前</strong><strong class="c"></strong></span></li>
    </ul>
    </div>
  </form>
  <div class="submit">
    <input name="save" type="button" value="提交" onclick="saveForm()" />
  </div>
</div>
</body>
</html>