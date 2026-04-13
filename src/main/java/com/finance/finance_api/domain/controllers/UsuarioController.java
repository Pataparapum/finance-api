package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.UsuarioCreateDto;
import com.finance.finance_api.domain.dto.response.UsuarioResponseDto;
import com.finance.finance_api.domain.interfaces.UsuarioInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones sobre el usuario")
public class UsuarioController {

    private final UsuarioInterface usuarioInterface;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
             @ApiResponse(responseCode = "404", description = "No hay usuarios en la base de datos")
    })
    public ResponseEntity<ApiResponseDto<List<UsuarioResponseDto>>> getUsers() {
        return ResponseEntity.ok(usuarioInterface.getUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuarios por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "el usuario con el id enviado no existe")
    })
    public ResponseEntity<ApiResponseDto<UsuarioResponseDto>> getUserById(
            @Parameter(description = "ID del usuario") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(usuarioInterface.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Crear usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "El usuario ya existe")
    })
    public ResponseEntity<ApiResponseDto<UsuarioResponseDto>> postUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo usuario",
                    required = true
            ) @RequestBody UsuarioCreateDto usuario
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioInterface.postUser(usuario));
    }

    @PutMapping("/{id}")
    @Operation(summary = " Actulizar un usuario existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ApiResponseDto<UsuarioResponseDto>> putUser(
            @Parameter(description = "ID del producto a actualizar")
            @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del producto",
                    required = true
            )
            @RequestBody UsuarioCreateDto usuario
    ) {
        return ResponseEntity.ok(usuarioInterface.putUser(id, usuario));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Elimina un usuario por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario eliminado con existo"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(
            @Parameter(description = "ID del usuario") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(usuarioInterface.deleteUsuario(id));
    }
}
