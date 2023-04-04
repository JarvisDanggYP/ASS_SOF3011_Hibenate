//package com.example.ass_sof3011_hibenate.controllers.admin;
//
//import com.example.ass_sof3011_hibenate.domain_models.ChucVu;
//import com.example.ass_sof3011_hibenate.repositories.ChiTietSanPhamRepository;
//import com.example.ass_sof3011_hibenate.repositories.ChucVuRepository;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.commons.beanutils.BeanUtils;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//
//@WebServlet({
//        "/chi-tiet-sp/index",    // GET
//        "/chi-tiet-sp/create",   // GET
//        "/chi-tiet-sp/edit",     // GET
//        "/chi-tiet-sp/delete",   // GET
//        "/chi-tiet-sp/store",    // POST
//        "/chi-tiet-sp/update",   // POST
//})
//public class ChiTietSPServlet extends HttpServlet {
//    private ChiTietSanPhamRepository chiTietSanPhamRepository;
//    public ChiTietSPServlet(){
//        this.chiTietSanPhamRepository = new ChiTietSanPhamRepository();
//
//    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//    protected void create(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.getRequestDispatcher("/views/views/chuc-vu/create.jsp")
//                .forward(request, response);
//    }
//    protected void index(HttpServletRequest request,
//                         HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setAttribute("danhSach", this.chiTietSanPhamRepository.getList());
//        request.getRequestDispatcher("/views/views/chi-tiet-sp/index.jsp")
//                .forward(request, response);
//    }
//
//    protected void edit(HttpServletRequest request,
//                        HttpServletResponse response)
//            throws ServletException, IOException {
//        String ma = request.getParameter("ma");
//        ChucVu chucVu = new ChucVu();
//        chucVu.setMa(ma);
//        this.chucVuRepository.check(ma);
//        request.setAttribute("cv", chucVu);
//        request.getRequestDispatcher("/views/views/chuc-vu/edit.jsp")
//                .forward(request, response);
//    }
//
//    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String ma = request.getParameter("ma");
//        String ten = request.getParameter("ten");
//        ChucVu chucVu = new ChucVu(ma,ten);
//        this.chucVuRepository.sua(ma,chucVu);
//        response.sendRedirect("/chuc-vu/index");
//    }
//
//    protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String ma = request.getParameter("ma");
//        ChucVu chucVu = new ChucVu();
//        chucVu.setMa(ma);
//        this.chucVuRepository.xoa(chucVu);
//        response.sendRedirect("/chuc-vu/index");
//    }
//
//    protected void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            ChucVu chucVu = new ChucVu();
//            BeanUtils.populate(chucVu, request.getParameterMap());
//            this.chucVuRepository.them(chucVu);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        response.sendRedirect("/chuc-vu/index");
//    }
//}
