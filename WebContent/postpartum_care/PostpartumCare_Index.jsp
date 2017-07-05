<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*"%>
<%@ page import="com.postpartum_care.model.*" import="com.pc_picture.model.*" import="com.firm.model.*" import="com.member.model.*"%>    
<%
    PostpartumCareService PCSvc = new PostpartumCareService();
    List<PostpartumCareVO> list = PCSvc.getAll();
    PostpartumCareVO pc1 = list.get(0);
    PostpartumCareVO pc2 = list.get(1);
    PostpartumCareVO pc3 = list.get(2);
    pageContext.setAttribute("pc1",pc1);
    pageContext.setAttribute("pc2",pc2);
    pageContext.setAttribute("pc3",pc3);
    
    Set<PcPictureVO> pcps1 = PCSvc.getPCPsByPc_no(pc1.getPc_no());
    Set<PcPictureVO> pcps2 = PCSvc.getPCPsByPc_no(pc2.getPc_no());
    Set<PcPictureVO> pcps3 = PCSvc.getPCPsByPc_no(pc3.getPc_no());
    
	PcPictureVO pcp1 = null;
	if (!pcps1.isEmpty()){
    	pcp1 = pcps1.iterator().next();
	}
	PcPictureVO pcp2 = null;
	if (!pcps2.isEmpty()){
    	pcp2 = pcps2.iterator().next();
	}
	PcPictureVO pcp3 = null;
	if (!pcps3.isEmpty()){
    	pcp3 = pcps3.iterator().next();
	}
    
    pageContext.setAttribute("pcp1",pcp1);
    pageContext.setAttribute("pcp2",pcp2);
    pageContext.setAttribute("pcp3",pcp3);
    
    String userType = (String) session.getAttribute("userType"); 

    if (userType != null){
    	if (userType.equals("PC")){
    		PostpartumCareVO userVO = (PostpartumCareVO) session.getAttribute("userVO");
    		pageContext.setAttribute("userVO",userVO);
    	} else if (userType.equals("Fir")){
    		FirmVO userVO = (FirmVO) session.getAttribute("userVO");
    		pageContext.setAttribute("userVO",userVO);
    	} else if (userType.equals("Mem")){
    		MemberVO userVO = (MemberVO) session.getAttribute("userVO");  
    		pageContext.setAttribute("userVO",userVO);
    	} 
    } else {
    	userType = "Non";
    }
    	MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO"); 
    	session.setAttribute("pageReq", "/firm/PersonalFir.jsp");    
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 引用icon -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- 引用Css -->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/postpartum_care/css/PostpartumCare_index.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>		
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>產後照護專區</title>
</head>
<body>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
	<!--cm start-->
<div class="jumbotron">	
   <div class="row">
      <div class="col-xs-12 col-sm-12"> 
  		<div id="carousel-id" class="carousel slide" data-ride="carousel">
      <!-- 幻燈片小圓點區 -->
      <ol class="carousel-indicators">
          <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-id" data-slide-to="1" class=""></li>
          <li data-target="#carousel-id" data-slide-to="2" class=""></li>
          <li data-target="#carousel-id" data-slide-to="3" class=""></li>
          <li data-target="#carousel-id" data-slide-to="4" class=""></li>        
      </ol>
      <!-- 幻燈片主圖區 -->
      <div class="carousel-inner">
          <div class="item active">
              <img src="img/pc_slider_index-1.png" alt="">
              <div class="container">
                  <div class="carousel-caption">
                   <div>


                   </div>
               		
              	  </div>
              	</div>
          </div>
          <div class="item">
              <img src="img/pc_slider_index-2.png" alt="">
              <div class="container">
                  <div class="carousel-caption">
<!--               		<p><a class="btn btn-lg btn-primary" href="#" role="button">立即查詢</a></p>                    -->
                  </div>
              </div>
          </div>
          <div class="item" >
              <img src="img/pc_slider_index-3.png" alt="">
              <div class="container">
                  <div class="carousel-caption">
<!--               		<p><a class="btn btn-lg btn-primary" href="#" role="button">立即查詢</a></p>  -->
                  </div>
              </div>
          </div>
           <div class="item">
              <img src="img/pc_slider_index-4.png" alt="">
              <div class="container">
                  <div class="carousel-caption">
<!--               		<p><a class="btn btn-lg btn-primary" href="#" role="button">立即查詢</a></p>                    -->
                  </div>
              </div>
          </div>
          <div class="item">
              <img src="img/pc_slider_index-5.png" alt="" >
              <div class="container">
                  <div class="carousel-caption" id="tittle_btn">
                    <p><a class="btn btn-info" href="#searchBox" role="button">立即查詢</a></p>
<!--               		<p><a class="btn btn-lg btn-primary" href="#" role="button">立即查詢</a></p>                    -->
                  </div>
              </div>
          </div>          
      </div>
      <!-- 上下頁控制區 -->
      <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
   <div class="clear">

  </div>
  </div>
  </div>
  </div>

 </div>

  <!--cm end-->

				<div class="container">
				<div class="s-title"><h2>新加入廠商</h2></div>
			
					
					   <div class="row pcs">
						<div class="col-xs-12 col-sm-4 ps-item" id="PC_1">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
							<img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcp1.pcp_no}" class="img-responsive">
							<h3><%= pc1.getPc_name() %></h3>
							<p><%= pc1.getPc_address() %></p>
							<input type="hidden" name="pc_no" value="<%= pc1.getPc_no() %>">
							<input type="hidden" name="action" value="getOne_For_Display">
							<div class="input-group" style="text-align: center; width: 100%;">
								<input type="submit" value="去看看" class="btn btn-info">
							</div>
						</form>
						</div>
						<div class="col-xs-12 col-sm-4 ps-item" id="PC_2">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
							<img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcp2.pcp_no}" class="img-responsive" >
							<h3><%= pc2.getPc_name() %></h3>
							<p><%= pc2.getPc_address() %></p>
							<input type="hidden" name="pc_no" value="<%= pc2.getPc_no() %>">
							<input type="hidden" name="action" value="getOne_For_Display">
							<div class="input-group" style="text-align: center; width: 100%;">
								<input type="submit" value="去看看" class="btn btn-info">
							</div>
						</form>
						</div>
											
						<div class="col-xs-12 col-sm-4 ps-item" id="PC_3">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
							<img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcp3.pcp_no}" class="img-responsive" >
							<h3><%= pc3.getPc_name() %></h3>
							<p><%= pc3.getPc_address() %></p>
							<input type="hidden" name="pc_no" value="<%= pc3.getPc_no() %>">
							<input type="hidden" name="action" value="getOne_For_Display">
							<div class="input-group" style="text-align: center; width: 100%;">
								<input type="submit" value="去看看" class="btn btn-info">
							</div>
						</form>
						</div>	
					</div>
				</div>	
				
				<br>
<!-- 				查詢產後廠商 -->
				<div class="container" id="searchBox">
					<div class="row">
					<div class="s-title"><h2>立即查詢</h2></div>
						<div class="col-xs-12 col-sm-offset-1 col-sm-10" id="search_pc">
						<FORM METHOD="post" ACTION="PC.do" >	
				<div class="row">
				  <div class="col-xs-12 col-sm-4">
				      <div class="form-group" style="width: 95%;" >
				      <label class="control-label" for="select_pc">選擇廠商類型</label>
					  <select  class="form-control selectpicker show-tick" data-style="btn-info" name="pc_type" id="select_pc" >
					      <option value="">全部</option>
					      <option value="月子中心">月子中心</option>
					      <option value="月子餐廠商">月子餐廠商</option>
					  </select>
					  </div>
				  </div>	
				  <div class="col-xs-12 col-sm-4">
				   <label class="control-label" for="select_pc">選擇搜尋地區</label>
							<div class="input-group" style="width: 100%;">

							<select class="form-control"  id="city_select" 
								style="width: 45%;height:100%;"></select> 
							<select
								class="form-control " id="area_select" 
								style="width: 55%;height:100%;"></select> 
							<input type="hidden"
								class="form-control" name="pc_address" id="result"
								value="" />

						</div>						 
				  </div>
				  <div class="col-xs-12 col-sm-4">
				      <div class="form-group" style="width: 95%;" >
				      <label class="control-label" for="select_pc">選擇排序</label>
					  <select  class="form-control selectpicker show-tick" data-style="btn-info" name="order" id="select_pc" >
					      <option value="pc_no">最新</option>
					      <option value="pc_eva_good">正評</option>
					      <option value="pc_point">預約積分</option>
					  </select>
					  </div>
				  </div>				  				  				  
				</div>
				<div class="row" style="margin-top:10px;">
				  <div class="col-xs-12 col-sm-12"> 						
      				<input type="hidden" name="action" value="listPCs_ByCompositeQuery">							
			  		<div class="input-group" style="width:98% ;height:auto;">		  				  	
							  <input type="text" class="form-control" style="height:auto;" name="pc_name" placeholder="請輸入廠商名稱關鍵字" >
							  <span class="input-group-btn">
							    <button class="btn btn-info" type="submit">立即搜尋</button>
							  </span>
					</div>	
				  </div>				  
				</div>				
<!-- 		<input type="text" name="pc_name" value="好貝">		 -->
<!-- 		<input type="submit" value="立即搜尋"> -->
<!--        	<input type="hidden" name="action" value="listPCs_ByCompositeQuery">						 -->
						</FORM>													
						</div>	
					</div>
					
					
<%-- <%if (request.getAttribute("listPCs_ByCompositeQuery")!=null){%> --%>
<%--        <jsp:include page="listPCs_ByCompositeQuery.jsp" /> --%>
<%-- <%} %>					 --%>
				</div>
<!-- 				查詢廠商結束 -->				
				
				<br>
				<br>		

				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-offset-1 col-sm-6">	
							<div style="margin-bottom:5px;"><span><img src="img/product-label-new-ribbon.png" style="padding-bottom:10px; padding-right:10px;"><span style="font-size:2em;">產後為什麼要坐月子？</span></span></div>
							<p>「坐月子」是華人自古以來的一項傳統，在中國最早可以追溯至西漢《禮記‧內則》，距今已有兩千多年的歷史，稱之為「月內」，是產後必須的儀式，由於時間大約長達一個月，因此才被稱為「坐月子」。
						這項習俗在各地有著不同的名稱，持續的時間也不大一樣，粵語稱之為「坐月」，台語稱「月內」，客語則叫作「做月子」；依各地民情風俗不同，時間上也有40天、2個月和100天的差別。	</p>
						</div>
						<div class="col-xs-12  col-sm-4">
						<img id="pc_img1" src="img/pc_index-1.jpg" class="img-responsive">	
						</div>
					</div>
				</div>
						
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12">	
							<div style="margin-bottom:5px;"><span><img src="img/product-label-sale-ribbon.png" style="padding-bottom:10px; padding-right:10px;"><span style="font-size:2em;">做月子的現代意義</span></span></div>
							<div>
							<div style="margin-bottom:5px;"><span><img src="img/product-label-new.png" style="padding-bottom:10px; padding-right:10px;"><span style="font-size:1.8em;">小家庭、婦女就業比例提升</span></span></div>
							<p>隨著時代改變，職業婦女越來越多，小家庭結構裡能協助媽咪的幫手也有限，坐月子的意義已和從前不太一樣。現代人的營養已不需要太擔心，坐月子的功能主要是提供媽咪恢復體力的休養時間，以及不需操心公事或其他家庭雜務、能夠專心學習與寶寶親密接觸及互動的機會，以利後續回歸職場或小家庭生活後能夠順利哺乳及照顧新生兒。</p>
							</div>

							<div>
							<div style="margin-bottom:5px;"><span><img src="img/product-label-sale.png" style="padding-bottom:10px; padding-right:10px;"><span style="font-size:1.8em;">平均生育年齡提高</span></span></div>							
							<p>過去婦女的生育年齡較低，產後的復原情況較好，復原速度也較快；而目前台灣社會的平均生育年齡已超過30歲，產後子宮復舊的能力較差，所以會需要更多的時間好好休養。
							陳玉娟主任指出，早期時代的婦女到了40〜50歲，多半就會有子宮懸韌帶下垂、壓迫膀胱的問題發生，然而現代人生孩子的年紀越來越大，懸韌帶恢復所需的時間也更久，因此產後雖然不建議一直臥床，但勞動和休息還是要取得適當的平衡，否則動得太多，將來子宮下垂的程度會更嚴重。</p>
							</div>

							<div>
							<div style="margin-bottom:5px;"><span><img src="img/product-label-new.png" style="padding-bottom:10px; padding-right:10px;"><span style="font-size:1.8em;">平均壽命逐漸延長</span></span></div>							
							<p>現代人平均壽命較長，生完小孩可能還要再活50年，因此產後遺留下來的後遺症會持續更多年，骨質流失的問題影響也更長遠，未來老年疾病所造成的問題會更值得重視。例如，以往很強調眼睛的照護，如產後不宜久視、不能做針線手工、也不能哭泣。換成現代的問題，就是3C產品用太多，會造成眼睛的傷害。
							因此，好好坐月子對媽咪來說當然比以前更重要，必須花更長的時間休息、補充營養、儲存身體的能量，才能彌補生產時分給寶寶的鈣質等養分、血液的流失，以及體力的消耗，讓媽咪未來能走更長遠的路。</p>
							</div>

							<div>
							<div style="margin-bottom:5px;"><span><img src="img/product-label-sale.png" style="padding-bottom:10px; padding-right:10px;"><span style="font-size:1.8em;">現代人「飲食不均衡」問題</span></span></div>						
							<p>過去坐月子是擔心媽咪的蛋白質攝取不足，但現代飲食則是餐餐有魚有肉，所以在食物的調整上，反而會提醒媽咪有沒有攝取足夠的纖維質，以預防或改善便秘；補充的鈣質量夠不夠，以補回流失的鈣質；而不是只輪著吃麻油雞和豬腳花生。比起過去，現在更重視的不是「營養不良」而是「營養不均衡」的問題。</p>
							</div>	

							<div class="well text-right">
								<p><i>坐月子是我們對於母親的一種尊敬儀式，感謝她冒著生命的危險賦予胎兒生命，也延續了整個大家族</i></p>
							</div>						

						</div>

					</div>
				</div>




		<br>
    <jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />		
	<script src="<%=request.getContextPath()%>/postpartum_care/js/addressForSearch.js"></script>
</body>
</html>