<%-- 
    Document   : 1dropdowns-menu
    Created on : 18-gen-2018, 16.40.18
    Author     : Marco

<!-- http://www.html.it/pag/18702/datepicker/
            Molto probabilmente ognuno di noi si sarà imbattuto, durante la compilazione di un form, 
            in spiegazioni ed etichette di campo del tipo gg/mm/aaaa oppure gg/mm/aa. 
            Altrettanto spesso si sarà trovato di fronte a tre menu a tendina dai quali dover scegliere 
            giorno, mese ed anno, magari chiedendosi che giorno della settimana sarà il 14 Aprile 2010.
            Tutti questi passaggi, controlli, etichette e spiegazioni mettono in evidenza come sia difficile 
            lavorare in maniera intuitiva e diretta con le date.
            La risposta di jQuery UI al problema è il widget datepicker che in italiano definiremmo calendario
            L’utilizzo principale di questo tipo di controllo è da una parte quello di facilitare l’utente 
            nell’inserimento di date in vario formato, dall’altro quello di ridurre al minimo errori nella 
            formattazione delle date stesse.
            Nella sua forma più semplice, il widget è attivabile su ogni elemento di input testuale 
            selezionabile con jQuery (esempio):
        $(".data:input").datepicker();
         -->


--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <!-- Bootstrap CDN
            Se non si desidera scaricare e ospitare da soli Bootstrap, 
            è possibile includerlo da un CDN (Content Delivery Network). 
            MaxCDN fornisce supporto CDN per CSS e JavaScript di Bootstrap. 
            Devi anche includere jQuery
        -->
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

   <!--  ---------------------RInizio ichiamo il Boostrap scaricato con MAVEN------------------------   -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 
        <!--  ---------------------fine Richiamo il Boostrap scaricato con MAVEN------------------------   -->

    </head>
    <body>


        <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                Dropdown
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Separated link</a></li>
            </ul>
        </div>

        
        ================================================
        ================================================
        <div class="container">
          <h2>Dropdowns</h2>
          <p>The .dropdown class is used to indicate a dropdown menu.</p>
          <p>Use the .dropdown-menu class to actually build the dropdown menu.</p>
          <p>To open the dropdown menu, use a button or a link with a class of .dropdown-toggle and data-toggle="dropdown".</p>                                          
          <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example
            <span class="caret"></span></button>
            <ul class="dropdown-menu">
              <li><a href="#">HTML</a></li>
              <li><a href="#">CSS</a></li>
              <li><a href="#">JavaScript</a></li>
            </ul>
          </div>
        </div>


        <div class="container">
        <table class="table table-striped">
            <caption>Your todos are</caption>
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is it Done?</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>Todo 1</td>
                        <td>10/12/2017</td>
                        <td>No</td>
                        <td><a class="btn btn-warning" href="/edit-todo">Edit Todo</a></td>
                        <td><a class="btn btn-warning" href="/delete-todo">Delete Todo</a></td>
                    </tr>
            </tbody>
        </table>
        <div>
            <a class="btn btn-default" href="/add-todo">Add a Todo</a>
        </div>
        
    </div>

        <DIV>  <%@include file="1style_footer.jsp" %> </DIV>
    </body>
</html>
