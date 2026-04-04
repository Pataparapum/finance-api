package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.response.TarjetaResponseDto;
import com.finance.finance_api.domain.dto.create.TarjetaCreateDto;

import java.util.List;
import java.util.UUID;

public interface TarjetaInterface {
    ApiResponseDto<List<TarjetaResponseDto>> getTarjetas();
    ApiResponseDto<TarjetaResponseDto> getTarjetaById(UUID id);
    ApiResponseDto<TarjetaResponseDto> postTarjeta(TarjetaCreateDto tarjeta);
    ApiResponseDto<TarjetaResponseDto> updateTarjeta(UUID id, TarjetaCreateDto tarjeta);
    ApiResponseDto<Void> deleteTarjeta(UUID id);
}
