package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.CreditoTarjetaCreateDto;
import com.finance.finance_api.domain.dto.response.CreditoTarjetaResponseDto;
import com.finance.finance_api.domain.interfaces.CreditoTarjetaInterface;
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
@RequestMapping("/credito-tarjeta")
@RequiredArgsConstructor
@Tag(name = "Creditos de tarjeta", description = "Operaciones sobre los creditos de tarjeta")
public class CreditoTarjetaController {

    private final CreditoTarjetaInterface creditoTarjetaInterface;

    @GetMapping
    @Operation(summary = "Obtener todos los creditos de tarjeta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Creditos de tarjeta encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay creditos de tarjeta en la base de datos")
    })
    public ResponseEntity<ApiResponseDto<List<CreditoTarjetaResponseDto>>> getCreditoTarjetas() {
        return ResponseEntity.ok(creditoTarjetaInterface.getCreditoTarjetas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener credito de tarjeta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credito de tarjeta encontrado"),
            @ApiResponse(responseCode = "404", description = "El credito de tarjeta con el id enviado no existe")
    })
    public ResponseEntity<ApiResponseDto<CreditoTarjetaResponseDto>> getCreditoTarjetaById(
            @Parameter(description = "ID del credito de tarjeta") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(creditoTarjetaInterface.getCreditoTarjetaById(id));
    }

    @PostMapping
    @Operation(summary = "Crear credito de tarjeta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Credito de tarjeta creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ApiResponseDto<CreditoTarjetaResponseDto>> postCreditotarjeta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo credito de tarjeta",
                    required = true
            ) @RequestBody CreditoTarjetaCreateDto creditoTarjeta
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creditoTarjetaInterface.postCreditotarjeta(creditoTarjeta));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un credito de tarjeta existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credito de tarjeta actualizado"),
            @ApiResponse(responseCode = "404", description = "Credito de tarjeta no encontrado")
    })
    public ResponseEntity<ApiResponseDto<CreditoTarjetaResponseDto>> putCreditoTarjeta(
            @Parameter(description = "ID del credito de tarjeta a actualizar") @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del credito de tarjeta",
                    required = true
            ) @RequestBody CreditoTarjetaCreateDto creditoTarjeta
    ) {
        return ResponseEntity.ok(creditoTarjetaInterface.putCreditoTarjeta(id, creditoTarjeta));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un credito de tarjeta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credito de tarjeta eliminado con exito"),
            @ApiResponse(responseCode = "404", description = "Credito de tarjeta no encontrado")
    })
    public ResponseEntity<ApiResponseDto<CreditoTarjetaResponseDto>> deleteCreditoTarjeta(
            @Parameter(description = "ID del credito de tarjeta") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(creditoTarjetaInterface.deleteCreditoTarjeta(id));
    }
}
