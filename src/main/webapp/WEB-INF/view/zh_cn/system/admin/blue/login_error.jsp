<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
</head>
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<body>
<div class="cont">
  <h1 class="seth1">系统消息</h1>
  <div class="bigok">
    <div class="bigokimg"><img src="${webPath}/resources/style/system/manage/blue/images/error.jpg" width="121" height="113" /></div>
    <div class="bigleft">
      <ul>
        <li>${op_title}！</li>
        <li class="hui">用户名、密码、验证码有至少一项不正确！</li>
		<li class="hui">自动为您跳转到刚才的页面！</li>
        <li class="successbtn"><a href="${webPath}/admin/login.htm">返回上一页</a></li>
      </ul>
    </div>
	<script>
	  var count=3;
	  window.setInterval(go,1000);
	  function go(){
	    count--;
	    if(count==0) window.location.href="${webPath}/admin/login.htm";
	  }
	</script>
  </div>
</div>
</body>
</html>
