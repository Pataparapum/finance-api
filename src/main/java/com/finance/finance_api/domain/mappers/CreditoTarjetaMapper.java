package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.create.CreditoTarjetaCreateDto;
import com.finance.finance_api.domain.dto.response.CreditoTarjetaResponseDto;
import com.finance.finance_api.infraestructura.entities.CreditoTarjetaEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditoTarjetaMapper {

    @Mapping(target = "usado", ignore = true)   // se calcula en @AfterMapping
    CreditoTarjetaResponseDto toDto(CreditoTarjetaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tarjeta", ignore = true)
    CreditoTarjetaEntity toEntity(CreditoTarjetaCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tarjeta", ignore = true)
    void updateEntity(CreditoTarjetaCreateDto dto, @MappingTarget CreditoTarjetaEntity entity);

    List<CreditoTarjetaResponseDto> toDtoList(List<CreditoTarjetaEntity> entities);

    // Calcula usado = limite - disponible después del mapeo base
    @AfterMapping
    default void calcularUsado(CreditoTarjetaEntity entity, @MappingTarget CreditoTarjetaResponseDto dto) {
        if (entity.getLimite() == null || entity.getDisponible() == null) {
            throw new IllegalStateException(
                    "No se puede calcular el crédito usado: límite o disponible es nulo (id: "
                            + entity.getId() + ")"
            );
        }
        if (entity.getDisponible().compareTo(entity.getLimite()) > 0) {
            throw new IllegalStateException(
                    "El crédito disponible no puede ser mayor al límite (id: " + entity.getId() + ")"
            );
        }
        dto.setUsado(entity.getLimite().subtract(entity.getDisponible()));
    }
}