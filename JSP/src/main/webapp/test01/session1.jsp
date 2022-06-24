<%--
  Created by IntelliJ IDEA.
  User: tlseh
  Date: 2022-06-24
  Time: 오후 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String name = (String)session.getAttribute("name");
    session.setAttribute("address","서울시 송파구 문정동 송파아이파크");
%>
<html>
<head>
    <title>session 내장 객체 테스트</title>
</head>
<body>
이름은 <%=name %> 입니다. <br>
<a href = session2.jsp>두번째 페이지로 이동</a>
</body>
</html>
