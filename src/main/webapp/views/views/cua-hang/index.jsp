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
          href="/bootstrap.min.css"/>
</head>
<body>
<h3 class="text-center">Cửa Hàng</h3>
<div class="col-8 offset-2">
    <form>
        <a href="/cua-hang/create" class="btn btn-success">Thêm cửa hàng </a>
        <c:if test = "${f:length(dsCuaHang) == 0}">
            <h5>ko có dữ liệu</h5>
        </c:if>

        <c:if test = "${f:length(dsCuaHang) != 0}">
            <table class="table mt-3">
                <thead class="table-dark">
                <th>Mã</th>
                <th>Tên</th>
                <th>Địa chỉ</th>
                <th>Thành phố</th>
                <th>Quốc gia</th>
                <th colspan="2">Hành động</th>
                </thead>
                <tbody>
                <c:forEach var="ch" items="${ dsCuaHang }">
                    <tr>
                        <td>${ ch.ma }</td>
                        <td>${ ch.ten }</td>
                        <td>${ ch.diaChi }</td>
                        <<td>
                        <c:if test ="${ch.thanhPho == 'HaNoi'}" >Hà Nội</c:if>
                        <c:if test ="${ch.thanhPho == 'NewYork'}" >New York</c:if>
                    </td>
                        <td>
                            <c:if test ="${ch.quocGia == 'VietNam'}" >Việt Nam</c:if>
                            <c:if test ="${ch.quocGia == 'US'}" >Mỹ</c:if>
                        </td>

                        <td>
                            <a href="/cua-hang/edit?ma=${ ch.ma }" class="btn btn-primary">Cập nhật</a>
                            <a href="/cua-hang/delete?ma=${ ch.ma }" class="btn btn-danger">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </c:if>


    </form>
</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
