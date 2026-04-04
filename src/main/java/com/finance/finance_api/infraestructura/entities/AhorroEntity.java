package com.finance.finance_api.infraestructura.entities;

import com.finance.finance_api.infraestructura.enums.EstadoAhorro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ahorro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AhorroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;

    @Column(name = "monto_objetivo", nullable = false)
    private BigInteger montoObjetivo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate inicio;

    @Column(name = "fecha_finalizacion", nullable = false)
    private LocalDate fin;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoAhorro estado;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaEntity cuenta;

    @OneToMany(mappedBy = "ahorro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AporteAhorroEntity> aportes = new ArrayList<>();
}
