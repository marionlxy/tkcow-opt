<%@ include file="/resources/jsp/taglibs.jsp"%>
<div class="top">
  <div class="page">
    <div class="pageright" id="site-nav">
      <ul class="quick-menu">
         <li class="mytaobao menu-item menupx">         
          <div class="menu"> <a class="menu-hd" href="" rel="nofollow">买家中心<b></b></a>
            <div class="menu-bd">
              <div class="menu-bd-panel">																						
                <div>
                	<a href="" rel="">已买到的宝贝</a>
                	<%-- <a href="<c:url value="/buyer/index.htm?type=4"/>" rel="nofollow">店铺动态</a>
                	<a href="<c:url value="/buyer/index.htm?type=2"/>"  rel="nofollow">好友动态</a> --%>
                </div>
              </div>     
            </div>
          </div>
        </li>
        <script>
		 var goods_count= '${fn:length(cart)}';
		 var total_price=0;
		 <c:set var="total_price" value="0"/>
		 <c:forEach var="gc" items="${cart}">
		 	<c:choose>
			<c:when test="${gc.goods.group_buy==2}">
				<c:set var="total_price" value="${gc.goods.group_goods.gg_price}*${gc.count}+${total_price}"/>
			</c:when>
			<c:otherwise>
				<c:set var="total_price" value="${gc.goods.store_price}*${gc.count}+${total_price}"/>
			</c:otherwise>
			</c:choose>
		 </c:forEach>
		 
		function cart_remove(id,store_id){
			    
           jQuery.post("<c:url value="store_second_url/remove_goods_cart.htm"/>",{"id":id,"store_id":store_id},function(data){
           jQuery("div[class=table] tr[id="+id+"]").remove();
		   jQuery(".shopping[id="+id+"]").remove();
		   jQuery(".shopp_ingtop[id="+id+"]").remove();
           jQuery("#cart_goods_count_top").html(data.count);
           jQuery("#cart_goods_price_top").html(data.total_price);
		   jQuery("#top_total_price_"+store_id).html(data.total_price);
           if(store_id!=""){
              jQuery("#total_price_"+store_id).html(data.sc_total_price);
           }
		   if( jQuery("form[id=cart_"+store_id+"]").find("tr[goods_list^=goods_info]").length==0){
		  	 jQuery("form[id=cart_"+store_id+"]").remove();
		   }
           if(jQuery("tr[goods_list^=goods_info]").length==0){
              jQuery(".car_nogoods").show();
           }
         },"json");
       }
		
	    jQuery(document).ready(function(){
		   jQuery("#cart_goods_top_menu").mouseover(function(){
			  jQuery.ajax({type:'POST',url:'<c:url value="store_second_url/cart_menu_detail.htm"/>',data:'',
						   beforeSend:function(){																																			
							     jQuery("#cart_goods_top_info").empty().html('<div class="menu-bd-panel"><div style="text-align:center;"><img src="<c:url value="/resources/style/common/images/loader.gif"/>"/></div></div>');
							     jQuery("#cart_goods_top_info").show();
							   },
						  success:function(data){
							     jQuery("#cart_goods_top_info").empty();
								 jQuery("#cart_goods_top_info").html(data);
							   }
						});
		   });
		   jQuery("#cart_menu").mouseleave(function(){
			  jQuery("#cart_goods_top_info").hide();
		   });
		});
	   </script>
        <li class="search menu-item menupx">																								
          <div class="menu" id="cart_menu"><span class="menu-hd" id="cart_goods_top_menu"><s></s>购物车<span id="cart_goods_count_top">${fn:length(cart)}</span>种商品<b></b></span>
            <div class="menu-bd" id="cart_goods_top_info">
             <div class="menu-bd-panel">
                <div style="text-align:center;"><img src="<c:url value="/resources/style/common/images/loader.gif"/>" /></div>
             </div>
            </div>
          </div>
        </li>				        											
		<li class="menupx"><a href="#">我的收藏</a></li>
        <c:if test="${fn:indexOf(user.userrole, 'ADMIN') != -1}">
        	<li class="menupx"><a href="<c:url value="/admin/index.htm"/>" target="_blank">管理后台</a></li>
        </c:if>
      </ul>
    </div>
    <div class="pageleft">
    <c:choose>
		<c:when test="${not empty user}">
			<span>${fn:substring(user.username, 0, 12)}您好,欢迎来到${fn:substring(config.websitename, 0, 30)}！</span>
     		<a href="<c:url value="/j_logout.htm"/>" class="lightblue">[退出]</a>
		</c:when>
									    
		<c:otherwise>
			<span>亲，欢迎来到${config.websitename}！</span>
     		<span class="pxlr"><a href="<c:url value="/user/login.htm"/>" class="lightblue">登录</a></span>
     		<span class="pxlr">或</span>
     		<span class="pxlr"><a href="<c:url value="/register.htm"/>" class="lightblue">注册</a></span>
		</c:otherwise>
	</c:choose>
    </div>
  </div>
</div>
