package com.finance.finance_api.domain.dto.response;

import com.finance.finance_api.infraestructura.enums.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponseDto {

    private UUID id;
    private TipoCuenta tipoCuenta;
    private BigInteger saldo;
    private String alias;
    private String titular;
    private String cuenta;
    private List<TarjetaResponseDto> tarjetas;
    private List<CreditoResponseDto> creditos;
    private List<AhorroResponseDto> ahorros;
}
