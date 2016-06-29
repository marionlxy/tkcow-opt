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
	
	<div class="" title="下级栏目·修改页面" style="padding: 10px 60px 20px 60px" >
		<form id="dataFormEdit" method="post" enctype="multipart/form-data">
			<input type="hidden" name="modId" value="${modId }">
			<input type="hidden" name="modParentName" value="${modParentName }">
			<input type="hidden" name="modParentId" >
			<input type="hidden" name="menuId" value="${menuId }"> 
			<input type="hidden" name="menuName" value="${menuName }">
			<input type="hidden" name="parentId"  >
			<input type="hidden" name="version" >
			<div class="fitem">
				<label>模块名称:</label> <input id="modName" name="modName" class="easyui-textbox" value="" 
					data-options="required:true"  />
			</div>
			<div class="fitem">
				<label>模块别名:</label> <input id="modByname" name="modByname" class="easyui-textbox" value=""
					data-options=""  />
			</div>
			<div class="fitem">
				<label>模块排序号:</label> <input id="modSquence" name="modSquence" value=""
					 
					missingMessage='必须填1-99之间的数字' class="easyui-numberbox"
					validType="length[0,2]" data-options="required:true" />
			</div>
			<div class="fitem">
				<label>链接名称(站内):</label> <input id="modUrl" name="modUrl" class="easyui-textbox" data-options="required:true"  value=""
					 />
			</div>
			<div class="fitem">
				<label>SEO标题:</label> <input id="modSeo" name="modSeo" class="easyui-textbox" 
					data-options="" value="" />
			</div>
			<div class="fitem">
				<label>SEO描述:</label> <input id="seoDes" name="seoDes" class="easyui-textbox" value=""
					 />
			</div>
			<div class="fitem">
				<label>描述:</label>
				<textarea id="modDes" name="modDes"  style=" border:1px solid #99bbe8;" validType="length[0,2000]"  rows="8" cols="50" class="easyui-validatebox" value="${modDes}" ></textarea>
			<!-- 	<textarea name="modDes" class="easyui-textbox"
					style="width: 316px; border: 1px solid #99bbe8; height: 200px"
					rows="4" cols="33" ></textarea> -->
			</div>

			<div class="fitem">
				<label>banner图片:</label>
				<input name="modNewBanner"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%">
				<!-- <input name="modNewBanner" class="easyui-textbox"> 
				<input name="modBanner"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%"  /> -->
				 <img id="ban" src="<%=basePath%>/${banImgUrl}" style="width:50px;height:50px;" />
			</div>
				<div class="fitem">
				<label>图标:</label> <input name="imgIcon" type='file' class="easyui-validatebox"
					validType="img_upload" data-options="prompt:'Choose a image...'"
					style="width: 25%" />
					<img id="icon" src="<%=basePath%>/${modIco}" style="width:50px;height:50px;" />
				</div>
				
			<div class="fitem">
				<label>小图片:</label> 
				<input name="modNewImg"
					class="easyui-validatebox" type='file' validType="img_upload"
					data-options="prompt:'Choose a image...'"
					style="width: 25%"/>
				<img id="img" src="<%=basePath%>/${modImg}" style="width:50px;height:50px;"/>
			</div>
			<div class="fitem">
				<label style="width: 150;">是否生成缩略介绍:</label>
				<c:choose>
					<c:when test="${modIsdes=='1' }">
					<span>是: </span><input class="validate[required] radio" type="radio" name="modIsdes" id="radio1" value="1" style="width: 30;" checked/>
					<input class="validate[required] radio" type="radio" name="modIsdes" id="radio2" value="0" style="width: 30;"/>
					</c:when>
					<c:otherwise>
					<span>否: </span>
					<input class="validate[required] radio" type="radio" name="modIsdes" id="radio1" value="1" style="width: 30;" />
					<input class="validate[required] radio" type="radio" name="modIsdes" id="radio2" value="0" style="width: 30;" checked/>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="adjoined-bottom">
			<div class="grid-container">
			<div class="grid-width-100">
			<div id="editor" name="editor">			
			</div></div></div></div>
			<!-- <div class="adjoined-bottom">
				<div class="grid-container">
					<div class="grid-width-100">
						<label>内容:</label>
						<textarea id="editor" name="editor" style="height: 40px;width: 200px"
							 data-options="required:true">
		            	</textarea>
					</div>
				</div>
			</div> -->
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
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" id="save" onclick="saveOrUpdate2()"
				style="width: 90px">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.parent.wrapper.rightMenuClick({ id: 'close' })"
				style="width: 90px">取消</a>
		</div>
		<div id="divDialog"></div>
	</div>

	<script type="text/javascript">
		initSample();
		var modId = '${modId}';
		
		jQuery(function() {
			
			// var modId = '${modId}';
			var basePath='<%=basePath%>';
			var content;
			$.post('${rootPath}/module/getOne',	{
				"modId" : modId
			},
					function(data){
						
						if (data.code == '0' || data.code == 0) {
							$("#modName").textbox('setValue',data.modName);
							$("#modByname").textbox('setValue',data.modByname);
							$("#modSquence").textbox('setValue', data.modSquence);
							$("#modUrl").textbox('setValue', data.modUrl);
							$("#modSeo").textbox('setValue', data.modSeo);
							$("#seoDes").textbox('setValue', data.seoDes);
							$("#modDes").text(data.modDes);;
							$("#img").attr('src',basePath+'/'+data.modImg); 
							$("#ban").attr('src',basePath+'/'+data.banImgUrl); 
							$("#icon").attr('src',basePath+'/'+data.modIco); 
							//$("#bannerId").val(ban.banId);
							//$("#banImgId").val(ban.banImgId);
							content = Base64.decode(data.content);
						}
					});
			
					setTimeout(function(){
						//alert(content);
						CKEDITOR.instances.editor.setData(content);
					},500);
		});
		//初始化列表
		$.ajax({
			url : '${rootPath}/banner/listModuleContent',
			type : 'post',
			data : "modId=" + modId,
			success : function(data) {
				for (var i = 0; i < data.total; i++) {
					$("#dataTable")
							.append(
									'<tr style="height:30px;line-height:30px;">'
											+ '<td style="border:1px solid #ccc;">'
											+ data.rows[i].BAN_IMG_NUM
											+ '</td>'
											//    		   		+'<td  style="border:1px solid #ccc;"><img src='+data.rows[i].BAN_IMG_URL+' style="width:30px;height:30px;"></td>'
											+ '<td  style="border:1px solid #ccc;">'
											+ showImg(data.rows[i].BAN_IMG_URL)
											+ '</td>'
											+ '<td style="border:1px solid #ccc;"><input type="button" value="删除" onclick="delerow(this)">'
											+ '<input type="hidden" value='+data.rows[i].BAN_ID+'>'
											+ '<input type="hidden" value='+data.rows[i].BAN_IMG_ID+'>'
											+ '<input type="hidden" value='+data.rows[i].BAN_IMG_NAME+'>'
											+ '<input type="hidden" value='+data.rows[i].BAN_IMG_RENAME+'>'
											+ '<input type="hidden" value='+data.rows[i].BAN_TITLE+'>'
											+ '<input type="hidden" value='+data.rows[i].BAN_IMG_OUTSIDE+'></td>'
											+ '</tr>');
				}
			}
		})
		$("#upload_id").click(function() {
			addImgs();
		});

		
		//提交记录
		function saveOrUpdate2() {
			var editorText=CKEDITOR.instances.editor.getData();
			//var BaseText=encode64(editorText);
			var BaseText=Base64.encode(editorText);
			$("#editor_id").val(BaseText);
			
			var basePath2='<%=basePath%>'+'/';
			var r = $('#dataFormEdit').form('validate');
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
					trList.BAN_IMG_URL=BAN_IMG_URL.replace(basePath2,'');
					trList.BAN_ID=BAN_ID;
					trList.BAN_IMG_ID=BAN_IMG_ID;
					trList.BAN_IMG_NAME=BAN_IMG_NAME;
					trList.BAN_IMG_RENAME=BAN_IMG_RENAME;
					trList.BAN_TITLE=BAN_TITLE;
					trList.BAN_IMG_OUTSIDE=BAN_IMG_OUTSIDE;
					lists.push(trList);
			}
			$("#bannerList_id").val(JSON.stringify(lists));
	
				var r = $('#dataFormEdit').form('validate');
				if (!r) {
					return false;
				} else {
					$('#dataFormEdit').form(
							'submit',
							{
								url : '${rootPath}/module/editFormSub',
								onSubmit : function() {
									$("#dataFormEdit").serializeArray();
								} ,
								success : function(data) {
									var ajaxobj = eval("(" + data + ")");
									if (ajaxobj.result == 'true'
											|| ajaxobj.result == true) {
										$.messager.alert('提示', ajaxobj.msg, function(){
											//window.parent.wrapper.rightMenuClick({ id: 'close' });
											url = '/module/subList?reqMenuId=' + ajaxobj.reqMenuId + '&backUp2='
											+ ajaxobj.backUp2;
											/* openWin(url); */
											var tabName = window.parent.wrapper.getCurrentTabTitle();
											window.parent.wrapper.addTabCloseOld(ajaxobj.menuName, url, '', tabName);
									});
									$('#save').linkbutton('disable');
									} else {
										$.messager
												.alert("提示", ajaxobj.msg, 'error');
									}
								} 
							});
				}
			}
		//返回父页面  
		function goBack(flag) {
			parent.returnParent(flag);
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
