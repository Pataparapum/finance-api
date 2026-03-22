package com.finance.finance_api.infraestructura.repositories;

import com.finance.finance_api.infraestructura.entities.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CuentaRepository extends JpaRepository<CuentaEntity, UUID> {
}
