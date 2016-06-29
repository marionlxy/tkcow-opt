package com.taikang.udp.sys.util;


import org.apache.commons.lang3.StringEscapeUtils;

public class StringEscapeUtilsTest {

	public static void main(String args[]){
		
		String sql="1' or '1'='1";
		System.out.println("防SQL注入:"+StringEscapeUtils.escapeJava(sql));	//防SQL注入
		
		System.out.println("转义HTML,注意汉字:"+StringEscapeUtils.escapeHtml4("<a>“</a><a>’</a>中文<a>&</a><<a>></a>")); 	//转义HTML,注意汉字
		System.out.println("反转义HTML:"+StringEscapeUtils.unescapeHtml4("<a>“</a><a>’</a>中文<a>&</a><<a>></a>"));	//反转义HTML
		
		System.out.println("转成Unicode编码："+StringEscapeUtils.escapeJava("陈磊兴")); 	//转义成Unicode编码
		
		System.out.println("转义XML："+StringEscapeUtils.escapeXml("<name>陈磊兴</name>")); 	//转义xml
		System.out.println("反转义XML："+StringEscapeUtils.unescapeXml("<name>陈磊兴</name>")); 	//转义xml

	}
}
