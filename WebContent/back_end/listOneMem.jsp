<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>

<jsp:useBean id="memberVO" scope="request" class="com.member.model.MemberVO" />
<html>
<head>
<title>會員資料 - listOneMem.jsp</title>

</head>
<script src="<%=request.getContextPath() %>/front_end/js/SuccessJoin.js"></script>
<body bgcolor='white'>




<FORM METHOD="post" ACTION="cgmem.do" id="form1" enctype="multipart/form-data">


					<div class="panel" style="width:100%;background:none;">
						<div class="panel-heading" style=" height: 3em;">
							<p class="panel-title" style="text-align: center;font-size:2em;color:#222;">會員資料</p>
						</div>
						<table class="table table-hover" style="text-align: center;">
							<thead>
								<tr>
									<th>會員編號</th>
									<th>帳號</th>
									<th>姓名</th>
									<th>信箱</th>
									<th>權限</th>
									<th>修改權限</th>
								</tr>
							</thead>
							<tbody>
								<tr align='center' valign='middle'>
										<td><%=memberVO.getMem_no()%></td>
										<td><%=memberVO.getMem_id()%></td>
										<td><%=memberVO.getMem_name()%></td>
										<td><%=memberVO.getMem_email()%></td>
										<td><%=memberVO.getMem_authority()%></td>
										<td><button type="submit" class="btn btn-success">修改</button></td>
								</tr>
							</tbody>
						</table>
					</div>


			<input type="hidden" name="mem_no" value="<%=memberVO.getMem_no()%>"/>
			<input type="hidden" name="mem_id" value="<%=memberVO.getMem_id()%>"/>
			<input type="hidden" name="mem_password" value="<%=memberVO.getMem_password()%>"/>
			<input type="hidden" name="mem_name" value="<%=memberVO.getMem_name()%>"/>
			<input type="hidden" name="mem_nike" value="<%=memberVO.getMem_nike()%>"/>
			<input type="hidden" name="mem_sex" value="<%=memberVO.getMem_sex()%>"/>
			<input type="hidden" name="mem_birthday" value="<%=memberVO.getMem_birthday()%>"/>
			<input type="hidden" name="mem_phone" value="<%=memberVO.getMem_phone()%>"/>
			<input type="hidden" name="mem_email" value="<%=memberVO.getMem_email()%>"/>
			<input type="hidden" name="mem_address" value="<%=memberVO.getMem_address()%>"/>
			<input type="hidden" name="mem_point" value="<%=memberVO.getMem_point()%>"/>
			<input type="hidden" name="mem_actual_point" value="<%=memberVO.getMem_actual_point()%>"/>
			<input type="hidden" name="mem_picture" value="<%=memberVO.getMem_picture()%>"/>
			<input type="hidden" name="mem_authority" value="<%=memberVO.getMem_authority()%>"/>
			<input type="hidden" name="mem_facebook" value="<%=memberVO.getMem_facebook()%>"/>
			<input type="hidden" name="mem_google" value="<%=memberVO.getMem_google()%>"/>
			<input type="hidden" name="change_aut" value="change">
			<input type="hidden" name="action" value="memupdate">
</FORM>
</body>
</html>
