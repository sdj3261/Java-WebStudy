<%--
  Created by IntelliJ IDEA.
  User: tlseh
  Date: 2022-06-24
  Time: 오후 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    String name = (String)session.getAttribute("name");
    String address = (String)session.getAttribute("address");
%>
<html>
<head>
    <title>내장 객체 테스트2</title>
</head>
<body>
이름은 <%=name %> 입니다.
주소는 <%= address %> 입니다.
</body>
</html>
