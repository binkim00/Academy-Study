<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>공지사항 글쓰기 | 고객센터 | 투어리스트인투어</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="/css/common.css">
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/jquery.smooth-scroll.min.js"></script>
</head>

<body>
<ul class="skipnavi">
    <li><a href="#container">본문내용</a></li>
</ul>

<!-- wrap -->
<div id="wrap">
    <%@ include file="Header.jsp" %>

    <div id="container">
        <!-- 위치 영역 -->
        <div class="location_area customer">
            <div class="box_inner">
                <h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
                <p class="location">고객센터 <span class="path">/</span> 공지사항</p>
                <ul class="page_menu clear">
                    <li><a href="${pageContext.request.contextPath}/list" class="on">공지사항</a></li>
                    <li><a href="#">문의하기</a></li>
                </ul>
            </div>
        </div>

        <div class="bodytext_area box_inner">
            <form action="${pageContext.request.contextPath}/write" method="post">
                <ul class="bbsview_list">
                    <li class="bbs_title">
                        제목 :
                        <input type="text" name="title" size="100" placeholder="제목을 입력해주세요." class="tbox" required />
                    </li>
                    <li class="bbs_content">
                        <div class="editer_content">
                            <textarea name="content" cols="110" rows="20" placeholder="내용을 입력해주세요." class="tbox" required></textarea>
                        </div>
                    </li>
                </ul>

                <p class="btn_line txt_right">
                    <input type="submit" value="글쓰기" class="btn_srch">
                    <a href="${pageContext.request.contextPath}/list" class="btn_bbs">목록</a>
                </p>
            </form>

            <ul class="near_list mt20">
                <li><h4 class="prev">다음글</h4><a href="javascript:;">추석 연휴 티켓/투어 배송 및 직접 수령 안내</a></li>
                <li><h4 class="next">이전글</h4><a href="javascript:;">이번 여름 휴가 제주 갈까? 미션 투어 (여행경비 50만원 지원)</a></li>
            </ul>
        </div>


    <!-- 푸터 영역 -->
    <footer>
        <div class="foot_area box_inner">
            <ul class="foot_list clear">
                <li><a href="javascript:;">이용약관</a></li>
                <li><a href="javascript:;">개인정보취급방침</a></li>
            </ul>
            <h2>투어리스트인투어</h2>
            <p class="addr">서울특별시 종로구 혜화동 9길 청운빌딩 5층
                <span class="gubun">/</span>
                <span class="br_line">대표전화 <span class="space0">02-1234-5678</span>
                <span class="gubun">/</span>
                <span class="br_line">E-mail : <span class="space0">titour@touristintour.com</span></span>
                </span>
            </p>
            <p class="copy box_inner">Copyright(c) TouristInTour all right reserved</p>
        </div>
    </footer>
</div>

<script>
    // 로그인 여부 확인
    window.addEventListener('DOMContentLoaded', function() {
        const isLogin = "${sessionScope.loginUser ne null}";
        if (!isLogin) {
            alert("로그인이 필요합니다.");
            location.href = "${pageContext.request.contextPath}/login";
        }
    });
</script>

</body>
</html>
