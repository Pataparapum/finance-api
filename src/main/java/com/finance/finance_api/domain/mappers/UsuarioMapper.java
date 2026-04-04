package com.finance.finance_api.domain.mappers;

import com.finance.finance_api.domain.dto.create.UsuarioCreateDto;
import com.finance.finance_api.domain.dto.response.UsuarioResponseDto;
import com.finance.finance_api.infraestructura.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BancoMapper.class})
public interface UsuarioMapper {

    UsuarioResponseDto toDto(UsuarioEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "bancos", ignore = true)
    UsuarioEntity toEntity(UsuarioCreateDto dto);

    List<UsuarioResponseDto> toDtoList(List<UsuarioEntity> entities);
}
