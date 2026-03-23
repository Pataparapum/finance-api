package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.CuentaDto;

import java.util.List;
import java.util.UUID;

public interface CuentaInterface {
    ApiResponseDto<List<CuentaDto>> getCuentas();
    ApiResponseDto<CuentaDto> getCuentaByid(UUID id);
    ApiResponseDto<CuentaDto> postCuenta(CuentaDto cuenta);
    ApiResponseDto<CuentaDto> updateCuenta(UUID id, CuentaDto cuenta);
    ApiResponseDto<Void> delateCuenta(UUID id);
}
