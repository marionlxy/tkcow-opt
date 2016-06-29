<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
<script src="${webPath}/resources/js/jquery.validate.min.js"></script>
<script src="${webPath}/resources/js/jquery.shop.common.js"></script>
<script src="${webPath}/resources/js/jquery.poshytip.min.js"></script>
<script charset="utf-8" src="${webPath}/resources/editor/kindeditor.js"></script>
<script charset="utf-8" src="${webPath}/resources/editor/lang/zh_CN.js"></script>
<script>
jQuery(document).ready(function(){
  //设置默认状态以及判断初始显示状态
	var state = jQuery("#display").val();
	if(state==""){
		jQuery("#display").val("true");
		jQuery("#displayOff").hide();
	}else{
		 if(state=="true"){
			jQuery("#displayOff").hide();
		}else{
			jQuery("#displayOn").hide();
		}
	}	
  jQuery("#theForm").validate({
	ignore: "",						  
    rules:{
	  title:{
	    required :true
	  },
	  articleclassId:{required:true},
	  sequence:{number:true},
	  content:{required:true}/* ,
	  mark:{
		    remote:{
				   url: "${webPath}/admin/article_mark.htm",     //后台处理程序
                   type: "post",               //数据发送方式
                   dataType: "json",           //接受数据格式   
                   data: {                     //要传递的数据
                     "mark": function(){return jQuery("#mark").val();},
					 "id":"${obj.id}"
		           }
				}
		  } */
	 },
	messages:{
	  title:{required:"文章标题不能为空"},
	  articleclassId:{required:"文章分类不能为空"},
	  sequence:{number:"序号只能为数字"},
	  content:{required:"文章内容不能为空"},
	  /* mark:{remote:"文章标识已经存在"} */
	}
  });
  //
  editor = KindEditor.create('#content',options); 
  jQuery("#articleclassId").val("${obj.articleClass.id}");
  //
  jQuery("#display_img").click(function(){
    var display=jQuery("#display").val();
	if(display=="true"){
	  jQuery("#display_img").attr("src","${webPath}/resources/style/system/manage/blue/images/no.jpg");
	  jQuery("#display").val("false");
	}else{
	  jQuery("#display_img").attr("src","${webPath}/resources/style/system/manage/${config.websitecss}/images/yes.jpg");
	  jQuery("#display").val("true");
	}
  }); 	
});

function saveForm(method){
   jQuery("#theForm").submit();
}
</script>
</head>
<body>
<div class="cont">
  <h1 class="seth1">文章管理</h1>
  <div class="settab">
  	<span class="tab-one"></span> 
  	<span class="tabs"> 
  		<a href="${webPath}/admin/article_list.htm">管理</a> 
  	  | <a href="${webPath}/admin/article_add.htm" <c:if test="${empty edit}">class="this"</c:if> >新增</a> 
  	    <c:if test="${not empty edit}"><a href="javascript:void(0);" class="this">编辑</a></c:if>										   
  	</span> 
  	<span class="tab-two"></span>
  </div>
  <form name="theForm" id="theForm" action="${webPath}/admin/article_save.htm" method=post>
    <input name="id" type="hidden" id="id" value="${obj.id}" />
    <input name="currentPage" type="hidden" id="currentPage" value="${currentPage}" />
    <input name="list_url" type="hidden" id="list_url" value="${webPath}/admin/article_list.htm" />
    <input name="add_url" type="hidden" id="add_url" value="${webPath}/admin/article_add.htm" />
    <div class="setcont">
      <ul class="set1">
        <li><strong class="orange fontsize20">*</strong>文章标题</li>
        <li>
          <span class="webname">
          	<input name="title" type="text" id="title" value="${obj.title}" size="40" />
          </span>
        </li>
      </ul>
      <ul class="set1">
        <li><strong class="orange fontsize20">*</strong>文章分类</li>
        <li>
          <span class="webnamesec sizese">
          	<select name="articleclassId" id="articleclassId">
            	<option value="">请选择...</option>
            	<c:forEach var="ac" items="${acs}">
            	  <option value="${ac.id}">${ac.classname}</option>
            	  <c:forEach var="ac1" items="${ac.childs}">
            	  	<option value="${ac1.id}">&nbsp;&nbsp;${ac1.classname}</option>
            	  </c:forEach>
  				</c:forEach>
            </select>
          </span>
         </li>
      </ul>
	   <ul class="set1">
        <li>外部URL</li>
        <li>
        <span class="webname">
          <input name="url" type="text" id="url" value="${obj.url}" size="40" />
          </span><span id="nothis"><strong class="q"></strong><strong class="w">填写URL后，该文章链接会直接跳转到对应的URL，URL以http://开头</strong><strong class="c"></strong></span></li>
		  
      </ul>
      <ul class="set1">
        <li>显示</li>
		<li>
        <input name="display" id="display" type="hidden" value="${obj.display}" >
        
        <c:if test="${obj.display ==1}">
        	<c:set var="image_url" value="${webPath}/resources/style/system/manage/${config.websitecss}/images/yes.jpg"></c:set>
        </c:if>
        <c:if test="${obj.display ==0}">
        	<c:set var="image_url" value="${webPath}/resources/style/system/manage/${config.websitecss}/images/no.jpg"></c:set>
        </c:if>
        <img  src="${image_url}" width="61" height="23" id="display_img" style="cursor:pointer"/>
		</li>
      </ul>
      <ul class="set1">
        <li>标识</li>
        <li><span class="webname">
          <input name="mark" type="text" id="mark" value="${obj.mark}" size="40" />
          </span></li>
      </ul>
      <ul class="set1">
        <li>序号</li>
        <li><span class="webname">
          <input name="sequence" type="text" id="sequence" value="${obj.sequence}" size="40" />
          </span></li>
      </ul>
	  <ul class="set1">
        <li><strong class="orange fontsize20">*</strong>内容</li>
        <li>
          <span class="webname">
           <textarea name="content" cols="100"  rows="10" id="content">${obj.content}</textarea>
          </span>
        </li>
      </ul>
    </div>
  </form>
  <div class="submit">
    <input name="save" type="button" value="提交" onclick="saveForm()" />
  </div>
</div>
</body>
</html>
