<%-- 
    Document   : google-maps-finder-3
    Created on : 29-gen-2018, 18.19.30
    Author     : Marco
link del codice
https://developers.google.com/maps/documentation/javascript/examples/places-placeid-finder
link guida
https://developers.google.com/places/place-id#id-overview


Cerca Lat e Longitudine
http://www.latitudinelongitudine.it/?address=Italia



<!--GOOGLE Api KEY OCCHIO CAMBIA RISPETTO AI PRIMI DUE ESEMPI CAMBIARE SOTTO!!
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0& -> callback=initMap"
        type="text/javascript">
</script>-->
</script>
IN QUESTO :
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0& -> libraries=places&callback=initMap"
    async defer>
</script>

--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <!-- Theme Made By www.w3schools.com - No Copyright -->
        <!-- Bootstrap CDN
            Se non si desidera scaricare e ospitare da soli Bootstrap, 
            è possibile includerlo da un CDN (Content Delivery Network). 
            MaxCDN fornisce supporto CDN per CSS e JavaScript di Bootstrap. 
            Devi anche includere jQuery
        -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              <!-- Latest compiled and minified CSS  -->
              <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library  -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript   -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <!-- Google Api  equivalente agli script di sotto- 
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        -->

        <title>Google Maps - FINDER ADDRESS -3 </title>

        <link href="${pageContext.request.contextPath}/resources/theme1/css/google-maps-finder-3.css" rel="stylesheet" type="text/css"/>




    </head>

    <body>


        <input id="pac-input" class="controls" type="text" placeholder="Enter a location">


        <div id="map"></div>

        <div id="infowindow-content">
            <span id="place-name"  class="title"></span><br>

            Place ID <span id="place-id"></span><br>

            <span id="place-address"></span>
        </div>





        <!-- js custom  -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/theme1/js/google-maps-finder-3.js" type="text/javascript"></script>

        <!--GOOGLE Api KEY OCCHIO CAMBIA RISPETTO AI PRIMI DUE ESEMPI-->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0&libraries=places&callback=initMap"
                async defer>
        </script>

    </body>
</html>
