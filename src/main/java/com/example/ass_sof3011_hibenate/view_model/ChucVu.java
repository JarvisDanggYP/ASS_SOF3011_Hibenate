package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class ChucVu implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "Ma", columnDefinition = "Varchar(20)",unique = true)
    private String ma;

    @Column(name = "Ten", columnDefinition = "Nvarchar(30)")
    private String ten;

    @OneToMany(mappedBy = "chucVu", fetch = FetchType.EAGER)
    private List<NhanVien> listNhanVien;

    public ChucVu(String id, String ma, String ten, List<NhanVien> listNhanVien) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.listNhanVien = listNhanVien;
    }

    public ChucVu() {
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

    public List<NhanVien> getListNhanVien() {
        return listNhanVien;
    }

    public void setListNhanVien(List<NhanVien> listNhanVien) {
        this.listNhanVien = listNhanVien;
    }
}
