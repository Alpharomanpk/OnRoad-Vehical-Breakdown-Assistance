package com.example.vehiclegarageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class garage_details extends AppCompatActivity {

    TextView editText_name, editText_mno, editText_email, editText_address, editText_password;
    Button button_submit;

    SharedPrefHandler sharedPrefHandler;
    String productCode;

    List<Garage> productList;
    String string_mno, string_mno1;
String stringuname,stringmno,stringemail,stringpass,stringcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage_details);

        sharedPrefHandler = new SharedPrefHandler(this);

        string_mno = sharedPrefHandler.getSharedPreferences("garea");
        string_mno1 = sharedPrefHandler.getSharedPreferences("gname");


        stringuname=sharedPrefHandler.getSharedPreferences("uname");
        stringmno=sharedPrefHandler.getSharedPreferences("mno");
        stringemail=sharedPrefHandler.getSharedPreferences("email");
        stringpass=sharedPrefHandler.getSharedPreferences("pass");
        stringcity=sharedPrefHandler.getSharedPreferences("city");




        editText_name = (TextView) findViewById(R.id.t1);
        editText_mno = (TextView) findViewById(R.id.t2);
        editText_email = (TextView) findViewById(R.id.t3);
        editText_password = (TextView) findViewById(R.id.t4);
        editText_address = (TextView) findViewById(R.id.t5);

        button_submit = (Button) findViewById(R.id.btn1);


        getProductByCode(string_mno, string_mno1);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateAccount();
                Toast.makeText(garage_details.this, "Enquiry Success and Garage Person Will Contact Soon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void getProductByCode(final String string_mno, final String string_mno1) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Garage>> call = api.getGaragedetails(string_mno, string_mno1);

        call.enqueue(new Callback<List<Garage>>() {
            @Override
            public void onResponse(Call<List<Garage>> call, Response<List<Garage>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if (response.body() != null) {
                    isSuccess = true;
                }

                if (isSuccess) {
                    editText_name.setText(productList.get(0).getname());
                    editText_mno.setText(productList.get(0).getgarage_facilities());
                    editText_address.setText(productList.get(0).getmobile());
                    editText_email.setText(productList.get(0).getaddress());


                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Garage>> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpdateAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.UpdateAccount(
                editText_name.getText().toString(),
                editText_mno.getText().toString(),
                editText_address.getText().toString(),
                editText_email.getText().toString(),
                stringuname,stringmno,stringemail,stringpass,stringcity
        );

        call.enqueue(new Callback<IsExist>() {
            @Override
            public void onResponse(Call<IsExist> call, Response<IsExist> response) {
                IsExist responseResult = response.body();

                Boolean isSuccess = false;
                if(responseResult != null) {
                    isSuccess = responseResult.getSuccess();
                }

                if(isSuccess)
                {


                } else {
                    // Show Creation Failed Message

                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
