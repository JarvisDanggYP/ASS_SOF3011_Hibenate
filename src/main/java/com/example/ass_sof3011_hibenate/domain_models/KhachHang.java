package com.example.ass_sof3011_hibenate.domain_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "KhachHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

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

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.LAZY)
    private List<GioHang> listGioHang;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.LAZY)
    private List<HoaDon> listHoaDon;

    public KhachHang(String ma, String ten, String tenDem, String ho, Date ngaySinh, String diaChi, String thanhPho, String quocGia, String sdt, String matKhau, String email) {
        this.ma = ma;
        this.tenDem = tenDem;
        this.ten = ten;
        this.ho = ho;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.quocGia = quocGia;
        this.sdt = sdt;
        this.matKhau = matKhau;
        this.email = email;
    }
}
