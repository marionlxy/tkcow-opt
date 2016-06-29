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
    <form id="dataForm">
    <input type="hidden" name="Parent_Id">
    <input type="hidden" name="Com_Id">
            <div class="fitem">
                <label>机构ID:</label>
                <input name="comId" class="easyui-textbox" readonly data-options="required:true" >
            </div>
            <div class="fitem">
                <label>机构编码:</label>
                <input name="comCode" class="easyui-textbox" data-options="required:true">
            </div>
            <div class="fitem">
                <label>机构简称:</label>
                <input name="comSname" class="easyui-textbox" data-options="required:true">
            </div>
            <div class="fitem">
                <label>机构全称:</label>
                <input name="comName" class="easyui-textbox" data-options="required:true">
            </div>
            <div class="fitem">
                <label>机构类型:</label>
                <select name="comType" class="easyui-combobox" panelHeight="60px" required="required">
                        <option value=""></option>
                        <option value="E">实体机构</option>
                        <option value="C">虚拟机构</option>
                 </select>
            </div>
            <div class="fitem">
                <label>机构层级:</label>
                <select name="comLevel" class="easyui-combobox" panelHeight="110px" required="required">
                        <option value=""></option>
                        <option value="1">一级（法人总公司）</option>
                        <option value="2">二级（分公司）</option>
                        <option value="3">三级（中支）</option>
                        <option value="4">四级（支公司）</option>
                 </select>
            </div>
            <div class="fitem">
                <label>父机构ID:</label>
                <input name="comParent" id ="comParent" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>机构状态:</label>
                 <select name="comStatus" class="easyui-combobox" panelHeight="60px" required="required">
                        <option value=""></option>
                        <option value="1">有效</option>
                        <option value="0">失效</option>
                 </select>
            </div>
            <div class="fitem">
                <label>省份代码:</label>
                <input name="proviceNo" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>城市代码:</label>
                <input name="cityNo" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>区县代码:</label>
                <input name="districtNo" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>地址:</label>
                <input name="comAddress" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>邮编:</label>
                <input name="comPost" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>电话:</label>
                <input name="comPhone" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>联系人:</label>
                <input name="comLinkman" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>备注:</label>
                <input name="remark" class="easyui-textbox">
            </div>
            </form>
    <div id="dlg-buttons" align="center">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" id="save" onclick="saveOrUpdate()" style="width:90px">保存</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="goBack(0)" style="width:90px">取消</a>
   </div>
</div>
    
<script type="text/javascript">

var Parent_Id;
var Com_Id;
jQuery(function() { 
    Parent_Id ='${Parent_Id}';
    Com_Id ='${Com_Id}';
    $("#comParent").val(Parent_Id);
    
    if (Com_Id != null && Com_Id != "" ) {
        var url = '${rootPath}/com/getComById?rowId=' + Com_Id;
        $('#dataForm').form('load', url);
    }
});


//保存记录
function saveOrUpdate() {
    var r = $('#dataForm').form('validate');
    if(!r) {
        return false;
    } else {        
        $.post("${rootPath}/com/saveComInfo?rowId=" + Com_Id,$("#dataForm").serializeArray(),
        function(data) {            
            if(data.result == 'true' || data.result == true) {
                $.messager.alert("success", data.msg);
                goBack(1);
            } else {
                $.messager.alert("error", data.msg);
            }
        });
    }
}

</script>

</body>
</html>
