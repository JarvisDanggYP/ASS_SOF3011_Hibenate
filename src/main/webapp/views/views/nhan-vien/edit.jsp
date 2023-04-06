<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 04/04/2023
  Time: 19:47
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
  </head>
  <body>
  <div class="col-8 offset-2">
      <form method="POST"
            action="/nhan-vien/update?ma=${nv.ma}">
          <div class="row mt-3">update
              <div class="col-6">
                  <label>Mã</label>
                  <input type="text" name="ma" class="form-control" value="${nv.ma}" disabled/>
              </div>
              <div class="col-6">
                  <label>Họ</label>
                  <input type="text" name="ho" class="form-control" value="${ nv.ho }" required/>
              </div>
          </div>
          <div class="row mt-3">
              <div class="col-6">
                  <label>Tên đệm</label>
                  <input type="text" name="tenDem" class="form-control" value="${ nv.tenDem }" required />
              </div>
              <div class="col-6">
                  <label>Tên</label>
                  <input type="text" name="ten" class="form-control" value="${ nv.ten }" required />
              </div>
          </div>
          <div class="row mt-3">
              <div class="col-6">
                  <label>Ngày sinh</label>
                  <input type="date" name="ngaySinh" class="form-control" value="${ nv.ngaySinh }" required/>
              </div>
              <div class="col-6">
                  <label>SDT</label>
                  <input type="tel" name="sdt" class="form-control"  value="${ nv.sdt }" required/>
              </div>
          </div>
          <div class="row mt-3">
              <div class="col-6">
                  <label>Địa chỉ</label>
                  <input type="text" name="diaChi" class="form-control" value="${ nv.diaChi }" required/>
              </div>
              <div class="col-6">
                  <label>Giới tính</label>
                  <input type="text" name="gioiTinh" class="form-control" value="${ nv.gioiTinh }" required/>
              </div>
          </div>
          <div class="row mt-3">
              <div class="col-6">
                  <label>Trạng Thái</label>
                  <select name="trangThai" class="form-select" required>
                      <option value="Onl" ${ nv.trangThai == "0" ? "selected" : ""}>Đang làm</option>
                      <option value="Off" ${ nv.trangThai == "1" ? "selected" : ""}>Nghỉ việc</option>
                  </select>
              </div>
              <div class="col-6">
                  <label>Chức vụ</label>
                  <select class="form-select" name="idChucVu" >
                      <c:forEach var="cv" items="${ dsChucVu }">
                          <option value="${cv.id}"  ${cv.id == idChucVu ? "selected" : ""}>${cv.ten}</option>
                      </c:forEach>
                  </select>
              </div>
          </div>
          <div class="row mt-3">
              <div class="col-6">
                  <label>Cửa Hàng</label>
                  <select class="form-select" name="idCuaHang" >
                      <c:forEach var="ch" items="${ dsCuaHang }">
                          <option value="${ch.id}"  ${ch.id == idCuaHang ? "selected" : ""}>${ch.ten}</option>
                      </c:forEach>
                  </select>
              </div>
              <div class="col-6">
                  <label>Mật Khẩu</label>
                  <input type="text" name="matKhau" class="form-control" value="${ nv.matKhau }" required />
              </div>
          </div>
          <div class="row mt-3">
              <div class="col-6">
                  <button class="btn btn-primary">Sửa</button>
              </div>
              <div class="col-6"></div>
          </div>
      </form>
  </div>
  </body>
</html>
