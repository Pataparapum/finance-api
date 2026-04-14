package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.CuentaCreateDto;
import com.finance.finance_api.domain.dto.response.CuentaResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.CuentaInterface;
import com.finance.finance_api.domain.mappers.CuentaMapper;
import com.finance.finance_api.infraestructura.entities.CuentaEntity;
import com.finance.finance_api.infraestructura.repositories.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CuentaService implements CuentaInterface {

    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    public ApiResponseDto<List<CuentaResponseDto>> getCuentas() {

        List<CuentaEntity> entities = cuentaRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron cuentas");
        }

        List<CuentaResponseDto> response = cuentaMapper.toDtoList(entities);

        return ApiResponseDto.<List<CuentaResponseDto>>builder()
                .mensaje("Cuentas obtenidas")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CuentaResponseDto> getCuentaByid(UUID id) {

        CuentaEntity entity = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta de id " + id + " no encontrada"));

        CuentaResponseDto response = cuentaMapper.toDto(entity);

        return ApiResponseDto.<CuentaResponseDto>builder()
                .mensaje("Cuenta de id " + id + " encontrada")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CuentaResponseDto> postCuenta(CuentaCreateDto cuenta) {

        CuentaEntity entity = cuentaRepository.save(cuentaMapper.toEntity(cuenta));
        CuentaResponseDto response = cuentaMapper.toDto(entity);

        return ApiResponseDto.<CuentaResponseDto>builder()
                .mensaje("Cuenta creada con exito")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteCuenta(UUID id) {

        cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta de id " + id + " no encontrada"));

        cuentaRepository.deleteById(id);

        return ApiResponseDto.<Void>builder()
                .mensaje("Cuenta borrada")
                .data(null)
                .build();
    }
}
