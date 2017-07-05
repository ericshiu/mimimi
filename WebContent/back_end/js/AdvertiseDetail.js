function $id(id) {
	return document.getElementById(id);
}

function doFirst(){
	
	var review=$id("adv_review").value;
	if (review!="0"){
		$id("btnpass1").disabled=true;
		$id("btnpass2").disabled=true;
	}
}

window.addEventListener('load', doFirst, false);