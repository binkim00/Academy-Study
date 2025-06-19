<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/Header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>홈 - 투두 웹 프로젝트</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
<div class="main text-center mt-5">
    <h1>To-Do 웹 프로젝트</h1>
    <p class="welcome-text mt-3">
        <c:choose>
            <c:when test="${empty sessionScope.loginUser}">
                회원가입 또는 로그인을 해주세요.
            </c:when>
            <c:otherwise>
                ${sessionScope.loginUser.id}님, 환영합니다.
                <br/><br/>
                <a href="${pageContext.request.contextPath}/todo/list" class="btn btn-primary">할일 목록 보기</a>
            </c:otherwise>
        </c:choose>
    </p>
</div>
</body>
</html>
