<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- JSP 페이지 설정: 자바 언어 사용, UTF-8로 인코딩 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - Application</title>
</head>
<body>

	<h2>web.xml에 설정한 내용 읽어오기</h2>
	<!-- 
	     application 객체는 ServletContext의 인스턴스이며, 
	     web.xml에 정의된 context-param을 읽을 수 있다.
	     getInitParameter("파라미터명")으로 접근
	 -->
	초기화 매개변수 : <%= application.getInitParameter("INIT_PARAM") %>
	
	<h2>서버의 물리적 경로 얻어오기</h2>
	<!-- 
	     getRealPath("가상경로") : 서버에 배포된 실제 경로(물리적 경로)를 반환함
	     예: /02ImplicitObject → C:/프로젝트경로/webapp/02ImplicitObject
	     파일 업로드나 경로 접근할 때 유용함
	 -->
	application 내장 객체 : <%= application.getRealPath("/02ImplicitObject") %>
	
	<h2>선언부에서 application 내장 객체 사용하기</h2>
	<%! 
		// 선언부: JSP가 서블릿으로 변환될 때 멤버변수나 메서드로 포함됨

		// 1. this 사용: 현재 JSP를 서블릿으로 본다면 this.getServletContext() 가능
		public String useImplicitObject(){
			return this.getServletContext().getRealPath("/02ImplicitObject");		
		}

		// 2. application 객체를 매개변수로 전달받아 사용하는 방식
		//    재사용성이 높고 테스트에도 유리함
		public String useImplicitObject(ServletContext app){
			return app.getRealPath("/02ImplicitObject");
		}
	%>
	
	<ul>
		<!-- 위에서 선언한 메서드를 호출하여 경로 출력 -->
		<li>this 사용 : <%= useImplicitObject() %></li>
		<li>내장 객체를 인수로 전달 : <%= useImplicitObject(application) %></li>
	</ul>
	
</body>
</html>
