package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.CreditoTarjetaCreateDto;
import com.finance.finance_api.domain.dto.response.CreditoTarjetaResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.CreditoTarjetaInterface;
import com.finance.finance_api.domain.mappers.CreditoTarjetaMapper;
import com.finance.finance_api.infraestructura.entities.CreditoTarjetaEntity;
import com.finance.finance_api.infraestructura.repositories.CreditoTarjetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditoTarjetaService implements CreditoTarjetaInterface {

    private final CreditoTarjetaRepository creditoTarjetaRepository;
    private final CreditoTarjetaMapper creditoTarjetaMapper;

    @Override
    public ApiResponseDto<List<CreditoTarjetaResponseDto>> getCreditoTarjetas() {

        List<CreditoTarjetaEntity> entities = creditoTarjetaRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron creditos de tarjeta");
        }

        List<CreditoTarjetaResponseDto> response = creditoTarjetaMapper.toDtoList(entities);

        return ApiResponseDto.<List<CreditoTarjetaResponseDto>>builder()
                .mensaje("Creditos de tarjeta obtenidos")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CreditoTarjetaResponseDto> getCreditoTarjetaById(UUID id) {

        CreditoTarjetaEntity entity = creditoTarjetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credito de tarjeta de id " + id + " no encontrado"));

        CreditoTarjetaResponseDto response = creditoTarjetaMapper.toDto(entity);

        return ApiResponseDto.<CreditoTarjetaResponseDto>builder()
                .mensaje("Credito de tarjeta de id " + id + " encontrado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CreditoTarjetaResponseDto> postCreditotarjeta(CreditoTarjetaCreateDto creditoTarjeta) {

        CreditoTarjetaEntity entity = creditoTarjetaRepository.save(creditoTarjetaMapper.toEntity(creditoTarjeta));
        CreditoTarjetaResponseDto response = creditoTarjetaMapper.toDto(entity);

        return ApiResponseDto.<CreditoTarjetaResponseDto>builder()
                .mensaje("Credito de tarjeta creado con exito")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CreditoTarjetaResponseDto> putCreditoTarjeta(UUID id, CreditoTarjetaCreateDto creditoTarjeta) {

        CreditoTarjetaEntity entity = creditoTarjetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El credito de tarjeta de id " + id + " no existe"));

        creditoTarjetaMapper.updateEntity(creditoTarjeta, entity);

        CreditoTarjetaEntity updated = creditoTarjetaRepository.save(entity);
        CreditoTarjetaResponseDto response = creditoTarjetaMapper.toDto(updated);

        return ApiResponseDto.<CreditoTarjetaResponseDto>builder()
                .mensaje("Credito de tarjeta actualizado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CreditoTarjetaResponseDto> deleteCreditoTarjeta(UUID id) {

        CreditoTarjetaEntity entity = creditoTarjetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credito de tarjeta de id " + id + " no encontrado"));

        creditoTarjetaRepository.deleteById(id);

        CreditoTarjetaResponseDto response = creditoTarjetaMapper.toDto(entity);

        return ApiResponseDto.<CreditoTarjetaResponseDto>builder()
                .mensaje("Credito de tarjeta borrado")
                .data(response)
                .build();
    }
}
