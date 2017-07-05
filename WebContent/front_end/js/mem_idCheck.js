
function $id(id){
  return document.getElementById(id);
}
function getInfo(){	
  var xhr = new XMLHttpRequest();
  
  //設定好回呼函數 
  xhr.onreadystatechange = function (){
    if( xhr.readyState == 4 ){

      if(xhr.status == 200){

    	  var text=xhr.responseText;
    	  var idvalue=$id("_id").value;
    	  var idvaluecheck="\""+idvalue+"\"";      //因為indexOF只要有包含就算有，所以必須回傳的帳號與要驗證的帳號前後都加"，來鎖定ID的比對
    	  if (text.indexOf(idvaluecheck)!=-1){

    		  $id("_idCheck").innerHTML="重複，請更換帳號!!";
    		  $id("Input_idCheck").value="重複，請更換帳號!!";
    		  
    		  $id("iconcheck").setAttribute("class","fa fa-times-circle");
    		  $id("iconcheck").style.color="red";
    		  $id("iconcheck").style.fontSize="14px";
    		  $id("iconcheck").innerHTML=" &nbsp此帳號已有人使用";	 
    		  
    	  }else{

    		  $id("_idCheck").innerHTML="此帳號可以用唷!!";
    		  $id("Input_idCheck").value="此帳號可以用唷!!";
    		  $id("checkword").style="display: inline;"
    		  $id("iconcheck").setAttribute("class","fa fa-check-circle");
    		  $id("iconcheck").style.color="green";
    		  $id("iconcheck").style.fontSize="14px";
    		  $id("iconcheck").innerHTML=" &nbsp此帳號可以使用";
//    	     
    	  }

      }else{
    	  alert( xhr.getAllResponseHeaders());
        alert( xhr.status);
       
      }//xhr.status == 200
    }//xhr.readyState == 4
  }//onreadystatechange
  
  //建立好Post連接
  //
  var url=null;
  if ($id("myModalLabel").innerHTML=="一般會員註冊"){
	  url = "mem_idCheck.jsp";
  }else{
	  url = "fir_idCheck.jsp";
  }
  xhr.open( "post", url , true);
  xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
  //送出請求
  var data_info="_id=" + $id("_id").value;
  xhr.send( data_info );

}//function 



function doFirst(){
	var str=$id("errormessage").innerHTML;
	$id("errorMsg").innerHTML=str;
	$id("errorBtn").click();
	$id("_id").keydown=getInfo();
	
}
window.addEventListener('load',doFirst,false);
