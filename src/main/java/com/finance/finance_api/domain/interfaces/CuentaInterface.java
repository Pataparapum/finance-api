package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.response.CuentaResponseDto;
import com.finance.finance_api.domain.dto.create.CuentaCreateDto;

import java.util.List;
import java.util.UUID;

public interface CuentaInterface {
    ApiResponseDto<List<CuentaResponseDto>> getCuentas();
    ApiResponseDto<CuentaResponseDto> getCuentaByid(UUID id);
    ApiResponseDto<CuentaResponseDto> postCuenta(CuentaCreateDto cuenta);
    ApiResponseDto<CuentaResponseDto> updateCuenta(UUID id, CuentaCreateDto cuenta);
    ApiResponseDto<Void> deleteCuenta(UUID id);
}
