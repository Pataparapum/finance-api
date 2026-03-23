package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.TarjetaDto;

import java.util.List;
import java.util.UUID;

public interface TarjetaInterface {
    ApiResponseDto<List<TarjetaDto>> getTarjetas();
    ApiResponseDto<TarjetaDto> getTarjetaById(UUID id);
    ApiResponseDto<TarjetaDto> postTarjeta(TarjetaDto tarjeta);
    ApiResponseDto<TarjetaDto> updateTarjeta(UUID id, TarjetaDto tarjeta);
    ApiResponseDto<Void> delateTarjeta(UUID id);
}
