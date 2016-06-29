<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统提示 - ${config.title} -</title>
<link href="${webPath}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
<link href="${webPath}/resources/style/system/front/default/css/index.css" type="text/css" rel="stylesheet" />
<link href="${webPath}/resources/style/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
</head>
<body>
<div class="main">
  <jsp:include page="./head.jsp"/>
  <jsp:include page="./nav.jsp"/>
  <div class="index" style="height:300px;">
    <div style="height:280px;border:1px solid #CCCCCC;overflow:hidden; margin-left:auto; margin-right:auto; margin-top:10px;">
      <div style="font-size:16px; color:#666666;margin-top:100px; padding-left:40%;">
      <span style="float:left;">
      <img src="${webPath}/resources/style/common/images/icon/error.png" />
      </span>
      <span style=" padding-top:10px; line-height:30px; float:left;">${op_title}！</span>
      </div>
    </div>
  </div>
  <c:if test="${not empty url}">
  	<script>
	  var count=3;
	  window.setInterval(go,1000);
	  function go(){
	    count--;
	    if(count==0) window.location.href="${url}";
	  }
	</script>
  </c:if>
  <jsp:include page="./footer.jsp"/>
</div>
</body>
</html>
