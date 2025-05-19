<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="member.dto.MemberDTO, member.dao.MemberDAO" %>
<%
    request.setCharacterEncoding("UTF-8");

    String email = request.getParameter("email1") + "@" + request.getParameter("email2");
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String phone = request.getParameter("phone");
    String gender = request.getParameter("gender");
    int agree = request.getParameter("agree") != null ? 1 : 0;
    String content = request.getParameter("content");

    MemberDTO dto = new MemberDTO();
    dto.setId(email);
    dto.setEmail(email);
    dto.setName(name);
    dto.setPassword(password);
    dto.setPhone(phone);
    dto.setGender(gender);
    dto.setAgree(agree);
    dto.setContent(content);

    MemberDAO dao = new MemberDAO();
    int result = dao.insertMember(dto);
    dao.close();

    if (result > 0) {
        System.out.println("회원가입 성공!");
        response.sendRedirect("login.jsp");
    } else {
        System.out.println("회원가입 실패!");
        out.println("<script>alert('회원가입 실패. 다시 시도해주세요.'); history.back();</script>");
    }
%>
