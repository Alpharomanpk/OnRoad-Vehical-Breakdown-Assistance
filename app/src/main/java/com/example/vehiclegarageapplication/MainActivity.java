package com.example.vehiclegarageapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1,button2,button3,button4,button5,button6,button7,button8;
    SharedPrefHandler sharedPrefHandler;
    Button button;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefHandler=new SharedPrefHandler(this);

        button1=(Button)findViewById(R.id.profile);
        button2=(Button)findViewById(R.id.garage);
        button3=(Button)findViewById(R.id.policy);
        button4=(Button)findViewById(R.id.feedback);
        button5=(Button)findViewById(R.id.aboutApp);




        button=(Button)findViewById(R.id.logout);


        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v)
            {
                sharedPrefHandler.setSharedPreferences("login", "false");
                Intent intent=new Intent(getApplication(),Login_page.class);
                startActivity(intent);
                finish();
            }
        });








        button1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v)
            {

                Intent intent=new Intent(getApplication(),Profile_page.class);
                startActivity(intent);
            }
        });




        button2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v)
            {

                Intent intent=new Intent(getApplication(),  Garage_page.class);
                startActivity(intent);
            }
        });



        button3.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v)
            {

                Intent intent=new Intent(getApplication(),Vehicle_policy.class);
                startActivity(intent);
            }
        });




        button4.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v)
            {

                Intent intent=new Intent(getApplication(),Feedback.class);
                startActivity(intent);
            }
        });




        button5.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v)
            {

                Intent intent=new Intent(getApplication(),About_app.class);
                startActivity(intent);
            }
        });



//        button7.setOnClickListener(new android.view.View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View v)
//            {
//
//                Intent intent=new Intent(getApplication(),HelpCenter.class);
//                startActivity(intent);
//            }
//        });






    }
}
