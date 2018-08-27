<%-- 
    Document   : affix-scrollspy-vertical-mix-4
    Created on : 24-gen-2018, 17.50.08
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

        <title>AFFIX-SCROLLSPY-VERTICAL-MIX-4</title>
    </head>

    <style>
        body {
            position: relative;
        }
        .affix {
            top: 20px;
            z-index: 9999 !important;
        }
        div.col-sm-9 div {
            height: 250px;
            font-size: 28px;
        }
        #section1 {color: #fff; background-color: #1E88E5;}
        #section2 {color: #fff; background-color: #673ab7;}
        #section3 {color: #fff; background-color: #ff9800;}
        #section41 {color: #fff; background-color: #00bcd4;}
        #section42 {color: #fff; background-color: #009688;}

        @media screen and (max-width: 810px) {
            #section1, #section2, #section3, #section41, #section42  {
                margin-left: 150px;
            }
        }
    </style>
    <body data-spy="scroll" data-target="#myScrollspy" data-offset="15">

        <div class="container-fluid" style="background-color:#2196F3;color:#fff;height:220px;">
            <h1>Scrollspy & Affix Example</h1>
            <h3>Fixed vertical sidenav on scroll</h3>
            <p>Scroll this page to see how the navbar behaves with data-spy="affix" and data-spy="scrollspy".</p>
            <p>The left menu sticks the page after you have scrolled a specified amount of pixels, and the links in the menu are automatically updated based on scroll position.</p>
        </div>
        <br>

        <div class="container">
            <div class="row">
                <nav class="col-sm-3" id="myScrollspy">
                    <ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="205">
                        <li><a href="#section1">Section 1</a></li>
                        <li><a href="#section2">Section 2</a></li>
                        <li><a href="#section3">Section 3</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Section 4 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#section41">Section 4-1</a></li>
                                <li><a href="#section42">Section 4-2</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
                <div class="col-sm-9">
                    <div id="section1">    
                        <h1>Section 1</h1>
                        <p>Try to scroll this section and look at the navigation list while scrolling!</p>
                    </div>
                    <div id="section2"> 
                        <h1>Section 2</h1>
                        <p>Try to scroll this section and look at the navigation list while scrolling!</p>
                    </div>        
                    <div id="section3">
                        <h1>Section 3</h1>
                        <p>Try to scroll this section and look at the navigation list while scrolling!</p>
                    </div>
                    <div id="section41">
                        <h1>Section 4-1</h1>
                        <p>Try to scroll this section and look at the navigation list while scrolling!</p>
                    </div>      
                    <div id="section42">
                        <h1>Section 4-2</h1>
                        <p>Try to scroll this section and look at the navigation list while scrolling!</p>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
