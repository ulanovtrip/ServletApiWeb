<%@ page import="ru.itis.site.dto.AccountDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: uis
  Date: 06.06.2021
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>USERS</h1>

<table>
    <tr>
        <th>ID</th>
        <th>FIRST NAME</th>
        <th>LAST NAME</th>
        <th>EMAIL</th>
    </tr>

    <%-- здесь можно писать java код--%>
    <%
        List<AccountDto> accountDtos = (List<AccountDto>) request.getAttribute("accounts");

        for (AccountDto accountDto : accountDtos) {
    %>
    <tr>
        <td><%=accountDto.getId()%>
        </td>
        <td><%=accountDto.getFirstName()%>
        </td>
        <td><%=accountDto.getLastName()%>
        </td>
        <td><%=accountDto.getEmail()%>
        </td>
    </tr>
    <%}%>

</table>

</body>
</html>
