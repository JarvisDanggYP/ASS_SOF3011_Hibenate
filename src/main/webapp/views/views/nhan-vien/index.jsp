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
          href="/css/bootstrap.min.css" />
</head>
<body>
<h3 class="text-center">Nhân viên</h3>
<div class="col-8 offset-2">
    <a href="/nhan-vien/create" class="btn btn-success mt-3">Thêm nhân viên</a>
    <c:if test="${f:length(dsNhanVien) == 0}">
        <h5>ko có dữ liệu</h5>
    </c:if>
    <c:if test="${f:length(dsNhanVien) != 0}">
        <table class="table mt-3">
            <thead class="table-dark">
            <th>Mã</th>
            <th>Họ</th>
            <th>Tên đệm</th>
            <th>Tên</th>
            <th>Ngày Sinh</th>
            <th>Giới tính</th>
            <th>SĐT</th>
            <th>Địa chỉ</th>
            <th>Trạng thái</th>
            <th>Chức vụ</th>
            <th>Cửa hàng</th>
            <th colspan="2">Hành động</th>

            </thead>
            <tbody>
            <c:forEach var="nv" items="${ dsNhanVien }">
                <tr>
                    <td>${ nv.ma }</td>
                    <td>${ nv.ho }</td>
                    <td>${ nv.tenDem}</td>
                    <td>${ nv.ten }</td>
                    <td>${ nv.ngaySinh }</td>
                    <td>${ nv.gioiTinh }</td>
                    <td>${ nv.sdt }</td>
                    <td>${ nv.diaChi }</td>
                    <td>
                        <c:if test="${nv.trangThai == '0'}">Đang Làm</c:if>
                        <c:if test="$${nv.trangThai == '1'}">Nghỉ Việc</c:if>
                    </td>
                    <td>${ nv.chucVu.ten}</td>
                    <td>${ nv.cuaHang.ten}</td>

                    <td>
                        <a href="/nhan-vien/edit?ma=${ nv.ma }" class="btn btn-primary">Cập nhật</a>

                    </td>
                    <td>
                        <a href="/nhan-vien/delete?ma=${ nv.ma }" class="btn btn-danger">Xóa</a>
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
