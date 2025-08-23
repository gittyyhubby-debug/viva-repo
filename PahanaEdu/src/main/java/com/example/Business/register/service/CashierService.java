package com.example.Business.register.service;

import com.example.Business.register.dto.CashierDTO;
import com.example.Business.register.mapper.CashierMapper;
import com.example.persistence.dao.CashierDAO;
import com.example.persistence.model.User;

import java.sql.SQLException;

public class CashierService {

    private final CashierDAO userDAO = new CashierDAO();

    public boolean registerCustomer(CashierDTO dto) throws SQLException {
        User user = CashierMapper.toUser(dto);
        return userDAO.saveUser(user);
    }
}
