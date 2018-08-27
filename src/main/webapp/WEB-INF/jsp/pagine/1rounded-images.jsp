<%-- 
    Document   : 1rounded-images
    Created on : 22-gen-2018, 16.09.45
    Author     : Marco
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 
        
        
        <!-- Bootstrap CDN
            Se non si desidera scaricare e ospitare da soli Bootstrap, 
            è possibile includerlo da un CDN (Content Delivery Network). 
            MaxCDN fornisce supporto CDN per CSS e JavaScript di Bootstrap. 
            Devi anche includere jQuery
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">-->
        
        <!-- Latest compiled and minified CSS 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->

        <!-- jQuery library 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>-->

        <!-- Latest compiled JavaScript 
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
        
        <!-- ------------------------CHIAMATE RISORSE STATICHE-------------------------------- -->
        <!--1ma forma per chiamare risorse statiche -->
        <link href="${pageContext.request.contextPath}/resources/theme1/css/1provaCss.css"  rel="stylesheet" />
       
        <!--2da forma per chiamare risorse statiche -->
        <link href="<c:url value="/resources/theme1/css/3provaCss.css" />" rel="stylesheet">
       
         <!--3za forma per chiamare risorse statiche -->
        <spring:url value="/resources/theme1/css/2provaCss.css" var="provaCss"  />
        <link href="${provaCss}" rel="stylesheet" />

        <style>

        </style>



        <title>ROUNDED-IMAGES 1</title>
    </head>
    <body>

        <div class="container color-sfondo">
            <div class="color-div-1">
                <h2>Rounded Corners</h2>
                <p>The .img-rounded class adds rounded corners to an image (not available in IE8):</p>            
                <img src="<%=request.getContextPath()%>/resources/theme1/images/spiaggia_650_x_450.png" 
                     class="img-rounded" alt="1Cinque Terre" width="304" height="236" /> 
            </div>
                <br><br>
            <div class="color-div-2">
                <img src="<c:url value="/resources/theme1/images/spiaggia_650_x_450.jpg" />" 
                     alt="2CINQUE TERRE" class="img-rounded" alt="Cinque Terre" width="304" height="236" /> 
            </div>
            <br><br>
            <div class="color-div-3"> 
                <img alt="MY IMAGE"  
                     src="<c:url value="/resources/theme1/images/cinqueterre.jpg"  />">
            </div>
            
             <div class="color-div-3"> 
                <img src="${pageContext.request.contextPath}/resources/theme1/images/spiaggia_650_x_450.png" 
                                  class="img-rounded" alt="1Cinque Terre" width="304" height="236" />
            </div>
            <br><br>
            <div class="path">   
                request.getContextPath() = <%=request.getContextPath()%> <br>
                pageContext.request.contextPath =${pageContext.request.contextPath}

            </div>

        </div>



    </body>
</html>
