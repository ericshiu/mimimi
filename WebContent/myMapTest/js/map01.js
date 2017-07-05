var a=0;

function $id(id){
	  return document.getElementById(id);
}


function doFirst(){
	
//	a++;
//	alert(a);
//	if (a!=1){
		var home=$id("myhome").value;
		alert(home);
//	}
	
	var geocoder = new google.maps.Geocoder();
	var addr=home;

		
	geocoder.geocode({
        'address': addr
    }, function (results, status) {
    	console.log(results);
        if (status == google.maps.GeocoderStatus.OK) {
        	console.log(results);
            myPosition = new google.maps.LatLng(results[0].geometry.location.lat(),results[0].geometry.location.lng());
            console.log(myPosition);
        	var map = new google.maps.Map(document.getElementById('myMap'),{
        		zoom:14,
        		center:myPosition,
        		mapTypeId:google.maps.MapTypeId.ROADMAP
        	});  
        	
        	
            var marker = new google.maps.Marker({
                position: myPosition,
                map: map,
                title: "這是我家",
              });   
        
        }	   
        
    });	
	
	
//	AIzaSyA6uC_QFIO4lPVQ2TKgLh6i4WIcBqw7MCs
}
window.addEventListener('load',doFirst,false);

