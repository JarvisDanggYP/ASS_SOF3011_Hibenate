package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.DongSp;
import com.example.ass_sof3011_hibenate.domain_models.KhachHang;
import com.example.ass_sof3011_hibenate.domain_models.MauSac;
import com.example.ass_sof3011_hibenate.repositories.MauSacRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
        "/mau-sac/index",    // GET
        "/mau-sac/create",   // GET
        "/mau-sac/edit",     // GET
        "/mau-sac/delete",   // GET
        "/mau-sac/store",    // POST
        "/mau-sac/update",   // POST
})
public class MauSacServlet extends HttpServlet {
    private MauSacRepository mauSacRepository;

    public MauSacServlet() {
        this.mauSacRepository = new MauSacRepository();
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
            response.sendRedirect("/mau-sac/index");
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/views/mau-sac/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);

    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("danhSach", this.mauSacRepository.findAll());
        request.setAttribute("view", "/views/views/mau-sac/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        MauSac mauSac = new MauSac();
        mauSac.setMa(ma);
        this.mauSacRepository.findByMa(ma);
        request.setAttribute("ms", mauSac);
        request.setAttribute("view", "/views/views/mau-sac/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
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
                response.sendRedirect(request.getContextPath() + "/mau-sac/create");
                return;

            }
            MauSac mauSac = (MauSac) this.mauSacRepository.findByMa(ma);
            BeanUtils.populate(mauSac, request.getParameterMap());
            this.mauSacRepository.update(mauSac);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/mau-sac/index");
    }


    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        MauSac mauSac = this.mauSacRepository.findByMa(ma);
        if (mauSac == null) {
            System.out.println("Không tìm thấy");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            this.mauSacRepository.delete(mauSac);
            response.sendRedirect("/mau-sac/index");
        }
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            if (ma.trim().isEmpty()||ten.trim().isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/mau-sac/create");
                return;

            }
            if (mauSacRepository.findByMa(ma) != null ){
                request.getSession().setAttribute("errorMessage", "Trùng mã");
                response.sendRedirect(request.getContextPath() + "/mau-sac/create");
                return;
            }
            MauSac mauSac = new MauSac();
            BeanUtils.populate(mauSac, request.getParameterMap());
            this.mauSacRepository.insert(mauSac);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/mau-sac/index");
    }
}
