package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.response.UsuarioResponseDto;
import com.finance.finance_api.domain.dto.create.UsuarioCreateDto;

import java.util.List;
import java.util.UUID;

public interface UsuarioInterface {
    ApiResponseDto<List<UsuarioResponseDto>> getUsers();
    ApiResponseDto<UsuarioResponseDto> getUserById(UUID id);
    ApiResponseDto<UsuarioResponseDto> postUser(UsuarioCreateDto usuario);
    ApiResponseDto<UsuarioResponseDto> putUser(UUID id, UsuarioCreateDto usuario);
    ApiResponseDto<Void> deleteUsuario(UUID id);
}
