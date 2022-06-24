<%--
  Created by IntelliJ IDEA.
  User: tlseh
  Date: 2022-06-24
  Time: 오후 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         errorPage="addException.jsp"
%>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    int sum = 0;

    for(int i=1; i<= num; i++) {
        sum = sum + i;
    }
%>
<html>
<head>
    <title>계산하기</title>
</head>
<body>
<h2>합계구하기</h2>
<h4>1부터 <%=num %>까지의 합은 <%=sum%> 입니다. </h4>
</body>
</html>
