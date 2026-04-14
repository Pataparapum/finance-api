package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.TarjetaCreateDto;
import com.finance.finance_api.domain.dto.response.TarjetaResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.TarjetaInterface;
import com.finance.finance_api.domain.mappers.TarjetaMapper;
import com.finance.finance_api.infraestructura.entities.TarjetaEntity;
import com.finance.finance_api.infraestructura.repositories.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarjetaService implements TarjetaInterface {

    private final TarjetaRepository tarjetaRepository;
    private final TarjetaMapper tarjetaMapper;

    @Override
    public ApiResponseDto<List<TarjetaResponseDto>> getTarjetas() {

        List<TarjetaEntity> entities = tarjetaRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron tarjetas");
        }

        List<TarjetaResponseDto> response = tarjetaMapper.toDtoList(entities);

        return ApiResponseDto.<List<TarjetaResponseDto>>builder()
                .mensaje("Tarjetas obtenidas")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<TarjetaResponseDto> getTarjetaById(UUID id) {

        TarjetaEntity entity = tarjetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarjeta de id " + id + " no encontrada"));

        TarjetaResponseDto response = tarjetaMapper.toDto(entity);

        return ApiResponseDto.<TarjetaResponseDto>builder()
                .mensaje("Tarjeta de id " + id + " encontrada")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<TarjetaResponseDto> postTarjeta(TarjetaCreateDto tarjeta) {

        TarjetaEntity entity = tarjetaRepository.save(tarjetaMapper.toEntity(tarjeta));
        TarjetaResponseDto response = tarjetaMapper.toDto(entity);

        return ApiResponseDto.<TarjetaResponseDto>builder()
                .mensaje("Tarjeta creada con exito")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteTarjeta(UUID id) {

        tarjetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarjeta de id " + id + " no encontrada"));

        tarjetaRepository.deleteById(id);

        return ApiResponseDto.<Void>builder()
                .mensaje("Tarjeta borrada")
                .data(null)
                .build();
    }
}
