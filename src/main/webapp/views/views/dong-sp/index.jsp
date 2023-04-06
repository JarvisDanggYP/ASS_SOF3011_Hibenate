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
<h3 class="text-center">Dòng Sản Phẩm</h3>
<div class="col-8 offset-2 mt-5 table-responsive">
    <div class="row">
        <div class="col-6">
            <a href="/dong-sp/create" class="btn btn-success">Thêm mới</a>
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
            <c:forEach items="${ danhSach }" var="ds">
                <tr>
                    <td>${ ds.ma }</td>
                    <td>${ ds.ten }</td>
                    <td>
                        <a class="btn btn-primary"
                           href="/dong-sp/edit?ma=${ ds.ma }">
                            Cập nhật
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-danger"
                           href="/dong-sp/delete?ma=${ ds.ma }">
                            Xoá
                        </a>
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
