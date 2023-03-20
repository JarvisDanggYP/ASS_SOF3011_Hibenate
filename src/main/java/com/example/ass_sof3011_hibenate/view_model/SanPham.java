package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "sanpham")
public class SanPham implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "idSp", columnDefinition = "uniqueidentifier")
    private String idSanPham;

    @Column(name = "ma", unique = true)
    private String maSP;
    @Column(name = "ten", columnDefinition = "Nvarchar(100)")
    private String tenSp;
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<ChiTietSanPham> lstSP;


    public SanPham() {
    }

    public SanPham(String idSanPham, String maSP, String tenSp, List<ChiTietSanPham> lstSP) {
        this.idSanPham = idSanPham;
        this.maSP = maSP;
        this.tenSp = tenSp;
        this.lstSP = lstSP;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public List<ChiTietSanPham> getLstSP() {
        return lstSP;
    }

    public void setLstSP(List<ChiTietSanPham> lstSP) {
        this.lstSP = lstSP;
    }

    @Override
    public String toString() {
        return tenSp;
    }


}
