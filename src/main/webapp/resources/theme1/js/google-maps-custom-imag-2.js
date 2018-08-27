/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

   /** edisu sala studio  coordinate = 45.090123, 7.658959**/

/**
            var miaPosizione = new google.maps.LatLng(45.853400, 9.003532);//(45.090123,7.658959);//

            var settings = {
                zoom: 15,
                center: miaPosizione,
                mapTypeControl: true,
                mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
                navigationControl: true,
                navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
                mapTypeId: google.maps.MapTypeId.ROADMAP};

            var map = new google.maps.Map(document.getElementById("mappa"), settings);

            //            var mioLogo = new google.maps.MarkerImage('< c:url value="/resources/theme1/images/cantinflas.jpg" />',
            var mioLogo = new google.maps.MarkerImage('/MavenBootstrap3Examples/resources/theme1/images/maurizio.png',
                    new google.maps.Size(60, 120),
                    new google.maps.Point(0, 0)
                    );


            var mioMarker = new google.maps.Marker({
                position: miaPosizione,
                map: map,
                icon: mioLogo,
                title: "Maurizio"
            });

            var box = '<div class="map-info"><h1>Maurizio Tarchini</h1>Lorem ipsum dolor, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>';

            var infobox = new google.maps.InfoWindow({
                content: box
            });

            google.maps.event.addListener(mioMarker, 'click', function () {
                infobox.open(map, mioMarker);
            });

*/




	
 function initMap() {
                       
        var miaPosizione = new google.maps.LatLng(45.090123,7.658959);
  	var settings = {
                        zoom: 15,
                        center: miaPosizione,
                        mapTypeControl: true,
                        mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
                        navigationControl: true,
                        navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
                        mapTypeId: google.maps.MapTypeId.ROADMAP};
	var map = new google.maps.Map(document.getElementById("mappa"), settings);
        var mioLogo = new google.maps.MarkerImage('/MavenBootstrap3Examples/resources/theme1/images/mpavon.png',
	new google.maps.Size(78,136),//ancho=78, alto=heigh=136bc
	new google.maps.Point(0,0)
	);


	var mioMarker = new google.maps.Marker({
		position: miaPosizione,
		map: map,
		icon: mioLogo,
	
		title:"Studente: Marco Pavon"
	});

	var contentString = '<div class="map-info"><h1>Marco Pavon\n\
                            </h1>Studende part-time presso il Dipartimento di Informatica, via Pessinetto 12, Torino. </div>';
 
	var infowindow = new google.maps.InfoWindow({
    	content: contentString
	});
	google.maps.event.addListener(mioMarker, 'click', function() {
  		infowindow.open(map,mioMarker);
		});

            
            
            
            
            
        }
        
        
       
