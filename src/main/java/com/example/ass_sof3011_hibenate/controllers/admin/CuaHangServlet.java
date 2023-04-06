package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.CuaHang;
import com.example.ass_sof3011_hibenate.domain_models.DongSp;
import com.example.ass_sof3011_hibenate.repositories.CuaHangRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
        "/cua-hang/create",   // GET
        "/cua-hang/edit",     // GET
        "/cua-hang/index",    // GET
        "/cua-hang/store",    // POST
        "/cua-hang/delete",   // GET
        "/cua-hang/update",   // POST
})
public class CuaHangServlet extends HttpServlet {
    private CuaHangRepository cuaHangRepository;

    public CuaHangServlet()
    {
        cuaHangRepository = new CuaHangRepository();
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
            response.sendRedirect("/cua-hang/index");
        }
    }
    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsCuaHang", cuaHangRepository.findAll());
        request.setAttribute("view", "/views/views/cua-hang/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/views/cua-hang/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        CuaHang cuaHang = new CuaHang();
        cuaHang.setMa(ma);
        this.cuaHangRepository.findByMa(ma);
        request.setAttribute("ch", cuaHang);
        request.setAttribute("view", "/views/views/cua-hang/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        CuaHang cuaHang = cuaHangRepository.findByMa(ma);
        cuaHangRepository.delete(cuaHang);
        response.sendRedirect("/cua-hang/index");
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            String diaChi = request.getParameter("diaChi");
            String thanhPho = request.getParameter("thanhPho");
            String quocGia = request.getParameter("quocGia");
            if (ma.trim().isEmpty()||ten.trim().isEmpty()|| diaChi.trim().isEmpty()|| thanhPho.trim().isEmpty()||quocGia.trim().isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/cua-hang/edit?ma=" + ma);
                return;

            }
            if (cuaHangRepository.findByMa(ma) != null ){
                request.getSession().setAttribute("errorMessage", "Trùng mã");
                response.sendRedirect(request.getContextPath() + "/cua-hang/create");
                return;
            }
            CuaHang domainModelCH = new CuaHang();
            BeanUtils.populate(domainModelCH, request.getParameterMap());
            this.cuaHangRepository.insert(domainModelCH);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/cua-hang/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            String diaChi = request.getParameter("diaChi");
            if (ma.trim().isEmpty()||ten.trim().isEmpty()|| diaChi.trim().isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/cua-hang/edit?ma=" + ma);
                return;

            }
            CuaHang cuaHang = cuaHangRepository.findByMa(ma);
            BeanUtils.populate(cuaHang, request.getParameterMap());
            this.cuaHangRepository.update(cuaHang);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/cua-hang/index");
    }
}
