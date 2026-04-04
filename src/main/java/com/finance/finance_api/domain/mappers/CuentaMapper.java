package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.create.CuentaCreateDto;
import com.finance.finance_api.domain.dto.response.CuentaResponseDto;
import com.finance.finance_api.infraestructura.entities.CuentaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TarjetaMapper.class, CreditoMapper.class, AhorroMapper.class})
public interface CuentaMapper {

    // cuenta (entity) -> numeroCuenta (dto)
    CuentaResponseDto toDto(CuentaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "banco", ignore = true)
    @Mapping(target = "creditos", ignore = true)
    @Mapping(target = "tarjetas", ignore = true)
    @Mapping(target = "ahorros", ignore = true)
    CuentaEntity toEntity(CuentaCreateDto dto);

    List<CuentaResponseDto> toDtoList(List<CuentaEntity> entities);
}