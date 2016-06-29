<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录 </title>

<link href="<%=basePath%>/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>/resources/style/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>/resources/js/jquery-1.6.2.js"></script>
<script src="<%=basePath%>/resources/js/jquery.validate.min.js"></script>
<script>
jQuery(document).ready(function(){
  jQuery("#theForm").validate({
     errorPlacement:function(error,element) {
      error.appendTo(element.parent().find(".login_error_sp"));  
	  element.parent().find(".login_error_sp").show('normal');
    },
	success:function(label){
	    label.parent().hide();
	},
	rules: {
			j_username: "required",
			j_password: "required",
			},
		messages: {
			j_username: "用户名不能为空",
			j_password: "密码不能为空"}
  });
});
</script>
</head>
<body>
<div class="main"> 
 <div class="head_login">
 <div class="login_logo" style="margin-top:30px; padding-left:80px;">
 <a href="<%=basePath%>/index.htm"><img src="<%=basePath%>/resources/style/system/front/default/images/logo.png"  border="0" /></a>
 </div>
 </div>
  <div class="index">
    <div class="index2">
      <div class="login_usertb">
        <div class="login_usetbox">
          <div class="login_usrin">
            <ul>
              <form action="<%=basePath%>/j_spring_security_check" method="post" name="theForm" id="theForm">
                <li class="login_usertxt"><b>登录名：</b>
                  <input name="j_username" type="text" id="j_username" class="login_utxt" autocomplete="off" />
                  <span class="login_error_sp" style="display:none"></span></li>
                <li class="login_usertxt"><b>密&nbsp;&nbsp;&nbsp;码：</b>
                  <input name="j_password" type="password" id="j_password"  autocomplete="off" class="login_utxt"/>
                  <span class="login_error_sp" style="display:none"></span></li>
            
                <li>
                  <input name="" type="submit" class="login_userbtn" value="登录"/>
                </li>
              </form>
              
            </ul>
          </div>
        </div>
        <div class="login_usertb_bgimg"><span class="imgcenter_span">
        
          <p><img src="<c:url value="/resources/style/common/images/login_img.jpg"/>" width="490" height="400" /></p>
          </span></div>
      </div>
    </div>
  </div>
 <!--  $!httpInclude.include("/footer.htm") -->
  <jsp:include page="./footer.jsp" ></jsp:include>
  
  </div>
</body>
</html>
