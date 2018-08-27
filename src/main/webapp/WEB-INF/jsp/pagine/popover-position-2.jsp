<%-- 
    Document   : popover-position-2
    Created on : 24-gen-2018, 17.06.51
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

        <title>POPOVER-POSITION-2</title>
    </head>
    <body>

        <div class="container">
            <h3>Popover Example</h3>
            <ul class="list-inline">
                <li><a href="#" title="Header" data-toggle="popover" data-placement="top" data-content="Content">Top</a></li>
                <li><a href="#" title="Header" data-toggle="popover" data-placement="bottom" data-content="Content">Bottom</a></li>
                <li><a href="#" title="Header" data-toggle="popover" data-placement="left" data-content="Content">Left</a></li>
                <li><a href="#" title="Header" data-toggle="popover" data-placement="right" data-content="Content">Right</a></li>
            </ul>
        </div>

        <script>
            $(document).ready(function () {
                $('[data-toggle="popover"]').popover();
            });
        </script>

    </body>
</html>
