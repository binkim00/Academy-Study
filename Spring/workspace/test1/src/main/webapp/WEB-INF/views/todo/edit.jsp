<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/Header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>할일 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
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
                <div class="card-header">할일 수정</div>
                <div class="card-body">
                    <form action="/todo/edit" method="post">
                        <input type="hidden" name="page" value="${pageRequestDTO.page}" />
                        <input type="hidden" name="size" value="${pageRequestDTO.size}" />

                        <input type="hidden" name="tno" value="${dto.tno}" />
                        <input type="hidden" name="writer" value="${dto.writer}" />

                        <div class="input-group mb-3">
                            <span class="input-group-text">TNO</span>
                            <input type="text" class="form-control" value="${dto.tno}" readonly />
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control" value="${dto.title}" />
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">DueDate</span>
                            <input type="date" name="dueDate" class="form-control" value="${dto.dueDate}" />
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Writer</span>
                            <input type="text" class="form-control" value="${dto.writer}" readonly />
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">Finished &nbsp;</label>
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
        <div class="row fixed-bottom" style="z-index:-100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script>
    let msg = '${empty msg?"":msg}';
    if (msg !== null && msg.length > 0) {
        alert(msg);
    }

    document.querySelector(".btn-danger").addEventListener("click", function (e) {
        e.preventDefault();
        e.stopPropagation();

        if (!confirm("정말 삭제하시겠습니까?")) {
            return;
        }

        const formObj = document.querySelector("form");
        formObj.action = "/todo/remove";
        formObj.method = "post";
        formObj.submit();
    });


    document.querySelector(".btn-secondary").addEventListener("click", function (e) {
        self.location = `/todo/list?${pageRequestDTO.link}`;
    });
</script>
</body>
</html>
