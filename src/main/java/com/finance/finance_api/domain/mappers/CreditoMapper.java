package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.create.CreditoCreateDto;
import com.finance.finance_api.domain.dto.response.CreditoResponseDto;
import com.finance.finance_api.infraestructura.entities.CreditoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditoMapper {

    @Mapping(target = "cuotasRestantes", ignore = true)   // se calcula en @AfterMapping
    CreditoResponseDto toDto(CreditoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cuenta", ignore = true)
    CreditoEntity toEntity(CreditoCreateDto dto);

    List<CreditoResponseDto> toDtoList(List<CreditoEntity> entities);

    // Calcula cuotasRestantes = cuotasTotales - cuotasPagadas después del mapeo base
    @AfterMapping
    default void calcularCuotasRestantes(CreditoEntity entity, @MappingTarget CreditoResponseDto dto) {
        if (entity.getCuotasTotales() < 0 || entity.getCuotasPagadas() < 0) {
            throw new IllegalStateException(
                    "Las cuotas no pueden ser negativas (id: " + entity.getId() + ")"
            );
        }
        if (entity.getCuotasPagadas() > entity.getCuotasTotales()) {
            throw new IllegalStateException(
                    "Las cuotas pagadas no pueden superar las cuotas totales (id: " + entity.getId() + ")"
            );
        }
        dto.setCuotasRestantes((byte)(entity.getCuotasTotales() - entity.getCuotasPagadas()));
    }
}