<%-- 
    Document   : media-object-1
    Created on : 24-gen-2018, 15.22.47
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

        <title>MEDIA-OBJECT-1</title>
    </head>
    <body>
        <div class="container">
            <h2>Media Object</h2>
            <p>Use the "media-left" class to left-align a media object. Text that should appear next to the image, is placed inside a container with class="media-body".</p>
            <p>Tip: Use the "media-right" class to right-align the media object.</p><br>

            <!-- Left-aligned media object -->
            <div class="media">
                <div class="media-left">
                    <img src="${pageContext.request.contextPath}/resources/theme1/images/jhonny-depp.jpg" class="media-object" style="width:60px">
                </div>
                <div class="media-body">
                    <h4 class="media-heading">Left-aligned</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
            </div>
            <hr>

            <!-- Right-aligned media object -->
            <div class="media">
                <div class="media-body">
                    <h4 class="media-heading">Right-aligned</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
                <div class="media-right">
                     <img src="${pageContext.request.contextPath}/resources/theme1/images/cantinflas.jpg" class="media-object" style="width:60px">
                </div>
            </div>
        </div>
    </body>
</html>
