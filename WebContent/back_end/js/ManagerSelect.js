//
//
//function $id(id){
//  return document.getElementById(id);
//}
//
//function doFirst(){
//	var aut=$id("aut").innerHTML;
//	var Aaut=aut.split(",");
//	var manaut=$id("manaut").innerHTML;
//	var Amanaut=manaut.split(",");
//
//	for (var i=0; i<Aaut.length-1;i++){
//
//			if (Aaut[i].trim().indexOf(Amanaut[0].trim())!=-1){
//				Aaut.splice(i,1);
//				Amanaut.splice(Amanaut[0],1);
//				i--;	
//				if (Amanaut.length==1){
//					break;  
//				}
//			}  
//	}	
//	var length=Aaut.length-1;
//	
//	for (var i=0;i<length;i++){
//		var self =$id(Aaut[i].trim())
//		var father=self.parentNode;
//		father.removeChild(self);
//	}
//	
//}
//window.addEventListener('load',doFirst,false);
