<%@page import="utils.BoardPage"%>
<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 게시물 개수와 게시물 데이터를 받아오기 위한 DAO 생성
	BoardDAO dao = new BoardDAO();
	// 검색 조건 및 검색어를 저장하는 Map을 생성
	Map<String, Object> param = new HashMap<>();
	// 제목이나 내용으로 검색할지 설정하는 데이터 searchField를 저장
	String searchField = request.getParameter("searchField");
	// 검색할 내용을 설정하는 데이터 searchWord를 저장
	String searchWord = request.getParameter("searchWord");
	// 검색어가 null이 아닐때만 map에 데이터를 저장
	if(searchWord != null && searchWord.length() > 0){
		param.put("searchField", searchField);
		param.put("searchWord", searchWord);
	}
	// 총 데이터 개수를 저장 - 총 페이지의 개수를 구하기 위하여
	int totalCount = dao.selectCount(param);
	
	// 한 페이지에 출력할 데이터 개수 
	int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
	// 페이지 블럭에 표시될 페이지의 개수
	int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
	// 전체 페이지의 개수 = 111/10의 올림 12
	int totalPage = (int)Math.ceil((double)totalCount/pageSize);
	
	// 현재 페이지 설정
	int pageNum = 1;
	// 클릭한 페이지를 의미하는 변수
	String pageTemp = request.getParameter("pageNum");
	// 클릭한 페이지가 있으면 현재 페이지를 변경하고 없으면 1페이지로 설정
	if(pageTemp != null && !pageTemp.equals("")){
		pageNum = Integer.parseInt(pageTemp);
	}
	
	//목록에 출력할 게시물의 범위 계산
	// 현재 페이지가 2페이지라면 2-1*10+1 = 11
	int start = (pageNum - 1) * pageSize + 1;
	// 현재 페이지가 2페이지라면 2*10 = 20
	int end = pageNum * pageSize;
	param.put("start", start);
	param.put("end", end);
	
	// 모든 데이터를 List 저장
	List<BoardDTO> boardLists = dao.selectListPage(param);
	// 데이터베이스 접속을 종료
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 게시판</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>목록 보기(List) - 현재 페이지 : <%=pageNum%> (전체:<%=totalPage %>)</h2>
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord"/>
					<input type="submit" value="검색하기"/>
				</td>
			</tr>
		</table>
	</form>
	<table border="1" width="90%">
		<tr>
			<th width="10%">번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
		</tr>
		<%if(boardLists.isEmpty()){ %>
			<tr>
				<td colspan="5" align="center">
					등록된 게시물이 없습니다^^*
				</td>
			</tr>
		<%}else{
			int virtualNum = 0;
			int countNum = 0;
			for(BoardDTO dto : boardLists){
				virtualNum = totalCount - (((pageNum - 1)*pageSize)+countNum++);
				%>
				<tr align="center">
					<td><%=virtualNum%></td>
					<td align="left">
						<a href="View.jsp?num=<%=dto.getNum() %>">
							<%=dto.getTitle()%>
						</a>
					</td>
					<td><%=dto.getId() %></td>
					<td><%=dto.getVisitCount() %></td>
					<td><%=dto.getPostDate() %></td>
				</tr>
				
		<%}
		}%>
	</table>
	<table border="1" width="90%">
		<tr align="center">
			<td>
				<%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %>
			</td>
			<td>
				<button type="button" onClick="location.href='Write.jsp';">
					글쓰기
				</button>
			</td>
		</tr>
	</table>
</body>
</html>










