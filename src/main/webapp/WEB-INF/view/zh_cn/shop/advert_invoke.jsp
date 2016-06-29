<%@ include file="/resources/jsp/taglibs.jsp"%>
<c:if test="${obj.apType =='img'}">
	document.write("<div style='float:left;overflow:hidden;'>");
  	document.write("<a href='${webPath}/advert_redirect.htm?url=${obj.apAccUrl}&id=${obj.adv_id}' target='_blank'>");
  	document.write("<img width='${obj.apWidth}' height='${obj.apHeight}' src='${webPath}/${obj.ap_acc.path}/${obj.ap_acc.name}' style='border:none;'/>");
  	document.write("</a>");
  	document.write("</div>"); 
</c:if>
<c:if test="${obj.apType =='slide'}">
  document.write("<script\/>");
  document.write("jQuery('document').ready(function(){");  
  document.write("jQuery('#advert').KinSlideshow({btn:{btn_bgColor:'#FFFFFF',btn_bgHoverColor:'#FF8921',btn_fontColor:'#000000',btn_fontHoverColor:'#EEEEEE',btn_borderColor:'#666666',btn_borderHoverColor:'#FFFFFF',btn_borderWidth:1}});");
  document.write("})");
  document.write("</script>");
  document.write("<div class='hdp' id='advert' style='visibility:hidden;'>");
  document.write("<ul>");
  <c:forEach var="adv" items="${obj.advs}">
      document.write("<a href='${webPath}/advert_redirect.htm?url=${adv.adUrl}&id=${adv.id}' target='_blank'><img src='${webPath}/${adv.ad_acc.path}/${adv.ad_acc.name}' width='${obj.apWidth}' height='${obj.apHeight}'/></a>");    		                             
  </c:forEach>
  document.write("</ul>");
  document.write("</div>");
</c:if>
<c:if test="${obj.apType =='text'}"> 
  document.write("<div style='width='${obj.apWidth}';height='${obj.apHeight}';float:left;overflow:hidden;'>");
  document.write("<a href='<c:url value="/advert_redirect.htm?url=${obj.apAccUrl}&id=${obj.adv_id}"/>' target='_blank'>");
  document.write("${obj.ap_text}");
  document.write("</a>");
  document.write("</div>"); 
</c:if>
<c:if test="${obj.apType =='scroll'}">
 <c:if test="${fn:length(obj.advs) > 0}">
 	document.write("<div class='conti_left' id='advert_left'><span></span></div>");
    document.write("<div class='conti'>");
    document.write("<ul>");
    <c:forEach var="adv" items="${obj.advs}">
    	document.write("<li>");
    	<%-- <c:forEach var="adv" items="${adv_list}"> --%>
    		document.write("<a href='${webPath}/advert_redirect.htm?url=${adv.adUrl}&id=${adv.id}' target='_blank'><img src='${webPath}/${adv.ad_acc.path}/${adv.ad_acc.name}' width='${obj.apWidth}' height='${obj.apHeight}'/></a>");
    	<%-- </c:forEach> --%>
    	document.write("</li>");
    </c:forEach>
    document.write("</ul>");
  	document.write("</div>");
  	document.write("<div class='conti_right' id='advert_right'><span></span></div>");
 </c:if>
</c:if>