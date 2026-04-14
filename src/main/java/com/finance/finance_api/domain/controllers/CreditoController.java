package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.CreditoCreateDto;
import com.finance.finance_api.domain.dto.response.CreditoResponseDto;
import com.finance.finance_api.domain.interfaces.CreditoInterface;
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
@RequestMapping("/credito")
@RequiredArgsConstructor
@Tag(name = "Creditos", description = "Operaciones sobre los creditos")
public class CreditoController {

    private final CreditoInterface creditoInterface;

    @GetMapping
    @Operation(summary = "Obtener todos los creditos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Creditos encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay creditos en la base de datos")
    })
    public ResponseEntity<ApiResponseDto<List<CreditoResponseDto>>> getCreditos() {
        return ResponseEntity.ok(creditoInterface.getCreditos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener credito por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credito encontrado"),
            @ApiResponse(responseCode = "404", description = "El credito con el id enviado no existe")
    })
    public ResponseEntity<ApiResponseDto<CreditoResponseDto>> getCreditoById(
            @Parameter(description = "ID del credito") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(creditoInterface.getCreditoById(id));
    }

    @PostMapping
    @Operation(summary = "Crear credito")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Credito creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ApiResponseDto<CreditoResponseDto>> postCredito(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo credito",
                    required = true
            ) @RequestBody CreditoCreateDto credito
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creditoInterface.postCredito(credito));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un credito existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credito actualizado"),
            @ApiResponse(responseCode = "404", description = "Credito no encontrado")
    })
    public ResponseEntity<ApiResponseDto<CreditoResponseDto>> putCredito(
            @Parameter(description = "ID del credito a actualizar") @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del credito",
                    required = true
            ) @RequestBody CreditoCreateDto credito
    ) {
        return ResponseEntity.ok(creditoInterface.putCredito(id, credito));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un credito por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credito eliminado con exito"),
            @ApiResponse(responseCode = "404", description = "Credito no encontrado")
    })
    public ResponseEntity<ApiResponseDto<Void>> deleteCredito(
            @Parameter(description = "ID del credito") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(creditoInterface.deleteCredito(id));
    }
}
