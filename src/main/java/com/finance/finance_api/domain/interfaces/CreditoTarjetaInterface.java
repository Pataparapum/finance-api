package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.CreditoTarjetaDto;

import java.util.List;
import java.util.UUID;

public interface CreditoTarjetaInterface {
    ApiResponseDto<List<CreditoTarjetaDto>> getCreditoTarjetas();
    ApiResponseDto<CreditoTarjetaDto> getCreditoTarjetaById(UUID id);
    ApiResponseDto<CreditoTarjetaDto> postCreditotarjeta(CreditoTarjetaDto creditoTarjeta);
    ApiResponseDto<CreditoTarjetaDto> updateCreditoTarjeta(UUID id, CreditoTarjetaDto creditoTarjeta);
    ApiResponseDto<CreditoTarjetaDto> deleteCreditoTarjeta(UUID id);
}
