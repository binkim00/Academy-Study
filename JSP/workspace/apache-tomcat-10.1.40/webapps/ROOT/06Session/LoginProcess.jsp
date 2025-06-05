<%@page import="membership.MemberDTO"%>     // 회원 정보를 담는 DTO 클래스 임포트
<%@page import="membership.MemberDAO"%>     // DB 접근을 위한 DAO 클래스 임포트
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 1. 로그인 폼에서 전송된 파라미터(user_id, user_pw) 값을 읽어옴
	String userId = request.getParameter("user_id");    // 폼에서 입력한 아이디
	String userPwd = request.getParameter("user_pw");    // 폼에서 입력한 비밀번호
	
    // 2. DAO 객체 생성 (DB 연결 등 내부에서 처리)
	MemberDAO dao = new MemberDAO();
	
    // 3. 전달받은 아이디와 비밀번호로 해당 회원 정보를 조회함
    //    - 일치하는 사용자가 있으면 DTO 객체에 정보가 담김
	MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
	
    // 4. DAO 사용이 끝났으므로 리소스 정리 (연결 해제 등)
	dao.close();
	
    // 5. 로그인 성공 여부 판단: memberDTO 안에 id가 null이 아니면 로그인 성공
	if(memberDTO.getId() != null){
        // ▶ 로그인 성공 시: 세션 객체에 사용자 정보 저장
		session.setAttribute("UserId", memberDTO.getId());    // 로그인한 사용자의 ID 저장
		session.setAttribute("UserName", memberDTO.getName()); // 로그인한 사용자의 이름 저장

        // ▶ 메인 페이지로 이동 (redirect 사용 시 URL이 변경됨)
		response.sendRedirect("LoginForm.jsp");
	}else{
        // ▶ 로그인 실패 시: request 영역에 오류 메시지 저장
		request.setAttribute("LoginErrMsg", "로그인 오류입니다.");

        // ▶ 다시 로그인 폼으로 이동 (forward는 URL이 유지됨)
		request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
	}
%>
