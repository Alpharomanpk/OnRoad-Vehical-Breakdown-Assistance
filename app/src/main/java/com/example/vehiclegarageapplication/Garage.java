package com.example.vehiclegarageapplication;

class Garage {
//    @SerializedName("product_name")

    private String garage_name;
    private String area_name;
    private String garage_facilities;
    private String mobile;
    private String address;



    public Garage(String garage_name, String area_name, String garage_facilities, String mobile, String address) {

        this.garage_name = garage_name;
        this.area_name = area_name;
        this.garage_facilities = garage_facilities;
        this.mobile = mobile;
        this.address = address;




    }







    public String getname() {
        return garage_name;
    }

    public void setname(String garage_name) {
        this.garage_name = garage_name;
    }




    public String getarea_name() {
        return area_name;
    }

    public void setarea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getgarage_facilities() {
        return garage_facilities;
    }

    public void setgarage_facilities(String garage_facilities) {
        this.garage_facilities = garage_facilities;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getmobile() {
        return mobile;
    }

    public void setmobile(String mobile) {
        this.mobile = mobile;
    }




}
