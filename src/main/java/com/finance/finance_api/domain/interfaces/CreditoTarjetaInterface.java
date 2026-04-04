package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.response.CreditoTarjetaResponseDto;
import com.finance.finance_api.domain.dto.create.CreditoTarjetaCreateDto;

import java.util.List;
import java.util.UUID;

public interface CreditoTarjetaInterface {
    ApiResponseDto<List<CreditoTarjetaResponseDto>> getCreditoTarjetas();
    ApiResponseDto<CreditoTarjetaResponseDto> getCreditoTarjetaById(UUID id);
    ApiResponseDto<CreditoTarjetaResponseDto> postCreditotarjeta(CreditoTarjetaCreateDto creditoTarjeta);
    ApiResponseDto<CreditoTarjetaResponseDto> updateCreditoTarjeta(UUID id, CreditoTarjetaCreateDto creditoTarjeta);
    ApiResponseDto<CreditoTarjetaResponseDto> deleteCreditoTarjeta(UUID id);
}
