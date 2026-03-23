package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.AhorroDto;
import com.finance.finance_api.domain.dto.ApiResponseDto;

import java.util.List;
import java.util.UUID;

public interface AhorroInterface {
    ApiResponseDto<List<AhorroDto>> getAhorros();
    ApiResponseDto<AhorroDto> getAhorroById(UUID id);
    ApiResponseDto<AhorroDto> postAhorro(AhorroDto ahorro);
    ApiResponseDto<AhorroDto> updateAhorro(UUID id, AhorroDto ahorro);
    ApiResponseDto<Void> delateAhorro(UUID id);
}
