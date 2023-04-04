package com.example.ass_sof3011_hibenate.domain_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietSp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "NamBH")
    private int namBaoHanh;

    @Column(name = "MoTa", columnDefinition = "Nvarchar(100)")
    private String moTa;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "GiaNhap", columnDefinition = "Decimal(20,0)")
    private Integer giaNhap;

    @Column(name = "GiaBan", columnDefinition = "Decimal(20,0)")
    private Integer giaBan;
    @ManyToOne
    @JoinColumn(name = "idSp")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "idNSX")
    private NSX nsx;

    @ManyToOne
    @JoinColumn(name = "idMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idDong")
    private DongSp dongSp;
    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    private List<GioHangChiTiet> listGioHangChiTiet;

    @OneToMany(mappedBy = "chiTietSp", fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> listHoaDonChiTiet;

}
