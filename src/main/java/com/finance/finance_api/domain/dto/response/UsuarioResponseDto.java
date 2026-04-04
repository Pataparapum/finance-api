package com.finance.finance_api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {

    private UUID id;
    private String name;
    private List<BancoResponseDto> bancos;

}
