window.onload = function (){
    var files = document.getElementsByClassName("imgInp_ori");
    for(var i = 0; i < files.length; i++){
    	files[i].onchange=showImage_ori;
    }
    
    var divDel = document.getElementsByClassName("divDel");
    for(var i = 0; i < divDel.length; i++){
    	divDel[i].onclick=delImageDiv;
    }  
    
    
    if ($id("errorMsgs")) {    
		var str=$id("errorMsgs").innerHTML;
		
		$id("errorMsg").innerHTML=str;
		$id("errorBtn").click();
	
	}
 
     
}

function setImgFun(){
    var files = document.getElementsByClassName("imgInp_add");
    for(var i = 0; i < files.length; i++){
    	files[i].onchange=showImage_add;
    }	
    var divDel = document.getElementsByClassName("divDel");
    for(var i = 0; i < divDel.length; i++){
    	divDel[i].onclick=delImageDiv;
    }      
    
}

function showImage_ori(e){
	console.log(this.files);
	
	var x = event.target;
	
	if (x.value.length != 0) {
		var parent = x.parentNode.parentNode.parentNode.parentNode;
		
		if (! parent.lastChild.hasChildNodes()) {
	        var div = document.createElement('div');
	        div.style.textAlign="center";
	        var img = document.createElement('img');
	        img.src = URL.createObjectURL(event.target.files[0]);
	        img.setAttribute("class","img-upload");
	        div.appendChild(img);
	        x.parentNode.parentNode.parentNode.parentNode.appendChild(div);

	        x.parentNode.parentNode.parentNode.childNodes[3].setAttribute("value",x.value);			
		} else {
			var last = parent.lastChild;
			parent.removeChild(last);
	        var div = document.createElement('div');
	        div.style.textAlign="center";
	        var img = document.createElement('img');
	        img.src = URL.createObjectURL(event.target.files[0]);
	        img.setAttribute("class","img-upload");
	        div.appendChild(img);
	        parent.appendChild(div);

	        x.parentNode.parentNode.parentNode.childNodes[3].setAttribute("value",x.value);			

		}
		

	}

}

function showImage_add(e){
	var x = event.target;
	var parent = x.parentNode.parentNode.parentNode.parentNode;

	
	if (x.value.length != 0) {
		
		if (parent.lastChild.childNodes.length > 1) {
	        var div = document.createElement('div');
	        div.style.textAlign="center";
	        var img = document.createElement('img');
	        img.src = URL.createObjectURL(event.target.files[0]);
	        img.setAttribute("class","img-upload");
 
	        
	        div.appendChild(img);
	        x.parentNode.parentNode.parentNode.parentNode.appendChild(div);

	        x.parentNode.parentNode.parentNode.childNodes[1].setAttribute("value",x.value);			
		} else {
			var last = parent.lastChild;
			parent.removeChild(last);
	        var div = document.createElement('div');
	        div.style.textAlign="center";
	        var img = document.createElement('img');
	        img.src = URL.createObjectURL(event.target.files[0]);
	        img.setAttribute("class","img-upload");
	        div.appendChild(img);
	        parent.appendChild(div);

	        x.parentNode.parentNode.parentNode.childNodes[1].setAttribute("value",x.value);			

		}
		

	}
}

function delImageDiv(e){
	var x = event.target;
	var nownode = x.parentNode.parentNode.parentNode.parentNode;
	var parent = x.parentNode.parentNode.parentNode.parentNode.parentNode;
	
	parent.removeChild(nownode);
	
}

function createImgDiv(){
	var x = document.getElementById("PC_PICTURES");
	var addPic = document.getElementById("addPic");	
	var div1 = document.createElement('div');
	div1.setAttribute("class","form-group pic-group");
//照片說明區	
	var div2 = document.createElement('div');
	div2.setAttribute("class","input-group");	
	div2.style.align="right";

	var span2 = document.createElement('span');
	span2.setAttribute("class","input-group-btn");
	var span3 = document.createElement('span');
	span3.setAttribute("class","btn btn-warning btn-del");
	var i1 = document.createElement('i');
	i1.setAttribute("class","fa fa-trash-o");
	i1.innerHTML=" &nbsp移除";
	var input2 = document.createElement('input');
	input2.type="button";
	input2.setAttribute("class","divDel");
	span2.appendChild(span3);	
	span3.appendChild(i1);
	span3.appendChild(input2);
	div2.appendChild(span2);	

//照片新增區	
	var div3 = document.createElement('div');
	div3.setAttribute("class","input-group");
	div3.style.width="95%";
	var span4 = document.createElement('span');
	span4.setAttribute("class","input-group-btn");
	var span5 = document.createElement('span');
	span5.setAttribute("class","btn btn-info btn-file");
	span5.innerText="選擇照片";
	var input3 = document.createElement('input');
	input3.type="file";
	input3.setAttribute("class","imgInp_add");
	input3.setAttribute("name","prp_picture");	
	span5.appendChild(input3);
	span4.appendChild(span5);
	var input4 = document.createElement('input');
	input4.type="text";
	input4.setAttribute("class","form-control");	
	input4.setAttribute("readyonly","readyonly");
	div3.appendChild(span4);	
	div3.appendChild(input4);
	
	var span2 = document.createElement('span');
	span2.setAttribute("class","input-group-btn");
	span2.style.height="100%";
	var span3 = document.createElement('span');
	span3.setAttribute("class","btn btn-warning btn-del");
	span3.style.height="100%";
	var i1 = document.createElement('i');
	i1.setAttribute("class","fa fa-trash-o");
	i1.innerHTML=" &nbsp移除";
	var input2 = document.createElement('input');
	input2.type="button";
	input2.setAttribute("class","divDel");
	span2.appendChild(span3);	
	span3.appendChild(i1);
	span3.appendChild(input2);
	div3.appendChild(span2);	
	
	
	div1.appendChild(div3);
	
	x.insertBefore(div1,addPic);

	setImgFun();
	
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
	    	  var idvalue=xhr.responseText; 	  

        	  if (!idvalue.match("查無此帳號")){
      			i = document.getElementById('idCheck-icon');
      			i.setAttribute("class","fa fa-times-circle");
      			i.style.color="red";
      			i.style.fontSize="14px";
      			i.innerHTML=" &nbsp此帳號已有人使用";	    		  
    		  
        	  }else{
      			i = document.getElementById('idCheck-icon');
      			i.setAttribute("class","fa fa-check-circle");
      			i.style.color="green";
      			i.style.fontSize="14px";
      			i.innerHTML=" &nbsp此帳號可以使用";	    		  
      			
        	  }	     		   
	    	  
	    	  

	      }else{
//	    	  alert( xhr.getAllResponseHeaders());
//	        alert( xhr.status);
	       
	      }//xhr.status == 200
	    }//xhr.readyState == 4
	  }//onreadystatechange
	  
	  //建立好Post連接
	  //
	  var url = "pc_idCheck.jsp";
	  xhr.open( "post", url , true);
	  xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
	  //送出請求
	  var data_info="pc_id=" + $id("pc_id").value;
	  xhr.send( data_info );

	}//function 

function addressSelect(){
	
}