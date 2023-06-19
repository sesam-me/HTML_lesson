<%--
  Created by IntelliJ IDEA.
  User: Playdata
  Date: 2023-06-19
  Time: 오후 5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String uri = request.getRequestURI();
  if(uri.equals("/login") || uri.equals("/user")){
  }else {
//    세션이 없으면 login으로 보내라
    if (session.getAttribute("uid") == null) response.sendRedirect("/login");
  }
%>
<table>
  <tr>
    <td><a href="/main">main</a></td>
    <td><a href="/login">login</a></td>
  </tr>
</table>
