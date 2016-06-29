<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<c:forEach var="ac" items="${acs}">
<tr id="${ac.id}" parent="${ac.parentId}" level="child_${ac.parentId}">
 <td align="center"><input name="ids" id="ids" type="checkbox" value="${ac.id}"/></td>
    <td colspan="2" align="center">
   <ul class="addclass">
   <li class="accc1" style="margin-left:30px;">
   	<img src="${webPath}/resources/style/system/manage/blue/images/expandtable.gif" cls="jian"/>
   	</li>
   <li class="ac2"><span class="num">
    <input type="text" name="${ac.sequence}" id="${ac.sequence}"  value="${ac.sequence}" onblur="ajax_update('${ac.id}','sequence',this)"/></span></li>
   <li class="accc3"><span class="classies">
   	<input type="text" name="${ac.classname}" id="${ac.classname}" value="${ac.classname}"  onblur="ajax_update('${ac.id}','classname',this)"/></span>
   </li>
  </ul>    
  </td>
    <td align="left" class="ac8">
    	<a href="${webPath}/admin/articleclass_edit.htm?id=${ac.id}">编辑</a>
       |<a href="javascript:void(0);" onclick="if(confirm('删除分类会同时删除该分类的所有下级，是否继续?'))window.location.href='${webPath}/admin/articleclass_del.htm?mulitId=${ac.id}'">删除</a></td>
</tr>
</c:forEach>

