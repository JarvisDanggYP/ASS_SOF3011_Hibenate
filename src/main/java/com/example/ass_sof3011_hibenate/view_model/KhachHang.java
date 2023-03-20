package com.example.ass_sof3011_hibenate.view_model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table
public class KhachHang implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "Ma", columnDefinition = "Varchar(20)",unique = true)
    private String ma;

    @Column(name = "Ten", columnDefinition = "Nvarchar(30)")
    private String ten;

    @Column(name = "TenDem", columnDefinition = "Nvarchar(30)")
    private String tenDem;

    @Column(name = "Ho", columnDefinition = "Nvarchar(30)")
    private String ho;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi", columnDefinition = "Nvarchar(100)")
    private String diaChi;

    @Column(name = "ThanhPho", columnDefinition = "Nvarchar(50)")
    private String thanhPho;

    @Column(name = "QuocGia", columnDefinition = "Nvarchar(50)")
    private String quocGia;

    @Column(name = "Sdt", columnDefinition = "Varchar(30)")
    private String sdt;

    @Column(name = "MatKhau", columnDefinition = "Varchar(MAX)")
    private String matKhau;

    @Column(name = "Email", columnDefinition = "Varchar(MAX)")
    private String email;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER)
    private List<GioHang> listGioHang;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER)
    private List<HoaDon> listHoaDon;

    public KhachHang(String id, String ma, String ten, String tenDem, String ho, Date ngaySinh, String diaChi, String thanhPho, String quocGia, String sdt, String matKhau, String email, List<GioHang> listGioHang, List<HoaDon> listHoaDon) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
        this.sdt = sdt;
        this.matKhau = matKhau;
        this.email = email;
        this.listGioHang = listGioHang;
        this.listHoaDon = listHoaDon;
    }

    public KhachHang(String id, String ma, String ten, String tenDem, String ho, Date ngaySinh, String diaChi, String thanhPho, String quocGia, String sdt, String matKhau, String email) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
        this.sdt = sdt;
        this.matKhau = matKhau;
        this.email = email;
    }
    
    

    public KhachHang() {
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

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GioHang> getListGioHang() {
        return listGioHang;
    }

    public void setListGioHang(List<GioHang> listGioHang) {
        this.listGioHang = listGioHang;
    }

    public List<HoaDon> getListHoaDon() {
        return listHoaDon;
    }

    public void setListHoaDon(List<HoaDon> listHoaDon) {
        this.listHoaDon = listHoaDon;
    }
}
