package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.response.AhorroResponseDto;
import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.AhorroCreateDto;

import java.util.List;
import java.util.UUID;

public interface AhorroInterface {
    ApiResponseDto<List<AhorroResponseDto>> getAhorros();
    ApiResponseDto<AhorroResponseDto> getAhorroById(UUID id);
    ApiResponseDto<AhorroResponseDto> postAhorro(AhorroCreateDto ahorro);
    ApiResponseDto<AhorroResponseDto> updateAhorro(UUID id, AhorroCreateDto ahorro);
    ApiResponseDto<Void> deleteAhorro(UUID id);
}
