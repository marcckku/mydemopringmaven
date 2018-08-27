<%-- 
    Document   : 1navbar-dropdown-menu
    Created on : 18-gen-2018, 17.05.04
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
         <title>Navbar-Drop-Menu</title>
        <!--  Access the bootstrap Css like.  
         vai a questo link per capire meglio= 
        https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-jsp/  -->
        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


        
        
        <style>
            .dropbtn {
                background-color: #cccccc;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {background-color: #f1f1f1}

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #3e8e41;
            }
        </style>
        
        
    </head>
    <body>
        
        
        
        
        
        
        <nav id="mainNav" class="navbar navbar-inverse" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" s> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                <a href="#" class="navbar-brand">WOW</a> </div>
            <div class="collapse navbar-collapse" id="mainMenu">
                

                <div class="dropdown">
                    <button class="dropbtn">Dropdown</button>
                    <div class="dropdown-content">
                        <a href="http://localhost:8080/MavenBootstrap3Examples/1dropdowns_menu">pag1</a>
                        <a href="http://localhost:8080/MavenBootstrap3Examples/2dropdowns_menu">pag2</a>
                        <a href="#">Link 1</a>
                        <a href="#">Link 2</a>
                        <a href="#">Link 3</a>
                    </div>
                </div>
                
                <ul class="nav navbar-nav">
                    <li><a href="#">Chi siamo</a></li>
                    <li class="dropdown"> 
                        <a class="dropdown-toggle" data-toggle="dropdown" 
                           role="button" aria-expanded="false" 
                           href="#">Servizi <span class="caret"></span></a>
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


        
        
          <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
         <link rel="stylesheet" href="webjars/bootstrap/3.3.7/js/bootstrap.min.js" />
        
    </body>
</html>
