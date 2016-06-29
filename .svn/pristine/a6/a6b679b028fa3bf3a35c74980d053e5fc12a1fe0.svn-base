<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/include.jsp"%>
<title></title>
<script type="text/javascript">  
    i = 1;  
    j = 1;  
    $(document).ready(function(){  
        $("#btn_add2").click(function(){  
            document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';  
              j = j + 1;  
        });  
    });  
  
    function del_1(o){  
     document.getElementById("newUpload1").removeChild(document.getElementById("div_"+o));  
    }  
      
    function del_2(o){  
         document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));  
    }  
  
</script>
</head>
<body>
	<h1>springMVC包装类上传文件</h1>
	<form name="userForm2"  action="${rootPath}/file/uploadBySpringGrp" enctype="multipart/form-data" method="post">
		<div id="newUpload2">
			<input type="file" name="file">
		</div>
		<input type="button" id="btn_add2" value="增加一行">
		<input type="submit" value="上传"   id="uploadBySpringGrp"  >
	</form>
</body>
<script type="text/javascript">
 jQuery(function() {
		// 上传按钮事件
		$('#uploadBySpringGrp').click( function () {
			method = "post";  
            action = "${rootPath}/file/uploadBySpringGrp";  
            submit();  
		});
 });
</script>
</html>
