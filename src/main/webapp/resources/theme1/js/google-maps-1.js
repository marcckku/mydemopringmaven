/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 function initMap() {
            var uluru = {lat: -25.363, lng: 131.044};
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 4,
                center: uluru
            });
            var marker = new google.maps.Marker({
                position: uluru,
                map: map
            });
            
            
        var miaPosizione = new google.maps.LatLng(45.853400,9.003532);
  	var settings = {
                        zoom: 15,
                        center: miaPosizione,
                        mapTypeControl: true,
                        mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
                        navigationControl: true,
                        navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
                        mapTypeId: google.maps.MapTypeId.ROADMAP};
	var map = new google.maps.Map(document.getElementById("map"), settings);
        var mioLogo = new google.maps.MarkerImage('/MavenBootstrap3Examples/resources/theme1/images/maurizio.png',
	new google.maps.Size(60,120),
	new google.maps.Point(0,0)
	);


	var mioMarker = new google.maps.Marker({
		position: miaPosizione,
		map: map,
		icon: mioLogo,
	
		title:"Maurizio"
	});

	var contentString = '<div class="map-info"><h1>Maurizio Tarchini</h1>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>';
 
	var infowindow = new google.maps.InfoWindow({
    	content: contentString
	});
	google.maps.event.addListener(mioMarker, 'click', function() {
  		infowindow.open(map,mioMarker);
		});

            
            
            
            
            
        }
        
        
       