<%--
  Created by IntelliJ IDEA.
  User: tlseh
  Date: 2022-06-24
  Time: 오후 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
============toString 내용 ====== <br>
<h1><%=exception.toString()%></h1>
============getmessage 내용 ====== <br>
<h1><%=exception.getMessage()%></h1>
============print stack trace 내용 ====== <br>
<h1><% exception.printStackTrace(); %></h1>
<H3>숫자만 입력 가능합니다. 다시 시도하세요.</H3>
<a href='add.html'>다시하기</a>
</body>
</html>
