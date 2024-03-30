package com.example.vehiclegarageapplication;

class Product {
//    @SerializedName("product_name")

    private String stringname;
    private String stringmno;
    private String stringemail;
  //  private String address;
    private String stringpassword;



    public Product(String stringname, String stringmno, String emaistringemaill, String stringpassword) {

        this.stringname = stringname;
        this.stringmno = stringmno;
        this.stringemail = stringemail;
        this.stringpassword = stringpassword;





    }







    public String getname() {
        return stringname;
    }

    public void setname(String stringname) {
        this.stringname = stringname;
    }




    public String getmno() {
        return stringmno;
    }

    public void setmno(String stringmno) {
        this.stringmno = stringmno;
    }

    public String getemail() {
        return stringemail;
    }

    public void setemail(String stringemail) {
        this.stringemail = stringemail;
    }



    public String getpassword() {
        return stringpassword;
    }

    public void setpassword(String stringpassword) {
        this.stringpassword = stringpassword;
    }




}
