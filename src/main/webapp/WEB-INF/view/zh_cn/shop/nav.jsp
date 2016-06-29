<%@ include file="/resources/jsp/taglibs.jsp"%>
<div class="nav"> <span class="navright"></span>
    <div class="navleft">
    	<b class="nav_lefts">全部商品分类
    <span>
    </span></b>
    </div>
    <ul class="navul">
    	<c:forEach var="nav" items="${navs}">
    		<c:set var="url" value="${nav.url}"></c:set>
    		<c:choose>
	  			<c:when test="${fn:indexOf(url, 'http://') != -1}">
	  				<li><a href="${url}" id="${nav.id}" <c:if test="${nav.newWin==1}"> target="_blank"</c:if> original_url="${nav.originalUrl}"><span>${nav.title}</span></a></li>
	  			</c:when>
	  			<c:otherwise>
	  				<li><a href="<c:url value="${url}"/>" id="${nav.id}" <c:if test="${nav.newWin==1}"> target="_blank"</c:if> original_url="${nav.originalUrl}"><span>${nav.title}</span></a></li>
	  			</c:otherwise>
	  		</c:choose>
    	</c:forEach>
    </ul>
  </div>
<script>
jQuery(document).ready(function(){
  jQuery(".navul a").each(function(){
    var original_url=jQuery(this).attr("original_url");
	if("${current_url}".indexOf(original_url)>=0){
	   jQuery(this).addClass("this");	
	}
  });
});
</script>