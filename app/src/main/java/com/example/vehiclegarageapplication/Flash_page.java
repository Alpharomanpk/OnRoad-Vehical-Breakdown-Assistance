package com.example.vehiclegarageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Flash_page extends AppCompatActivity {
SharedPrefHandler sharedPrefHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_page);

        sharedPrefHandler=new SharedPrefHandler(this);


        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    sharedPrefHandler = new SharedPrefHandler(getApplicationContext());

                    if (sharedPrefHandler.getSharedPreferences("login").equals("NF")) {
                        sharedPrefHandler.setSharedPreferences("login", "false");
                        Intent inwhizz_gcm = new Intent(Flash_page.this, Login_page.class);
                        startActivity(inwhizz_gcm);
                    } else if (sharedPrefHandler.getSharedPreferences("login").equals("false")) {
                        Intent inwhizz_gcm = new Intent(Flash_page.this, Login_page.class);
                        startActivity(inwhizz_gcm);

                    } else if (sharedPrefHandler.getSharedPreferences("login").equals("true")) {
                        Intent i = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i);

                    }
                    finish();
                }catch (Exception e)
                {

                }


            }
        };

        thread.start();


    }
}
