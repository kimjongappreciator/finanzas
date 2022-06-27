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
    private String nombre;
    @Column(length = 10, nullable = true)
    private Integer importancia;
    @Column
    private Date createdAt = new Date();
    @Column(length = 20, nullable = true)
    private BigDecimal VN;
    @Column(length = 20, nullable = true)
    private BigDecimal VC;
    @Column(nullable = true)
    private Integer anios;
    @Column(nullable = true)
    private Integer periodo_cupon_id;
    @Column(nullable = true)
    private Integer plazo_Gracia;
    @Column(nullable = true)
    private Integer DXA;
    @Column(length = 15, nullable = true)
    private String tipo_tasa;
    @Column(length = 10, nullable = true)
    private Integer periodo_capitalizacion_id;
    @Column(length = 10, nullable = true)
    private BigDecimal p_tasa_interes;
    @Column(length = 10, nullable = true)
    private BigDecimal p_tasa_anual_descuento;
    @Column(length = 10, nullable = true)
    private BigDecimal p_impuesto;
    @Column(nullable = true)
    private Date emision;
    @Column(length = 10, nullable = true)
    private BigDecimal p_prima;
    @Column(length = 10, nullable = true)
    private BigDecimal p_estructuracion;
    @Column(length = 10, nullable = true)
    private BigDecimal p_colocacion;
    @Column(length = 10, nullable = true)
    private BigDecimal p_flotacion;
    @Column(length = 10, nullable = true)
    private BigDecimal p_cavali;
    @Column
    private String tipo_moneda;

    //@Valid
    //@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "period_id")
    //private List<Period> periods = new ArrayList<>();
}
