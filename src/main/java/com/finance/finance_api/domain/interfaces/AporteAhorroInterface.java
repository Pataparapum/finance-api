package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.AporteAhorroDto;

import java.util.List;
import java.util.UUID;

public interface AporteAhorroInterface {
    ApiResponseDto<List<AporteAhorroDto>> getAportes();
    ApiResponseDto<AporteAhorroDto> getAporteById(UUID id);
    ApiResponseDto<AporteAhorroDto> postAporte(AporteAhorroDto aporte);
    ApiResponseDto<AporteAhorroDto> updateAporte(UUID id, AporteAhorroDto aporte);
    ApiResponseDto<Void> deleteAporte(UUID id);
}
