
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});

	function start1(e) {
		var file = e.target.files[0];
		var i1 = document.getElementById("imgdiv");

		if (file) {
			var reader = new FileReader();
			reader.onload = function(q) {
				i1.src = q.target.result;
			}
		}
		reader.readAsDataURL(file);
	}

	function init() {

		document.getElementById("mem_picture").onchange = start1;

	}

	window.onload = init;