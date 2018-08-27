<%-- 
    Document   : google-maps-1
    Created on : 25-gen-2018, 16.23.55
    Author     : Marco

Google Maps
Add Google Maps (For more information, read our Google Maps Tutorial).

Use CSS to set the width, height and color of the map:



js library GUIDA ON LINE GOOGLE MAPS API
https://developers.google.com/maps/documentation/javascript/?hl=es_419


esempi
https://developers.google.com/maps/documentation/javascript/examples/






--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        #map {
            height: 400px;
            width: 100%;
        }
    </style>
</head>
<body>
    <h3>My Google Maps 1</h3>
    <div id="map"></div>
    
    
    
    
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/theme1/js/google-maps-1.js"></script>
   
    <!--GOOGLE Api KEY-->
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0&callback=initMap"
            type="text/javascript">
    </script>
</body>
</html>
