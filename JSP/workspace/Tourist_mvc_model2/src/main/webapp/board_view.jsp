<%@page import="board.dto.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>
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
<html lang="ko">
<head>
<title> 박물관 미션 투어 당첨자 발표 | 공지사항 | 고객센터 | 투어리스트인투어 </title>
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
<script>
function deletePost(){
	if(confirm("정말 삭제하시겠습니까?")){
		var formObj = document.createElement("form");
		formObj.innerHTML = "<input type='hidden' name='num' value='<%=dto.getNum()%>' />";
		formObj.method = "post";
		formObj.action = "boarddelete.do";
		document.body.appendChild(formObj);
		formObj.submit();
	}
}
</script>

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
				<li class="bbs_title"><%=dto.getTitle() %></li>
				<li class="bbs_hit">작성일 : <span><%=dto.getPostDate() %></span></li>
				<li class="bbs_date">조회수 : <span><%=dto.getVisitCount() %></span></li>
				<li class="bbs_content">
					<div class="editer_content">
					    <%= dto.getContent().replaceAll("(\r\n|\r|\n)", "<br/>") %>
                    </div>
				</li>
			</ul>
			<p class="btn_line txt_right">
				<%if(session.getAttribute("UserId")!=null
				&& session.getAttribute("UserId").equals(dto.getId())){ %>
					<a onclick="deletePost()" class="btn_bbs">삭제하기</a>
					<a href="boardedit.do?num=${dto.num}" class="btn_bbs">수정하기</a>
				<%}%>
				<a href="./boardlist.do" class="btn_bbs">목록</a>
			</p>
			<ul class="near_list mt20">
				<li><h4 class="prev">다음글</h4><a href="javascript:;">추석 연휴 티켓/투어 배송 및 직접 수령 안내</a></li>		
				<li><h4 class="next">이전글</h4><a href="javascript:;">이번 여름 휴가 제주 갈까? 미션 투어 (여행경비 50만원 지원)</a></li>
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
            <p class="addr">서울특별시 종로구 혜화동 9길 청운빌딩 5층 <span class="gubun">/</span>        
				<span class="br_line">대표전화 <span class="space0">02-1234-5678</span> <span class="gubun">/</span>        
					<span class="br_line">E-mail : <span class="space0"> titour@touristintour.com</span></span>
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
