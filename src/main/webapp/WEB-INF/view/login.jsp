<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/include.jsp"%>
<link href="<%=basePath%>/static/css/login.css" rel="stylesheet"
	type="text/css" />

<title>欢迎登陆</title>
</head>
<script type="text/javascript">
if(window!=top){
    top.location = "${rootPath}/login";
}

$(function(){         
    $('#kaptchaImage').click(function () {
     //生成验证码  
     $(this).hide().attr('src', './ValidateCodeServlet/Validate.png?' + Math.floor(Math.random()*100) ).fadeIn();  
     event.cancelBubble=true;  
    });  
});

function changeCode() {  
    $('#kaptchaImage').hide().attr('src', './ValidateCodeServlet/Validate.png?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
}
</script>

<body style="font-family: Verdana,Geneva,sans-serif,微软雅黑;color:#08566F;">
	<div class="second_body">
		<div class="logo">
			<img src="<%=basePath%>/static/images/login/logo.png" />
		</div>
		<div class="title-zh">泰康之家官网管理端</div>
		<div class="title-en">The home of taikang website management side</div>

		<div class="message">
			<label id="loginError" class="error"></label>
		</div>
		<div>
			<table border="0" style="width: 285px;">
				<!-- <tr>
					<td style="padding-bottom: 5px; width: 55px;">用户名：</td>
					<td colspan="2"><input class="login easyui-textbox"
						name="usernamepro" value="" id="usernamepro"
						data-options="iconCls:'icon-man',iconWidth:35,required:true">
					</td>
				</tr>  -->
				<div style="algin:center">
				<div style="margin:5px  padding:5px ">
						<tr>
							<td whdth='44' height='28' valign="bottom">
								<div align="right">
									<span class="STYLE3"  style= "font-size:16px">用户名：</span>
								</div>
							</td>
							<td width="10" valign="bottom"></td>
							<td  heigth="24" colspan="2" valign="bottom">
								<div align="left">
									<input type="text" name="usernamepro"  id="usernamepro" 
											style="width:110px; height: 22px; background-color:#87adbf ; border:solid 1px  #153966;
												font-size:15px;color: #283439">
								</div>
							</td>
						</tr>
						<!--  <tr>
							<td class="lable"
								style="letter-spacing: 0.5em; vertical-align: middle">密码：</td>
							<td colspan="2"><input class="login easyui-textbox"
								name="passwordpro" value="" id="passwordpro" type="password"
								data-options="iconCls:'icon-lock',iconWidth:25,required:true"></td>
						</tr>  -->
						
						<tr>
							<td heigth="28"  valign="bottom" >
								<div align="right" style="margin-top:8px;">
									<span class="STYLE3"  style="font-size:16px">密&nbsp;&nbsp;&nbsp;码：</span>
								</div>
							</td>
							<td width="10" valign="bottom">&nbsp;</td>
							<td  heigth="24" colspan="2" valign="bottom">
								<input type="password" name="passwordpro"  id="passwordpro" 
											style="width:110px; height: 22px; background-color:#87adbf ;
											 border:solid 1px  #153966; font-size:15px;color: #283439">
							</td>
						</tr>
				</div>
				<tr>
					<c:choose> 
					<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'blocked'}"> 
			            <div style="color:red; align:right" > 
			                	登录失败次数过多，账号已经被锁定。
			            </div> 
			        </c:when>
			        <c:otherwise>
    					<div  style="color:red;align:right"> 
			                	${SPRING_SECURITY_LAST_EXCEPTION.message}
			            </div> 
  					</c:otherwise> 
			        </c:choose>
				</tr>
				<!--  <tr>
						<td style="padding-bottom: 5px; width: 55px;">验证码:</td>
						<td colspan="2"><input name="validateCode" id="validatecodepro"
							maxlength="4" class="login easyui-textbox"
							data-options="iconCls:'icon-vector_key',iconWidth:25,required:true" />
							<img src="./ValidateCodeServlet/Validate.png" id="kaptchaImage"
							style="margin-bottom: -3px" /> <a href="#" onclick="changeCode()">看不清?换一张</a>
						</td>
					</tr>  -->
					<tr>
						<td heigth="28"  valign="bottom" >
							<div align="right">
								<span class="STYLE3" style="font-size:16px">验证码：</span>
							</div>
						</td>
						<td width="10" valign="bottom">&nbsp;</td>
						<td   heigth="28"  valign="bottom">
							<input type="text" name="validateCode" id="validatecodepro" 
								style="width:55px; height: 22px; background-color:#87adbf ;
								 border:solid 1px  #153966;	font-size:15px;color: #283439">
						</td>
						<td  width="48" valign="bottom">
							<div align="left" height="15px"  valign="bottom" style="margin-top:4px;padding-right:45px;">
								<img src="./ValidateCodeServlet/Validate.png" id="kaptchaImage" style="margin:1.5px"/> 
								<a href="#" onclick="changeCode()"  style="font-size:12px ">换一张</a>
							</div>
						</td>
					</tr>
				<tr>
					<td></td>
						<td colspan="2">
						<form id="loginForm" method="post" action="${rootPath}/j_spring_security_check">
							<input type="hidden" name="rememberMe" id="rememberMe"/>
							<input type="hidden" name="j_username" id="username"/>
							<input type="hidden" name="j_password" id="password"/>
							<input type="hidden" name="validateCode" id="validatecode"/>
						</form>
					</td>
				</tr>
				<div style="padding:0px, contentarea:10px; ">
				<tr>
					<td colspan="3" style="text-align:center">
						<input type="submit" value="登录"  style="font-size:13px" class="login_button" id="login" onclick="loginAction()" />
						<input type="button" value="重置"  style="font-size:13px" class="reset_botton" onclick="reset()" /></td>
				</tr>
				</div>
				</div>
			</table>
		</div>
	</div>
	<script type="text/javascript">
$(function(){
	$('#username').textbox('textbox').focus();	
});

document.onkeydown = function(e){
    var event = e || window.event;  
    var code = event.keyCode || event.which || event.charCode;
    if (code == 13) {
    	loginAction();
    }
}

 function reset() {
	 $("#usernamepro").val("");
	 $("#passwordpro").val("");
	 $("#validatecodepro").val("");
	 $("#rememberMe").val("");
	 $("#username").val("");
	 $("#password").val("");
	 $("#validatecode").val("");
};
 

function loginAction() {
	var r = $('#loginForm').form('validate');
	if (!r) {
		return false;
	} else {
		if ($("#remember").is(":checked")) {
			$("#rememberMe").val("true");
		} else {
			$("#rememberMe").val("false");
		}
		var username = encode64($("#usernamepro").val());
		var password = encode64($("#passwordpro").val());
		var validateCode = encode64($("#validatecodepro").val());
		
		$("#username").val(username);
		$("#password").val(password);
		$("#validatecode").val(validateCode);
		$("#loginForm").submit();
	}
}
</script>
</html>