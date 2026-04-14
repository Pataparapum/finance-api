package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.CuentaCreateDto;
import com.finance.finance_api.domain.dto.response.CuentaResponseDto;
import com.finance.finance_api.domain.interfaces.CuentaInterface;
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
@RequestMapping("/cuenta")
@RequiredArgsConstructor
@Tag(name = "Cuentas", description = "Operaciones sobre las cuentas")
public class CuentaController {

    private final CuentaInterface cuentaInterface;

    @GetMapping
    @Operation(summary = "Obtener todas las cuentas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuentas encontradas"),
            @ApiResponse(responseCode = "404", description = "No hay cuentas en la base de datos")
    })
    public ResponseEntity<ApiResponseDto<List<CuentaResponseDto>>> getCuentas() {
        return ResponseEntity.ok(cuentaInterface.getCuentas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cuenta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada"),
            @ApiResponse(responseCode = "404", description = "La cuenta con el id enviado no existe")
    })
    public ResponseEntity<ApiResponseDto<CuentaResponseDto>> getCuentaByid(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(cuentaInterface.getCuentaByid(id));
    }

    @PostMapping
    @Operation(summary = "Crear cuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cuenta creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ApiResponseDto<CuentaResponseDto>> postCuenta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la nueva cuenta",
                    required = true
            ) @RequestBody CuentaCreateDto cuenta
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cuentaInterface.postCuenta(cuenta));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una cuenta por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta eliminada con exito"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<ApiResponseDto<Void>> deleteCuenta(
            @Parameter(description = "ID de la cuenta") @PathVariable UUID id
    ) {
        return ResponseEntity.ok(cuentaInterface.deleteCuenta(id));
    }
}
