package com.example.Business.reports.controller;

import com.example.Business.reports.dto.SalesFilterDTO;
import com.example.Business.reports.dto.SalesReportDTO;
import com.example.Business.reports.service.SalesReportService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/Admin/SalesReport")
public class SalesReportController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SalesReportController.class.getName());
    private final SalesReportService salesReportService = new SalesReportService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Create filter DTO from request parameters
            SalesFilterDTO filterDTO = createFilterFromRequest(request);

            // Get sales data using service
            List<SalesReportDTO> salesList = salesReportService.getSalesReport(filterDTO);

            // Check if this is an AJAX request
            if ("true".equals(request.getParameter("ajax"))) {
                handleAjaxRequest(response, salesList);
            } else {
                handleNormalRequest(request, response, salesList);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing sales report request", e);
            request.setAttribute("error", "Failed to load sales report: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/SalesReport.jsp");
            dispatcher.forward(request, response);
        }
    }

    private SalesFilterDTO createFilterFromRequest(HttpServletRequest request) {
        return new SalesFilterDTO(
                request.getParameter("fromDate"),
                request.getParameter("toDate"),
                request.getParameter("customer"),
                request.getParameter("minAmount"),
                request.getParameter("maxAmount"),
                request.getParameter("sortBy"),
                request.getParameter("topN")
        );
    }

    private void handleAjaxRequest(HttpServletResponse response, List<SalesReportDTO> salesList)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            for (SalesReportDTO sale : salesList) {
                out.println("<tr class='hover:bg-gray-50'>");
                out.println("<td class='px-4 py-2 border'>" + sale.getId() + "</td>");
                out.println("<td class='px-4 py-2 border'>" + sale.getCustomerName() + "</td>");
                out.println("<td class='px-4 py-2 border'>" + sale.getCustomerPhone() + "</td>");
                out.println("<td class='px-4 py-2 border'>" + sale.getTotalAmount() + "</td>");
                out.println("<td class='px-4 py-2 border'>" + sale.getDiscount() + "</td>");
                out.println("<td class='px-4 py-2 border'>" + sale.getServiceCharge() + "</td>");
                out.println("<td class='px-4 py-2 border'>" + sale.getCreatedAt() + "</td>");
                out.println("</tr>");
            }
        }
    }

    private void handleNormalRequest(HttpServletRequest request, HttpServletResponse response,
                                     List<SalesReportDTO> salesList) throws ServletException, IOException {
        request.setAttribute("salesList", salesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/SalesReport.jsp");
        dispatcher.forward(request, response);
    }
}
