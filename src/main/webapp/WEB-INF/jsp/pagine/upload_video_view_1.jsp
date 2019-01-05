<%-- 
    Document   : upload_video_view_1

    link: https://stackoverflow.com/questions/10066349/spring-display-image-on-jsp-file
    Created on : 9-giu-2018, 20.49.17
    Author     : Utente
--%>

<%@page import="java.lang.String"%>
<%@page import="com.my.librery.UtilGridFSDBFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--  ---------------------RInizio ichiamo il Boostrap scaricato con MAVEN------------------------   -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 
        <!--  ---------------------fine Richiamo il Boostrap scaricato con MAVEN------------------------   -->
        <!--  ---------------------Inizio è sempre meglio prende il Boostrap dal Server più vicino on line------------------------   -->
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
        <!--  ---------------------fine è sempre meglio prende il Boostrap dal Server più vicino on line------------------------   -->
        <!-- ------------------------CHIAMATE RISORSE STATICHE  SCOPO DIMOSTRATIVO-------------------------------- -->
        <!--1ma forma per chiamare risorse statiche -->
        <link href="${pageContext.request.contextPath}/resources/theme1/css/1provaCss.css"  rel="stylesheet" />

        <!--2da forma per chiamare risorse statiche -->
        <link href="<c:url value="/resources/theme1/css/3provaCss.css" />" rel="stylesheet">

        <!--3za forma per chiamare risorse statiche -->
        <spring:url value="/resources/theme1/css/2provaCss.css" var="provaCss"  />
        <link href="${provaCss}" rel="stylesheet" />

        <!-- vEDERE la vista 1rounded-images per vedere come si usano i path per i vari file buono -->
        <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
            .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
            .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
            .tg .tg-4eph{background-color:#f9f9f9}


            html {
                font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen-Sans,Ubuntu,Cantarell,Helvetica Neue,sans-serif;
                font-size: 14px;
                line-height: 1.4;
                overflow-x: hidden;
            }

            body { 
                color: #393939;
                margin: 0;
                padding: 0;
                overflow-x: hidden;
            }

            * {
                box-sizing: border-box;
            }

            .div2{
                background-color: gold;
            }
        </style>

        <title>JSP Page VIEW VIDEO UPLOAD FROM USER INTO MONGODB</title>
    </head>
    <body>

        <h1>VIEW VIDEO UPLOADED</h1>

       
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


        
        
        
        <p>Video courtesy of <a href="http://localhost:8080/MavenBootstrap3Examples/init_upload_video_1" target="_blank" />UPLOAD VIDEO AGAIN
        <div  class="container"> 
            <h3>FILE LIST</h3>

            <c:if test="${!empty listaFiles}">
                <table class="tg" class="table table-striped" >
                    <tr>

                        <th width="80">_id</th>
                        <th width="120">fileName</th>
                        <th width="80">aliases</th>
                        <th width="80">chunkSize</th>
                        <th width="150">uploadData</th>
                        <th width="80">length</th>
                        <th width="120">ContentType</th>
                        <th width="120">md5</th>

                        <th width="50">Formato</th>
                        <th width="120">Output File</th>
                        <!--<th width="120">Image EncodingBase64</th>-->
                        <th width="60">Edit</th>
                        <th width="60">Delete</th>
                    </tr>
                    <c:forEach items="${listaFiles}" var="metadatoFile">
                        <tr>
                            <td>${metadatoFile.getId()}</td>
                            <td>${metadatoFile.getFilename()}</td>
                            <td>${metadatoFile.getAliases()}</td>
                            <td>${metadatoFile.getChunkSize()}</td>
                            <td>${metadatoFile.getUploadDate()}</td>
                            <td>${metadatoFile.getLength()}</td>
                            <td>${metadatoFile.getContentType()}</td>
                            <td>${metadatoFile.getMd5()}</td>

                            <td>${metadatoFile.getFormato()}</td>
                            <c:choose>
                                <c:when test="${metadatoFile.getFormato() ne null && metadatoFile.getFormato() ne 'jpg'  }">
                                    <!-- <td><img src="data:image/jpg;base64,<c :out value='$ {image.bytesFile}'/>"    width="120" height="120" /> </td> -->
                                    <td>
                                        <video width="120" height="120" controls  name="media"   >
                                            <source src="data:video/mp4;base64,<c:out value='${metadatoFile.getDato()}'/>"  
                                                    type="video/mp4" autostart="true"    >
                                            Your browser does not support the <code>video</code> tag.
                                        </video> 
                                            <!-- <embed src="data:image/mp4;base64,<c :out value='$ {metadatoFile.getDato()}'/>"  
                                                    type="video/mp4" autostart="true" type="application/x-mplayer2" 
                                                    pluginspage="http://www.microsoft.com/Windows/MediaPlayer/" 
                                                    name="mediaplayer1" ShowStatusBar="true" 
                                                    EnableContextMenu="false" width="700" height="500" 
                                                    autostart="false" loop="false" align="middle" volume="60" >
                                                        
                                             </embed>-->
                                            
                                    </td>
                                </c:when>
                                <c:otherwise> 
                                    <td><img src="data:image/jpg/png;base64,<c:out value='${metadatoFile.getDato()}'/>"    
                                             width="95" height="95" /> </td>
                                    </c:otherwise>
                                </c:choose>
                                  
                            <td><a class="btn btn-warning" href="<c:url value='/edit/file/${metadatoFile.getId()}' />" >Edit</a></td>
                            <td><a class="btn btn-warning"  href="<c:url value='/remove/file/${metadatoFile.getId()}' />" >Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

        </div>
        
        
        
        <div>
///////////////////////////////////////////////////////////////////////////////
            <c:forEach var="d" items="${dato_}">
                <div >
                    <img src="data:image/jpg;base64,<c:out value='${d}'/>"    
                                             width="95" height="95" /> </td>
                </div>
            </c:forEach>
            
        </div>
        
        
    </body> 
</html>







<!--

                                    
                                    <video width="90" height="90" controls>
                                        <source src="<c :out value='$ {image.encodingBase64}'/>" type="video/mp4"   >
                                        Your browser does not support the <code>video</code> tag.
                                    </video>
                                
                                






        <div style="text-align:center"> 
            <button onclick="playPause()">Play/Pause</button> 
            <button onclick="makeBig()">Big</button>
            <button onclick="makeSmall()">Small</button>
            <button onclick="makeNormal()">Normal</button>
            <br><br>
            <video id="video1" width="420">
                <source src="mov_bbb.mp4" type="video/mp4">
                <source src="mov_bbb.ogg" type="video/ogg">
                Your browser does not support HTML5 video.
            </video>
        </div> 

        <script>
            var myVideo = document.getElementById("video1");

            function playPause() {
                if (myVideo.paused)
                    myVideo.play();
                else
                    myVideo.pause();
            }

            function makeBig() {
                myVideo.width = 560;
            }

            function makeSmall() {
                myVideo.width = 320;
            }

            function makeNormal() {
                myVideo.width = 420;
            }
        </script> 

        <p>Video courtesy of <a href="https://www.bigbuckbunny.org/" target="_blank">Big Buck Bunny</a>.</p>
        
-->