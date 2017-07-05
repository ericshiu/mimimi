<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Mommy Loves Baby Shopping Center: Product_Picture Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Mommy Loves Baby Shopping Center: Product_Picture Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Product_Picture</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllPRP.jsp'>List</a> all photos. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="prp.do" >
        <b>輸入商品編號 (如PRO0000001):</b>
        <input type="text" name="pro_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="PRPSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
  <li>				   
     <FORM METHOD="post" ACTION="prp.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="pro_no">
         <c:forEach var="prpVO" items="${PRPSvc.all}" > 
          <option value="${prpVO.pro_no}">${prpVO.prp_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="prp.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="pro_no">
         <c:forEach var="prpVO" items="${PRPSvc.all}" > 
          <option value="${prpVO.pro_no}">${prpVO.pro_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>商品照片管理</h3>

<ul>
  <li><a href='addPRP.jsp'>Add</a> a new Photo.</li>
</ul>

</body>

</html>
