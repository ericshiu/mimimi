
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="">
	<head>
	<title>歡迎使用媽咪寶典</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- 引用icon -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- 引用Css -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bookcss.css">
		
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">

		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
    <style type="text/css">


        .container .cell .buttons a div:hover { 
            margin-top:-10px;
            margin-left:-5px;
            box-shadow: 5px 5px 2px #888888;
            text-decoration:none;
        }



    </style>



<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
<div class="container">
    <div class="cell" style="text-align: center;">
        <div class="titlea_titlea">歡迎使用媽咪寶典</div>
        <div class="subtitle">替你量身打造，可以輕鬆獲得專使資訊、追蹤您的身體狀況、管理目前所有資料，就是一次搞定啦！</div>
        <div class="subtitle"></div>
        <div class="notice-boards"></div>


        <div class="buttons container" style="display: -webkit-box;     margin-left: -4px;">
            
			<div class="col-xs-12 col-sm-2">
			</div>
			<div class="col-xs-12 col-sm-1">
			</div>
			
				<div id="ember1081" class="home-big-button ember-view col-xs-12 col-sm-2">
           <a href="<%=request.getContextPath() %>/member/Calendar.jsp" style="text-decoration:none;">                    
                 <div style="background-image:url('img/bg-co1.png'); background-size: cover;" class="home-big-button__icon">
                 <div class="home-big-button__badge  ">
                     <div id="ember1083" class="liquid-container ember-view">
                         <div id="ember1086" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                     </div>
                 </div>
                 <svg class="home-big-button__svg" viewBox="0 0 32 32">
                     <i class="fa fa-calendar"></i>
                 </svg>
             </div>
             <h3>專屬日曆</h3>
             </a>
			
			</div>
			
			<div id="ember1081" class="home-big-button ember-view col-xs-12 col-sm-2">
           <a href="<%=request.getContextPath() %>/member/bd_select.jsp" style="text-decoration:none;">                    
                 <div style="background-image:url('img/bg-co.png'); background-size: cover;" class="home-big-button__icon">
                 <div class="home-big-button__badge  ">
                     <div id="ember1083" class="liquid-container ember-view">
                         <div id="ember1086" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                     </div>
                 </div>
                 <svg class="home-big-button__svg" viewBox="0 0 32 32">
                     <i class="fa fa-home"></i>
                 </svg>
             </div>
             <h3>寶寶中心</h3>
             </a>
             </div>
             <div id="ember1081" class="home-big-button ember-view col-xs-12 col-sm-2">
          	 <a href="<%=request.getContextPath() %>/member/mh_select.jsp" style="text-decoration:none;">                    
                 <div style="background-image:url('img/bg-income1.png'); background-size: cover;" class="home-big-button__icon">
                 <div class="home-big-button__badge  ">
                     <div id="ember1083" class="liquid-container ember-view">
                         <div id="ember1086" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                     </div>
                 </div>
                 <svg class="home-big-button__svg" viewBox="0 0 32 32">
                     <i class="fa fa-file-text"></i>
                 </svg>
             </div>
             <h3>媽媽健康資料</h3>
             </a>
             </div>
            

        </div>

        <div class="notice-boards">
        <div class="titlea_titlea"></div>
        <div class="subtitle"></div>
        </div>



        <div class="buttons container" style="display: -webkit-box;    margin-left: 90px;      margin-top:-10PX;}">

            <div id="ember1081" class="home-big-button ember-view col-xs-12 col-sm-2">
                    <a href="<%=request.getContextPath() %>/member/ins_select.jsp" style="text-decoration:none;">
                              <div style="background-image:url('img/bg-redpink.png'); background-size: cover;" class="home-big-button__icon">
                                        <div class="home-big-button__badge  ">
                                            <div id="ember1083" class="liquid-container ember-view">
                                                <div id="ember1086" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                                            </div>
                                        </div>
                                        <svg class="home-big-button__svg" viewBox="0 0 32 32">
                                            <i class="fa fa-pencil"></i>
                                        </svg>
                                    </div>
                                    <h3>產檢紀錄</h3>
                    </a>
            </div>

            <div id="ember1088" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/optional_test/listHos_ByCompositeQueryHome.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-blue.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge  ">
                        <div id="ember1090" class="liquid-container ember-view">
                            <div id="ember1093" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                        </div>
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                        	<i class="fa fa-hospital-o"></i>
                    </svg>
                </div>
                <h3>查詢資院檢查項目</h3>
            </a>
            </div>



            <div id="ember1095" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/member/fm_select_add.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-settings.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge  ">
                        <div id="ember1097" class="liquid-container ember-view">
                            <div id="ember1100" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                        </div>
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                       <i class="glyphicon glyphicon-heart"></i>
                    </svg>
                </div>
                <h3>胎動紀錄</h3>
                </a>
            </div>


            <div id="ember1102" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/member/vn_select.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-blue.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge home-big-button__badge--active home-big-button__badge--reddot">
                        <!---->
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                      <i class="fa fa-pencil"></i>
                    </svg>
                </div>
                <h3>施打疫苗紀錄</h3>
                </a>
            </div>



            <div id="ember1104" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/vc_select.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-pb.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge  ">
                        <div id="ember1106" class="liquid-container ember-view">
                            <div id="ember1109" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                        </div>
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                        <i class="fa fa-calendar"></i>
                    </svg>
                </div>
                <h3>疫苗時程表</h3>
                </a>
            </div>


        </div>
    </div>
</div>






<div class="container">
    <div class="cell" style="text-align: center;">
        <div class="titlea_titlea"></div>
        <div class="subtitle"></div>
        <div class="notice-boards"></div>
        <div class="buttons container" style="display: -webkit-box">
            <div id="ember1081" class="home-big-button ember-view col-xs-12 col-sm-2">
               <a href="<%=request.getContextPath() %>/member/be_select.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-product.png'); background-size: cover;" class="home-big-button__icon">
                	    <div class="home-big-button__badge  ">
                	        <div id="ember1083" class="liquid-container ember-view">
                	            <div id="ember1086" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                	        </div>
                	    </div>
                	    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                	        <i class="fa fa-edit"></i>
                	    </svg>
                	</div>
                	<h3>新生兒進食紀錄</h3>
                </a>
            </div>



            <div id="ember1088" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/member/bex_select.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-category.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge  ">
                        <div id="ember1090" class="liquid-container ember-view">
                            <div id="ember1093" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                        </div>
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                        	<i class="fa fa-hospital-o"></i>
                    </svg>
                </div>
                <h3>新生兒排泄紀錄</h3>
                </a>
            </div>



            <div id="ember1095" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/member/bs_select.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-sales.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge  ">
                        <div id="ember1097" class="liquid-container ember-view">
                            <div id="ember1100" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                        </div>
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                       <i class="glyphicon glyphicon-heart"></i>
                    </svg>
                </div>
                <h3>新生兒睡眠紀錄</h3>
                </a>
            </div>


            <div id="ember1102" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/member/baby_compute.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-adsportal.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge home-big-button__badge--active home-big-button__badge--reddot">
                        <!---->
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                        <i class="fa fa-calendar-check-o"></i>
                    </svg>
                </div>
                <h3>寶寶生長百分比計算機</h3>
                </a>
            </div>



            <div id="ember1104" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/member/bg_select.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-income.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge  ">
                        <div id="ember1106" class="liquid-container ember-view">
                            <div id="ember1109" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                        </div>
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                        <i class="fa fa-music"></i>
                    </svg>
                </div>
                <h3>寶寶生長曲線紀錄</h3>
                </a>
            </div>



            <div id="ember1111" class="home-big-button ember-view col-xs-12 col-sm-2">
            <a href="<%=request.getContextPath() %>/em_select.jsp" style="text-decoration:none;">
                <div style="background-image:url('img/bg-wallet.png'); background-size: cover;" class="home-big-button__icon">
                    <div class="home-big-button__badge  ">
                        <div id="ember1113" class="liquid-container ember-view">
                            <div id="ember1116" class="liquid-child ember-view" style="top: 0px; left: 0px;"></div>
                        </div>
                    </div>
                    <svg class="home-big-button__svg" viewBox="0 0 32 32">
                           <i class="fa fa-music"></i> 
                    </svg>
                </div>
                <h3>胎教音樂</h3>
                </a>
            </div>
            
        </div>
    </div>
</div>

		<div style="height:200px;" ></div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/js/js.js'></script>
	</body>
</html>

