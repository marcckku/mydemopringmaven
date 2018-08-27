<%-- 
    Document   : google-maps-search-hotel-autocomplete-5
    Created on : 29-gen-2018, 19.32.36
    Author     : Marco




https://developers.google.com/maps/documentation/javascript/examples/places-autocomplete-hotelsearch
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

        <title>Google Maps - RICERCA AUTO HOTEL -5 </title>
        <link href="${pageContext.request.contextPath}/resources/theme1/css/google-maps-search-hotel-autocomplete-5.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>



        <div id="findhotels">
            Find hotels in:
        </div>

        <div id="locationField">
            <input id="autocomplete" placeholder="Enter a city" type="text" />
        </div>

        <div id="controls">
            <select id="country">
                <option value="all">All</option>
                <option value="au">Australia</option>
                <option value="br">Brazil</option>
                <option value="ca">Canada</option>
                <option value="fr">France</option>
                <option value="de">Germany</option>
                <option value="mx">Mexico</option>
                <option value="nz">New Zealand</option>
                <option value="it" selected>Italy</option>
                <option value="za">South Africa</option>
                <option value="es">Spain</option>
                <option value="pt">Portugal</option>
                <option value="us" >U.S.A.</option>
                <option value="uk">United Kingdom</option>
            </select>
        </div>

        <div id="map"></div>

        <div id="listing">
            <table id="resultsTable">
                <tbody id="results"></tbody>
            </table>
        </div>

        <div style="display: none">
            <div id="info-content">
                <table>
                    <tr id="iw-url-row" class="iw_table_row">
                        <td id="iw-icon" class="iw_table_icon"></td>
                        <td id="iw-url"></td>
                    </tr>
                    <tr id="iw-address-row" class="iw_table_row">
                        <td class="iw_attribute_name">Address:</td>
                        <td id="iw-address"></td>
                    </tr>
                    <tr id="iw-phone-row" class="iw_table_row">
                        <td class="iw_attribute_name">Telephone:</td>
                        <td id="iw-phone"></td>
                    </tr>
                    <tr id="iw-rating-row" class="iw_table_row">
                        <td class="iw_attribute_name">Rating:</td>
                        <td id="iw-rating"></td>
                    </tr>
                    <tr id="iw-website-row" class="iw_table_row">
                        <td class="iw_attribute_name">Website:</td>
                        <td id="iw-website"></td>
                    </tr>
                </table>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/resources/theme1/js/google-maps-search-hotel-autocomplete-5.js" type="text/javascript"></script>

        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0&libraries=places&callback=initMap"
                async defer>
        </script>


    </body>
</html>
