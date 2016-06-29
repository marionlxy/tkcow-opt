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

   <div style="width :100%;height: 100%;" >  
		<div id= "config"></div >  
		<div style='margin-left : 150px;margin-bottom: 5px;vertical-align : bottom;position: absolute;bottom : 5px;'>  
		      <a href="javascript:void(0);" id="btn-ok" onclick="$('#MyPopWindow').window('close');" class ="easyui-linkbutton" iconCls="icon-ok" >确定</a>  
		</div>  
   </div> 
    
<script type="text/javascript">

	var columnStr;
	jQuery(function() {
		columnStr = '${columnStr}';
		initConfig();
	});

	function initConfig() {
		//alert('${param.columnStr}');  
		$('#config').html("");
		var columns = eval(columnStr);
		var nodeText = "<table></table>";
		var table = $(nodeText);
		var tr = null;
		var td = null;
		$.each(columns, function(i, col) {
			if (i % 4 == 0) {
				if (i > 0) {
					table.append(tr);
				}
				tr = $("<tr></tr>");
			}
			nodeText = "<td width='100px'><input type='checkbox' value='"
					+ col.field + "' " + (col.hidden == false ? "checked" : "")
					+ ">";
			nodeText += col.title + "</input>";
			//alert(nodeText);
			td = $(nodeText);
			tr.append(td);
		});
		
		if (columns.length % 4 != 0) {
			table.append(tr);
		}
		$('#config').append(table);
	}
	
	function getConfiged() {  
		var checks=$('#config' ).children('table').first().find( 'input');  
		return checks;  
	} 
</script>

</body>
</html>
