<%@ page import="board.dao.BoardDAO, board.dto.BoardDTO, member.dto.MemberDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%
    request.setCharacterEncoding("UTF-8");

    String title = request.getParameter("title");
    String content = request.getParameter("contents");

    MemberDTO user = (MemberDTO)session.getAttribute("userDTO");

    if (user == null) {
        out.println("<script>alert('로그인 후 이용해 주세요.'); location.href='../LoginForm.jsp';</script>");
        return;
    }

    String id = user.getId();

    BoardDTO dto = new BoardDTO();
    dto.setTitle(title);
    dto.setContent(content);
    dto.setId(id);

    BoardDAO dao = new BoardDAO();
    int result = dao.insertWrite(dto);
    dao.close();
%>

<%
    if (result == 1) {
%>
    <script>
        alert("작성 완료되었습니다.");
        location.href = "notice_list.jsp";
    </script>
<%
    } else {
%>
    <script>
        alert("글 등록 실패");
        history.back();
    </script>
<%
    }
%>
