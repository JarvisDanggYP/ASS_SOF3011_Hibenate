<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 11/03/2023
  Time: 23:59
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
          action="/san-pham/store">
        <div class="col-md-4">
            <label for="validationCustom01" class="form-label">Mã</label>
            <input type="text" class="form-control" id="validationCustom01" name="ma" required>
        </div>
        <div class="col-md-4">
            <label for="validationCustom02" class="form-label">Tên Sản Phẩm</label>
            <input type="text" class="form-control" id="validationCustom02" name="ten" required>
        </div>

        <div class="col-12">
            <button class="btn btn-primary col-2 offset-0" type="submit">Add</button>
        </div>
    </form>
</div>

<script src="/js/bootstrap.min.js"></script>

</body>
</html>
