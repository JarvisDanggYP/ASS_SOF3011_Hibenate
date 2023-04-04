<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 11/03/2023
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="/css/bootstrap.min.css"/>
</head>
<body>
<div class="col-8 offset-2 mt-5 table-responsive">
    <div class="row">
        <div class="col-6">
            <a href="/chuc-vu/create" class="btn btn-success">Thêm mới</a>
        </div>
    </div>

    <c:if test="${ f:length(danhSach) == 0 }">
        <h3>Không có dữ liệu</h3>
    </c:if>

    <c:if test="${ f:length(danhSach) != 0 }">
        <table class="table table-striped mt-3">
            <thead class="table-primary">
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th colspan="2">Thao tác</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${ danhSach }" var="cv">
                <tr>
                    <td>${ cv.ma }</td>
                    <td>${ cv.ten }</td>

                    <td>
                        <a href="/chuc-vu/edit?ma=${ cv.ma }" class="btn btn-primary">Cập nhật</a>
                    </td>

                    <td>
                        <a href="/chuc-vu/delete?ma=${ cv.ma }" class="btn btn-danger">Xoá</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </c:if>

</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
