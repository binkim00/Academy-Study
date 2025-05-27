<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, program.dao.ProgramDAO, program.dto.ProgramDTO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title> 프로그램 소개 | 상품투어 | 투어리스트인투어 </title>
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

    <div id="container">
        <!-- location_area -->
        <div class="location_area package">
            <div class="box_inner">
                <h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
                <p class="location">상품투어 <span class="path">/</span> 프로그램 소개</p>
                <ul class="page_menu clear">
                    <li><a href="javascript:;" class="on">프로그램 소개</a></li>
                    <li><a href="javascript:;">여행 자료</a></li>
                </ul>
            </div>
        </div>	
        <!-- //location_area -->

        <!-- bodytext_area -->
        <div class="bodytext_area place_area box_inner">
            <%
                ProgramDAO dao = new ProgramDAO();
                List<ProgramDTO> list = dao.selectAll();
                dao.close();
            %>
            <ul class="program_list clear">
            <%
                for (ProgramDTO p : list) {
            %>
                <li>
				    <a href="ProgramDetail.jsp?id=<%=p.getId()%>">
				        <img class="img_place" src="img/<%=p.getImg()%>" alt="<%=p.getTitle()%>" />
				    </a>
				    <h3><%=p.getTitle()%></h3>
				    <p class="subttl"><%=p.getSchedule()%></p>
				    <div class="program_content">
				        <p><%=p.getText()%> <span class="subtxt"><%=p.getSubtext()%></span></p>
				    </div>
				    <p class="btn_more">
				        <a href="ProgramDetail.jsp?id=<%= p.getId() %>">더보기</a>
				    </p>
				</li>

            <%
                }
            %>
            </ul>
        </div>
        <!-- //bodytext_area -->

    </div>
    <!-- //container -->

    <footer>
        <div class="foot_area box_inner">
            <ul class="foot_list clear">
                <li><a href="javascript:;">이용약관</a></li>
                <li><a href="javascript:;">개인정보취급방침</a></li>
            </ul>
            <h2>투어리스트인투어</h2>
            <p class="addr">
                서울특별시 종로구 혜화동 9길 청운빌딩 5층 <span class="gubun">/</span>        
                <span class="br_line">대표전화 <span class="space0">02-1234-5678</span> <span class="gubun">/</span>        
                    <span class="br_line">E-mail : <span class="space0">titour@touristintour.com</span></span>
                </span>
            </p>
            <p class="copy box_inner">Copyright(c) TouristInTour all right reserved</p>
            <ul class="snslink clear">
                <li><a href="javascript:;">blog</a></li>
                <li><a href="javascript:;">facebook</a></li>
                <li><a href="javascript:;">instargram</a></li>
            </ul>
        </div>
    </footer>

</div>
<!-- //wrap -->

<h2 class="hdd">빠른 링크 : 전화 문의,카카오톡,오시는 길,꼭대기로</h2>
<div class="quick_area">
    <ul class="quick_list">
        <li><a href="tel:010-7184-4403"><h3>전화 문의</h3><p>010-1234-5678</p></a></li>
        <li><a href="javascript:;"><h3>카카오톡 <em>상담</em></h3><p>1:1상담</p></a></li>
        <li><a href="javascript:;"><h3 class="to_contact">오시는 길</h3></a></li>
    </ul>
    <p class="to_top"><a href="#layout0" class="s_point">TOP</a></p>
</div>

</body>
</html>
