package com.upc.finanzas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "bono")
public class Bono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private Long userId;
    @Column(length = 50, nullable = true)
    private String Name;
    @Column(length = 10, nullable = true)
    private Integer importance;
    @Column
    private Date createdAt;
    @Column(length = 20, nullable = true)
    private BigDecimal vN;
    @Column(length = 20, nullable = true)
    private BigDecimal vC;
    @Column(nullable = true)
    private Integer numOfYears;
    @Column(nullable = true)
    private Integer couponPeriod;
    @Column(nullable = true)
    private Integer gracePeriod;
    @Column(nullable = true)
    private Integer dxA;
    @Column(length = 15, nullable = true)
    private String rateType;
    @Column(length = 10, nullable = true)
    private Integer capPeriod;
    @Column(length = 10, nullable = true)
    private BigDecimal interestRate;
    @Column(length = 10, nullable = true)
    private BigDecimal annualDiscountRate;
    @Column(length = 10, nullable = true)
    private BigDecimal iR;
    @Column(nullable = true)
    private Date emissionDate;
    @Column(length = 10, nullable = true)
    private BigDecimal primalPercentage;
    @Column(length = 10, nullable = true)
    private BigDecimal structuringPercentage;
    @Column(length = 10, nullable = true)
    private BigDecimal collocationPercentage;
    @Column(length = 10, nullable = true)
    private BigDecimal flotationPercentage;
    @Column(length = 10, nullable = true)
    private BigDecimal cavaliPercentage;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bono_id")
    private List<Inflation> inflations = new ArrayList<>();

}
