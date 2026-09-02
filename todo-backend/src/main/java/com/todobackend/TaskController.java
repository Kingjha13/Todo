package com.todobackend;


import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class TaskController {
    List<Registration> res = new ArrayList<>();
    List<Person> per = new ArrayList<>();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private JwtUtil jwt;

    @GetMapping("/")
    public String rea(){
        return "Hello i wish for your good career";
    }
    @GetMapping("/login")
    public List<Registration> getLogin() {return res;}
    @PostMapping("/logins")
    public String returnDetails(@RequestBody Registration newRes){
        for (Registration re : res) {
            Long id = newRes.id;
            if (newRes.id.equals(re.id) && encoder.matches(newRes.password,re.password)) {
                return "Welcome";
            }
            else if(newRes.id.equals(re.id) && !encoder.matches(newRes.password,re.password)){
                return "Password are wrong";
            }
        }
        return "Username and password are not exists";
    }
//    @PostMapping("/resister")
//    public Boolean posRest(@RequestBody Registration newRes){
//        for(Registration re : res){
//            if(re.id.equals(newRes.id)){
//                return false;
//            }
//        }
//        newRes.password=encoder.encode(newRes.password);
//        res.add(newRes);
//        return true;
//    }

    @PostMapping("/resister")
    public String posRest(@RequestBody Registration newRes){
        for(Registration re : res){
            if(re.id.equals(newRes.id)){
                return "Unable to create token because user already exists with same id";
            }
        }
        newRes.password=encoder.encode(newRes.password);
        res.add(newRes);
        return jwt.generateToken(newRes.name);
    }


    @PostMapping("/test")
    public String returns(@RequestBody String abs){
        return abs+"a";
    }
//    @GetMapping("/me")
//    public String checkMe(@RequestHeader ("Authorization") String authHeader){
//        if (jwt.validateToken(authHeader)){
//            return jwt.extractUserName(authHeader);
//        }
//        else{
//            return "Invalid Token";
//        }
//    }
    @PostMapping("/me")
    public String checkMe(@RequestBody String abs){
        if(jwt.validateToken(abs)){
            return jwt.extractUserName(abs);
        }
        return "Invalid Toke";
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody Registration newDel){
        Long id = newDel.id;
        return  ver(id);
    }
    public String ver(Long id){
        for(int i=0;i<res.size();i++){
            if(res.get(i).id.equals(id)){
                res.remove(i);
                return "Deletion completed successfully";
            }
        }
        return "No any user found with this id";
    }
    @PutMapping("/update")
    public String Update(@RequestBody Registration newUpdate){
        Long id = newUpdate.id;
        String name = newUpdate.name;
        return verify(name,id);
    }
    public String verify(String name  , Long id){
        for (Registration re : res) {
            if (Objects.equals(re.id, id)) {
                re.name = name;
                return "Name Updated successfully";
            }
        }
        return "No user found with this id";
    }

    @PostMapping("rp")
    public String resisterPerson(@RequestBody Person  pers){
        for(Person pr : per){
            if(Objects.equals(pr.id, pers.id)){
                return "User already exists with same id";
            }
        }
        per.add(pers);
        return "You have successfully resistered";
    }
    @GetMapping("/gp")
    public List<Person> getP(){
        return per;
    }
//    @GetMapping("/gp/{id}")
//    public String getUserById(@PathVariable String id){
//        return "Featching user with Id: "+id;
//    }
    @GetMapping("/gp/{city}")
    public List<Person> getUse(@PathVariable String city){
        List<Person> lt = new ArrayList<>();
        for(Person re : per){
            if (re.password != null && re.password.equalsIgnoreCase(city)) {
                lt.add(re);
            }
        }
        return lt;
    }
}
