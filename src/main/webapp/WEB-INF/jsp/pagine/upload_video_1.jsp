

<%-- 
    Document   : upload_video_1
    Created on : 7-mag-2018, 17.30.25
    Author     : Utente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--  Access the bootstrap Css like.  
         vai a questo link per capire meglio= 
        https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/  -->
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 

        <!-- <link href="< c:url value='/static/css/bootstrap.css' />"  rel="stylesheet" type="text/css" />
        <link href="< c:url value='/static/css/app.css' />" rel="stylesheet" type="text/css" />
        <link href="../../../resources/theme1/css/1_sign_in.css" rel="stylesheet" type="text/css"/>
        -->

         <!-- ------------------------CHIAMATE RISORSE STATICHE-------------------------------- -->
        <!--1ma forma per chiamare risorse statiche -->
        <link href="${pageContext.request.contextPath}/resources/theme1/css/1provaCss.css"  rel="stylesheet" />
       
        <!--2da forma per chiamare risorse statiche -->
        <link href="<c:url value="/resources/theme1/css/3provaCss.css" />" rel="stylesheet">
       
         <!--3za forma per chiamare risorse statiche -->
        <spring:url value="/resources/theme1/css/2provaCss.css" var="provaCss"  />
        <link href="${provaCss}" rel="stylesheet" />
        
        <style>


            .div1{
                background-color: green;
            }

            .div2{
                background-color: gold;
            }

            .div3{
                background-color: greenyellow;
            }

            .div4{
                background-color: graytext;
            }


            .div5{
                background-color: hotpink;
            }
        </style>

        <title>JSP Page UPLOAD VIDEO</title>
    </head>
    <body>
        <h1>UPLOAD VIDEO 1</h1>

<!--
        <div class="div1">
            *//////////////////////////////////////////***<br/>
            <h3>
                - INSERIMENTO IN MONGODB TRAMITE FRAMEWORK SPRING - MODEL - <br/>
                - NON BISOGNA STENDERE LA CLASSE HTTPSERVET. <br/>
                - MA USARE @Requestmapping(). TRAMITE LA ACTION { actionUploadVideo1 } LASCIATO AL PROGRAMMATORE!!<br/>
            </h3>
            *//////////////////////////////////////////***<br/>
            <form :form method="POST" modelAttribute="fileVideo" 
                       enctype="multipart/form-data" 
                       action="actionUploadVideo1" commandName="fileVideo"   >
                <table>
                    <tr>
                        Inserisci nome Video
                        <td><form :label path="titolo"></form :label></td>
                            <td><input type="text" name="titolo" path="titolo"/></td>
                        </tr>
                        <tr>Seleziona File
                            <td><form :label path="file">Select a file to upload</form :label></td>
                            <td><input type="file" name="file"  path="file"/></td>
                        </tr>
                        <tr>
                        </tr>
                    </table>
                    <input type="submit"  value="uploadVideo"/>
                    <input type="reset" />
            </form :form>
        </div>  

        <body>
	<form method="POST" action="uploadMultipleFile" enctype="multipart/form-data">
		File1 to upload: <input type="file" name="file"><br /> 
		Name1: <input type="text" name="name"><br /> <br /> 
		File2 to upload: <input type="file" name="file"><br /> 
		Name2: <input type="text" name="name"><br /> <br />
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
</body>





-->

        <div class="div2">
            *//////////////////////////////////////////***<br/>
            <h4>
                - UPLOAD FILE SINGOLO. PER QUESTO BISOGNA AVER MODIFICATO A LIVELLO DI web.xml <br/>
               - O CON LA ANNOTATION { @MultipartConfig } NELLA TUA CLASSE CONTROLLER.<br/>
               - IN QUESTO CASO BISOGNA CHE IL TUO CONTROLLER EXTENDA LA CLASSE HTTPSERVLET.<br/>
               -------------------------------------------------------------------------------------------------------------------------------<br/>
               - ATTENZIONE!! SE SI USA SPRING MVC FRAMEWORK, NON SI DEVE EXTENDERE LA CLASSE HTTPSERVLET.<br/>
               - TRAMITE LA ACTION {controllerPagineBootstrapMapping} CONFIGURATO NEL web.xml.<br/>
            </h4>
            *//////////////////////////////////////////***<br/>

            <h3>File Upload:</h3>
            Select a file to upload: <br />
            <form action="actionUploadVideo1" method="post"
                  enctype="multipart/form-data">
                <input type="file" name="file" size="50" />
                <br />
                <input type="submit" value="Upload File" />
            </form>

        </div>


    </body>
</html>
