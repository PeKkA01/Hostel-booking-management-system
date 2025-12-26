package com.hotel.model;
 public class Customer{
    private String Name;
    private int Phone;

    public Customer(String Name, int Phone){
        this.Name = Name;
        this.Phone = Phone;

    }
     public String getName(){return Name;}
     public int getPhone(){return Phone;}
 }