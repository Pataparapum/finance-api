package com.finance.finance_api.infraestructura.repositories;

import com.finance.finance_api.infraestructura.entities.AporteAhorroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AporteAhorroRepository extends JpaRepository<AporteAhorroEntity, UUID> {
}
