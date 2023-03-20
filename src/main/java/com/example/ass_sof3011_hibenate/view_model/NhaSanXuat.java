
package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "nsx")
public class NhaSanXuat implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "idNSX", columnDefinition = "uniqueidentifier")
    private String idNhaSX;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten", columnDefinition = "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "nsx", fetch = FetchType.EAGER)
    private List<ChiTietSanPham> lstSP;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String idNhaSX, String ma, String ten, List<ChiTietSanPham> lstSP) {
        this.idNhaSX = idNhaSX;
        this.ma = ma;
        this.ten = ten;
        this.lstSP = lstSP;
    }

    public String getIdNhaSX() {
        return idNhaSX;
    }

    public void setIdNhaSX(String idNhaSX) {
        this.idNhaSX = idNhaSX;
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

    public List<ChiTietSanPham> getLstSP() {
        return lstSP;
    }

    public void setLstSP(List<ChiTietSanPham> lstSP) {
        this.lstSP = lstSP;
    }

    @Override
    public String toString() {
        return ten;
    }


}
