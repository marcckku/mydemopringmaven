<%-- 
    Document   : 1style_navbar
    Created on : 17-gen-2018, 15.28.38
    Author     : Marco

Dritta su Spring
http://www.littlebigextra.com/add-bootstrap-css-jquery-to-springboot-mvc/
@SpringBootApplication is equivalent of using all three @Configuration, @EnableWebMvc and @ComponentScan
@SpringBootApplication = @Configuration + @EnableWebMvc+@ComponentScan

LINK bootstrap 3
http://www.semanticstone.net/tutorial/bootstrap-3-realizziamo-layout-responsivo/

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
        
      <!--  richiamo valido di resorse esterne tramite link
            <title>Demo Navbar Esempio style 1</title>
            <link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
        -->

        <!--è valido--><!-- <base href="/"/> non va!!!
          <title>Demo Navbar Esempio style 1</title>
          <meta name="description" content=""/>
          <meta name="viewport" content="width=device-width"/>
          
          <script type="text/javascript" src="webjars/jquery/1.11.1/jquery/jquery.min.js"></script>
          <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
          <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
       -->


      <!--  <title>Demo Navbar Esempio style 1</title>
         Access the bootstrap Css like.  
         vai a questo link per capire meglio= 
        https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/   -->
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

   
       
    </head>
    <body>

        
        ----------------------------------
        BASE NAVBAR
        ----------------------------------

        <div id="headerWrap" class="wrap">
            <header class="container">
                <nav id="mainNav">
                    <!-- qui il menù principale -->
                </nav>
            </header>
        </div>
        <div class="container">
            <div id="carousel"></div>
            <div id="content"></div>
        </div>
        <div id="footerWrap" class="wrap">
            footer!!!!!!
            <footer class="container">
                <nav id="tools">
                    <!-- qui il menù secondario -->
                </nav>
            </footer>
        </div>

        ----------------------------------
        BASE NAVBAR
        ----------------------------------
        

        <nav id="mainNav" class="navbar navbar-inverse" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" s> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                <a href="#" class="navbar-brand">WOW</a> </div>
            <div class="collapse navbar-collapse" id="mainMenu">
                <ul class="nav navbar-nav">
                    <li><a href="#">Chi siamo</a></li>
                    <li class="dropdown"> 
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" href="#">Servizi <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Ideazione loghi</a></li>
                            <li><a href="#">Immagine coordinata</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Sviluppo siti web</a></li>
                            <li><a href="#">Creazione app.</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Portfolio</a></li>
                    <li><a href="#">Contatti</a></li>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Cerca nel sito...">
                    </div>
                </form>
            </div>
        </nav>



        <div class="container"><br/>
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert"
                   aria-label="close">×</a>
                <strong>Success!</strong> It is working as we expected.
            </div>
        </div>






     <div> 

        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Spring Boot</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#about">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>
     </div>

        
        

        <div>  <%@include file="1style_footer.jsp" %> </div>

         <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
         <link rel="stylesheet" href="webjars/bootstrap/3.3.7/js/bootstrap.min.js" />
    </body>
</html>
