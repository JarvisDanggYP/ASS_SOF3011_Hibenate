package com.example.ass_sof3011_hibenate.controllers.user;

import com.example.ass_sof3011_hibenate.domain_models.NhanVien;
import com.example.ass_sof3011_hibenate.repositories.NhanVienRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet({
        "/login",
})
public class NhanVienLoginServlet extends HttpServlet {
    private NhanVienRepository nhanVienRepository;
    public NhanVienLoginServlet(){
        this.nhanVienRepository = new NhanVienRepository();
    }
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("view", "/views/views/login.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String matKhau = request.getParameter("matKhau");
        NhanVien nhanVien = this.nhanVienRepository.login(ma, matKhau);
        HttpSession session = request.getSession();
        if (nhanVien == null) {
            session.setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu");
            response.sendRedirect("/login");
        } else {
            session.setAttribute("nv", nhanVien);
            response.sendRedirect("/khach-hang/index");
        }
    }
}
