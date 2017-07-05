function $id(id){
	return document.getElementById(id);
}



function Btn(){
	if ($id("checktitle").innerHTML=="OK" && $id("checkpicture").innerHTML=="OK"){
	$id("Btn").disabled=false;
	$id("Btn").value="送出";
	}else{
	$id("Btn").disabled=true;
	$id("Btn").value="請填寫完成";
	}
}


function init() {

	
	$id("adv_picture").onchange=function(e){
		//預覽圖程式
		
		if (this.value==null || this.value==""){
			$id("checkpicture").innerHTML="請廣告標題";
			$id("checkpicture").setAttribute("class","fa fa-times-circle");
			Btn();
		}else{
			$id("checkpicture").innerHTML="OK";
			$id("checkpicture").setAttribute("class","fa fa-check-circle");
			Btn();
		}
		
		
		var file = e.target.files[0];
		var i1 = document.getElementById("advimg");

		if (file) {
			var reader = new FileReader();
			reader.onload = function(q) {
				i1.src = q.target.result;
			}
		}
		reader.readAsDataURL(file);
	};
	
	$id("adv_title").onchange=function(){
	
		if (this.value==null || this.value==""){
			$id("checktitle").innerHTML="請廣告標題";
			$id("checktitle").setAttribute("class","fa fa-times-circle");
			Btn();
		}else{
			$id("checktitle").innerHTML="OK";
			$id("checktitle").setAttribute("class","fa fa-check-circle");
			Btn();
		}
	};

//	成功訊息視窗跳出
	if ($id("Smessage")!=null){
		str=$id("Smessage").innerHTML
		$id("SMsg").innerHTML=str;
		$id("SBtn").click();
	
	}
	
//	錯誤訊息視窗跳出
	if($id("errormessage")!=null){
		str2=$id("errormessage").innerHTML
		$id("errorMsg").innerHTML=str2;
		$id("errorBtn").click();
	}
	

}

window.addEventListener('load',init,false);