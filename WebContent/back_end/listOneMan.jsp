<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.authority.model.*"%>

<jsp:useBean id="manVO" scope="request"	class="com.manager.model.ManagerVO" />
<jsp:useBean id="autDAO" scope="request" class="com.authority.model.AuthorityDAO" />
<jsp:useBean id="autlistDAO" scope="page" class="com.authority_list.model.AuthorityListDAO" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<FORM METHOD="post" ACTION="cgman.do" id="form1">


		<div class="panel panel-success" style="width: 100%;background:none;">
			<div class="panel-heading" style="height: 4em;background:none;">
				<p class="panel-title" style="text-align: center; font-size: 2em;">管理員權限資料</p>
			</div>
			<div>
				<table class="table table-hover" style="text-align: center;">
					<thead>
						<tr>
							<th>管理員編號</th>
							<th>姓名</th>
							<th colspan="2">管理功能</th>

						</tr>
					</thead>
					<tbody>
						<tr align='center' valign='middle'>
							<td><%=manVO.getMan_no()%></td>
							<td><%=manVO.getMan_name()%></td>
						<td style="width:300px;text-align:left;" colspan="2" >
						<ol>
						<c:forEach var="autlistVO" items="${autlistDAO.all}">
                    		
<%-- 	                    		${autlistVO.aut_no} --%>
							
                    		 <c:if test="${manVO.man_no==autlistVO.man_no}">
                    				<li style="margin:2px;">
                    		 		<c:forEach var="autVO" items="${autDAO.all}">
	                   			  		<c:if test="${autlistVO.aut_no==autVO.aut_no}">
	                   				 	${autVO.aut_name}<br>
	                   				 	</c:if>
	                   			 	</c:forEach>
	                   			 	</li>
                    		</c:if>
                		</c:forEach>
                		</ol>
						</td>
							
						</tr>
						
						
		
						<tr>
						
						<th>修改權限</th>
						<td><button type="submit" class="btn btn-success">修改</button></td>
						<td colspan="2" style="text-align:left;">
							<% int i=0; %>
							<ol>
							<c:forEach var="autVO" items="${autDAO.all}">
							<li style="margin:2px;">
								<input type="checkbox" name="aut_no" value="${autVO.aut_no}">${autVO.aut_name }
								
							</c:forEach>
							</li>	
							</ol>	
 						</td>
 						
 						
						</tr>
						 <input type="hidden" name="man_no" value="${manVO.man_no}">
						<input type="hidden" name="action" value="changeManAut">
						
					</tbody>
				</table>
			</div>
		</div>
		
		
		
		
		


		<%-- 			<input type="hidden" name="fir_no" value="<%=firVO.getFir_no()%>"/> --%>
		<%-- 			<input type="hidden" name="fir_id" value="<%=firVO.getFir_id()%>"/> --%>
		<%-- 			<input type="hidden" name="fir_password" value="<%=firVO.getFir_password()%>"/> --%>

		<input type="hidden" name="change_aut" value="change"> <input
			type="hidden" name="action" value="firupdate">
	</FORM>
</body>
</html>

</body>
</html>