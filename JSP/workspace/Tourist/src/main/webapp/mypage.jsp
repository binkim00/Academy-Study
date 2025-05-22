<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="member.dao.MemberDAO, member.dto.MemberDTO" %>
<%
    MemberDTO member = (MemberDTO) session.getAttribute("userDTO");

    if (member == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title> MYPAGE | 투어리스트인투어 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/common.css">
    <script src="js/jquery-1.11.3.min.js"></script>
    <script src="js/common.js"></script>  
    <script src="js/jquery.smooth-scroll.min.js"></script> 
</head>

<body>

<%@ include file="Header.jsp" %>

<div id="layout0"> <!-- TOP 버튼과 연결될 ID 추가 -->
<div id="container">
    <div class="location_area member">
        <div class="box_inner">
            <h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
            <p class="location">MYPAGE <span class="path">/</span> 개인 정보 수정</p>
            <ul class="page_menu clear">
                <li><a href="#" class="on">개인 정보 수정</a></li>
            </ul>
        </div>
    </div>

    <div class="bodytext_area box_inner">
        <dl class="myinfo">
            <dt>내 정보</dt>
                        
            <dd>
                <form action="#" class="regForm">
                    <fieldset>
                        <legend>내정보 입력 양식</legend>
                        <ul class="reg_list">
                        <% if (member != null) { %>
                            <li class="clear"><span class="tit_lbl">이름</span><div class="reg_content"><%= member.getName() %></div></li>
                            <li class="clear"><span class="tit_lbl">성별</span><div class="reg_content"><%= member.getGender() %></div></li>
                            <li class="clear"><span class="tit_lbl">핸드폰</span><div class="reg_content"><%= member.getPhone() %></div></li>
                            <li class="clear"><span class="tit_lbl">이메일</span><div class="reg_content"><%= member.getEmail() %></div></li>
                            <li class="clear"><span class="tit_lbl">개인정보 활용 동의</span><div class="reg_content"><%= member.getAgree() == 1 ? "동의함" : "동의 안 함" %></div></li>
                            <li class="clear"><span class="tit_lbl">가입일</span><div class="reg_content"><%= member.getRegidate() %></div></li>
                            <li class="clear"><span class="tit_lbl">남긴 글</span><div class="reg_content"><%= member.getContent() %></div></li>
                        <% } %>
                        </ul>
                        <p class="btn_line">
                            <!-- 나중에 수정 페이지 연결 예정 -->
                            <a href="editMember.jsp" class="btn_baseColor">수정하기</a>
                        </p>
                    </fieldset>
                </form>
            </dd>
        </dl>
    </div>
</div>
</div> <!-- //layout0 -->

<footer>
    <div class="foot_area box_inner">
        <ul class="foot_list clear">
            <li><a href="javascript:;">이용약관</a></li>
            <li><a href="javascript:;">개인정보취급방침</a></li>
        </ul>
        <h2>투어리스트인투어</h2>
        <p class="addr">서울특별시 종로구 혜화동 9길 청운빌딩 5층 / 대표전화 02-1234-5678 / E-mail : titour@touristintour.com</p>
        <p class="copy box_inner">Copyright(c) TouristInTour all right reserved</p>
        <ul class="snslink clear">
            <li><a href="javascript:;">blog</a></li>
            <li><a href="javascript:;">facebook</a></li>
            <li><a href="javascript:;">instargram</a></li>
        </ul>
    </div>
</footer>

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
