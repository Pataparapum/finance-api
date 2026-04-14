package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.AporteAhorroCreateDto;
import com.finance.finance_api.domain.dto.response.AporteAhorroResponseDto;
import com.finance.finance_api.domain.interfaces.AporteAhorroInterface;
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
@RequestMapping("/aporte-ahorro")
@RequiredArgsConstructor
@Tag(name = "Aportes de ahorro", description = "Operaciones sobre los aportes de ahorro")
public class AporteAhorroController {

    private final AporteAhorroInterface aporteAhorroInterface;

    @GetMapping
    @Operation(summary = "Obtener todos los aportes de ahorro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aportes encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay aportes en la base de datos")
    })
    public ResponseEntity<ApiResponseDto<List<AporteAhorroResponseDto>>> getAportes() {
        return ResponseEntity.ok(aporteAhorroInterface.getAportes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener aporte de ahorro por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aporte encontrado"),
            @ApiResponse(responseCode = "404", description = "El aporte con el id enviado no existe")
    })
    public ResponseEntity<ApiResponseDto<AporteAhorroResponseDto>> getAporteById(
            @Parameter(description = "ID del aporte") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(aporteAhorroInterface.getAporteById(id));
    }

    @PostMapping
    @Operation(summary = "Crear aporte de ahorro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Aporte creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ApiResponseDto<AporteAhorroResponseDto>> postAporte(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo aporte",
                    required = true
            ) @RequestBody AporteAhorroCreateDto aporte
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(aporteAhorroInterface.postAporte(aporte));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un aporte de ahorro existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aporte actualizado"),
            @ApiResponse(responseCode = "404", description = "Aporte no encontrado")
    })
    public ResponseEntity<ApiResponseDto<AporteAhorroResponseDto>> putAporte(
            @Parameter(description = "ID del aporte a actualizar") @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del aporte",
                    required = true
            ) @RequestBody AporteAhorroCreateDto aporte
    ) {
        return ResponseEntity.ok(aporteAhorroInterface.putAporte(id, aporte));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un aporte de ahorro por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aporte eliminado con exito"),
            @ApiResponse(responseCode = "404", description = "Aporte no encontrado")
    })
    public ResponseEntity<ApiResponseDto<Void>> deleteAporte(
            @Parameter(description = "ID del aporte") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(aporteAhorroInterface.deleteAporte(id));
    }
}
