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
</head>
<body>
<div class="cont">
  <h1 class="seth1">文章管理</h1>
  <div class="settab">
  	<span class="tab-one"></span> 
  	<span class="tabs"> 
  	  <a href="${webPath}/admin/article_list.htm"  class="this">管理</a>
  	| <a href="${webPath}/admin/article_add.htm">新增</a> 
  	</span> 
  	<span class="tab-two"></span>
  </div>
  <FORM name="ListForm" id="ListForm"  action="${webPath}/admin/article_list.htm" method="post">
  <div class="leveltable">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="level_table" style="font-size:12px">
      <tr style="background:#2A7AD2; height:30px; color:#FFF">
        <td width="56">&nbsp;</td>
        <td width="322">文章标题</td>
        <td width="401" >文章分类</td>
        <td width="91">是否显示</td>
        <td width="205">操作</td>
      </tr>
      
      <c:forEach var="obj" items="${objs}">
      	<tr>
        <td><input name="checkbox" type="checkbox" id="checkbox" value="${obj.id}"/></td>
        <td>${obj.title}</td>
        <td>${obj.articleClass.classname}</td>
        <td>
        	<c:if test="${obj.display == 1}">是</c:if><c:if test="${obj.display == 0}">否</c:if>
        </td>
        <td class="ac9">
        	<a href="${webPath}/admin/article_edit.htm?id=${obj.id}&amp;currentPage=${currentPage}">编辑</a> 
           |<a href="${webPath}/admin/article_del.htm?mulitId=${obj.id}&amp;currentPage=${currentPage}">删除</a>
         </td>
      	</tr>
      </c:forEach>
      <tr>
        <td><input type="checkbox" name="all" id="all" onclick="selectAll(this)" value="" /></td>
        <td><span class="allevel">全部</span><span class="shop_btn_del">
          <input name="" type="button" value="删除" style="cursor:pointer;" onclick="cmd('${webPath}/admin/article_del.htm')"/>
          </span></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>
    <div class="fenye"> ${gotoPageFormHTML}</div>	
  </div>
 
 </FORM>
</div>
</body>
</html>
