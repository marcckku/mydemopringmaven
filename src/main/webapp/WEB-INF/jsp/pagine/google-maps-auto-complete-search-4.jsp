<%-- 
    Document   : google-maps-auto-complete-search-4
    Created on : 29-gen-2018, 19.09.28
    Author     : Marco
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

        <!-- Google Api  equivalente agli script di sotto- -->
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        

        <title>Google Maps - RICERCA CON AUTO COMPLETAMENTO -4 </title>

        <link href="${pageContext.request.contextPath}/resources/theme1/css/google-maps-search-autocomplete-4.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="pac-card" id="pac-card">
            <div>
                <div id="title">
                    Autocomplete search
                </div>
                <div id="type-selector" class="pac-controls">
                    <input type="radio" name="type" id="changetype-all" checked="checked">
                    <label for="changetype-all">All</label>

                    <input type="radio" name="type" id="changetype-establishment">
                    <label for="changetype-establishment">Establishments</label>

                    <input type="radio" name="type" id="changetype-address">
                    <label for="changetype-address">Addresses</label>

                    <input type="radio" name="type" id="changetype-geocode">
                    <label for="changetype-geocode">Geocodes</label>
                </div>
                <div id="strict-bounds-selector" class="pac-controls">
                    <input type="checkbox" id="use-strict-bounds" value="">
                    <label for="use-strict-bounds">Strict Bounds</label>
                </div>
            </div>
            <div id="pac-container">
                <input id="pac-input" type="text"
                       placeholder="Enter a location">
            </div>
        </div>
        <div id="map"></div>
        <div id="infowindow-content">
            <img src="" width="16" height="16" id="place-icon">
            <span id="place-name"  class="title"></span><br>
            <span id="place-address"></span>
        </div>


        <script src="${pageContext.request.contextPath}/resources/theme1/js/google-maps-search-autocomplete-4.js" type="text/javascript"></script>

        <!--
        <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places&callback=initMap"
                async defer>
        </script>-->
         <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0&libraries=places&callback=initMap"
                async defer>
        </script>
        
    </body>
</html>
