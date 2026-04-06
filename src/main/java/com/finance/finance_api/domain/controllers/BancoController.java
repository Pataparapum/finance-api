package com.finance.finance_api.domain.controllers;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.BancoCreateDto;
import com.finance.finance_api.domain.dto.response.BancoResponseDto;
import com.finance.finance_api.infraestructura.services.BancoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/banco")
@RequiredArgsConstructor
public class BancoController {

    private final BancoServices bancoServices;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<BancoResponseDto>>> getBancos() {
        return ResponseEntity.ok(bancoServices.getBancos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BancoResponseDto>> getBancoById(@PathVariable UUID id) {
        return ResponseEntity.ok(bancoServices.getBancoById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<BancoResponseDto>> postBanco(BancoCreateDto banco) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bancoServices.postBanco(banco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteBanco(@PathVariable UUID id) {
        return ResponseEntity.ok(bancoServices.deleteBanco(id));
    }
}
