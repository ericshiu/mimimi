
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.baby_growth.model.* "%>
<%@ page import="com.baby_data.model.*"%>
<%@ page import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<%	List<BabyGrowthVO> list2 = (List<BabyGrowthVO>) request.getAttribute("lisBG_ByCompositeQuery"); 
	pageContext.setAttribute("list2",list2);
%>
<%
  	BabyDataService BDSvc = new BabyDataService();
	List<BabyDataVO> list = BDSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="">
	<head>
	<title>寶寶生長曲線圖</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- 引用icon -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- 引用Css -->
		 <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" type="text/css" href="css/title.css">
  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.12/c3.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/inputtype.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />	

		<div class="container">
			<div class="row">
							<div class="col-xs-12 col-sm-12">
					<div class="widget center">
					  <div class="text center">
					    <h1 class="">寶寶生長紀錄</h1>
					    <p>Go ahead, resize me.</p>
					  </div>
					  
					  <div class="blur">
					  
					  </div>
					</div>
					</div>
				<div class="col-xs-12 col-sm-3">
				<jsp:include page="../listgrop.jsp" flush="true"/>
				</div>





				<div class="col-xs-12 col-sm-9">
				</div>
				<div class="col-xs-12 col-sm-12">
				<jsp:useBean id="babyDataSvc" scope="page" class="com.baby_data.model.BabyDataService" />
					
						<div class="input-group" style=" width: 95%; margin: 10px;">
						<form METHOD="post" ACTION="bg.do" name="form1">
							<select size="1" name="bd_no" style="height: 30px;" onchange="submit();">
							            <option value="請選擇寶寶">請選擇寶寶
							            
							
								<c:forEach var="babyDataVO" items="${babyDataSvc.all}">
									<c:if test="${userVO.mem_no==babyDataVO.mem_no}">
										<option value="${babyDataVO.bd_no}" ${(babyGrowthVO.bd_no==babyDataVO.bd_no)?'selected':'' } >${babyDataVO.bd_name}
									</c:if>
								</c:forEach>
							</select>
<!-- 						<input type="hidden" name="action" value="getOneBD"> -->
							<input type="hidden" name="action" value="listBG_ByCompositeQuery">
						</form>
						</div>
				  	
				  	<div class="tbl-header">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <thead>
				      
				        <tr>
				        <th>寶寶名稱</th>
				          <th>紀錄日期</th>
				          <th>身高</th>
				          <th>體重</th>
				          <th>頭圍</th>
				          <th></th>
				        </tr>
				       
				      </thead>
				    </table>
				  	</div>
				  	<div class="tbl-content">
				  	
				  	
				  		
				    <table cellpadding="0" cellspacing="0" border="0">
				      <tbody>
				      
				      <c:forEach var="babyGrowthVO" items="${list2}">
				      <c:forEach var="babyDataVO" items="${list}">
				      <c:if test="${userVO.mem_no==babyDataVO.mem_no}">
					  		<c:if test="${babyDataVO.bd_no==babyGrowthVO.bd_no}">
				        <tr>
				          <td>${babyDataVO.bd_name}</td>
				          <td>${babyGrowthVO.bg_date}</td>
				          <td>${babyGrowthVO.bg_height}</td>
				          <td>${babyGrowthVO.bg_weight}</td>
				          <td>${babyGrowthVO.bg_head}</td>
				           <td>
						        
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/bg.do">
						     
									<input class="btn btn-primary btn-lg outline" type="submit" value="修改" style="padding: 6px 12px;background-color: #fff;">
								     <input type="hidden" name="bg_no" value="${babyGrowthVO.bg_no}">
								     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								     <input type="hidden" name="action"	value="getOne_For_Update">
								   </FORM>
			
						          
						          </td>
				          
				        </tr>
				        </c:if>
				        </c:if>
				        </c:forEach>
				       </c:forEach>
				      </tbody>
				    </table>
				  	</div>
					</div>

					<div class="col-xs-12 col-sm-4">
					</div>


					<div class="container .button col-xs-12 col-sm-4">
						<a href="bg_add.jsp"><button id="button">新增紀錄</button></a>
					</div>


					<div class="col-xs-12 col-sm-4">
					</div>
				</div>
				</div></div>
					
			<%int i=0; %>
			 <c:forEach var="babyGrowthVO" items="${list2}">
			 <%i++; %>
					<div id="mem<%=i %>" style="display: none;">
					${babyGrowthVO.bg_date},${babyGrowthVO.bg_weight},${babyGrowthVO.bg_height},${babyGrowthVO.bg_head}
					</div>
			</c:forEach>
			<div id="count" style="display: none;">
			<%=i %>
			</div>	
			
			<div id="birthday" style="display: none;">
			2017-04-22
			</div>	
			<div class="container">
			<div class="row">
				<div class="col-xs-11 col-sm-12">
					<div id="hi" style="height: 500px"></div>
				</div>
				<div class="col-xs-11 col-sm-12">
					<div id="we" style="height: 500px"></div>
				</div>
				<div class="col-xs-11 col-sm-12">
					<div id="ha" style="height: 500px"></div>
				</div>	
			</div>
			</div>



		<div style="height:200px;" ></div>
		<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script type="text/javascript" src='js/js.js'></script>
  		<script type="text/javascript" src='js/table.js'></script>
       <script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.12/c3.js"></script>
        <script type="text/javascript">
        
        
        var final =new Array();
        var heightarr =new Array();
        var headarr =new Array();
        final[0]='體重';
        headarr[0]='頭圍';
        heightarr[0]='身高';

        function $id(id){
  		  return document.getElementById(id);
  		}

  		function doFirst(){	
  			var birthday=$id("birthday").innerHTML;
  			

	

  			for(var i=1;i<=45;i++){
  				
  				var week=i;    						//月數
  				var count=$id("count").innerHTML;		//j要跑的資料筆數
  				
  				// ******開始週之秒數與結束週之秒數***********************
  				var second=new Date(birthday.trim());
  				var datasecond=second.getTime();
  				var weekstart=datasecond+1000*60*60*24*30*i;
  				var weekend=datasecond+1000*60*60*24*30*(i+1);
  				// *****************************
  				var aa=new Date(weekstart); //得到的開始
  				var bb=new Date(weekend);   //得到的結束

  				// alert("週"+week)
  				// alert("開始"+aa.toString());
  				// alert("結束"+bb.toString());
  				var matchdate=0;					//符合的筆數
  				var weight=0;						//體重
  				var height=0;
  				var head=0;

  				for(var j=1;j<=count;j++){
  					var m="mem"+j;						//取名mem1 外面div的id

  					var mm=$id(m).innerHTML;			//取值 

  					var mem=mm.split(",");				//切割成字串陣列[0]日期,[1]體重,[2]身高,[3]頭圍

  					var date=mem[0];					//抓日期

  					var d=new Date(date);				//轉時間格式

  					var second=d.getTime();				//得到秒數
  					
  					if (second>weekstart && second<=weekend){

  						matchdate++;   //符合幾的次數

			  			  weight=parseInt(mem[1])+weight;  //體重轉成int加上外面的體重
			  			
			              height=parseInt(mem[2])+height;  //體重轉成int加上外面的體重
			              head=parseInt(mem[3])+head;  //體重轉成int加上外面的體重
  						
  					}			

  				}




  		  var weightavg=weight/matchdate;		//平均值
          var heightavg=height/matchdate;
          var headavg=head/matchdate;
          

  				if (isNaN(weightavg)){
  					
  					final[week]=null;
  					heightarr[week]=null;
  					headarr[week]=null;
  					
  				}else{
  					final[week]=weightavg;
  					heightarr[week]=heightavg;
  					headarr[week]=headavg;
  					
  				}

		         

		          

  				
  				matchdate=0;						//次數跟體重都要歸0
  				weight=0;
  				height=0;
  				head=0;
  				
  			}
  			
  			
  			
  			var chart=
  	  			c3.generate({
  	  			    data: {
  	  			        x: 'x',
  	  			        columns: [
  	  			           	['x', 0,1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60],

  	  			            ['97%',4.3,5.7,7,7.9,8.6,9.2,9.7,10.2,10.5,10.9,11.2,11.5,11.8,12.1,12.4,12.7,12.9,13.2,13.5,13.7,14,14.3,14.5,14.8,15.1,15.3,15.6,15.9,16.1,16.4,16.6,16.9,17.1,17.3,17.6,17.8,18,18.3,18.5,18.7,19,19.2,19.4,19.7,19.9,20.1,20.4,20.6,20.9,21.1,21.3,21.6,21.8,22.1,22.3,22.5,22.8,23,23.3,23.5,23.8],
  	  			            ['85%',3.9,5.1,6.3,7.2,7.9,8.4,8.9,9.3,9.6,10,10.3,10.5,10.8,11.1,11.3,11.6,11.8,12,12.3,12.5,12.7,13,13.2,13.4,13.7,13.9,14.1,14.4,14.6,14.8,15,15.2,15.5,15.7,15.9,16.1,16.3,16.5,16.7,16.9,17.1,17.3,17.5,17.7,17.9,18.1,18.3,18.5,18.7,18.9,19.1,19.3,19.5,19.7,19.9,20.1,20.3,20.5,20.7,20.9,21.1],
  	  			            ['50%',3.3,4.5,5.6,6.4,7,7.5,7.9,8.3,8.6,8.9,9.2,9.4,9.6,9.9,10.1,10.3,10.5,10.7,10.9,11.1,11.3,11.5,11.8,12,12.2,12.4,12.5,12.7,12.9,13.1,13.3,13.5,13.7,13.8,14,14.2,14.3,14.5,14.7,14.8,15,15.2,15.3,15.5,15.7,15.8,16,16.2,16.3,16.5,16.7,16.8,17,17.2,17.3,17.5,17.7,17.8,18,18.2,18.3],
  	  			            ['15%',2.9,3.9,4.9,5.6,6.2,6.7,7.1,7.4,7.7,7.9,8.2,8.4,8.6,8.8,9,9.2,9.4,9.6,9.7,9.9,10.1,10.3,10.5,10.6,10.8,11,11.1,11.3,11.5,11.6,11.8,11.9,12.1,12.2,12.4,12.5,12.7,12.8,12.9,13.1,13.2,13.4,13.5,13.6,13.8,13.9,14.1,14.2,14.3,14.5,14.6,14.7,14.9,15,15.2,15.3,15.4,15.6,15.7,15.8,16],
  	  			            ['3%',2.5,3.4,4.4,5.1,5.6,6.1,6.4,6.7,7,7.2,7.5,7.7,7.8,8,8.2,8.4,8.5,8.7,8.9,9,9.2,9.3,9.5,9.7,9.8,10,10.1,10.2,10.4,10.5,10.7,10.8,10.9,11.1,11.2,11.3,11.4,11.6,11.7,11.8,11.9,12.1,12.2,12.3,12.4,12.5,12.7,12.8,12.9,13,13.1,13.3,13.4,13.5,13.6,13.7,13.8,13.9,14.1,14.2,14.3]
  	  			            ,final
  	  			        ]
  	  			    }
  	  			    ,
  	  			    color: {
  	  			        pattern: ['#d62728', '#ff7f0e', '#98df8a', '#ff7f0e', '#d62728','#17becf']
  	  			    },
  	  			    zoom: {
  	  			        enabled: true
  	  			    },
  	  			    grid: {
  	  			        x: {
  	  			            show: true
  	  			        },
  	  			        y: {
  	  			            show: true
  	  			        }
  	  			    }
  	  			    ,
  	  				axis: {
  	  			        y: {
  	  			            max: 23,
  	  			            min: 4,
  	  			            // Range includes padding, set 0 if no padding needed
  	  			            // padding: {top:0, bottom:0}
  	  			        }
  	  			    },
  	  			    bindto: '#we'
  	  			});



  	  			var chart=
  	  			c3.generate({
  	  			    data: {
  	  			        x: 'x',
  	  			        columns: [
  	  			            ['x', 0,1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60],

  	  			            ['97%',53.4,58.4,62.2,65.3,67.8,69.9,71.6,73.2,74.7,76.2,77.6,78.9,80.2,81.5,82.7,83.9,85.1,86.2,87.3,88.4,89.5,90.5,91.6,92.6,93.6,93.8,94.8,95.7,96.6,97.5,98.3,99.2,100,100.8,101.5,102.3,103.1,103.8,104.5,105.2,105.9,106.6,107.3,108,108.6,109.3,109.9,110.6,111.2,111.8,112.5,113.1,113.7,114.3,115,115.6,116.2,116.8,117.4,118.1,118.7],
  	  			            ['85%',51.8,56.7,60.5,63.5,66,68.1,69.8,71.4,72.9,74.3,75.6,77,78.2,79.4,80.6,81.8,82.9,84,85.1,86.1,87.1,88.1,89.1,90,91,91.2,92.1,93,93.8,94.7,95.5,96.2,97,97.8,98.5,99.2,99.9,100.6,101.3,102,102.7,103.3,104,104.6,105.2,105.8,106.5,107.1,107.7,108.3,108.9,109.5,110.1,110.7,111.2,111.8,112.4,113,113.6,114.2,114.8],
  	  			            ['50%',49.9,54.7,58.4,61.4,63.9,65.9,67.6,69.2,70.6,72,73.3,74.5,75.7,76.9,78,79.1,80.2,81.2,82.3,83.2,84.2,85.1,86,86.9,87.8,88,88.8,89.6,90.4,91.2,91.9,92.7,93.4,94.1,94.8,95.4,96.1,96.7,97.4,98,98.6,99.2,99.9,100.4,101,101.6,102.2,102.8,103.3,103.9,104.4,105,105.6,106.1,106.7,107.2,107.8,108.3,108.9,109.4,110],
  	  			            ['15%',47.9,52.7,56.4,59.3,61.7,63.7,65.4,66.9,68.3,69.6,70.9,72.1,73.3,74.4,75.5,76.5,77.5,78.5,79.5,80.4,81.3,82.2,83,83.8,84.6,84.7,85.5,86.3,87,87.7,88.4,89.1,89.7,90.4,91,91.6,92.2,92.8,93.4,94,94.6,95.2,95.7,96.3,96.8,97.4,97.9,98.5,99,99.5,100,100.5,101.1,101.6,102.1,102.6,103.1,103.6,104.1,104.7,105.2],
  	  			            ['3%',46.3, 51.1, 54.7, 57.6, 60, 61.9, 63.6, 65.1, 66.5, 67.7, 69, 70.2, 71.3, 72.4, 73.4, 74.4, 75.4, 76.3, 77.2, 78.1, 78.9, 79.7, 80.5, 81.3, 82.1, 82.1, 82.8, 83.5,84.2, 84.9, 85.5, 86.2, 86.8, 87.4, 88, 88.5, 89.1, 89.7, 90.2, 90.8, 91.3, 91.9, 92.4, 92.9, 93.4, 93.9, 94.4, 94.9, 95.4, 95.9, 96.4, 96.9, 97.4, 97.9, 98.4,98.8, 99.3, 99.8, 100.3, 100.8, 101.2],
  	  			        heightarr
  	  			            
  	  			        ]
  	  			    }
  	  			    ,
  	  			    color: {
  	  			        pattern: ['#d62728', '#ff7f0e', '#98df8a', '#ff7f0e', '#d62728','#17becf']
  	  			    },
  	  			    zoom: {
  	  			        enabled: true
  	  			    },
  	  			    grid: {
  	  			        x: {
  	  			            show: true
  	  			        },
  	  			        y: {
  	  			            show: true
  	  			        }
  	  			    }
  	  			    ,
  	  			    axis: {
  	  			        y: {
  	  			            max: 118,
  	  			            min: 52
  	  			            ,
  	  			            // Range includes padding, set 0 if no padding needed
  	  			            // padding: {top:0, bottom:0}
  	  			        }
  	  			    },
  	  			    bindto: '#hi'
  	  			});



  	  			var chart=
  	  			c3.generate({
  	  			    data: {
  	  			        x: 'x',
  	  			        columns: [
  	  			            ['x', 0,1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60],

  	  			            ['97%',36.9, 39.5, 41.3, 42.7, 43.9, 44.8, 45.6, 46.3, 46.9, 47.4, 47.8, 48.2, 48.5, 48.8, 49, 49.3, 49.5, 49.7, 49.9, 50, 50.2, 50.4, 50.5, 50.7, 50.8, 50.9, 51.1, 51.2, 51.3, 51.4, 51.6, 51.7, 51.8, 51.9, 52, 52, 52.1, 52.2, 52.3, 52.4, 52.4, 52.5, 52.6, 52.7, 52.7, 52.8, 52.8, 52.9, 53, 53, 53.1, 53.1, 53.2, 53.2, 53.3, 53.3, 53.4, 53.4, 53.5, 53.5, 53.5],
  	  			            ['85%',35.8, 38.5, 40.3, 41.7, 42.9, 43.8, 44.6, 45.3, 45.8, 46.3, 46.7, 47.1, 47.4, 47.7, 47.9, 48.2, 48.4, 48.6, 48.7, 48.9, 49.1, 49.2, 49.4, 49.5, 49.7, 49.8, 49.9, 50, 50.2, 50.3, 50.4, 50.5, 50.6, 50.7, 50.8, 50.8, 50.9, 51, 51.1, 51.2, 51.2, 51.3, 51.4, 51.4, 51.5, 51.6, 51.6, 51.7, 51.7, 51.8, 51.8, 51.9, 51.9, 52, 52, 52.1, 52.1, 52.2, 52.2, 52.2, 52.3],
  	  			            ['50%',34.5, 37.3, 39.1, 40.5, 41.6, 42.6, 43.3, 44, 44.5, 45, 45.4, 45.8, 46.1, 46.3, 46.6, 46.8, 47, 47.2, 47.4, 47.5, 47.7, 47.8, 48, 48.1, 48.3, 48.4, 48.5, 48.6, 48.7, 48.8, 48.9, 49, 49.1, 49.2, 49.3, 49.4, 49.5, 49.5, 49.6, 49.7, 49.7, 49.8, 49.9, 49.9, 50, 50.1, 50.1, 50.2, 50.2, 50.3, 50.3, 50.4, 50.4, 50.4, 50.5, 50.5, 50.6, 50.6, 50.7, 50.7, 50.7],
  	  			            ['15%',33.1, 36.1, 37.9, 39.3, 40.4, 41.3, 42.1, 42.7, 43.2, 43.7, 44.1, 44.4, 44.7, 45, 45.2, 45.5, 45.6, 45.8, 46, 46.2, 46.3, 46.4, 46.6, 46.7, 46.8, 47, 47.1, 47.2, 47.3, 47.4, 47.5, 47.6, 47.7, 47.8, 47.8, 47.9, 48, 48.1, 48.1, 48.2, 48.3, 48.3, 48.4, 48.4, 48.5, 48.5, 48.6, 48.6, 48.7, 48.7, 48.8, 48.8, 48.9, 48.9, 49, 49, 49, 49.1, 49.1, 49.2, 49.2],
  	  			            ['3%',32.1, 35.1, 36.9, 38.3, 39.4, 40.3, 41, 41.7, 42.2, 42.6, 43, 43.4, 43.6, 43.9, 44.1, 44.3, 44.5, 44.7, 44.9, 45, 45.2, 45.3, 45.4, 45.6, 45.7, 45.8, 45.9, 46, 46.1, 46.2, 46.3, 46.4, 46.5, 46.6, 46.6, 46.7, 46.8, 46.9, 46.9, 47, 47, 47.1, 47.2, 47.2, 47.3, 47.3, 47.4, 47.4, 47.5, 47.5, 47.5, 47.6, 47.6, 47.7, 47.7, 47.7, 47.8, 47.8, 47.9, 47.9, 47.9]
  	  			            ,headarr
  	  			        ]
  	  			    }
  	  			    ,
  	  			    color: {
  	  			        pattern: ['#d62728', '#ff7f0e', '#98df8a', '#ff7f0e', '#d62728','#17becf']
  	  			    },
  	  			    zoom: {
  	  			        enabled: true
  	  			    },
  	  			    grid: {
  	  			        x: {
  	  			            show: true
  	  			        },
  	  			        y: {
  	  			            show: true
  	  			        }
  	  			    }
  	  			    ,
  	  			    axis: {
  	  			        y: {
  	  			            max: 52,
  	  			            min: 35
  	  			            ,
  	  			            // Range includes padding, set 0 if no padding needed
  	  			            // padding: {top:0, bottom:0}
  	  			        }
  	  			    },
  	  			    bindto: '#ha'
  	  			});
  		
  		}


			
  		window.addEventListener('load',doFirst,false);
		
  		
  		
        
        </script>
		
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
	</body>
</html>


