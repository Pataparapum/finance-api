package com.finance.finance_api.infraestructura.repositories;

import com.finance.finance_api.infraestructura.entities.CreditoTarjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditoTarjetaRepository extends JpaRepository<CreditoTarjetaEntity, UUID> {
}
