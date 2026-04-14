package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.CreditoCreateDto;
import com.finance.finance_api.domain.dto.response.CreditoResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.CreditoInterface;
import com.finance.finance_api.domain.mappers.CreditoMapper;
import com.finance.finance_api.infraestructura.entities.CreditoEntity;
import com.finance.finance_api.infraestructura.repositories.CreditoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditoService implements CreditoInterface {

    private final CreditoRepository creditoRepository;
    private final CreditoMapper creditoMapper;

    @Override
    public ApiResponseDto<List<CreditoResponseDto>> getCreditos() {

        List<CreditoEntity> entities = creditoRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron creditos");
        }

        List<CreditoResponseDto> response = creditoMapper.toDtoList(entities);

        return ApiResponseDto.<List<CreditoResponseDto>>builder()
                .mensaje("Creditos obtenidos")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CreditoResponseDto> getCreditoById(UUID id) {

        CreditoEntity entity = creditoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credito de id " + id + " no encontrado"));

        CreditoResponseDto response = creditoMapper.toDto(entity);

        return ApiResponseDto.<CreditoResponseDto>builder()
                .mensaje("Credito de id " + id + " encontrado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CreditoResponseDto> postCredito(CreditoCreateDto credito) {

        CreditoEntity entity = creditoRepository.save(creditoMapper.toEntity(credito));
        CreditoResponseDto response = creditoMapper.toDto(entity);

        return ApiResponseDto.<CreditoResponseDto>builder()
                .mensaje("Credito creado con exito")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<CreditoResponseDto> putCredito(UUID id, CreditoCreateDto credito) {

        CreditoEntity entity = creditoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El credito de id " + id + " no existe"));

        creditoMapper.updateEntity(credito, entity);

        CreditoEntity updated = creditoRepository.save(entity);
        CreditoResponseDto response = creditoMapper.toDto(updated);

        return ApiResponseDto.<CreditoResponseDto>builder()
                .mensaje("Credito actualizado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteCredito(UUID id) {

        creditoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credito de id " + id + " no encontrado"));

        creditoRepository.deleteById(id);

        return ApiResponseDto.<Void>builder()
                .mensaje("Credito borrado")
                .data(null)
                .build();
    }
}
