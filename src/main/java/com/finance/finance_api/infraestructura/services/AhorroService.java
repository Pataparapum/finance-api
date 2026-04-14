package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.AhorroCreateDto;
import com.finance.finance_api.domain.dto.response.AhorroResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.AhorroInterface;
import com.finance.finance_api.domain.mappers.AhorroMapper;
import com.finance.finance_api.infraestructura.entities.AhorroEntity;
import com.finance.finance_api.infraestructura.repositories.AhorroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AhorroService implements AhorroInterface {

    private final AhorroRepository ahorroRepository;
    private final AhorroMapper ahorroMapper;

    @Override
    public ApiResponseDto<List<AhorroResponseDto>> getAhorros() {

        List<AhorroEntity> entities = ahorroRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron ahorros");
        }

        List<AhorroResponseDto> response = ahorroMapper.toDtoList(entities);

        return ApiResponseDto.<List<AhorroResponseDto>>builder()
                .mensaje("Ahorros obtenidos")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<AhorroResponseDto> getAhorroById(UUID id) {

        AhorroEntity entity = ahorroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ahorro de id " + id + " no encontrado"));

        AhorroResponseDto response = ahorroMapper.toDto(entity);

        return ApiResponseDto.<AhorroResponseDto>builder()
                .mensaje("Ahorro de id " + id + " encontrado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<AhorroResponseDto> postAhorro(AhorroCreateDto ahorro) {

        AhorroEntity entity = ahorroRepository.save(ahorroMapper.toEntity(ahorro));
        AhorroResponseDto response = ahorroMapper.toDto(entity);

        return ApiResponseDto.<AhorroResponseDto>builder()
                .mensaje("Ahorro creado con exito")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<AhorroResponseDto> putAhorro(UUID id, AhorroCreateDto ahorro) {

        AhorroEntity entity = ahorroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ahorro de id " + id + " no existe"));

        ahorroMapper.updateEntity(ahorro, entity);

        AhorroEntity updated = ahorroRepository.save(entity);
        AhorroResponseDto response = ahorroMapper.toDto(updated);

        return ApiResponseDto.<AhorroResponseDto>builder()
                .mensaje("Ahorro actualizado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteAhorro(UUID id) {

        ahorroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ahorro de id " + id + " no encontrado"));

        ahorroRepository.deleteById(id);

        return ApiResponseDto.<Void>builder()
                .mensaje("Ahorro borrado")
                .data(null)
                .build();
    }
}
