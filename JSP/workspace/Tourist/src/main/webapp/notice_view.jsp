<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="board.dao.BoardDAO, board.dto.BoardDTO" %>

<%
    String num = request.getParameter("num");
    if (num == null) {
        out.println("<p>잘못된 접근입니다.</p>");
        return;
    }

    BoardDAO dao = new BoardDAO();
    dao.updateVisitCount(num);
    BoardDTO dto = dao.selectView(num);

    // 👉 이전/다음 글 DTO 가져오기
    BoardDTO prevPost = dao.getPrevPost(num);
    BoardDTO nextPost = dao.getNextPost(num);

    dao.close();

    if (dto == null) {
        out.println("<p>존재하지 않는 게시글입니다.</p>");
        return;
    }
%>



<!DOCTYPE html>
<html lang="ko">
<head>
<title><%= dto.getTitle() %> | 공지사항 | 고객센터 | 투어리스트인투어</title>
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="css/common.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/common.js"></script>  
<script src="js/jquery.smooth-scroll.min.js"></script> 
<!--[if lte IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/placeholders.min.js"></script>
<![endif]-->
</head>

<body>
<ul class="skipnavi">
    <li><a href="#container">본문내용</a></li>
</ul>

<!-- wrap -->
<div id="wrap">

<%@ include file="Header.jsp" %>

<!-- header 그대로 유지 (생략 가능) -->

<div id="container">
    <!-- location_area -->
    <div class="location_area customer">
        <div class="box_inner">
            <h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
            <p class="location">고객센터 <span class="path">/</span> 공지사항</p>
            <ul class="page_menu clear">
                <li><a href="#" class="on">공지사항</a></li>
                <li><a href="#">문의하기</a></li>
            </ul>
        </div>
    </div>	
    <!-- //location_area -->

    <!-- bodytext_area -->
    <div class="bodytext_area box_inner">			
        <ul class="bbsview_list">
            <li class="bbs_title"><%= dto.getTitle() %></li>
            <li class="bbs_hit">작성일 : <span><%= dto.getPostdate() %></span></li>
            <li class="bbs_date">조회수 : <span><%= dto.getVisitcount() %></span></li>
            <li class="bbs_content">
                <div class="editer_content">
                    <pre style="white-space: pre-wrap;"><%= dto.getContent() %></pre>
                </div>
            </li>
        </ul>

        <p class="btn_line txt_right">
            <a href="notice_list.jsp" class="btn_bbs">목록</a>
        </p>

        <!-- 이전글/다음글 기능은 아직 미구현 상태 -->
        <ul class="near_list mt20">
		    <li><h4 class="prev">다음글</h4>
		        <% if (nextPost != null) { %>
		            <a href="notice_view.jsp?num=<%= nextPost.getNum() %>"><%= nextPost.getTitle() %></a>
		        <% } else { %>
		            <a href="javascript:;">(없음)</a>
		        <% } %>
		    </li>		
		    <li><h4 class="next">이전글</h4>
		        <% if (prevPost != null) { %>
		            <a href="notice_view.jsp?num=<%= prevPost.getNum() %>"><%= prevPost.getTitle() %></a>
		        <% } else { %>
		            <a href="javascript:;">(없음)</a>
		        <% } %>
		    </li>
		</ul>

    </div>
    <!-- //bodytext_area -->

</div>
<!-- //container -->

<!-- footer 영역 그대로 유지 -->

</div>
<!-- //wrap -->

<!-- 빠른 링크 영역 그대로 유지 -->

</body>
</html>
