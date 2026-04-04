package com.finance.finance_api.domain.dto.create;

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
public class CreditoTarjetaCreateDto {

    private BigInteger limite;
    private BigInteger disponible;
    private byte corte;
    private byte pago;
    private UUID tarjeta;
}
