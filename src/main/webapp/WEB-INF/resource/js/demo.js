var basicHref;
var map;

var data = {
"features": [
  {
    "type": "Feature",
    "geometry": {
      "type": "Point",
      "coordinates": [120.498097, 23.047295]
    },
    "properties": { 
      "valueCount": 5
    }
  },
  {
    "type": "Feature",
    "geometry": {
      "type": "Point",
        "coordinates": [120.497778, 23.047333]
      },
    "properties": { 
      "valueCount": 5
    }
  },
  {
    "type": "Feature",
    "geometry": {
      "type": "Point",
      "coordinates": [120.497612, 23.047253]
    },
    "properties": { 
      "valueCount": 5
    }
  },
  {
    "type": "Feature",
      "geometry": {
      "type": "Point",
      "coordinates": [120.497411, 23.047168]
    },
    "properties": { 
      "valueCount": 3
    }
  }
]
};

function setBasicHref(value) {
  basicHref = value;
}

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 23.047943, lng: 120.494246},
    mapTypeId: 'satellite',
    scrollwheel: true,
    zoom: 6
  });
  
  /*var directionsService = new google.maps.DirectionsService;
  var directionsDisplay = new google.maps.DirectionsRenderer;
  directionsDisplay.setMap(map);
    
  var myLatLng = {lat: 23.047874, lng: 120.497431};
  var destnation = {lat: 23.047579, lng: 120.491863};
  
  calculateAndDisplayRoute(directionsService, directionsDisplay, myLatLng, destnation);*/
  
  /*var marker = new google.maps.Marker({
    position: myLatLng,
    map: map,
    title: 'People'
  });
  
  var marker2 = new google.maps.Marker({
    position: destnation,
    map: map,
    title: 'Save'
  });*/
  
  //draw_heatmap(data);
}

function calculateAndDisplayRoute(directionsService, directionsDisplay, source, destination) {
  directionsService.route({
    origin: source,
    destination: destination,
    travelMode: 'DRIVING'
  }, function(response, status) {
    if (status === 'OK') {
      directionsDisplay.setDirections(response);
    } else {
      window.alert('Directions request failed due to ' + status);
    }
  });
}

function setMarker(latitude, longitude, name, image, infowindow) {
  var marker = new google.maps.Marker({
    position: new google.maps.LatLng(latitude, longitude),
    title: name + "\n" + latitude + "\n" + longitude,
    icon: "http://maps.google.com/mapfiles/ms/icons/red-dot.png"
  });
  
  if (infowindow) {
    marker.addListener('mouseover', function() {
      infowindow.open(map, this);
    });

    marker.addListener('mouseout', function() {
      infowindow.close();
    });
  }

  marker.setMap(map);
}

function draw_heatmap(results) {
  var heatmapData = [];

  for (var i = 0; i < results.features.length; i++) {
    var coords = results.features[i].geometry.coordinates;
    var latLng = new google.maps.LatLng(coords[1], coords[0]);
    var weightedLoc = {
      location: latLng,
      weight: results.features[i].properties.valueCount
    };
    heatmapData.push(weightedLoc);
  }

  var heatmap = new google.maps.visualization.HeatmapLayer({
    data: heatmapData,
    dissipating: true,
    map: map,
    radius: 80
  });
}

function showHeatmap(results) {
  var heatmapData = [];
  
  for (var i = 1; i < results.length; i++) {
    if (results[i][0] != null && results[i][1] != null) {     
      var latLng = new google.maps.LatLng(results[i][0], results[i][1]);
      var weight = (results[i][8] / 10);
      var weightedLoc = {
        location: latLng,
        weight: weight
      };
      
      heatmapData.push(weightedLoc);
    }
  }
  
  var heatmap = new google.maps.visualization.HeatmapLayer({
    data: heatmapData,
    dissipating: true,
    map: map,
    radius: 10
  });
}

function getFireData() {
  $.ajax({
    url: basicHref + "/map/getFireData",
    type: 'POST',
    dataType: 'text',
    async: false,
    success: function(responseObj) {
      if (responseObj) {
        Papa.parse(responseObj, {
          complete: function(results) {
            showHeatmap(results.data);
          }
        })
      }
    },
    error: function(xhr, ajaxOptions, thrownError) {
      console.log("error");
    }
  });
}

$(function() {
  $(document).ready(function () {
    setInterval(getFireData(), 86400000);
  });
})