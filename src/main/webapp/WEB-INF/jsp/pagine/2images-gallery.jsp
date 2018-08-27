<%-- 
    Document   : 2images-gallery
    Created on : 22-gen-2018, 23.47.27
    Author     : Marco


https://www.w3schools.com/bootstrap/bootstrap_examples.asp
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

        <title>GALLERIA-FOTO</title>
    </head>
    <body>

        <div class="container">
            <h2>Image Gallery</h2>
            <p>The .thumbnail class can be used to display an image gallery.</p>
            <p>The .caption class adds proper padding and a dark grey color to text inside thumbnails.</p>
            <p>Click on the images to enlarge them.</p>
            <div class="row">

                <div class="col-md-4">
                    <div class="thumbnail">
                        <a href="/w3images/lights.jpg" target="_blank">
                            <img src="<%=request.getContextPath()%>/resources/theme1/images/Rakotz-Bridge-Gablenz-Germania.png" 
                                 alt="Lights" style="width:100%">
                           
                            <div class="caption">
                                <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="thumbnail">
                        <a href="/w3images/lights.jpg" target="_blank">
                            <img src="${pageContext.request.contextPath}/resources/theme1/images/Rakotz-Bridge-Gablenz-Germania.png" 
                                 alt="Lights" style="width:100%">
                           
                            <div class="caption">
                                <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
                            </div>
                        </a>
                    </div>
                </div>


                <div class="col-md-3">
                    <div class="thumbnail">
                        <a href="/w3images/nature.jpg" target="_blank">                             
                            <img alt="MY IMAGE" src="<c:url value="/resources/theme1/images/cinqueterre.jpg"  />" width="304" height="236">
                           
                            <div class="caption">
                                <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="thumbnail">
                        <a href="/w3images/fjords.jpg" target="_blank">
                            <img src="${pageContext.request.contextPath}/resources/theme1/images/spiaggia_650_x_450.png" 
                                 class="img-rounded" alt="1Cinque Terre" width="304" height="236"     /> <!--style="width:100%"-->
                           
                            <div class="caption">
                                <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
                            </div>
                        </a>
                    </div>
                </div>
                                 
                                 
            </div>
        </div>
    </body>
</html>
