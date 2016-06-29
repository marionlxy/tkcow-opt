<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.net.URLDecoder" %>
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
	<div style="padding: 10px 60px 20px 60px">
		<form id="dataFormAdd">
			<input type="hidden" name="menuId" id="menuId" value='${menuId }'>
			<input type="hidden" name="menuName" id="menuName" value='${menuName }'>
			<div class="fitem">
				 <input name="menuType" type="radio" value="sub"  onclick="hideThe()"><lable>下级栏目</lable>
				 <input name="menuType" type="radio" value="leaf" id="leaf" onclick="showThe()"><lable>叶子栏目</lable>
			</div>
<!-- 			<div class="fitem" > -->
<!-- 				 <input name="menuType" type="radio" value="leaf" id="leaf" onclick="showThe()"><lable>叶子栏目</lable> -->
<!-- 			</div> -->
			
			<div class="fitem" id="aaa" style="margin-left: 100px;color: orange;font-size: large;font-weight: bold;" >
				 <lable>栏目模板</lable>
			</div>
			<div class="fitem" id="moduleContent">
				 <input id="moduleContent1" name="moduleType" type="radio" value="module"  ><lable>内容模板</lable>
			</div>
			<div class="fitem" id="moduleListWithImg">
				 <input id="moduleListWithImg1" name="moduleType" type="radio" value="module"  ><lable>带图列表模板</lable>
			</div>
			<div class="fitem" id="moduleListWithLogo">
				 <input id="moduleListWithLogo1" name="moduleType" type="radio" value="module"  ><lable>logo列表模板</lable>
			</div>
			<div class="fitem" id="moduleListWithoutImg">
				 <input id="moduleListWithoutImg1" name="moduleType" type="radio" value="module"  ><lable>不带图列表模板</lable>
			</div>
			<div class="fitem" id="developCourseModule">
				 <input id="developCourseModule1" name="moduleType" type="radio" value="module"  ><lable>发展历程模板</lable>
			</div>

		</form>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save" onclick="saveOrUpdate()"
				style="width: 90px">确定</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="returnParent(1)" style="width: 90px">取消</a>

		</div>
	</div>
	
	<script type="text/javascript">
	
	$('#aaa').hide();
	$('#moduleContent').hide();
	$('#moduleListWithImg').hide();
	$('#moduleListWithLogo').hide();
	$('#moduleListWithoutImg').hide();
		
		jQuery(function() {

			jQuery.ajaxSetup({
				cache : false
			});

			// 	$('#site').combobox({
			//         url:'${rootPath}/getDictCombox?dictType=kemu1',    
			//         valueField:'dictId',    
			//         textField:'dictName',
			//         panelWidth: 100,
			//         panelHeight:'auto'
			//  }); 
		});

		function showThe(){
			$('#aaa').show();
			$('#moduleContent').show();
			$('#moduleListWithImg').show();
			$('#moduleListWithLogo').show();
			$('#moduleListWithoutImg').show();
			$('#developCourseModule').show();
		}
		function hideThe(){
			$('#aaa').hide();
			$('#moduleContent').hide();
			$('#moduleListWithImg').hide();
			$('#moduleListWithLogo').hide();
			$('#moduleListWithoutImg').hide();
			$('#moduleContent1').removeAttr('checked');
			$('#moduleListWithImg1').removeAttr('checked');
			$('#moduleListWithLogo1').removeAttr('checked');
			$('#moduleListWithoutImg1').removeAttr('checked');
			$('#developCourseModule1').removeAttr('checked');
		}
		
		//保存记录
		function saveOrUpdate() {
			var r = $('#dataFormAdd').form('validate');
			if (!r) {
				return false;
			} else {
				$('#save').linkbutton('disable');
				$.post("${rootPath}/moduleDes/isFourLevel", $("#dataFormAdd")
						.serializeArray(), function(data) {
					if (data.result == 'true' || data.result == true) {
						// 			 	$.messager.show({title:'提示',msg:data.msg,showType:'show'});
						// $.messager.alert("提示");
						url = '/module/addSubCol?'+data;
						window.parent.wrapper.addTab('栏目标题-'+data.menuName,url,'');
					} else {
						// 				$.messager.alert('提示',data.msg,'error');
						$.messager.alert("提示");
					}
				});
			}
		}
	</script>
</body>
</html>
