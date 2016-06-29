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
//
  jQuery("#theForm").validate({
    rules:{
	  title:{
	    required :true
	  }
	 },
	messages:{
	  title:{required:"导航标题不能为空"}
	}
  });
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
  //
  jQuery("#new_win_img").click(function(){
    var new_win=jQuery("#new_win").val();
	if(new_win=="1"){
	  jQuery("#new_win_img").attr("src","${webPath}/resources/style/system/manage/blue/images/no.jpg");
	  jQuery("#newWin").val("0");
	}else{
	  jQuery("#new_win_img").attr("src","${webPath}/resources/style/system/manage/${config.websitecss}/images/yes.jpg");
	  jQuery("#newWin").val("1");
	}
  }); 	  
  //
  jQuery(":radio[id='type']").click(function(){
      var type=jQuery(":radio[id='type'][checked=true]").val();
      jQuery("select").hide();
      jQuery("#"+type+"_id").show();
	  if(type!="diy"){
	    jQuery("#url_set").hide();
	  }else  jQuery("#url_set").show();
	  if(type=="sparegoods"){
		  jQuery("#url_set").show();
		  jQuery("#ul_place").hide();
		  }
  });
  //
  jQuery(":radio[id='type'][value=${obj.type}]").attr("checked","checked");
  jQuery(":radio[id=location][value='${obj.location}']").attr("checked",true);
  
  <c:if test="${obj.type=='articleclass'}">
  	jQuery("#articleclass_id").show();
	jQuery("#articleclass_id").val("${obj.typeId}");
  </c:if>  
});

function saveForm(){
 var type=jQuery(":radio[id='type'][checked=true]").val();
 var type_id=jQuery("#"+type+"_id");
 var type_id = type_id.val();
 if(type_id)
   jQuery("#typeId").val(typeId);
 jQuery("#theForm").submit();
}
</script>
</head>
<body>
<div class="cont">
  <h1 class="seth1">页面导航</h1>
  <div class="settab">
  	<span class="tab-one"></span> 
  	<span class="tabs"> <a href="${webPath}/admin/navigation_list.htm">管理</a> |  
  	<a href="${webPath}/admin/navigation_add.htm" <c:if test="${empty edit}">class="this"</c:if>>新增</a>
  	<c:if test="${not empty edit}"><a href="javascript:void(0);" class="this">编辑</a></c:if> 
  	</span> <span class="tab-two"></span>
  </div>
  <form name="theForm" id="theForm" action="${webPath}/admin/navigation_save.htm" method=post>
    <input name="id" type="hidden" id="id" value="${obj.id}" />
    <input name="currentPage" type="hidden" id="currentPage" value="${currentPage}" />
    <input name="list_url" type="hidden" id="list_url" value="${webPath}/admin/navigation_list.htm" />
    <input name="add_url" type="hidden" id="add_url" value="${webPath}/admin/navigation_add.htm" />
    <div class="setcont">
      <ul class="set1">
        <li><strong class="orange fontsize20">*</strong>导航类型
          <input name="typeId" type="hidden" id="typeId" value="${obj.typeId}" />
        </li>
        <li>
          <input name="type" id="type" type="radio" value="diy" style="border:none;width:16px;height:16px;"/>
                                  自定义类型
         </li>
        
        <li>
          <input name="type" id="type" type="radio" style="border:none;width:16px;height:16px;" value="articleclass"/>
          文章导航
          <select name="articleclass_id" id="articleclass_id"  style="display:none;">
	          <c:forEach var="ac" items="${acs}">
	          	 <option value="${ac.id}">${ac.className}</option>
	          	 
	          	 <c:forEach var="ac1" items="${ac.childs}">
	          		<option value="${ac1.className}">&nbsp;&nbsp;${ac1.className}</option>
		         </c:forEach> 
	          	 
			  </c:forEach>  
          </select>
        </li>
      </ul>
      <ul class="set1">
        <li><strong class="orange fontsize20">*</strong>导航标题</li>
        <li><span class="webname">
          <input name="title" type="text" id="title" value="${obj.title}" size="40" />
          </span></li>
      </ul>
      <ul class="set1" id="url_set">
        <li>导航URL</li>
        <li><span class="webname">																
          <input name="url" type="text" id="url" value="${obj.url}" size="40" <c:if test="obj.sysNav == 1">readonly="readonly"</c:if> />
          站外导航请加http://
        </span></li>
      </ul>
      <ul class="set1" id="ul_place">
        <li>导航位置</li>
        <li>
          <input id="location" name="location" type="radio" style="border:none;width:16px;height:16px;" value="-1"/>
          顶部
          <input name="location" type="radio" id="location" style="border:none;width:16px;height:16px;" value="0"/>
          中间
          <input id="location" name="location" type="radio" style="border:none;width:16px;height:16px;" value="1"/>
          底部 
          </li>
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
        	<img src="${image_url}" width="61" height="23" id="display_img" style="cursor:pointer"/> 
        </li>
      </ul>
      <ul class="set1">
        <li>新窗口打开</li>
        <li>
          <input name="newWin" id="newWin" type="hidden" value="${obj.newWin}" >
          
            <c:if test="${obj.newWin ==1}">
        	 <c:set var="image_url" value="${webPath}/resources/style/system/manage/${config.websitecss}/images/yes.jpg"></c:set>
           </c:if>
           <c:if test="${obj.newWin ==0}">
        	<c:set var="image_url" value="${webPath}/resources/style/system/manage/${config.websitecss}/images/no.jpg"></c:set>
       	   </c:if>
        	<img src="${image_url}" width="61" height="23" id="new_win_img" style="cursor:pointer"/> 
          </li>
      </ul>
      <ul class="set1">
        <li>序号</li>
        <li><span class="webname">
          <input name="sequence" type="text" id="sequence" value="${obj.sequence}" size="40"/>
          </span></li>
      </ul>
    </div>
  </form>
  <div class="submit">
    <input name="save" type="button" value="提交" onclick="saveForm()" />
  </div>
</div>
</body>
</html>
