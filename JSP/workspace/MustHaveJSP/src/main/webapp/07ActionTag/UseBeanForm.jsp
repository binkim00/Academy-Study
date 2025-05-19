<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>액션 태그 - UseBean</title></head>
<body>

    <h2>액션 태그로 폼값 한 번에 받기</h2> 

    <%-- 
        사용자로부터 이름(name)과 나이(age)를 입력받는 HTML 폼입니다.
        입력한 값은 POST 방식으로 'UseBeanAction.jsp'로 전송됩니다.
        이때 name 속성의 값(name, age)은 자바빈즈의 멤버 변수와 동일해야
        <jsp:setProperty property="*">로 자동 대입이 가능합니다.
    --%>
    <form method="post" action="UseBeanAction.jsp"> 

        <%-- 이름 입력란: 전송될 파라미터 이름은 name="name" 입니다. --%>
        이름 : <input type="text" name="name" /> <br /> 

        <%-- 나이 입력란: 전송될 파라미터 이름은 name="age" 입니다. --%>
        나이 : <input type="text" name="age" /> <br /> 

        <%-- 전송 버튼을 누르면 action 경로(UseBeanAction.jsp)로 데이터가 전송됩니다. --%>
        <input type="submit" value="폼값 전송" />

    </form>
    
</body>
</html>
