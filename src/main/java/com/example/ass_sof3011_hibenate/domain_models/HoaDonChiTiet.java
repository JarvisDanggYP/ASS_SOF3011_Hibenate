package com.example.ass_sof3011_hibenate.domain_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "HoaDonChiTiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idHoaDon")
    private HoaDon hoaDon;

    @Id
    @ManyToOne
    @JoinColumn(name = "idChiTietSp")
    private ChiTietSp chiTietSp;

    @Column(name = "SoLuong")
    private Integer soLuongTon;

    @Column(name = "DonGia")
    private BigDecimal donGia;


}
