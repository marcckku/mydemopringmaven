<%-- 
    Document   : media-object-nested-3
    Created on : 24-gen-2018, 15.45.02
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CDN
            Se non si desidera scaricare e ospitare da soli Bootstrap, 
            è possibile includerlo da un CDN (Content Delivery Network). 
            MaxCDN fornisce supporto CDN per CSS e JavaScript di Bootstrap. 
            Devi anche includere jQuery
        -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

          <!--  ---------------------RInizio ichiamo il Boostrap scaricato con MAVEN------------------------   -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 
        <!--  ---------------------fine Richiamo il Boostrap scaricato con MAVEN------------------------   -->
        
        
        
        <title>MEDIA-OBJECT-NESTED</title>
    </head>
    <body>
        <div class="container">
            <h2>Nested Media Objects</h2>
            <p>Media objects can also be nested (a media object inside a media object):</p><br>
            <div class="media">
                <div class="media-left">
                    <img src="${pageContext.request.contextPath}/resources/theme1/images/jhonny-depp.jpg" class="media-object" style="width:45px">
                </div>
                <div class="media-body">
                    <h4 class="media-heading">John Doe <small><i>Posted on February 19, 2016</i></small></h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>

                    <!-- Nested media object -->
                    <div class="media">
                        <div class="media-left">
                            <img src="${pageContext.request.contextPath}/resources/theme1/images/cantinflas.jpg" class="media-object" style="width:45px">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">John Doe <small><i>Posted on February 19, 2016</i></small></h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>

                            <!-- Nested media object -->
                            <div class="media">
                                <div class="media-left">
                                    <img src="${pageContext.request.contextPath}/resources/theme1/images/marilyn-monroe.jpg" class="media-object" style="width:45px">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">John Doe <small><i>Posted on February 19, 2016</i></small></h4>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
