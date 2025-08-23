package com.example.Business.user.service;



import com.example.Business.user.dto.UserDTO;
import com.example.persistence.dao.LoginDAO;


import java.sql.SQLException;

public class LoginService {
    private final LoginDAO loginDAO;

    public LoginService() {
        this.loginDAO = new LoginDAO();
    }

    public String authenticate(String username, String password) throws SQLException {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null;
        }

        // Check hardcoded admin
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "admin";
        }

        // Check cashier in DB
        boolean cashierExists = loginDAO.authenticateCashier(username, password);
        if (cashierExists) {
            return "cashier";
        }

        return null; // invalid login
    }
}
