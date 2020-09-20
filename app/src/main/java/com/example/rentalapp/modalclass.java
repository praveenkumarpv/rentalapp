package com.example.rentalapp;

public class modalclass {
    private String carname;
    private String imurl;
    private String ren;
    private String carmodal;
    private String carkm;
    private String carmilage;
    private String carfuel;

    public String getCarmodal() {
        return carmodal;
    }

    public void setCarmodal(String carmodal) {
        this.carmodal = carmodal;
    }


    private  modalclass(){}
    modalclass(String carname, String imurl, String ren, String carmodal,String carkm,String carmilage,String carfuel){
        this.carname=carname;
        this.imurl = imurl;
        this.ren = ren;
        this.carmodal = carmodal;
        this.carkm = carkm;
        this.carmilage = carmilage;
        this.carfuel = carfuel;
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
    public String getCarkm() {
        return carkm;
    }

    public void setCarkm(String carkm) {
        this.carkm = carkm;
    }

    public String getCarmilage() {
        return carmilage;
    }

    public void setCarmilage(String carmilage) {
        this.carmilage = carmilage;
    }

    public String getCarfuel() {
        return carfuel;
    }

    public void setCarfuel(String carfuel) {
        this.carfuel = carfuel;
    }
}
