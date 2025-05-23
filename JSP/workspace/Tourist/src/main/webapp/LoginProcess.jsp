<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.dao.MemberDAO, member.dto.MemberDTO" %>
<%
    // 로그인 요청 파라미터 받기
    String id = request.getParameter("id");
    String password = request.getParameter("password");

    // DAO를 이용한 로그인 검증
    MemberDAO dao = new MemberDAO();
    MemberDTO userDTO = dao.getMemberDTO(id, password);  // ▶ 로그인 성공 시 DTO 반환
    dao.close();

    if (userDTO != null) {
        // ✅ 로그인 성공 → 세션에 DTO 저장
        session.setAttribute("userDTO", userDTO);
        session.setAttribute("userId", userDTO.getId());
        response.sendRedirect("index.jsp"); // 또는 원하는 페이지로 이동
    } else {
%>
        <script>
            alert("아이디 또는 비밀번호가 틀렸습니다.");
            history.back();
        </script>
<%
    }
%>
