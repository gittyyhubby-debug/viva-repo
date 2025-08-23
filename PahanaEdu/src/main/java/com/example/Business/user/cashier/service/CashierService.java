package com.example.Business.user.cashier.service;

import com.example.Business.user.cashier.dto.CashierDTO;
import com.example.Business.user.cashier.mapper.CashierMapper;
import com.example.persistence.dao.CashierDAO;
import com.example.persistence.model.Cashier;

import java.util.ArrayList;
import java.util.List;

public class CashierService {

    private final CashierDAO cashierDAO = new CashierDAO();

    public List<CashierDTO> getAllCashiers() {
        List<Cashier> cashiers = cashierDAO.getAllCashiers();
        List<CashierDTO> cashierDTOs = new ArrayList<>();

        for (Cashier cashier : cashiers) {
            cashierDTOs.add(CashierMapper.toDTO(cashier));
        }

        return cashierDTOs;
    }
}
