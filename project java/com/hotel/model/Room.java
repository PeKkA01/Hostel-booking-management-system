package com.hotel.model;
   public class Room{
    private int  roomId;
    private String type;
    private double PricePerNight;
    private boolean Avialability;

   public Room(int roomId,String type,double PricePerNight,boolean Avialability){
        this.roomId = roomId;
        this.type = type;
        this.PricePerNight = PricePerNight;
        this.Avialability = Avialability;

   }
    public int getroomID(){ return roomId;}
    public String gettype(){return type;}
    public double getPricePerNight(){return PricePerNight;}
    public boolean getAvialability(){return Avialability;}

    public void setavialable(boolean avialable){
             this.Avialability = avialable; 
    }
   }