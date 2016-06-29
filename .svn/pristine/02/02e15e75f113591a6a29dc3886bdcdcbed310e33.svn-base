<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%@ include file="/include.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/script/css/icon.css">
    <title></title>
</head>
<body>
	<div style="margin: 5px; height: 70px; overflow: auto;">
		<div style="margin:10px;float:left">
		<div style="margin-left:20px;height:30px;float:right" id="hid">
		    <input type="hidden" id="roleCode"/>
		     <input type="hidden" id="clcoy"/>
		     <input type="hidden" id="combranch"/>
		     <input type="hidden" id="comage"/>
			分公司<input id="combo1"   style="width:80px"/> 
			中支：<input id="combo2" style="width:80px"/>
			 营销服务部：<input style="width:80px" id="combo3" />
			     <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a> 
		</div>
		</div>
	</div>
   <table id="userDataGrd"></table> 
	 <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="save()" style="width:90px">添加</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="Back(0)" style="width:90px">取消</a>
   </div> 
<script type="text/javascript">
var comCoy; 
var comBranch;
var comAgenter;
var roleCode;
jQuery(function(){ 
	roleCode='${roleCode}';
	comCoy='${comCode}';
	comBranch='${comBranch}';
	comAgenter='${comAgenter}';
	$('#roleCode').val(roleCode);
	$('#clcoy').val(comCoy);
	$('#combranch').val(comBranch);
	$('#comage').val(comAgenter);
   
	 if("0"==comCoy){//总公司
		 $('#combo1').combobox({
		        url:'${rootPath}/com/getComOne',    
		        valueField:'comCode',    
		        textField:'comName',
		        onSelect: function(rec){    
		            var url = '${rootPath}/com/getComTwo?comId='+rec.comId;   
		            $('#combo2').combobox('reload', url);
		            $('#combo2').combobox('clear'); //清除上次返回的数据 
		           $('#clcoy').val(rec.comCode);
		          },
		    }); 
		 $('#combo2').combobox({
		        valueField:'comCode',    
		        textField:'comName',
		     	 onSelect: function(rec){    
			            var url = '${rootPath}/com/getComTwo?comId='+rec.comId;   
			            $('#combo3').combobox('reload', url);
			            $('#combo3').combobox('clear'); //清除上次返回的数据 
			           $('#combranch').val(rec.comCode);
			        },
			        onLoadSuccess: function(){  
			     		var val=$('#combo2').combobox('getData');
			     		$('#combo2').combobox('select',val[0].comCode)
                      },
		    }); 
		 $('#combo3').combobox({
		        valueField:'comCode',    
		        textField:'comName',
		          onSelect:function(rec){
		        	 $('#comage').val(rec.comCode)  
		          },
		    }); 
	   }else{//分公司
		   if(comAgenter== null || comAgenter.trim()==''){//有分公司、有中支、无营销服务部
		 		  $('#combo1').combobox({
				        url:'${rootPath}/com/getComOne',    
				        valueField:'comCode',    
				        textField:'comName',
				     	readonly:true,
				     	onLoadSuccess: function(){  
				     		var val=$('#combo1').combobox('getData');
				     		var clcoy=$('#clcoy').val();
				     		for(var i=0;i<val.length;i++){
				     			if(val[i].comCode==clcoy){
				     				$(this).combobox('select',val[i].comCode);
				     				var url = '${rootPath}/com/getComTwo?comId='+val[i].comId; 
				     				 $('#combo2').combobox('reload', url);
				     			}
				     		}
				          },
				    }); 
				 $('#combo2').combobox({
				        valueField:'comCode',    
				        textField:'comName',
				     	readonly:true,
				     	onLoadSuccess: function(){  
				     		var val=$('#combo2').combobox('getData');
				     		var combranch=$('#combranch').val();
				     		for(var i=0;i<val.length;i++){
				     			if(val[i].comCode==combranch){
				     				$(this).combobox('select',val[i].comCode);
				     				var url = '${rootPath}/com/getComTwo?comId='+val[i].comId; 
				     				 $('#combo3').combobox('reload', url);
				     			}
				     		}
				          },
				    }); 
				 $('#combo3').combobox({
				        valueField:'comCode',    
				        textField:'comName',
				        onSelect: function(rec){    
				           $('#comage').val(rec.comCode);
				          }  
				    }); 
			  }else{//有分公司、有中支、有营销服务部
				  $('#combo1').combobox({
				        url:'${rootPath}/com/getComOne',    
				        valueField:'comCode',    
				        textField:'comName',
				     	readonly:true,
				     	onLoadSuccess: function(){  
				     		var val=$('#combo1').combobox('getData');
				     		var clcoy=$('#clcoy').val();
				     		for(var i=0;i<val.length;i++){
				     			if(val[i].comCode==clcoy){
				     				$(this).combobox('select',val[i].comCode);
				     				 var url = '${rootPath}/com/getComTwo?comId='+val[i].comId; 
				     				 $('#combo2').combobox('reload', url);
				     			}
				     		}
				          },
				    }); 
				 $('#combo2').combobox({
				        valueField:'comCode',    
				        textField:'comName',
				     	readonly:true,
				     	onLoadSuccess: function(){  
				     		var val=$('#combo2').combobox('getData');
				     		var combranch=$('#combranch').val();
				     		for(var i=0;i<val.length;i++){
				     			if(val[i].comCode==combranch){
				     				$(this).combobox('select',val[i].comCode);
				     				var url = '${rootPath}/com/getComTwo?comId='+val[i].comId; 
				     				 $('#combo3').combobox('reload', url);
				     			}
				     		}
				          },
				    }); 
				 $('#combo3').combobox({
				        valueField:'comCode',    
				        textField:'comName',
				        readonly:true,
				     	onLoadSuccess: function(){  
				     		var val=$('#combo3').combobox('getData');
				     		var comage=$('#comage').val();
				     		var comagent =comage.trim();
				     		for(var i=0;i<val.length;i++){
				     			if(val[i].comCode==comagent){
				     				$(this).combobox('select',val[i].comCode);
				     			}
				     		}
				          },
				    }); 
				
			  }
			
	   }
});	
  
$('#btn').click(function(){
	var comCoy =$('#clcoy').val();
	var comBranch =$('#combranch').val();
	var comAgenter =$('#comage').val();
	var url='${rootPath}/user/list?comCoy='+comCoy+'&comBranch='+comBranch+'&comAgenter='+comAgenter;
	$('#userDataGrd').datagrid({
   		iconCls:'icon-tip',
   		rownumbers:true,
   		pagination:true,
   		fitColumns:true,
   		method : 'post',
   		url:url,
		idField : 'userId',
		columns:[[
		          {field:'ck',checkbox:true},
					{field:'userCode',title:'用户编码',width:30},
					{field:'userName',title:'用户名',width:40},
					{field:'addProp',title:'用户性质',width:30,
						formatter: function(value,row,index){
		                            if(value=='H'){
		                            	return '总公司';
		                            }else if(value=='B'){
		                            	return '分公司';
		                            }
						}	
					},
					{field:'workStatus',title:'工作状态',width:30,
						formatter: function(value,row,index){
		                            if(value=='1'){
		                            	return '在岗';
		                            }else if(value=='0'){
		                            	return '离岗';
		                            }
						}	
					},
					{field:'comCoy',title:'分公司',width:30},
					{field:'comBranch',title:'中支',width:30},
		 ]],
	});
});

function save()
{
	   var rows = $('#userDataGrd').datagrid('getSelections');
	   console.info(rows)
	   var roleCode=$('#roleCode').val();
	   $.ajax({
			 type: "POST",
	            url: "${rootPath}/userRole/saveUserRoles?roleCode="+roleCode,
	            contentType:"application/json",
	            data: JSON.stringify(rows),
	            dataType: "json",
	            success: function (data) {
	            	if(data.result == 'true' || data.result == true)
	    			{
	    				com.message("success", "该角色用户添加成功！");
	    				Back(1);
	    			}
	    			else
	    			{
	    				com.message("error", "该角色用户添加失败 ！");
	    			}
	              }
	           });
}



</script>

</body>
</html>