package com.example.vehiclegarageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile_page extends AppCompatActivity
{

    EditText editText_name, editText_mno, editText_email, editText_address, editText_password;
    Button button_submit;
    String stringname, stringmno, stringemail, stringaddress, stringpassword;
    SharedPrefHandler sharedPrefHandler;
    String productCode;

    List<Product> productList;
String string_mno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        sharedPrefHandler = new SharedPrefHandler(this);

        string_mno=sharedPrefHandler.getSharedPreferences("usermno");

        editText_name = (EditText) findViewById(R.id.et_create_nameuqq);
        editText_mno = (EditText) findViewById(R.id.et_create_mnouqq);
        editText_email = (EditText) findViewById(R.id.et_create_emailuqq);
        editText_password = (EditText) findViewById(R.id.et_create_passworduqq);
        editText_address = (EditText) findViewById(R.id.et_create_postaluqq);

        button_submit = (Button) findViewById(R.id.btn_create_submituqq);


        getProductByCode(string_mno);





//
//        button_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stringname=editText_name.getText().toString();
//                        stringmno=editText_mno.getText().toString();
//                        stringemail=editText_email.getText().toString();
//                        stringaddress=editText_address.getText().toString();
//                        stringpassword=editText_password.getText().toString();
//
//                       // UpdateAccount();
//                Intent intent=new Intent(getApplication(),MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });

    }


    //fetch

    private void getProductByCode(final String string_mno)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Product>> call = api.getProductByCodeprofile(string_mno);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if(response.body() != null) {
                    isSuccess = true;
                }

                if(isSuccess) {
                    editText_name.setText(productList.get(0).getname());
                    editText_mno.setText(productList.get(0).getmno());
                 //   editText_address.setText(productList.get(0).getaddress());
                    editText_email.setText(productList.get(0).getemail());
                    editText_password.setText(productList.get(0).getpassword());


                    //finish();

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    }






































