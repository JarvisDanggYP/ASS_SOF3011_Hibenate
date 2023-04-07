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

<div class="col-8 offset-2">
    <form>
        <a href="/chi-tiet-sp/create" class="btn btn-success">Add chi tiết sản phẩm</a>
        <c:if test = "${f:length(dsCtsp) == 0}">
            <h5>ko có dữ liệu</h5>
        </c:if>
        <c:if test = "${f:length(dsCtsp) != 0}">
            <table class="table mt-3">
                <thead class="table-dark">
                <th>Năm Bảo Hành</th>
                <th>Mô tả</th>
                <th>Số lượng tồn</th>
                <th>Giá nhập</th>
                <th>Giá bán</th>
                <th>Sản phẩm</th>
                <th>Nhà sản xuất</th>
                <th>Màu sắc</th>
                <th>Dòng sản Phẩm</th>

                <th colspan="2">Hành động</th>
                </thead>
                <tbody>
                <c:forEach var="ctsp" items="${ dsCtsp }">
                    <tr>
                        <td>${ ctsp.namBaoHanh }</td>
                        <td>${ ctsp.moTa }</td>
                        <td>${ ctsp.soLuongTon }</td>
                        <td>${ ctsp.giaNhap }</td>
                        <td>${ ctsp.giaBan }</td>
                        <td>${ ctsp.sanPham.ten }</td>
                        <td>${ ctsp.nsx.ten }</td>
                        <td>${ ctsp.mauSac.ten }</td>
                        <td>${ ctsp.dongSp.ten }</td>

                        <td>
                            <a href="/chi-tiet-sp/edit?id=${ ctsp.id }" class="btn btn-primary">Cập nhật</a>
                        </td>
                        <td>
                            <a href="/chi-tiet-sp/delete?id=${ ctsp.id }" class="btn btn-danger">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </c:if>

    </form>
</div>

