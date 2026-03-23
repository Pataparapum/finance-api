package com.finance.finance_api.domain.dto;

import com.finance.finance_api.infraestructura.enums.EstadoCredito;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditoDto {

    private String alias;
    private BigInteger montoOriginal;
    private int cuotaTotal;
    private int cuotaMensual;
    private int cuotaPagada;
    private BigDecimal tasa;
    private LocalDate inicio;
    private LocalDate finalizacion;
    private LocalDate proximo;
    private EstadoCredito estado;
}
