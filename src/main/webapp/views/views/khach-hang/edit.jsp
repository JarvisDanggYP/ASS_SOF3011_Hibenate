<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 19/03/2023
  Time: 21:09
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
<div class="col-8 offset-2 bg-light">
    <form method="POST"
          action="/khach-hang/update?ma=${ kh.ma }">
        <div class="row mt-3">
            <div class="col-6">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" value="${ kh.ma }" disabled />
            </div>
            <div class="col-6">
                <label>Họ</label>
                <input type="text" name="ho" class="form-control" value="${ kh.ho }" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Tên đệm</label>
                <input type="text" name="tenDem" class="form-control" value="${ kh.tenDem }" />
            </div>
            <div class="col-6">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control" value="${ kh.ten }" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Ngày sinh</label>
                <input type="date" name="ngaySinh" class="form-control" value="${ kh.ngaySinh }" />
            </div>
            <div class="col-6">
                <label>Số điện thoại</label>
                <input type="tel" name="sdt" class="form-control" value="${ kh.sdt }" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Địa chỉ</label>
                <input type="text" name="diaChi" class="form-control" value="${ kh.diaChi }" />
            </div>
            <div class="col-6">
                <label>Mật khẩu</label>
                <input type="password" name="password" class="form-control" />
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Quốc gia</label>
                <select name="quocGia" class="form-select">
                    <option value="vn" ${ kh.quocGia == "vn" ? "selected" : "" }>Việt Nam</option>
                    <option value="en" ${ kh.quocGia == "en" ? "selected" : "" }>Anh</option>
                </select>
            </div>
            <div class="col-6">
                <label>Thành phố</label>
                <select name="thanhPho" class="form-select">
                    <option value="hanoi">Hà Nội</option>
                    <option value="london">London</option>
                </select>
            </div>
        </div><div class="row mt-3">
        <div class="col-6">
            <button class="btn btn-primary">Sửa</button>
        </div>
        <div class="col-6"></div>
    </div>
    </form>
</div>

<script src="/js/bootstrap.min.js"></script>
</body>
</html>
