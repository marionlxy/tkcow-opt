<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
<script src="${webPath}/resources/js/jquery.shop.common.js"></script>
<body>
<div class="cont">
  <h1 class="seth1">合作伙伴</h1>
  <div class="settab"><span class="tab-one"></span> <span class="tabs"> <a href="${webPath}/admin/partner_list.htm"  class="this">管理</a> | <a href="${webPath}/admin/partner_add.htm">新增</a> </span> <span class="tab-two"></span></div>
  <form name="queryForm" id="queryForm" action="" method="post">
  <div class="allmem_search">
    <ul>
      <li> <span>标题</span> <span class="allmen size4">
        <input name="title" type="text" id="title" />
        </span> <span class="btn_search">
        <input name="" type="submit"  value="搜索" style="cursor:pointer;"/>
        </span> </li>
    </ul>
  </div>
  </form>
  <div class="brandtable">
  							 
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="friend_table" style="font-size:12px">
      <tr style="background:#2A7AD2; height:30px; color:#FFF">
        <td width="71">&nbsp;</td>
        <td width="149">排序</td>
        <td width="327">标题</td>
        <td  width="217">图片标识</td>
        <td width="398" >链接</td>
        <td width="232" align="left">操作</td>
      </tr>
	  
	  <c:forEach var="obj" items="${objs}">
		  	<tr>
	        <td ><input name="checkbox" type="checkbox" id="checkbox" value="${obj.id}" style="border:none; width:16px; height:16px;"/></td>
	        <td><span class="pxnum size5">${obj.sequence}</span></td>
	        <td><span class="pxnum size7">${obj.title}</span></td>
	        <td>
	         <c:choose>
	  			<c:when test="${not empty obj.imageAccessory}">
	  				<img src="${webPath}/${obj.imageAccessory.path}/${obj.imageAccessory.name}" width="92" height="35" />
	  			</c:when>
	  			<c:otherwise>
	  				文字链接
	  			</c:otherwise>
	  		 </c:choose>
	        </td>
	        <td>${obj.url}</td>
	        <td class="ac8" align="left">
	          <a href="${webPath}/admin/partner_edit.htm?id=${obj.id}&currentPage=${currentPage}">编辑</a> | 
	          <a href="javascript:void(0);" onclick="if(confirm('合作伙伴删除后不可恢复，是否继续删除?'))window.location.href='${webPath}/admin/partner_del.htm?mulitId=${obj.id}&currentPage=${currentPage}'">删除</a>
	        </td>
	       </tr>
	  </c:forEach>
	  
      <tr style="background:#F2F2F2; height:30px;">
        <td colspan="6">
        	<div class="fenye">${gotoPageHTML}</div>
          	<div class="shopbtn shopfx">
             <input name="all" type="checkbox" id="all" value="" onclick="selectAll(this)" />&nbsp;&nbsp;全部
            </div>
            <div class="shop_btn_del shopbtn">
             <input name="" type="button" value="删除" style="cursor:pointer;" onclick="cmd('${webPath}/admin/partner_del.htm','currentPage=$!currentPage')"/>
            </div>
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
