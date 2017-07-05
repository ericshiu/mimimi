
    var MyPoint = "/OrdServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var webSocket;
	
	function connect() {
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			
			if(jsonObj.type=='update') {
				$('.ordNo').each(function(index){
		        	if ($('.ordNo').eq(index).text()==jsonObj.ord_no){
			    		$(".ordStatus").eq(index).text("修改訂單中");
			    		$(".prepare").eq(index).attr("disabled","true");	
			    	}
		        });
			}
			
	        if(jsonObj.type=='finishUpdate') {
	        	$('.ordNo').each(function(index){
		        	if ($('.ordNo').eq(index).text()==jsonObj.ord_no){
			    		$(".ordStatus").eq(index).text("確認訂單");
			    		$(".prepare").eq(index).removeAttr("disabled");	
			    	}
		        });
	        }
	        
	        if(jsonObj.type=='prepareShip') {
	        	$('.memOrdNo').each(function(index){
		        	if ($('.memOrdNo').eq(index).text()==jsonObj.ord_no){
			    		$(".memOrdStatus").eq(index).text("出貨中");
			    		$(".changeOrd").eq(index).attr("disabled","true");
			    	}
		        });
	        }
	        
	        if(jsonObj.type=='finishShip') {
	        	$('.memOrdNo').each(function(index){
		        	if ($('.memOrdNo').eq(index).text()==jsonObj.ord_no){
			    		$(".memOrdStatus").eq(index).text("完成出貨");
			    		$(".finishOrd").eq(index).removeAttr("disabled");
			    		$(".ordShipNo").eq(index).text(jsonObj.ord_ship_no);
			    	}
		        });
	        }    
		};

	}
	
	function clickBtn(index) {
		
		var ord_no = document.getElementById("ord_no"+index).value;
		var jsonObj = {"type" : "update", "ord_no" : ord_no};
		webSocket.send(JSON.stringify(jsonObj));
	}
	
	function finishUpdate() {
		
		var ord_no = document.getElementById("ord_no").value;
		var jsonObj = {"type" : "finishUpdate", "ord_no" : ord_no};
        webSocket.send(JSON.stringify(jsonObj));
	}
	
	function prepareShip(index) {
		var ord_no = document.getElementById("ord_no"+index).value;
		var jsonObj = {"type" : "prepareShip", "ord_no" : ord_no};
        webSocket.send(JSON.stringify(jsonObj));
	}
	
	function finishShip(index) {
		var ord_no = document.getElementById("ord_no"+index).value;
		var ord_ship_no = document.getElementById("ord_ship_no"+index).value;
		var jsonObj = {"type" : "finishShip", "ord_no" : ord_no, "ord_ship_no" : ord_ship_no};
        webSocket.send(JSON.stringify(jsonObj));
	}

	function disconnect () {
		webSocket.close();
	}
