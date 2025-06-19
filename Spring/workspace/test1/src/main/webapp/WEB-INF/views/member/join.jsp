<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/Header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/join.css">
</head>
<body>
<script>
  function checkPasswordsMatch(e) {
    const pw = document.getElementById('pw').value;
    const pwConfirm = document.getElementById('pwConfirm').value;
    if (pw !== pwConfirm) {
      alert('비밀번호가 일치하지 않습니다.');
      e.preventDefault();
    }
  }
</script>
<div class="container">
  <h2>회원가입</h2>
  <form action="${pageContext.request.contextPath}/member/join" method="post">
    <label for="id">아이디</label>
    <input type="text" name="id" id="id" required />

    <label for="pw">비밀번호</label>
    <input type="password" name="pw" id="pw" required />

    <label for="pwConfirm">비밀번호 확인</label>
    <input type="password" name="pwConfirm" id="pwConfirm" required />

    <label for="email">이메일</label>
    <div class="email-group">
      <input type="text" name="email1" placeholder="이메일 앞부분" required />
      <span>@</span>
      <input type="text" name="email2" placeholder="도메인" required />
    </div>

    <button type="submit">가입하기</button>
  </form>
</div>
</body>
</html>
