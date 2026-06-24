package com.nn.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;

@SpringBootApplication
@RestController
public class ApiApplication {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
    
    @GetMapping("/api/{user}:{pass}")
    public Map<String, Object> authenticate(@PathVariable String user, @PathVariable String pass) {
        Map<String, Object> response = new HashMap<>();
        
        String sql = "SELECT comment FROM comments WHERE username = ? AND password = ?";
        
        try {
            String comment = jdbcTemplate.queryForObject(sql, String.class, user, pass);
            response.put("comment", comment);
            return response;
        } catch (Exception e) {
            response.put("error", "Auth error - invalid credentials");
            return response;
        }
    }
}
