package com.example.vehiclegarageapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Garage_page extends AppCompatActivity {


    SharedPrefHandler sharedPrefHandler;
    Button  button_submit;
    Spinner spinner_dept;
    String string_sub,string_msg,string_umno,string_dated,string_dept;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage_page);


        sharedPrefHandler=new SharedPrefHandler(this);




        spinner_dept=(Spinner)findViewById(R.id.spr_garage_city);

        button_submit=(Button)findViewById(R.id.btn_garage_submit);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                string_dept=spinner_dept.getSelectedItem().toString();

               sharedPrefHandler.setSharedPreferences("city",string_dept);

                Intent intent=new Intent(getApplication(),Garage_area_name_list.class);
                startActivity(intent);







            }
        });


    }
}