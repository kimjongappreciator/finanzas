package com.upc.finanzas.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Inflation")
@Data
public class Inflation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false, name = "bono_id")
    private Long bonoId;
    @Column(nullable = false, name = "inflation_year")
    private Integer Anio;
    @Column(length = 10, nullable = false , name = "inflation")
    private Float Inflacion;
}
