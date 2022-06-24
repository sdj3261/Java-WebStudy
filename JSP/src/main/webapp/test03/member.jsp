<%--
  Created by IntelliJ IDEA.
  User: tlseh
  Date: 2022-06-24
  Time: 오후 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
         import ="java.util.*"
         import ="test03.*"
%>
<html>
<head>
    <style>
        h1 {
            text-align: center;
        }
    </style>
    <title>회원 정보 출력</title>
</head>
<body>
<H1>회원 정보 출력</H1>
<%
    request.setCharacterEncoding("UTF-8");
    String _name = request.getParameter("name");
    MemberVO memberVO = new MemberVO();
    memberVO.setName(_name);
    MemberDAO dao = new MemberDAO();
    List membersList = dao.listMembers(memberVO);
%>
<table border = 1 width=800 align = center>
    <Tr align = center bgcolor = "#ffff66">
        <td>ID</td>
        <td>비밀번호</td>
        <td>이름</td>
    <td>이메일</td>
    <td>가입일자</td>
    </Tr>

    <%
        for (int i=0; i< membersList.size(); i++) {
            MemberVO vo = (MemberVO) membersList.get(i);
            String id = vo.getId();
            String pwd = vo.getPwd();
            String name = vo.getName();
            String email = vo.getEmail();
            Date joinDate = vo.getJoinDate();
    %>

    <tr align = center>
        <Td><%= id %></Td>
        <td><%=  pwd %></td>
        <td><%= name %></td>
        <td><%= email %></td>
        <td><%= joinDate %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
