<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 라이브러리 임포트 -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<style>
	a{text-decoration: none;}
</style>
</head>
<body>
	<h2>파일 첨부형 게시판 - 목록 보기(List)</h2>
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
			<th width="*">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
			<th width="8%">첨부</th>
		</tr>
	<%-- c:choose : JSTL의 if문 시작 --%>
	<c:choose>
		<%-- c:when test="${조건식}" : 조건식의 결과가 true이면 안의 코드를 실행 --%>
		<c:when test="${empty boardLists}">
			<tr>
				<td colspan="5" align="center">
					등록된 게시물이 없습니다^^*
				</td>
			</tr>
		</c:when>
		<%-- c:when에 해당하지 않는 경우 실행되는 c:otherwise --%>
		<c:otherwise>
			<!--  List를 반복하는 JSTL반복문 -->
			<!-- items : 반복 가능한 데이터, 리스트나 배열 -->
			<!-- var : 리스트나 배열에서 데이터를 하나씩 꺼낼때 사용할 변수이름 -->
			<!-- varStatus : forEach에서 사용가능한 여러가지 데이터의 변수이름 -->
			<!-- varStatus있는 데이터 : index(0부터의 실행 순번), count(1부터의 실행 순번), first(첫번쨰 실행일때 true가 출력), last(마지막 실행일때 true) -->
			<c:forEach items="${boardLists}" var="row" varStatus="loop">
				<tr align="center">
					<td>${map.totalCount - (((map.pageNum-1)*map.pageSize) + loop.index)}</td>
					<td align="left">
						<a href="../mvcboard/view.do?idx=${row.idx}">
							${row.title}
						</a>
					</td>
					<td>${row.name}</td>
					<td>${row.visitcount}</td>
					<td>${row.postdate}</td>
					<td>
					
					<%-- c:url : 한글같은 인코딩 문제가 있는 데이터를 보낼때 사용하는 JSTL --%>
					<!-- value : 주소값 -->
					<!-- var : url완성 후 사용할 변수 이름 --> 
					<c:url value="../mvcboard/download.do" var="fileUrl">
						<%-- c:param : url과 함께 전달할 파라미터 설정--%>
						<!-- name : 파라미터 키 -->
						<!-- value : 파리미터의 값 -->
						<c:param name="ofile" value="${row.ofile}" />
						<c:param name="sfile" value="${row.sfile}" />
						<c:param name="idx" value="${row.idx}" />
					</c:url>
					<!-- JSTL if문 : else가 존재하지 않는 if문 -->
					<%-- c:if test="${조건식}" --%>
					<c:if test="${not empty row.ofile}">
						<a href="../mvcboard/download.do?ofile=${row.ofile}&sfile=${row.sfile}&idx=${row.idx}">[Down]</a>
						<%-- c:url 에서 설정한 var를 href에 설정 --%>
						<a href="${fileUrl}">[인코딩Down]</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</table>
	<table border="1" width="90%">
		<tr align="center">
			<td>
				${ map.pagingImg }
			</td>
			<td>
				<button type="button" onClick="location.href='../mvcboard/write.do';">
					글쓰기
				</button>
			</td>
		</tr>
	</table>
</body>
</html>