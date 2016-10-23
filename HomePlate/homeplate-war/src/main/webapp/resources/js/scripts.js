
function terug() {
  document.location.href="javascript:history.back()";
}

function openNewWindow(myUrl) {
  if (myUrl == "") {
      return false;
  }
  var myWindowName = "Sponsor";
  var myWindowOptions = "width=800,height=600";
  window.open(myUrl, myWindowName, myWindowOptions);
}

function stuurBerichtNaarRobur(naar) {
  document.location.href="m"+"ailt"+"o:"+naar+"@"+"robur"+"58."+"c"+"om";
}

$(function() {
  $("#myCarousel .item").first().addClass("active");
  $("#sponsorWidget .item").first().addClass("active");
  $(".carousel-indicators li").first().addClass("active");
  $('[data-toggle="popover"]').popover()
})