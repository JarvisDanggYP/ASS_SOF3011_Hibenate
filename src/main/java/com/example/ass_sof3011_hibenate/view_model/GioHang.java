package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table
public class GioHang implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @ManyToOne
    @JoinColumn(name = "idKH")
    private KhachHang khachHang;

    @Column(name = "idNV", columnDefinition = "uniqueidentifier")
    private String idNV;

    @Column(name = "Ma", columnDefinition = "Varchar(20)",unique = true)
    private String ma;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @Column(name = "TenNguoiNhan", columnDefinition = "Nvarchar(50)")
    private String tenNguoiNhan;

    @Column(name = "DiaChi", columnDefinition = "Nvarchar(100)")
    private String diaChi;

    @Column(name = "Sdt", columnDefinition = "Varchar(30)")
    private String sdt;

    @Column(name = "TrangThai")
    private int trangThai;

    @OneToMany(mappedBy = "gioHang", fetch = FetchType.EAGER)
    private List<GioHangChiTiet> listGioHangChiTiet;

    public GioHang(String id, KhachHang khachHang, String idNV, String ma, Date ngayTao, Date ngayThanhToan, String tenNguoiNhan, String diaChi, String sdt, int trangThai, List<GioHangChiTiet> listGioHangChiTiet) {
        this.id = id;
        this.khachHang = khachHang;
        this.idNV = idNV;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trangThai = trangThai;
        this.listGioHangChiTiet = listGioHangChiTiet;
    }

    public GioHang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public List<GioHangChiTiet> getListGioHangChiTiet() {
        return listGioHangChiTiet;
    }

    public void setListGioHangChiTiet(List<GioHangChiTiet> listGioHangChiTiet) {
        this.listGioHangChiTiet = listGioHangChiTiet;
    }
}
