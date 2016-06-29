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
<body>
<div class="cont">
  <h1 class="seth1">页面导航</h1>
  <div class="settab"><span class="tab-one"></span> <span class="tabs"> <a href="${webPath}/admin/navigation_list.htm"  class="this">管理</a> | <a href="${webPath}/admin/navigation_add.htm">新增</a> </span> <span class="tab-two"></span></div>
  <form action="${webPath}/admin/navigation_list.htm" method="post">
  <div class="allmem_search">
    <ul>
      <li> <span>导航标题</span> <span class="allmen size4">
        <input name="title" type="text" id="title" />
        </span> <span class="btn_search">
        <input name="" type="submit"  value="搜索" style="cursor:pointer;"/>
        </span> 
       </li>
    </ul>
  </div>
  </form>
  <FORM name="ListForm" id="ListForm"  action="${webPath}/admin/navigation_list.htm" method="post">
  <div class="leveltable">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="level_table" style="font-size:12px">
      <tr style="background: #2A7AD2; height:30px; color:#FFF">
        <td width="66">&nbsp;</td>
        <td width="121">序号</td>
        <td width="240" >标题</td>
        <td width="446" >URL</td>
        <td  width="118">导航位置</td>
        <td  width="94">新窗口打开</td>
        <td width="125">显示</td>
        <td width="184">操作</td>
      </tr>
      
      <c:forEach var="obj" items="${objs}">
      	<tr>
         <td><input name="checkbox" type="checkbox" id="checkbox" value="${obj.id}"/></td>
         <td>${obj.sequence}</td>
         <td>${obj.title}</td>
         <td>${obj.url}</td>
	         <c:if test="${obj.location == -1 }">
	           <c:set var="location" value="顶部"></c:set>
	         </c:if>
	         <c:if test="${obj.location == 0 }">
	         	<c:set var="location" value="中间"></c:set>
	         </c:if>
	         <c:if test="${obj.location == 1}">
	         	<c:set var="location" value="底部"></c:set>
	         </c:if>
		<td>${location}</td>
			<c:if test="${obj.newWin == 1}">
	         	<c:set var="newWin" value="是"></c:set>
	        </c:if>
	        <c:if test="${obj.newWin != 1}">
	         	<c:set var="newWin" value="否"></c:set>
	        </c:if>
        <td>${newWin}</td>
        
            <c:if test="${obj.display == 1}">
	         	<c:set var="display" value="是"></c:set>
	        </c:if>
	        <c:if test="${obj.display != 1}">
	         	<c:set var="display" value="否"></c:set>
	        </c:if>
        <td>${display}</td>
        <td class="ac9">
        	<a href="${webPath}/admin/navigation_edit.htm?id=${obj.id}&amp;currentPage=${currentPage}">编辑</a> 
         <c:if test="${obj.sysNav != 1}">
         	|<a href="${webPath}/admin/navigation_del.htm?mulitId=${obj.id}&amp;currentPage=${currentPage}">删除</a>
         </c:if>
        </td>
      </tr>
      </c:forEach>
      <tr>
        <td><input type="checkbox" name="all" id="all" onclick="selectAll(this)" value="" /></td>
        <td><span class="allevel">全部</span><span class="shop_btn_del">
          <input name="" type="button" value="删除" style="cursor:pointer;" onclick="cmd('${webPath}/admin/navigation_del.htm')"/>
          </span></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
  </div>
  <div class="fenye"><input type="hidden" name="currentPage" id="currentPage" value="$!currentPage" />
	  <input name="mulitId" type="hidden" id="mulitId" />
	  <input name="title" type="hidden" id="title" value="${title}" />
	  ${gotoPageFormHTML}</div>
 </FORM>
</div>
</body>
</html>
