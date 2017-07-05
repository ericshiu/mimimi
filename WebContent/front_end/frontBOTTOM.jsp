<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*"%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title></title>

	</head>
	<body>
		
		<div style="height:200px;" ></div>
		<!-- 底部選單 -->
		<div class="footer-container">
		    <div class="footer">
		        <div class="container">
		            <div class="row">
		                <p id="back-top"><a href="#top"><span></span></a> </p>
		                <div class="footer-cols-wrapper">
		                    <div class="footer-col col-xs-12 col-sm-6 col-md-3">
		                        <h4>MOM 寶典</h4>
		                        <div class="footer-col-content">
		                            <ul>
		                                <li><a href="<%=request.getContextPath()%>/member/Calendar.jsp">專屬日曆</a></li>
		                                <li><a href="<%=request.getContextPath()%>/member/mh_select.jsp">媽媽健康資料</a></li>
		                                <li><a href="<%=request.getContextPath()%>/member/ins_select.jsp">產檢紀錄</a></li>
		                                <li><a href="<%=request.getContextPath()%>/optional_test/listHos_ByCompositeQueryHome.jsp">查詢醫院自費項目</a></li>
		                                <li><a href="<%=request.getContextPath()%>/member/em_select.jsp">胎教音樂</a></li>
		                                
		                             </ul>
		                        </div>
		                    </div>
		                </div>
		                    <div class="footer-col col-xs-12 col-sm-6 col-md-3">
		                        <h4>BABY 專區</h4>
		                        <div class="footer-col-content">
		                            <ul>
		                                <li><a href="<%=request.getContextPath()%>/member//bd_select.jsp">寶寶中心</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/member/fm_select_add.jsp">胎動記錄</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/member/vn_select.jsp">施打疫苗記錄</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/vc_select.jsp">疫苗時程表</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/member/be_select.jsp">新生兒進食記錄</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/member/bex_select.jsp">新生兒排泄記錄</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/member/bs_select.jsp">新生兒睡眠記錄</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/member/baby_compute.jsp">寶寶生長百分比計算機</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/member/bg_select.jsp">寶寶生長曲線圖</a>
		                                </li>
		                             
		                            </ul>
		                        </div>
		                    </div>
		                    <div class="footer-col col-xs-12 col-sm-6 col-md-3">
		                        <h4>產後護理專區</h4>
		                        <div class="footer-col-content">
		                            <ul>
		                                <li><a href="<%=request.getContextPath()%>/front_end/RegisterFir.jsp">廠商註冊</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/postpartum_care/PostpartumCare_Index.jsp">醫院查詢</a>
		                                </li>
		                                
		                            </ul>
		                        </div>
		                        <br>
		                         <h4>討論區</h4>
		                        <div class="footer-col-content">
		                            <ul>
		                                <li><a href="<%=request.getContextPath()%>/forum/ForumINDEX.jsp">最新文章</a>
		                                </li>
		                                <li><a href="<%=request.getContextPath()%>/forum/ForumINDEX.jsp">熱門文章</a>
		                                </li>
		                                
		                            </ul>
		                        </div>
		                        
		                        
		                        
		                    </div>
		                    
		                    <div class="footer-col col-xs-12 col-sm-6 col-md-3 last">

		                        <div class="block block-facebook-page">
		                            <div class="block-title">
		                                <strong><span>Facebook Page</span></strong>
		                            </div>
		                            <div class="block-content">
		                                <div class="fb-page" data-href="https://www.facebook.com/TemplateMonster" data-hide-cover="false" data-show-facepile="true" data-show-posts="false">
		                                    <div class="fb-xfbml-parse-ignore">
		                                        <blockquote cite="https://www.facebook.com/facebook"><a href="https://www.facebook.com/facebook">Facebook</a>
		                                        </blockquote>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>

		                    </div>
		                    <div class="clear"></div>
		                </div>
		                <div class="clear"></div>
		            </div>
		        </div>
		    </div>
		    <div class="footer-bottom-part">
		        <div class="footer-left-block col-xs-12 col-sm-6 col-md-4">
		            <div class="header-phone">
		                <div class="phone-number">
		                    <span class="fl-justicons-telephone95"></span>
		                    <span>TEL:0800-000-123</span>
							
						</div>
		                <div class="schedule">
		                    <p><i>7 Days a week from 9:00 am to 7:00 pm</i>
		                    </p>
		                </div>
		            </div>
		        </div>
		        <div class="footer-center-block col-xs-12 col-sm-6 col-md-4">
		            <div class="footer-address-store">
		                <p>28 Jackson Blvd Ste 1020 Chicago IL 60604-2340</p>
		            </div>
		        </div>
		        <div class="footer-right-block col-xs-12 col-sm-6 col-md-4">
		            <div class="soc-icon">
		                <a href="https://www.facebook.com/facebook" class="fa fa-facebook-f"></a>
		                <a href="https://twitter.com/?lang=zh-tw" class="fa fa-twitter"></a>
		                <a href="https://plus.google.com/?hl=zh_tw" class="fa fa-google-plus"></a>
		                <a href="http://youtube.com.tw" class="fa fa-youtube"></a>
		            </div>
		        </div>
		    </div>
		
		<!-- 底部結束	 -->
		
	</body>
</html>