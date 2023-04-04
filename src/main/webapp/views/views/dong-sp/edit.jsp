<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 17/03/2023
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="/css/bootstrap.min.css"/>
</head>
<body>
<div class="col-8 offset-2">
    <form class="row g-3 needs-validation col-10 offset-1 mt-5" novalidate method="POST"
          action="/dong-sp/update?ma=${ds.ma}">
        <div class="col-md-4">
            <label for="validationCustom01" class="form-label">Mã</label>
            <input type="text" class="form-control" value="${ds.ma}" id="validationCustom01" name="ma" disabled required>
        </div>
        <div class="col-md-4">
            <label for="validationCustom02" class="form-label">Tên Dòng Sản Phẩm</label>
            <input type="text" class="form-control" value="${ds.ten}" id="validationCustom02" name="ten" required>
        </div>

        <div class="col-12">
            <button class="btn btn-primary col-2 offset-0" type="submit">Sửa</button>
        </div>
    </form>
</div>

<script src="/js/bootstrap.min.js"></script>
</body>
</html>
