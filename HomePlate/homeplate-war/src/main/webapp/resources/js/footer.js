$(document).ready(function() {

    $('body').on('click', '.naarbovenlink', function(evnt){
        evnt.preventDefault();
        $('html, body').animate({scrollTop:$(".page-header").offset().top - 50}, "slow");
    });

});