package com.hotel.model;

public class Booking {
    private int BookingID;
    private String CustomerName;
    private int roomId;
    private String CheckIN;
    private String CheckOUT;
     
    public Booking(int BookingID,String CustomerName, int roomId,String CheckIN,String CheckOUT){
        this.BookingID = BookingID;
        this.CustomerName = CustomerName;
        this.roomId = roomId;
        this.CheckIN =  CheckIN;
        this.CheckOUT = CheckOUT;

    }
     public int getBookingId(){return BookingID;}
     public String getCustomer(){return CustomerName;}
     public int getroomID(){return roomId;}
     public String getCheckIN(){return CheckIN;}
     public String getCheckOUT(){return CheckOUT;} 

}       
