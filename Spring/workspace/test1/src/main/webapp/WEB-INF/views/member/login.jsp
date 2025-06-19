<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/Header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/join.css">
</head>
<body>
<div class="container">
  <h2>로그인</h2>
  <form action="${pageContext.request.contextPath}/member/login" method="post">
    <label for="id">아이디</label>
    <input type="text" name="id" id="id" required />

    <label for="pw">비밀번호</label>
    <input type="password" name="pw" id="pw" required />

    <button type="submit">로그인</button>
  </form>

  <c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
  </c:if>
</div>
</body>
</html>
