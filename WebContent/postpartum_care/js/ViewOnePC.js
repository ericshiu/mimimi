function doFirst(){
	var myPosition;
//  	myPosition = new google.maps.LatLng(24.9708264,121.18820769999999);
	var geocoder = new google.maps.Geocoder();
	var temp = $("#pc_address").attr("value");
	var addr = temp;
	geocoder.geocode({
        'address': addr
    }, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            myPosition = new google.maps.LatLng(results[0].geometry.location.lat(),results[0].geometry.location.lng());
    
        	var map = new google.maps.Map(document.getElementById('myMap'),{
        		zoom:14,
        		center:myPosition,
        		mapTypeId:google.maps.MapTypeId.ROADMAP
        	});  
        	
        	
            var marker = new google.maps.Marker({
                position: myPosition,
                map: map,
                title: $("#pc_name").attr("value")
              });   
        
        }	   
        
    });	
	
	
    
    if ($id("errorMsgs")) {   
    	
		var str=$id("errorMsgs").innerHTML;
		
		$id("errorMsg").innerHTML=str;
		$id("errorBtn").click();
	
	}	
    
    
    if ($id("successMsgs")) {   
		var str=$id("successMsgs").innerHTML;
		
		$id("successMsg").innerHTML=str;
		$id("successBtn").click();
	
	}		
	
}

function $id(id){
	  return document.getElementById(id);
	}

//$(".carousel-inner > .item").css("height",$(".carousel-inner > .item > img").height());
//
//$( window ).resize(function() {
//	  $(".carousel-inner > .item").css("height",$(".carousel-inner > .item > img").height());
//	});

window.addEventListener('load',doFirst,false);


