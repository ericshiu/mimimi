$(function(){
  $('.gallery img').click(function(){
    $('.itemImg').attr('src', $(this).attr('src'));
  });
})