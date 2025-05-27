<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="program.dao.ProgramDAO, program.dto.ProgramDTO" %>
<%
    String id = request.getParameter("id");
    ProgramDAO dao = new ProgramDAO();
    ProgramDTO dto = dao.selectProgramById(id);
    dao.close();

    if (dto == null) {
%>
    <script>
        alert("해당 프로그램 정보를 찾을 수 없습니다.");
        location.href = "program.jsp";
    </script>
<%
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title><%= dto.getTitle() %></title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/layout_base.css">
    <link rel="stylesheet" href="css/swiper.min.css">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/common.js"></script>
    <script src="js/rollmain.js"></script>
</head>
<body>

<jsp:include page="Header.jsp" />

<div id="container">

	<div class="location_area package">
            <div class="box_inner">
                <h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
                <p class="location">상품투어 <span class="path">/</span> 프로그램 상세 소개</p>
                <ul class="page_menu clear">
                    <li><a href="javascript:;" class="on">프로그램 소개</a></li>
                    <li><a href="javascript:;">여행 자료</a></li>
                </ul>
            </div>
        </div>	

    <div class="bodytext_area place_area">
        <div class="box_inner">

            <!-- 썸네일 이미지 -->
            <p style="text-align:center;">
                <img src="img/<%= dto.getImg() != null ? dto.getImg() : "default.jpg" %>" alt="<%= dto.getTitle() %>" style="max-width:100%; height:auto;">
            </p>

            <!-- 제목 -->
            <h2 style="font-size:30px; font-weight:bold; text-align:center; margin:30px 0;">
                <%= dto.getTitle() %>
            </h2>

            <!-- 일정 및 지역 -->
            <p class="subttl" style="text-align:center; font-size:16px; color:#fa6400;">
                지역: <%= dto.getArea() != null ? dto.getArea() : "-" %> | 일정: <%= dto.getSchedule() != null ? dto.getSchedule() : "-" %>
            </p>

            <!-- 상세 설명 -->
            <div class="program_content" style="margin-top:30px; line-height:1.8; font-size:15px;">
                <p><strong>가격:</strong> <%= dto.getPrice() > 0 ? dto.getPrice() + "원" : "문의 요망" %></p>
                <p><strong>상세설명:</strong><br>
                    <%= dto.getContent() != null ? dto.getContent().replaceAll("\n", "<br>") : "상세 정보가 없습니다." %>
                </p>
            </div>

            <!-- 버튼 -->
            <p class="btn_line txt_right" style="margin-top:30px;">
                <a href="program.jsp" class="btn_bbs">목록으로</a>
            </p>
        </div>
    </div>
</div>

</body>
</html>
