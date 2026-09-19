package com.todobackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import com.todobackend.JwtUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class TaskController {
    private final JwtUtil jwtUtil;
    List<RegisterUsername> ress= new ArrayList<>();
    private final BCryptPasswordEncoder passwordEncoder;
    private DataSource dataSource;

    public TaskController(JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder, DataSource dataSource) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.dataSource=dataSource;
    }


    @GetMapping("/")
    public String ree(){
        return "Hello king Jha";
    }
    @PostMapping("/registerusername")
    public boolean resisterun(@RequestBody RegisterUsername ress){
        String name = ress.getName();
        String email = ress.getEmail();
        String password =passwordEncoder.encode(ress.getPassword());
        try {
            Connection connection = DataSourceUtils.getConnection(dataSource);
            String sql = "Insert INTO userentery (name,email,password) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
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
    @PostMapping("/verify")
    public String ver(@RequestBody String str){
        if(jwtUtil.validateToken(str)){
            return jwtUtil.extractUserName(str);
        }
        return "Something went wrong";
    }
    @PostMapping("/getname")
    public String getName(@RequestBody String token){
        if(!jwtUtil.validateToken(token)){
            return "User not Authorised";
        }
        String email = jwtUtil.extractUserName(token);
        try {
            Connection connection = DataSourceUtils.getConnection(dataSource);
            String sql = "select name from userentery where email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            String name = null;
            if(resultSet.next()){
                name = resultSet.getString("name");
            }
            connection.close();
            preparedStatement.close();
            return name;
        }
        catch (Exception e){
            return  "Something went wrong";
        }

    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody SignIn sign){
        String email = sign.getEmail();
        String password = sign.getPassword();
        try{
            Connection connection = DataSourceUtils.getConnection(dataSource);
            String sql = "select password from userentery where email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            String pass = null;
            if(resultSet.next()){
                pass=resultSet.getString("password");
            }
            preparedStatement.close();
            connection.close();
            if(pass!=null && passwordEncoder.matches(password,pass)){
                String token= jwtUtil.generateToken(email);
                return new AuthResponse(true,token);
            }
            else{
                return new AuthResponse(false,"Password are invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AuthResponse(false,"Something went wrong");
        }
    }
}

