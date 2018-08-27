<%-- 
    Document   : glyphycon-1
    Created on : 23-gen-2018, 18.21.42
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

        <title>GLYPHICON-1</title>
    </head>
    <body>
        <div class="container">
            <h2>Glyphicon Examples</h2>
            <p>Envelope icon: <span class="glyphicon glyphicon-envelope"></span></p>    
            <p>Envelope icon as a link:
                <a href="#"><span class="glyphicon glyphicon-envelope"></span></a>
            </p>
            <p>Search icon: <span class="glyphicon glyphicon-search"></span></p>
            <p>Search icon on a button:
                <button type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
            </p>
            <p>Search icon on a styled button:
                <button type="button" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
            </p>
            <p>Print icon: <span class="glyphicon glyphicon-print"></span></p>      
            <p>Print icon on a styled link button:
                <a href="#" class="btn btn-success btn-lg">
                    <span class="glyphicon glyphicon-print"></span> Print 
                </a>
            </p> 
        </div>
    </body>
</html>
