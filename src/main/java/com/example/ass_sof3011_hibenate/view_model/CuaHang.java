package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class CuaHang implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "Ma", columnDefinition = "Varchar(20)",unique = true)
    private String ma;

    @Column(name = "Ten", columnDefinition = "Nvarchar(MAX)")
    private String ten;

    @Column(name = "DiaChi", columnDefinition = "Nvarchar(MAX)")
    private String diaChi;

    @Column(name = "ThanhPho", columnDefinition = "Nvarchar(MAX)")
    private String thanhPho;

    @Column(name = "QuocGia", columnDefinition = "Nvarchar(MAX)")
    private String quocGia;

    @OneToMany(mappedBy = "chucVu", fetch = FetchType.EAGER)
    private List<NhanVien> listNhanVien;

    public CuaHang(String id, String ma, String ten, String diaChi, String thanhPho, String quocGia, List<NhanVien> listNhanVien) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
        this.listNhanVien = listNhanVien;
    }

    public CuaHang() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public List<NhanVien> getListNhanVien() {
        return listNhanVien;
    }

    public void setListNhanVien(List<NhanVien> listNhanVien) {
        this.listNhanVien = listNhanVien;
    }
}
