<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
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
			<%--<div class="fitem">
	    	<label>投诉编号:</label>
	        <input name="complain_num" class="easyui-textbox" disabled>
		</div> --%>
			<input type="hidden" name="menuId" id="menuId" value='${menuId}'>
			<input type="hidden" name="modId" id="modId" value='${modId}'>
			<input type="hidden" name="parentId" id="parentId" value='${parentId}'>
			<input type="hidden" name="menuName" id="menuName"
				value='${menuName }'>
			<input type="hidden" name="reqmenuid" id="reqmenuid"
				value='${reqmenuid }'>
			<input type="hidden" name="modIsleaf" id="modIsleaf"
				value='${modIsleaf }'>
			<input type="hidden" name="modLevel" id="modLevel"
				value='${modLevel }'>
			<input type="hidden" name="modName" id="modName"
				value='${modName }'>
			<%-- <input type="hidden" name="msg" id="msg"
				value='${msg }'>	 --%>
<!-- 			<div class="fitem"> -->
<!-- 				<input name="menuType"  type="radio" id="radio1"  style="width: 2%" value="sub"> -->
<!-- 				<lable>下级栏目</lable> -->
<!-- 			</div> -->
<!-- 			<div class="fitem"> -->
<!-- 				<input name="menuType"  type="radio" id="radio2"  style="width: 2%"  value="leaf"> -->
<!-- 				<lable>叶子栏目</lable> -->
<!-- 			</div> -->
			<div class="fitem">
				 <input name="menuType" type="radio" value="sub" id="sub" onclick="hideThe()"><lable>下级栏目</lable>
				 <input name="menuType" type="radio" value="col" id="col" onclick="hideThe()" ><lable>栏目介绍</lable>
				 <input name="menuType" type="radio" value="leaf" id="leaf" onclick="showThe()"><lable>叶子栏目</lable>
			</div>
			
			<div class="fitem" id="aaa" style="margin-left: 100px;color: orange;font-size: large;font-weight: bold;" >
				 <lable>栏目模板</lable>
			</div>
			<div class="fitem" id="moduleContent">
				 <input id="moduleContent1" name="moduleType" type="radio" value="03"  ><lable>内容模板</lable>
			</div>
			<div class="fitem" id="moduleListWithImg">
				 <input id="moduleListWithImg1" name="moduleType" type="radio" value="04"  ><lable>带图列表模板</lable>
			</div>
			<div class="fitem" id="moduleListWithLogo">
				 <input id="moduleListWithLogo1" name="moduleType" type="radio" value="05"  ><lable>logo列表模板</lable>
			</div>
			<div class="fitem" id="moduleListWithoutImg">
				 <input id="moduleListWithoutImg1" name="moduleType" type="radio" value="06"  ><lable>不带图列表模板</lable>
			</div>
			<div class="fitem" id="developCourseModule">
				 <input id="developCourseModule1" name="moduleType" type="radio" value="07"  ><lable>发展历程模板</lable>
			</div>
		</form>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()"style="width: 90px">确定</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="reset()" style="width: 90px">取消</a>
		</div>
	</div>


	<script type="text/javascript">
	
		$('#aaa').hide();
		$('#moduleContent').hide();
		$('#moduleListWithImg').hide();
		$('#moduleListWithLogo').hide();
		$('#moduleListWithoutImg').hide();
		$('#developCourseModule').hide();
		
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
			$('#developCourseModule').hide();
			$('#moduleContent1').removeAttr('checked');
			$('#moduleListWithImg1').removeAttr('checked');
			$('#moduleListWithLogo1').removeAttr('checked');
			$('#moduleListWithoutImg1').removeAttr('checked');
			$('#developCourseModule1').removeAttr('checked');
		}
		
	</script>
	
	<script type="text/javascript">
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
		
		var msg=$('#msg').val();
		if(msg!=null){
			$.messager.alert('提示',msg);
		}
		//保存记录
		function saveOrUpdate() {
			var r = $('#dataFormAdd').form('validate');
			var modId=null;modIsleaf=null;modLevel=null;modName=null;menuName=null;
			modId=$("#modId").val();modIsleaf='${modIsleaf}';modLevel='${modLevel}';
			modName='${modName}';menuName='${menuName}';
			var menuId='${menuId}';
			var parentId='${parentId}';	
			
			var val = $('input:radio[name="menuType"]:checked').val();
	        if(val == null) {
	        	$.messager.alert('提示',"请选中一条！",'error');
	            return false;
	        }
			if (!r) {
				return false;
			} else {
				var tabName = window.parent.wrapper.getCurrentTabTitle();
				if(modIsleaf=='1'){
					$.messager.alert('提示',"已是叶子栏目,无法新增！",'error');
				}else if(modLevel=='4'){
					$.messager.alert('提示',"已是叶子栏目,无法新增！",'error');
				}else if((modLevel=='3'&&val=='col')){
					$.messager.alert('提示',"没有栏目介绍！",'error');
				}else if((modLevel=='3'&&val=='sub')){
					$.messager.alert('提示',"没有下级栏目！",'error');
				}else if((modLevel=='3'&&val=='col')){
						$.messager.alert('提示',"没有栏目介绍！",'error');
				}else if(val=='sub'){
					var modNameNew=$('input:radio[name="menuType"]:checked').next("lable").html();
					url = '/module/addSubCol?modId=' + modId
					/* + '&menuName=' + encodeURI(menuName) */
					+ "&menuType=" + val 
					+ "&modName=" + escape(encodeURIComponent(modName))
					+ "&modIsleaf=" + modIsleaf
					+ "&menuId=" + menuId
					+ "&parentId=" + parentId
					+ "&modLevel=" + modLevel;
					window.parent.wrapper.addTabCloseOld("新增" +modNameNew,
					url, '', tabName);
				}else if(val=='col'){
						var modNameNew=$('input:radio[name="menuType"]:checked').next("lable").html();
						url = '/column/addColIntroduce?modId=' + modId
						/* + '&menuName=' + encodeURI(menuName) */
						+ "&menuType=" + val
						+ "&modName=" + escape(encodeURIComponent(modName))
						+ "&modIsleaf=" + modIsleaf
						+ "&menuId=" + menuId
						+ "&parentId=" + parentId
						+ "&modLevel=" + modLevel;
						window.parent.wrapper.addTabCloseOld("新增" +modNameNew,
						url, '', tabName);	
				}else{
					var modType = $('input:radio[name="moduleType"]:checked').val();
					var modNameNew=$('input:radio[name="moduleType"]:checked').next("lable").html();
					if (modType == null || modType == '') {
						$.messager.alert('提示',"请选择叶子栏目模板类型!",'error');
					} else {
						url = '/module/addLeafCol?modId=' + modId
						/* + '&menuName=' + encodeURI(menuName) */
						+ "&menuType=" + val
						+ "&modName=" + escape(encodeURIComponent(modName))
						+ "&modIsleaf=" + modIsleaf
						+ "&menuId=" + menuId
						+ "&parentId=" + parentId
						+ "&modLevel=" + modLevel
						+ "&leafType=" + modType;
						window.parent.wrapper.addTabCloseOld("新增" +modNameNew,url, '', tabName);
					}
				}
				
				};
			}
		function reset(){
			$('#aaa').hide();
			$('#moduleContent').hide();
			$('#moduleListWithImg').hide();
			$('#moduleListWithLogo').hide();
			$('#moduleListWithoutImg').hide();
			$('#developCourseModule').hide();
			
			
			$('#moduleContent1').removeAttr('checked');
			$('#moduleListWithImg1').removeAttr('checked');
			$('#moduleListWithLogo1').removeAttr('checked');
			$('#moduleListWithoutImg1').removeAttr('checked');
			$('#developCourseModule1').removeAttr('checked');
			
			$('#sub').removeAttr('checked');
			$('#leaf').removeAttr('checked');
			$('#col').removeAttr('checked');
			
		}
	
	</script>
</body>
</html>
