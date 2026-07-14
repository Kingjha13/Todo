package com.todobackend;

public class Notes {
    int title;
    String description;
    boolean status;
    Notes(){}
    public Notes(int title,String description,boolean status){
        this.title=title;
        this.description=description;
        this.status=status;
    }

    public int getTitle() {
        return title;
    }
    public String getDescription(){
        return description;
    }
    public boolean getStatus(){
        return status;
    }
    public void setTitle(int x){
        this.title=x;
    }
    public void setDescription(String x){
        this.description=x;
    }
    public void setStatus(boolean x){
        this.status=x;
    }
}
