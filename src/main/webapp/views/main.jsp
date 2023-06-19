<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>
<body>
<%@ include file="header.jsp"%>


<%--<%@ include file="header.jsp"%> 랑 동일한거임. 시작할 때 로그인화면 뜨는거--%>
<%--    <%--%>
<%--    if(session.getAttribute("uid")==null)--%>
<%--        response.sendRedirect("/login");--%>
<%--    %>--%>




  <img src="/img/ffsf.png">


    <%
        String uname = (String) request.getAttribute("uname");
        for(int i=0; i<10; i++){ %>
            <h1> <%=uname%> 환영합니다.</h1>

        <%}
    %>

    <h1><%=(String) request.getAttribute("uname")%></h1>
    <h1> ${name} 환영합니다.</h1>
    <%@ include file="todos.jsp"%>
    <%@ include file="todolist.jsp"%>
    <%@ include file="keyword.jsp"%>

  <a href="/back">back</a>
</body>
</html>