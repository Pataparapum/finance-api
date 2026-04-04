package com.finance.finance_api.domain.dto.response;

import com.finance.finance_api.infraestructura.enums.EstadoAhorro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AhorroResponseDto {

    private UUID id;
    private String nombre;
    private String descripcion;
    private BigInteger montoObjetivo;
    private BigInteger montoMensual;
    private LocalDate inicio;
    private LocalDate fin;
    private EstadoAhorro estado;
    private List<AporteAhorroResponseDto> aportes;
}
