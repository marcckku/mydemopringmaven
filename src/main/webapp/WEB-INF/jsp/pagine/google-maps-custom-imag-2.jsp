<%-- 
    Document   : google-maps-custom-imag-2
    Created on : 26-gen-2018, 13.39.26
    Author     : Marco


custom api google-maps
http://www.yourinspirationweb.com/2012/01/19/come-utilizzare-la-mappe-di-google-tramite-le-api/


IMPORTATNTE!!!!!
Per utilizzare l'API JavaScript di Google Maps, devi registrare il tuo progetto dell'app nella 
Console API di Google e ottenere una chiave API di Google che puoi aggiungere alla tua app.

ANDARE QUI: https://developers.google.com/maps/documentation/javascript/get-api-key
 CLICK SU    [ GET KEY ].
DA TENERE A MENTE: Per migliorare la sicurezza della tua app, limita l'uso di questa chiave in
                    NOME : 
                          API key
                        KEY:            
                            AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0

ANDARE SU GMAIL-> ENTRARE ->


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

        <title>Google Maps - Custom - My picture on Map </title>



        <style>
            #mappa{
                width: 700px;
                height: 550px;
            }
            .map-info{
                font-family: Verdana;
                font-size: 13px;
                width: 300px;
            }
        </style>


    </head>


    <body>

        <div id="mappa"></div>



        <!--
                <iframe width="700" height="550" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" 
                        src="http://maps.google.it/maps?f=q&amp;source=s_q&amp;hl=it&amp;q=Via+Monte+Generoso+11A,+6828+Balerna,+Ticino,+Svizzera&amp;aq=&amp;sll=41.442726,12.392578&amp;sspn=26.362962,67.631836&amp;vpsrc=0&amp;ie=UTF8&amp;geocode=FfOquwIdLGKJAA&amp;split=0&amp;hq=&amp;hnear=Via+Monte+Generoso+11A,+6828+Balerna,+Ticino,+Svizzera&amp;t=m&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
                <br />
                <small><a href="http://maps.google.it/maps?f=q&amp;source=embed&amp;hl=it&amp;q=Via+Monte+Generoso+11A,+6828+Balerna,+Ticino,+Svizzera&amp;aq=&amp;sll=41.442726,12.392578&amp;sspn=26.362962,67.631836&amp;vpsrc=0&amp;ie=UTF8&amp;geocode=FfOquwIdLGKJAA&amp;split=0&amp;hq=&amp;hnear=Via+Monte+Generoso+11A,+6828+Balerna,+Ticino,+Svizzera&amp;t=m&amp;z=14&amp;iwloc=A" 
                          style="color:#0000FF;text-align:left">Visualizzazione ingrandita della mappa</a></small>
        -->



        <!-- js custom  -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/theme1/js/google-maps-custom-imag-2.js"></script>
     
        <!--    
             <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=true"></script> 
        -->
        <!--GOOGLE Api KEY-->
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAXgBRx-ChEPNAFlsX4cZZ8IF7dfF7SsU0&callback=initMap"
                type="text/javascript">
        </script>


    </body>
</html>
