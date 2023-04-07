package com.example.ass_sof3011_hibenate.controllers.admin;

import com.example.ass_sof3011_hibenate.domain_models.*;
import com.example.ass_sof3011_hibenate.repositories.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.UUID;

@WebServlet({
        "/chi-tiet-sp/index",    // GET
        "/chi-tiet-sp/create",   // GET
        "/chi-tiet-sp/edit",     // GET
        "/chi-tiet-sp/delete",   // GET
        "/chi-tiet-sp/store",    // POST
        "/chi-tiet-sp/update",   // POST
})
public class ChiTietSPServlet extends HttpServlet {
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    private SanPhamRepository sanPhamRepository;

    private NSXRepository nsxRepository;

    private MauSacRepository mauSacRepository;

    private DongSpRepository dongSpRepository;

    public ChiTietSPServlet() {
        chiTietSanPhamRepository = new ChiTietSanPhamRepository();
        sanPhamRepository = new SanPhamRepository();
        nsxRepository = new NSXRepository();
        mauSacRepository = new MauSacRepository();
        dongSpRepository = new DongSpRepository();

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
            response.sendRedirect("/chi-tiet-sp/index");
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("dsSanPham", sanPhamRepository.findAll());
        request.setAttribute("dsNSX", nsxRepository.findAll());
        request.setAttribute("dsMauSac", mauSacRepository.findAll());
        request.setAttribute("dsDongSP", dongSpRepository.findAll());
        request.setAttribute("view", "/views/views/chi-tiet-sp/create.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void index(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("dsCtsp", chiTietSanPhamRepository.findAll());
        request.setAttribute("view", "/views/views/chi-tiet-sp/index.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("dsSanPham", sanPhamRepository.findAll());
        request.setAttribute("dsNSX", nsxRepository.findAll());
        request.setAttribute("dsMauSac", mauSacRepository.findAll());
        request.setAttribute("dsDongSP", dongSpRepository.findAll());

        UUID id = UUID.fromString(request.getParameter("id"));

        ChiTietSp ctsp = chiTietSanPhamRepository.findByID(id);
        request.setAttribute("ctsp", ctsp);

        request.setAttribute("idSanPham", chiTietSanPhamRepository.findIdSanPhamById(id));
        request.setAttribute("idNSX", chiTietSanPhamRepository.findIdNSXById(id));
        request.setAttribute("idMauSac", chiTietSanPhamRepository.findIdMauSacById(id));
        request.setAttribute("idDongSP", chiTietSanPhamRepository.findIdDongSpById(id));

        request.setAttribute("view", "/views/views/chi-tiet-sp/edit.jsp");
        request.getRequestDispatcher("/views/views/layout.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try {

                // Lấy các giá trị từ form
                UUID idDong = UUID.fromString(request.getParameter("idDongSP"));
                UUID idNSX = UUID.fromString(request.getParameter("idNSX"));
                UUID idMauSac = UUID.fromString(request.getParameter("idMauSac"));
                UUID idSp = UUID.fromString(request.getParameter("idSanPham"));
                UUID id = UUID.fromString(request.getParameter("id"));

                // Tạo đối tượng
                DongSp dongSp = new DongSp();
                dongSp.setId(idDong);
                NSX nsx = new NSX();
                nsx.setId(idNSX);
                MauSac mauSac = new MauSac();
                mauSac.setId(idMauSac);
                SanPham sanPham = new SanPham();
                sanPham.setId(idSp);

                // Set các giá trị vào đối tượng
                ChiTietSp chiTietSp = chiTietSanPhamRepository.findByID(id);
                chiTietSp.setSanPham(sanPham);
                chiTietSp.setMauSac(mauSac);
                chiTietSp.setNsx(nsx);
                chiTietSp.setDongSp(dongSp);
                chiTietSp.setNamBaoHanh(Integer.parseInt(request.getParameter("namBaoHanh")));
                chiTietSp.setMoTa(request.getParameter("moTa"));
                chiTietSp.setSoLuongTon(Integer.parseInt(request.getParameter("soLuongTon")));
                chiTietSp.setGiaNhap(new BigDecimal(request.getParameter("giaNhap")));
                chiTietSp.setGiaBan(new BigDecimal(request.getParameter("giaBan")));
                if (chiTietSanPhamRepository.update(chiTietSp)) {
//                    request.getSession().setAttribute("message", "Update thành công");
                    response.sendRedirect(request.getContextPath() + "/chi-tiet-sp/index");
                }else {
                    request.getSession().setAttribute("errorMessage", "Update thất bại");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChiTietSp ctsp = chiTietSanPhamRepository.findByID(UUID.fromString(String.valueOf(id)));
        chiTietSanPhamRepository.delete(ctsp);
        response.sendRedirect("/chi-tiet-sp/index");
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UUID idSanPham = UUID.fromString(request.getParameter("idSanPham"));
            SanPham sp = new SanPham();
            sp.setId(idSanPham);

            UUID idNSX = UUID.fromString(request.getParameter("idNSX"));
            NSX nsx = new NSX();
            nsx.setId(idNSX);

            UUID idMauSac = UUID.fromString(request.getParameter("idMauSac"));
            MauSac ms = new MauSac();
            ms.setId(idMauSac);

            UUID idDongSP = UUID.fromString(request.getParameter("idDongSP"));
            DongSp dsp = new DongSp();
            dsp.setId(idDongSP);

            ChiTietSp chiTietSanPham = new ChiTietSp();
            chiTietSanPham.setSanPham(sp);
            chiTietSanPham.setNsx(nsx);
            chiTietSanPham.setMauSac(ms);
            chiTietSanPham.setDongSp(dsp);

            BeanUtils.populate(chiTietSanPham, request.getParameterMap());
            chiTietSanPhamRepository.insert(chiTietSanPham);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/chi-tiet-sp/index");
    }
}
