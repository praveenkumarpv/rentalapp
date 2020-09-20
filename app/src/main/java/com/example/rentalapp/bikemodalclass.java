package com.example.rentalapp;

public class bikemodalclass {
   private String bikename;
   private String bimurl;
   private String rent;
   private String bikemodal;
   private String bikekmdriven;
   private String bmilage;
   private String bfuel;


   private  bikemodalclass(){}
    bikemodalclass(String bikename, String bimurl, String rent, String bikemodal,String bikekmdriven,String bmilage ,String bfuel){
        this.bikename=bikename;
        this.bimurl = bimurl;
        this.rent = rent;
        this.bikemodal =bikemodal;
        this.bikekmdriven = bikekmdriven;
        this.bmilage = bmilage;
        this.bfuel = bfuel;
    }

    public String getBikekmdriven() {
        return bikekmdriven;
    }

    public void setBikekmdriven(String bikekmdriven) {
        this.bikekmdriven = bikekmdriven;
    }

    public String getBmilage() {
        return bmilage;
    }

    public void setBmilage(String bmilage) {
        this.bmilage = bmilage;
    }

    public String getBfuel() {
        return bfuel;
    }

    public void setBfuel(String bfuel) {
        this.bfuel = bfuel;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getBikename() {
        return bikename;
    }

    public void setBikename(String bikename) {
        this.bikename = bikename;
    }



    public String getBimurl() {
        return bimurl;
    }

    public void setBimurl(String bimurl) {
        this.bimurl = bimurl;
    }

    public String getBikemodal() {
        return bikemodal;
    }

    public void setBikemodal(String bikemodal) {
        this.bikemodal = bikemodal;
    }

}


