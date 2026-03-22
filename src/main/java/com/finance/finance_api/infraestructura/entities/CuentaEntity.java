package com.finance.finance_api.infraestructura.entities;

import com.finance.finance_api.infraestructura.enums.TipoCuenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tipo_de_cuenta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Column(name = "saldo", nullable = false)
    @Builder.Default
    private BigInteger saldo = BigInteger.valueOf(0);

    @Column(name = "alias", nullable = false, length = 30)
    @Builder.Default
    private String alias = "  ";

    @Column(name = "titular", nullable = false, length = 30)
    private String titular;

    @Column(name = "numero_cuenta", nullable = false, length = 4)
    private String cuenta;

    @ManyToOne
    @JoinColumn(name = "banco_id", nullable = false)
    private BancoEntity banco;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditoEntity> creditos = new ArrayList<>();

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TarjetaEntity> tarjetas = new ArrayList<>();

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AhorroEntity> ahorros = new ArrayList<>();









}
