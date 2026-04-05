package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.UsuarioCreateDto;
import com.finance.finance_api.domain.dto.response.UsuarioResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceAlreadyExistsException;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.UsuarioInterface;
import com.finance.finance_api.domain.mappers.UsuarioMapper;
import com.finance.finance_api.infraestructura.entities.UsuarioEntity;
import com.finance.finance_api.infraestructura.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioInterface {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public ApiResponseDto<List<UsuarioResponseDto>> getUsers() {

        List<UsuarioEntity> entity = usuarioRepository.findAll();
        List<UsuarioResponseDto> response = usuarioMapper.toDtoList(entity);

        return ApiResponseDto.<List<UsuarioResponseDto>>builder()
                .codigo(200)
                .mensaje("Usuarios obtenidos")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<UsuarioResponseDto> getUserById(UUID id) {

        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario de id: " + id + " no encontrado"));

        UsuarioResponseDto response = usuarioMapper.toDto(entity);
        return ApiResponseDto.<UsuarioResponseDto>builder()
                .codigo(200)
                .mensaje("Usuario de id " + id + " encontrado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<UsuarioResponseDto> postUser(UsuarioCreateDto usuario) {

        UsuarioEntity user = usuarioRepository.findByName(usuario.getName());

        if (user != null) {
            throw new ResourceAlreadyExistsException("Nombre de usuario ya esta en uso");
        }

        UsuarioEntity response = usuarioRepository.save(usuarioMapper.toEntity(usuario));

        return ApiResponseDto.<UsuarioResponseDto>builder()
                .codigo(200)
                .mensaje("usuario creado con exito")
                .data(usuarioMapper.toDto(response))
                .build();

    }

    @Override
    public ApiResponseDto<UsuarioResponseDto> putUser(UUID id, UsuarioCreateDto usuario) {

        usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario de id " + id + " no existe"));

        UsuarioEntity response = usuarioRepository.save(usuarioMapper.toEntity(usuario));

        return ApiResponseDto.<UsuarioResponseDto>builder()
                .codigo(200)
                .mensaje("Usuario actualizado")
                .data(usuarioMapper.toDto(response))
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteUsuario(UUID id) {

        UsuarioEntity user = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario de id " + id + " no existe"));

        usuarioRepository.deleteById(id);

        return ApiResponseDto.<Void>builder()
                .codigo(200)
                .mensaje("Usuario borrado con exito")
                .data(null)
                .build();
    }
}
