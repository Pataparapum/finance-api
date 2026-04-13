package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.BancoCreateDto;
import com.finance.finance_api.domain.dto.response.BancoResponseDto;
import com.finance.finance_api.infraestructura.services.BancoServices;
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
@RequestMapping("/banco")
@RequiredArgsConstructor
@Tag(name = "bancos", description = "operaciones sobre los bancos")
public class BancoController {

    private final BancoServices bancoServices;

    @GetMapping
    @Operation(description = "Obtener todos los bancos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bancos encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron bancos")
    })
    public ResponseEntity<ApiResponseDto<List<BancoResponseDto>>> getBancos() {
        return ResponseEntity.ok(bancoServices.getBancos());
    }

    @GetMapping("/{id}")
    @Operation(description = "Obtener banco por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Banco encontrado"),
            @ApiResponse(responseCode = "404", description = "Banco busquado no existe")
    })
    public ResponseEntity<ApiResponseDto<BancoResponseDto>> getBancoById(
            @Parameter(description = "ID del banco") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(bancoServices.getBancoById(id));
    }

    @PostMapping
    @Operation(description = "Creación de banco")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Banco creado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    public ResponseEntity<ApiResponseDto<BancoResponseDto>> postBanco(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo banco",
                    required = true
            ) @RequestBody BancoCreateDto banco
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bancoServices.postBanco(banco));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Elimina a un banco por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Banco eliminado"),
            @ApiResponse(responseCode = "404", description = "Banco a eliminar no existe")
    })
    public ResponseEntity<ApiResponseDto<Void>> deleteBanco(
            @Parameter(description = "ID del banco") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(bancoServices.deleteBanco(id));
    }
}
