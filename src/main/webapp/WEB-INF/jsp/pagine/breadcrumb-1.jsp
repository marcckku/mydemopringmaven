<%-- 
    Document   : breadcrumb-1
    Created on : 23-gen-2018, 18.42.14
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

        <title>BREAD-CRUM</title>
    </head>
    <body>
        <div class="container">
            <h2>Breadcrumbs</h2>
            <p>The .breadcrumb class indicates the current page's location within a navigational hierarchy:</p>                  
            <ul class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">Private</a></li>
                <li><a href="#">Pictures</a></li>
                <li class="active">Vacation</li>        
            </ul>
        </div>
    </body>
</html>
