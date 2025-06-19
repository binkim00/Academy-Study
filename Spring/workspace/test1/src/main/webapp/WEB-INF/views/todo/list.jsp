<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/Header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- 부트스트랩 CSS를 CDN방식으로 다운로드 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<c:if test="${not empty msg}">
    <script>
        alert('${msg}');
    </script>
</c:if>

<div class="container-fluid">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Navbar</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
                            <a class="nav-link" href="${pageContext.request.contextPath}/todo/list">리스트</a>
                            <a class="nav-link" href="${pageContext.request.contextPath}/todo/register">등록</a>
                        </div>
                    </div>
                </div>
            </nav>

        </div>
    </div>
    <div class="row content">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Search</h5>
                    <form action="/todo/list" method="get">
                        <input type="hidden" name="size" value="${pageRequestDTO.size}">
                        <div class="mb-3">
                            <input type="checkbox" name="finished" ${pageRequestDTO.finished?"checked":""}> 완료여부
                        </div>
                        <div class="mb-3">
                            <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>제목
                            <input type="checkbox" name="types" value="w" ${pageRequestDTO.checkType("w")?"checked":""}>작성자
                            <input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
                        </div>
                        <div class="input-group mb-3 dueDateDiv">
                            <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                            <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                        </div>
                        <div class="input-group mb-3">
                            <div class="float-end">
                                <button class="btn btn-primary" type="submit">Search</button>
                                <button class="btn btn-info" type="button">Clear</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row content">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    Featured
                </div>
                <div class="card-body">
                    <h5 class="card-title">할일 리스트</h5>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Tno</th>
                            <th scope="col">Title</th>
                            <th scope="col">Writer</th>
                            <th scope="col">DueDate</th>
                            <th scope="col">Finished</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${responseDTO.dtoList}" var="dto">
                            <tr>
                                    <%-- c:out : &,<,>," 같은 특수기호를 이스케이프 처리하는 JSTL 기능 --%>
                                    <%-- 이스케이프 처리 : 특수기호가 출력될 수 있도록 앞에 역슬래시(\)를 넣어주는 처리--%>
                                <th scope="row"><c:out value="${dto.tno}"/></th>
                                <td>
                                    <a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}" class="text-decoration-none">
                                        <c:out value="${dto.title}"/>
                                    </a>
                                </td>
                                <td><c:out value="${dto.writer}"/></td>
                                <td>${dto.dueDate}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${dto.finished}">O</c:when>
                                                <c:otherwise>X</c:otherwise>
                                            </c:choose>
                                        </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="float-end">
                        <ul class="pagination flex-wrap">
                            <c:if test="${responseDTO.prev}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.start-1}">Previous</a>
                                </li>
                            </c:if>
                            <%-- 페이지의 숫자 부분--%>
                            <%-- begin : 첫번째 페이지--%>
                            <%-- end : 마지막 페이지--%>
                            <%-- num : 반복에 사용할 변수--%>
                            <c:forEach begin="${responseDTO.start}"
                                       end="${responseDTO.end}"
                                       var="num">
                                <li class="page-item ${responseDTO.page == num ? "active":""}">
                                    <a class="page-link" data-num="${num}">${num}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${responseDTO.next}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.end+1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row content">
        <h1>Content</h1>
    </div>
    <div class="row content">
        <div class="row fixed-bottom" style="z-index:-100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>
<!-- 부트스트랩 JavaScript를 CDN방식으로 다운로드 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
    document.querySelector(".pagination").addEventListener("click", function(e){
        e.preventDefault();
        e.stopPropagation()
        // 클릭했던 태그를 target에 저장
        const target = e.target;
        // 클릭한 태그가 a태그인지 확인하는 if
        if(target.tagName !== 'A'){
            // a태그가 아니라면 아무런 처리를 하지 않고 함수를 끝냄
            return;
        }
        // data-num 속성에 각 페이지 데이터를 저장하여 사용
        const num = target.getAttribute("data-num");
        // 검색창의 form태그를 변수에 저장
        const formObj = document.querySelector("form");
        // 클릭한 번호에 맞는 페이지를 hidden 태그에 저장
        formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'/>`;
        // submit을 실행
        formObj.submit();
    });
    // 검색조건을 모두 삭제하고 페이지를 새로고침 하는 버튼
    /*document.querySelector(".btn-info").addEventListener("click",function(){
       self.location="/todo/list";
    });*/
    document.querySelector(".btn-info").addEventListener("click",function(e){
        let formObj = document.querySelector("form");
        formObj.finished.checked = false;
        formObj.types[0].checked = false;
        formObj.types[1].checked = false;
        formObj.keyword.value="";
        formObj.from.value="";
        formObj.to.value="";
    });
</script>
</body>
</html>
