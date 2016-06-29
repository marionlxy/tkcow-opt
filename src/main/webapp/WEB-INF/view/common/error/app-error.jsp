<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head><title>发生异常错误</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<script type="text/javascript">	
</script>
</head>
<body>
<% Exception e = (Exception)request.getAttribute("exception"); %>
<H2>发生业务错误: <%= e.getClass().getSimpleName()%></H2>
<hr />
</body>
</html>