function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.397, lng: 150.644},
    mapTypeId: 'satellite',
    scrollwheel: true,
    zoom: 8
  });
  
  //var data = { id: "111", firstx: 1, firsty: 2, secondx: 3, secondy: 4, myx: 5, myy: 6 };
  
  /*$.ajax({
      dataType : "json",
      contentType : "application/json",
      type : "POST",
      url : "map/postAreaData",
      data : JSON.stringify(data),
      contentType : "application/json",
      done : function(data) {
        console.log(data);
      },
      fail : function( jqXHR, textStatus ) {
    	console.log( "Request failed: " + textStatus );
      }
  });*/
}