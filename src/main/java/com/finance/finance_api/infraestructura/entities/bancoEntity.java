package com.finance.finance_api.infraestructura.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "banco")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class bancoEntity {

    @Id
    @Column(name = "id")
    UUID bancoID;
}
