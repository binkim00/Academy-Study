<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/Header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/join.css">
</head>
<body>
<div class="container">
    <h2>회원 탈퇴</h2>
    <p style="color: red; text-align: center;">정말로 탈퇴하시겠습니까?<br>탈퇴 시 작성하신 모든 할 일이 삭제됩니다.</p>
    <form action="${pageContext.request.contextPath}/member/delete" method="post" style="text-align: center;">
        <button type="submit" style="background-color: crimson;">회원 탈퇴</button>
    </form>
</div>
</body>
</html>
