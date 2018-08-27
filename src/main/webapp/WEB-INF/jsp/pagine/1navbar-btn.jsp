<%-- 
    Document   : 1navbar-btn
    Created on : 18-gen-2018, 16.17.55
    Author     : Marco
https://getbootstrap.com/docs/3.3/components/#nav-justified
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>


<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Demo Navbar-Form Esempio style 3</title>
        <!--  Access the bootstrap Css like.  
         vai a questo link per capire meglio= 
        https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/  -->
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 

<!-- META-INF.resources.webjars.bootstrap.3.3.7.css -->

    </head>
    <body>

        <button type="button" class="btn btn-default navbar-btn">Sign in</button>

        <DIV>  <%@include file="1style_footer.jsp" %> </DIV>

        <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
