package com.todobackend;



public class Registration {
    String name;
    Long id;
    String password;
    public Registration(){}
    public Registration(String name , Long id , String password){
        this.name=name;
        this.id=id;
        this.password=password;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return  password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
}