<%--
  Created by IntelliJ IDEA.
  User: jarvisdanggyp
  Date: 06/04/2023
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>

<div class="col-8 offset-2">
    <form method="POST"
          action="/chi-tiet-sp/update?id=${ctsp.id}">
        <div class="row mt-3">
            <div class="col-12">
                <label>Năm Bảo Hành</label>
                <input type="text" name="namBaoHanh" class="form-control" value="${ctsp.namBaoHanh}" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Mô tả</label>
                <input type="text" name="moTa" class="form-control" value="${ctsp.moTa}" required/>
            </div>
            <div class="col-6">
                <label>Số lượng tồn</label>
                <input type="text" name="soLuongTon" class="form-control" value="${ctsp.soLuongTon}" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Giá nhập</label>
                <input type="text" name="giaNhap" class="form-control" value="${ctsp.giaNhap}" required/>
            </div>
            <div class="col-6">
                <label>Giá bán</label>
                <input type="text" name="giaBan" class="form-control" value="${ctsp.giaBan}" required/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Chức vụ</label>
                <select class="form-select" name="idSanPham">
                    <c:forEach var="sp" items="${ dsSanPham }">
                        <option value="${sp.id}">${sp.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-6">
                <label>Nhà sản xuất</label>
                <select class="form-select" name="idNSX">
                    <c:forEach var="nsx" items="${ dsNSX }">
                        <option value="${nsx.id}">${nsx.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Màu Sắc</label>
                <select class="form-select" name="idMauSac">
                    <c:forEach var="ms" items="${ dsMauSac }">
                        <option value="${ms.id}">${ms.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-6">
                <label>Dòng sản phẩm</label>
                <select class="form-select" name="idDongSP">
                    <c:forEach var="dsp" items="${ dsDongSP }">
                        <option value="${dsp.id}">${dsp.ten}</option>
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
</div>

