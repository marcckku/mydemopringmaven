<%-- 
    Document   : template-simply-me-8
    Created on : 24-gen-2018, 19.36.52
    Author     : Marco


https://www.w3schools.com/bootstrap/bootstrap_templates.asp

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

        <title>TEMPLATES-SIMPLY-ME-8</title>
        <style>
            .bg-1 { 
                background-color: #1abc9c;
                color: #ffffff;
            }
            .bg-2 { 
                background-color: #474e5d;
                color: #ffffff;
            }
            .bg-3 { 
                background-color: #ffffff;
                color: #555555;
            }
            .bg-4 { 
                background-color: #2f2f2f;
                color: #fff;
            }
            .container-fluid {
                padding-top: 70px;
                padding-bottom: 70px;
            }
            .navbar {
                padding-top: 15px;
                padding-bottom: 15px;
                border: 0;
                border-radius: 0;
                margin-bottom: 0;
                font-size: 12px;
                letter-spacing: 5px;
            }
            .navbar-nav  li a:hover {
                color: #1abc9c !important;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="#">Me</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">WHO</a></li>
                        <li><a href="#">WHAT</a></li>
                        <li><a href="#">WHERE</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid bg-1 text-center">
            <h3>Who Am I?</h3>
            <img src="${pageContext.request.contextPath}/resources/theme1/images/bird.jpg" class="img-responsive img-circle" 
                 style="display:inline" alt="Bird" width="350" height="350">
            <h3>I'm an adventurer</h3>
        </div>

        <div class="container-fluid bg-2 text-center">
            <h3>What Am I?</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>
            <a href="#" class="btn btn-default btn-lg">
                <span class="glyphicon glyphicon-search"></span> Search
            </a>
        </div>

        <div class="container-fluid bg-3 text-center">    
            <h3>Where To Find Me?</h3><br>
            <div class="row">
                <div class="col-sm-4">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                     <img src="${pageContext.request.contextPath}/resources/theme1/images/bird1.jpg" class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="col-sm-4"> 
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                     <img src="${pageContext.request.contextPath}/resources/theme1/images/bird2.jpg" class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="col-sm-4"> 
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                     <img src="${pageContext.request.contextPath}/resources/theme1/images/bird3.jpg" class="img-responsive" style="width:100%" alt="Image">
                </div>
            </div>
        </div>

        <footer class="container-fluid bg-4 text-center">
            <p>Bootstrap Theme Made By <a href="https://www.w3schools.com">www.w3schools.com</a></p> 
        </footer>

    </body>
</html>
