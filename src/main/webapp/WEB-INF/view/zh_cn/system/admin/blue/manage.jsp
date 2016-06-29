<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=basePath%>/resources/style/system/manage/${config.websitecss}/template.css"  rel="stylesheet" type="text/css"/>
<script src="<%=basePath%>/resources/js/jquery-1.6.2.js"></script>
<script src="<%=basePath%>/resources/js/jquery.shop.base.js"></script>
<script>
jQuery(document).ready(function(){
    pagestyle();
	jQuery(".webmap a").click(function(){
	    jQuery(".webmap_box").fadeIn('normal');
	 });
	jQuery(".close_map").click(function(){
	    jQuery(".webmap_box").fadeOut('normal');
	});
	jQuery("a[id^=complex_]").click(function(){
	  var suffix=jQuery(this).attr("suffix");
	  if(jQuery("#"+suffix+"info").css("display")=="block"){
	      jQuery("#"+suffix+"info").hide();
		  jQuery("#"+suffix+"img").attr("src","${webPath}/resources/style/system/manage/blue/images/spread.jpg");
	   }else{
	      jQuery("#"+suffix+"info").show();
		  jQuery("#"+suffix+"img").attr("src","${webPath}/resources/style/system/manage/blue/images/contract.jpg");	  
	   }
	});
	jQuery(".webskin em a img").click(function(){
	      var webcss = jQuery(this).attr("webcss");
			jQuery.post("${webPath}/admin/set_websiteCss.htm",{
						"webcss":webcss
						},function(data){
						window.location.href="${webPath}/admin/index.htm";	
							},"text");
		});
});	
</script>
</head>
<body scroll="yes">
<div class="main">
  <div class="top">
    <div class="login">您登录的身份是：${user.username} &nbsp;&nbsp;| <a href="<c:url value="/admin/logout.htm"/>" target="_self">安全退出</a>|<a href="${webPath}/admin/admin_pws.htm" target="main_workspace">修改密码</a>| <a href="${webPath}/index.htm" target="_blank">门户首页</a></div>
    <div class="logo"></div>
    <div class="nav">
      <ul>
        <li><a href="javascript:void(0);" class="home" id="common_operation_menu" onclick="openURL('show','common_operation')">首页</a></li>
        <li><a href="javascript:void(0);" class="active" id="about_website_menu" onclick="openURL('show','about_website')">网站</a></li>
      </ul>
    </div>
  </div>
  <div class="index" id="workspace">
    <div class="left">
      <div class="lefttop"> </div>
      <div class="left_ul">
        <ul class="ulleft" id="common_operation">
          <div class="leftone">常用操作</div>
          <li><a class="this" id="welcome_op" href="javascript:void(0);" onclick="openURL('url','${webPath}/admin/welcome.htm','main_workspace','welcome_op')">欢迎页面</a> 
          </li>         
          <li><a id="aboutus_op" href="javascript:void(0);" onclick="openURL('url','${webPath}/admin/aboutus.htm','main_workspace','aboutus_op')">关于我们</a></li>
        </ul>
        <ul class="ulleft" style="display:none;" id="about_website">
          <div class="leftone">网站相关</div>
          <li><a href="javascript:void(0);" class="this" id="articleClass_op" onclick="openURL('url','${webPath}/admin/articleclass_list.htm','main_workspace','articleClass_op');">文章分类</a> </li>
          <li><a href="javascript:void(0);" id="article_op" onclick="openURL('url','${webPath}/admin/article_list.htm','main_workspace','article_op');">文章管理</a></li>
          <%-- <li><a href="javascript:void(0);" id="partner_op" onclick="openURL('url','${webPath}/admin/partner_list.htm','main_workspace','partner_op');">合作伙伴</a></li> --%>
          <li><a href="javascript:void(0);" id="nav_op" onclick="openURL('url','${webPath}/admin/navigation_list.htm','main_workspace','nav_op');">页面导航</a></li>
          <li><a href="javascript:void(0);" id="advert_set_op" onclick="openURL('url','${webPath}/admin/advert_list.htm','main_workspace','advert_set_op')">广告管理</a></li>
        </ul>
      </div>
    </div>
    <div class="content">
    <div class="navbar">
      <div style="line-height:36px;">您的位置：控制台><span id="top_nav_info">站点设置</span></div>
      </div>
      <iframe id="main_workspace" name="main_workspace" src="${webPath}/admin/welcome.htm" style="overflow:auto;height:550px" frameborder="0" width="100%" height="100%" scrolling="yes" onload="window.parent"></iframe>
    </div>
  </div>
</div>
${uc_login_js}
</body>
</html>
