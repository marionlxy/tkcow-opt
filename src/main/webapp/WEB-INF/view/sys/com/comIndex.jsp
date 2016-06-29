<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
        <div data-options="region:'west',collapsible:false,title:'机构目录',iconCls: 'icon-org', height: $(window).height() - 4" style="width: 180px;scroll:true,overflow: hidden;">
            <ul id="tt" animate="true">
            </ul>
        </div>
        <div data-options="region:'center',border:false"  style="overflow: hidden;">
             <table id="dataTable" >
             <input type="hidden" id="Com_Id"/>
             </table> 
        </div>
         <div id="divDialog">
    </div>
    </div>
<script type="text/javascript">

$('#tt').tree({    
    url:'${rootPath}/com/getComTree?id=0', 
    id:'id',
    state:'state',
    text:'text',
    onSelect:function(node) {
		url="${rootPath}/com/getChildList?Com_Id="+node.id;
		$('#dataTable').datagrid('load',url);
		$('#Com_Id').val(node.id);
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
            idField : 'Com_Id',//此处根据实际情况进行填写
            columns:[[
                            {field:'ck',checkbox:true},
                            {field:'Com_Id',title:'机构ID',width:150},
                            {field:'Com_Code',title:'机构编码',width:150},
                            {field:'Com_SName',title:'机构简称',width:200},
                            {field:'Com_Type',title:'机构类型',width:100,
                                formatter: function(value,row,index){
                                    if(value=='E'){
                                        return '实体机构';
                                    }else if(value=='0'){
                                        return '虚拟机构';
                                    }
                                }
                            },
                            {field:'Com_Level',title:'机构层级',width:100,
                                formatter: function(value,row,index){
                                    if(value=='1'){
                                        return '一级（法人总公司）';
                                    }else if(value=='2'){
                                        return '二级（分公司）';
                                    }else if(value=='3'){
                                        return '三级（中支）';
                                    }else if(value=='4'){
                                        return '四级（支公司）';
                                    }
                                }
                            },
                            {field:'Com_Status',title:'机构状态',width:100,
                                formatter: function(value,row,index){
                                    if(value=='1'){
                                        return '有效';
                                    }else if(value=='0'){
                                        return '失效';
                                    }
                                }       
                            },
                            {field:'Remark',title:'备注',width:100},
                        //注：最后一行后面的逗号要去掉
            ]],
            toolbar : [{
                id : 'btnadd',
                text : '刷新',
                iconCls : 'icon-arrow_refresh',
                handler : function() {
                    var url="${rootPath}/com/getComTree";
                    $('#dataTable').datagrid('load',url);
                    $('#tt').tree('reload');
                }
               },'-',{
                id : 'btnadd',
                text : '新增',
                iconCls : 'icon-add',
                handler : function() {
                    addComrow();
                }
            }, '-', {
                id : 'btnedit',
                text : '编辑',
                iconCls : 'icon-edit',
                handler : function() {
                    editComrow();
                }
            }, '-', {
                id : 'btndel',
                text : '删除',
                iconCls : 'icon-cross',
                handler : function() {
                	deleComrow();
                }
            }],
            onLoadSuccess : function(data) {
                $('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
            }
        });
        
       });
   //新增
   function addComrow(){
        var Com_Id=$('#Com_Id').val();
        if(Com_Id.length==0) {
        	Com_Id = "";
            url = '${rootPath}/com/showComAddPage?Parent_Id='+Com_Id;
            $("#divDialog").dialog({
                title: "新增机构信息",
                width: 450,
                height: 550,
                href:url,
                cache: false,
                closed: false,    
                modal:true
            });
        } else {
        	url = '${rootPath}/com/showComAddPage?Parent_Id='+Com_Id;
	        $("#divDialog").dialog({
	            title: "新增机构信息",
	            width: 450,
	            height: 550,
	            href:url,
	            cache: false,
	            closed: false,    
	            modal:true
	        });
        }
   }
   
   //修改
   function editComrow(){
       var row = $('#dataTable').datagrid('getSelected');
       var Com_Id=$('#Com_Id').val();
       if (row){
			url = '${rootPath}/com/showComEditPage?Com_Id='+ row.Com_Id + "&Parent_Id=" + Com_Id;
			$("#divDialog").dialog({
			    title: "编辑数据字典",
			    width: 450,
			    height: 550,
			    href:url,
			    cache: false,
			    closed: false,    
			    modal:true
			});
       } else {
    	   $.messager.alert("warning","请选择你要更新的记录!");
			return;
       }
   }

   //删除
   function deleComrow(){
       var row = $('#dataTable').datagrid('getSelected');
       if (row){
               com.message('confirm','确定删除该机构吗？请谨慎操作！','提示',function(r) {
               if (r){
            	    $.post('${rootPath}/com/deleteCom',{rowId:row.Com_Id},function(data) {
                    if(data.result == 'true' || data.result == true) {
                    	$.messager.alert("success", data.msg);
                        goBack(1);
                    } else {
                    	$.messager.alerte("error", data.msg);
                    }       
                   });
               }
           });
       } else {
           $.messager.alert("warning","请选择你要操作的记录!");
           return;
      }
   }
   
    function goBack(flag) {
        $("#divDialog").window('close');
        if(flag==1) {
        	url="${rootPath}/com/getChildList?Com_Id="+$('#Com_Id').val();
            $('#dataTable').datagrid('reload',url);
            $('#tt').tree('reload');
        }
    }
        
</script>
</body>
</html>