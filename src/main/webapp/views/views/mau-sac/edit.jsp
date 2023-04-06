<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 17/03/2023
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>


<c:if test="${not empty sessionScope.errorMessage}">
    <div class="alert alert-danger" role="alert">
            ${sessionScope.errorMessage}
    </div>
    <% session.removeAttribute("errorMessage"); %>
</c:if>
<html>
<head>
    <title>Màu Sắc</title>
    <link rel="stylesheet"
          href="/css/bootstrap.min.css"/>
</head>
<body>

<form class="row g-3 needs-validation col-10 offset-1 mt-5" novalidate method="POST"
      action="/mau-sac/update?ma=${ms.ma}">
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label">Mã</label>
        <input type="text" class="form-control" value="${ms.ma}" disabled id="validationCustom01" name="ma" required>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label">Tên Màu Sắc</label>
        <input type="text" class="form-control" value="${ms.ten}" id="validationCustom02" name="ten" required>
    </div>

    <div class="col-12">
        <button class="btn btn-primary col-2 offset-0" type="submit">Sửa</button>
    </div>
</form>
</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
