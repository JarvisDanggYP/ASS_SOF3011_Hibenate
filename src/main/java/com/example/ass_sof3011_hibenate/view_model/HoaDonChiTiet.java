package com.example.ass_sof3011_hibenate.view_model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class HoaDonChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idHoaDon")
    private HoaDon hoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "idChiTietSp")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "SoLuong")
    private int soLuongTon;

    @Column(name = "DonGia", columnDefinition = "Decimal(20,0)")
    private Float donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(HoaDon hoaDon, ChiTietSanPham chiTietSanPham, int soLuongTon, Float donGia) {
        this.hoaDon = hoaDon;
        this.chiTietSanPham = chiTietSanPham;
        this.soLuongTon = soLuongTon;
        this.donGia = donGia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
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


}
