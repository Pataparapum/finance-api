package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.UsuarioCreateDto;
import com.finance.finance_api.domain.dto.response.UsuarioResponseDto;
import com.finance.finance_api.domain.interfaces.UsuarioInterface;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioInterface usuarioInterface;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UsuarioResponseDto>>> getUsers() {
        return ResponseEntity.ok(usuarioInterface.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UsuarioResponseDto>> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioInterface.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<UsuarioResponseDto>> postUser(@RequestBody UsuarioCreateDto usuario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioInterface.postUser(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UsuarioResponseDto>> putUser(@PathVariable UUID id, @RequestBody UsuarioCreateDto usuario) {
        return ResponseEntity.ok(usuarioInterface.putUser(id, usuario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioInterface.deleteUsuario(id));
    }
}
