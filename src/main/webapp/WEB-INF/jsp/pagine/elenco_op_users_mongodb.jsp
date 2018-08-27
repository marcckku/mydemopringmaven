<%-- 
<%-- 
  Document   : elenco_op_users_mongodb
  Created on : 31-mag-2018, 14.59.58
  Author     : Utente
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page Elenco Users_ From Mongodb</title>
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


        </style>


    </head>
    <body>
        <h1>
            OPERAZIONI SU GLI USERS PRESENTI NEL DATABASE
        </h1>

    <c:url var="addAction" value="/contollerUser/add/oneUser_" ></c:url>


    <form:form action="${addAction}" commandName="oneUser_" method="post" >
        <table>
            <c:if test="${empty user_.name}">
                <tr>
                    <td>
                <form:label path="seq"><spring:message text="SEQ"/></form:label>
                </td>
                <td>
                <form:input path="seq" readonly="true" size="8"  disabled="true" />
                <form:hidden path="seq" />
                </td> 
                </tr>
            </c:if>
            <tr>
                <td>
            <form:label path="name"><spring:message text="NAME"/></form:label>
            </td>
            <td>
            <form:input path="name" />
            </td> 
            </tr>
            <tr>
                <td>
            <form:label path="email"><spring:message text="EMAIL"/></form:label>
            </td>
            <td>
            <form:input path="email" />
            </td>
            </tr>
            <tr>
            <td colspan="2">
                <c:if test="${empty user_.name}">
                    <input type="submit" value="<spring:message text="Edit USER_"/>" />
                </c:if>
                <c:if test="${empty user_.email}">
                    <input type="submit" value="<spring:message text="Add USER_"/>" />
                </c:if>
            </td>
            </tr>
        </table>	
    </form:form>
    <br>
    <h3>User_ List</h3>
    <c:if test="${!empty listaUsers_}">
        <table class="tg">
            <tr>
                <th width="80">User_ seq</th>
                <th width="120">User_ Name</th>
                <th width="120">User_ Email</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listaUsers_}" var="user_">
                <tr>
                    <td>${user_.seq}</td>
                    <td>${user_.name}</td>
                    <td>${user_.email}</td>
                    <td><a href="<c:url value='/edit/${user_.seq}' />" >Edit</a></td>
                    <td><a href="<c:url value='/remove/${user_.seq}' />" >Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>

