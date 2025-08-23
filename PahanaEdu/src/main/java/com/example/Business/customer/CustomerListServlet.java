package com.example.Business.customer;

import com.example.persistence.model.Customer;
import com.example.util.DBConn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customers")
public class CustomerListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        String sortBy = request.getParameter("sortBy");
        String format = request.getParameter("format");

        List<Customer> customerList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT nic, name, email, phone FROM customers WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Search by name, email, or phone
        if (search != null && !search.isEmpty()) {
            sql.append(" AND (name LIKE ? OR email LIKE ? OR phone LIKE ?)");
            String searchPattern = "%" + search + "%";
            params.add(searchPattern);
            params.add(searchPattern);
            params.add(searchPattern);
        }

        // Sorting
        if ("name".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY name ASC");
        } else {
            sql.append(" ORDER BY nic ASC");
        }

        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer c = new Customer();
                    c.setNic(rs.getString("nic"));
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setPhone(rs.getString("phone"));
                    customerList.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error: " + e.getMessage());
        }

        // Log customer count for debugging
        System.out.println("Fetched " + customerList.size() + " customers for format=" + format);

        if ("json".equals(format)) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                if (customerList.isEmpty()) {
                    out.print("[]");
                } else {
                    out.print("[");
                    boolean first = true;
                    for (Customer customer : customerList) {
                        String name = customer.getName() != null ? customer.getName().replace("\"", "\\\"") : "";
                        String phone = customer.getPhone() != null ? customer.getPhone().replace("\"", "\\\"") : "";
                        if (!first) {
                            out.print(",");
                        }
                        out.print("{\"name\":\"" + name + "\",\"phone\":\"" + phone + "\"}");
                        first = false;
                    }
                    out.print("]");
                }
            } finally {
                out.close();
            }
        } else if ("true".equals(request.getParameter("ajax"))) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                for (Customer customer : customerList) {
                    out.println("<tr class='border-t border-gray-700 hover:bg-gray-700/50'>");
                    out.println("<td class='px-4 py-2 border border-gray-700'>" + (customer.getNic() != null ? customer.getNic() : "") + "</td>");
                    out.println("<td class='px-4 py-2 border border-gray-700'>" + (customer.getName() != null ? customer.getName() : "") + "</td>");
                    out.println("<td class='px-4 py-2 border border-gray-700'>" + (customer.getEmail() != null ? customer.getEmail() : "") + "</td>");
                    out.println("<td class='px-4 py-2 border border-gray-700'>" + (customer.getPhone() != null ? customer.getPhone() : "") + "</td>");
                    out.println("<td class='px-4 py-2 border border-gray-700'>");
                    out.println("<a href='edit-customer?nic=" + (customer.getNic() != null ? customer.getNic() : "") + "' class='text-yellow-400 hover:underline'>Edit</a> | ");
                    out.println("<a href='delete-customer?nic=" + (customer.getNic() != null ? customer.getNic() : "") + "' class='text-red-500 hover:underline' " +
                            "onclick='return confirm(\"Are you sure you want to delete this customer?\")'>Delete</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }
            } finally {
                out.close();
            }
        } else {
            request.setAttribute("customerList", customerList);
            request.getRequestDispatcher("CustomerList.jsp").forward(request, response);
        }
    }
}
