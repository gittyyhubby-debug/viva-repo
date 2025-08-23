package com.example.Business.reports.service;

import com.example.Business.reports.dto.SalesFilterDTO;
import com.example.Business.reports.dto.SalesReportDTO;
import com.example.Business.reports.mapper.SalesReportMapper;
import com.example.persistence.dao.SalesDAO;
import com.example.persistence.model.Sale;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesReportService {

    private static final Logger LOGGER = Logger.getLogger(SalesReportService.class.getName());
    private final SalesDAO salesDAO = new SalesDAO();

    public List<SalesReportDTO> getSalesReport(SalesFilterDTO filterDTO) {
        try {
            List<Sale> sales = salesDAO.getSalesWithFilters(
                    filterDTO.getFromDate(),
                    filterDTO.getToDate(),
                    filterDTO.getCustomer(),
                    filterDTO.getMinAmount(),
                    filterDTO.getMaxAmount(),
                    filterDTO.getSortBy(),
                    filterDTO.getTopN()
            );
            return SalesReportMapper.toDTOList(sales);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving sales report", e);
            throw new RuntimeException("Failed to retrieve sales report", e);
        }
    }

    public List<SalesReportDTO> getAllSales() {
        try {
            List<Sale> sales = salesDAO.getAllSales();
            return SalesReportMapper.toDTOList(sales);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all sales", e);
            throw new RuntimeException("Failed to retrieve sales", e);
        }
    }

    public double getTotalSalesAmount() {
        try {
            return salesDAO.getTotalSalesAmount();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving total sales amount", e);
            return 0.0;
        }
    }

    public int getTotalSalesCount() {
        try {
            return salesDAO.getTotalSalesCount();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving total sales count", e);
            return 0;
        }
    }
}