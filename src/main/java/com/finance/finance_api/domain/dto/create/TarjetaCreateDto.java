package com.finance.finance_api.domain.dto.create;

import com.finance.finance_api.infraestructura.enums.TipoTarjeta;
import com.finance.finance_api.infraestructura.enums.TipoUso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaCreateDto {

    private String numTarjeta;
    private String mes;
    private String año;
    private TipoTarjeta tipoTarjeta;
    private TipoUso uso;
    private String alias;
    private CreditoTarjetaCreateDto creditoTarjeta;
    private UUID cuenta;

}
