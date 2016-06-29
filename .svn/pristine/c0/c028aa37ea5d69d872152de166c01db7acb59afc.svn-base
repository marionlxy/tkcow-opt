<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8" isErrorPage="true"%>  
<%@include file="/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head><title>发生异常错误</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
<script type="text/javascript">	
	jQuery(function(){
		var flag=1;
		$("#showDetail").bind("click", function(){
			  if(flag==1){
				  $("#errorMsg").show();
				  flag=2;
			  }else{
				  flag=1;
				  $("#errorMsg").hide();
				  
			  }
			});		
	});
</script>
</head>
<body>
<% Exception e = (Exception)request.getAttribute("exception"); %>
<H2>发生系统错误: <%= e.getClass().getSimpleName()%></H2>
<hr />

<p>您可以<a href="#" id="showDetail">点击这里查看详细错误信息</a>，报告以下错误信息给系统管理员，可以更加快速的解决问题。</p>
    <div id="errorMsg" style="display:none">
    	<P>错误描述：</P>
    	<pre><font color="red">
    		<%= e.getMessage()%>
			</font>
		</pre>
		<P>详细错误信息：</P>
		<% e.printStackTrace(new java.io.PrintWriter(out)); %>
    </div>
    <br>
<!-- <p>或者<a href="javascript:reback();">直接返回</a>。</p> -->
</body>
</html>