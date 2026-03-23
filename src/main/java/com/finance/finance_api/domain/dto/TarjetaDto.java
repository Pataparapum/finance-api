package com.finance.finance_api.domain.dto;

import com.finance.finance_api.infraestructura.enums.TipoTarjeta;
import com.finance.finance_api.infraestructura.enums.TipoUso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaDto {

    private String numTarjeta;
    private String mes;
    private String año;
    private TipoTarjeta tipo;
    private TipoUso uso;
    private String alias;
    private CreditoTarjetaDto creditoTarjeta;

}
