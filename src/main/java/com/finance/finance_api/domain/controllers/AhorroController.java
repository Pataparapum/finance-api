package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.AhorroCreateDto;
import com.finance.finance_api.domain.dto.response.AhorroResponseDto;
import com.finance.finance_api.domain.interfaces.AhorroInterface;
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
@RequestMapping("/ahorro")
@RequiredArgsConstructor
@Tag(name = "Ahorros", description = "Operaciones sobre los ahorros")
public class AhorroController {

    private final AhorroInterface ahorroInterface;

    @GetMapping
    @Operation(summary = "Obtener todos los ahorros")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ahorros encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay ahorros en la base de datos")
    })
    public ResponseEntity<ApiResponseDto<List<AhorroResponseDto>>> getAhorros() {
        return ResponseEntity.ok(ahorroInterface.getAhorros());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ahorro por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ahorro encontrado"),
            @ApiResponse(responseCode = "404", description = "El ahorro con el id enviado no existe")
    })
    public ResponseEntity<ApiResponseDto<AhorroResponseDto>> getAhorroById(
            @Parameter(description = "ID del ahorro") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(ahorroInterface.getAhorroById(id));
    }

    @PostMapping
    @Operation(summary = "Crear ahorro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ahorro creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ApiResponseDto<AhorroResponseDto>> postAhorro(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo ahorro",
                    required = true
            ) @RequestBody AhorroCreateDto ahorro
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ahorroInterface.postAhorro(ahorro));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ahorro existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ahorro actualizado"),
            @ApiResponse(responseCode = "404", description = "Ahorro no encontrado")
    })
    public ResponseEntity<ApiResponseDto<AhorroResponseDto>> putAhorro(
            @Parameter(description = "ID del ahorro a actualizar") @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del ahorro",
                    required = true
            ) @RequestBody AhorroCreateDto ahorro
    ) {
        return ResponseEntity.ok(ahorroInterface.putAhorro(id, ahorro));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un ahorro por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ahorro eliminado con exito"),
            @ApiResponse(responseCode = "404", description = "Ahorro no encontrado")
    })
    public ResponseEntity<ApiResponseDto<Void>> deleteAhorro(
            @Parameter(description = "ID del ahorro") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(ahorroInterface.deleteAhorro(id));
    }
}
