<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>泰康门户系统后台管理登录</title>
</head>
<link href="<%=basePath%>/resources/style/system/manage/blue/login.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>/resources/js/jquery-1.6.2.js"></script>
<script>

function login(){
	jQuery("#theForm").submit();
}
var EnterSubmit = function(evt){
evt = window.event || evt;
 if (evt.keyCode == 13)
 { 
  login();
 }
}
window.document.onkeydown=EnterSubmit;
jQuery(document).ready(function(){
     if(top.location!=this.location)top.location=this.location;//跳出框架在主窗口登录
	jQuery('#j_username').focus();	
});
</script>
<body>
<form name="theForm" id="theForm" action="<%=basePath%>/j_spring_security_check" method="post">
<div class="main_body">
  <div class="login_top"> </div>
  <div class="login_mid">
    <div class="login_left"></div>
    <div class="login_m">
      <ul>
        <li>
          <div class="usename"></div>
          
          <c:choose>
			<c:when test="${not empty user}">
				<div class="utxt">
					<input name="j_username" type="hidden" id="j_username" value="${user.username}" />${user.username}
		 		</div>
			</c:when>
			<c:otherwise>
				<div class="ntxt">
            		<input name="j_username" type="text" id="j_username"  autocomplete="false"/>
          		</div>
			</c:otherwise>
		  </c:choose> 
        </li>
        <li>
          <div class="password"></div>
          <div class="ptxt">
            <input name="j_password" type="password" id="j_password"  autocomplete="false"/>
          </div>
        </li>
      </ul>
    </div>
    <div class="login_r"></div>
  </div>
  <div class="login_mid2">
    <div class="login_left2"></div>
    <div class="login_m2">
	  <div style="width:140px;float:left;"><a href="${webPath}/index.htm" target="_blank" style="padding-top:0px; display:block; float:right; margin-right:30px;color:#fff">返回首页</a></div>
      <div class="m1">
        <input name="" type="button"  value="" onclick="login();" style="cursor:pointer"/>
        <input name="login_role" type="hidden" id="login_role" value="admin" />
      </div>
      <div class="m2">
        <input name="" type="reset"  value=""  style="cursor:pointer"/>
      </div>
    </div>
    <div class="login_r2"></div>
  </div>
  <div class="bottom">
    <div class="bottoml"></div>
    <div class="bottomm"></div>
    <div class="bottomr"></div>
  </div>
  <div class="tell">
    <p>Copyright 2011-2014 © <a href="" target="_blank"><span style="color: #2662AC;">Taikang</span></a> 泰康</p>
  </div>
</div>
</form>
</body>
</html>
