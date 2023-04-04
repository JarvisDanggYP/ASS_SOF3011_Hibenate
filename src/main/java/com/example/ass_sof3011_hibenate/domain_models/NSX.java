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
@Table(name = "NSX")
@Getter

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NSX implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten", columnDefinition = "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "nsx", fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSP;

}
