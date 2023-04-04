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
@Table(name = "NhanVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "Ma", columnDefinition = "Varchar(20)", unique = true)
    private String ma;

    @Column(name = "Ten", columnDefinition = "Nvarchar(30)")
    private String ten;

    @Column(name = "TenDem", columnDefinition = "Nvarchar(30)")
    private String tenDem;

    @Column(name = "Ho", columnDefinition = "Nvarchar(30)")
    private String ho;

    @Column(name = "GioiTinh", columnDefinition = "Nvarchar(10)")
    private String gioiTinh;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi", columnDefinition = "Nvarchar(100)")
    private String diaChi;

    @Column(name = "Sdt", columnDefinition = "Varchar(30)")
    private String sdt;

    @Column(name = "MatKhau", columnDefinition = "Varchar(MAX)")
    private String matKhau;

    @Column(name = "Email", columnDefinition = "Varchar(MAX)")
    private String email;

    @Column(name = "TrangThai")
    private Integer trangThai;
    @ManyToOne
    @JoinColumn(name = "idCH")
    private CuaHang cuaHang;

    @ManyToOne
    @JoinColumn(name = "idCV")
    private ChucVu chucVu;

    @ManyToOne
    @JoinColumn(name = "idGuiBC")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
    private List<HoaDon> listHoaDon;

}
