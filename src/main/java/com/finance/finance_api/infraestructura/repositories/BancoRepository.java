package com.finance.finance_api.infraestructura.repositories;

import com.finance.finance_api.infraestructura.entities.BancoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BancoRepository  extends JpaRepository<BancoEntity, UUID> {
}
