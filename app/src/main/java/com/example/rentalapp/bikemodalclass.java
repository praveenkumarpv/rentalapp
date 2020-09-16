package com.example.rentalapp;

public class bikemodalclass {
   private String bikename;
   private String bimurl;
   private String rent;
   private  String bikemodal;


   private  bikemodalclass(){}
    bikemodalclass(String bikename, String bimurl, String rent, String bikemodal){
        this.bikename=bikename;
        this.bimurl = bimurl;
        this.rent = rent;
        this.bikemodal =bikemodal;
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


