package com.example.dogprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dogprofile.adapter.DogAdapter;
import com.example.dogprofile.adapter.MainDogAdapter;
import com.example.dogprofile.model.Dog;
import com.example.dogprofile.retrofit.DogApi;
import com.example.dogprofile.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.test);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Button btn = (Button) findViewById(R.id.adminButton);



        loadUsers();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Login.class);

                startActivity(i);
                finish();
            }
        });
    }



  private void loadUsers() {
    RetrofitService retrofitService = new RetrofitService();
    DogApi dogApi=retrofitService.getRetrofit().create(DogApi.class);
    dogApi.getAllDogs()
      .enqueue(new Callback<List<Dog>>() {

        @Override
        public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {

          populateListView(response.body());
        }

        @Override
        public void onFailure(Call<List<Dog>> call, Throwable t) {


          Toast.makeText(MainActivity.this,"Failed to load users", Toast.LENGTH_SHORT).show();
          Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error occured", t);

        }
      });
  }

  private void populateListView(List<Dog> dogList) {
    MainDogAdapter dogAdapter=new MainDogAdapter(dogList);
    recyclerView.setAdapter(dogAdapter);
  }
}
