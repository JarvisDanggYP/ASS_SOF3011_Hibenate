package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.ChucVu;
import com.example.ass_sof3011_hibenate.domain_models.DongSp;
import com.example.ass_sof3011_hibenate.repositories.DongSpRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet({
        "/dong-sp/index",    // GET
        "/dong-sp/create",   // GET
        "/dong-sp/edit",     // GET
        "/dong-sp/delete",   // GET
        "/dong-sp/store",    // POST
        "/dong-sp/update",   // POST
})
public class DongSPServlet extends HttpServlet {
    private DongSpRepository dongSPRepository;

    public DongSPServlet() {
        this.dongSPRepository = new DongSpRepository();
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
            response.sendRedirect("/dong-sp/index");
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/views/dong-sp/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("danhSach", this.dongSPRepository.findAll());
        request.setAttribute("view", "/views/views/dong-sp/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
    }

    protected void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        DongSp dongSp = new DongSp();
        dongSp.setMa(ma);
        this.dongSPRepository.findByMa(ma);
        request.setAttribute("ds", dongSp);
        request.setAttribute("view", "/views/views/dong-sp/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
    }

    protected void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        DongSp dongSp = this.dongSPRepository.findByMa(ma);
        if (dongSp == null) {
            System.out.println("Không tìm thấy");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            this.dongSPRepository.delete(dongSp);
            response.sendRedirect("/dong-sp/index");
        }
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
                response.sendRedirect(request.getContextPath() + "/dong-sp/edit?ma=" + ma);
                return;

            }
            DongSp dongSp = (DongSp) this.dongSPRepository.findByMa(ma);
            BeanUtils.populate(dongSp, request.getParameterMap());
            this.dongSPRepository.update(dongSp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/dong-sp/index");
    }


    protected void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            if (ma.trim().isEmpty()||ten.trim().isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/dong-sp/create");
                return;

            }
            if (dongSPRepository.findByMa(ma) != null ){
                request.getSession().setAttribute("errorMessage", "Mã đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/dong-sp/create");
                return;
            }
            DongSp dongSp = new DongSp();
            BeanUtils.populate(dongSp, request.getParameterMap());
            this.dongSPRepository.insert(dongSp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/dong-sp/index");
    }
}

