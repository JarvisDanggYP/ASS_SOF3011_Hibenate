package com.example.ass_sof3011_hibenate.filters;

import com.example.ass_sof3011_hibenate.domain_models.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({
        "/chi-tiet-sp/*",
        "/chuc-vu/*",
        "/cua-hang/*",
        "/dong-sp/*",
//        "/gio-hang/*",
//        "/gio-hang-chi-tiet/*",
        "/hoa-don/*",
        "/khach-hang/*",
        "/hoa-don-chi-tiet/*",
        "/mau-sac/*",
        "/nsx/*",
        "/nhan-vien/*",
        "/san-pham/*",

})
public class AuthenFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        NhanVien nv = (NhanVien) session.getAttribute("nv");

        if (nv == null) {
            session.setAttribute("errorMessage", "Vui lòng đăng nhập");
            res.sendRedirect("/login");
        } else {
            chain.doFilter(req, res);
        }
    }
}
