package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.CuaHang;
import com.example.ass_sof3011_hibenate.domain_models.HoaDon;
import com.example.ass_sof3011_hibenate.domain_models.KhachHang;
import com.example.ass_sof3011_hibenate.domain_models.NhanVien;
import com.example.ass_sof3011_hibenate.repositories.HoaDonRepository;
import com.example.ass_sof3011_hibenate.repositories.KhachHangRepository;
import com.example.ass_sof3011_hibenate.repositories.NhanVienRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.UUID;

@WebServlet({
        "/hoa-don/index",    // GET
        "/hoa-don/create",   // GET
        "/hoa-don/edit",     // GET
        "/hoa-don/delete",   // GET
        "/hoa-don/store",    // POST
        "/hoa-don/update",   // POST
})
public class HoaDonServlet extends HttpServlet {
    private HoaDonRepository hoaDonRepository;

    private KhachHangRepository khachHangRepository;

    private NhanVienRepository nhanVienRepository;

    public HoaDonServlet()
    {
        hoaDonRepository = new HoaDonRepository();
        khachHangRepository = new KhachHangRepository();
        nhanVienRepository = new NhanVienRepository();

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
            response.sendRedirect("/hoa-don/index");
        }
    }
    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsHoaDon", hoaDonRepository.findAll());
        request.setAttribute("view", "/views/views/hoa-don/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsKhachHang", khachHangRepository.findAll());
        request.setAttribute("dsNhanVien", nhanVienRepository.findAll());
        request.setAttribute("view", "/views/views/hoa-don/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsKhachHang", khachHangRepository.findAll());
        request.setAttribute("dsNhanVien", khachHangRepository.findAll());

        String ma = request.getParameter("ma");
        HoaDon hd = hoaDonRepository.findByMa(ma);
        request.setAttribute("hd", hd);

        request.setAttribute("idKhachHang", hd.getKhachHang().getId());
        request.setAttribute("idNhanVien", hd.getNhanVien().getId());

        request.setAttribute("view", "/views/views/hoa-don/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("ma");
        HoaDon hd = hoaDonRepository.findByMa(String.valueOf(id));
        hoaDonRepository.delete(hd);
        response.sendRedirect("/hoa-don/index");


    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String tenNguoiNhan = request.getParameter("tenNguoiNhan");
            String diaChi = request.getParameter("diaChi");
            String sdt = request.getParameter("sdt");
            if (ma.trim().isEmpty()||tenNguoiNhan.trim().isEmpty()|| diaChi.trim().isEmpty()|| diaChi.trim().isEmpty()||sdt.trim().isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/hoa-don/edit?ma=" + ma);
                return;

            }
            if (hoaDonRepository.findByMa(ma) != null ){
                request.getSession().setAttribute("errorMessage", "Trùng mã");
                response.sendRedirect(request.getContextPath() + "/hoa-don/create");
                return;
            }
//            DateTimeConverter dateTimeConverter = new DateConverter(new Date());
//            dateTimeConverter.setPattern("yyyy-MM-dd");
//            ConvertUtils.register(dateTimeConverter, Date.class);


            UUID idKhachHang= UUID.fromString(request.getParameter("idKhachHang"));
            KhachHang kh = new KhachHang();
            kh.setId(idKhachHang);

            UUID idNhanVien = UUID.fromString(request.getParameter("idNhanVien"));
            NhanVien nv = new NhanVien();
            nv.setId(idNhanVien);

            HoaDon domainModelHD = new HoaDon();
            domainModelHD.setKhachHang(kh);
            domainModelHD.setNhanVien(nv);

            BeanUtils.populate(domainModelHD, request.getParameterMap());
            hoaDonRepository.insert(domainModelHD);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/hoa-don/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String tenNguoiNhan = request.getParameter("tenNguoiNhan");
            String diaChi = request.getParameter("diaChi");
            String sdt = request.getParameter("sdt");

            if (ma.trim().isEmpty()|| tenNguoiNhan.isEmpty()|| diaChi.isEmpty()|| sdt.isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/hoa-don/create");
                return;

            }

//            DateTimeConverter dateTimeConverter = new DateConverter(new Date());
//            dateTimeConverter.setPattern("yyyy-MM-dd");
//            ConvertUtils.register(dateTimeConverter, Date.class);


            UUID idKhachHang= UUID.fromString(request.getParameter("idKhachHang"));
            KhachHang kh = new KhachHang();
            kh.setId(idKhachHang);

            UUID idNhanVien = UUID.fromString(request.getParameter("idNhanVien"));
            NhanVien nv = new NhanVien();
            nv.setId(idNhanVien);

            HoaDon domainModelHD = hoaDonRepository.findByMa(String.valueOf(ma));
            domainModelHD.setKhachHang(kh);
            domainModelHD.setNhanVien(nv);

            BeanUtils.populate(domainModelHD, request.getParameterMap());
            hoaDonRepository.update(domainModelHD);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/hoa-don/index");
    }
}
