<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session LoginForm</title>
</head>
<body>
	<!-- 공통 링크(스타일, 메뉴 등) 포함. 보통 head 안에 써도 되고, 여긴 body에 있음 -->
	<jsp:include page="../Common/Link.jsp" />

	<h2>로그인 페이지</h2>

	<span style="color:red; font-size:1.2em;">
		<!-- 
			request 영역에 LoginErrMsg라는 속성이 있다면 에러 메시지를 출력,
			없으면 빈 문자열 출력. 
			→ forward 방식으로 이동한 경우에만 request 값이 유지됨
		-->
		<%=request.getAttribute("LoginErrMsg") == null ? "" : request.getAttribute("LoginErrMsg") %>
	</span>

	<% 
	// ▶ 세션에 UserId가 없으면 로그인 상태가 아님 → 로그인 폼 출력
	if(session.getAttribute("UserId") == null){ 
	%>
		<script>
		// ▶ form 제출 전에 입력값이 비었는지 검사하는 함수
		function validateForm(form){
			// 아이디가 입력되지 않은 경우
			if(!form.user_id.value){
				alert("아이디를 입력하세요.");
				// false를 반환하면 form 제출이 중단됨 (LoginProcess.jsp로 이동 안 함)
				return false;
			}
			// 비밀번호가 입력되지 않은 경우
			if(form.user_pw.value==""){
				alert("패스워드를 입력하세요.");
				return false;
			}
		}
		</script>

		<!-- 
			onsubmit 이벤트에서 validateForm 함수를 실행함.
			→ 이 함수에서 false를 반환하면 폼 제출이 되지 않음 (서버로 전송 중지됨)
		-->
		<form action="LoginProcess.jsp" method="post" name="loginFrm"
		onsubmit="return validateForm(this);">
			아이디 : <input type="text" name="user_id" /> <br/>
			패스워드 : <input type="password" name="user_pw"/><br/>
			<input type="submit" value="로그인하기" />
		</form>

	<%}else{ %>
		<!-- 
			세션에 UserId가 존재한다면 로그인 상태이므로,
			폼 대신 로그인된 사용자 이름과 로그아웃 링크를 보여줌
		-->
		<%=session.getAttribute("UserName")%>회원님, 로그인하셨습니다.<br/>
		<a href="Logout.jsp">[로그아웃]</a>
	<%} %>
</body>
</html>
