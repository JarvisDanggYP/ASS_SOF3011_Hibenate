package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.ChucVu;
import com.example.ass_sof3011_hibenate.domain_models.SanPham;
import com.example.ass_sof3011_hibenate.repositories.SanPhamRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
        "/san-pham/index",
        "/san-pham/create",
        "/san-pham/edit",
        "/san-pham/update",
        "/san-pham/store",
        "/san-pham/delete",
})
public class SanPhamServlet extends HttpServlet {

    private SanPhamRepository sanPhamRepository;

    public SanPhamServlet() {
        this.sanPhamRepository = new SanPhamRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else {
            this.index(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            response.sendRedirect("/san-pham/index");
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/views/san-pham/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")                .forward(request, response);
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("danhSach", this.sanPhamRepository.findAll());
        request.setAttribute("view", "/views/views/san-pham/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham sanPham = new SanPham();
        sanPham.setMa(ma);
        this.sanPhamRepository.findByMa(ma);
        request.setAttribute("sp", sanPham);
        request.setAttribute("view", "/views/views/san-pham/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")                .forward(request, response);
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");

            if (ma.trim().isEmpty()||ten.trim().isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/san-pham/edit?ma=" + ma);
                return;

            }
            SanPham sanPham = (SanPham) this.sanPhamRepository.findByMa(ma);
            BeanUtils.populate(sanPham, request.getParameterMap());
            this.sanPhamRepository.update(sanPham);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/san-pham/index");
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        SanPham sanPham = this.sanPhamRepository.findByMa(ma);
        if (sanPham == null) {
            System.out.println("Không tìm thấy");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            this.sanPhamRepository.delete(sanPham);
            response.sendRedirect("/san-pham/index");
        }
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            if (ma.trim().isEmpty()||ten.trim().isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/san-pham/create");
                return;

            }
            if (sanPhamRepository.findByMa(ma) != null ){
                request.getSession().setAttribute("errorMessage", "Mã đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/san-pham/create");
                return;
            }
            SanPham sanPham = new SanPham();
            BeanUtils.populate(sanPham, request.getParameterMap());
            this.sanPhamRepository.insert(sanPham);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/san-pham/index");
    }
}

