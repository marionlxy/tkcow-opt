<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<link href="${webPath}/resources/style/common/css/overlay.css" type="text/css" rel="stylesheet" />
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
<script src="${webPath}/resources/js/jquery-ui-1.8.21.js"></script>
<script src="${webPath}/resources/js/jquery.shop.common.js"></script>
<script>
function advert_invoke(obj){
  var text=jQuery(obj).attr("url");
  alert(text);
}
</script>
</head>
<body>
<div class="cont">
  <h1 class="seth1">广告管理</h1>
  <div class="settab">
  	<span class="tab-one"></span> 
  	<span class="tabs"> <a href="${webPath}/admin/advert_list.htm">所有广告</a>
    | <a href="${webPath}/admin/advert_add.htm" >新增广告</a>
    | <a href="${webPath}/admin/adv_pos_list.htm" class="this">所有广告位</a>
    | <a href="${webPath}/admin/adv_pos_add.htm">新增广告位</a> 
   </span> 
   <span class="tab-two"></span>
  </div>
  <form method="post" id="queryForm">
  <div class="orders">
    <ul>
      <li></li>
    </ul>
  </div>
  </form>
  <form method="post" id="ListForm">
  <div class="allshop_table">
    <table width="98%" border="0" cellspacing="0" cellpadding="0" class="yhj_table" style="font-size:12px">
      <tr style="background:#2A7AD2;height:30px; color:#FFF">
        <td  width="25%">广告位名称</td>
        <td  width="8%">类别</td>
        <td  width="8%">宽度</td>
        <td  width="8%">高度</td>
        <td  width="10%">价格(金币/月)</td>
        <td  width="8%">广告类型</td>
        <td  width="8%">广告状态</td>
        <td  align="center">操作</td>
      </tr>
      <c:forEach var="obj" items="${objs}">
      	<tr>
        <td>${obj.ap_title}</td>
           <c:if test="${obj.ap_type=='img'}">
           	  <c:set var="type" value="图片"></c:set>
           </c:if>
           <c:if test="${obj.ap_type=='scroll'}">
           	  <c:set var="type" value="滚动"></c:set>
           </c:if>
           <c:if test="${obj.ap_type=='slide'}">
           		<c:set var="type" value="幻灯"></c:set>
           </c:if>
           <c:if test="${obj.ap_type=='text'}">
           		<c:set var="type" value="文字"></c:set>
           </c:if>
        <td>${type}</td>
        <td>${obj.ap_width}</td>
        <td>${obj.ap_height}</td>
        <td>${obj.ap_price}</td>
	        <c:if test="${obj.ap_show_type==0}">
	           		<c:set var="show_type" value="固定广告"></c:set>
	        </c:if>
	        <c:if test="${obj.ap_show_type==1}">
	           		<c:set var="show_type" value="随机广告"></c:set>
	        </c:if>
        <td>${show_type}</td>
           <c:if test="${obj.ap_status == 0}">
           		<c:set var="ap_status" value="关闭"></c:set>
           </c:if>
           <c:if test="${obj.ap_status == 1}">
           		<c:set var="ap_status" value="启用"></c:set>
           </c:if>
        <td >${ap_status}</td>
        <td  align="center" class="aoprater">
            <a href="${webPath}/admin/adv_pos_edit.htm?id=${obj.id}" class="blue">编辑</a>
            <%-- <c:if test="${obj.ap_sys_type==1}">
            |<a href="javascript:void(0);" onclick="if(confirm('广告位删除后，对应的广告也会同步删除，是否继续？'))window.location.href='${webPath}/admin/adv_pos_del.htm?mulitId=${obj.id}'" class="blue">删除</a>
            </c:if> --%>
        	|<a href="javascript:void(0);" onclick="advert_invoke(this);" url='<script src="${webPath}/advert_invoke.htm?id=${obj.id}"></script>' class="blue">调用代码</a>
        </td>
      	</tr>
      </c:forEach>
      
      <tr style="background:#F2F2F2; height:30px;">
        <td colspan="8">
        <div class="fenye3">
          <input name="ap_title" type="hidden" id="ap_title" value="${ap_title}" />
          <input name="currentPage" type="hidden" id="currentPage" value="${currentPage}" />
          ${gotoPageFormHTML}
        </div>
        </td>
      </tr>
    </table>
  </div>
  </form>
</div>
</body>
</html>
