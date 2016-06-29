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
<script type="text/javascript"
	src="<%=basePath%>/static/js/fileuploder/ajaxfileupload.js"></script>
<title></title>
 <script type="text/javascript">
    $(function(){
       //上传图片
       $("#btnUpload").click(function() {
               ajaxFileUpload();
       });
    });
    function getJSONStr(perdata){  
        var rawStr = perdata;  
        var reg = /<pre.+?>(.+)<\/pre>/g;  
        var result = rawStr.match(reg);  
        return $.parseJSON(RegExp.$1);  
    } 
    function ajaxFileUpload() {
        // 开始上传文件时显示一个图片
        $("#wait_loading").ajaxStart(function() {
            $(this).show();
        // 文件上传完成将图片隐藏起来
        }).ajaxComplete(function() {
            $(this).hide();
        });
        var elementIds=["flag"]; //flag为id、name属性名
        $.ajaxFileUpload({
            url: "${rootPath}/file/uploadByAjaxGrp", 
            type: 'post',
            secureuri: false, //一般设置为false
            complete:null,
            fileElementId: 'file', // 上传文件的id、name属性名
            dataType: 'text', //返回值类型，一般设置为json、application/json
            data: { Id: '123', name: 'lunis' }, //此参数非常严谨，写错一个引号都不行
            success: function(data, status,msg){  
            	jsondata = getJSONStr(data);
            	alert(2222);
                alert(jsondata.msg);
            },
            error: function(data, status, e){ 
                alert(e);
            }
        });
        //return false;
    }
    </script>
  </head>
  
  <body>
    <div id="wait_loading" style="padding: 50px 0 0 0;display:none;">
        <div style="width: 103px;margin: 0 auto;"><img src="<%=basePath %>/static/images/sys_logo.jpg"/></div>
        <br></br>
        <div style="width: 103px;margin: 0 auto;"><span>请稍等...</span></div>
        <br></br>
    </div>
    <input type="file" id="file" name="file"><br/>
    <input type="hidden" id="flag" name="flag" value="ajax文件上传"/>
    <input type="button" id="btnUpload" value="上传图片" />
  </body>
</html>