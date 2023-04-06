<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/9/23
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>

<c:if test="${not empty sessionScope.errorMessage}">
    <div class="alert alert-danger" role="alert">
            ${sessionScope.errorMessage}
    </div>
    <% session.removeAttribute("errorMessage"); %>
</c:if>



<%--<form method="post" action="/login">--%>
<%--    <div class="mt-3">--%>
<%--        <label>Mã NV</label>--%>
<%--        <input type="text" name="ma" class="form-control" />--%>
<%--    </div>--%>
<%--    <div class="mt-3">--%>
<%--        <label>Password</label>--%>
<%--        <input type="password" name="matKhau" class="form-control" />--%>
<%--    </div>--%>

<%--    <div class="mt-3">--%>
<%--        <button class="btn btn-primary">Đăng nhập</button>--%>
<%--    </div>--%>
<%--</form>--%>

<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-6 col-sm-8">
            <div class="card mt-5">
                <div class="card-body">
                    <img src="https://www.instagram.com/static/images/web/mobile_nav_type_logo.png/735145cfe0a4.png" alt="Instagram logo" class="d-block mx-auto mb-4">
                    <form method="post" action="/login">
                        <div class="mb-3">
                            <label for="username" class="form-label sr-only">Username</label>
                            <input type="text" id="username" name="ma" class="form-control form-control-lg" placeholder="Username">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label sr-only">Password</label>
                            <input type="password" id="password" name="matKhau" class="form-control form-control-lg" placeholder="Password">
                        </div>
                        <div class="d-grid mb-3">
                            <button type="submit" class="btn btn-primary btn-lg">Log In</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>