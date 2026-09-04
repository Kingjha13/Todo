package com.todobackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import com.todobackend.JwtUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class TaskController {
    private final JwtUtil jwtUtil;
    List<RegisterUsername> ress= new ArrayList<>();
    private DataSource dataSource;

    public TaskController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("registerusername")
    public boolean resisterun(@RequestBody RegisterUsername ress){
        String name = ress.getName();
        String username = ress.getEmail();
        String password =jwtUtil.generateToken(ress.getPassword());
        try {
            Connection connection = DataSourceUtils.getConnection(dataSource);
            String sql = "Insert INTO userentery (name,username,password) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
