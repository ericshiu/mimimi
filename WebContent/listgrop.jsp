<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
			<h2 style="font-size: 30px; color:#000;">選單</h2>
						<ul class="list-group">
	
							
							<a href="<%=request.getContextPath() %>/member/bd_select.jsp">
							<li class="list-group-item">寶寶中心</li>
							</a>
							
							
							<a href="<%=request.getContextPath() %>/member/mh_select.jsp">
							<li class="list-group-item">媽媽健康紀錄</li>
							</a>
							
							<a href="<%=request.getContextPath() %>/member/Calendar.jsp">
							<li class="list-group-item">專屬日曆</li>
							</a>
						  
	
	
	
						    <a data-toggle="collapse" href="#collapse2">
							<li class="list-group-item">準媽媽檢查篇</li>
							</a>
							<div id="collapse2" class="panel-collapse collapse">
						        <ul class="list-group">
						          <a href="<%=request.getContextPath() %>/optional_test/listHos_ByCompositeQueryHome.jsp"><li class="list-group-item">孕期可做檢查/自費項目</li></a>
						          <a href="<%=request.getContextPath() %>/member/ins_select.jsp"><li class="list-group-item">已預約產檢</li></a>
						          
						        </ul>
						     
						    </div>
	
	
	
						    <a data-toggle="collapse" href="#collapse3">
							<li class="list-group-item">準媽媽生活篇</li>
							</a>
							<div id="collapse3" class="panel-collapse collapse">
						        <ul class="list-group">
						          <a href="<%=request.getContextPath() %>/member/fm_select_add.jsp"><li class="list-group-item">胎動紀錄</li></a>
						          <a href="<%=request.getContextPath() %>/em_select.jsp"><li class="list-group-item">胎教音樂</li></a>
	
						        </ul>
						     
						    </div>
	
	
			   
	
							
	
							<a data-toggle="collapse" href="#collapse6">
							<li class="list-group-item">育兒手冊</li>
							</a>
							<div id="collapse6" class="panel-collapse collapse">
						        <ul class="list-group">
						          <a href="<%=request.getContextPath() %>/member/baby_compute.jsp"><li class="list-group-item">寶寶生長百分比計算機</li></a>
						           <a href="<%=request.getContextPath() %>/member/bg_select.jsp"><li class="list-group-item">寶寶生長曲線圖</li></a>
						           
						        </ul>
						     
						    </div>
						    <a data-toggle="collapse" href="#collapse7">
							<li class="list-group-item">新生兒護理</li>
							</a>
							<div id="collapse7" class="panel-collapse collapse">
						        <ul class="list-group">
						         	<a href="<%=request.getContextPath() %>/member/be_select.jsp"><li class="list-group-item">新生兒進食紀錄</li></a>
						           	<a href="<%=request.getContextPath() %>/member/bex_select.jsp"><li class="list-group-item">新生兒排泄紀錄</li></a>
									<a href="<%=request.getContextPath() %>/member/bs_select.jsp"><li class="list-group-item">新生兒睡眠紀錄</li></a>
						        </ul>
						     
						    </div>
						    <a data-toggle="collapse" href="#collapse8">
							<li class="list-group-item">疫苗注射時程提醒</li>
							</a>
							<div id="collapse8" class="panel-collapse collapse">
						        <ul class="list-group">
						        	<a href="<%=request.getContextPath() %>/member/vn_select.jsp"><li class="list-group-item">疫苗注射紀錄</li></a>
									<a href="<%=request.getContextPath() %>/vc_select.jsp"><li class="list-group-item">疫苗時程</li></a>
	
						        </ul>
						     
						    </div>
	
							
						    
	
						   
	
	
	
							
						</ul>
</body>
</html>