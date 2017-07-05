var empty="null";
function $id(id){
  return document.getElementById(id);
}
function favorite(){	

  var xhr = new XMLHttpRequest();
  
  //設定好回呼函數 
  xhr.onreadystatechange = function (){
    if( xhr.readyState == 4 ){
   
      if(xhr.status == 200){
    	  
    	  if (empty=="true"){
    		  $id("btn").setAttribute("class","glyphicon glyphicon-heart-empty");
			  $id("SMsg").innerHTML="加入收藏";
			  $id("SBtn").click();
			  empty="false";
    		 
    	  }else{
    		  $id("btn").setAttribute("class","glyphicon glyphicon-heart");
			  $id("SMsg").innerHTML="取消收藏";
			  $id("SBtn").click();
			  empty="true";
    	  }
    	  

      }else{

      }//xhr.status == 200
    }//xhr.readyState == 4
  }//onreadystatechange
  
  //建立好Post連接
  //
  
  if($id("mem_no1").value!=""){
	  if (empty=="null"){
		  empty=$id("empty").value;
	  }else{
		  empty=empty;
	  }
	  var url=$id("path").value;
	  
	  xhr.open( "post", url , true);
	  xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
	  //送出請求
	  if(empty=="true"){
		  var data_info="mem_no=" + $id("mem_no1").value+"&fa_no=" + $id("fa_no1").value+"&action=insert";
		  
	  }else{
		  var data_info="mem_no=" + $id("mem_no1").value+"&fa_no=" + $id("fa_no1").value+"&action=delete";
	  }

	  xhr.send( data_info );

  }else{
	 
			$id("errorMsg").innerHTML="請登入會員才能收藏";
			$id("errorBtn").click();
		
  }
  
 
}//function 

//
//
//function doFirst(){
//
//	
//}
//window.addEventListener('load',doFirst,false);
