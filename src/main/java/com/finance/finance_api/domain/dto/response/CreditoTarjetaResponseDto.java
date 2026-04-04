package com.finance.finance_api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditoTarjetaResponseDto {

    private UUID id;
    private BigInteger limite;
    private BigInteger disponible;
    private BigInteger usado;
    private byte corte;
    private byte pago;
}
