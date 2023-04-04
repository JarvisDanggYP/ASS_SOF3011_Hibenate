/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ass_sof3011_hibenate.domain_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DongSp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DongSp implements Serializable{
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten",columnDefinition =  "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "dongSp", fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSanPham;

    public DongSp(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }
}
