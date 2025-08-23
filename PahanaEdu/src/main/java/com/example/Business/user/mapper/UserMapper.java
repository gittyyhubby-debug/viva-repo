package com.example.Business.user.mapper;

import com.example.Business.user.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static UserDTO mapToUserDTO(ResultSet rs) throws SQLException {
        String username = rs.getString("username");
        String userType = "admin".equals(username) ? "admin" : "cashier";
        return new UserDTO(username, userType);
    }
}