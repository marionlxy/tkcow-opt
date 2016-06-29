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
</head>
<body>
		<div class="easyui-layout" data-options="fit : true,border : false">
		    <div data-options="region:'center',border:false"   style="overflow: hidden;">
			         <table id="dataTable">
			         </table> 
		     </div>
		       
		    <div id="divDialog"> </div>
		</div>
<script type="text/javascript">
	
	jQuery(function(){	  
   	//初始化列表
   	$('#dataTable').datagrid({
   		iconCls:'icon-tip',
   		singleSelect : true,
   		rownumbers:true,
   		fit:true,
   		pagination:true,
   		fitColumns:true,
   		method : 'post',		
   		url:'${rootPath}/filedown/list',
		idField : 'Id',//此处根据实际情况进行填写
		columns:[[
			{field:'ck',checkbox:true},
			{field:'Id',title:'附件流水号',width:80},
			{field:'relation_no',title:'关联业务号',width:80},
			{field:'filepath_name',title:'附件路径',width:80},
			{field:'file_name',title:'附件名称',width:80},
			/* {field:'filePath',title:'附件路径',width:80}, */
			/* {field:'desc',title:'desc',width:80},
			{field:'remark',title:'remark',width:80}, */
			{field:'oper_name',title:'上传人',width:80},
			{field:'create_time',title:'上传时间',width:80},
			{field:'aa',title:'操作', width:150,
				formatter: function(value,row,index){
					return "<a href='#' onclick=downFile('"+row.Id+"','"+row.filepath_name+"','" + row.file_name + "') style='margin-left:10px'>&nbsp<span class='icon icon-group'>&nbsp;</span>[下载附件]</a>";
				}
	    	}
			//注：最后一行后面的逗号要去掉
		]],
		toolbar : [{
			id : 'btnflash',
			text : '刷新',
			iconCls : 'icon-arrow_refresh',
			handler : function() {
				var url='${rootPath}/filedown/list';
				$('#dataTable').datagrid('reload',url);
			}
		}],
		onLoadSuccess : function(data) {
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		}
	});
   	
   	//初始化下拉框-示例，请根据需要自定义实现
   	/*
   	 $('#combo1').combobox({    
  	        url:'${rootPath}/getDictlist?dicttypeid=userstatus',    
  	        valueField:'dictid',    
  	        textField:'dictname',
  	     	panelHeight:'auto'
  	    }); 
  	  */ 
   });
   
	// 下载附件
	function downFile(fileId,filepathName,file_name) {
        var url="${rootPath}/file/download?filepathName=" + filepathName + "&file_name=" + file_name; 
        var elemIF = document.createElement("iframe");  
		elemIF.src = url;
		elemIF.style.display = "none";
		document.body.appendChild(elemIF);
	}

	//点击增加弹出增加窗口
	function openWin(url) {
		$('#iframeDialogSelect').attr("src", url);
		$('#divDialog').window('open');

	}
</script>
</body>
</html>