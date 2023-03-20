
package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "chitietsanpham")
public class ChiTietSanPham {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "NamBH")
    private int namBaoHanh;

    @Column(name = "MoTa", columnDefinition = "Nvarchar(100)")
    private String moTa;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "GiaNhap", columnDefinition = "Decimal(20,0)")
    private Integer giaNhap;

    @Column(name = "GiaBan", columnDefinition = "Decimal(20,0)")
    private Integer giaBan;
    @ManyToOne
    @JoinColumn(name = "idSp")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idNSX")
    private NhaSanXuat nhaSanXuat;

    @ManyToOne
    @JoinColumn(name = "idMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idDong")
    private DongSanPham dongSanPham;
    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.EAGER)
    private List<GioHangChiTiet> listGioHangChiTiet;

    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.EAGER)
    private List<HoaDonChiTiet> listHoaDonChiTiet;

    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String id, int namBaoHanh, String moTa, int soLuongTon, Integer giaNhap, Integer giaBan, SanPham sanPham, NhaSanXuat nhaSanXuat, MauSac mauSac, DongSanPham dongSanPham) {
        this.id = id;
        this.namBaoHanh = namBaoHanh;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.sanPham = sanPham;
        this.nhaSanXuat = nhaSanXuat;
        this.mauSac = mauSac;
        this.dongSanPham = dongSanPham;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNamBaoHanh() {
        return namBaoHanh;
    }

    public void setNamBaoHanh(int namBaoHanh) {
        this.namBaoHanh = namBaoHanh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Integer getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Integer giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Integer getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public NhaSanXuat getNsx() {
        return nhaSanXuat;
    }

    public void setNsx(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public DongSanPham getDongSp() {
        return dongSanPham;
    }

    public void setDongSp(DongSanPham dongSanPham) {
        this.dongSanPham = dongSanPham;
    }

}
