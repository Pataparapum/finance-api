package com.finance.finance_api.infraestructura.repositories;

import com.finance.finance_api.infraestructura.entities.TarjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TarjetaRepository extends JpaRepository<TarjetaEntity, UUID> {
}
