<%-- 
    Document   : 1style_navbar
    Created on : 17-gen-2018, 15.28.38
    Author     : Marco


http://www.littlebigextra.com/add-bootstrap-css-jquery-to-springboot-mvc/
@SpringBootApplication is equivalent of using all three @Configuration, @EnableWebMvc and @ComponentScan
@SpringBootApplication = @Configuration + @EnableWebMvc+@ComponentScan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>Demo Navbar Esempio style 2</title>
        <!-- Access the bootstrap Css like.  vai a questo link per capire meglio= https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/-->
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

        <!--
        < spring:url value="/css/main.css" var="springCss" />
        <link href="$ { springCss }"rel="stylesheet" />
        -->
        <!-- per adesso che facciamo solo la view questo può essere anche tolto!! -->
        <c:url value="/css/main.css" var="jstlCss" />
        <link href="${jstlCss}" rel="stylesheet" />
    </head>
    <body>


        <div id="citation" class="col-md-6 text-center col-md-offset-3">
            <blockquote>“Il design non è come sembra o come appare. Il design è come funziona”.
                <footer><cite>Steve Jobs</cite></footer>
            </blockquote>
        </div>
    </div>
    <div class="row voffset4" id="widgets">
        <div class="col-md-4 col-xs-6">
            <h3 class="text-center">Title</h3>
            <img class="img-thumbnail img-responsive" src="http://placehold.it/360x200/cccccc/000000"/>
            <p class="voffset2">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce justo metus, accumsan viverra iaculis in, pulvinar vel nulla. Aliquam semper erat maximus scelerisque accumsan.</p>
            <a class="btn btn-default pull-right voffset2">Leggi tutto...</a> </div>
        <div class="col-md-4 col-xs-6">
            <h3 class="text-center">Title</h3>
            <img class="img-thumbnail img-responsive" src="http://placehold.it/360x200/cccccc/000000"/>
            <p class="voffset2">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce justo metus, accumsan viverra iaculis in, pulvinar vel nulla. Aliquam semper erat maximus scelerisque accumsan.</p>
            <a class="btn btn-default pull-right voffset2">Leggi tutto...</a> </div>
        <div class="col-md-4 hidden-sm hidden-xs">
            <h3 class="text-center">Title</h3>
            <img class="img-thumbnail img-responsive" src="http://placehold.it/360x200/cccccc/000000"/>
            <p class="voffset2">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce justo metus, accumsan viverra iaculis in, pulvinar vel nulla. Aliquam semper erat maximus scelerisque accumsan.</p>
            <a class="btn btn-default pull-right voffset2">Leggi tutto...</a> </div>
    </div>


    

    <a>  <%@include file="1style_footer.jsp" %> </a>
    
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    
</body>
</html>
