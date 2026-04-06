package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.response.CreditoResponseDto;
import com.finance.finance_api.domain.dto.create.CreditoCreateDto;

import java.util.List;
import java.util.UUID;

public interface CreditoInterface {
    ApiResponseDto<List<CreditoResponseDto>> getCreditos();
    ApiResponseDto<CreditoResponseDto> getCreditoById(UUID id);
    ApiResponseDto<CreditoResponseDto> postCredito(CreditoCreateDto credito);
    ApiResponseDto<CreditoResponseDto> putCredito(UUID id, CreditoCreateDto credito);
    ApiResponseDto<Void> deleteCredito(UUID id);
}
