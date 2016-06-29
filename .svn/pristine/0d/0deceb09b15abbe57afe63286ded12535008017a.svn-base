<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/resources/jsp/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${webPath}/resources/style/system/front/default/css/public.css" type="text/css" rel="stylesheet" />
<link href="${webPath}/resources/style/system/front/default/css/index.css" type="text/css" rel="stylesheet" />
<script src="${webPath}/resources/js/jquery-1.6.2.js"></script>
<script src="${webPath}/resources/js/jquery.lazyload.js"></script>
<script src="${webPath}/resources/js/jcarousellite_1.0.1.min.js"></script>
<script src="${webPath}/resources/js/jquery.KinSlideshow.min.js"></script>
<script>
jQuery(document).ready(function(){
  jQuery(".shop_left_btn_con li").mouseover(function(){
	var child_count = jQuery(this).attr("child_count");
	if(child_count>0){
	 var id=jQuery(this).attr("id");
     jQuery("#child_"+id).show();			
	}
  }).mouseleave(function(){
	 var child_count = jQuery(this).attr("child_count");
	if(child_count>0){ 
     var id=jQuery(this).attr("id");
     jQuery("#child_"+id).hide();
	}
  });
  jQuery(".specialde li").mouseover(function(){
     jQuery(this).find("i").show();
  }).mouseleave(function(){
     jQuery(this).find("i").hide();
  });
  jQuery(".productone ul").mouseover(function(){
    jQuery(".productone ul").removeClass("this");
	jQuery(this).addClass("this");
  }).mouseleave(function(){
    jQuery(".productone ul").removeClass("this");
  });
  jQuery(".floorul li").mouseover(function(){
	var store_gc=jQuery(this).attr("store_gc");
    jQuery(".floorul li[store_gc="+store_gc+"]").css("cursor","pointer").removeClass("this");
	jQuery(this).addClass("this");
    var id=jQuery(this).attr("id");
	jQuery(".ftab[store_gc="+store_gc+"]").hide();
	jQuery(".ftab[store_gc="+store_gc+"][id="+id+"]").show();
  });
  jQuery(".productone img").lazyload({effect:"fadeIn",width:147,height:147});
  jQuery(".rankimg img").lazyload({effect:"fadeIn",width:73,height:73});
 //
 jQuery(".index_sales_left>h3>ul>li").mouseover(function(){
    jQuery(".index_sales_left>h3>ul>li").removeClass("this");
	jQuery(this).addClass("this");
	jQuery(".index_sales_box>[class^=index_sales_]").hide();
	var div_index=jQuery(this).attr("div_index");
	jQuery(".index_sales_"+div_index).show();
 });
 
 jQuery(".conti").jCarouselLite({
	 btnNext:"#advert_left",
     btnPrev:"#advert_right",
	 auto: 5000,
	 speed: 2000,
	 visible:1
  });
  //
});

</script>
</head>
<body>
<jsp:include page="./top.jsp"/>
<div class="main"> 
 <jsp:include page="./head.jsp"/>
  <jsp:include page="./nav.jsp"/> 
  <div class="index">
    <div class="indextop"> 
      <!--所有分类开始-->
      <div class="menuhidden">
        <div class="index_menu">
          <div class="shop_left_btn_con">
             <ul>
              <%-- <c:forEach var="gc" items="${gcs}">
					<c:set var="icon_img" value="/resources/style/common/images/icon/default_icon.png"/>
					<li id="${gc.id}" style="cursor:pointer;" child_count="${fn:length(gc.childs)}">
						<span class="d">
						<i class="d_img"> 
				  		<c:choose>
				  			<c:when test="${gc.icon_type == 0}">
				  				<c:if test="${gc.icon_sys != '' }">
				  					<c:set var="icon_img" value="/resources/style/common/images/icon/icon_${gc.icon_sys}.png"/>
				  				</c:if>
				  			</c:when>
				  			<c:otherwise>
				  				<c:if test="${not empty gc.icon_acc}">
				  					<c:set var="icon_img" value="/${gc.icon_acc.path}/${gc.icon_acc.name}"/>
				  				</c:if>
				  			</c:otherwise>
				  		</c:choose>
			  			<img src="<c:url value="${icon_img}"/>" width="20" height="18" /> 
			  			</i>   
			  			<a href="<c:url value="/store_goods_list_${gc.id}.htm"/>"> ${gc.className}</a>
			  			</span>
			  			<div class="shop_left_btn_layer" id="child_${gc.id}">
                  		  <div class="shop_left_btn_layer_left">
                  		  	<i class="d_img"><img src="<c:url value="${icon_img}"/>" width="20" height="18" /></i>
                  		  	<a href="<c:url value="/store_goods_list_${gc.id}.htm"/>"> ${gc.className}</a>
                  		  </div>
                  		  <div class="shop_left_btn_layer_right">
                  		  	<c:forEach var="gc1" items="${gc.childs}">
                  		  		<c:if test="${gc1.display }">
				  				  <div class="shop_left_one">
		                      		<div class="shop_left_btn_layer_tit">
		                      			<span><a href="<c:url value="/store_goods_list_${gc1.id}.htm"/>" target="_blank">${gc1.className}</a>
		                      			</span>
		                      		</div>
		                      		<div class="shop_left_btn_layer_con">
		                        	<dl>
		                          	<dd>
		                          	<c:forEach var="gc2" items="${gc1.childs}">
		                          		<c:if test="${gc2.display}">
		                          		<a href="<c:url value="/store_goods_list_${gc2.id}.htm"/>" target="_blank">
		                          		${gc2.className}
		                          		</a>
		                          		</c:if>
		                          	</c:forEach>
		                          	<div class="clear"></div>
		                          </dd>
		                        	</dl>
		                      	</div>
		                    	</div>
				  				</c:if>
                  		  	
                  		  	</c:forEach> 
                  		   </div>
		               </div>   
			  		</li>	
			  </c:forEach> --%>
			</ul> 
          </div>
          <div class="allclass"><a href="">全部商品分类</a></div>
        </div>
      </div>
      <!--所有分类结束-->
      
      
      <!-- 幻灯片部分 -->
      <div class="index_tm">
        <div class="index_hdp"> 
          <script src="${webPath}/advert_invoke.htm?id=1"></script> 
        </div>
        <div class="shopsep">
          <h3>商城特别推荐</h3>
        </div>
        
        <!-- 商城特别推荐 -->
        <div class="index_conti"> 
          <script src="${webPath}/advert_invoke.htm?id=2"></script>
        </div>
      </div>
      
      
      
      <div class="indextop_adv" > 
        <!-- <script src="$!webPath/advert_invoke.htm?id=262148"></script> 
        <script src="$!webPath/advert_invoke.htm?id=262149"></script>  -->
      </div>
      
      
      
      <!-- 用户信息部分 -->
      <div class="index_lnb"> 
      	<c:choose>
			<c:when test="${empty user}">
				<div class="logins"> 
        			<span class="logina"><a href=""><b>免费注册</b></a></span> 
        			<span class="loginb "><a href="<%=basePath%>/user/login.htm"><b>登录</b></a></span> 
        			<span class="loginc"><a href=""><b>免费开店</b></a></span>
        		</div>
			</c:when>
										    
			<c:otherwise>
				<div class="logined"> 
				  <i>                                                                             
				  	 <c:set var="img" value="${imageWebServer}/${config.memberIcon.path}/${config.memberIcon.name}"/>
					 <img src="${img}" width="30" height="30" /> 
		          </i>
		          <ul>
		            <li>您好<em>${fn:substring(user.username, 0, 10)}</em> ><a href=""> 进入用户中心</a></li>
		            <li>预存款:<b> ¥<c:if test="${not empty user.availablebalance}">${user.availablebalance}</c:if>  
		                          <c:if test="${empty user.availablebalance}">0</c:if>
		                     </b> | 积分: <b>${user.integral}</b>
		            </li>
		          </ul>
		        </div>
			</c:otherwise>
		</c:choose>
      	
      	<!-- 新闻模块  -->
      	<div class="new">
          <h3><span><a href="" target="_blank">更多</a></span>商城新闻</h3>
          <ul>
          	
          	<c:forEach var="article" items="${articles}">
          		<c:choose>
          			<c:when test="${article.url != ''}">
          				<c:set var="url" value="${article.url}"/>
          			</c:when>
          			<c:otherwise>
          				<c:set var="url" value="${webPath}/article_${article.id}.htm"/>
          			</c:otherwise>
          		</c:choose>
          		<li>
          		   <span><fmt:formatDate value="${article.addtime}" type="date"/></span>
          		   <a href="${url}" target="_blank">${fn:substring(article.title, 0, 19)}</a>
          		</li>                               
          	</c:forEach>
          	
          </ul>
        </div>
      </div>
    </div>
   <%--  <div class="friendlink">
      <h1>合作伙伴</h1>
      <ul class="linkimg">
        <c:forEach var="info" items="${img_partners}">
        	<li> <a href="${info.url}" target="_blank"><img src="${imageWebServer}/${info.imageAccessory.path}/${info.imageAccessory.name}" width="92" height="35" /></a></li>
        </c:forEach>
      </ul>
      <!--<div class="linka"> #foreach($info in $text_partners)<a href="$!info.url" target="_blank">$!info.title</a>#end </div>-->
    </div> --%>
    <!-- <div class="shopping"> 
    
    
  
    </div> -->
  </div>
  	<jsp:include page="./footer.jsp"/>
  </div>
</body>
</html>
