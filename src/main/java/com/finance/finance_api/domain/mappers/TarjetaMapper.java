package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.TarjetaDto;
import com.finance.finance_api.infraestructura.entities.TarjetaEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TarjetaMapper {

    private TarjetaMapper() {}

    public static TarjetaDto toDto(TarjetaEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("TarjetaEntity no puede ser nulo");
        }

        return TarjetaDto.builder()
                .numTarjeta(entity.getNumTarjeta())
                .mes(entity.getMes())
                .año(entity.getAño())
                .tipo(entity.getTipotarjeta())
                .uso(entity.getUso())
                .alias(entity.getAlias())
                .creditoTarjeta(entity.getCreditoTarjeta() != null
                        ? CreditoTarjetaMapper.toDto(entity.getCreditoTarjeta())
                        : null)
                .build();
    }

    public static TarjetaEntity toEntity(TarjetaDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("TarjetaDto no puede ser nulo");
        }
        if (dto.getNumTarjeta() == null || dto.getNumTarjeta().isBlank()) {
            throw new IllegalArgumentException("El número de tarjeta no puede estar vacío");
        }
        if (dto.getNumTarjeta().length() > 4) {
            throw new IllegalArgumentException("El número de tarjeta debe contener máximo 4 dígitos");
        }
        if (dto.getMes() == null || dto.getMes().isBlank()) {
            throw new IllegalArgumentException("El mes de caducidad no puede estar vacío");
        }
        if (dto.getAño() == null || dto.getAño().isBlank()) {
            throw new IllegalArgumentException("El año de caducidad no puede estar vacío");
        }
        if (dto.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de tarjeta no puede ser nulo");
        }
        if (dto.getUso() == null) {
            throw new IllegalArgumentException("El tipo de uso de la tarjeta no puede ser nulo");
        }

        return TarjetaEntity.builder()
                .numTarjeta(dto.getNumTarjeta())
                .mes(dto.getMes())
                .año(dto.getAño())
                .tipotarjeta(dto.getTipo())
                .uso(dto.getUso())
                .alias(dto.getAlias() != null ? dto.getAlias() : "sin alias")
                .build();
    }

    public static List<TarjetaDto> toDtoList(List<TarjetaEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(TarjetaMapper::toDto)
                .collect(Collectors.toList());
    }
}
