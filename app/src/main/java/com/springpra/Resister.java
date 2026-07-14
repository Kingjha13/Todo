package com.springpra;

public class Resister {
    String name;
    Long id;
    String password;
    public Resister(){}
    public Resister(String name, Long id , String password){
        this.name=name;
        this.id=id;
        this.password=password;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public long getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setId(Long id){
        this.id=id;
    }
}
