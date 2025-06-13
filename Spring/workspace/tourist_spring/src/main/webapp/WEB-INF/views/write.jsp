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
        <div class="location_area customer">
            <div class="box_inner">
                <h2 class="tit_page">TOURIST <span class="in">in</span> TOUR</h2>
                <p class="location">고객센터 <span class="path">/</span> 공지사항</p>
                <ul class="page_menu clear">
                    <li><a href="/list" class="on">공지사항</a></li>
                    <li><a href="#">문의하기</a></li>
                </ul>
            </div>
        </div>

        <div class="bodytext_area box_inner">
            <h3 class="tit">공지사항 글쓰기</h3>

            <form action="${pageContext.request.contextPath}/write" method="post">
                <div class="bbs_write">
                    <ul>
                        <li>
                            <label for="title">제목</label>
                            <input type="text" id="title" name="title" class="tbox full" required />
                        </li>
                        <li>
                            <label for="content">내용</label>
                            <textarea id="content" name="content" class="tbox full" rows="10" required></textarea>
                        </li>
                    </ul>
                </div>

                <div class="btn_line txt_right">
                    <button type="submit" class="btn_bbs">등록</button>
                    <a href="${pageContext.request.contextPath}/list" class="btn_bbs">취소</a>
                </div>
            </form>
        </div>
    </div>

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
