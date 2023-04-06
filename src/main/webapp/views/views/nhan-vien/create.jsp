<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/11/23
  Time: 09:59
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
          href="/css/bootstrap.min.css"/>
    <h3> Thêm Nhân Viên</h3>
</head>
<body>
<div class="col-8 offset-2">
    <form method="POST"
          action="/nhan-vien/store">
        <div class="row mt-3">
            <div class="col-6">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" required/>
            </div>
            <div class="col-6">
                <label>Họ</label>
                <input type="text" name="ho" class="form-control" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Tên đệm</label>
                <input type="text" name="tenDem" class="form-control" required/>
            </div>
            <div class="col-6">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Ngày sinh</label>
                <input type="date" name="ngaySinh" class="form-control" required/>
            </div>
            <div class="col-6">
                <label>SDT</label>
                <input type="tel" name="sdt" class="form-control" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Địa chỉ</label>
                <input type="text" name="diaChi" class="form-control" required/>
            </div>
            <div class="col-6">
                <label>Giới tính</label>
                <input type="text" name="gioiTinh" class="form-control" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Trạng Thái</label>
                <select name="trangThai" class="form-select" required>
                    <option value="Onl">Đang làm</option>
                    <option value="Off">Nghỉ việc</option>
                </select>
            </div>
            <div class="col-6">
                <label>Chức vụ</label>
                <select class="form-select" name="idChucVu" id="">
                    <c:forEach var="cv" items="${ dsChucVu }">
                        <option value="${cv.id}">${cv.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Cửa Hàng</label>
                <select class="form-select" name="idCuaHang">
                    <c:forEach var="ch" items="${ dsCuaHang }">
                        <option value="${ch.id}">${ch.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-6">
                <label>Mật Khẩu</label>
                <input type="text" name="matKhau" class="form-control" value="${ nv.matKhau }" required />
            </div>
        </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <button class="btn btn-primary">Thêm mới</button>
            </div>
            <div class="col-6"></div>
        </div>
    </form>
</div>

<script src="/js/bootstrap.min.js"></script>
</body>
</html>
