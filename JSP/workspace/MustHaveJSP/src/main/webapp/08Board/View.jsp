<%@page import="model1.board.BoardDTO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String num = request.getParameter("num");

BoardDAO dao = new BoardDAO();
// 조회수 1을 증가시키는 메서드
dao.updateVisitCount(Integer.parseInt(num));
// 데이터 한건을 dto에 저장
BoardDTO dto = dao.selectView(Integer.parseInt(num));
dao.close();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 게시판 - 상세보기</title>
<script>
function deletePost(){
	// confirm 확인창에서 확인버튼을 누르면 true가 저장됨
	var confirmed = confirm("정말로 삭제하겠습니까?");
	// 확인 버튼을 눌렀을때의 처리
	if(confirmed){
		// name속성에 writeFrm가 적혀있는 폼태그를 변수에 저장
		var form = document.writeFrm;
		// 폼태그의 method를 post로 설정
		form.method = "post";
		// 폼태그의 action을 DeleteProcess.jsp로 설정
		form.action = "DeleteProcess.jsp";
		// submit버튼을 실행한 것과 같이 폼태그를 실행
		form.submit();
	}
}
</script>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>회원제 게시판 - 상세 보기(View)</h2>
	<form name="writeFrm" >
		<input type="hidden" name="num" value="<%= num %>"/>
		<table border="1" width="90%">
			<tr>
				<td>번호</td>
				<td><%=dto.getNum() %></td>
				<td>작성자</td>
				<td><%=dto.getName() %></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%=dto.getPostDate() %></td>
				<td>조회수</td>
				<td><%=dto.getVisitCount() %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3"><%=dto.getTitle() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3" height="100">
					<%=dto.getContent().replace("\r\n","<br/>") %>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<%
					if(session.getAttribute("UserId") != null 
					&& session.getAttribute("UserId").toString().equals(dto.getId())){
				%>
					<button type="button" onclick="location.href='Edit.jsp?num=<%=dto.getNum()%>';">
						수정하기
					</button>
					<button type="button" onclick="deletePost();">삭제하기</button>
				<%} %>
					<button type="button" onclick="location.href='List.jsp';">
						목록보기
					</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>






