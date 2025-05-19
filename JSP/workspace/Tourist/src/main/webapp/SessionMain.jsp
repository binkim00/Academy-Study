<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String userId = (String) session.getAttribute("UserId");
    String userName = (String) session.getAttribute("UserName");

    if (userId == null) {
        response.sendRedirect("LoginForm.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Session Main</title>
</head>
<body>

  <h2>로그인 성공 🎉</h2>

  <p><strong><%= userName %></strong> 님 환영합니다!</p>
  <p>당신의 아이디: <%= userId %></p>

  <hr/>

  <p><a href="Logout.jsp">[로그아웃]</a></p>

</body>
</html>
