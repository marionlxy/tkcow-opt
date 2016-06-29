/**
* 模块名：爱佑汇共通脚本
* 程序名: 通用方法common_iu.js
* Copyright(c) 2015-02-12 t-wuke
**/

var strPath=window.top.location.pathname;     
var postPath=strPath.substring(0,strPath.substring(1).indexOf('/') + 1); 

// 获取【列表】全部字典数据（t-wuke）
function getDictList(dictTypes){
	var dictList= $.ajax({url:postPath+'/getDictList?dictTypes='+dictTypes+'',type:'GET',async:false,cache:false}).responseText;
	return $.parseJSON(dictList);
}
// 获取【列表】某类型的字典数据{需要先调用getDictList()方法}（t-wuke）
function getDictName(dictList,type,value){
	for(var i = 0 ; i < dictList.length ; i++){
		if(dictList[i].dictTypeId == type && dictList[i].dictId ==value)
			return dictList[i].dictName;
	}
}

