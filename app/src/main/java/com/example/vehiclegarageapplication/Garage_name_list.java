package com.example.vehiclegarageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Garage_name_list extends AppCompatActivity
{
    ListView listView_temp ;

    List<Garage> productList;




    ArrayAdapter<String> adapter;
    String[] products;






    SharedPrefHandler sharedPrefHandler;
    String strmno;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garage_name_list);
        sharedPrefHandler=new SharedPrefHandler(this);

        strmno=sharedPrefHandler.getSharedPreferences("garea");

        listView_temp = (ListView) findViewById(R.id.lv_temp1);


        getProductByCode(strmno);


        listView_temp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String s=listView_temp.getItemAtPosition(position).toString();
                sharedPrefHandler.setSharedPreferences("gname",s);
                Intent intent=new Intent(getApplication(),garage_details.class);
                startActivity(intent);


            }
        });



    }



    private void getProductByCode(final String strmno) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Garage>> call = api.getgaragename(strmno);

        call.enqueue(new Callback<List<Garage>>() {
            @Override
            public void onResponse(Call<List<Garage>> call, Response<List<Garage>> response) {
                // List<Product> responseResult = response.body();
                productList = response.body();

                Boolean isSuccess = false;
                if(productList != null) {
                    isSuccess = true;
                }

                if(isSuccess) {

                    // responseResult.getSuccess();
                    // Update all field with result data

                    loadProductListView();

                } else {


                }
            }

            @Override
            public void onFailure(Call<List<Garage>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }





    private void loadProductListView()
    {
        //Creating an String array for the ListView
        products = new String[productList.size()];


        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++)
        {
            products[i] = productList.get(i).getname();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        listView_temp.setAdapter(adapter);









    }
}
