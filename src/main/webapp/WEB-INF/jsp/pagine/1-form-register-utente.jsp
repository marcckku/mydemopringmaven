<%-- 
    Document   : 1-form-register-utente
    Created on : 17-gen-2018, 18.21.54
    Author     : Marco


https://www.codeproject.com/Tips/655458/Spring-MVC-Maven-Twitter-Bootstrap-example


<!-- original = < form:form method="pos" modelAttribute="user" >  -->  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<bs:button text="Go" />

<html lang="en">
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Demo Navbar Esempio style 1</title>
        
        <!-- Bootstrap CDN
            Se non si desidera scaricare e ospitare da soli Bootstrap, 
            è possibile includerlo da un CDN (Content Delivery Network). 
            MaxCDN fornisce supporto CDN per CSS e JavaScript di Bootstrap. 
            Devi anche includere jQuery
        -->
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        
        <!--
        < spring:url value="/css/main.css" var="springCss" />
        <link href="$ { springCss }"rel="stylesheet" />
        -->
       <!-- per adesso che facciamo solo la view questo può essere anche tolto!! 
        < c:url value="/css/main.css" var="jstlCss" />
        <link href="$ { jstlCss}" rel="stylesheet" />-->
        
        
    </head>
    <body>
        <h1>Form Register Utente</h1>


       
    <form:form method="post" modelAttribute="user" action="adduser"   commandName="user">
        <table>
            <tr>
                <td>Username:</td>
                <td><form:input type="text" path="name" /></td>
            <td><form:errors path="name" cssClass="red" /></td>
            </tr>
            <br>
            <tr>
                <td>Email:</td>
                <td><form:input type="text" path="email"/></td>
            <td><form:errors path="email" cssClass="red" /></td>
            </tr>
        </table>
        <input type="submit" />
        <input type="reset" />
    </form:form>
    
    
   
    
</body>
</html>
