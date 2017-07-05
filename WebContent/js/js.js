$(document).ready(function() {
  //change the integers below to match the height of your upper dive, which I called
  //banner.  Just add a 1 to the last number.  console.log($(window).scrollTop())
  //to figure out what the scroll position is when exactly you want to fix the nav
  //bar or div or whatever.  I stuck in the console.log for you.  Just remove when
  //you know the position.
  $(window).scroll(function () { 

    console.log($(window).scrollTop());

    if ($(window).scrollTop() > 135) {
      $('#nav_bar').addClass('navbar-fixed-top');
      $('#nav_bar').addClass('border');
    }

    if ($(window).scrollTop() < 135) {
      $('#nav_bar').removeClass('navbar-fixed-top');
      $('#nav_bar').removeClass('border');
    }
  });
});

$(document).ready(function(){
  $("#banner").hover(function(){
    $("#banner").css("color","#C10066");
    },function(){
    $("#banner").css("color","#ff96a7");
  });
});

// $(function() {
  // $( "#button" ).click(function() {
  //   $( "#button" ).addClass( "onclic", 250, validate);
  // });

  // function validate() {
  //   setTimeout(function() {
  //     $( "#button" ).removeClass( "onclic" );
  //     $( "#button" ).addClass( "validate", 450, callback );
  //   }, 2250 );
  // }
  //   function callback() {
  //     setTimeout(function() {
  //       $( "#button" ).removeClass( "validate" );
  //     }, 1250 );
  //   }
// });
// time

