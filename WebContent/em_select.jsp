<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.education_music.model.*"%>
<%@ page import="java.util.*"%>




<!DOCTYPE html>
<html lang="">
	<head>
	<style type="text/css">
		

			html, body {
			  height: 100%;
			}

			@keyframes rotate {
			  from {
			    transform: rotate(0deg);
			  }
			  to {
			    transform: rotate(359deg);
			  }
			}
			.rotateDisc {
			  animation-name: rotate;
			  animation-duration: 2.5s;
			  animation-iteration-count: infinite;
			  animation-timing-function: linear;
			}

			.show0 {
			  transform: rotateY(0deg) !important;
			}

			.show90 {
			  transform: rotateY(-90deg) !important;
			}

			body {
			  margin: 0px;
			  padding: 0px;
			  font-family: Ubuntu, sans;
			  font-size: 18px;
			  transition: all 0.3s;
			}
			body a {
			  text-decoration: none;
			  color: inherit;
			}
			body img {
			  max-height: 100%;
			  max-width: 100%;
			}
			body #backgroundGradient,
			body #backgroundGradientTransition {
			  position: absolute;
			  top: 0;
			  bottom: 0;
			  left: 0;
			  right: 0;
			  transition: all 0.5s;
			}
			body #backgroundGradient {
			  background: radial-gradient(#4b3a40,#e0d9c6);
			}
			body #player {
			  position: absolute;
			  top: 50%;
			  left: 50%;
			  width: 200px;
			  height: 200px;
			  margin: -165px 0 0 -112px;
			}
			body #player #vinyl {
			  position: absolute;
			  top: 8px;
			  left: 0px;
			  transition: all 0.8s;
			}
			body #player #vinyl #disc {
			  position: absolute;
			  top: 0;
			  left: 12px;
			  width: 200px;
			  height: 200px;
			  background: url("http://bloo.ovh/music-player/img/vinyl.png") no-repeat;
			}
			body #player #vinyl #timer {
			  position: absolute;
			  top: 38px;
			  left: 49px;
			  transform: rotate(90deg);
			}
			body #player #vinyl #total-timer {
			  cursor: pointer;
			}
			body #player #vinyl #timer-dash {
			  transition: stroke-dasharray 1s linear;
			}
			body #player #cover {
			  width: 100%;
			  height: 100%;
			  border: 8px solid #F1F1F1;
			  box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.2);
			  transform: rotateY(0deg);
			  background: white;
			  backface-visibility: hidden;
			  opacity: 1;
			  transition: transform 0.5s;
			}
			body #player #playlist {
			  position: absolute;
			  top: -184px;
			  left: -143px;
			  width: 500px;
			  height: 400px;
			  border: 12px solid #333333;
			  padding: 6px;
			  background: #F1F1F1;
			  transform: rotateY(-179.9deg);
			  box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
			  backface-visibility: hidden;
			  transition: all 0.5s;
			}
			body #player #playlist p {
			  margin: 0;
			  padding: 2px 4px 3px;
			  font-size: 30px;
			  text-shadow: 0 1px 1px white;
			  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
			  cursor: pointer;
			}
			body #player #playlist p:last-child {
			  border: none;
			}
			body #player #playlist span {
			  color: #3B4545;
			}
			body #player #playlist .active {
			  color: #557778;
			}
			body #player #infos {
			  margin: 15px 0 0 12px;
			  text-shadow: 0 0 2px rgba(0, 0, 0, 0.2);
			  position: relative;
			}
			body #player #infos #title {
			  color: #333333;
			  font-size: 42px;
			  font-weight: bold;
			  text-align: center;
			  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.3);
			  transition: all 0.3s;
			}
			body #player #infos #artist {
			  color: #3A4E55;
			  font-size: 20px;
			  text-align: center;
			  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.3);
			  transition: all 0.3s;
			}
			body #player #buttons {
			  position: relative;
			  margin-top: 30px;
			  width: 200px;
			}
			body #player #buttons #playPause {
			  position: absolute;
			  top: 0px;
			  left: 85px;
			  width: 65px;
			  height: 65px;
			  border-radius: 50%;
			  border-top: 2px solid white;
			  background: linear-gradient(to top, #E5E0DD, #FBF9F3);
			  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
			  cursor: pointer;
			  transition: all 0.2s;
			}
			body #player #buttons #playPause p {
			  display: inline-block;
			  margin: 5px 0 0 16px;
			  transform: scale(1, 1.5);
			  /* W3C */
			  -webkit-transform: scale(1, 1.5);
			  /* Safari and Chrome */
			  -moz-transform: scale(1, 1.5);
			  /* Firefox */
			  -ms-transform: scale(1, 1.5);
			  /* IE 9 */
			  -o-transform: scale(1, 1.5);
			  /* Opera */
			  color: rgba(255, 255, 255, 0.3);
			  font-size: 43px;
			  font-weight: bold;
			  text-shadow: 1px 4px 6px #E5E0DD, 0 0 0 rgba(0, 0, 0, 0.5), 1px 4px 6px #E5E0DD;
			}
			body #player #buttons #playPause:active {
			  margin-top: 1px;
			  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
			}
			body #player #buttons .previous {
			  position: absolute;
			  top: 13px;
			  left: 38px;
			  width: 40px;
			  height: 40px;
			  border-radius: 50%;
			  border-top: 2px solid white;
			  background: linear-gradient(to top, #E5E0DD, #FBF9F3);
			  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
			  cursor: pointer;
			  transition: all 0.2s;
			}
			body #player #buttons .previous p {
			  display: inline-block;
			  margin: -3px 0 0 10px;
			  color: rgba(255, 255, 255, 0.3);
			  font-size: 30px;
			  font-weight: bold;
			  text-shadow: 1px 4px 6px #E5E0DD, 0 0 0 rgba(0, 0, 0, 0.5), 1px 4px 6px #E5E0DD;
			}
			body #player #buttons .previous:active {
			  margin-top: 1px;
			  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
			}
			body #player #buttons .next {
			  position: absolute;
			  top: 13px;
			  right: 2px;
			  width: 40px;
			  height: 40px;
			  border-radius: 50%;
			  border-top: 2px solid white;
			  background: linear-gradient(to top, #E5E0DD, #FBF9F3);
			  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
			  cursor: pointer;
			  transition: all 0.2s;
			}
			body #player #buttons .next p {
			  display: inline-block;
			  margin: -3px 0 0 18px;
			  color: rgba(255, 255, 255, 0.3);
			  font-size: 30px;
			  font-weight: bold;
			  text-shadow: 1px 4px 6px #E5E0DD, 0 0 0 rgba(0, 0, 0, 0.5), 1px 4px 6px #E5E0DD;
			}
			body #player #buttons .next:active {
			  margin-top: 1px;
			  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
			}
			body #player #buttons #playlistLink {
			  position: absolute;
			  top: 10px;
			  left: -40px;
			  width: 40px;
			  height: 40px;
			  border-radius: 50%;
			  background: linear-gradient(to top, #E5E0DD, #FBF9F3);
			  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
			  cursor: pointer;
			  transition: all 0.2s;
			}
			body #player #buttons #playlistLink::before {
			  background-size:20px;
			  position: relative;
			  content: "";
			  display: inline-block;
			  width: 40px;
			  height: 40px;
			  background: url("http://bloo.ovh/music-player/img/playlist.png") no-repeat 10px 12px;
			  vertical-align: middle;
			  background-size:20px;
			}
			body #player #buttons #volume {
			  position: absolute;
			  top: 10px;
			  right: -70px;
			  width: 40px;
			  height: 40px;
			  border-radius: 50%;
			  background: linear-gradient(to top, #E5E0DD, #FBF9F3);
			  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
			  cursor: pointer;
			  transition: all 0.2s;
			  /* WebKit */
			  /* Firefox */
			}
			body #player #buttons #volume::before {
			  position: relative;
			  content: "";
			  display: inline-block;
			  width: 40px;
			  height: 40px;
			  background: url("http://bloo.ovh/music-player/img/volume.png") no-repeat 10px 12px;
			  vertical-align: middle;
			  background-size:20px;
			}
			body #player #buttons #volume input[type=checkbox] {
			  position: absolute;
			  left: -9999px;
			}
			body #player #buttons #volume input[type=range] {
			  -webkit-appearance: none;
			  width: 75px;
			  margin: -180px 0 0 -17px;
			  vertical-align: middle;
			  background: none;
			  transform: rotate(-90deg);
			  display: none;
			}
			body #player #buttons #volume input[type=checkbox]:checked ~ input[type=range] {
			  display: inline-block;
			}
			body #player #buttons #volume input[type=range]::-webkit-slider-runnable-track {
			  width: 100%;
			  height: 6px;
			  cursor: pointer;
			  background: linear-gradient(to bottom, #E6E6E6, #fff);
			  border-radius: 3px;
			  border: none;
			  box-shadow: 0 0 1px rgba(0, 0, 0, 0.6) inset;
			}
			body #player #buttons #volume input[type=range]::-moz-range-track {
			  width: 100%;
			  height: 6px;
			  cursor: pointer;
			  background: linear-gradient(to bottom, #E6E6E6, #fff);
			  border-radius: 3px;
			  border: none;
			  box-shadow: 0 0 1px rgba(0, 0, 0, 0.6) inset;
			}
			body #player #buttons #volume input[type=range]::-webkit-slider-thumb {
			  -webkit-appearance: none;
			  height: 6px;
			  width: 20px;
			  margin: 0px !important;
			  border-radius: 3px;
			  border: 1px solid rgba(0, 0, 0, 0.4);
			  background: #bcbcbc;
			  cursor: pointer;
			}
			body #player #buttons #volume input[type=range]::-moz-range-thumb {
			  height: 4px;
			  width: 20px;
			  border-radius: 3px;
			  border: 1px solid rgba(0, 0, 0, 0.4);
			  background: #bcbcbc;
			  cursor: pointer;
			  top: 0px;
			  left: 0px;
			}
			body #player #buttons #time {
			  position: absolute;
			  top: 16px;
			  right: -12px;
			  color: #3A4E55;
			  font-size: 11px;
			  text-align: right;
			  transition: all 0.5s;
			}
			body #player #buttons span {
			  color: #333;
			  font-weight: bold;
			  font-size: 13px;
			}


	</style>
	<title>胎教音樂
	</title>
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

  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">

		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/title.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/table.css">
		
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">

		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<jsp:include page="/front_end/frontTOP.jsp" flush="true" />	
		<div class="container">
			<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="widget center">
					  <div class="text center">
					    <h1 class="">胎教音樂</h1>
					    <p>Go ahead, resize me.</p>
					  </div>
					  <div class="blur">
					  </div>
					</div>
					</div>
				<div class="col-xs-12 col-sm-3">
				 <jsp:include page="listgrop.jsp" flush="true"/>
				</div>



				<!-- 9999 -->
				<div class="col-xs-12 col-sm-8" style="border: ;">
					<div style="width:;height: 780px;border: solid;" >
						<div id="backgroundGradientTransition"></div>
							<div id="backgroundGradient"></div>
								<div id="player">
									<div id="vinyl">
										<div id="disc"></div>
										<div id="timer">
											<?xml version="1.0" encoding="UTF-8" standalone="no"?>
											<svg id="svg4203" height="32.185mm" width="33.339mm" version="1.1"viewBox="0 0 33.338757 32.184547">
											 <g id="layer1" transform="translate(.031909 .39310)">
											  <path id="total-timer" style="color-rendering:auto;text-decoration-color:#000000;color:#000000;font-variant-numeric:normal;shape-rendering:auto;solid-color:#000000;stroke:#232323;text-decoration-line:none;stroke-width:.79005;fill:#232323;font-variant-position:normal;mix-blend-mode:normal;block-progression:tb;font-feature-settings:normal;shape-padding:0;font-variant-alternates:normal;text-indent:0;font-variant-caps:normal;image-rendering:auto;white-space:normal;text-decoration-style:solid;font-variant-ligatures:none;isolation:auto;stroke-linecap:round;text-transform:none" d="m16.638 0.0019233c-2.815 0-5.6302 0.72765-8.1517 2.1835-4.775 2.7568-7.8131 7.7296-8.1228 13.194 0 0.93263 1.3916 0.96025 1.4276 0 0.31027-4.977 3.0165-9.5095 7.3708-12.023 4.6271-2.6714 10.325-2.6714 14.952 0 4.3543 2.5139 7.0601 7.0465 7.3704 12.023 0 0.94918 1.4276 0.96035 1.4276 0-0.311-5.465-3.349-10.438-8.124-13.195-2.521-1.4554-5.336-2.1831-8.151-2.1831z"/>
											  <path id="timer-dash"
											   transform="scale(-1)"
											   style="font-variant-ligatures:none;font-variant-caps:normal;font-variant-numeric:normal;opacity:1;fill:none;fill-opacity:0.57034218;stroke:#cdcdcd;stroke-width:1.34621394;stroke-linecap:round;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:0 49;stroke-dashoffset:0;stroke-opacity:1; pointer-events: none;"
											   d="m -1.0605774,-16.198862 c 0,5.565088 -2.9689391,10.7074411 -7.7884466,13.489985 -4.819508,2.78254381 -10.757386,2.78254366 -15.576893,-4e-7 -4.819507,-2.7825442 -7.788446,-7.9248976 -7.788446,-13.4899856" />
											 </g>
											</svg>
										</div>
									</div>

									<div id="cover">
										<img id="album" />
									</div>

									<div id="playlist">
									</div>

									<div id="infos">
										<div id="title" style="width: 20em;margin-left: -326px;"></div>
										<div id="artist"></div>
									</div>

									<div id="buttons">
										<div id="playlistLink"></div>
										<div src="arrow.png" class="previous"><p>&lsaquo;</p></div>
										<div src="arrow.png" class="next"><p>&rsaquo;</p></div>
										<div id="playPause"><p >&rtrif;</p></div>
										<label for="volumeCheckbox" id="volume">
											<input id="volumeCheckbox" type="checkbox">
											<input id="volumeSlider" type="range"></span>
										</div>
									</div>
								</div>
							</div>
							<%int i=0; %>
				<jsp:useBean id="EMSvc" scope="page" class="com.education_music.model.EducationMusicService" />
				 <c:forEach var="educationMusicVO" items="${EMSvc.all}">
				
							<div>
								<audio preload="auto"  id="audio<%=i %>"  src="<%= request.getContextPath()%>/EducationMusicMP3?em_no=${educationMusicVO.em_no}"></audio>
					
							</div>
						<%i++; %>	
				</c:forEach>
					</div>

						
				</div>
				<!-- 9999 -->
				


			</div>
		</div>
		

		<%int a=0; %>
		 <c:forEach var="educationMusicVO" items="${EMSvc.all}">
				
			<div>
				<audio preload="auto" autobuffer id="audio<%=a %>" type="audio/mpeg" src="<%= request.getContextPath()%>/EducationMusicMP3?em_no=${educationMusicVO.em_no}"></audio>
							
			</div>
				<%a++; %>					
		</c:forEach>

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
  		
  		
<script type="text/javascript">
  			
"use strict";
$(document).ready(function() {
	var songs = [
		{
			"title": "帕赫貝爾鋼琴",
			"artist": "1",
			"cover": "img/mu1_resized.jpg",
			"num": "1",
			"darkColor": "#Fff",
			"lightColor": "#3A4E55"
			
		},
		{
			"title": "幸福的道路",
			"artist": "2",
			"cover": "img/mu2_resized.jpg",
			"num": "2",
			"darkColor": "#fff",
			"lightColor": "#4B3A40"
		},
		{
			"title": "勃拉姆斯【搖籃曲】",
			"artist": "3",
			"cover": "img/mu3_resized.jpg",
			"num": "3",
			"darkColor": "#FFF",
			"lightColor": "#328be2"
		}
		,
		{
			"title": "甜蜜入夢",
			"artist": "4",
			"cover": "img/mu4_resized.jpg",
			"num": "4",
			"darkColor": "#FFF",
			"lightColor": "#a777e2"
		}
		,
		{
			"title": "潑水歌",
			"artist": "5",
			"cover": "img/mu5_resized.jpg",
			"num": "5",
			"darkColor": "#FFF",
			"lightColor": "#ff8bd0"
		}

	];

	var nbSongs = songs.length;
	var currentSong = 0;
	var dashLength = 49;
	var audio = null;
	var timer = null;

	function init() {
		// Chargement de la playlist
		for (var i=0; i<songs.length; i++) {
			$("<p>").html(songs[i].title + " &#183; " + songs[i].artist).appendTo($("#playlist"));
		}
		$("#playlist p:first-child").addClass("active");

		// Chargement de la première musique
		loadSong(1);
		pause();
		$("#volumeSlider").change();

		// Playlist
		$("#playlist p").click(function() {
			loadSong($(this).index());
			hidePlaylist();
		});

		// Rotation du disque
		$("#disc").addClass("rotateDisc");

		$("#playPause").on("click", function() {
			if (audio.paused) on();
			else pause();
		});

		$(".previous").on("click", function() {
			nextSong(-1);
		});

		$(".next").on("click", function() {
			nextSong(1);
		});

		$("#total-timer").click(function(e) {
			// Coordonnées du centre du cercle
			var posElement = $("#total-timer").offset();
			var left = parseInt(posElement.left+12);
			var top = parseInt(posElement.top) + 136/2;
			console.log(posElement);

			var dx = e.pageX - left;
			var dy = e.pageY - top;
			console.log("dx: "+ dx + " - dy: " + dy);

			// On récupère l'angle
			var angleRad = Math.atan2(dx, dy);
			var angle = angleRad * 180 / Math.PI;

			// On va au bon endroit dans la musique
			audio.currentTime = (180 - angle)/180 * audio.duration;
			console.log((180 - angle)/180 * audio.duration);
		});

		$("#playlistLink").click(function() {
			if ($("#cover").hasClass("show90"))
				hidePlaylist();
			else showPlaylist();
		});

		// Son
		$("#volumeSlider").on("change mousemove", function() {
			audio.volume = ($(this).get(0).value / 100).toFixed(2);
		});
	}

	function loadSong(num) {
		currentSong = num;
		if (audio !== null) audio.pause();
		audio = $("#audio"+num).get(0);
		on(0);
		audio.volume = ($("#volumeSlider").get(0).value / 100).toFixed(2);

		$("#album").attr("src", songs[currentSong].cover);
		setTimeout( function(){
			// Infos
			$("#title").html(songs[currentSong].title);
			$("#artist").html(songs[currentSong].artist);
				$("#playlist p").removeClass("active");
				$("#playlist p span").remove();
				$("#playlist p:eq("+currentSong+")").addClass("active");

			// Style
				$("#playlist p").css("color", "#231f16");
				$("#playlist p.active").css("color", songs[currentSong].darkColor);
				$("#playlist p.active").html("<span>&rtrif;</span> " + $("#playlist p.active").html());
			$("#artist").css("color", songs[currentSong].darkColor);
			changeBackground();
		}, 300);

	}

	function changeBackground() {
		var bg = $("#backgroundGradient");
		$("#backgroundGradientTransition").css("background", "radial-gradient("+songs[currentSong].lightColor+","+songs[currentSong].darkColor+")");
			bg.css("opacity", "0");


		setTimeout( function(){
				bg.css("background", "radial-gradient("+songs[currentSong].lightColor+","+songs[currentSong].darkColor+")");
				bg.css("opacity", "1");
		}, 500);
	}

	function showPlaylist() {
		// On rentre le CD
		animateVinyl(0);

		// On tourne la pochette
		setTimeout( function(){
			$("#disc").css("opacity", "0");
			$("#timer").css("opacity", "0");
	    },600);

		setTimeout( function(){
			$("#cover").addClass("show90");
		}, 600);

		// On affiche la playlist
		setTimeout( function(){
			$("#playlist").addClass("show0");
	    },900);
	}

	function hidePlaylist() {
		// On cache la playlist
		$("#playlist").removeClass("show0");

	    // On tourne la pochette
		setTimeout( function(){
			$("#cover").removeClass("show90");
		}, 200);

		setTimeout( function(){
			$("#disc").css("opacity", "1");
			$("#timer").css("opacity", "1");
		}, 700);

	    // On sort le CD
		if (!audio.paused) {
			setTimeout( function(){
				animateVinyl("50%");
			},800);
		}
	}

	function animateVinyl(direction) {
		$("#vinyl").css({
			"left": direction,
			"transition": "all 0.8s"
		});
	}

	function on(start) {
		animateVinyl("50%");
		if (start !== undefined && audio.currentTime !== start)
			audio.currentTime = start;
		audio.play();
		$("#playPause p").html("&#61;").css({
			"transform": "rotate(90deg) scale(1,1.5)",
			"margin": "0px 0 0 23px"
		});

		// MAJ du temps
		if (timer) clearInterval(timer);
		timer = setInterval(updateTime, 1000);

		audio.removeEventListener("ended", off);
		audio.addEventListener("ended", off);
	}

	function pause() {
		animateVinyl(0);
		audio.pause();
		$("#playPause p").html("&rtrif;").css({
			"transform": "rotate(0deg) scale(1,1.5)",
			"margin": "0px 0 0 16px"
		});

		if (timer) clearInterval(timer);
		timer = null;
	}

	function off() {
		pause();
		nextSong(1);
		$("#timer-dash").css("stroke-dasharray", 0 + " " + (dashLength));
		$("#disc").css("transform", "rotate(0deg)");
	}

	function nextSong(direction) {
		currentSong = (currentSong + direction + nbSongs) % nbSongs;
		loadSong(currentSong);
	}

	function updateTime() {
		var ratio = 49 * audio.currentTime / audio.duration;
		$("#timer-dash").css("stroke-dasharray", ratio + " " + (dashLength - ratio));
	}

	init();
});

  		</script>
	</body>
</html>

