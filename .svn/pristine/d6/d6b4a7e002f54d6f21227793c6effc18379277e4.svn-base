<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/include.jsp"%>
<script src="<%=basePath%>/static/ckeditor/ckeditor.js"></script>
<script src="<%=basePath%>/static/ckeditor/samples/js/sample.js"></script>
<script src="<%=basePath%>/static/js/imageValidate.js"></script>
<title></title>
<style>
input{height:20px;}
</style>
</head>
<body>
	
	<div class="" title="下级栏目·新增页面" style="padding: 10px 60px 20px 60px">
		<form id="dataFormSubCloAdd"  method="post"
			enctype="multipart/form-data">
			<%--<div class="fitem">
	    	<label>投诉编号:</label>
	        <input name="complain_num" class="easyui-textbox" disabled>
		</div> --%>
		
			<input type="hidden" name="modParentId" value='${modParentId }'>
			<input type="hidden" name="modIsleaf" value='${modIsleaf }'>
			<input type="hidden" name="menuType" value='${menuType }'>
			<input type="hidden" name="modParentName" value='${modParentName }'>
			<input type="hidden" name="modLevel" value='${modLevel }'>
			<input type="hidden" name="menuId" id="menuId" value='${menuId}'>
			<input type="hidden" name="menuName" id="menuName" value='${modParentName}'>
			<input type="hidden" name="parentId" id="parentId" value='${parentId}'>
			<div class="fitem">
				<label>模块名称:</label> <input name="modName" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
				<label>模块别名:</label> <input name="modByname" class="easyui-textbox" data-options="">
			</div>
			<div class="fitem">
				<label>模块排序号:</label> <input name="modSquence"  missingMessage='必须填1-99之间的数字' class="easyui-numberbox" validType="length[0,2]" data-options="required:true">
			</div>
			<div class="fitem">
				<label>链接名称(站内):</label> <input name="modUrl" class="easyui-textbox" data-options="required:true">
			</div>
			<div class="fitem">
				<label>SEO标题:</label> <input name="modSeo" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>SEO描述:</label> <input name="seoDes" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>描述:</label>
				<!-- <input name="complainMsg"  validType="length[0,30]" required="required"> -->
				<textarea name="modDes" class="easyui-validatebox " validType="length[0,2000]"
					style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33"></textarea>
			</div>

			<div class="fitem">
				<label>banner图片:</label> <input name="modBanner"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%" />
			</div>
			<div class="fitem">
				<label>图标:</label> <input name="imgIcon" type='file' class="easyui-validatebox"
					validType="img_upload" data-options="prompt:'Choose a image...'"
					style="width: 25%" />
			</div>
			<div class="fitem">
				<label>小图片:</label> <input name="modImg" type='file' class="easyui-validatebox"
					validType="img_upload" data-options="prompt:'Choose a image...'"
					style="width: 25%" />
			</div>
			<div class="fitem">
				<label style="width: 150;">是否生成缩略介绍::</label>
				<span>是: </span><input class="validate[required] radio" type="radio" name="modIsdes" id="radio1" value="1" style="width: 30;" checked/>
				<span>否: </span><input class="validate[required] radio" type="radio" name="modIsdes" id="radio2" value="0" style="width: 30;"/>
			</div>
			<div class="adjoined-bottom">
				<div class="grid-container">
					<div class="grid-width-100">
						<label>内容:</label>
						<textarea id="editor" name="editor" style="height: 40px;width: 200px"
							 data-options="required:true">
		            	</textarea>
					</div>
				</div>
			</div>
			<span style="display:block;float:left;margin-right:24px;margin-left:54px;">图片组：</span>
			   <table cellpadding="0"  style="width:600px;border:1px solid #ccc;text-align:center;float:left;">
			   	<tr style="height:30px;line-height:30px;">
			   		<td style="border:1px solid #ccc;">排序号</td>
			   		<td  style="border:1px solid #ccc;">图片</td>
			   		<td style="border:1px solid #ccc;">操作</td>
			   	</tr>
			   	<tbody id="dataTable" ></tbody>
			   </table>
			   <input name="bannerList" type="hidden" value='' id="bannerList_id">
		   <input id="upload_id" type="button" value="上传" style="float:left;height:30px;line-height:30px;margin-left:20px;width:80px;">
		   <div style="clear:both"></div>
		   
			<input type="hidden" id="editor_id" name="editor_name">	
		</form>
		<div id="divDialog"></div>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save" onclick="saveOrUpdate1()"
				style="width: 90px">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })" style="width: 90px">取消</a>

		</div>
	</div>

	<script type="text/javascript">
		initSample();
		var banImgId;
		jQuery(function() {
			$("#upload_id").click(function(){
				addImgs();
			});
			banImgId = '${banImgId}';
			
			jQuery.ajaxSetup({
				cache : false
			});
		});

		//保存记录
		function saveOrUpdate1() {
			var editorText=CKEDITOR.instances.editor.getData();
			var BaseText=Base64.encode(editorText);
			//var BaseText=Base64.encode(editorText);
			$("#editor_id").val(BaseText);
			var basePath='<%=basePath%>'+'/';
			var r = $('#dataFormSubCloAdd').form('validate');
			var lists=new Array();
			var trLeng=$("#dataTable").find("tr").length;
			for(var i=0;i<trLeng;i++){
				var trList={};
					var BAN_IMG_NUM=$("#dataTable").find("tr").eq(i).find("td").html();
					var BAN_IMG_URL=$("#dataTable").find("tr").eq(i).find("td").next("td").find("img").attr("src");
					var BAN_ID=$("#dataTable").find("tr").eq(i).find("td").next("td").next("td").find("input").next("input").val();
					var BAN_IMG_ID=$("#dataTable").find("tr").eq(i).find("td").next("td").next("td").find("input").next("input").next("input").val();
					var BAN_IMG_NAME=$("#dataTable").find("tr").eq(i).find("td").next("td").next("td").find("input").next("input").next("input").next("input").val();
					var BAN_IMG_RENAME=$("#dataTable").find("tr").eq(i).find("td").next("td").next("td").find("input").next("input").next("input").next("input").next("input").val();
					var BAN_TITLE=$("#dataTable").find("tr").eq(i).find("td").next("td").next("td").find("input").next("input").next("input").next("input").next("input").next("input").val();
					var BAN_IMG_OUTSIDE=$("#dataTable").find("tr").eq(i).find("td").next("td").next("td").find("input").next("input").next("input").next("input").next("input").next("input").next("input").val();
					trList.BAN_IMG_NUM=BAN_IMG_NUM;
					trList.BAN_IMG_URL=BAN_IMG_URL.replace(basePath,'');
					trList.BAN_ID=BAN_ID;
					trList.BAN_IMG_ID=BAN_IMG_ID;
					trList.BAN_IMG_NAME=BAN_IMG_NAME;
					trList.BAN_IMG_RENAME=BAN_IMG_RENAME;
					trList.BAN_TITLE=BAN_TITLE;
					trList.BAN_IMG_OUTSIDE=BAN_IMG_OUTSIDE;
					lists.push(trList);
			}
			$("#bannerList_id").val(JSON.stringify(lists));
			
			if (!r) {
				return false;
			} else {
				$('#dataFormSubCloAdd').form(
						'submit',
						{
							url : '${rootPath}/module/addFormSub',
							onSubmit : function() {
								$("#dataFormSubCloAdd").serializeArray();
// 								alert('a');
							} ,
						//	dataType:'text/html',
							success : function(data) {
								$('#save').linkbutton('disable');
								var ajaxobj = eval("(" + data + ")");
								if (ajaxobj.result == 'true'
										|| ajaxobj.result == true) {
									$.messager.alert('提示', ajaxobj.msg, function(){
									url = '/module/subList?reqMenuId=' + ajaxobj.reqMenuId + '&backUp2='
										+ ajaxobj.backUp2;
										//openWin(url); 
									var tabName = window.parent.wrapper.getCurrentTabTitle();
									window.parent.wrapper.addTabCloseOld(ajaxobj.menuName, url, '', tabName);
								});
									
								} else {
									//$.messager.alert('提示',data.msg,'error');
									$.messager
											.alert("提示", ajaxobj.msg, 'error');
									$('#save').linkbutton('enable');
								}
							} 
						});
				}
		}
		
		function delerow(obj){
			var banImgId=$(obj).next("input").next("input").val();
		    if (banImgId!="" && banImgId!=undefined && banImgId!=null){
		        $.messager.confirm('提示','确定要删除行记录吗？',function(r){
		            if (r){
		                $.post('${rootPath}/banner/del',{banImgId:banImgId},function(data){
		                	
		                	if(data.result == 'true' || data.result == true)
							{
		                		$(obj).parents("tr").remove();
		                		$('#dataTable').datagrid('reload');    // reload the user data
							}
							else
							{
								$.messager.alert('提示',data.msg,'error');
							}                    	
		                });
		            }
		        });
		    }else{
		    	$(obj).parents("tr").remove();
		    }
		}
		
		/**
		 * 增加图片组<br/>
		 */
		function addImgs() {
			url = '${rootPath}/moduleDes/showImgsAddPage';
			$("#divDialog").dialog({
		        title: "新增图片组",
		        width: 450,
		        height: 250,
		        href:url,
				cache: false,
				closed: false,    
			    modal:true
		    });
		}
		 
			//显示图片
			function showImg(image){
			if(image!=null){
					return '<img src=<%=basePath%>'+'/'+image+' width="25px" height="20px"/>';
			}else{
				return '<img src=<%=basePath%>'+'/'+image+' width="25px" height="20px"/>';
			}
			
			}
		 
			//返回父页面  
			function returnParent(dataStr){
				var data = eval('(' + dataStr + ')');
					$("#divDialog").window('close');
				$("#dataTable").append('<tr style="height:30px;line-height:30px;">'
		   				+'<td style="border:1px solid #ccc;">'+data.BAN_IMG_NUM+'</td>'
		   				+'<td style="border:1px solid #ccc;">'+showImg(data.BAN_IMG_URL)+'</td>'
//		    		   		+'<td  style="border:1px solid #ccc;"><img src='+data.BAN_IMG_URL+' style="width:30px;height:30px;"></td>'
		   		   		+'<td style="border:1px solid #ccc;"><input type="button" value="删除" onclick="delerow(this)">'
		   		   		+'<input type="hidden" value='+data.BAN_ID+'>'
		    		   	+'<input type="hidden" >'
		   		   		+'<input type="hidden" value='+data.BAN_IMG_NAME+'>'
		   		   		+'<input type="hidden" value='+data.BAN_IMG_RENAME+'>'
		   		   		+'<input type="hidden" value='+data.BAN_TITLE+'>'
		   		   		+'<input type="hidden" value='+data.BAN_IMG_OUTSIDE+'></td>'
		   		   		+'</tr>');
					
				
			}
	</script>
</body>
</html>
