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
	<div class="easyui-layout" data-options="fit : true,border : true">
		<div
			data-options="region:'west',collapsible:false,title:'数据字典',iconCls: 'icon-org', height: $(window).height() - 4"
			style="width: 180px; scroll: true, overflow: hidden;">
			<ul id="tt" animate="true">
			</ul>
		</div>
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dataTable">
				<input type="hidden" id="dictTypeId" />
			</table>
		</div>
		<div id="divDialog"></div>
	</div>
	<script type="text/javascript">
	$('#tt').tree({    
	    url:'${rootPath}/sys/dictType/getAllDictTypeTree', 
	    id:'id',
	    state:'state',
	    text:'text',
	    onSelect:function(node){
		    			    url="${rootPath}/sys/dictEntry/getDictEntryList?dictTypeId="+node.id;
		    		      	$('#dataTable').datagrid('load',url);
		    		      	$('#dictTypeId').val(node.id);
	    		        }
	});  
	
	jQuery(function(){	  
	   	//初始化列表
	   	$('#dataTable').datagrid({
	   		iconCls:'icon-tip',
	   		singleSelect : true,
	   		fit:true,
	   		rownumbers:true,
	   		pagination:true,
	   		method : 'post',		
			idField : 'dictTypeId',//此处根据实际情况进行填写
			columns:[[
			                {field:'ck',checkbox:true},
							{field:'dictTypeId',title:'字典类型',width:150},
							{field:'dictId',title:'字典代码',width:140},
							{field:'dictName',title:'字典描述',width:200},
							{field:'status',title:'状态',width:100,
								formatter: function(value,row,index){
		                            if(value=='1'){
		                            	return '有效';
		                            }else if(value=='0'){
		                            	return '失效';
		                            }
						}		
							},
							{field:'sortNo',title:'排序',width:110},
							{field:'parentId',title:'父类型',width:100},
							{field:'seqNo',title:'级联关系描述',width:120},
						//注：最后一行后面的逗号要去掉
		]],
			toolbar : [{
				id : 'btnadd',
				text : '刷新',
				iconCls : 'icon-arrow_refresh',
				handler : function() {
					 var url="${rootPath}/sys/dictEntry/getDictEntryList";
					$('#dataTable').datagrid('load',url);
					$('#tt').tree('reload');
				}
			   },'-',{
				id : 'btnadd',
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					addEntryrow();
				}
			}, '-', {
				id : 'btnedit',
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					editEntryrow();
				}
			}, '-', {
				id : 'btndel',
				text : '删除',
				iconCls : 'icon-cross',
				handler : function() {
					deleEntryrow();
				}
			}, '-', {
				id : 'btnedit',
				text : '类别维护',
				iconCls : 'icon-bullet_wrench',
				handler : function() {
					manage();
				}
			}],
			onLoadSuccess : function(data) {
				$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			}
		});
	   	
	   });
   //新增
   function addEntryrow(){
	    var dictTypeId=$('#dictTypeId').val();
	    if(dictTypeId.length==0){
	    	com.message("warning","您还没有选择类别!");
	    }else{
    	url = '${rootPath}/sys/dictEntry/showDictEntryAddPage?dictTypeId='+dictTypeId;
    	$("#divDialog").dialog({
	        title: "新增数据字典",
	        width: 450,
	        height: 300,
	        href:url,
			cache: false,
			closed: false,    
		    modal:true
	    });
	    }
   }
   
   //修改
   function editEntryrow(){    	
       var row = $('#dataTable').datagrid('getSelected');
	
       if (row){
       	url = '${rootPath}/sys/dictEntry/showDictEntryEditPage?dictTypeId='+ row.dictTypeId+'&dictId='+row.dictId;
        $("#divDialog").dialog({
	        title: "编辑数据字典",
	        width: 450,
	        height: 300,
	        href:url,
			cache: false,
			closed: false,    
			modal:true
	    });
		//openWin(url);
       }
       else
       {
    	   com.message("warning","请选择你要更新的记录!");
		return;
       }
   }
   
   //管理类别
   function manage(){    	
       	url = '${rootPath}/sys/dictType/showDictTypeIndexPage'
        $("#divDialog").dialog({
	        title: "类别维护",
	        width: 620,
	        height: 380,
	        href:url,
			cache: false,
			closed: false,    
			modal:true
	    });
   }
   
   //删除
   function deleEntryrow(){
       var row = $('#dataTable').datagrid('getSelected');
       if (row){
        	   com.message('confirm','确定删除数据字典吗？请谨慎操作！','提示',function(r){
               if (r){
                   $.post('${rootPath}/sys/dictEntry/deleteDictEntry',{dictTypeId:row.dictTypeId,dictId:row.dictId},function(data){
                	   if(data.result == 'true' || data.result == true) {
           				com.message("success", "数据字典删除成功！");
           				goBack(1);
           			} else {
           				com.message("error", "数据字典删除失败 ！");
           			}    	
                   });
               }
           });
       }
   }
   
	function goBack(flag) {
		if(flag==1) {
			url = '${rootPath}/sys/dictEntry/getDictEntryList',
			$('#dataTable').datagrid('reload');
		}
		$("#divDialog").window('close');
	}
    </script>
</body>
</html>