/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Dongsp")
public class DongSanPham implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "idDong", columnDefinition = "uniqueidentifier")
    private String idDongsp;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten",columnDefinition =  "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "dongSp", fetch = FetchType.EAGER)
    private List<ChiTietSanPham> lstSanPham;

    public DongSanPham() {
    }

    public DongSanPham(String idDongsp, String ma, String ten, List<ChiTietSanPham> lstSanPham) {
        this.idDongsp = idDongsp;
        this.ma = ma;
        this.ten = ten;
        this.lstSanPham = lstSanPham;
    }

    public String getIdDongsp() {
        return idDongsp;
    }

    public void setIdDongsp(String idDongsp) {
        this.idDongsp = idDongsp;
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
