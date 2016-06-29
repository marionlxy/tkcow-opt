<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%
String baseUrl = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+baseUrl;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="rootPath" value="<%=baseUrl%>"></c:set>
<c:set var="basePath" value="<%=basePath%>"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

 <!--加载共通CSS-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/base.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/claim.css">
<!--加载960CSS框架-->
<link href="<%=basePath%>/static/css/960/fluid/reset.css" rel="stylesheet" type="text/css"  />
<link href="<%=basePath%>/static/css/960/fluid/text.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/css/960/fluid/grid.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]><link href="<%=basePath%>/static/css/960/fluid/ie6.css" rel="stylesheet" type="text/css" /><![endif]-->
<!--[if IE 7]><link href="<%=basePath%>/static/css/960/fluid/ie.css"  rel="stylesheet" type="text/css" /><![endif]-->
<!--加载按钮CSS-->
<link href="<%=basePath%>/static/css/btns/css3btn.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/css/btns/sexybuttons.css" rel="stylesheet" type="text/css" />
 <!--加载组件CSS-->  
<link href="<%=basePath%>/static/js/jquery-plugin/showloading/showLoading.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/static/js/jquery-plugin/jnotify/jquery.jnotify.css" rel="stylesheet" type="text/css" />
<!--加载样式修正CSS-->
<link href="<%=basePath%>/static/css/hack/fix.css" rel="stylesheet" type="text/css" />

<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=basePath%>/static/js/easyui/themes/default/easyui.css" id="swicth-style">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/js/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/js/easyui/themes/form.css">

<script type="text/javascript" src="<%=basePath%>/static/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/json.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/loadmask/jquery.loadmask.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<!--加载  js-->
<script type="text/javascript" src="<%=basePath%>/static/js/jquery-plugin/showloading/jquery.showLoading.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/common.js"></script> 
<script type="text/javascript" src="<%=basePath%>/static/js/utils.js"></script> 
<script type="text/javascript" src="<%=basePath%>/static/js/jquery-plugin/jnotify/jquery.jnotify.js"></script>
<!-- <script type="text/javascript" src="<%=basePath%>/static/js/common/common.js"></script> -->
<script type="text/javascript" src="<%=basePath%>/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/changeEasyuiTheme.js"></script>
<!--加载jeasyui.extensions  js-->
<script type="text/javascript" src="<%=basePath%>/static/js/jeasyui-extensions/jquery.jdirk.min.js"></script>

<link href="<%=basePath%>/static/js/jeasyui-extensions/icon-all.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/static/js/jeasyui-extensions/jeasyui.icons.all.min.js"></script>
<link href="<%=basePath%>/static/js/jeasyui-extensions/jeasyui.extensions.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/static/js/jeasyui-extensions/jeasyui.extensions.all.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">


<!--加载Ajax Autocomplete Demo-->
<script type="text/javascript"
	src="<%=basePath%>/static/js/common/jquery.autocomplete.js"></script>
<style type="text/css">
.autocomplete-suggestions {
	border: 1px solid #999;
	background: #FFF;
	overflow: auto;
}

.autocomplete-suggestion {
	padding: 2px 5px;
	white-space: nowrap;
	overflow: hidden;
}

.autocomplete-selected {
	background: #F0F0F0;
}

.autocomplete-suggestions strong {
	font-weight: normal;
	color: #3399FF;
}

.autocomplete-group {
	padding: 2px 5px;
}

.autocomplete-group strong {
	display: block;
	border-bottom: 1px solid #000;
}
</style>

<title></title>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : true">
		<div
			data-options="region:'west',collapsible:false,title:'UC用户',iconCls: 'icon-org', height: $(window).height() - 4"
			style="width: 180px; scroll: true, overflow: hidden;">
			<ul id="tt" animate="true">
			</ul>
		</div>
		<div data-options="region:'center',border:false" style="overflow: hidden;">
			<h2>Redis uc Query</h2>
	<p>The textbox allows a user to enter information autoComplete.</p>
	&nbsp;&nbsp;&nbsp;<h4>　UC 用户数据模型</h4>
	<font color = "red" >
		<p>　　　Key:　　TK_User:用户帐号:用户部门名称:用户姓名 </p>
		<p>　　　Value:　用户帐号　用户姓名　用户部门ID　用户部门名称　上级部门ID</p>    
	</font>
	<h4>　　　example.</h4>
	<font color = "red" >
		<p>　　　Key:　　TK_User:dunyxx:张三:tk_1392798370093366 </p>
		<p>　　　Value:　java　Object　　Tk_UserBO.java</p>    
	</font>
	<br/>
	<p>　　Casey：键值数据建模中的最佳实践，是专注于访问模式。它鼓励开发者站在从系统外部应用获取数据的角度，来处理问题。<br/>
	　　　　　 如果数据写入的时候，能够确保它将匹配应用获取数据所需要的格式，那么在这种方式下数据模型几乎是透明的。<br/>
　　　　 　良好的键值数据模型会从访问模式方法的设计中“淡出”。<br/>
		　
　　　　 　除了访问模式外，设计方面的考虑还包括：是否将对数据进行加密、进行版本管理或是在持久化之后进行修改，数据操作更倾向于读取还是写入，<br/>
　　　　 　以及是否将永远不会被修改。永远都不会被修改的数据，被称为“不可变”数据，而不可变数据往往会为系统的架构带来一些便利。<br/>
		
　　　　 　 　键值数据库并不具有诸如SQL这样的查询语言所提供的“丰富性”。开发者如果期望在键值数据库上使用类似SQL的查询语言，那么将会发现实际并非如此。
		</p>
		
	<div class="easyui-panel" title="查询uc用户"
		style="width: 100%; padding: 30px 60px">
		<div style="margin-bottom: 20px">
			<div>根据用户姓名或用户账号模糊查询:</div>
			<input type="text" name="autocomplete-ajax" id="autocomplete-ajax"
				style="position: relative; width: 50%; height: 25px; border: 1px solid #95B8E7; background-color: #fff; vertical-align: middle; display: inline-block; overflow: hidden; white-space: nowrap; margin: 0; padding: 0; -moz-border-radius: 5px 5px 5px 5px; -webkit-border-radius: 5px 5px 5px 5px; border-radius: 5px 5px 5px 5px;" />
		</div>
		<!-- <div style="margin-bottom: 20px">
			<div>根据部门模糊查询:</div>
			<input type="text" name="tags" id="tags"
				style="position: relative; width: 50%; height: 25px; border: 1px solid #95B8E7; background-color: #fff; vertical-align: middle; display: inline-block; overflow: hidden; white-space: nowrap; margin: 0; padding: 0; -moz-border-radius: 5px 5px 5px 5px; -webkit-border-radius: 5px 5px 5px 5px; border-radius: 5px 5px 5px 5px;" />
		</div> -->

	</div>
		</div>
</div>

	<script type="text/javascript">
	$('#tt').tree({    
	    url:'${rootPath}/redisDemo/getAllUCUserTree', 
	    id:'id',
	    state:'state',
	    text:'text',
	    onSelect:function(node){
		    			    /* url='${rootPath}/redisDemo/getAllUCUserTree';
		    		      	$('#dataTable').datagrid('load',url);
		    		      	$('#dictTypeId').val(node.id); */
	    },
        onBeforeExpand:function(node,param){ 
        	//alert(1); 
        },
        onExpand:function(node,param){ 
        	//alert(2); 
        }
	});

	$(function () {
		$('#autocomplete-ajax').autocomplete({
		    serviceUrl: '${rootPath}/redisDemo/queryUsers',
		    type:"post",
		    minChars: 2,
		    noCache:true,
		    groupBy: 'category',
		    maxHeight:500,
		    //width:500,
		    /* params:{
				id : 'btndel',
				text : escape(encodeURIComponent($('#autocomplete-ajax').val())),
				iconCls : 'icon-cross'
			}, */
		    //ajaxSettings:{settings:{contentType: "application/x-www-form-urlencoded; charset=utf-8"}},
		    /* paramName: 'searchString',
		    transformResult: function(response) {
		    	alert(response);
		    	alert( JSON.stringify(response));
		        return {
		        	
		            suggestions: $.map(response.suggestions, function(dataItem) {
		            	alert(1);
		                return { value: dataItem.user_name + "_" + dataItem.departname, data: dataItem.user_id };
		            })
		        };
		    }, */
		    onSelect: function (suggestion) {
		    	//alert(JSON.stringify(suggestion));
		        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
		    }
		});
        
        $("#tags").autocomplete({
        	serviceUrl: '${rootPath}/redisDemo/queryUsersByDepid',
		    type:"post",
		    minChars: 2,
		    maxHeight:500,
		    onSelect: function (suggestion) {
		    	//alert(JSON.stringify(suggestion));
		        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
		    }
		});
	});
	</script>
</body>
</html>