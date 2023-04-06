package com.example.ass_sof3011_hibenate.domain_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CuaHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuaHang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

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

    @OneToMany(mappedBy = "chucVu", fetch = FetchType.LAZY)
    private List<NhanVien> listNhanVien;

}
