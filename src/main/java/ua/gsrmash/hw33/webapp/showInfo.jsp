<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Summary info</title>
    <style>
       td {
        padding: 5px;
       }
    </style>
</head>
<body>
<section>
    <h1 style="color:#0033ff">Summary requests information</h1>
    <jsp:useBean id="infoList" type="java.util.ArrayList" scope="request"/>
    <table border="1">
        <tr>
            <th><i>IP</i></th>
            <th><i>User-Agent</i></th>
            <th><i>Time</i></th>
        </tr>
        <c:forEach items="${infoList}" var="info">
            <tr>
                <td><b>${info.ip}</b></td>
                <td><b>${info.userAgent}</b></td>
                <td>${info.requestTime}</td>
            </tr>
        </c:forEach>
    </table>
</section>
<div style="margin-left: 5%; padding: 20px;">
    <INPUT TYPE="button" VALUE="Back" onClick="history.go(-1);">
</div>
</body>
</html>


