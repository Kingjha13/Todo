package com.springpra;

public class Person {
    String id;
    String name;
    String password;
    public Person(){}
    public Person(String id , String name , String password){
        this.id=id;
        this.name=name;
        this.password=password;
    }
    public String getName(){
        return  name;
    }
    public String getId(){
        return  name;
    }
    public String getPassword(){
        return password;
    }
    public void setId(){
        this.id=id;
    }
    public void setName(){
        this.name=name;
    }
    public void setPassword(){
        this.password=password;
    }
}
