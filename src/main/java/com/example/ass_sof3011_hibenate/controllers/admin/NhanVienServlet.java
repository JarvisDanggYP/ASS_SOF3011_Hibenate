package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.ChucVu;
import com.example.ass_sof3011_hibenate.domain_models.CuaHang;
import com.example.ass_sof3011_hibenate.domain_models.NhanVien;
import com.example.ass_sof3011_hibenate.repositories.ChucVuRepository;
import com.example.ass_sof3011_hibenate.repositories.CuaHangRepository;
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
        "/nhan-vien/index",    // GET
        "/nhan-vien/create",   // GET
        "/nhan-vien/edit",     // GET
        "/nhan-vien/delete",   // GET
        "/nhan-vien/store",    // POST
        "/nhan-vien/update",   // POShang
})
public class NhanVienServlet extends HttpServlet {
    private NhanVienRepository nhanVienRepository;

    private ChucVuRepository chucVuRepository;

    private CuaHangRepository cuaHangRepository;

    public NhanVienServlet() {
        nhanVienRepository = new NhanVienRepository();
        chucVuRepository = new ChucVuRepository();
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
            response.sendRedirect("/nhan-vien/index");
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsNhanVien", this.nhanVienRepository.findAll());
        request.setAttribute("view", "/views/views/nhan-vien/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp")
                .forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsChucVu", chucVuRepository.findAll());
        request.setAttribute("dsCuaHang", cuaHangRepository.findAll());
        request.setAttribute("view", "/views/views/nhan-vien/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsCuaHang", cuaHangRepository.findAll());
        request.setAttribute("dsChucVu", chucVuRepository.findAll());
        String ma = request.getParameter("ma");
        NhanVien nhanVien = nhanVienRepository.findByMa(ma);
        request.setAttribute("nv", nhanVien);

        request.setAttribute("idCuaHang", nhanVienRepository.findIdCuaHangByMa(ma));
        request.setAttribute("idChucVu", nhanVienRepository.findIdChucVuByMa(ma));
        request.setAttribute("view", "/views/views/nhan-vien/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        NhanVien nhanVien = nhanVienRepository.findByMa(ma);
        nhanVienRepository.delete(nhanVien);
        response.sendRedirect("/nhan-vien/index");
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ma = request.getParameter("ma");
            String ten = request.getParameter("ten");
            String tenDem = request.getParameter("tenDem");
            String ho = request.getParameter("ho");
            String sdt = request.getParameter("sdt");
            String matKhau = request.getParameter("matKhau");
            String gioiTinh = request.getParameter("gioiTinh");
            if (ma.trim().isEmpty()||ten.trim().isEmpty()|| tenDem.isEmpty()|| ho.isEmpty()|| sdt.isEmpty()|| matKhau.isEmpty()|| gioiTinh.isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/nhan-vien/create");
                return;

            }
            if (nhanVienRepository.findByMa(ma) != null ){
                request.getSession().setAttribute("errorMessage", "Mã đã tồn tại");
                response.sendRedirect(request.getContextPath() + "/nhan-vien/create");
                return;
            }
//            DateTimeConverter dateTimeConverter = new DateConverter(new Date());
//            dateTimeConverter.setPattern("yyyy-MM-dd");
//            ConvertUtils.register(dateTimeConverter, Date.class);

            UUID idChucVu = UUID.fromString(request.getParameter("idChucVu"));
            ChucVu chucVu = new ChucVu();
            chucVu.setId(idChucVu);

            UUID idCuaHang = UUID.fromString(request.getParameter("idCuaHang"));
            CuaHang ch = new CuaHang();
            ch.setId(idCuaHang);


            NhanVien domainModelNV = new NhanVien();
            domainModelNV.setChucVu(chucVu);
            domainModelNV.setCuaHang(ch);

            BeanUtils.populate(domainModelNV, request.getParameterMap());
            nhanVienRepository.insert(domainModelNV);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/nhan-vien/index");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ten = request.getParameter("ten");
            String ma = request.getParameter("ma");
            String tenDem = request.getParameter("tenDem");
            String ho = request.getParameter("ho");
            String sdt = request.getParameter("sdt");
            String matKhau = request.getParameter("matKhau");
            String gioiTinh = request.getParameter("gioiTinh");

            if (ma.trim().isEmpty()||ten.trim().isEmpty()|| tenDem.isEmpty()|| ho.isEmpty()|| sdt.isEmpty()|| matKhau.isEmpty()|| gioiTinh.isEmpty()){
                request.getSession().setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                response.sendRedirect(request.getContextPath() + "/nhan-vien/create");
                return;

            }
//            DateTimeConverter dateTimeConverter = new DateConverter(new Date());
//            dateTimeConverter.setPattern("yyyy-MM-dd");
//            ConvertUtils.register(dateTimeConverter, Date.class);

            UUID idChucVu = UUID.fromString(request.getParameter("idChucVu"));
            ChucVu cv = new ChucVu();
            cv.setId(idChucVu);

            UUID idCuaHang = UUID.fromString(request.getParameter("idCuaHang"));
            CuaHang ch = new CuaHang();
            ch.setId(idCuaHang);

            NhanVien domainModelNV = nhanVienRepository.findByMa(ma);
            domainModelNV.setChucVu(cv);
            domainModelNV.setCuaHang(ch);

            BeanUtils.populate(domainModelNV, request.getParameterMap());

            this.nhanVienRepository.update(domainModelNV);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/nhan-vien/index");
    }
}
