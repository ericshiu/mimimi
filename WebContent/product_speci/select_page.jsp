<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Mommy Loves Baby Shopping Center: Product_Speci Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Mommy Loves Baby Shopping Center: Product_Speci Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Product Specification</p>

<h3>商品規格資料查詢:</h3>
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
  <li><a href='listAllPSP.jsp'>List</a> all Product Specifications. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="psp.do" >
        <b>輸入商品編號 (如PRO0000001):</b>
        <input type="text" name="pro_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="PSPSvc" scope="page" class="com.product_speci.model.ProductSpeciService" />
   
  <li>
     <FORM METHOD="post" ACTION="psp.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="pro_no">
         <c:forEach var="pspVO" items="${PSPSvc.all}" > 
          <option value="${pspVO.psp_no}">${pspVO.pro_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="psp.do" >
       <b>選擇商品規格項目:</b>
       <select size="1" name="psp_no">
         <c:forEach var="pspVO" items="${PSPSvc.all}" > 
          <option value="${pspVO.pro_no}">${pspVO.psp_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="psp.do" >
       <b>選擇商品規格細項:</b>
       <select size="1" name="psp_no">
         <c:forEach var="pspVO" items="${PSPSvc.all}" > 
          <option value="${pspVO.pro_no}">${pspVO.psp_list}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>商品規格管理</h3>

<ul>
  <li><a href='addPSP.jsp'>Add</a> a new Product Specification.</li>
</ul>

</body>

</html>
