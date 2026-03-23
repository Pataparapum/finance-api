package com.finance.finance_api.domain.dto;

import com.finance.finance_api.infraestructura.enums.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDto {

    private TipoCuenta tipoCuenta;
    private BigInteger saldo;
    private String alias;
    private String titular;
    private String numeroCuenta;
    private List<TarjetaDto> tarjetas;
    private List<CreditoDto> credito;
    private List<AhorroDto> ahorro;
}
