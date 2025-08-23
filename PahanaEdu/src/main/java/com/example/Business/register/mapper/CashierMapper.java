package com.example.Business.register.mapper;

import com.example.Business.register.dto.CashierDTO;
import com.example.persistence.model.User;

public class CashierMapper {

    public static User toUser(CashierDTO dto) {
        return new User(dto.getName(), dto.getEmail(), dto.getUsername(), dto.getPassword());
    }
}
