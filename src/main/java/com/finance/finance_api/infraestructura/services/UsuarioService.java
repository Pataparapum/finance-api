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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioInterface {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ApiResponseDto<List<UsuarioResponseDto>> getUsers() {

        List<UsuarioEntity> entities = usuarioRepository.findAll();
        List<UsuarioResponseDto> response = usuarioMapper.toDtoList(entities);

        return ApiResponseDto.<List<UsuarioResponseDto>>builder()
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
                .mensaje("Usuario de id " + id + " encontrado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<UsuarioResponseDto> postUser(UsuarioCreateDto usuario) {

        if (usuarioRepository.findByName(usuario.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException("Ya existe el usuario con el nombre " + usuario.getName());
        };

        UsuarioEntity entity = usuarioMapper.toEntity(usuario);

        entity.setPassword(passwordEncoder.encode(usuario.getPassword()));

        UsuarioResponseDto response = usuarioMapper.toDto(usuarioRepository.save(entity));

        return ApiResponseDto.<UsuarioResponseDto>builder()
                .mensaje("usuario creado con exito")
                .data(response)
                .build();

    }

    @Override
    public ApiResponseDto<UsuarioResponseDto> putUser(UUID id, UsuarioCreateDto usuario) {

        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario de id " + id + " no existe"));

        usuarioMapper.updateEntity(usuario, entity);

        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }

        UsuarioEntity updated = usuarioRepository.save(entity);
        UsuarioResponseDto response = usuarioMapper.toDto(updated);

        return ApiResponseDto.<UsuarioResponseDto>builder()
                .mensaje("Usuario actualizado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteUsuario(UUID id) {

        UsuarioEntity user = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario de id " + id + " no existe"));

        usuarioRepository.deleteById(id);

        return ApiResponseDto.<Void>builder()
                .mensaje("Usuario borrado con exito")
                .data(null)
                .build();
    }
}
