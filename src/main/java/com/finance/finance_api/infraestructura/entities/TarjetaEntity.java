package com.finance.finance_api.infraestructura.entities;

import com.finance.finance_api.infraestructura.enums.TipoTarjeta;
import com.finance.finance_api.infraestructura.enums.TipoUso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tarjeta")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TarjetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "num_tarjeta", nullable = false, length = 4)
    private String numTarjeta;

    @Column(name = "mes_caducidad", nullable = false, length = 2)
    private String mes;

    @Column(name = "año_caducidad", nullable = false, length = 4)
    private String año;

    @Column(name = "tipo_tarjeta", nullable = false)
    private TipoTarjeta tipotarjeta;

    @Column(name = "uso", nullable = false)
    private TipoUso uso;

    @Column(name = "nombre_titular", nullable = false, length = 30)
    private String titular;

    @Column(name = "alias", nullable = false, length = 30)
    @Builder.Default
    private String alias = "sin alias";

    @ManyToOne
    @JoinColumn(name ="cuenta_id", nullable = false)
    private CuentaEntity cuenta;
}
