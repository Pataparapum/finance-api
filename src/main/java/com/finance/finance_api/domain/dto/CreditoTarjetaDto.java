package com.finance.finance_api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditoTarjetaDto {

    private BigInteger limite;
    private BigInteger disponible;
    private int corte;
    private int pago;
}
