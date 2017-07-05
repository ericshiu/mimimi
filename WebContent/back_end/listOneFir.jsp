<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.firm.model.*"%>

<jsp:useBean id="firVO" scope="request" class="com.firm.model.FirmVO" />
<html>
<head>
<title>會員資料 - listOneFir.jsp</title>
</head>
<script src="<%=request.getContextPath() %>/front_end/js/SuccessJoin.js"></script>
<body bgcolor='white'>



<FORM METHOD="post" ACTION="cgmem.do" id="form1" enctype="multipart/form-data">


					<div class="panel" style="width:100%;background:none;">
						<div class="panel-heading" style=" height: 3em;">
							<p class="panel-title" style="text-align: center;font-size:2em;color:#222;">商場廠商資料</p>
						</div>
						<div>
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
										<td><%=firVO.getFir_no()%></td>
										<td><%=firVO.getFir_id()%></td>
										<td><%=firVO.getFir_name()%></td>
										<td><%=firVO.getFir_email()%></td>
										<td><%=firVO.getFir_authority()%></td>
										<td><button type="submit" class="btn btn-success">修改</button></td>
								</tr>
								<tr>
				
								</tr>
							</tbody>
						</table>
						</div>
					</div>


			<input type="hidden" name="fir_no" value="<%=firVO.getFir_no()%>"/>
			<input type="hidden" name="fir_id" value="<%=firVO.getFir_id()%>"/>
			<input type="hidden" name="fir_password" value="<%=firVO.getFir_password()%>"/>
			<input type="hidden" name="fir_name" value="<%=firVO.getFir_name()%>"/>
			<input type="hidden" name="fir_type" value="<%=firVO.getFir_type()%>"/>
			<input type="hidden" name="fir_phone" value="<%=firVO.getFir_phone()%>"/>
			<input type="hidden" name="fir_email" value="<%=firVO.getFir_email()%>"/>
			<input type="hidden" name="fir_address" value="<%=firVO.getFir_address()%>"/>
			<input type="hidden" name="fir_eva_good" value="<%=firVO.getFir_eva_good()%>"/>
			<input type="hidden" name="fir_eva_normal" value="<%=firVO.getFir_eva_normal()%>"/>
			<input type="hidden" name="fir_eva_bad" value="<%=firVO.getFir_eva_bad()%>"/>
			<input type="hidden" name="fir_account" value="<%=firVO.getFir_account()%>"/>
			<input type="hidden" name="fir_authority" value="<%=firVO.getFir_authority()%>"/>
			<input type="hidden" name="change_aut" value="change">
			<input type="hidden" name="action" value="firupdate">
</FORM>
</body>
</html>
