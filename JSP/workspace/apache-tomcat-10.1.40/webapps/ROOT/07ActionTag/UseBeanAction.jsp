<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>액션 태그 - UseBean</title></head>
<body>

    <h3>액션 태그로 폼값 한 번에 받기</h3>

    <%-- 
        <jsp:useBean> : 자바빈즈 객체를 생성하거나 기존에 존재하는 객체를 재사용합니다.
        여기서는 common.Person 클래스를 기반으로 "person"이라는 이름의 객체를 생성합니다.
    --%>
    <jsp:useBean id="person" class="common.Person" />

    <%-- 
        <jsp:setProperty property="*" name="person" /> :
        폼에서 전달된 모든 파라미터를 자바빈즈에 자동으로 매핑해주는 기능입니다.

        - property="*" 은 '모든 속성'이라는 뜻.
        - 전송된 파라미터의 name과 Person 클래스의 멤버 변수 이름이 같으면
          자동으로 setter 메서드(setXxx)를 호출해 값을 설정합니다.

        📌 이 방식은 일일이 setProperty를 나열할 필요 없이 자동 매핑이 되어 편리함!
        📌 전제 조건: 폼에서 전달되는 name 속성과 Person 클래스의 변수명이 같아야 함
    --%>
    <jsp:setProperty property="*" name="person" />

    <%-- 값 출력 --%>
    <ul>
        <%-- person.getName()을 호출한 결과가 HTML로 출력됨 --%>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li>  

        <%-- person.getAge()을 호출한 결과가 출력됨 --%>
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
    </ul>

</body>
</html>
