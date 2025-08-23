package com.example.Business.reports.dto;

public class SalesFilterDTO {
    private String fromDate;
    private String toDate;
    private String customer;
    private String minAmount;
    private String maxAmount;
    private String sortBy;
    private String topN;

    public SalesFilterDTO() {}

    public SalesFilterDTO(String fromDate, String toDate, String customer,
                         String minAmount, String maxAmount, String sortBy, String topN) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.customer = customer;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.sortBy = sortBy;
        this.topN = topN;
    }

    // Getters and Setters
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getTopN() {
        return topN;
    }

    public void setTopN(String topN) {
        this.topN = topN;
    }
}