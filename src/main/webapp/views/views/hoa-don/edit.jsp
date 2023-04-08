<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 06/04/2023
  Time: 09:40
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
    <title>Title</title>
    <link rel="stylesheet"
          href="/css/bootstrap.min.css" />
</head>
<body>
<div class="col-8 offset-2">
    <form method="POST"
          action="/hoa-don/update?ma=${hd.ma}">
        <div class="row mt-3">
            <div class="col-6">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" value="${hd.ma}" disabled/>
            </div>
            <div class="col-6">
                <label>Ngày tạo</label>
                <input type="date" name="ngayTao" class="form-control" value="${ hd.ngayTao }" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Ngày thanh toán</label>
                <input type="date" name="ngayThanhToan" class="form-control" value="${ hd.ngayThanhToan }" required />
            </div>
            <div class="col-6">
                <label>Ngày ship</label>
                <input type="date" name="ngayShip" class="form-control" value="${ hd.ngayShip }" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Ngày nhận</label>
                <input type="date" name="ngayNhan" class="form-control" value="${ hd.ngayNhan }" required/>
            </div>
            <div class="col-6">
                <label>Tình trạng</label>
                <select name="tinh_trang" class="form-select" required>
                    <option value="0" ${ hd.trangThai == "0" ? "selected" : "" }>Đã thanh toán</option>
                    <option value="1" ${ hd.trangThai == "1" ? "selected" : "" }>Chưa thanh toán</option>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Tên người nhận</label>
                <input type="text" name="tenNguoiNhan" class="form-control" value="${ hd.tenNguoiNhan }" required/>
            </div>
            <div class="col-6">
                <label>Địa chỉ</label>
                <input type="text" name="diaChi" class="form-control" value="${ hd.diaChi }" required />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Sđt</label>
                <input type="text" name="sdt" class="form-control" value="${ hd.sdt }" required />
            </div>
            <div class="col-6">
                <label>Khách Hàng</label>
                <select class="form-select" name="idKhachHang" >
                    <c:forEach var="kh" items="${ dsKhachHang }">
                        <option value="${kh.id}" ${kh.id == idKhachHang ? "selected" : ""}>${kh.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Nhân Viên</label>
                <select class="form-select" name="idNhanVien" >
                    <c:forEach var="nv" items="${ dsNhanVien }">
                        <option value="${nv.id}" ${nv.id == idNhanVien ? "selected" : ""}>${nv.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <button class="btn btn-primary">Sửa</button>
            </div>
            <div class="col-6"></div>
        </div>
    </form>
    <script src="/js/bootstrap.min.js"></script>

</div>
</body>
</html>
