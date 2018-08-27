<%-- 
    Document   : 1non-navbar-links
    Created on : 18-gen-2018, 16.31.44
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Demo Navbar-Form Esempio style 3</title>
        <!--  Access the bootstrap Css like.  
         vai a questo link per capire meglio= 
        https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/  -->
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 



    </head>
    <body>
        <div>
            <p class="navbar-text navbar-right">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>
         </div>
        <DIV>  <%@include file="1style_footer.jsp" %> </DIV>

        <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
