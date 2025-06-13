<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 25. 6. 13.
  Time: 오전 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <!-- 부트스트랩 CSS를 CDN방식으로 다운로드 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <div class="col">
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">Navbar</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
              <a class="nav-link" href="#">Features</a>
              <a class="nav-link" href="#">Pricing</a>
              <a class="nav-link disabled">Disabled</a>
            </div>
          </div>
        </div>
      </nav>
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
            <c:forEach items="${dtoList}" var="dto">
              <tr>
                  <%-- c:out : &,<,>," 같은 특수기호를 이스케이프 처리하는 JSTL 기능 --%>
                  <%-- 이스케이프 처리 : 특수기호가 출력될 수 있도록 앞에 역슬래시(\)를 넣어주는 처리--%>
                <th scope="row"><c:out value="${dto.tno}"/></th>
                <td>
                  <a href="/todo/read?tno=${dto.tno}" class="text-decoration-none">
                    <c:out value="${dto.title}"/>
                  </a>
                </td>
                <td><c:out value="${dto.writer}"/></td>
                <td>${dto.dueDate}</td>
                <td>${dto.finished}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
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
</body>
</html>
