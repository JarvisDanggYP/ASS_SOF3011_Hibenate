package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.ChiTietSp;
import com.example.ass_sof3011_hibenate.domain_models.HoaDon;
import com.example.ass_sof3011_hibenate.domain_models.HoaDonChiTiet;
import com.example.ass_sof3011_hibenate.repositories.ChiTietSanPhamRepository;
import com.example.ass_sof3011_hibenate.repositories.HoaDonChiTietRepository;
import com.example.ass_sof3011_hibenate.repositories.HoaDonRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@WebServlet({
        "/hoa-don-chi-tiet/index",    // GET
        "/hoa-don-chi-tiet/create",   // GET
        "/hoa-don-chi-tiet/edit",     // GET
        "/hoa-don-chi-tiet/delete",   // GET
        "/hoa-don-chi-tiet/store",    // POST
        "/hoa-don-chi-tiet/update",   // POST
        })
public class HoaDonChiTietServlet extends HttpServlet {
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    private HoaDonRepository hoaDonRepository;
    private ChiTietSanPhamRepository chiTietSanPhamRepositoryRepo;
    public HoaDonChiTietServlet()
    {
        hoaDonChiTietRepository = new HoaDonChiTietRepository();
        hoaDonRepository = new HoaDonRepository();
        chiTietSanPhamRepositoryRepo =new ChiTietSanPhamRepository();

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
            response.sendRedirect("/hoa-don-chi-tiet/index");
        }
    }
    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsHoaDonChiTiet", hoaDonChiTietRepository.findAll());
        request.setAttribute("view", "/views/views/hoa-don-chi-tiet/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsHoaDon", hoaDonRepository.findAll());
        request.setAttribute("dsChiTietSanPham", chiTietSanPhamRepositoryRepo.findAll());
        request.setAttribute("view", "/views/views/hoa-don-chi-tiet/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsHoaDon", hoaDonRepository.findAll());
        request.setAttribute("dsChiTietSanPham", chiTietSanPhamRepositoryRepo.findAll());

        UUID id = UUID.fromString(request.getParameter("id"));
        HoaDonChiTiet hdct = hoaDonChiTietRepository.findByID(id);
        request.setAttribute("hdct", hdct);

        request.setAttribute("idHoaDon", hdct.getHoaDon().getId());
        request.setAttribute("idChiTietSanPham", hdct.getChiTietSp().getId());

        request.setAttribute("view", "/views/views/hoa-don-chi-tiet/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        HoaDonChiTiet hdct = hoaDonChiTietRepository.findByID(id);
        hoaDonChiTietRepository.delete(hdct);
        response.sendRedirect("/hoa-don-chi-tiet/index");
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {


            UUID idHoaDon = UUID.fromString(request.getParameter("idHoaDon"));
            HoaDon hd = new HoaDon();
            hd.setId(idHoaDon);

            UUID idChiTietSanPham = UUID.fromString(request.getParameter("idChiTietSanPham"));
            ChiTietSp ctsp = new ChiTietSp();
            ctsp.setId(idChiTietSanPham);


            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hd);
            hoaDonChiTiet.setChiTietSp(ctsp);

            BeanUtils.populate(hoaDonChiTiet, request.getParameterMap());
            hoaDonChiTietRepository.insert(hoaDonChiTiet);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/hoa-don-chi-tiet/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            UUID id = UUID.fromString(request.getParameter("id"));

            UUID idHoaDon = UUID.fromString(request.getParameter("idHoaDon"));
            HoaDon hd = new HoaDon();
            hd.setId(idHoaDon);

            UUID idChiTietSanPham = UUID.fromString(request.getParameter("idChiTietSanPham"));
            ChiTietSp ctsp = new ChiTietSp();
            ctsp.setId(idChiTietSanPham);


            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findByID(id);
            hoaDonChiTiet.setHoaDon(hd);
            hoaDonChiTiet.setChiTietSp(ctsp);

            BeanUtils.populate(hoaDonChiTiet, request.getParameterMap());
            hoaDonChiTietRepository.update(hoaDonChiTiet);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(list);
        response.sendRedirect("/hoa-don-chi-tiet/index");
    }
}
