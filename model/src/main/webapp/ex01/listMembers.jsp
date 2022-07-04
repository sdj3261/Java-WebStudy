<%--
  Created by IntelliJ IDEA.
  User: tlseh
  Date: 2022-07-02
  Time: 오후 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import =" java.util.*,ex01.* "
         pageEncoding ="UTF-8"
         isELIgnored="false"
%>

<%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
    request.setCharacterEncoding("utf-8");
%>
<html>
<head>
    <c:choose>
    <c:when test = '${msg == "addMember"}'>
        <script>
            window.onload = function () {
                alert("회원을 등록했습니다.");
            }
        </script>
    </c:when>
    <c:when test = '${msg == "modified"}'>
        <script>
            window.onload = function () {
                alert("회원을 수정했습니다.");
            }
        </script>
    </c:when>
    <c:when test = '${msg == "deleted"}'>
        <script>
            window.onload = function () {
                alert("회원을 삭제했습니다.");
            }
        </script>
    </c:when>
    </c:choose>
    <title>회원정보출력창</title>
    <style>
        .cls1 {
            font-size : 40px;
            text-align : center;
        }
        .cls2 {
            font-size : 20px;
            text-align : center;
        }
    </style>
</head>
<body>
<p class = "cls1">회원정보</p>
<table align = "center" border = "2">
<tr align = "center" bgcolor = "lightgreen">
    <td width = "7%"><b>아이디</b></td>
    <td width = "7%"><b>비밀번호</b></td>
    <td width = "7%"><b>이름</b></td>
    <td width = "7%"><b>이메일</b></td>
    <td width = "7%"><b>가입일</b></td>
</tr>
    <c:choose>
        <c:when test = "${ empty membersList}">
            <tr>
                <td colspan = 5>
                    <b>등록된 회원이 없습니다.</b>
                </td>
            </tr>
        </c:when>
        <c:when test = "${!empty membersList}">
            <c:forEach var="memInfo" items="${membersList}">
            <tr align = "center">
                <td>${memInfo.id}</td>
                <td>${memInfo.pwd}</td>
                <td>${memInfo.name}</td>
                <td>${memInfo.email}</td>
                <td>${memInfo.joinDate}</td>
                <td><a href =${contextPath}/member/modMemberForm.do?id=${memInfo.id }"> 수정</a></td>
                <td><a href =${contextPath}/member/delMember.do?id=${memInfo.id }"> 삭제</a></td>
            </tr>
        </c:forEach>
        </c:when>
    </c:choose>
</table>
<a href = "${contextPath}/ex01/memberForm.jsp">
    <p class = "cls2"> 회원 가입하기</p>
</a>
</body>
</html>
