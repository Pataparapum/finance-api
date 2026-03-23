package com.finance.finance_api.domain.interfaces;

import com.finance.finance_api.infraestructura.entities.BancoEntity;

import java.util.List;

public interface BancoInterface {
    public List<BancoEntity> getBanco();
}
