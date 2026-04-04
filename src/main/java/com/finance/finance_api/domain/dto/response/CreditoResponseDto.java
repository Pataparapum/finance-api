package com.finance.finance_api.domain.dto.response;

import com.finance.finance_api.infraestructura.enums.EstadoCredito;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditoResponseDto {

    private UUID id;
    private String alias;
    private BigInteger montoOriginal;
    private byte cuotasTotales;
    private byte cuotaMensual;
    private byte cuotasPagadas;
    private byte cuotasRestante;
    private BigDecimal tasa;
    private LocalDate inicio;
    private LocalDate fin;
    private LocalDate proximo;
    private EstadoCredito estado;
}
