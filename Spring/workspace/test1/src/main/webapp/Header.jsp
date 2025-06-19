<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="background:#f2f2f2; padding:15px; text-align:center; font-size:16px;">
  <a href="${pageContext.request.contextPath}/" style="margin: 0 10px;">홈</a>

  <c:choose>
    <c:when test="${empty sessionScope.loginUser}">
      <a href="${pageContext.request.contextPath}/member/join" style="margin: 0 10px;">회원가입</a>
      <a href="${pageContext.request.contextPath}/member/login" style="margin: 0 10px;">로그인</a>
    </c:when>
    <c:otherwise>
      <span style="margin: 0 10px;">${sessionScope.loginUser.id}님 환영합니다</span>
      <a href="${pageContext.request.contextPath}/member/logout" style="margin: 0 10px;">로그아웃</a>
      <a href="${pageContext.request.contextPath}/member/delete" style="margin: 0 10px;">회원 탈퇴</a>
    </c:otherwise>
  </c:choose>
</div>
