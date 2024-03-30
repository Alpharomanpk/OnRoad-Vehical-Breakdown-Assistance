package com.example.vehiclegarageapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    // String BASE_URL = "https://simplifiedcoding.net/demos/";
    // String BASE_URL = "https://fanciful-petroleum.000webhostapp.com/api/";
    String BASE_URL = "https://light-headed-tenden.000webhostapp.com/global/vehicleGarage/";



    @GET("user/login") //i.e https://fanciful-petroleum.000webhostapp.com/api/user/login?mobileNo=9035292096&password=123456
    Call<IsExist> doLogin(@Query("mno") String username, @Query("password") String password);





    @GET("https://light-headed-tenden.000webhostapp.com/global/vehicleGarage/user_profile.php")
    Call<List<Product>> getProductByCodeprofile(@Query("f1") String string_mno);



    @GET("https://light-headed-tenden.000webhostapp.com/global/vehicleGarage/garage_area_list.php")
    Call<List<Garage>> getareaname(@Query("f1") String strmno);


    @GET("https://light-headed-tenden.000webhostapp.com/global/vehicleGarage/garage_name_list.php")
    Call<List<Garage>> getgaragename(@Query("f1") String strmno);



    @GET("https://light-headed-tenden.000webhostapp.com/global/vehicleGarage/garage_details.php")
    Call<List<Garage>> getGaragedetails(@Query("f1") String string_mno,@Query("f2") String string_mno1);




    @POST("https://light-headed-tenden.000webhostapp.com/global/vehicleGarage/register.php")
    Call<IsExist> CreateAccount(
            @Query("f1") String stringname,
            @Query("f2") String stringmno,
            @Query("f3") String stringemail,
            @Query("f4") String stringaddress,
            @Query("f5") String stringpassword,
            @Query("f6") String stringcity,
            @Query("f7") String stringgender


    );






    @POST("https://light-headed-tenden.000webhostapp.com/global/vehicleGarage/garage_enquiry.php")
    Call<IsExist> UpdateAccount(
            @Query("f1") String stringname1,
            @Query("f2") String stringmno1,
            @Query("f3") String stringemail1,
            @Query("f4") String stringaddress,
            @Query("f5") String stringuname,
            @Query("f6") String stringmno,
            @Query("f7") String stringemail,
            @Query("f8") String stringpass,
            @Query("f9") String stringcity


    );











}
