<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 08/04/2023
  Time: 02:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="/css/bootstrap.min.css" />
</head>
<body>
<h3 class="text-center">Giỏ hàng</h3>
<div class="col-8 offset-2">
    <a href="/gio-hang/create" class="btn btn-success mt-3">Thêm giỏ hàng</a>
    <c:if test="${f:length(dsNhanVien) == 0}">
        <h5>ko có dữ liệu</h5>
    </c:if>
    <c:if test="${f:length(dsNhanVien) != 0}">
        <table class="table mt-3">
            <thead class="table-dark">
            <th>Mã</th>
            <th>Tên Khách Hàng</th>
            <th>Ngày Tạo</th>
            <th>Ngày Thanh Toán</th>
            <th>Tên người nhận</th>
            <th>SDT</th>
            <th>Địa chỉ</th>
            <th>Trạng thái</th>
            <th colspan="2">Hành động</th>

            </thead>
            <tbody>
            <c:forEach var="gh" items="${ dsGioHang }">
                <tr>
                    <td>${ gh.ma }</td>
                    <td>${ gh.khachHang.ten}</td>
                    <td>${ gh.ngayTao}</td>
                    <td>${ gh.ngayThanhToan }</td>
                    <td>${ gh.tenNguoiNhan }</td>
                    <td>${ gh.sdt }</td>
                    <td>${ gh.diaChi }</td>
                    <td>
                        <c:if test="${nv.trangThai == '0'}">Chưa thanh toán</c:if>
                        <c:if test="$${nv.trangThai == '1'}">Đã thanh toán</c:if>
                    </td>

                    <td>
                        <a href="/gio-hang/edit?ma=${ gh.ma }" class="btn btn-primary">Cập nhật</a>

                    </td>
                    <td>
                        <a href="/gio-hang/delete?ma=${ gh.ma }" class="btn btn-danger">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </c:if>

    <script src="/js/bootstrap.min.js"></script>
</div>
</body>
</html>
