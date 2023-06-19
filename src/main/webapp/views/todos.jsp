<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ToDos</title>
</head>
<body>
<%--로그인 안되어 있으면 로그인 화면 으로 가라--%>
<%--    <%--%>
<%--        if(session.getAttribute("uid")==null)--%>
<%--            response.sendRedirect("/login");--%>
<%--    %>--%>
<%-- 요거 지우기 --%>

    <div>
<!--        method=post로 toPost로 던지는걸 해줘야함-->
        <form action="/todoinsert" method="post">
            <input type="text" name="content" placeholder="컨텐츠를 입력해주세요">
            <input type="submit">
        </form>
    </div>
</body>
</html>