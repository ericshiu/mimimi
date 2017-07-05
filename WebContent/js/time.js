
$(document).ready(function(){
 
  // timer

  // start
var i=0;
  var button = $('#button2'),
  clkbar = $('.clkbar'),
  playState = '-webkit-animation-play-state';

  button.click(function() {

  // pause or start
   if(i==0){clkbar.css(playState, function(i, v) {
       return v === 'paused' ? 'running' : 'paused';
       $('body').toggleClass('paused', $(this).css(playState) === 'paused');
    });}
   if(i==2){
	   
   }
      
  });

});

