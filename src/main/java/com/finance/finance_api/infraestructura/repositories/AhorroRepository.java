package com.finance.finance_api.infraestructura.repositories;

import com.finance.finance_api.infraestructura.entities.AhorroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AhorroRepository extends JpaRepository<AhorroEntity, UUID> {
}
