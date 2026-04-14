package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.TarjetaCreateDto;
import com.finance.finance_api.domain.dto.response.TarjetaResponseDto;
import com.finance.finance_api.domain.interfaces.TarjetaInterface;
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
@RequestMapping("/tarjeta")
@RequiredArgsConstructor
@Tag(name = "Tarjetas", description = "Operaciones sobre las tarjetas")
public class TarjetaController {

    private final TarjetaInterface tarjetaInterface;

    @GetMapping
    @Operation(summary = "Obtener todas las tarjetas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarjetas encontradas"),
            @ApiResponse(responseCode = "404", description = "No hay tarjetas en la base de datos")
    })
    public ResponseEntity<ApiResponseDto<List<TarjetaResponseDto>>> getTarjetas() {
        return ResponseEntity.ok(tarjetaInterface.getTarjetas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tarjeta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarjeta encontrada"),
            @ApiResponse(responseCode = "404", description = "La tarjeta con el id enviado no existe")
    })
    public ResponseEntity<ApiResponseDto<TarjetaResponseDto>> getTarjetaById(
            @Parameter(description = "ID de la tarjeta") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(tarjetaInterface.getTarjetaById(id));
    }

    @PostMapping
    @Operation(summary = "Crear tarjeta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarjeta creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ApiResponseDto<TarjetaResponseDto>> postTarjeta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la nueva tarjeta",
                    required = true
            ) @RequestBody TarjetaCreateDto tarjeta
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tarjetaInterface.postTarjeta(tarjeta));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una tarjeta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarjeta eliminada con exito"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    public ResponseEntity<ApiResponseDto<Void>> deleteTarjeta(
            @Parameter(description = "ID de la tarjeta") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(tarjetaInterface.deleteTarjeta(id));
    }
}
