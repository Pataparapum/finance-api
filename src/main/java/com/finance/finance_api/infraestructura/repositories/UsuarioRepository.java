package com.finance.finance_api.infraestructura.repositories;

import com.finance.finance_api.infraestructura.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
