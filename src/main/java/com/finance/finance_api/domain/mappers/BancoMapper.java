package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.BancoDto;
import com.finance.finance_api.domain.dto.CuentaDto;
import com.finance.finance_api.infraestructura.entities.BancoEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BancoMapper {

    private BancoMapper() {}

    public static BancoDto toDto(BancoEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("BancoEntity no puede ser nulo");
        }

        List<CuentaDto> cuentas = entity.getCuentas() != null
                ? entity.getCuentas().stream()
                .map(CuentaMapper::toDto)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return BancoDto.builder()
                .banco(entity.getNombre())
                .cuentas(cuentas)
                .build();
    }

    public static BancoEntity toEntity(BancoDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("BancoDto no puede ser nulo");
        }
        if (dto.getBanco() == null || dto.getBanco().isBlank()) {
            throw new IllegalArgumentException("El nombre del banco no puede estar vacío");
        }

        BancoEntity entity = new BancoEntity();
        entity.setNombre(dto.getBanco());
        return entity;
    }

    public static List<BancoDto> toDtoList(List<BancoEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(BancoMapper::toDto)
                .collect(Collectors.toList());
    }
}

