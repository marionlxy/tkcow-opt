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
<div >
	<form id="dataFormShow">
	<input type="hidden" name="rowId">
			<div class="fitem">
                <label>日志编码:</label>
                <input name="logId" id="logId" class="easyui-textbox" readonly >
            </div>
            <div class="fitem">
                <label>日志类型:</label>
                <select class="easyui-combobox" name="logType" style="width:80px" panelHeight="60px" disabled >
                            <option value=""></option>                      
                            <option value="1">成功日志</option>
                            <option value="0">异常日志</option>
                </select>
            </div>
            <div class="fitem">
                <label>操作类型:</label>
                <select class="easyui-combobox" name="operType" style="width:80px" panelHeight="80px" disabled >
                            <option value=""></option>                      
                            <option value="C">新增</option>
                            <option value="R">读取</option>
                            <option value="U">更新</option>
                            <option value="D">删除</option>
                </select>
            </div>
            <div class="fitem">
                <label>操作名称:</label>
                <input name="operName" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>日志信息:</label>
                <input name="message" class="easyui-textbox" readonly >
            </div>
            <div class="fitem">
                <label>日志等级:</label>
                <input name="logLevel" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>操作人id:</label>
                <input name="userId" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>操作人:</label>
                <input name="userName" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>操作时间:</label>
                <input name="createTime" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>客户ip:</label>
                <input name="clientIp" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>请求URL:</label>
                <input name="reqUrl" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>请求方法:</label>
                <input name="reqMethod" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>请求参数:</label>
                <input name="reqParam" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>备注1:</label>
                <input name="backup1" class="easyui-textbox" readonly>
            </div>
            <div class="fitem">
                <label>备注2:</label>
                <input name="backup2" class="easyui-textbox" readonly>
            </div>
			</form>
    <div id="dlg-buttons" align="center">
       <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">保存</a> -->
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="returnParent(1)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

var rowId;
jQuery(function(){

    //初始化下拉框-示例，请根据需要自定义实现
    /*
     $('#combo1').combobox({    
            url:'${rootPath}/getDictlist?dicttypeid=userstatus',    
            valueField:'dictid',    
            textField:'dictname',
            panelHeight:'auto'
        }); 
      */ 
    rowId ='${rowId}';
    $('#logId').val(rowId);
    if (rowId != null && rowId != ""){
        var url='${rootPath}/log/getOne?rowId=' + rowId;
        $('#dataFormShow').form('load', url);
    }
});

</script>

</body>
</html>
