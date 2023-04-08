package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.*;
import com.example.ass_sof3011_hibenate.repositories.CuaHangRepository;
import com.example.ass_sof3011_hibenate.repositories.GioHangRepository;
import com.example.ass_sof3011_hibenate.repositories.KhachHangRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@WebServlet({
        "/gio-hang/index",    // GET
        "/gio-hang/create",   // GET
        "/gio-hang/edit",     // GET
        "/gio-hang/delete",   // GET
        "/gio-hang/store",    // POST
        "/gio-hang/update",   // POST
})
public class GioHangServlet extends HttpServlet {
    private GioHangRepository gioHangRepository;
    private KhachHangRepository khachHangRepository;

    public GioHangServlet()
    {
        gioHangRepository = new GioHangRepository();
        khachHangRepository = new KhachHangRepository();
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
            response.sendRedirect("/gio-hang/index");
        }
    }
    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsGioHang", this.gioHangRepository.findAll());
        request.setAttribute("view", "/views/views/gio-hang/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsKhachHang", khachHangRepository.findAll());
        request.setAttribute("dsGioHang", gioHangRepository.findAll());
        request.setAttribute("view", "/views/views/gio-hang/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsKhachHang", khachHangRepository.findAll());
        request.setAttribute("dsGioHang", gioHangRepository.findAll());
        String ma = request.getParameter("ma");
        GioHang gioHang = gioHangRepository.findByMa(ma);
        request.setAttribute("gh", gioHang);

        request.setAttribute("idKhachHang", khachHangRepository.findById(ma));
        request.setAttribute("idGioHang", gioHangRepository.findByMa(ma));
        request.setAttribute("view", "/views/views/gio-hang/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        GioHang gioHang = gioHangRepository.findByMa(ma);
        gioHangRepository.delete(gioHang);
        response.sendRedirect("/gio-hang/index");
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String tenNguoiNhan = request.getParameter("tenNguoiNhan");
            String diaChi = request.getParameter("diaChi");
            String sdt = request.getParameter("sdt");
            if (ma.trim().isEmpty()||tenNguoiNhan.trim().isEmpty()|| diaChi.isEmpty()|| sdt.isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/gio-hang/create");
                return;

            }
            if (gioHangRepository.findByMa(ma) != null ){
                request.getSession().setAttribute("errorMessage", "Mã đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/gio-hang/create");
                return;
            }

            UUID idKhachHang = UUID.fromString(request.getParameter("idKhachHang"));
            KhachHang khachHang = new KhachHang();
            khachHang.setId(idKhachHang);

            GioHang gioHang = new GioHang();
            gioHang.setKhachHang(khachHang);

            BeanUtils.populate(idKhachHang, request.getParameterMap());
            gioHangRepository.insert(gioHang);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/gio-hang/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ngayTao = request.getParameter("ngayTao");
            String ngayThanhToan = request.getParameter("ngayThanhToan");
            String tenNguoiNhan = request.getParameter("tenNguoiNhan");
            String diaChi = request.getParameter("diaChi");
            String sdt = request.getParameter("sdt");
            String trangThai = request.getParameter("gioiTinh");

            if (ma.trim().isEmpty()||tenNguoiNhan.trim().isEmpty()|| diaChi.isEmpty()|| sdt.isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/gio-hang/create");
                return;

            }

            UUID idKhachHang = UUID.fromString(request.getParameter("idKhachHang"));
            KhachHang khachHang = new KhachHang();
            khachHang.setId(idKhachHang);

            GioHang gioHang = gioHangRepository.findByMa(ma);
            gioHang.setKhachHang(khachHang);

            BeanUtils.populate(gioHang, request.getParameterMap());

            this.gioHangRepository.update(gioHang);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/gio-hang/index");
    }
}
