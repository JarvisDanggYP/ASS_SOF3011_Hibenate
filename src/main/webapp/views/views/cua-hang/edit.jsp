<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 06/04/2023
  Time: 04:09
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

<div class="col-8 offset-2">
  <form method="POST"
        action="/cua-hang/update?ma=${ch.ma}">
    <div class="row mt-3">
      <div class="col-6">
        <label>Mã</label>
        <input type="text" name="ma" class="form-control" value="${ch.ma}" disabled/>
      </div>
      <div class="col-6">
        <label>Tên</label>
        <input type="text" name="ten" class="form-control" value="${ ch.ten }" required />
      </div>
    </div>
    <div class="row mt-3">
      <div class="col-6">
        <label>Địa chỉ</label>
        <input type="text" name="diaChi" class="form-control" value="${ ch.diaChi }" required/>
      </div>
      <div class="col-6">
        <label>Thành phố</label>
        <select name="thanh_pho" class="form-select" required>
          <option value="HaNoi" ${ ch.thanhPho == "HaNoi" ? "selected" : "" }>Hà Nội</option>
          <option value="NewYork" ${ ch.thanhPho == "NewYork" ? "selected" : "" }>New York</option>
        </select>
      </div>
    </div>
    <div class="row mt-3">
      <div class="col-12">
        <label>Quốc gia</label>
        <select name="quoc_gia" class="form-select" required>
          <option value="VietNam" ${ ch.quocGia == "VietNam" ? "selected" : "" }>Việt Nam</option>
          <option value="US" ${ ch.quocGia == "US" ? "selected" : "" }>Mỹ</option>
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
</div>

