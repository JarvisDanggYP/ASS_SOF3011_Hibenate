package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "mausac")
public class MauSac implements Serializable {
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "idMauSac", columnDefinition = "uniqueidentifier")
    private String idMau;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten", columnDefinition = "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "mauSac", fetch = FetchType.EAGER)
    private List<ChiTietSanPham> lstSanPham;

    public MauSac() {
    }

    public MauSac(String idMau, String ma, String ten, List<ChiTietSanPham> lstSanPham) {
        this.idMau = idMau;
        this.ma = ma;
        this.ten = ten;
        this.lstSanPham = lstSanPham;
    }

    public String getIdMau() {
        return idMau;
    }

    public void setIdMau(String idMau) {
        this.idMau = idMau;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }


    public List<ChiTietSanPham> getLstSanPham() {
        return lstSanPham;
    }

    public void setLstSanPham(List<ChiTietSanPham> lstSanPham) {
        this.lstSanPham = lstSanPham;
    }

    @Override
    public String toString() {
        return ten;
    }
}
