package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.CreditoDto;

import java.util.List;
import java.util.UUID;

public interface CreditoInterface {
    ApiResponseDto<List<CreditoDto>> getCreditos();
    ApiResponseDto<CreditoDto> getCreditoById(UUID id);
    ApiResponseDto<CreditoDto> postCredito(CreditoDto credito);
    ApiResponseDto<CreditoDto> updateCredito(UUID id, CreditoDto credito);
    ApiResponseDto<Void> deleteCredito(UUID id);
}
