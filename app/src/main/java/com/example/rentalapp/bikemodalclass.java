package com.example.rentalapp;

public class bikemodalclass {
   private String bikename;
   private String bimurl;
   private String rent;
   private  bikemodalclass(){}
    private  bikemodalclass(String bikename,String bimurl,String rent){
        this.bikename=bikename;
        this.bimurl = bimurl;
        this.rent = rent;
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

}


