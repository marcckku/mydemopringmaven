<%-- 
    Document   : 5style_navbar-scroll
    Created on : 19-gen-2018, 19.30.25
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CDN
            Se non si desidera scaricare e ospitare da soli Bootstrap, 
            è possibile includerlo da un CDN (Content Delivery Network). 
            MaxCDN fornisce supporto CDN per CSS e JavaScript di Bootstrap. 
            Devi anche includere jQuery
        -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <title>Navbar-5-SCROLL</title>
    </head>
    <body>
    <body style="height:1500px">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">WebSiteName</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">Page 1</a></li>
                    <li><a href="#">Page 2</a></li>
                    <li><a href="#">Page 3</a></li>
                </ul>
            </div>
        </nav>

        <div class="container" style="margin-top:50px">
            <h3>Fixed Navbar</h3>
            <div class="row">
                <div class="col-md-4">
                    <p>A fixed navigation bar stays visible in a fixed position (top or bottom) independent of the page scroll.</p>
                    <p>A fixed navigation bar stays visible in a fixed position (top or bottom) independent of the page scroll.</p>    
                </div>
                <div class="col-md-4"> 
                    <p>A fixed navigation bar stays visible in a fixed position (top or bottom) independent of the page scroll.</p>
                    <p>A fixed navigation bar stays visible in a fixed position (top or bottom) independent of the page scroll.</p>
                </div>
                <div class="col-md-4"> 
                    <p>A fixed navigation bar stays visible in a fixed position (top or bottom) independent of the page scroll.</p>
                    <p>A fixed navigation bar stays visible in a fixed position (top or bottom) independent of the page scroll.</p> 
                </div>
            </div>
        </div>

        <h1>Scroll this page to see the effect</h1>








    </body>
</html>
