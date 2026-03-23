package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.CreditoTarjetaDto;
import com.finance.finance_api.infraestructura.entities.CreditoTarjetaEntity;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreditoTarjetaMapper {

    private CreditoTarjetaMapper() {}

    public static CreditoTarjetaDto toDto(CreditoTarjetaEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("CreditoTarjetaEntity no puede ser nulo");
        }

        BigInteger limite = entity.getLimite();
        BigInteger disponible = entity.getDisponible();

        if (limite == null) {
            throw new IllegalStateException("El límite de crédito de la tarjeta no puede ser nulo");
        }
        if (disponible == null) {
            throw new IllegalStateException("El crédito disponible de la tarjeta no puede ser nulo");
        }
        if (disponible.compareTo(limite) > 0) {
            throw new IllegalStateException("El crédito disponible no puede ser mayor al límite");
        }

        BigInteger usado = limite.subtract(disponible);

        return CreditoTarjetaDto.builder()
                .limite(limite)
                .disponible(disponible)
                .usado(usado)
                .corte(entity.getCorte())
                .pago(entity.getPago())
                .build();
    }

    public static CreditoTarjetaEntity toEntity(CreditoTarjetaDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CreditoTarjetaDto no puede ser nulo");
        }
        if (dto.getLimite() == null) {
            throw new IllegalArgumentException("El límite de crédito no puede ser nulo");
        }
        if (dto.getLimite().compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("El límite de crédito debe ser mayor a cero");
        }
        if (dto.getDisponible() == null) {
            throw new IllegalArgumentException("El crédito disponible no puede ser nulo");
        }
        if (dto.getDisponible().compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("El crédito disponible no puede ser negativo");
        }
        if (dto.getDisponible().compareTo(dto.getLimite()) > 0) {
            throw new IllegalArgumentException("El crédito disponible no puede ser mayor al límite");
        }
        if (dto.getCorte() < 1 || dto.getCorte() > 31) {
            throw new IllegalArgumentException("El día de corte debe estar entre 1 y 31");
        }
        if (dto.getPago() < 1 || dto.getPago() > 31) {
            throw new IllegalArgumentException("El día de pago debe estar entre 1 y 31");
        }

        return CreditoTarjetaEntity.builder()
                .limite(dto.getLimite())
                .disponible(dto.getDisponible())
                .corte(dto.getCorte())
                .pago(dto.getPago())
                .build();
    }

    public static List<CreditoTarjetaDto> toDtoList(List<CreditoTarjetaEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(CreditoTarjetaMapper::toDto)
                .collect(Collectors.toList());
    }
}
