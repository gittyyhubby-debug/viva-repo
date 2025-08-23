package com.example.Business.reports.mapper;

import com.example.Business.reports.dto.SalesReportDTO;
import com.example.persistence.model.Sale;

import java.util.List;
import java.util.stream.Collectors;

public class SalesReportMapper {

    public static SalesReportDTO toDTO(Sale sale) {
        return new SalesReportDTO(
                sale.getId(),
                sale.getCustomerName(),
                sale.getCustomerPhone(),
                sale.getTotalAmount(),
                sale.getDiscount(),
                sale.getServiceCharge(),
                sale.getCreatedAt()
        );
    }

    public static Sale toEntity(SalesReportDTO dto) {
        return new Sale(
                dto.getId(),
                dto.getCustomerName(),
                dto.getCustomerPhone(),
                dto.getTotalAmount(),
                dto.getDiscount(),
                dto.getServiceCharge(),
                dto.getCreatedAt()
        );
    }

    public static List<SalesReportDTO> toDTOList(List<Sale> sales) {
        return sales.stream()
                .map(SalesReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Sale> toEntityList(List<SalesReportDTO> dtos) {
        return dtos.stream()
                .map(SalesReportMapper::toEntity)
                .collect(Collectors.toList());
    }
}