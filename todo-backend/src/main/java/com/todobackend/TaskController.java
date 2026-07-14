package com.todobackend;


import jdk.jfr.Registered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {
    List<Registration> res = new ArrayList<>();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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
    @PostMapping("/resister")
    public Boolean posRest(@RequestBody Registration newRes){
        for(Registration re : res){
            if(re.id.equals(newRes.id)){
                return false;
            }
        }
        newRes.password=encoder.encode(newRes.password);
        res.add(newRes);
        return true;
    }

//    @PostMapping("/resister")
//    public Registration posRest(@RequestBody Registration newRes){
//        res.add(newRes);
//        return newRes;
//    }
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
            if (re.id == id) {
                re.name = name;
                return "Name Updated successfully";
            }
        }
        return "No user found with this id";
    }
}
