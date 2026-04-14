package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.AporteAhorroCreateDto;
import com.finance.finance_api.domain.dto.response.AporteAhorroResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.AporteAhorroInterface;
import com.finance.finance_api.domain.mappers.AporteAhorroMapper;
import com.finance.finance_api.infraestructura.entities.AporteAhorroEntity;
import com.finance.finance_api.infraestructura.repositories.AporteAhorroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AporteAhorroService implements AporteAhorroInterface {

    private final AporteAhorroRepository aporteAhorroRepository;
    private final AporteAhorroMapper aporteAhorroMapper;

    @Override
    public ApiResponseDto<List<AporteAhorroResponseDto>> getAportes() {

        List<AporteAhorroEntity> entities = aporteAhorroRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron aportes de ahorro");
        }

        List<AporteAhorroResponseDto> response = aporteAhorroMapper.toDtoList(entities);

        return ApiResponseDto.<List<AporteAhorroResponseDto>>builder()
                .mensaje("Aportes obtenidos")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<AporteAhorroResponseDto> getAporteById(UUID id) {

        AporteAhorroEntity entity = aporteAhorroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aporte de id " + id + " no encontrado"));

        AporteAhorroResponseDto response = aporteAhorroMapper.toDto(entity);

        return ApiResponseDto.<AporteAhorroResponseDto>builder()
                .mensaje("Aporte de id " + id + " encontrado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<AporteAhorroResponseDto> postAporte(AporteAhorroCreateDto aporte) {

        AporteAhorroEntity entity = aporteAhorroRepository.save(aporteAhorroMapper.toEntity(aporte));
        AporteAhorroResponseDto response = aporteAhorroMapper.toDto(entity);

        return ApiResponseDto.<AporteAhorroResponseDto>builder()
                .mensaje("Aporte creado con exito")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<AporteAhorroResponseDto> putAporte(UUID id, AporteAhorroCreateDto aporte) {

        AporteAhorroEntity entity = aporteAhorroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El aporte de id " + id + " no existe"));

        aporteAhorroMapper.updateEntity(aporte, entity);

        AporteAhorroEntity updated = aporteAhorroRepository.save(entity);
        AporteAhorroResponseDto response = aporteAhorroMapper.toDto(updated);

        return ApiResponseDto.<AporteAhorroResponseDto>builder()
                .mensaje("Aporte actualizado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteAporte(UUID id) {

        aporteAhorroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aporte de id " + id + " no encontrado"));

        aporteAhorroRepository.deleteById(id);

        return ApiResponseDto.<Void>builder()
                .mensaje("Aporte borrado")
                .data(null)
                .build();
    }
}
