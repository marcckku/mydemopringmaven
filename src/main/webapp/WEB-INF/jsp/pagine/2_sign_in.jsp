<%-- 
    Document   : 2_sign_in
    Created on : 21-mag-2018, 16.35.53
    Author     : Utente

https://bootsnipp.com/user/snippets/N6RkO 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--  Access the bootstrap -->
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 

      
        <!--
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/4.3.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/dist/bootsnipp.min.css">
        -->
        
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <style>
            @charset "utf-8";


            @import url//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css);



            div.main{
                background: #0264d6; /* Old browsers */
                background: -moz-radial-gradient(center, ellipse cover,  #0264d6 1%, #1c2b5a 100%); /* FF3.6+ */
                background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(1%,#0264d6), color-stop(100%,#1c2b5a)); /* Chrome,Safari4+ */
                background: -webkit-radial-gradient(center, ellipse cover,  #0264d6 1%,#1c2b5a 100%); /* Chrome10+,Safari5.1+ */
                background: -o-radial-gradient(center, ellipse cover,  #0264d6 1%,#1c2b5a 100%); /* Opera 12+ */
                background: -ms-radial-gradient(center, ellipse cover,  #0264d6 1%,#1c2b5a 100%); /* IE10+ */
                background: radial-gradient(ellipse at center,  #0264d6 1%,#1c2b5a 100%); /* W3C */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#0264d6', endColorstr='#1c2b5a',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
                height:calc(100vh);
                width:100%;
            }

            [class*="fontawesome-"]:before {
                font-family: 'FontAwesome', sans-serif;
            }

            /* ---------- GENERAL ---------- */

            * {
                box-sizing: border-box;
                margin:0px auto;

                &:before,
                    &:after {
                    box-sizing: border-box;
                }

            }

            .body {

                color: #606468;
                font: 99.5%/8.5em 'Open Sans', sans-serif;
                margin: 0;
            }

            a {
                color: #eef;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }

            input {
                border: none;
                font-family: 'Open Sans', Arial, sans-serif;
                font-size: 13px;
                line-height: 1.5em;
                padding: 0;
                -webkit-appearance: none;
            }

            p {
                line-height: 1.5em;
            }

            .clearfix {
                *zoom: 1;

                &:before,
                    &:after {
                    content: ' ';
                    display: table;
                }

                &:after {
                    clear: both;
                }

            }

            .container {
                left: 50%;
                position: fixed;
                top: 50%;
                transform: translate(-50%, -50%);
            }

            /* ---------- LOGIN ---------- */

            #login form{
                width: 252px;
            }
            #login, .logo{
                display:inline-block;
                width:90%;
            }
            #login{
                border-right:3px solid #fff;
                padding: 100px 22px;
                width: 89%;
                background-color:  blue;
            }
            .logo{
                color:#fffee;
                font-size:120px;
                line-height: 250px;
                background-color:  greenyellow;
            }

            #login form span.fa {
                background-color: #fff;
                border-radius: 900px 11px 11px 900px;
                color: #0f00ee;
                display: block;
                float: left;
                height: 50px;
                font-size:23px;
                line-height: 50px;
                text-align: center;
                width: 50px;
            }

            #login form input {
                height: 50px;
            }
            fieldset{
                padding:0;
                border:0;
                margin: 0;

            }
            /**  **/
            #login form input[type="text"], input[type="password"] {
                background-color: #fff0ee;
                border-radius: 2px 10px 10px 2px;
                color: black;
                margin-bottom: 1em;
                padding: 0 12px;
                width: 200px;
            }

            #login form input[type="submit"] {
                border-radius: 6px;
                -moz-border-radius: 6px;
                -webkit-border-radius: 6px;
                background-color: #0000fe;
                color: #eee;
                font-weight: bold;
                /* margin-bottom: 2em; */
                text-transform: uppercase;
                padding: 1px 35px;
                height: 20px;
            }

            #login form input[type="submit"]:hover {
                background-color: green;
            }

            #login > p {
                text-align: center;
            }

            #login > p span {
                padding-left: 5px;
            }
            .middle {
                display: flex;
                width: 650px;
            }


        </style>

        <title>SIGN IN 2</title>
    </head>
    <body   class="body" >
        <h1>SIGN IN 2!</h1>

        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">




        <div class="container">
            <center>
                <div class="middle">
                    <div id="login">

                        <form action="javascript:void(0);" method="get">

                            <fieldset class="clearfix">

                                <p ><span class="fa fa-user"></span><input type="text"  Placeholder="Username" required></p> <!-- JS because of IE support; better: placeholder="Username" -->
                                <p><span class="fa fa-lock"></span><input type="password"  Placeholder="Password" required></p> <!-- JS because of IE support; better: placeholder="Password" -->

                                <div>
                                    <span style="width:48%; text-align:left;  display: inline-block;"><a class="small-text" href="#">Forgot
                                            password?</a></span>
                                    <span style="width:50%; text-align:right;  display: inline-block;"><input type="submit" value="Sign In"></span>
                                </div>

                            </fieldset>
                            <div class="clearfix"></div>
                        </form>

                        <div class="clearfix"></div>

                    </div> <!-- end login -->
                    <div class="logo"> 
                         
                        <div> <img src="${pageContext.request.contextPath}/resources/theme1/images/sagomaRagazzo.png"  alt=""/> ME</div>
                       
                        <div class="clearfix">
                            
                            
                            
                        </div>
                    </div>
                      
                </div>
            </center>
        </div>




        <script type="text/javascript" src="webjars/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
