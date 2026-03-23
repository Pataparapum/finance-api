package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.BancoDto;

import java.util.List;
import java.util.UUID;

public interface BancoInterface {
    ApiResponseDto<List<BancoDto>> getBancos();
    ApiResponseDto<BancoDto> getBancoById(UUID id);
    ApiResponseDto<BancoDto> postBanco(BancoDto banco);
    ApiResponseDto<BancoDto> updateBanco(UUID id, BancoDto banco);
    ApiResponseDto<Void> deleteBanco(UUID id);
}
