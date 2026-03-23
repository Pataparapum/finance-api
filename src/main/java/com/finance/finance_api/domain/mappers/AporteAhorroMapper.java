package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.AporteAhorroDto;
import com.finance.finance_api.infraestructura.entities.AporteAhorroEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AporteAhorroMapper {

    private AporteAhorroMapper() {}

    public static AporteAhorroDto toDto(AporteAhorroEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("AporteAhorroEntity no puede ser nulo");
        }

        return AporteAhorroDto.builder()
                .monto(entity.getMonto())
                .fecha(entity.getFecha())
                .nota(entity.getNota())
                .build();
    }

    public static AporteAhorroEntity toEntity(AporteAhorroDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("AporteAhorroDto no puede ser nulo");
        }
        if (dto.getMonto() == null) {
            throw new IllegalArgumentException("El monto del aporte no puede ser nulo");
        }
        if (dto.getMonto().signum() <= 0) {
            throw new IllegalArgumentException("El monto del aporte debe ser mayor a cero");
        }
        if (dto.getFecha() == null) {
            throw new IllegalArgumentException("La fecha del aporte no puede ser nula");
        }

        return AporteAhorroEntity.builder()
                .monto(dto.getMonto())
                .fecha(dto.getFecha())
                .nota(dto.getNota() != null && !dto.getNota().isBlank() ? dto.getNota() : "  ")
                .build();
    }

    public static List<AporteAhorroDto> toDtoList(List<AporteAhorroEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(AporteAhorroMapper::toDto)
                .collect(Collectors.toList());
    }
}
