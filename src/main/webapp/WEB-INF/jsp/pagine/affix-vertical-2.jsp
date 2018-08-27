<%-- 
    Document   : affix-vertical-2
    Created on : 24-gen-2018, 17.40.40
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

        <title>AFFIX-VERTICAL-2</title>
    </head>
    <style>
        /* Note: Try to remove the following lines to see the effect of CSS positioning */
        .affix {
            top: 20px;
            z-index: 9999 !important;
        }
    </style>
</head>
<body>

    <div class="container-fluid" style="background-color:#2196F3;color:#fff;height:200px;">
        <h1>Bootstrap Affix Example</h1>
        <h3>Fixed (sticky) vertical sidenav on scroll</h3>
        <p>Scroll this page to see how the left navigation menu behaves with data-spy="affix".</p>
        <p><strong>The left menu sticks to the page when you have scrolled a specified amount of pixels.</strong></p>
    </div>
    <br>

    <div class="container">
        <div class="row">
            <nav class="col-sm-3">
                <ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="205">
                    <li class="active"><a href="#section1">Section 1</a></li>
                    <li><a href="#section2">Section 2</a></li>
                    <li><a href="#section3">Section 3</a></li>
                </ul>
            </nav>
            <div class="col-sm-9">   
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
                <h1>Some text to enable scrolling</h1>
            </div>
        </div>
    </div>

</body>
</html>

