package com.example.ass_sof3011_hibenate.view_model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class GioHangChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idGioHang")
    private GioHang gioHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "idChiTietSp")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "SoLuong")
    private int soLuongTon;

    @Column(name = "DonGia", columnDefinition = "Decimal(20,0)")
    private Float donGia;

    @Column(name = "DonGiaKhiGiam", columnDefinition = "Decimal(20,0)")
    private Float donGiaKhiGiam;

    public GioHangChiTiet() {
    }

    public GioHangChiTiet(GioHang gioHang, ChiTietSanPham chiTietSanPham, int soLuongTon, Float donGia, Float donGiaKhiGiam) {
        this.gioHang = gioHang;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }

    public ChiTietSanPham getChiTietSp() {
        return chiTietSanPham;
    }

    public void setChiTietSp(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public Float getDonGiaKhiGiam() {
        return donGiaKhiGiam;
    }

    public void setDonGiaKhiGiam(Float donGiaKhiGiam) {
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

   
}
