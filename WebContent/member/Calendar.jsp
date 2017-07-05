
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.baby_eating.model.*"%>
<%@ page import="com.baby_data.model.*"%>
<%@ page import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<%
  	BabyDataService BDSvc = new BabyDataService();
	List<BabyDataVO> list = BDSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
	<head>
  <style type="text/css">
        .col-sm-4 div:hover {
      z-index: 0.5;
    }

     div:hover > #hi {
      -webkit-transform: scale(1.7,1.7);
                
      -webkit-transition: .3s transform;
     
    }
    div:hover > #we {
      -webkit-transform: scale(1.7,1.7);
                
      -webkit-transition: .3s transform;
     
    }
    div:hover > #ha {
      -webkit-transform: scale(1.7,1.7);
                
      -webkit-transition: .3s transform;
     
    }  
  </style>
	<title>歡迎使用媽咪寶典</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- 引用icon -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- 引用Css -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/title.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/calendar.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.12/c3.css" rel="stylesheet" type="text/css">

		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		

	</head>
	<body>
<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
		<div class="container">
			<div class="row">

				
				<div class="col-xs-12 col-sm-12">
					<div class="widget center">
						  <div class="text center">
						    <h1 class="">專屬行事曆</h1>
						    <p>Go ahead, resize me.</p>
						  </div>
						  <div class="blur">
						  </div>
					</div>	
				</div>
<!-- 			<div class="col-xs-11 col-sm-5"> -->
        	
<%-- 			<jsp:useBean id="babyDataSvc" scope="page" class="com.baby_data.model.BabyDataService" /> --%>
          
<!--             <div class="input-group" style=" width: 95%; margin: 10px;"> -->
<!--             <form> -->
<!--               <select id="bd_no" size="1" name="bd_no" style="height: 30px;"> -->
<%--                 <c:forEach var="babyDataVO" items="${babyDataSvc.all}"> --%>
<%--                 <c:if test="${userVO.mem_no==babyDataVO.mem_no}"> --%>
<%--                 <option value="${babyDataVO.bd_no}" ${(babyGrowthVO.bd_no==babyDataVO.bd_no)?'selected':'' } >${babyDataVO.bd_name} --%>
<%--                 </c:if> --%>
<%--                 </c:forEach> --%>
<!--               </select> -->
<!--               <button type="button" onclick="myFunction()">送出</button> -->
<!--             </form> -->
            
<!--             </div> -->
            </div>
            	<div class="col-xs-12 col-sm-3">
				 
						<jsp:include page="../listgrop.jsp" flush="true"/>
				</div>
         
				<div class="col-xs-11 col-sm-6">
				<div id="calendar"></div>
				</div>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
				<link href='https://fonts.googleapis.com/css?family=Exo+2:400,100' rel='stylesheet' type='text/css'>
				<div class="col-xs-12 col-sm-3">
					<div class="col-xs-12 col-sm-12" style="margin-top:12em;display: inline-flex">
					<div style="width: 16px;height: 16px;" class="pink"></div>寶寶睡眠
					</div>
					
					<div class="col-xs-12 col-sm-12" style="display: inline-flex">
					<div style="width: 16px;height: 16px;" class="cyan"></div>胎動記錄
					</div>
					
					<div class="col-xs-12 col-sm-12" style=";display: inline-flex">
					<div style="width: 16px;height: 16px;" class="red"></div>產檢日
					</div>
					
					<div class="col-xs-12 col-sm-12" style="display: inline-flex">
					<div style="width: 16px;height: 16px;" class="blue"></div>寶寶進食記錄
					</div>
					
					<div class="col-xs-12 col-sm-12" style="display: inline-flex">
					<div style="width: 16px;height: 16px;" class="black"></div>媽媽體重
					</div>
					
					<div class="col-xs-12 col-sm-12" style="display: inline-flex">
					<div style="width: 16px;height: 16px;" class="green"></div>寶寶生長紀錄
					</div>
					
					<div class="col-xs-12 col-sm-12" style="display: inline-flex">
					<div style="width: 16px;height: 16px;" class="orange"></div>寶寶排泄記錄
					</div>
					
					
					
				</div>
			</div>
		
		<div class="container">
			<div class="row">
				<div class="col-xs-11 col-sm-12">
					<div id="mh"></div>
				</div>
			
		    	<div class="col-xs-11 col-sm-6">
					<div id="beat"></div>
	       		</div>
	       		<div class="col-xs-11 col-sm-6">
					<div id="fmlvch"></div>
	       		</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-11 col-sm-4">
					<div id="hi" ></div>
				</div>
				<div class="col-xs-11 col-sm-4">
					<div id="we" ></div>
				</div>
				<div class="col-xs-11 col-sm-4">
					<div id="ha" ></div>
				</div>	
			</div>
			</div>
					
				
		<div style="display: none;">
		<jsp:useBean id="BESvc" scope="page" class="com.baby_eating.model.BabyEatingService" />
					 <%int i=0; %>
				      <c:forEach var="babyEatingVO" items="${BESvc.all}">
				      
				      		<div id="bd_no<%=i %>">${babyEatingVO.bd_no}</div>
				      		<div id="be_date<%=i %>">${babyEatingVO.be_date}</div>
					        <div id="be_sort<%=i %>">${babyEatingVO.be_sort}</div>
					        <div id="be_mete<%=i %>">${babyEatingVO.be_mete}</div>
					  <%i++; %>
				      </c:forEach>
	  	<div id="becount"><%=i %></div>
	  	
	  	 <jsp:useBean id="BEXSvc" scope="page" class="com.baby_excretion.model.BabyExcretionService" />
	        <c:forEach var="babyExcretionVO" items="${BEXSvc.all}">
	        <c:forEach var="babyDataVO" items="${list}">
	        
		        <c:if test="${userVO.mem_no==babyDataVO.mem_no}">
		  		<c:if test="${babyDataVO.bd_no==babyExcretionVO.bd_no}">
			       
			        	<div id="bd_name<%=i %>">${babyDataVO.bd_name}</div>
			          	<div id="bex_date<%=i %>">${babyExcretionVO.bex_date}</div>
			           <div id="bex_details<%=i %>">${babyExcretionVO.bex_details}</div>
				       <%i++; %>   
        </c:if></c:if></c:forEach></c:forEach>
        	<div id="bexcount"><%=i %></div>
        	
         <jsp:useBean id="BGSvc" scope="page" class="com.baby_growth.model.BabyGrowthService" />
		      <c:forEach var="babyGrowthVO" items="${BGSvc.all}">
		      <c:forEach var="babyDataVO" items="${list}">
		      <c:if test="${userVO.mem_no==babyDataVO.mem_no}">
			  		<c:if test="${babyDataVO.bd_no==babyGrowthVO.bd_no}">
		        
		          <div id="bd_namecl<%=i %>">${babyDataVO.bd_name}</div>
		           <div id="bg_datecl<%=i %>">${babyGrowthVO.bg_date}</div>
		           <div id="bg_heightcl<%=i %>">${babyGrowthVO.bg_height}</div>
		           <div id="bg_weightcl<%=i %>">${babyGrowthVO.bg_weight}</div>
		           <div id="bg_headcl<%=i %>">${babyGrowthVO.bg_head}</div>
		          <%i++; %>
		            </c:if></c:if></c:forEach></c:forEach>
			<div id="bgcount"><%=i %></div>
			
		
		<jsp:useBean id="BSSvc" scope="page" class="com.baby_sleep.model.BabySleepService" />
	        <c:forEach var="babySleepVO" items="${BSSvc.all}">
	        <c:forEach var="babyDataVO" items="${list}">
	        
		        <c:if test="${userVO.mem_no==babyDataVO.mem_no}">
		  		<c:if test="${babyDataVO.bd_no==babySleepVO.bd_no}">
			      
			        	<div id="bd_nameclsp<%=i %>">${babyDataVO.bd_name}</div>
			          	<div id="bs_startclsp<%=i %>">${babySleepVO.bs_start}</div>
			          	<div id="bs_time<%=i %>">${babySleepVO.bs_time}</div>
			        <%i++; %>
			     </c:if></c:if></c:forEach></c:forEach>
			     <div id="bscount"><%=i %></div>
		 <jsp:useBean id="FMSvc" scope="page" class="com.fetal_movement.model.FetalMovementService" />
	         <c:forEach var="fetalMovementVO" items="${FMSvc.all}">
	       
	         <c:if test="${userVO.mem_no==fetalMovementVO.mem_no}">
	        
	          
	          <div id="fm_date<%=i %>">${fetalMovementVO.fm_date}</div>
	          <div id="fm_lv<%=i %>">${fetalMovementVO.fm_lv}</div>	          
	          
	        <%i++; %>
	        
	        </c:if>
	        </c:forEach>
	        <div id="lvcount" style="display: none;"><%=i %></div>
	    	
	      <jsp:useBean id="InsSvc" scope="page" class="com.inspection.model.InspectionService" />
				        <c:forEach var="inspectionVO" items="${InsSvc.all}">
				        <c:if test="${userVO.mem_no==inspectionVO.mem_no}">
				        
				         <div id="ins_hospital<%=i %>">${inspectionVO.ins_hospital}</div>
				         <div id="ins_resetvation_no<%=i %>">${inspectionVO.ins_resetvation_no}</div>
				          <div id="ins_date<%=i %>">${inspectionVO.ins_date}</div>
				          	<%i++; %>
				          </c:if></c:forEach>		     
			<div id="inscount" style="display: none;"><%=i %></div>
			<%int mhh=0; %>
      		<jsp:useBean id="MHSvc" scope="page" class="com.mom_health.model.MomHealthService" />
                	<c:forEach var="mom_healthVO" items="${MHSvc.all}">
                 	<c:if test="${userVO.mem_no==mom_healthVO.mem_no}">
                     <div id="mh_date<%=mhh %>">${mom_healthVO.mh_date}</div>
                     <div id="mh_weight<%=mhh %>">${mom_healthVO.mh_weight}</div>
                     <%mhh++; %>
                   </c:if></c:forEach>		     
			<div id="mhh" style="display: none;"><%=mhh %></div>		     
				     
		  <%int mh=0; %>
		  
		    <c:forEach var="mom_healthVO" items="${MHSvc.all}">
		    
		    <div id="mhdatech<%=mh %>" >${mom_healthVO.mh_date}</div>
		    <div id="mhweightch<%=mh %>">${mom_healthVO.mh_weight}</div>
		    <%mh++; %>
		    </c:forEach>
		    
		   	<div id="mhcountch" ><%=mh %></div>
		   	
		<%int fm=0; %>
	         <c:forEach var="fetalMovementVO" items="${FMSvc.all}">
	        
	         <c:if test="${userVO.mem_no==fetalMovementVO.mem_no}">

	          <div id="fm_lvch<%=fm %>">${fetalMovementVO.fm_lv}</div>	          
	          
	        <%fm++; %>
	        
	        </c:if>
	        </c:forEach>
	        <div id="fm_lvch"><%=fm %></div>
		</div>
		<%int bg=0; %>
			 <c:forEach var="babyGrowthVO" items="${BGSvc.all}">
			 <%bg++; %>
					<div id="mem<%=bg %>" style="display: none;">
					${babyGrowthVO.bg_date},${babyGrowthVO.bg_weight},${babyGrowthVO.bg_height},${babyGrowthVO.bg_head}
					</div>
			</c:forEach>
			<div id="bgcountch" style="display: none;">
			<%=bg %>
			</div>	
			
			<div id="birthday" style="display: none;">
			2013-04-22
			</div>	
		
		  
		<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
			
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/js/js.js'></script>
  		<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.12/c3.js"></script>
  		<script type="text/javascript">
  		var x ;
//   		var c1 = { eventName: '蝴蝶你好棒', calendar: 'Work', color: 'orange', date: moment('2017-6-5') };
//   		var c2 = { eventName: '蝴蝶你好棒', calendar: 'Sports', color: 'blue', date: moment('2017/6/5') };
//   		var c3 = [c1,c2];
		var c8 = new Array();
  		var becount=$id("becount").innerHTML;
  		var bexcount=$id("bexcount").innerHTML;
  		var bgcount=$id("bgcount").innerHTML;
  		var bscount=$id("bscount").innerHTML;
  		var lvcount=$id("lvcount").innerHTML;
  		var inscount=$id("inscount").innerHTML;
  		var mhcount=$id("mhh").innerHTML;
  		var bedate1 =new Array();   //be的資料
  		var bedate2 =new Array();
  		var bedate3 =new Array();
  		var bedate4 =new Array();
  		var bedate5 =new Array();
  		var bedate6 =new Array();
  		bedate1[0]="美強生";bedate1[1]=0;
  		bedate2[0]="母奶";bedate2[1]=0;
  		bedate3[0]="明治";bedate3[1]=0;
  		bedate4[0]="桂格";bedate4[1]=0;
  		bedate5[0]="雀巢";bedate5[1]=0;
  		bedate6[0]="其他";bedate6[1]=0;
  		var mhdatech = new Array();
  		var mhweightch = new Array();
  		var mhcountch=$id("mhcountch").innerHTML;
  		mhdatech[0]="x";
  		mhweightch[0]="媽媽體重"
  		
  		var fmdate1 =new Array();   //fm的資料
  		var fmdate2 =new Array();
  		var fmdate3 =new Array();
  		var fmdate4 =new Array();
  		var fmdate5 =new Array();
  		
  		fmdate1[0]="胎動等級A";fmdate1[1]=0;
  		fmdate2[0]="胎動等級B";fmdate2[1]=0;
  		fmdate3[0]="胎動等級C";fmdate3[1]=0;
  		fmdate4[0]="胎動等級D";fmdate4[1]=0;
  		fmdate5[0]="胎動等級E";fmdate5[1]=0;
  		var fm_lvch=$id("fm_lvch").innerHTML;
  		
  		

        var final =new Array();
        var heightarr =new Array();
        var headarr =new Array();
        final[0]='體重';
        headarr[0]='頭圍';
        heightarr[0]='身高';
        var birthday=$id("birthday").innerHTML;

  		function $id(id){
    		return document.getElementById(id);
    	}
//   		window.addEventListener('load',myFunction,false);
  		function myFunction() {
  		    x = document.getElementById("bd_no").value;
  		  	
  		  	
  		  return beFist()
  		}
  		
  		window.addEventListener('load',doFist,false);
  		
  		function doFist() {
  			
  			
  			
	        for(var q=0;q<mhcountch;q++){
	        	
		          var aa="mhdatech"+q;
		         
		          var mhdatechh=$id(aa).innerHTML;
		          var bb="mhweightch"+q; 
		          var mhweightchh=$id(bb).innerHTML;
		        
		          mhdatech[q+1]=mhdatechh;
		          mhweightch[q+1]=mhweightchh;
		          
		
		    }
	        
  			for(var w=0;w<fm_lvch;w++){
  				var fm_lvchh="fm_lvch"+w;	
  				var fm_lvchhh=$id(fm_lvchh).innerHTML;
  				
  				
  					if(fm_lvchhh=="A"){
  						fmdate1[1]=fmdate1[1]+1;
  						
  					}else if(fm_lvchhh=="B"){
  						fmdate2[1]=fmdate2[1]+1;
  						
  					}else if(fm_lvchhh=="C"){
  						fmdate3[1]=fmdate3[1]+1;
  						
  					}else if(fm_lvchhh=="D"){
  						fmdate4[1]=fmdate4[1]+1;
  						
  					}else if(fm_lvchhh=="E"){
  						fmdate5[1]=fmdate5[1]+1;
  						
  					}
  					
  				
  				
  			}
  			
  			for(var j=0;j<becount;j++){
  				var bd_noo="bd_no"+j;	
  				var bd_nooo=$id(bd_noo).innerHTML;
  				var be_sortt="be_sort"+j;	
  				var be_sorttt=$id(be_sortt).innerHTML;
  				var be_metee="be_mete"+j;	
  				var be_meteee=$id(be_metee).innerHTML;
  				var be_datee="be_date"+j;	
  				var be_dateee=$id(be_datee).innerHTML;
  				

  			
  				
  					if(be_sorttt=="美強生"){
  						bedate1[1]=bedate1[1]+parseInt(be_meteee);
  						
  					}else if(be_sorttt=="明治"){
  						bedate3[1]=bedate3[1]+parseInt(be_meteee);
  						
  					}else if(be_sorttt=="母奶"){
  						bedate2[1]=bedate2[1]+parseInt(be_meteee);
  						
  					}else if(be_sorttt=="桂格"){
  						bedate4[1]=bedate4[1]+parseInt(be_meteee);
  						
  					}else if(be_sorttt=="雀巢"){
  						bedate5[1]=bedate5[1]+parseInt(be_meteee);
  						
  					}else if(be_sorttt=="其他"){
  						bedate6[1]=bedate6[1]+parseInt(be_meteee);
  						
  					}
  					
  				}
  			
  			
  			
			for(var z=1;z<=60;z++){
  				
  				var week=z;    						//月數
  				var bgcountch=$id("bgcountch").innerHTML;		//j要跑的資料筆數
  				
  				// ******開始週之秒數與結束週之秒數***********************
  				var second=new Date(birthday.trim());
  				var datasecond=second.getTime();
  				var weekstart=datasecond+1000*60*60*24*30*z;
  				var weekend=datasecond+1000*60*60*24*30*(z+1);
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

  				for(var j=1;j<=bgcountch;j++){
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

  			
  			
  				
  			
			




  		    
  			
  			var chart = c3.generate({
	  		    data: {
	  		        // iris data from R
	  		        columns: [
	  		        	bedate1,bedate2,bedate3,bedate4,bedate5,bedate6
	  		        ],
	  		        type : 'pie',
	  		        onclick: function (d, i) { console.log("onclick", d, i); },
	  		        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
	  		        onmouseout: function (d, i) { console.log("onmouseout", d, i); }
	  		    },
                bindto: '#beat'
	  		});


  			
  			
  			
	        var chart=
		          c3.generate({
		              data: {
		                  x: 'x',
		                  columns: [
		                	  mhdatech,
		                	  mhweightch
			                  
			                  ]
			              }
			            ,
			          color: {
			              pattern: ['#1f77b4', '#aec7e8', '#ff7f0e', '#ffbb78', '#2ca02c', '#98df8a', '#d62728', '#ff9896', '#9467bd', '#c5b0d5', '#8c564b', '#c49c94', '#e377c2', '#f7b6d2', '#7f7f7f', '#c7c7c7', '#bcbd22', '#dbdb8d', '#17becf', '#9edae5']
			          },
			          zoom: {
			              enabled: true
			          },
			          axis: {
			              x: {
			
			                  type: 'timeseries',
			                  tick: {
			                      format: '%Y-%m-%d'
			                  }
			              }
			          },
			          bindto: '#mh'
			      });
	        
	        var chart = c3.generate({
	  		    data: {
	  		        // iris data from R
	  		        columns: [
	  		        	fmdate1,fmdate2,fmdate3,fmdate4,fmdate5
	  		        ],
	  		        type : 'pie',
	  		        onclick: function (d, i) { console.log("onclick", d, i); },
	  		        onmouseover: function (d, i) { console.log("onmouseover", d, i); },
	  		        onmouseout: function (d, i) { console.log("onmouseout", d, i); }
	  		    },
                bindto: '#fmlvch'
	  		});

		  		 	  	
	  		}	
  		
  		
  		function beFist() {
  			
  			

  		
  		}
  		
  		
  		
  		
  		
//   	日立
  		
  		window.addEventListener('load',bsFist,false);
		
  		
  		
  		function bsFist() {
  			var a = 0;
  			for(var j=0;j<becount;j++){
  				a++;
  				var be_sortt="be_sort"+j;	
  				var be_sorttt=$id(be_sortt).innerHTML;
  				var be_metee="be_mete"+j;	
  				var be_meteee=$id(be_metee).innerHTML;
  				var be_datee="be_date"+j;	
  				var be_dateee=$id(be_datee).innerHTML;
  				
  				be_date = { eventName: "進食紀錄："+be_sorttt+" "+be_meteee, calendar: 'Sports', color: 'blue', date: moment(be_dateee) };
  				c8[0] = { eventName: '', calendar: 'Work', color: '', date: moment('2017-6-5') };
  				c8[j+1] =  be_date;
  			}
  			
  			for(j=a;j<bexcount;j++){
  				a++;
  				var bd_namee="bd_name"+j;	
  				var bd_nameee=$id(bd_namee).innerHTML;
  				
  				var bex_datee="bex_date"+j;	
  				var bex_dateee=$id(bex_datee).innerHTML;
  				
  				var bex_detailss="bex_details"+j;	
  				var bex_detailsss=$id(bex_detailss).innerHTML;
  				
  				bex_date = { eventName: "排泄紀錄："+bd_nameee+"，"+bex_detailsss, calendar: 'Sports', color: 'orange', date: moment(bex_dateee) };
  				c8[j+1] =  bex_date;
  			}
  		
  			for(j=a;j<bgcount;j++){
  				a++;
  				var bd_namecll="bd_namecl"+j;	
  				var bd_nameclll=$id(bd_namecll).innerHTML;
  				
  				var bg_datecll="bg_datecl"+j;	
  				var bg_dateclll=$id(bg_datecll).innerHTML;
  				
  				var bg_heightcll="bg_heightcl"+j;	
  				var bg_heightclll=$id(bg_heightcll).innerHTML;
  				
  				var bg_weightcll="bg_weightcl"+j;	
  				var bg_weightclll=$id(bg_weightcll).innerHTML;
  				
  				var bg_heightcll="bg_heightcl"+j;	
  				var bg_heightclll=$id(bg_heightcll).innerHTML;
  				
  				bg_date = { eventName: "寶寶生長紀錄："+bd_nameclll+" 身高："+bg_heightclll+" 體重："+bg_weightclll+" 頭圍："+bg_heightclll, calendar: '生長  ', color: 'green', date: moment(bg_dateclll) };
  				c8[j+1] =  bg_date;
  			}
  	
  			
  			for(j=a;j<bscount;j++){
  				a++;
  				var bd_nameclspp="bd_nameclsp"+j;	
  				var bd_nameclsppp=$id(bd_nameclspp).innerHTML;
  				
  				var bs_startclspp="bs_startclsp"+j;	
  				var bs_startclsppp=$id(bs_startclspp).innerHTML;
  				
  				var bs_timee="bs_time"+j;	
  				var bs_timeee=$id(bs_timee).innerHTML;

  				bs_date = { eventName: "寶寶睡眠 紀錄："+bd_nameclsppp+" 睡眠："+bs_timeee, calendar: '睡眠', color: 'pink', date: moment(bs_startclsppp) };
  				c8[j+1] =  bs_date;
  			}
  			
  			for(j=a;j<lvcount;j++){
  				a++;
  				var fm_datee="fm_date"+j;	
  				var fm_dateee=$id(fm_datee).innerHTML;
  				
  				var fm_lvv="fm_lv"+j;	
  				var fm_lvvv=$id(fm_lvv).innerHTML;
  				
  				

  				fm_date = { eventName: "胎動紀錄： 等級 "+fm_lvvv, calendar: '胎動', color: 'cyan', date: moment(fm_dateee) };
  				c8[j+1] =  fm_date;
  			}
  			
  			for(j=a;j<inscount;j++){
  				a++;
  				var ins_hospitall="ins_hospital"+j;	
  				var ins_hospital=$id(ins_hospitall).innerHTML;
  				
  				var ins_resetvation_noo="ins_resetvation_no"+j;	
  				var ins_resetvation_no=$id(ins_resetvation_noo).innerHTML;
  				
  				var ins_datee="ins_date"+j;	
  				var ins_date=$id(ins_datee).innerHTML;
  				
  				

  				ins_date = { eventName: "產檢日： "+ins_date+" 醫院 "+ins_hospital+" 號碼 "+ins_resetvation_no, calendar: '產檢', color: 'red', date: moment(ins_date) };
  				c8[j+1] =  ins_date;
  			}
  			
  			
  			
  			for(var mhh=0;mhh<mhcount;mhh++){
  				
  				a++;
  				var mh_datee="mh_date"+mhh;	
  				var mh_date=$id(mh_datee).innerHTML;
  				
  				var mh_weightt="mh_weight"+mhh;	
  				var mh_weight=$id(mh_weightt).innerHTML;
				
				
  				
  				mh_date = { eventName: "媽媽體重： "+mh_weight, calendar: '媽媽', color: 'black', date: moment(mh_date) };
  				c8[a+1] =  mh_date;
  			}
  		
  			
  			
  		
  		!function() {

  		  var today = moment();

  		  function Calendar(selector, events) {
  		    this.el = document.querySelector(selector);
  		    this.events = events;
  		    this.current = moment().date(1);
  		    this.draw();
  		    var current = document.querySelector('.today');
  		    if(current) {
  		      var self = this;
  		      window.setTimeout(function() {
  		        self.openDay(current);
  		      }, 500);
  		    }
  		  }

  		  Calendar.prototype.draw = function() {
  		    //Create Header
  		    this.drawHeader();

  		    //Draw Month
  		    this.drawMonth();
  		  }

  		  Calendar.prototype.drawHeader = function() {
  		    var self = this;
  		    if(!this.header) {
  		      //Create the header elements
  		      this.header = createElement('div', 'header');
  		      this.header.className = 'header';

  		      this.title = createElement('h1');

  		      var right = createElement('div', 'right');
  		      right.addEventListener('click', function() { self.nextMonth(); });

  		      var left = createElement('div', 'left');
  		      left.addEventListener('click', function() { self.prevMonth(); });

  		      //Append the Elements
  		      this.header.appendChild(this.title); 
  		      this.header.appendChild(right);
  		      this.header.appendChild(left);
  		      this.el.appendChild(this.header);
  		    }

  		    this.title.innerHTML = this.current.format('MMM YYYY');
  		  }

  		  Calendar.prototype.drawMonth = function() {
  		    var self = this;
  		    
  		    // this.events.forEach(function(ev) {
  		    //  ev.date = self.current.clone().date(Math.random() * (29 - 1) + 1);
  		    // });
  		    
  		    
  		    if(this.month) {
  		      this.oldMonth = this.month;
  		      this.oldMonth.className = 'month out ' + (self.next ? 'next' : 'prev');
  		      this.oldMonth.addEventListener('webkitAnimationEnd', function() {
  		        self.oldMonth.parentNode.removeChild(self.oldMonth);
  		        self.month = createElement('div', 'month');
  		        self.backFill();
  		        self.currentMonth();
  		        self.fowardFill();
  		        self.el.appendChild(self.month);
  		        window.setTimeout(function() {
  		          self.month.className = 'month in ' + (self.next ? 'next' : 'prev');
  		        }, 16);
  		      });
  		    } else {
  		        this.month = createElement('div', 'month');
  		        this.el.appendChild(this.month);
  		        this.backFill();
  		        this.currentMonth();
  		        this.fowardFill();
  		        this.month.className = 'month new';
  		    }
  		  }

  		  Calendar.prototype.backFill = function() {
  		    var clone = this.current.clone();
  		    var dayOfWeek = clone.day();

  		    if(!dayOfWeek) { return; }

  		    clone.subtract('days', dayOfWeek+1);

  		    for(var i = dayOfWeek; i > 0 ; i--) {
  		      this.drawDay(clone.add('days', 1));
  		    }
  		  }

  		  Calendar.prototype.fowardFill = function() {
  		    var clone = this.current.clone().add('months', 1).subtract('days', 1);
  		    var dayOfWeek = clone.day();

  		    if(dayOfWeek === 6) { return; }

  		    for(var i = dayOfWeek; i < 6 ; i++) {
  		      this.drawDay(clone.add('days', 1));
  		    }
  		  }

  		  Calendar.prototype.currentMonth = function() {
  		    var clone = this.current.clone();

  		    while(clone.month() === this.current.month()) {
  		      this.drawDay(clone);
  		      clone.add('days', 1);
  		    }
  		  }

  		  Calendar.prototype.getWeek = function(day) {
  		    if(!this.week || day.day() === 0) {
  		      this.week = createElement('div', 'week');
  		      this.month.appendChild(this.week);
  		    }
  		  }

  		  Calendar.prototype.drawDay = function(day) {
  		    var self = this;
  		    this.getWeek(day);

  		    //Outer Day
  		    var outer = createElement('div', this.getDayClass(day));
  		    outer.addEventListener('click', function() {
  		      self.openDay(this);
  		    });

  		    //Day Name
  		    var name = createElement('div', 'day-name', day.format('ddd'));

  		    //Day Number
  		    var number = createElement('div', 'day-number', day.format('DD'));


  		    //Events
  		    var events = createElement('div', 'day-events');
  		    this.drawEvents(day, events);

  		    outer.appendChild(name);
  		    outer.appendChild(number);
  		    outer.appendChild(events);
  		    this.week.appendChild(outer);
  		  }

  		  Calendar.prototype.drawEvents = function(day, element) {
  		    if(day.month() === this.current.month()) {
  		      var todaysEvents = this.events.reduce(function(memo, ev) {
  		        if(ev.date.isSame(day, 'day')) {
  		          memo.push(ev);
  		        }
  		        return memo;
  		      }, []);

  		      todaysEvents.forEach(function(ev) {
  		        var evSpan = createElement('span', ev.color);
  		        element.appendChild(evSpan);
  		      });
  		    }
  		  }

  		  Calendar.prototype.getDayClass = function(day) {
  		    classes = ['day'];
  		    if(day.month() !== this.current.month()) {
  		      classes.push('other');
  		    } else if (today.isSame(day, 'day')) {
  		      classes.push('today');
  		    }
  		    return classes.join(' ');
  		  }

  		  Calendar.prototype.openDay = function(el) {
  		    var details, arrow;
  		    var dayNumber = +el.querySelectorAll('.day-number')[0].innerText || +el.querySelectorAll('.day-number')[0].textContent;
  		    var day = this.current.clone().date(dayNumber);

  		    var currentOpened = document.querySelector('.details');

  		    //Check to see if there is an open detais box on the current row
  		    if(currentOpened && currentOpened.parentNode === el.parentNode) {
  		      details = currentOpened;
  		      arrow = document.querySelector('.arrow');
  		    } else {
  		      //Close the open events on differnt week row
  		      //currentOpened && currentOpened.parentNode.removeChild(currentOpened);
  		      if(currentOpened) {
  		        currentOpened.addEventListener('webkitAnimationEnd', function() {
  		          currentOpened.parentNode.removeChild(currentOpened);
  		        });
  		        currentOpened.addEventListener('oanimationend', function() {
  		          currentOpened.parentNode.removeChild(currentOpened);
  		        });
  		        currentOpened.addEventListener('msAnimationEnd', function() {
  		          currentOpened.parentNode.removeChild(currentOpened);
  		        });
  		        currentOpened.addEventListener('animationend', function() {
  		          currentOpened.parentNode.removeChild(currentOpened);
  		        });
  		        currentOpened.className = 'details out';
  		      }

  		      //Create the Details Container
  		      details = createElement('div', 'details in');

  		      //Create the arrow
  		      var arrow = createElement('div', 'arrow');

  		      //Create the event wrapper

  		      details.appendChild(arrow);
  		      el.parentNode.appendChild(details);
  		    }

  		    var todaysEvents = this.events.reduce(function(memo, ev) {
  		      if(ev.date.isSame(day, 'day')) {
  		        memo.push(ev);
  		      }
  		      return memo;
  		    }, []);

  		    this.renderEvents(todaysEvents, details);

  		    arrow.style.left = el.offsetLeft - el.parentNode.offsetLeft + 27 + 'px';
  		  }

  		  Calendar.prototype.renderEvents = function(events, ele) {
  		    //Remove any events in the current details element
  		    var currentWrapper = ele.querySelector('.events');
  		    var wrapper = createElement('div', 'events in' + (currentWrapper ? ' new' : ''));

  		    events.forEach(function(ev) {
  		      var div = createElement('div', 'event');
  		      var square = createElement('div', 'event-category ' + ev.color);
  		      var span = createElement('span', '', ev.eventName);

  		      div.appendChild(square);
  		      div.appendChild(span);
  		      wrapper.appendChild(div);
  		    });

  		    if(!events.length) {
  		      var div = createElement('div', 'event empty');
  		      var span = createElement('span', '', 'No Events');

  		      div.appendChild(span);
  		      wrapper.appendChild(div);
  		    }

  		    if(currentWrapper) {
  		      currentWrapper.className = 'events out';
  		      currentWrapper.addEventListener('webkitAnimationEnd', function() {
  		        currentWrapper.parentNode.removeChild(currentWrapper);
  		        ele.appendChild(wrapper);
  		      });
  		      currentWrapper.addEventListener('oanimationend', function() {
  		        currentWrapper.parentNode.removeChild(currentWrapper);
  		        ele.appendChild(wrapper);
  		      });
  		      currentWrapper.addEventListener('msAnimationEnd', function() {
  		        currentWrapper.parentNode.removeChild(currentWrapper);
  		        ele.appendChild(wrapper);
  		      });
  		      currentWrapper.addEventListener('animationend', function() {
  		        currentWrapper.parentNode.removeChild(currentWrapper);
  		        ele.appendChild(wrapper);
  		      });
  		    } else {
  		      ele.appendChild(wrapper);
  		    }
  		  }


  		  Calendar.prototype.nextMonth = function() {
  		    this.current.add('months', 1);
  		    this.next = true;
  		    this.draw();
  		  }

  		  Calendar.prototype.prevMonth = function() {
  		    this.current.subtract('months', 1);
  		    this.next = false;
  		    this.draw();
  		  }

  		  window.Calendar = Calendar;

  		  function createElement(tagName, className, innerText) {
  		    var ele = document.createElement(tagName);
  		    if(className) {
  		      ele.className = className;
  		    }
  		    if(innerText) {
  		      ele.innderText = ele.textContent = innerText;
  		    }
  		    return ele;
  		  }
  		}();
  		
  		!function() {

  		  var data = [

  		    
  		    
  		    
  		    // { eventName: '蝴蝶你好棒', calendar: 'Kids', color: 'yellow', date: moment('2017/6/5') },
  		    // { eventName: '蝴蝶你好棒', calendar: 'Other', color: 'green', date: moment('2017/6/5') },
  		    // { eventName: '蝴蝶你好棒', calendar: '11111', color: 'blue', date: moment('2017/9/5') },
  		    // { eventName: '蝴蝶你好棒', calendar: '11111', color: 'blue', date: moment('2017/10/5') },
  		    // { eventName: '蝴蝶你好棒', calendar: '11111', color: 'blue', date: moment('2018/1/5') }
  		    // { eventName: 'Interview - Jr. Web Developer', calendar: 'Work', color: 'orange' },
  		    // { eventName: 'Demo New App to the Board', calendar: 'Work', color: 'orange' },
  		    // { eventName: 'Dinner w/ Marketing', calendar: 'Work', color: 'orange' },

  		    // { eventName: 'Game vs Portalnd', calendar: 'Sports', color: 'blue' },
  		    // { eventName: 'Game vs Houston', calendar: 'Sports', color: 'blue' },
  		    // { eventName: 'Game vs Denver', calendar: 'Sports', color: 'blue' },
  		    // { eventName: 'Game vs San Degio', calendar: 'Sports', color: 'blue' },

  		    // { eventName: 'School Play', calendar: 'Kids', color: 'yellow' },
  		    // { eventName: 'Parent/Teacher Conference', calendar: 'Kids', color: 'yellow' },
  		    // { eventName: 'Pick up from Soccer Practice', calendar: 'Kids', color: 'yellow' },
  		    // { eventName: 'Ice Cream Night', calendar: 'Kids', color: 'yellow' },

  		    // { eventName: 'Free Tamale Night', calendar: 'Other', color: 'green' },
  		    // { eventName: 'Bowling Team', calendar: 'Other', color: 'green' },
  		    // { eventName: 'Teach Kids to Code', calendar: 'Other', color: 'green' },
  		    // { eventName: 'Startup Weekend', calendar: 'Other', color: 'green' }
  		  ];

  		  

  		  function addDate(ev) {
  		    
  		  }

  		  var calendar = new Calendar('#calendar', c8);

  		}();
  		
	  		
  		}
  		</script>
	</body>
</html>

