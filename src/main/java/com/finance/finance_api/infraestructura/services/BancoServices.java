package com.finance.finance_api.infraestructura.services;

import com.finance.finance_api.domain.dto.ApiResponseDto;
import com.finance.finance_api.domain.dto.create.BancoCreateDto;
import com.finance.finance_api.domain.dto.response.BancoResponseDto;
import com.finance.finance_api.domain.excepcions.ResourceNotFoundException;
import com.finance.finance_api.domain.interfaces.BancoInterface;
import com.finance.finance_api.domain.mappers.BancoMapper;
import com.finance.finance_api.infraestructura.entities.BancoEntity;
import com.finance.finance_api.infraestructura.repositories.BancoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BancoServices implements BancoInterface {

    private final BancoRepository bancoRepository;
    private final BancoMapper bancoMapper;

    @Override
    public ApiResponseDto<List<BancoResponseDto>> getBancos() {

        List<BancoEntity> entities = bancoRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("no se encontraron bancos");
        }
        List<BancoResponseDto> response = bancoMapper.toDtoList(entities);

        return ApiResponseDto.<List<BancoResponseDto>>builder()
                .mensaje("Bancos obtenidos")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<BancoResponseDto> getBancoById(UUID id) {

        BancoEntity entity = bancoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banco de id " + id + " no encontrado"));

        BancoResponseDto response = bancoMapper.toDto(entity);

        return ApiResponseDto.<BancoResponseDto>builder()
                .mensaje("Banco de id " + id + " encontrado")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<BancoResponseDto> postBanco(BancoCreateDto banco) {

        BancoEntity entity = bancoRepository.save(bancoMapper.toEntity(banco));
        BancoResponseDto response = bancoMapper.toDto(entity);

        return ApiResponseDto.<BancoResponseDto>builder()
                .mensaje("Banco creado con exito")
                .data(response)
                .build();
    }

    @Override
    public ApiResponseDto<Void> deleteBanco(UUID id) {

        BancoEntity entity = bancoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("banco de id " + id + " no encontrado"));

        bancoRepository.deleteById(id);
        return ApiResponseDto.<Void>builder()
                .mensaje("Banco borrado")
                .data(null)
                .build();
    }
}
