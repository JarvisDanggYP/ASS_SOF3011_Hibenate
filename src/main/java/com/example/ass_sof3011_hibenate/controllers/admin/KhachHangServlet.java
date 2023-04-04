package com.example.ass_sof3011_hibenate.controllers.admin;


import com.example.ass_sof3011_hibenate.domain_models.KhachHang;
import com.example.ass_sof3011_hibenate.repositories.KhachHangRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


@WebServlet({
        "/khach-hang/index",    // GET
        "/khach-hang/create",   // GET
        "/khach-hang/edit",     // GET
        "/khach-hang/delete",   // GET
        "/khach-hang/store",    // POST
        "/khach-hang/update",   // POST
})
public class KhachHangServlet extends HttpServlet {
    private KhachHangRepository khachHangRepository;

    public KhachHangServlet() {
        this.khachHangRepository = new KhachHangRepository();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
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
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            response.sendRedirect("/khach-hang/index");
        }
    }


    protected void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/views/khach-hang/create.jsp")
                .forward(request, response);

    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        KhachHang khachHang = new KhachHang();
        khachHang.setMa(ma);
        this.khachHangRepository.findByMa(ma);
        request.setAttribute("kh", khachHang);
        request.getRequestDispatcher("/views/views/khach-hang/edit.jsp")
                .forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        KhachHang khachHang = this.khachHangRepository.findByMa(ma);
        if (khachHang == null) {
            System.out.println("Không tìm thấy");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            this.khachHangRepository.delete(khachHang);
            response.sendRedirect("/khach-hang/index");
        }
    }

    protected void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            KhachHang domainModelKH = (KhachHang) this.khachHangRepository.findByMa(ma);
            BeanUtils.populate(domainModelKH, request.getParameterMap());
            this.khachHangRepository.update(domainModelKH);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/khach-hang/index");

    }

    protected void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute("danhSach", this.khachHangRepository.findAll());
        request.getRequestDispatcher("/views/views/khach-hang/index.jsp")
                .forward(request, response);
    }

    protected void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            KhachHang qlkh = new KhachHang();
            BeanUtils.populate(qlkh, request.getParameterMap());
            this.khachHangRepository.insert(qlkh);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/khach-hang/index");
    }
}
