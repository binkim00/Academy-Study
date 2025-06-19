<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/Header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>할일 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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
<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-primary text-white">
            할일 등록
        </div>
        <div class="card-body">
            <form method="post" action="${pageContext.request.contextPath}/todo/register">
                <div class="mb-3">
                    <label class="form-label">Title</label>
                    <input type="text" name="title" class="form-control" placeholder="할일 제목" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Due Date</label>
                    <input type="date" name="dueDate" class="form-control" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Writer</label>
                    <input type="text" name="writer" class="form-control" placeholder="작성자"
                           value="${sessionScope.loginUser.id}" readonly />
                </div>
                <div class="d-flex justify-content-end mt-4">
                    <button type="submit" class="btn btn-primary me-2">등록</button>
                    <button type="reset" class="btn btn-secondary">초기화</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
