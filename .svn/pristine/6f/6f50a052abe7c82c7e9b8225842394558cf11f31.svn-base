<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/include.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/modern_op.css">
	  <style type="text/css">
        .listview{ margin:0 !important;}
        .listview li{width:100px !important;background-color:skyblue !important;float:left;margin:3px;}
    </style>
    <title></title>
</head>
<body>
    <table id="dataMenuTable" title="菜单列表" style="width:100%;height:400px" >
    </table>    
    <div id="divDialog">
	</div>

<script type="text/javascript">

   function setButten(menuId){
	   url = '${rootPath}/menu/showEditButtonPage?menuId='+ menuId;
	   $("#divDialog").dialog({
	        title: "设置按钮",
	        width: 670,
	        height: 420,
	        href:url,
			cache: false,
			closed: false,    
		    modal:true
	    });
   }
	
	jQuery(function(){
   	//初始化列表
   	$('#dataMenuTable').treegrid({
   		iconCls:'icon-tip',
   		singleSelect : true,
   		remoteSort:false,
   		rownumbers:true,
   		pagination:true,
   		fit:true,	
   		url:'${rootPath}/menu/getMenuList4Tree',
   		method : 'post', 	
      	idField:'Menu_Id',    
        treeField:'Menu_Name', //此处根据实际情况进行填写
		columns:[[
		    {field:'ck',checkbox:true},
			{field:'Menu_Name',title:'菜单名称',width:150},
			{field:'Menu_Id',title:'菜单ID',width:80,sortable:'true'},
			{field:'Menu_Status',title:'菜单状态',width:80,
				formatter: function(value,row,index){
                    if(value=='1'){
                    	return '有效';
                    }else if(value=='0'){
                    	return '无效';
                    }
		           }	
	         },
	     	{field:'Is_Leaf',title:'是否叶子菜单',width:100,
					formatter: function(value,row,index){
	                    if(value=='1'){
	                    	return '是';
	                    }else if(value=='0'){
	                    	return '否';
	                    }
			     }		
		},
		    {field:'Parent_Id',title:'父菜单ID',width:80},
			{field:'Menu_Url',title:'访问路径',width:150},
			{field:'Menu_Order',title:'菜单顺序',width:100},
			{field:'CREATOR',title:'创建人',width:100},
			/*{field:'CREATE_TIME',title:'创建时间',width:140},*/
			{field:'aa',title:'页面按钮', width:110,
				formatter: function(value,row,index){
				   return "<a href='#' onclick=setButten('"+row.Menu_Id+"') style='margin-left:10px'><span class='icon icon-wrench'>&nbsp</span>[设置按钮]</a>"
				}
	    }
		]],
		toolbar : [{
			id : 'btnref',
			text : '刷新',
			iconCls : 'icon-arrow_refresh',
			handler : function() {
				var mUrl='${rootPath}/menu/getMenuList4Tree';
				$('#dataMenuTable').treegrid('reload');
			}
		}, '-', {
			id : 'btnaddparent',
			text : '添加父菜单',
			iconCls : 'icon-add',
			handler : function() {
				addrow(0);
			}
		}, '-', {
			id : 'btnaddLeaf',
			text : '添加叶子菜单',
			iconCls : 'icon-add',
			handler : function() {
				addrow(1);
			}
		}, '-', {
			id : 'btnedit',
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				editrow();
			}
		}, '-', {
			id : 'btndel',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				delerow();
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataMenuTable').treegrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
   });


	//新增
	function addrow(isLeaf) {
		// 添加叶子菜单时需要检查是否选中父菜单
		if (isLeaf == 1) {
			var row = $('#dataMenuTable').datagrid('getSelected');
			if (!row) {
				$.messager.alert("warning", "请选择父菜单!");
	            return;
			} else {
				var parent = "";
				if (row.Parent_Id == 0 || row.Parent_Id =="0") {
                    parent = row.Menu_Id;
				} else {
				    parent = row.Parent_Id + "|" + row.Menu_Id;
				}
				url = '${rootPath}/menu/showMenuAddPage?isLeaf=' + isLeaf + "&parent=" + parent;
	            $("#divDialog").dialog({
	                title : "新增菜单",
	                width : 450,
	                height : 250,
	                href : url,
	                cache : false,
	                closed : false,
	                modal : true
	            });
			}
		} else {
			url = '${rootPath}/menu/showMenuAddPage?isLeaf=' + isLeaf + "&parent=" + parent;
			$("#divDialog").dialog({
				title : "新增菜单",
				width : 450,
				height : 250,
				href : url,
				cache : false,
				closed : false,
				modal : true
			});
		}
	}


	//修改
	function editrow() {
		var row = $('#dataMenuTable').datagrid('getSelected');

		if (row) {
			url = '${rootPath}/menu/showMenuEditPage?menuId=' + row.Menu_Id + '&isLeaf='
					+ row.Is_Leaf;
			$("#divDialog").dialog({
				title : "编辑菜单",
				width : 450,
				height : 250,
				href : url,
				cache : false,
				closed : false,
				modal : true
			});
			//openWin(url);
		} else {
			$.messager.alert("warning", "请选择你要更新的记录!");
			return;
		}
	}

	//删除
	function delerow() {
		var row = $('#dataMenuTable').datagrid('getSelected');
		if (row) {
			com.message('confirm', '删除后不可恢复,确定要删除该菜单吗？', '提示', function(r) {
				if (r) {
					$.post('${rootPath}/menu/deleteMenu?menuId=' + row.Menu_Id,
							function(data) {
								if (data.result == 'true'
										|| data.result == true) {
									$.messager.alert("success", "菜单删除成功！");
									goBack(1);
								} else if (data.result == 'backMeg') {
									$.messager.alert('提示', data.msg, 'info');
								} else if (data.result == 'false') {
									$.messager.alert("error", "菜单删除失败！");
								}
							});
				}
			});
		} else {
			$.messager.alert("warning", "请选择你要操作的记录!");
			return;
		}
	}

	function goBack(flag) {
		if (flag == 1) {
			url = '${rootPath}/menu/getMenuList4Tree', $('#dataMenuTable')
					.treegrid('reload');
		}
		$("#divDialog").window('close');
	}
</script>
</body>
</html>