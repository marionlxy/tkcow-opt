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
    <div  data-options="region:'north',height:60">
			<form id="dataComForm" method="post">
			<input type="hidden" name="userId" id="userId">
			    <br/>
				<div style="position: relative;">

					<label>　　机构编码:　　</label> <input name="comCode" id="comCode" class="z-text easyui-textbox" />
                    <label>　　机构名称:　　</label> <input name="comName" id="comName" class="z-text easyui-textbox" />
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doquery()" style="width:90px">查询</a>
				</div>
			</form>
		</div>
        <div data-options="region:'center',border:false"  style="overflow: hidden;">
             <table id="dataTableCom" >
             <input type="hidden" id="Com_Id"/>
             </table> 
        </div>
 </div>
<script type="text/javascript">
var userId;
    jQuery(function(){    
        userId = '${userId}';
        //初始化列表
        $('#dataTableCom').datagrid({
            iconCls:'icon-tip',
            singleSelect : true,
            fit:true,
            url:"${rootPath}/com/getComList",
            rownumbers:true,
            pagination:true,
            method : 'post',        
            idField : 'Com_Id',//此处根据实际情况进行填写
            columns:[[
                            {field:'ck',checkbox:true},
                            {field:'Com_Code',title:'机构编码',width:100},
                            {field:'Com_SName',title:'机构简称',width:160},
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
                            {field:'Com_Status',title:'机构状态',width:80,
                                formatter: function(value,row,index){
                                    if(value=='1'){
                                        return '有效';
                                    }else if(value=='0'){
                                        return '失效';
                                    }
                                }       
                            },
                            //{field:'Remark',title:'备注',width:100},
                        //注：最后一行后面的逗号要去掉
            ]],
            toolbar : [{
                id : 'btnadd',
                text : '刷新',
                iconCls : 'icon-arrow_refresh',
                handler : function() {
                	doquery();
                }
               }, '-', {
                id : 'btnedit',
                text : '确定',
                iconCls : 'icon-accept',
                handler : function() {
                    commitRow();
                }
            }, '-', {
                id : 'btndel',
                text : '取消',
                iconCls : 'icon-cross',
                handler : function() {
                	goBackFromOrganize();
                }
            }],
            onLoadSuccess : function(data) {
                $('#dataTableCom').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
            }
        });
        
       });
   
   // 更新用户机构
   function commitRow(){
       var row = $('#dataTableCom').datagrid('getSelected');
       if (row){
            var url = '${rootPath}/user/setCom?Com_Id='+ row.Com_Code + '&userId='+ userId;
            $.post(url, $("#dataComForm").serializeArray(),
                    function(data) {
                        if (data.result == 'true' || data.result == true) {
                            $.messager.alert("success", data.msg);
                            goBack(1);
                        } else {
                            $.messager.alert("error", data.msg);
                        }
                    });
       } else {
           $.messager.alert("warning","请选择你要设置的机构!");
            return;
       }
   }

   //表格查询
   function doquery() {
        //查询参数直接添加在queryParams中
        var queryParams = $('#dataTableCom').datagrid('options').queryParams;
        var fields = $('#dataComForm').serializeArray(); //自动序列化表单元素为JSON对象
        $.each(fields, function(i, field) {
            queryParams[field.name] = field.value; //设置查询参数
    
        });
        var url = '${rootPath}/com/getByconlist';
        $('#dataTableCom').datagrid('reload',url); //设置好查询参数 reload 一下就可以了
   }
   function goBackFromOrganize() {
        $("#divDialog").window('close');
	}    
</script>
</body>
</html>