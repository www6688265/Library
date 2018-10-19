<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 2018/9/25
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <title>list</title>
</head>
<body>
<h4>jkjfklsak</h4>
<table>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.bookid}</td>
            <td>${item.type}</td>
            <td></td>
    </tr>
    </c:forEach>

</table>
</body>
</html>
