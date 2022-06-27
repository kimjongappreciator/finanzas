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
    private String Nombre;
    @Column(length = 10, nullable = true)
    private Integer Importancia;
    @Column
    private Date createdAt = new Date();
    @Column(length = 20, nullable = true)
    private BigDecimal VN;
    @Column(length = 20, nullable = true)
    private BigDecimal VC;
    @Column(nullable = true)
    private Integer Anios;
    @Column(nullable = true)
    private Integer Periodo_Cupon_id;
    @Column(nullable = true)
    private Integer Plazo_Gracia;
    @Column(nullable = true)
    private Integer DXA;
    @Column(length = 15, nullable = true)
    private String Tipo_Tasa;
    @Column(length = 10, nullable = true)
    private Integer Periodo_Capitalizacion_id;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Tasa_Interes;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Tasa_Anual_Descuento;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Impuesto;
    @Column(nullable = true)
    private Date Emision;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Prima;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Estructuracion;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Colocacion;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Flotacion;
    @Column(length = 10, nullable = true)
    private BigDecimal P_Cavali;
    @Column
    private String tipo_moneda;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "period_id")
    private List<Period> periods = new ArrayList<>();
}
