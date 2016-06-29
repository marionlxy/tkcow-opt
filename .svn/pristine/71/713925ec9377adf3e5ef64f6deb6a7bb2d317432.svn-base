<%@ include file="/resources/jsp/taglibs.jsp"%>
<script>
jQuery(document).ready(function(){
  jQuery(".toph_bgsear li").mouseover(function(){
      jQuery(".toph_bgsear>li").show();
  }).mouseleave(function(){
       jQuery(".toph_bgsear>li").last().hide();
  }).click(function(){
	 var index=jQuery(this).index();
	 if(index==1){
	   var f_text=jQuery(".toph_bgsear li").find("a").first().text();
       jQuery(".toph_bgsear li").find("a").first().html(jQuery(this).find("a").text()+"<s></s>");
	   jQuery(".toph_bgsear li").find("a").last().text(f_text);
	   jQuery("#type").val(jQuery(this).attr("type"));
	   jQuery(".toph_bgsear>li").last().hide();
	 }
  });

  jQuery(".two_code_a").click(function(){
	  jQuery(this).parent().remove();
	  });
	
});
function search_form(){
  var keyword=arguments[0];
  var type=arguments[1];
  if(keyword!=""&&keyword!=undefined){
   jQuery("#keyword").val(keyword);
  }
  if(type!=""&&type!=undefined){
    jQuery("#type").val(type);
  }
  jQuery("#searchForm").submit();	
  jQuery("#keyword").val("");
}
</script>

<form action="<c:url value="/search.htm"/>" method="post" target="_blank" id="searchForm">
  <input name="type" type="hidden" id="type" value="${type}" />
  <div class="head">							  
    <div class="logo">
    	 <c:choose>
			<c:when test="${not empty config.websiteLogo}">    
				<a href="<c:url value="/index.htm"/>"><img src="<c:url value="${imageWebServer}/${config.websiteLogo.path}/${config.websiteLogo.name}"/>" border="0" /> </a>
			</c:when>
			<c:otherwise>
				 <a href="<c:url value="/index.htm"/>"><img src="<c:url value="/resources/style/system/front/default/images/logo.png"/>" border="0" /></a>
			</c:otherwise>
		</c:choose>
    </div>
    
    <div class="toph_bigsearch">
      <div class="toph_sear">
        <ul class="toph_bgsear">
          <li class="this" type="goods"><a href="javascript:void(0);">宝贝<s></s></a></li>
          <li style="display:none" type="store"><a href="javascript:void(0);">店铺</a></li>
        </ul>
        <input name="keyword" type="text" id="keyword" placeholder="搜索其实真的很简单！" x-webkit-speech lang="zh-CN" onwebkitspeechchange="jQuery('#searchForm').submit()" class="toph_sear_txt"/>
        <input name="input" type="button" value="搜索" style="cursor:pointer;" onclick="search_form();" class="toph_sear_btn" />
      </div>
      <div class="keyword">
      	<c:forEach var="info" items="${fn:split(config.hotsearch, ',') }">
      		<a href="javascript:void(0);" onclick="search_form('${info}','goods');">${info}</a> 
      	</c:forEach> 
      </div>
    </div>
  </div>
</form>
