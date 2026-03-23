package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.AhorroDto;
import com.finance.finance_api.infraestructura.entities.AhorroEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AhorroMapper {

    private AhorroMapper() {}

    public static AhorroDto toDto(AhorroEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("AhorroEntity no puede ser nulo");
        }

        return AhorroDto.builder()
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .objetivo(entity.getMontoObjetivo())
                .inicio(entity.getInicio())
                .finalizacion(entity.getFin())
                .estado(entity.getEstado())
                .aportes(entity.getAporte() != null
                        ? entity.getAporte().stream()
                        .map(AporteAhorroMapper::toDto)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static AhorroEntity toEntity(AhorroDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("AhorroDto no puede ser nulo");
        }
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del ahorro no puede estar vacío");
        }
        if (dto.getNombre().length() > 30) {
            throw new IllegalArgumentException("El nombre del ahorro no puede superar los 30 caracteres");
        }
        if (dto.getDescripcion() == null || dto.getDescripcion().isBlank()) {
            throw new IllegalArgumentException("La descripción del ahorro no puede estar vacía");
        }
        if (dto.getDescripcion().length() > 30) {
            throw new IllegalArgumentException("La descripción del ahorro no puede superar los 30 caracteres");
        }
        if (dto.getObjetivo() == null) {
            throw new IllegalArgumentException("El monto objetivo del ahorro no puede ser nulo");
        }
        if (dto.getObjetivo().signum() <= 0) {
            throw new IllegalArgumentException("El monto objetivo del ahorro debe ser mayor a cero");
        }
        if (dto.getInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio del ahorro no puede ser nula");
        }
        if (dto.getFinalizacion() == null) {
            throw new IllegalArgumentException("La fecha de finalización del ahorro no puede ser nula");
        }
        if (dto.getFinalizacion().isBefore(dto.getInicio())) {
            throw new IllegalArgumentException("La fecha de finalización no puede ser anterior a la de inicio");
        }
        if (dto.getEstado() == null) {
            throw new IllegalArgumentException("El estado del ahorro no puede ser nulo");
        }

        return AhorroEntity.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .montoObjetivo(dto.getObjetivo())
                .inicio(dto.getInicio())
                .fin(dto.getFinalizacion())
                .estado(dto.getEstado())
                .build();
    }

    public static List<AhorroDto> toDtoList(List<AhorroEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(AhorroMapper::toDto)
                .collect(Collectors.toList());
    }
}
