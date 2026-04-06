package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.response.AporteAhorroResponseDto;
import com.finance.finance_api.domain.dto.create.AporteAhorroCreateDto;

import java.util.List;
import java.util.UUID;

public interface AporteAhorroInterface {
    ApiResponseDto<List<AporteAhorroResponseDto>> getAportes();
    ApiResponseDto<AporteAhorroResponseDto> getAporteById(UUID id);
    ApiResponseDto<AporteAhorroResponseDto> postAporte(AporteAhorroCreateDto aporte);
    ApiResponseDto<AporteAhorroResponseDto> putAporte(UUID id, AporteAhorroCreateDto aporte);
    ApiResponseDto<Void> deleteAporte(UUID id);
}
