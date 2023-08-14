package com.example.dogprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogprofile.retrofit.DogApi;
import com.example.dogprofile.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adopt extends AppCompatActivity {

    int dId;
    String dOwner, dEmail, dAdd, dPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adopt);

        TextView id = (TextView) findViewById(R.id.idDog);
        TextView owner = (TextView) findViewById(R.id.ownerName);
        TextView email = (TextView) findViewById(R.id.emailOwner);
        TextView address = (TextView) findViewById(R.id.addOwner);
        TextView phone = (TextView) findViewById(R.id.phoneOwner);
        ImageView img = (ImageView) findViewById(R.id.imgG);
        Button btn = (Button) findViewById(R.id.fButton);
        Button ret = (Button) findViewById(R.id.returnButton);

        img.setImageResource(R.drawable.pawprint);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        dId = sharedPref.getInt("key1",0);
        dOwner = sharedPref.getString("key2","");
        dEmail = sharedPref.getString("key3","");
        dAdd = sharedPref.getString("key4","");
        dPhone = sharedPref.getString("key5","");

        id.setText("Dog ID: "+dId);
        owner.setText("Owner Name: " + dOwner);
        email.setText("Email: " + dEmail);
        address.setText("Address: " + dAdd);
        phone.setText("Phone Number: " + dPhone);

        RetrofitService retrofitService=new RetrofitService();
        DogApi dogApi =retrofitService.getRetrofit().create(DogApi.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Void> call = dogApi.deleteDog(dId);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent i = new Intent(adopt.this, Congrats.class);

                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(adopt.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adopt.this,adoption.class);

                startActivity(i);
                finish();

            }
        });



    }
}
