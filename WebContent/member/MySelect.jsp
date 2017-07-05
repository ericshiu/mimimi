<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="">

<body>

			<div class="col-xs-12 col-lg-12 left1">
				<a href="#" data-toggle="modal" data-target="#myModal" class="thumbnail" id="thumbnail" style="border-radius:100%;">
				<img style="border-radius:100%;" id="memhead" src="<%= request.getContextPath() %>/MemberPhoto?mem_no=${userVO.mem_no}"></a>
			</div>
			<div class="col-xs-12 col-lg-12 left2">
				
				
				<p id="jobname" > ${userVO.mem_name} 
				 
				<% if( "${userVO.mem_sex}"=="男"){%>
				    先生，你好!
				<%}else{%>
				    小姐，妳好!
				<%}%>
				
				</p>
			</div>

			<div class="col-xs-12 col-lg-12 left3">
				<div class="panel-heading" role="tab" id="heading1">

					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse1" aria-expanded="false"
						aria-controls="collapse1" id="leftfont">
						<a	href="<%=request.getContextPath()%>/member/MyPersonal.jsp">會員資訊維護 </a>
						</a>
				</div>

				<div class="panel-heading" role="tab" id="heading2">
					<!-- <h3 class="panel-title"> -->
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse2" aria-expanded="false"
						aria-controls="collapse2" id="leftfont">
						<a	href="<%=request.getContextPath()%>/member/memOrd.jsp">訂單管理 </a>
						</a></a>
					
				</div>

			<div class="panel-heading" role="tab" id="heading6">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse6" aria-expanded="false"
						aria-controls="collapse6" id="leftfont">
						<a	href="<%=request.getContextPath()%>/member/MyPointRecord.jsp">積分紀錄 </a>
						</a>
				</div>

				<div class="panel-heading" role="tab" id="heading4">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse4" aria-expanded="false"
						aria-controls="collapse4" id="leftfont"><a
						href="<%=request.getContextPath()%>/member/MyPCApplication.jsp">預約管理</a>
						</a>
					<!-- </h3> -->
				</div>



				

				<div class="panel-heading" role="tab" id="heading6">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="" aria-expanded="false"
						aria-controls="collapse6" id="leftfont"><a
						href="<%=request.getContextPath()%>/member/MyEvaluation.jsp">瀏覽評價紀錄</a>
					</a>
				</div>

				<div class="panel-heading" role="tab" id="heading6">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="" aria-expanded="false"
						aria-controls="collapse6" id="leftfont"><a
						href="<%=request.getContextPath()%>/member/MyArticle.jsp">文章管理</a>
					</a>
					<!-- </h3> -->
				</div>


				<div class="panel-heading" role="tab" id="heading7">
					<a class="collapsed" role="button" data-toggle="collapse"
						data-parent="#accordion" href="#collapse7" aria-expanded="false"
						aria-controls="collapse7" id="leftfont">查詢檢舉紀錄<b class="caret"></b></a>
					

				</div>

				<div id="collapse7" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="heading7">
					<div class="panel-body" id="leftfont">
					<a	href="<%=request.getContextPath()%>/member/MyForumReport.jsp">討論區檢舉 </a></div>
					<div class="panel-body" id="leftfont">
					<a	href="<%=request.getContextPath()%>/member/MyPCReport.jsp">廠商檢舉 </a>
					</div>
				</div>

<!-- 				<div class="panel-heading" role="tab" id="heading8"> -->
<!-- 					<a class="collapsed" role="button" data-toggle="collapse" -->
<!-- 						data-parent="#accordion" href="#collapse8" aria-expanded="false" -->
<!-- 						aria-controls="collapse8" id="leftfont">瀏覽我的最愛<b class="caret"></b></a> -->
<!-- 					</h3> -->
<!-- 				</div> -->

<!-- 				<div id="collapse8" class="panel-collapse collapse" role="tabpanel" -->
<!-- 					aria-labelledby="heading8"> -->
<!-- 					<div class="panel-body" id="leftfont">賣場商品</div> -->
<!-- 					<div class="panel-body" id="leftfont">收藏文章</div> -->
<!-- 				</div> -->
	
			</div>
</body>

</html>

