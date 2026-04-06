package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.create.AporteAhorroCreateDto;
import com.finance.finance_api.domain.dto.response.AporteAhorroResponseDto;
import com.finance.finance_api.infraestructura.entities.AporteAhorroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AporteAhorroMapper {

    AporteAhorroResponseDto toDto(AporteAhorroEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ahorro", ignore = true)
    AporteAhorroEntity toEntity(AporteAhorroCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ahorro", ignore = true)
    void updateEntity(AporteAhorroCreateDto dto, @MappingTarget AporteAhorroEntity entity);

    List<AporteAhorroResponseDto> toDtoList(List<AporteAhorroEntity> entities);
}
