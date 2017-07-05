window.onload = function (){
    document.getElementById("pc_password").onchange = pwCheck; 
    document.getElementById("pc_passwordcheck").onchange = pwReCheck; 
}

function pwCheck(){
	var pw = document.getElementById("pc_password").value;
	if (pw.length<6) {
		alert("00");
		var checkdiv = document.getElementById("pc_password_div");
		var i1 = document.createElement('i');
		i1.setAttribute("class","fa fa-trash-o");
		i1.innerHTML=" &nbsp移除";		
		}
}

function $id(id){
  return document.getElementById(id);
}

function getInfo(){	
  var xhr = new XMLHttpRequest();
  //設定好回呼函數 
  xhr.onreadystatechange = function (){
    if( xhr.readyState == 4 ){
      if(xhr.status == 200){
//    	  alert("一切正常");
    	  var text=xhr.responseText;
    	  var idvalue=$id("mem_id").value;
    	  
    	  if (text.indexOf(idvalue)!=-1){
    		  $id("mem_idCheck").innerHTML="重複，請更換帳號!!";
    	  }else{
    		  $id("mem_idCheck").innerHTML="此帳號可以用唷!!";
    	  }
//        $id("mem_idCheck").innerHTML = xhr.responseText;
      }else{
    	  alert( xhr.getAllResponseHeaders());
        alert( xhr.status);
       
      }//xhr.status == 200
    }//xhr.readyState == 4
  }//onreadystatechange
  
  //建立好Post連接
  //
  var url = "mem_idCheck.jsp";
  xhr.open( "post", url , true);
  xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
  //送出請求
  var data_info="mem_id=" + $id("mem_id").value;
  xhr.send( data_info );

}//function 