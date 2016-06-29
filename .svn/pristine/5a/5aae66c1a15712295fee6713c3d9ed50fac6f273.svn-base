<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${webPath}/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="cont">
  <h1 class="seth1">系统消息</h1>
  <div class="bigok">
    <div class="bigokimg"><img src="${webPath}/resources/style/system/manage/${config.websitecss}/images/sys.jpg" /></div>
    <div class="bigleft">
      <ul>
        <li>${op_title}</li>
        <li class="hui">自动为您跳转到刚才的页面！</li>
        <li class="successbtn">
        	<c:if test="${not empty list_url}"><a href="${list_url}">返回上一页</a></c:if>
        	<c:if test="${not empty add_url}"><a href="${add_url}">继续新增</a></c:if>
        </li>		
      </ul>
    </div>
	<c:if test="${not empty list_url}">
	<script>
	  var count=3;
	  window.setInterval(go,1000);
	  function go(){
	    count--;
	    if(count==0) window.location.href="${list_url}";
	  }
	</script>
	</c:if>
  </div>
</div>
</body>
</html>
