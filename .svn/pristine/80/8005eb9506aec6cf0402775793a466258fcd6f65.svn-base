<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath;
    if (request.getServerPort() == 80) {
        basePath = request.getScheme() + "://"
                + request.getServerName() + path;
    } else {
        basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort() + path;
    }
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统提示 - ${config.title}</title>
<meta name="keywords" content="${config.keywords}" />
<meta name="description" content="${config.description} "/>

<link href="<%=basePath%>/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>/resources/style/system/front/default/css/index.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>/resources/style/system/front/default/css/goods.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>/resources/js/jquery-1.6.2.js"></script>
</head>
<body>
<jsp:include page="./top.jsp"/>
<div class="main">
  <jsp:include page="./head.jsp"/>
  <%-- <jsp:include page="/nav1.htm"/> --%>
  <div class="index" style="height:300px;">
    <div style="height:280px;border:1px solid #CCCCCC;overflow:hidden; margin-left:auto; margin-right:auto; margin-top:10px;">
      <div style="font-size:16px; color:#666666;margin-top:100px; padding-left:40%;">
      <span style="float:left;">
      <img src="<c:url value="/resources/style/common/images/icon/succeed.png"/>" />
      </span>
      <span style=" padding-top:10px; line-height:30px; float:left;">${op_title}！</span>
      </div>
    </div>
  </div>
<script>
	 var count=3;
	  window.setInterval(go,1000);
	  function go(){
	    count--;
	    if(count==0) window.location.href="${url}";
	  } 
	</script>
  <jsp:include page="./footer.jsp"/>
</div>
</body>
</html>
