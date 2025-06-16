<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <title> 공지사항 | 고객센터 | 투어리스트인투어 </title>
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="stylesheet" href="/css/common.css">
  <script src="/js/jquery-1.11.3.min.js"></script>
  <script src="/js/common.js"></script>
  <script src="/js/jquery.smooth-scroll.min.js"></script>
  <!--[if lte IE 9]>
  <script src="/js/html5shiv.js"></script>
  <script src="/js/placeholders.min.js"></script>
  <![endif]-->
</head>

<body>

<c:if test="${not empty msg}">
  <script>
    alert("${msg}");
  </script>
</c:if>

<ul class="skipnavi">
  <li><a href="#container">부문내용</a></li>
</ul>

<div id="wrap">

  <%@ include file="./Header.jsp" %>

  <div id="container">
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

    <div class="bodytext_area box_inner">
      <form method="get" class="minisrch_form">
        <fieldset>
          <legend>검색</legend>
          <input type="text" name="searchWord" class="tbox" title="검색어를 입력해주세요" placeholder="검색어를 입력해주세요" value="${param.searchWord}">
          <button class="btn_srch">검색</button>
          <a class="btn_srch" href="${pageContext.request.contextPath}/write" id="writeBtn">글쓰기</a>
        </fieldset>
      </form>

      <table class="bbsListTbl" summary="번호,제목,조회수,작성일 등을 제공하는 표">
        <caption class="hdd">공지사항 목록</caption>
        <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">조회수</th>
          <th scope="col">작성일</th>
        </tr>
        </thead>

        <c:set var="startNum" value="${(pageNum - 1) * 10}" />
        <c:set var="isSearch" value="${not empty param.searchWord}" />

        <tbody>
        <c:choose>
          <c:when test="${empty list}">
            <tr>
              <td colspan="4" align="center">등록된 게시물이 없습니다.</td>
            </tr>
          </c:when>
          <c:otherwise>
            <c:forEach var="vo" items="${list}" varStatus="loop">
              <tr>
                <td>
                  <c:choose>
                    <c:when test="${isSearch}">
                      ${fn:length(list) - loop.index}
                    </c:when>
                    <c:otherwise>
                      ${totalCount - (startNum + loop.index)}
                    </c:otherwise>
                  </c:choose>
                </td>
                <td class="tit_notice">
                  <a href="/view?num=${vo.num}">${vo.title}</a>
                </td>
                <td>${vo.visitcount}</td>
                <td><fmt:formatDate value="${vo.postdate}" pattern="yyyy-MM-dd" /></td>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
        </tbody>

      </table>

      <!-- pagination -->
      <div class="pagination">
        <c:choose>
          <c:when test="${not empty searchWord}">
            <c:set var="queryStr" value="&searchWord=${searchWord}" />
          </c:when>
          <c:otherwise>
            <c:set var="queryStr" value="" />
          </c:otherwise>
        </c:choose>


        <a href="/list?pageNum=1${queryStr}" class="firstpage pbtn">
          <img src="/img/btn_firstpage.png" alt="첫 페이지로 이동">
        </a>

        <c:if test="${pageNum > 1}">
          <a href="/list?pageNum=${pageNum - 1}${queryStr}" class="prevpage pbtn">
            <img src="/img/btn_prevpage.png" alt="이전 페이지로 이동">
          </a>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPage}">
          <a href="/list?pageNum=${i}${queryStr}">
            <span class="pagenum ${i == pageNum ? 'currentpage' : ''}">${i}</span>
          </a>
        </c:forEach>

        <c:if test="${pageNum < totalPage}">
          <a href="/list?pageNum=${pageNum + 1}${queryStr}" class="nextpage pbtn">
            <img src="/img/btn_nextpage.png" alt="다음 페이지로 이동">
          </a>
        </c:if>

        <a href="/list?pageNum=${totalPage}${queryStr}" class="lastpage pbtn">
          <img src="/img/btn_lastpage.png" alt="마지막 페이지로 이동">
        </a>
      </div>

      <!-- //pagination -->
    </div><!-- //bodytext_area -->
  </div><!-- //container -->
</div><!-- //wrap -->

<!-- 빠른 링크 -->
<div class="quick_area">
  <ul class="quick_list">
    <li><a href="tel:010-7184-4403"><h3>전화 문의</h3><p>010-1234-5678</p></a></li>
    <li><a href="javascript:;"><h3>카카오톡 <em>상당</em></h3><p>1:1상당</p></a></li>
    <li><a href="javascript:;"><h3 class="to_contact">오시는 길</h3></a></li>
  </ul>
  <p class="to_top"><a href="#layout0" class="s_point">TOP</a></p>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const isLogin = "${sessionScope.loginUser ne null}";
    const writeBtn = document.querySelector("#writeBtn");

    if (writeBtn) {
      writeBtn.addEventListener("click", function(e) {
        if (isLogin !== "true") {
          e.preventDefault();
          alert("로그인이 필요합니다.");
          location.href = "${pageContext.request.contextPath}/login";
        }
      });
    }
  });
</script>



</body>
</html>
