package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.response.BancoResponseDto;
import com.finance.finance_api.domain.dto.create.BancoCreateDto;

import java.util.List;
import java.util.UUID;

public interface BancoInterface {
    ApiResponseDto<List<BancoResponseDto>> getBancos();
    ApiResponseDto<BancoResponseDto> getBancoById(UUID id);
    ApiResponseDto<BancoResponseDto> postBanco(BancoCreateDto banco);
    ApiResponseDto<Void> deleteBanco(UUID id);
}
