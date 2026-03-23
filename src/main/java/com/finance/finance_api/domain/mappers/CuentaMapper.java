package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.CuentaDto;
import com.finance.finance_api.infraestructura.entities.CuentaEntity;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaMapper {

    private CuentaMapper() {}

    public static CuentaDto toDto(CuentaEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("CuentaEntity no puede ser nulo");
        }

        return CuentaDto.builder()
                .tipoCuenta(entity.getTipoCuenta())
                .saldo(entity.getSaldo())
                .alias(entity.getAlias())
                .titular(entity.getTitular())
                .numeroCuenta(entity.getCuenta())
                .tarjetas(entity.getTarjetas() != null
                        ? entity.getTarjetas().stream()
                        .map(TarjetaMapper::toDto)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .credito(entity.getCreditos() != null
                        ? entity.getCreditos().stream()
                        .map(CreditoMapper::toDto)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .ahorro(entity.getAhorros() != null
                        ? entity.getAhorros().stream()
                        .map(AhorroMapper::toDto)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static CuentaEntity toEntity(CuentaDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CuentaDto no puede ser nulo");
        }
        if (dto.getTipoCuenta() == null) {
            throw new IllegalArgumentException("El tipo de cuenta no puede ser nulo");
        }
        if (dto.getTitular() == null || dto.getTitular().isBlank()) {
            throw new IllegalArgumentException("El titular de la cuenta no puede estar vacío");
        }
        if (dto.getNumeroCuenta() == null || dto.getNumeroCuenta().isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (dto.getNumeroCuenta().length() > 4) {
            throw new IllegalArgumentException("El número de cuenta debe contener máximo 4 caracteres");
        }

        return CuentaEntity.builder()
                .tipoCuenta(dto.getTipoCuenta())
                .saldo(dto.getSaldo() != null ? dto.getSaldo() : BigInteger.ZERO)
                .alias(dto.getAlias() != null ? dto.getAlias() : "  ")
                .titular(dto.getTitular())
                .cuenta(dto.getNumeroCuenta())
                .build();
    }

    public static List<CuentaDto> toDtoList(List<CuentaEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(CuentaMapper::toDto)
                .collect(Collectors.toList());
    }
}

