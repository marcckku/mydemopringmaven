<%-- 
    Document   : input-sizing-1
    Created on : 24-gen-2018, 15.18.06
    Author     : Marco
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <title>INPUT-SIZING</title>
    </head>
    <body>
        <div class="container">
  <h2>Input Sizing</h2>
  <p>The form below shows input elements with different heights using .input-lg and .input-sm:</p>
  <form>
    <div class="form-group">
      <label for="inputdefault">Default input</label>
      <input class="form-control" id="inputdefault" type="text">
    </div>
    <div class="form-group">
      <label for="inputlg">input-lg</label>
      <input class="form-control input-lg" id="inputlg" type="text">
    </div>
    <div class="form-group">
      <label for="inputsm">input-sm</label>
      <input class="form-control input-sm" id="inputsm" type="text">
    </div>
    <div class="form-group">
      <label for="sel1">Default select list</label>
      <select class="form-control" id="sel1">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
      </select>
    </div>
    <div class="form-group">
      <label for="sel2">input-lg</label>
      <select class="form-control input-lg" id="sel2">
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
    </div>
    <div class="form-group">
      <label for="sel3">input-sm</label>
      <select class="form-control input-sm" id="sel3">
        <option>1</option>
        <option>2</option>
        <option>3</option>
      </select>
    </div>
  </form>
</div>

    </body>
</html>
