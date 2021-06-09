<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: uis
  Date: 06.06.2021
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>

<body>

<table>

    <tr>
        <th>ID</th>
        <th>FIRST NAME</th>
        <th>LAST NAME</th>
        <th>EMAIL</th>
    </tr>


    <%--Пример кода с использованием JSTL--%>
    <c:forEach items="${accounts}" var="account">
        <tr>
                <%--<c:set> - конвертация из Long -> String--%>
                <%--<td><c:set>${account.id}</c:set>></td>--%>
            <td>${account.id}</td>
            <td>${account.firstName}</td>
            <td>${account.lastName}</td>
            <td>${account.email}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
