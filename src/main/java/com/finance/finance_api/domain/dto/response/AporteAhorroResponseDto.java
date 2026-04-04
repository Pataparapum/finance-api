package com.finance.finance_api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AporteAhorroResponseDto {

    private UUID id;
    private BigInteger monto;
    private LocalDate fecha;
    private String nota;
}
