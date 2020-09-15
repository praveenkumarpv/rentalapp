package com.example.rentalapp;

public class modalclass {
    private  String carname;
    private  String imurl;
    private String ren;
    private  modalclass(){}
    private  modalclass(String carname,String imurl,String ren){
        this.carname=carname;
        this.imurl = imurl;
        this.ren = ren;
    }

    public String getImurl() {
        return imurl;
    }

    public void setImurl(String imurl) {
        this.imurl = imurl;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }



    public String getRen() {
        return ren;
    }

    public void setRen(String ren) {
        this.ren = ren;
    }
}
