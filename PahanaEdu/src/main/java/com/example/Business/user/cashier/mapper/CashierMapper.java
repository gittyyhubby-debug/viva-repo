package com.example.Business.user.cashier.mapper;

import com.example.Business.user.cashier.dto.CashierDTO;
import com.example.persistence.model.Cashier;

public class CashierMapper {
    public static CashierDTO toDTO(Cashier cashier) {
        CashierDTO dto = new CashierDTO();
        dto.setId(cashier.getId());
        dto.setName(cashier.getName());
        dto.setUsername(cashier.getUsername());
        dto.setEmail(cashier.getEmail());
        dto.setProfile(cashier.getProfile());
        return dto;
    }
}
