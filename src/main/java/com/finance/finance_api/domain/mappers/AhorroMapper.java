package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.create.AhorroCreateDto;
import com.finance.finance_api.domain.dto.response.AhorroResponseDto;
import com.finance.finance_api.infraestructura.entities.AhorroEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigInteger;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AporteAhorroMapper.class})
public interface AhorroMapper {

    @Mapping(target = "montoMensual", ignore = true)    // se calcula en @AfterMapping
    AhorroResponseDto toDto(AhorroEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cuenta", ignore = true)
    AhorroEntity toEntity(AhorroCreateDto dto);

    List<AhorroResponseDto> toDtoList(List<AhorroEntity> entities);

    // Calcula montoMensual = objetivo / meses entre inicio y fin (mínimo 1 mes)
    @AfterMapping
    default void calcularMontoMensual(AhorroEntity entity, @MappingTarget AhorroResponseDto dto) {
        if (entity.getMontoObjetivo() == null) {
            throw new IllegalStateException(
                    "El monto objetivo no puede ser nulo para calcular el monto mensual (id: " + entity.getId() + ")"
            );
        }
        if (entity.getInicio() == null || entity.getFin() == null) {
            throw new IllegalStateException(
                    "Las fechas de inicio y fin son necesarias para calcular el monto mensual (id: " + entity.getId() + ")"
            );
        }
        if (entity.getFin().isBefore(entity.getInicio())) {
            throw new IllegalStateException(
                    "La fecha de fin no puede ser anterior a la de inicio (id: " + entity.getId() + ")"
            );
        }

        long meses = ChronoUnit.MONTHS.between(entity.getInicio(), entity.getFin());

        // Si el plazo es menor a un mes se trata como 1 mes para evitar división por cero
        if (meses < 1) {
            meses = 1;
        }

        dto.setMontoMensual(entity.getMontoObjetivo().divide(BigInteger.valueOf(meses)));
    }
}