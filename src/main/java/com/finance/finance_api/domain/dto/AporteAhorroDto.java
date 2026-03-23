package com.finance.finance_api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AporteAhorroDto {

    private BigInteger monto;
    private LocalDate fecha;
    private String nota;
}
