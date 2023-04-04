package com.example.ass_sof3011_hibenate.domain_models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "MauSac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MauSac implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "ma", unique = true)
    private String ma;
    @Column(name = "ten",columnDefinition =  "Nvarchar(100)")
    private String ten;
    @OneToMany(mappedBy = "mauSac", fetch = FetchType.LAZY)
    private List<ChiTietSp> lstSanPham;

    public MauSac(String ma, String ten) {
    }
    public MauSac(String ma) {
    }

}
