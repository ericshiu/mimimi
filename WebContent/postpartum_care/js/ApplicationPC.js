window.onload = function (){
 

      
    if ($id("errorMsgs")) {    
		var str=$id("errorMsgs").innerHTML;
		
		$id("errorMsg").innerHTML=str;
		$id("errorBtn").click();
	
	}
 
     
}

function $id(id){
	  return document.getElementById(id);
	}


function formatDate(){

	    $('#datetimepicker1').datetimepicker({
	    	format: "YYYY-MM-DD HH:mm",
	        icons: {
	            time: "fa fa-clock-o",
	            date: "fa fa-calendar",
	            up: "fa fa-arrow-up",
	            down: "fa fa-arrow-down"
	          },
	    	minDate: moment().add(3, 'days'),
	    	maxDate: moment().add(60, 'days')
	    });


}


function babyHelper() {
	$(pca_memo).val("攜帶朋友一同前往(2位都是孕婦)");
}


//$(function () {
//    $('#datetimepicker1').datetimepicker({
//    	format: "YYYY-MM-DD HH:mm",
//        icons: {
//            time: "fa fa-clock-o",
//            date: "fa fa-calendar",
//            up: "fa fa-arrow-up",
//            down: "fa fa-arrow-down"
//          },
//    	minDate: moment().add(3, 'days'),
//    	maxDate: moment().add(60, 'days')
//    });
//
//});


