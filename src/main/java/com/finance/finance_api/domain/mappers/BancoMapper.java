package com.finance.finance_api.domain.mappers;


import com.finance.finance_api.domain.dto.create.BancoCreateDto;
import com.finance.finance_api.domain.dto.response.BancoResponseDto;
import com.finance.finance_api.infraestructura.entities.BancoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CuentaMapper.class})
public interface BancoMapper {

    // nombre (entity) -> banco (dto)
    BancoResponseDto toDto(BancoEntity entity);

    // banco (dto) -> nombre (entity)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cuentas", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    BancoEntity toEntity(BancoCreateDto dto);


    List<BancoResponseDto> toDtoList(List<BancoEntity> entities);
}
