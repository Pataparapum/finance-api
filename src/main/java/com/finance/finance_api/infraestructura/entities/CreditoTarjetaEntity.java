package com.finance.finance_api.infraestructura.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "credito_tarjeta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreditoTarjetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "limite_credito", nullable = false)
    private BigInteger limite;

    @Column(name = "credito_disponible", nullable = false)
    private BigInteger disponible;

    @Column(name = "dia_corte", nullable = false)
    private int corte;

    @Column(name = "dia_pago", nullable = false)
    private int pago;

    @OneToOne
    @JoinColumn(name = "tarjeta_id", nullable = false, unique = true)
    private TarjetaEntity tarjeta;
}
