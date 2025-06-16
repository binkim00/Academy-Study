<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 25. 6. 13.
  Time: 오후 4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
          <form action="/todo/edit" method="post">
            <div class="input-group mb-3">
              <span class="input-group-text">TNO</span>
              <input type="text" name="tno" class="form-control"
                     value="${dto.tno}" readonly>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Title</span>
              <input type="text" name="title" class="form-control"
                     value="${dto.title}">
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">DueDate</span>
              <input type="date" name="dueDate" class="form-control"
                     value="${dto.dueDate}">
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Writer</span>
              <input type="text" name="writer" class="form-control"
                     value="${dto.writer}" readonly>
            </div>
            <div class="form-check">
              <label class="form-check-label">
                Finished &nbsp;
              </label>
              <input type="checkbox" class="form-check-input" name="finished"
              ${dto.finished ? "checked" : ""}>
            </div>
            <div class="my-4">
              <div class="float-end">
                <button type="button" class="btn btn-danger">Remove</button>
                <button type="submit" class="btn btn-primary">Modify</button>
                <button type="button" class="btn btn-secondary">List</button>
              </div>
            </div>
          </form>
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
  // JavaScript에서 $를 사용하는 EL사용시 '' 감싸야함
  let msg = '${empty msg?"":msg}';
  if(msg != null && msg.length > 0){
    alert(msg);
  }
  document.querySelector(".btn-danger").addEventListener("click", function(e){

    // 기본으로 설정되어 있던 /todo/edit의 Submit이 실행되지 않도록 설정
    // 태그에서 설정되어있는 기능을 막는 메서드
    e.preventDefault();
    // 부모 태그에 이벤트가 설정되는것 막도록 설정
    e.stopPropagation();

    // form 태그를 실행하기 위해 JavaScript를 이용
    const formObj = document.querySelector("form");
    // action을 삭제용 URI로 변경
    formObj.action="/todo/remove";
    // method를 post로 번경
    formObj.method="post";
    // submit으로 서버에 데이터를 전송
    formObj.submit();
  })
  document.querySelector(".btn-secondary").addEventListener("click", function(e){
    self.location="/todo/list";
  })
</script>
</body>
</html>
