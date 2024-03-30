package com.example.vehiclegarageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Create_account extends AppCompatActivity
{

    EditText editText_name,editText_mno,editText_email,editText_address,editText_password;

    Spinner spinner_city;
    RadioGroup radioGroup;
    RadioButton radioButton_male,radioButton_female;
    Button button_submit;
    String stringname,stringmno,stringemail,stringcity,stringgender,stringaddress,stringpassword;
SharedPrefHandler sharedPrefHandler;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        sharedPrefHandler=new SharedPrefHandler(this);


        editText_name=(EditText)findViewById(R.id.et_create_name);
        editText_mno=(EditText)findViewById(R.id.et_create_mno);
        editText_email=(EditText)findViewById(R.id.et_create_email);
        editText_password=(EditText)findViewById(R.id.et_create_password);
        editText_address=(EditText)findViewById(R.id.et_create_postal);

        spinner_city=(Spinner)findViewById(R.id.et_create_city);
        radioGroup=(RadioGroup)findViewById(R.id.gender_group);
        radioButton_male=(RadioButton)findViewById(R.id.gender_male);
        radioButton_female=(RadioButton)findViewById(R.id.gender_female);


        button_submit=(Button)findViewById(R.id.btn_create_submit);




        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                stringname=editText_name.getText().toString();
                stringmno=editText_mno.getText().toString();
                stringemail=editText_email.getText().toString();
                stringpassword=editText_password.getText().toString();
                stringaddress=editText_address.getText().toString();
                stringcity=spinner_city.getSelectedItem().toString();
                stringgender=(radioButton_male.isChecked()? "Male":"Female");

                sharedPrefHandler.setSharedPreferences("lmno",stringmno);
                sharedPrefHandler.setSharedPreferences("lpass",stringpassword);


                sharedPrefHandler.setSharedPreferences("uname",stringname);
                sharedPrefHandler.setSharedPreferences("mno",stringmno);
                sharedPrefHandler.setSharedPreferences("email",stringemail);
                sharedPrefHandler.setSharedPreferences("pass",stringaddress);
                sharedPrefHandler.setSharedPreferences("city",stringcity);


                SetValidation();




            }
        });


    }



    public void SetValidation() {
        // Check for a valid name.
        if (editText_name.getText().toString().isEmpty()) {
            editText_name.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else {
            isNameValid = true;
        }

        // Check for a valid email address.
        if (editText_email.getText().toString().isEmpty()) {
            editText_email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(editText_email.getText().toString()).matches()) {
            editText_email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }

        // Check for a valid phone number.
        if (editText_mno.getText().toString().isEmpty()) {
            editText_mno.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else if (!Patterns.PHONE.matcher(editText_mno.getText().toString()).matches() || editText_mno.getText().toString().length() < 10) {
            editText_mno.setError(getResources().getString(R.string.phone_error));
            isEmailValid = false;
        } else {
            isPhoneValid = true;
        }

        // Check for a valid password.
        if (editText_password.getText().toString().isEmpty()) {
            editText_password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid) {

            CreateUserAccount();

            Intent intent=new Intent(getApplication(),Login_page.class);
            startActivity(intent);
            Toast.makeText(Create_account.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
            // Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }

    }






    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<IsExist> call = api.CreateAccount(
                stringname,
                stringmno,
                stringemail,
                stringaddress,
                stringpassword,
                stringcity,
                stringgender
        );

        call.enqueue(new Callback<IsExist>() {
            @Override
            public void onResponse(Call<IsExist> call, Response<IsExist> response) {
                IsExist responseResult = response.body();

                Boolean isSuccess = false;
                if(responseResult != null) {
                    isSuccess = responseResult.getSuccess();
                }

                if(isSuccess) {
                    showCreateSuccessToast();

                } else {
                    // Show Creation Failed Message
                    showCreateFailedToast();
                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showCreateFailedToast() {
        Toast.makeText(this, "OOPS,   Create action failed!", Toast.LENGTH_LONG).show();
    }

    private void showCreateSuccessToast() {
        Toast.makeText(this, "  created successfully.", Toast.LENGTH_LONG).show();
    }

    private void showEditFailedToast() {
        Toast.makeText(this, "OOPS,   Edit action failed!", Toast.LENGTH_LONG).show();
    }

    private void showEditSuccessToast() {
        Toast.makeText(this, "  updated successfully.", Toast.LENGTH_LONG).show();
    }
}













