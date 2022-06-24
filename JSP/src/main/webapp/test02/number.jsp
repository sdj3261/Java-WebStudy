<%--
  Created by IntelliJ IDEA.
  User: tlseh
  Date: 2022-06-24
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    int num = Integer.parseInt(request.getParameter("num"));
%>
<html>
<head>
    <title>에러페이지 테스트</title>
</head>
<body>
<h1><%=num %> 출력 jsp</h1>

</body>
</html>
