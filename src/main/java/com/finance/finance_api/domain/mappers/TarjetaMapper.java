package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.create.TarjetaCreateDto;
import com.finance.finance_api.domain.dto.response.TarjetaResponseDto;
import com.finance.finance_api.infraestructura.entities.TarjetaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CreditoTarjetaMapper.class})
public interface TarjetaMapper {

    // tipotarjeta (entity) -> tipo (dto)
    TarjetaResponseDto toDto(TarjetaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titular", ignore = true)
    @Mapping(target = "cuenta", ignore = true)
    @Mapping(target = "creditoTarjeta", ignore = true)
    TarjetaEntity toEntity(TarjetaCreateDto dto);

    List<TarjetaResponseDto> toDtoList(List<TarjetaEntity> entities);
}