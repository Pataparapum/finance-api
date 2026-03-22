package com.finance.finance_api.infraestructura.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "aporte_ahorro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AporteAhorroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "monto", nullable = false)
    private BigInteger monto;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "nota", nullable = false)
    @Builder.Default
    private String nota = "  ";

    @ManyToOne
    @JoinColumn(name = "ahorro_id", nullable = false)
    private AhorroEntity ahorro;
}
