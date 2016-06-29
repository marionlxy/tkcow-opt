<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
<script src="${webPath}/resources/js/jquery.shop.common.js"></script>
</head>
<body>
<div class="cont">
  <h1 class="seth1">广告管理</h1>
  <div class="settab">
  	<span class="tab-one"></span> 
  	<span class="tabs"> 
  		<a href="${webPath}/admin/advert_list.htm" class="this">所有广告</a>
  		| <a href="${webPath}/admin/advert_add.htm" >新增广告</a>
  		| <a href="${webPath}/admin/adv_pos_list.htm">所有广告位</a>
  		| <a href="${webPath}/admin/adv_pos_add.htm">新增广告位</a> 
  	</span> <span class="tab-two"></span>
  </div>
  <form method="post" id="queryForm">
	  <div class="orders">
	    <ul>
	      <li><span>广告名称</span> <span class="allmen size6">
	        <input name="ad_title" type="text" id="ad_title" />
	        </span><span class="btn_search">
	        <input name="" type="submit"  value="搜索" style="cursor:pointer;" />
	        </span></li>
	    </ul>
	  </div>
  </form>
  <form method="post" id="ListForm">
  <div class="allshop_table">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="yhj_table" style="font-size:12px">
      <tr style="background:#2A7AD2; height:30px; color:#FFF">
        <td width="21%">广告名称</td>
        <td  width="19%">所属广告位</td>
        <td  width="9%" align="center">类别</td>
        <td  width="8%" align="center">开始时间</td>
        <td  width="8%" align="center">结束时间</td>
        <td  width="12%" align="center"><span class="djl">点击数</span></td>
        <td  width="8%">广告状态</td>
        <td  align="center">操作</td>
      </tr>
      <c:forEach var="obj" items="${objs}">
      	<tr>
	        <td>${obj.ad_title}</td>
	        <td >${obj.ad_ap.apTitle}</td>
		        <c:if test="${obj.ad_ap.apType=='img'}">
		        	<c:set var="type" value="图片"/>
		        </c:if>
		        <c:if test="${obj.ad_ap.apType== 'scroll' }">
		        	<c:set var="type" value="滚动"/>
		        </c:if>
		        <c:if test="${obj.ad_ap.apType== 'slide' }">
		        	<c:set var="type" value="幻灯"/>
		        </c:if>
		        <c:if test="${obj.ad_ap.apType=='text'}">
		        	<c:set var="type" value="文字"/>
		        </c:if>
        
        	<td align="center">${type}</td>
	        <td align="center"><fmt:formatDate value="${obj.ad_begin_time}" type="date"/></td>
	        <td align="center"><fmt:formatDate value="${obj.ad_end_time}" type="date"/></td>
	        <td align="center" class="blue">${obj.ad_click_num}</td>
	        	
		        <c:if test="${obj.ad_status==0}">
		        	<c:set var="ad_status" value="待审核"/>
		        </c:if>
		        <c:if test="${obj.ad_status==1}">
		        	<c:set var="ad_status" value="审核通过"/>
		        </c:if>
		        <c:if test="${obj.ad_status==-1}">
		        	<c:set var="ad_status" value="审核失败"/>
		        </c:if>
	        <td >${ad_status}</td>
        	<td align="center" class="aoprater">
        		<a href="${webPath}/admin/advert_edit.htm?id=${obj.id}&page=${currentPage}" class="blue">编辑</a>
        		|<a href="javascript:void(0);" onclick="if(confirm('删除后不可恢复，是否继续?'))window.location.href='${webPath}/admin/advert_del.htm?mulitId=${obj.id}'" class="blue">删除</a>
        	</td>
        </tr>
      </c:forEach>
      <tr style="background:#F2F2F2; height:30px;">
        <td colspan="9">
         <div class="fenye3">
          <input name="ad_title" type="hidden" id="ad_title" value="${ad_title}" />
          <input type="hidden" name="mulitId" id="mulitId"/>
          <input name="page" type="hidden" id="page" value="${currentPage}" />
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
