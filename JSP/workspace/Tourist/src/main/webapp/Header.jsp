<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="member.dto.MemberDTO" %>
<%
    MemberDTO user = (MemberDTO) session.getAttribute("userDTO");
%>
<header id="header">
  <div class="header_area box_inner clear">

    <!-- ✅ 로고 영역 -->
    <h1><a href="index.jsp">Tourist in tour</a></h1>

    <!-- ✅ 모바일 메뉴 버튼 -->
    <p class="openMOgnb">
      <a href="#"><b class="hdd">메뉴열기</b><span></span><span></span><span></span></a>
    </p>

    <!-- ✅ 메뉴 전체 영역 -->
    <div class="header_cont">

      <!-- ✅ 로그인/로그아웃, 회원가입 -->
      <ul class="util clear">
		  <% if (user == null) { %>
		    <li><a href="login.jsp">로그인</a></li>
		    <li><a href="join.jsp">회원가입</a></li>
		  <% } else { %>
		    <!-- ✅ 이름 누르면 mypage.jsp로 이동 -->
		    <li><a href="mypage.jsp"><%= user.getName() %>님 환영합니다.</a></li>
		    <li><a href="Logout.jsp">로그아웃</a></li>
		  <% } %>
	</ul>

      <!-- ✅ 메인 GNB 메뉴 -->
      <nav>
        <ul class="gnb clear">
          <li><a href="javascript:;" class="openAll1">여행정보</a>
            <div class="gnb_depth gnb_depth2_1">
              <ul class="submenu_list">
                <li><a href="javascript:;">국내</a></li>
                <li><a href="javascript:;">해외</a></li>
              </ul>
            </div>
          </li>
          <li><a href="javascript:;" class="openAll2">고객센터</a>
            <div class="gnb_depth gnb_depth2_2">
              <ul class="submenu_list">
                <li><a href="notice_list.jsp">공지사항</a></li>
                <li><a href="notice_write.jsp">문의하기</a></li>
              </ul>
            </div>
          </li>
          <li><a href="javascript:;" class="openAll3">상품투어</a>
            <div class="gnb_depth gnb_depth2_3">
              <ul class="submenu_list">
                <li><a href="program.jsp">프로그램 소개</a></li>
                <li><a href="javascript:;">여행자료</a></li>
              </ul>
            </div>
          </li>
          <li><a href="javascript:;" class="openAll4">티켓/가이드</a>
            <div class="gnb_depth gnb_depth2_4">
              <ul class="submenu_list">
                <li><a href="javascript:;">항공</a></li>
                <li><a href="javascript:;">호텔</a></li>
              </ul>
            </div>
          </li>
        </ul>
      </nav>

      <!-- ✅ 모바일용 닫기 버튼 -->
      <p class="closePop"><a href="javascript:;">닫기</a></p>
    </div> <!-- //header_cont -->
  </div> <!-- //header_area -->
</header>