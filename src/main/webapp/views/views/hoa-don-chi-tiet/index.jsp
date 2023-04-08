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

<h3 class="text-center">Hoá Đơn Chi Tiết</h3>
<div class="col-8 offset-2">
    <a href="/hoa-don-chi-tiet/create" class="btn btn-success mt-3">Add hóa đơn chi tiết</a>
    <c:if test = "${f:length(dsHoaDonChiTiet) == 0}">
        <h5>ko có dữ liệu</h5>
    </c:if>
    <c:if test = "${f:length(dsHoaDonChiTiet) != 0}">
        <table class="table mt-3">
            <thead class="table-dark">
            <th>Mã</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
            <th>Mô Tả</th>
            <th colspan="2">Hành động</th>

            </thead>
            <tbody>
            <c:forEach var="hdct" items="${ dsHoaDonChiTiet }">
                <tr>
                    <td>${ hdct.hoaDon.ma}</td>
                    <td>${ hdct.soLuongTon }</td>
                    <td>${ hdct.donGia}</td>
                    <td>${ hdct.chiTietSp.moTa}</td>


                    <td>
                        <a href="/hoa-don-chi-tiet/edit?id=${ hdct.hoaDon.id }" class="btn btn-primary">Cập nhật</a>
                        <a href="/hoa-don-chi-tiet/delete?id=${ hdct.hoaDon.id  }" class="btn btn-danger">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </c:if>


</div>


