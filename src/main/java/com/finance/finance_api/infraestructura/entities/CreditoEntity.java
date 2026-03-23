package com.finance.finance_api.infraestructura.entities;

import com.finance.finance_api.infraestructura.enums.EstadoCredito;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "credito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "alias", nullable = false)
    @Builder.Default
    private String alias = "sin alias asignado";

    @Column(name = "monto_original", nullable = false)
    private BigInteger montoOriginal;

    @Column(name = "cuotas_totales", nullable = false)
    private int cuotasTotales;

    @Column(name = "cuota_mensual", nullable = false)
    private int cuotaMensual;

    @Column(name = "cuotas_pagadas", nullable = false)
    private int cuotasPagadas;

    @Column(name = "tasa_interes", nullable = false)
    private BigDecimal tasa;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate inicio;

    @Column(name = "fecha_finalizacion", nullable = false)
    private LocalDate fin;

    @Column(name = "fecha_proximo_pago", nullable = false)
    private LocalDate proximo;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoCredito estado;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaEntity cuenta;


}
