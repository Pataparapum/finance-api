package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.CreditoDto;
import com.finance.finance_api.infraestructura.entities.CreditoEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreditoMapper {

    private CreditoMapper() {}

    public static CreditoDto toDto(CreditoEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("CreditoEntity no puede ser nulo");
        }

        return CreditoDto.builder()
                .alias(entity.getAlias())
                .montoOriginal(entity.getMontoOriginal())
                .cuotaTotal(entity.getCuotasTotales())
                .cuotaMensual(entity.getCuotaMensual())
                .cuotaPagada(entity.getCuotasPagadas())
                .tasa(entity.getTasa())
                .inicio(entity.getInicio())
                .finalizacion(entity.getFin())
                .proximo(entity.getProximo())
                .estado(entity.getEstado())
                .build();
    }

    public static CreditoEntity toEntity(CreditoDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CreditoDto no puede ser nulo");
        }
        if (dto.getMontoOriginal() == null) {
            throw new IllegalArgumentException("El monto original del crédito no puede ser nulo");
        }
        if (dto.getMontoOriginal().signum() <= 0) {
            throw new IllegalArgumentException("El monto original del crédito debe ser mayor a cero");
        }
        if (dto.getCuotaTotal() <= 0) {
            throw new IllegalArgumentException("Las cuotas totales deben ser mayor a cero");
        }
        if (dto.getCuotaMensual() <= 0) {
            throw new IllegalArgumentException("La cuota mensual debe ser mayor a cero");
        }
        if (dto.getCuotaPagada() < 0) {
            throw new IllegalArgumentException("Las cuotas pagadas no pueden ser negativas");
        }
        if (dto.getCuotaPagada() > dto.getCuotaTotal()) {
            throw new IllegalArgumentException("Las cuotas pagadas no pueden superar las cuotas totales");
        }
        if (dto.getTasa() == null) {
            throw new IllegalArgumentException("La tasa de interés no puede ser nula");
        }
        if (dto.getTasa().signum() < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (dto.getInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio del crédito no puede ser nula");
        }
        if (dto.getFinalizacion() == null) {
            throw new IllegalArgumentException("La fecha de finalización del crédito no puede ser nula");
        }
        if (dto.getFinalizacion().isBefore(dto.getInicio())) {
            throw new IllegalArgumentException("La fecha de finalización no puede ser anterior a la de inicio");
        }
        if (dto.getProximo() == null) {
            throw new IllegalArgumentException("La fecha del próximo pago no puede ser nula");
        }
        if (dto.getEstado() == null) {
            throw new IllegalArgumentException("El estado del crédito no puede ser nulo");
        }

        return CreditoEntity.builder()
                .alias(dto.getAlias() != null ? dto.getAlias() : "sin alias asignado")
                .montoOriginal(dto.getMontoOriginal())
                .cuotasTotales(dto.getCuotaTotal())
                .cuotaMensual(dto.getCuotaMensual())
                .cuotasPagadas(dto.getCuotaPagada())
                .tasa(dto.getTasa())
                .inicio(dto.getInicio())
                .fin(dto.getFinalizacion())
                .proximo(dto.getProximo())
                .estado(dto.getEstado())
                .build();
    }

    public static List<CreditoDto> toDtoList(List<CreditoEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(CreditoMapper::toDto)
                .collect(Collectors.toList());
    }
}
