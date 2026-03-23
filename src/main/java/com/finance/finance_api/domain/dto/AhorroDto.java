package com.finance.finance_api.domain.dto;

import com.finance.finance_api.infraestructura.enums.EstadoAhorro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AhorroDto {

    private String nombre;
    private String descripcion;
    private BigInteger objetivo;
    private LocalDate inicio;
    private LocalDate finalizacion;
    private EstadoAhorro estado;
    private List<AporteAhorroDto> aportes;
}
